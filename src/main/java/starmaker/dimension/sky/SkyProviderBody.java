package starmaker.dimension.sky;

import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import org.lwjgl.opengl.GL11;

import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.StarType;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.FMLClientHandler;
import starmaker.utils.MakerUtils;
import starmaker.utils.data.DimData;

public class SkyProviderBody extends SkyProviderBase {

	private final DimData data;
	private BodiesData body_data;
	public SkyProviderBody(DimData data)
	{
		this.data = data;
		this.body_data = BodiesRegistry.getData(data.getBody());

	}
	
	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float size, float ticks) {

		if(this.data.getBody() instanceof Satellite) {
			Satellite sat = (Satellite) this.data.getBody();
			this.renderImage(sat.getParentPlanet().getBodyIcon(), 0F, 0F, 0F, 180F, 1.0F, .4F);
		}

		if(this.data.getBody() instanceof Moon)
		{
			Planet parent = ((Moon)this.data.getBody()).getParentPlanet();
			float s = ((Moon)this.data.getBody()).getRelativeDistanceFromCenter().scaledDistance;
			s = getMaxDistance() - s * 0.5F * this.data.getPlanetSize();
			float speed = ((Moon)this.data.getBody()).getRelativeOrbitTime();
			float x = this.mc.world.getCelestialAngle(ticks) * -360.0F / 10;
			float y = this.mc.world.getCelestialAngle(ticks) * 360.0F + 120F;
			this.renderImage(parent.getBodyIcon(), x, y, 0, s, 1.0F);
			
			DimData parentData = MakerUtils.bodies.get(parent.getDimensionID());
			GL11.glPushMatrix(); 
			float f = 0.9F;

			if(parentData == null)
				parentData = MakerUtils.unreachable_bodies.get(parent);


			if(parentData != null && parentData.getSkyColor() != null)
				this.renderAtmo(tessellator, x, y, s - 0.4F, new Vec3d(parentData.getSkyColor().x / 255.0F * f, parentData.getSkyColor().y / 255.0F * f, parentData.getSkyColor().z / 255.0F * f));
			GL11.glPopMatrix(); 
		}
		
		if(getStarData().getStarType() == StarType.BLACKHOLE) {
		
			//GlStateManager.enableAlpha();
			//GlStateManager.alphaFunc(GL11.GL_GREATER, 0.08f);
			//renderImage(NewGuiCelestialSelection.vortexTexture, 0F, 0F, this.mc.world.getCelestialAngle(ticks) * 360.0F + 180F, size + 25F);

			GlStateManager.depthMask(false);
			GlStateManager.pushMatrix();
			
				GlStateManager.rotate(this.getCelestialAngle(getDayLength()) + 40F, 0, 0, 1);
				GlStateManager.pushMatrix();
					
					
					
					
					//GlStateManager.rotate(this.getCelestialAngle(getDayLength() / 50), 0, 0, 1);
		
					GlStateManager.translate(0, 120F, 0);
					
					GlStateManager.rotate(180, -1, 1, 0);
					GlStateManager.rotate(this.getCelestialAngle(getDayLength() / 50), 0, 1, 0);
					
					
					float f10 = size + 40F;
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(NewGuiCelestialSelection.vortexTexture);
		
					buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
					buffer.pos(-f10, -100.0D, f10).tex(0, 1).color(1, 1, 1, 1.0F).endVertex();
					buffer.pos(f10, -100.0D, f10).tex(1, 1).color(1, 1, 1, 1.0F).endVertex();
					buffer.pos(f10, -100.0D, -f10).tex(1, 0).color(1, 1, 1, 1.0F).endVertex();
					buffer.pos(-f10, -100.0D, -f10).tex(0, 0).color(1, 1, 1, 1.0F).endVertex();
					tessellator.draw();
				GlStateManager.popMatrix();
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
				//GlStateManager.enableAlpha();
				//GlStateManager.alphaFunc(GL11.GL_GREATER, 0.08f);
				//renderImage(getStar().getBodyIcon(), 10F, 0F, this.getCelestialAngle(getDayLength()) + 180F, size - 20F);
				//GlStateManager.disableAlpha();	
			GlStateManager.popMatrix();
			
			

			GlStateManager.depthMask(true);
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			GlStateManager.depthMask(false);
			//
			renderImage(getStar().getBodyIcon(), 0F, 0F, this.getCelestialAngle(getDayLength()) + 180F, size);

			//GlStateManager.disableAlpha();	
		}

		if(this.data.getRingOnSkyTexture() != null) {
			GlStateManager.pushMatrix();
			GL11.glScalef(0.8F, 0.6F, 0.8F);
			renderImage(data.getRingOnSkyTexture(), this.getCelestialAngle(getDayLength()) - 180F, 80, 0, 500);
			GlStateManager.popMatrix();
		}

		GL11.glPushMatrix();
		GL11.glRotatef(this.mc.world.getCelestialAngle(ticks) * 360.0F, 0.0F, 0.0F, 1.0F);  
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		int i = 0;
		for(Planet planet : GalaxyRegistry.getPlanetsForSolarSystem(getSolarSystem()))
		{
			BodiesData data = BodiesRegistry.getData(planet);
			if(data != null && data.getType() == TypeBody.STAR) {
				float distance = planet.getRelativeDistanceFromCenter().scaledDistance;
				distance *= 40;
				if(planet.getPhaseShift() < 0 && planet.getPhaseShift() > Math.PI)
					distance *= -1;
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F); 
				GL11.glRotatef(5 - (8 * i), 1.0F, 0.0F, 0.0F); 
				GL11.glRotatef(20 - (12 * i) + distance, 0.0F, 0.0F, 1.0F);
				this.renderSunAura(tessellator, 3.0F + planet.getRelativeSize(), 0.7F, data.getStarColor());
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
				renderImage(planet.getBodyIcon(), -90F, 185F - (8 * i), -20 + (12 * i) - distance, 1.0F + planet.getRelativeSize());
				i += 2;
				
				
			}
		}
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();


	}

	@Override
	protected boolean enableBaseImages() {
		return true;
	}

	@Override
	protected ModeLight modeLight() {
		
		BodiesData bd = null;
		
		if(data.getBody() instanceof Planet) {
			Star star = ((Planet)data.getBody()).getParentSolarSystem().getMainStar();
			bd = BodiesRegistry.getData(star);
		}
		
		if(data.getBody() instanceof IChildBody)
			bd = BodiesRegistry.getData(((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar());
		
		if(bd != null && bd.getStarType() == StarType.BLACKHOLE) return ModeLight.DEFAULT;
		
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
		if(data.getSunTexture() != null) 
			return data.getSunTexture();
		return getSolarSystem().getMainStar().getBodyIcon();
	}

	@Override
	protected StarColor colorSunAura() {
		Star star = null;
		BodiesData bd = null;
		
		if(data.getBody() instanceof Planet) 
			star = ((Planet)data.getBody()).getParentSolarSystem().getMainStar();
					
		
		if(data.getBody() instanceof IChildBody)
			star = ((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar();
	
		if(star != null)
			bd = BodiesRegistry.getData(star);
		
		if(star == GalacticraftCore.solarSystemSol.getMainStar())
			bd = null;
		
		return bd != null ? bd.getStarColor() : StarColor.WHITE;
	}
	
	private SolarSystem getSolarSystem() {
		if(data.getBody() instanceof Planet)
			return ((Planet)data.getBody()).getParentSolarSystem();
		
		if(data.getBody() instanceof IChildBody)
			return ((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem();
		
		return GalacticraftCore.planetOverworld.getParentSolarSystem();
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
		
		if(bd != null && bd.getStarType() == StarType.BLACKHOLE) return false;
		
		return true;
	}
	   
	@Override
	public boolean enableSmoothRender() {return sunSize() < 2.0F ? false : data.getSkyColor().length() == 0;}

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
	
	@Override
	public boolean enableRenderPlanet() {
		return this.body_data.getType() != TypeBody.ASTEROID;
	}
}
