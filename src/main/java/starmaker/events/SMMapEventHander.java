package starmaker.events;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		/*for(DimData data : MakerUtils.bodies.values()) {
			if (event.celestialBody == data.getBody() && data.getRingTexture() != null)
	    	{
	            minecraft.renderEngine.bindTexture(data.getRingTexture());
	            float size = ((GuiCelestialSelection)minecraft.currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
	            ((GuiCelestialSelection)minecraft.currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 16.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 30, 7);
	    	}
		}*/
    }
}
