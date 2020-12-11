package starmaker.proxy;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	public void preload() {		
	}

	public void load() {}

	public void postload() {}
	
	public void register_event(Object obj)
	{
    	MinecraftForge.EVENT_BUS.register(obj);
	}
}
