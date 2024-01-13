package starmaker.events;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starmaker.utils.MakerUtils;
import starmaker.utils.data.DimData;

public class SMMapEventHander {

	final Minecraft minecraft = FMLClientHandler.instance().getClient();
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onBodyRender(CelestialBodyRenderEvent.Pre renderEvent)
	{
		
	}
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderPlanetPost(CelestialBodyRenderEvent.Post event)
    {
		DimData data = null;

		if(event.celestialBody.getReachable()) {
			data = MakerUtils.bodies.get(event.celestialBody.getDimensionID());
		}
		else {
			data = MakerUtils.unreachable_bodies.get(event.celestialBody);
		}

		if(data != null && data.getRingOnMapTexture() != null) {
			minecraft.renderEngine.bindTexture(data.getRingOnMapTexture());
			float size = ((GuiCelestialSelection)minecraft.currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
			((GuiCelestialSelection)minecraft.currentScreen).drawTexturedModalRect(-8.0F * size, -8.0F * size, 16.0F * size, 16.0F * size, 0, 0, 32, 32, false, false, 32, 32);

		}
    }
}
