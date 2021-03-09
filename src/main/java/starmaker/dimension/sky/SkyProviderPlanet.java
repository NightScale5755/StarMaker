package starmaker.dimension.sky;

import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import starmaker.StarMaker.DimData;

public class SkyProviderPlanet extends SkyProviderBase {

	private final DimData data;
	public SkyProviderPlanet(DimData data)
	{
		this.data = data;
	}
	
	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float size, float ticks) {
		
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
		return this.data.getSunSize();
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
		
		if(data.getBody() instanceof Planet)
			bd = BodiesRegistry.getData(((Planet)data.getBody()).getParentSolarSystem().getMainStar());
		
		if(data.getBody() instanceof IChildBody)
			bd = BodiesRegistry.getData(((IChildBody)data.getBody()).getParentPlanet().getParentSolarSystem().getMainStar());
		
		return bd != null ? bd.getStarColor() : StarColor.YELLOW;
	}

	@Override
	protected Vector3 getAtmosphereColor() {
		return null;
	}

}
