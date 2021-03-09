package starmaker.dimension;

import java.util.List;
import java.util.Random;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProviderSpace;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace;
import asmodeuscore.core.astronomy.dimension.world.worldengine.biome.WE_BaseBiome;
import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.WE_ChunkProvider;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_CaveGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_OreGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_RavineGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_TerrainGenerator;
import galaxyspace.core.client.fx.ParticleRainCustom;
import galaxyspace.systems.SolarSystem.moons.titan.dimension.sky.WeatherProviderTitan;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IWeatherProvider;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starmaker.StarMaker;
import starmaker.StarMaker.BiomeData;
import starmaker.StarMaker.DimData;
import starmaker.StarMaker.OreGenData;
import starmaker.dimension.sky.SkyProviderPlanet;
import starmaker.dimension.sky.WeatherProviderPlanet;
import starmaker.utils.ParseConfig;

public class WorldProviderPlanet extends WE_WorldProviderSpace implements IWeatherProvider {
	
	private static int getId;
	
	private DimData getDimData()
	{
		return StarMaker.bodies.get(this.getDimension());
	}
	
	@Override
	public CelestialBody getCelestialBody() {
		if(getDimData() != null)
		{
			//StarMaker.debug(StarMaker.bodies.get(this.getDimension()).getBody().getName());
			return getDimData().getBody(); 
		}
		return GalacticraftCore.moonMoon;
	}
	
	@Override
	public double getFuelUsageMultiplier() {
		return 1.0D;
	}

	@Override
	public float getFallDamageModifier() {
		return 1.0F;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public double getMeteorFrequency() {
		return (3 - (getSkyColor().x + getSkyColor().y + getSkyColor().z)) * 10;
	}
	 
	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(getDimData().getFogColor().x / 255.0F * f, getDimData().getFogColor().y / 255.0F * f, getDimData().getFogColor().z / 255.0F * f);
	}

	@Override
	public Vector3 getSkyColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(getDimData().getSkyColor().x / 255.0F * f, getDimData().getSkyColor().y / 255.0F * f, getDimData().getSkyColor().z / 255.0F * f);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
    	float f = this.world.getCelestialAngle(par1);
        float f1 = 1.0F - (MathHelper.cos(f * ((float)Math.PI * 2F)) * 2.0F + 0.25F);
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
	public boolean hasSunset() {
		return false;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return WE_ChunkProviderSpace.class;
	}

	@Override
	public DimensionType getDimensionType() {		
		return WorldUtil.getDimensionTypeById(this.getDimension());
	}

	@Override
	public boolean enableAdvancedThermalLevel() {
		return true;
	}
	 
	@Override
	public IRenderHandler getCloudRenderer() {
		return new CloudRenderer();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getWeatherRenderer()
    {
        return new WeatherProviderPlanet(getDimData());
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public Particle getParticle(WorldClient world, double x, double y, double z)
    {
		if(getDimData().getBody().atmosphere.thermalLevel() < 0.0) return null;
		
        return new ParticleRainCustom(world, x, y + 0.1D, z, 0.0D, 0.0D, 0.0D, EnumParticleTypes.SMOKE_NORMAL.getParticleID(), 1.0F, new Vector3(1F, 0.4F, 0.0F));
    }
	
	@Override
	@SideOnly(Side.CLIENT)	
    public IRenderHandler getSkyRenderer()
    {
    	if (super.getSkyRenderer() == null)
		{
			this.setSkyRenderer(new SkyProviderPlanet(getDimData()));
		}
    	
		return super.getSkyRenderer();
    }
    

	@Override
	public void onChunkProvider(int cX, int cZ, ChunkPrimer primer) {
	
	}

	@Override
	public void onPopulate(int cX, int cZ) {
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}

	@Override
	public void genSettings(WE_ChunkProvider cp) {
		cp.createChunkGen_List .clear(); 
		cp.createChunkGen_InXZ_List .clear(); 
		cp.createChunkGen_InXYZ_List.clear(); 
		cp.decorateChunkGen_List .clear(); 
		cp.biomesList.clear();
		((WE_ChunkProviderSpace)cp).worldGenerators.clear();		
		((WE_ChunkProviderSpace)cp).CRATER_PROB = getDimData().getCrateProb();
		
		WE_Biome.setBiomeMap(cp, 1.4D, 4, getDimData().getMapSize(), 1.0D);		
			
		WE_TerrainGenerator terrainGenerator = new WE_TerrainGenerator(); 
		terrainGenerator.worldStoneBlock = ParseConfig.getBlock(getDimData().getStoneBlock()); 
		terrainGenerator.worldSeaGen = !getDimData().getWaterBlock().isEmpty();
		terrainGenerator.worldSeaGenBlock = ParseConfig.getBlock(getDimData().getWaterBlock());
		terrainGenerator.worldSeaGenMaxY = getDimData().getWaterY();
		cp.createChunkGen_List.add(terrainGenerator);
		
		if(getDimData().getGenCaves()) {
			WE_CaveGen cg = new WE_CaveGen(); 
			cg.replaceBlocksList.clear(); 
			cg.addReplacingBlock(terrainGenerator.worldStoneBlock); 
			cg.lavaMaxY = 15;
			cp.createChunkGen_List.add(cg); 
		}
		
		if(getDimData().getGenRavines()) {
			WE_RavineGen rg = new WE_RavineGen();
			rg.replaceBlocksList.clear();
			rg.addReplacingBlock(terrainGenerator.worldStoneBlock);
			rg.lavaBlock = Blocks.LAVA.getDefaultState();
			rg.lavaMaxY = 15;		
			cp.createChunkGen_List.add(rg);
		}
		
		
		
		double distance = 0D;
		for(BiomeData biome : getDimData().getBiomes()) {
			WE_BiomeLayer layer = new WE_BiomeLayer();		
			layer.add(ParseConfig.getBlock(biome.getSubsurfaceBlock()), terrainGenerator.worldStoneBlock, -256, 0, -4, -2, true);
			layer.add(ParseConfig.getBlock(biome.getSurfaceBlock()), ParseConfig.getBlock(biome.getSubsurfaceBlock()), -256, 0, -1, 0, false);
			layer.add(Blocks.BEDROCK.getDefaultState(), 0, 0, 1, 2, true);
		
			if(!biome.getOreGenData().isEmpty()) {
				WE_OreGen standardOres = new WE_OreGen();
			
				for(OreGenData oregen : biome.getOreGenData())
				{
					standardOres.add(ParseConfig.getBlock(oregen.getOre()), ParseConfig.getBlock(oregen.getReplaced()), oregen.getBlockCount(), oregen.getMinY(), oregen.getMaxY(), oregen.getAmountPerChunk());
				}				
				cp.decorateChunkGen_List.add(standardOres);
			}
			WE_Biome.addBiomeToGeneration(cp, new WE_BaseBiome(distance, biome.getPersistance(), biome.getOctaves(), biome.getHeight(), biome.getIntquility(), layer) {
				
				@Override
				public void decorateBiome(World world, Random rand, int x, int z)
				{
				}
				
			}.setSize(280.0D, 1.5D).setBaseSpawn());
			distance += biome.getBiomeSize();			
		}
	}

	@Override
	public void weatherSounds(int j, Minecraft mc, World world, BlockPos blockpos, double xx, double yy, double zz,	Random random) {
		if(getDimData().getBody().atmosphere.thermalLevel() < 0.0) return;
		
		if ((int) yy >= blockpos.getY() + 1 && world.getPrecipitationHeight(blockpos).getY() > blockpos.getY()) {
			mc.world.playSound(xx, yy, zz, SoundEvents.WEATHER_RAIN_ABOVE, SoundCategory.WEATHER, 0.8F, 0.6F + random.nextFloat() * 0.2F, false);
		} else {
			mc.world.playSound(xx, yy, zz, SoundEvents.WEATHER_RAIN, SoundCategory.WEATHER, 0.8F, 0.8F + random.nextFloat() * 0.06F + random.nextFloat() * 0.06F, false);
		}
	}

	@Override
	public int getSoundInterval(float rainStrength) {
		if(getDimData().getBody().atmosphere.thermalLevel() < 0.0) return 1;
		
		int result = 80 - (int)(rainStrength * 88F);
        return result > 0 ? result : 1;
	}

}
