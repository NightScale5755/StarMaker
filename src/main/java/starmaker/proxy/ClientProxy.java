package starmaker.proxy;

import starmaker.events.SMMapEventHander;

public class ClientProxy extends CommonProxy{

	@Override
	public void preload() {
		register_event(new SMMapEventHander());
	}

	@Override
	public void load() {

	}

	@Override
	public void postload() {

	}
}
