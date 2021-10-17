package starmaker.dimension.sky;

import org.lwjgl.opengl.GL11;

import asmodeuscore.api.dimension.IAdvancedSpace.StarClass;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import starmaker.StarMaker;
import starmaker.utils.data.DimData;

public class SkyProviderPlanet extends SkyProviderBase {

	private final DimData data;
	public SkyProviderPlanet(DimData data)
	{
		this.data = data;			
	}
	
	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float size, float ticks) {
		
		
		if(this.data.getBody() instanceof Moon)
		{
			Planet parent = ((Moon)this.data.getBody()).getParentPlanet();
			float s = ((Moon)this.data.getBody()).getRelativeDistanceFromCenter().scaledDistance;
			s = getMaxDistance() - s * 0.5F;
			float speed = ((Moon)this.data.getBody()).getRelativeOrbitTime();
			float x = this.mc.world.getCelestialAngle(ticks) * -360.0F / 10;
			float y = this.mc.world.getCelestialAngle(ticks) * 360.0F + 120F;
			this.renderImage(parent.getBodyIcon(), x, y, 0, s);
			
			DimData parentData = StarMaker.bodies.get(parent.getDimensionID());
			GL11.glPushMatrix(); 
			float f = 0.9F;
			this.renderAtmo(tessellator, x, y, s - 0.4F, new Vector3(parentData.getSkyColor().x / 255.0F * f, parentData.getSkyColor().y / 255.0F * f, parentData.getSkyColor().z / 255.0F * f));
			GL11.glPopMatrix(); 
		}
		
		if(getStarData().getStarClass() == StarClass.BLACKHOLE) {
		
			renderImage(NewGuiCelestialSelection.vortexTexture, 0F, 0F, this.mc.world.getCelestialAngle(ticks) * 360.0F + 180F, size + 25F);
			renderImage(getStar().getBodyIcon(), 0F, 0F, this.mc.world.getCelestialAngle(ticks) * 360.0F + 180F, size);
			
		}
	}

	@Override
	protected boolean enableBaseImages() {
		return true;
	}

	@Override
	protected ModeLight modeLight() {
		return ModeLight.DEFAULT;
	}

	@Override
	protected float sunSize() {
		return this.data.getSunSize() < -1F ? 10-this.data.getBody().getRelativeDistanceFromCenter().unScaledDistance : this.data.getSunSize();
	}

	@Override
	protected boolean enableStar() {
		return true;
	}

	@Override
	protected ResourceLocation sunImage() {
		if(data.getBody() instanceof Planet)
			return ((Planet)data.getBody()).getParentSolarSystem().getMainStar().getBodyIcon();
		
		if(data.getBody() instanceof IChildBody)
			return ((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar().getBodyIcon();
		
		return GalacticraftCore.planetOverworld.getParentSolarSystem().getMainStar().getBodyIcon();
	}

	@Override
	protected StarColor colorSunAura() {
		BodiesData bd = null;
		
		if(data.getBody() instanceof Planet) {
			Star star = ((Planet)data.getBody()).getParentSolarSystem().getMainStar();
			bd = BodiesRegistry.getData(star);
		}
		
		if(data.getBody() instanceof IChildBody)
			bd = BodiesRegistry.getData(((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar());
		
		return bd != null ? bd.getStarColor() : StarColor.WHITE;
	}

	@Override
	protected Vector3 getAtmosphereColor() {
		return null;
	}
	
	@Override
	public int expandSizeAura() {return sunSize() < 2.0F ? -2 : 0;}
	
	@Override
	public boolean enableLargeSunAura() {
		BodiesData bd = null;
		
		if(data.getBody() instanceof Planet) {
			Star star = ((Planet)data.getBody()).getParentSolarSystem().getMainStar();
			bd = BodiesRegistry.getData(star);
		}
		
		if(data.getBody() instanceof IChildBody)
			bd = BodiesRegistry.getData(((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar());
		
		if(bd.getStarClass() == StarClass.BLACKHOLE) return false;
		
		return true;
	}
	   
	@Override
	public boolean enableSmoothRender() {return sunSize() < 2.0F ? false : data.getBody().atmosphere.hasNoGases();}

	private float getMaxDistance()
	{
		float max = -1F;
		if(this.data.getBody() instanceof Moon)
		{
			Planet parent = ((Moon)this.data.getBody()).getParentPlanet();
			for(Moon moon : GalaxyRegistry.getMoonsForPlanet(parent))
			{
				if(moon.getRelativeDistanceFromCenter().unScaledDistance > max)
					max = moon.getRelativeDistanceFromCenter().unScaledDistance;
			}
			
		
		}
		return max;
	}
	
	private Star getStar() {
		Star star = null;
	
		if(data.getBody() instanceof Planet) 
			star = ((Planet)data.getBody()).getParentSolarSystem().getMainStar();
		if(data.getBody() instanceof IChildBody)
			star = ((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar();
		
		return star;
	}
	
	private BodiesData getStarData() {
		BodiesData bd = BodiesRegistry.getData(getStar());
		return bd;
	}
	
}
