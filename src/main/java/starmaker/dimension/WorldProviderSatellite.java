package starmaker.dimension;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import asmodeuscore.core.astronomy.sky.CustomCloudRender;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.IOrbitDimension;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.api.world.IZeroGDimension;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starmaker.dimension.sky.SkyProviderBody;
import starmaker.dimension.sky.WeatherProviderBody;
import starmaker.utils.MakerUtils;
import starmaker.utils.data.DimData;
import starmaker.world.BiomeProviderBody;

public class WorldProviderSatellite extends WorldProviderSpaceStation
		implements IOrbitDimension, IZeroGDimension, ISolarLevel, IExitHeight {

	Set<Entity> freefallingEntities = new HashSet<Entity>();

	protected DimData getDimData() {
		int id = -1;
		for(Entry<String, Integer> map : GalaxyRegistry.getRegisteredSatelliteIDs().entrySet()) {
			if(map.getValue() == this.getDimension()) {
				Satellite s = GalaxyRegistry.getRegisteredSatellites().get(map.getKey());
				id = s.getDimensionID();
			}
		}
		System.out.println(id + " | " + MakerUtils.bodies + " | " + MakerUtils.bodies.get(id));
		return MakerUtils.bodies.get(id);
	}

	@Override
	public CelestialBody getCelestialBody() {
		if (getDimData() != null) {
			// StarMaker.debug(StarMaker.bodies.get(this.getDimension()).getBody().getName());
			return getDimData().getBody();
		}

		return GalacticraftCore.moonMoon;
	}

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass()
    {
        return ChunkProviderSatellite.class;
    }
    
    @Override
    public Class<? extends BiomeProvider> getBiomeProviderClass()
    {
    	return BiomeProviderBody.class;
    }
    
	@Override
	public DimensionType getDimensionType() {
		int id = -1;
		for(Entry<String, Integer> map : GalaxyRegistry.getRegisteredSatelliteIDs().entrySet()) {
			if(map.getValue() == this.getDimension()) {
				Satellite s = GalaxyRegistry.getRegisteredSatellites().get(map.getKey());
				id = s.getDimensionID();
			}
		}

		
		return WorldUtil.getDimensionTypeById(id);
	}

	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public boolean isDaytime() {
		final float a = this.world.getCelestialAngle(0F);
		// TODO: adjust this according to size of planet below
		return a < 0.42F || a > 0.58F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(getDimData().getFogColor().x / 255.0F * f, getDimData().getFogColor().y / 255.0F * f,
				getDimData().getFogColor().z / 255.0F * f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vector3 getSkyColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(getDimData().getSkyColor().x / 255.0F * f, getDimData().getSkyColor().y / 255.0F * f,
				getDimData().getSkyColor().z / 255.0F * f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		float f = this.world.getCelestialAngle(par1);
		float f1 = 1.0F - (MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.25F);
		f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
		return f1 * f1 * 0.5F + getDimData().getStarBrightness();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 1.0F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
		f2 = MathHelper.clamp(f2, 0.0F, 1.0F);

		f2 = 1.0F - f2;

		return f2 * getDimData().getSunBrightness();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getCloudRenderer() {

		if (getDimData().getCloudColor() == null)
			return new CloudRenderer();

		if (super.getCloudRenderer() == null) {

			float[] f = { 1.0F };
			CustomCloudRender cloud = new CustomCloudRender(f) {

				@Override
				public ResourceLocation getCloudTexture() {
					return default_clouds;
				}

				@Override
				public Vec3d getCloudColor(float renderPartialTicks) {
					float f = this.mc.world.provider.getSunBrightness(1.0F);

					return new Vec3d(getDimData().getCloudColor().x * f, getDimData().getCloudColor().y * f,
							getDimData().getCloudColor().z * f);

					// return getDimData().getCloudColor();
				}

				@Override
				public float getCloudMovementSpeed(WorldClient world) {
					return 1F;
				}
			};
			this.setCloudRenderer(cloud);
		}
		return super.getCloudRenderer();

	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {

		return getDimData().getCloudColor() != null ? getDimData().getCloudHeight() : 0.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getWeatherRenderer() {
		return new WeatherProviderBody(getDimData());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer() {
		if (super.getSkyRenderer() == null) {
			this.setSkyRenderer(new SkyProviderBody(getDimData()));
		}

		return super.getSkyRenderer();
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return tier > 2;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public float getFallDamageModifier() {
		return 1F;
	}

	@Override
	public double getFuelUsageMultiplier() {
		return 1F;
	}

	@Override
	public float getGravity() {
		return getDimData().getGravity();
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public double getYCoordinateToTeleport() {
		return 1000;
	}

	@Override
	public double getSolarEnergyMultiplier() {
		return 0;
	}

	@Override
	public boolean inFreefall(Entity entity) {
		return freefallingEntities.contains(entity);
	}

	@Override
	public void setInFreefall(Entity entity) {
		freefallingEntities.add(entity);
	}

	@Override
	public void updateWeather() {
		freefallingEntities.clear();
		super.updateWeather();
	}

	@Override
	public String getPlanetToOrbit() {
		Satellite body = (Satellite)getCelestialBody();
		
		return body.getParentPlanet().getUnlocalizedName();
	}

	@Override
	public int getYCoordToTeleportToPlanet() {
		return 5;
	}

	@Override
	public void createSkyProvider() {
        if (this.getCloudRenderer() == null)
            this.setCloudRenderer(super.getCloudRenderer());
	}

	@Override
	public float getSkyRotation() {
		return 0;
	}

	@Override
	public void setSpinDeltaPerTick(float arg0) {
	}

	@Override
	public long getDayLength() {
		return getDimData().getDayLenght();
	}
}
