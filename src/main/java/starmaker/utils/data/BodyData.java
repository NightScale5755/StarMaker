package starmaker.utils.data;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.world.DimensionType;

public class BodyData {
	private CelestialBody body;
	private DimensionType dimtype;
	
	public BodyData(CelestialBody body, DimensionType type)
	{
		this.body = body;
		this.dimtype = type;
	}
	
	public CelestialBody getCelestialBody()
	{
		return body;
	}
	
	public DimensionType getDimensionType()
	{
		return dimtype;
	}
}
