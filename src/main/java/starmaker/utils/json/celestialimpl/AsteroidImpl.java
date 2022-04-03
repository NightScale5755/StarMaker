
package starmaker.utils.json.celestialimpl;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import starmaker.utils.json.data.OrbitDataImpl;
import starmaker.utils.json.data.OreGenImpl;

public class AsteroidImpl
{

	@SerializedName("parent_system")
	@Expose
	private String parentSystem;	
	@SerializedName("orbit_data")
	@Expose
	private OrbitDataImpl orbitData;			
	@SerializedName("temperature")
	@Expose
	private float temperature;	
	@SerializedName("solar_radiation")
	@Expose
	private boolean solarRadiation;	
	@SerializedName("sun_brightness")
	@Expose
	private double sunBrightness;
	@SerializedName("star_brightness")
	@Expose
	private double starBrightness;	
	@SerializedName("tier")
	@Expose
	private int tier;	
	@SerializedName("sun_size")
	@Expose
	private float sun_size;
	@SerializedName("unreachable")
	@Expose
	private boolean unreachable;
	@SerializedName("oregen")
	@Expose
	private List<OreGenImpl> oregen = null;

	public AsteroidImpl()
	{
	}

	public AsteroidImpl(String parentSystem, OrbitDataImpl orbitData,
			float temperature, boolean solarRadiation, double sunBrightness,
			double starBrightness, int tier, float sun_size, boolean unreachable, List<OreGenImpl> oregen)
	{
		super();
		this.parentSystem = parentSystem;		
		this.orbitData = orbitData;
		this.temperature = temperature;
		this.solarRadiation = solarRadiation;
		this.sunBrightness = sunBrightness;
		this.starBrightness = starBrightness;
		this.tier = tier;
		this.sun_size = sun_size;
		this.unreachable = unreachable;
		this.oregen = oregen;
	}

	public String getParentSystem()
	{
		return parentSystem;
	}

	public void setParentSystem(String parentSystem)
	{
		this.parentSystem = parentSystem;
	}

	public AsteroidImpl withParentSystem(String parentSystem)
	{
		this.parentSystem = parentSystem;
		return this;
	}

	public OrbitDataImpl getOrbitData()
	{
		return orbitData;
	}

	public void setOrbitData(OrbitDataImpl orbitData)
	{
		this.orbitData = orbitData;
	}

	public AsteroidImpl withOrbitData(OrbitDataImpl orbitData)
	{
		this.orbitData = orbitData;
		return this;
	}
	
	public float getTemperature()
	{
		return temperature;
	}

	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	}

	public AsteroidImpl withTemperature(float temperature)
	{
		setTemperature(temperature);
		return this;
	}
	
	public boolean getSolarRadiation()
	{
		return solarRadiation;
	}

	public void setSolarRadiation(boolean solarRadiation)
	{
		this.solarRadiation = solarRadiation;
	}

	public AsteroidImpl withSolarRadiation(Boolean solarRadiation)
	{
		this.solarRadiation = solarRadiation;
		return this;
	}

	public Float getSunBrightness()
	{
		return (float) sunBrightness;
	}

	public void setSunBrightness(double sunBrightness)
	{
		this.sunBrightness = sunBrightness;
	}

	public AsteroidImpl withSunBrightness(double sunBrightness)
	{
		this.sunBrightness = sunBrightness;
		return this;
	}

	public Float getStarBrightness()
	{
		return (float) starBrightness;
	}

	public void setStarBrightness(double starBrightness)
	{
		this.starBrightness = starBrightness;
	}

	public AsteroidImpl withStarBrightness(double starBrightness)
	{
		this.starBrightness = starBrightness;
		return this;
	}
	
	public void setSunSize(float size)
	{
		this.sun_size = size;
	}
	
	public float getSunSize()
	{
		return this.sun_size;
	}
	
	public AsteroidImpl withSunSize(float size)
	{
		this.sun_size = size;
		return this;
	}
	
	public void setTier(int tier)
	{
		this.tier = tier;
	}
	
	public int getTier()
	{
		return this.tier;
	}
	
	public AsteroidImpl withTier(int tier)
	{
		this.tier = tier;
		return this;
	}
	
	public void setUnreachable(boolean unreachable)
	{
		this.unreachable = unreachable;
	}
	
	public boolean getUnreachable()
	{
		return this.unreachable;
	}
	
	public AsteroidImpl withUnreachable(boolean unreachable)
	{
		this.unreachable = unreachable;
		return this;
	}
	
	public List<OreGenImpl> getOreGenList()
	{
		return this.oregen;
	}
	
	public void setOreGenList(List<OreGenImpl> oregen)
	{
		this.oregen = oregen;
	}
	
	public AsteroidImpl withOreGenList(List<OreGenImpl> oregen)
	{
		this.oregen = oregen;
		return this;
	}
}
