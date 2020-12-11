package starmaker.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SMEventHandler {

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent e)
	{
		if(e.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) e.getEntityLiving();
			
			/*for(DimensionType i : DimensionManager.getRegisteredDimensions().keySet())
				StarMaker.debug(i);*/
			
			//StarMaker.debug(StarMaker.bodies.get(-1150).getData().getDayLengthPlanet());
		}
		
		
	}
}
