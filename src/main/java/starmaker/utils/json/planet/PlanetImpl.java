
package starmaker.utils.json.planet;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.minecraft.util.math.Vec3i;

public class PlanetImpl
{

	@SerializedName("parent_system")
	@Expose
	private String parentSystem;
	@SerializedName("phase")
	@Expose
	private double phase;
	@SerializedName("size")
	@Expose
	private double size;
	@SerializedName("distance_from_center")
	@Expose
	private double distanceFromCenter;
	@SerializedName("relative_time")
	@Expose
	private double relativeTime;
	@SerializedName("gravity")
	@Expose
	private double gravity;
	@SerializedName("atmosphere_pressure")
	@Expose
	private int atmospherePressure;
	@SerializedName("temperature")
	@Expose
	private double temperature;
	@SerializedName("wind")
	@Expose
	private double wind;
	@SerializedName("day_lenght")
	@Expose
	private Integer dayLenght;
	@SerializedName("breathable")
	@Expose
	private Boolean breathable;
	@SerializedName("solar_radiation")
	@Expose
	private Boolean solarRadiation;
	@SerializedName("corrosive_atmo")
	@Expose
	private Boolean corrosiveAtmo;
	@SerializedName("sun_brightness")
	@Expose
	private double sunBrightness;
	@SerializedName("star_brightness")
	@Expose
	private double starBrightness;
	@SerializedName("sky")
	@Expose
	private List<Integer> sky = null;
	@SerializedName("fog")
	@Expose
	private List<Integer> fog = null;
	@SerializedName("world_data")
	@Expose
	private WorldDataImpl worldData;
	@SerializedName("biomes")
	@Expose
	private List<BiomeImpl> biomes = null;
	@SerializedName("sun_size")
	@Expose
	private float sun_size;	
	@SerializedName("precipitation")
	@Expose
	private boolean precipitation;
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public PlanetImpl()
	{
	}

	/**
	 * 
	 * @param phase
	 * @param sky
	 * @param biomes
	 * @param solarRadiation
	 * @param dayLenght
	 * @param parentSystem
	 * @param relativeTime
	 * @param atmospherePressure
	 * @param size
	 * @param gravity
	 * @param corrosiveAtmo
	 * @param temperature
	 * @param sunBrightness
	 * @param starBrightness
	 * @param breathable
	 * @param worldData
	 * @param distanceFromCenter
	 * @param wind
	 * @param fog
	 */
	public PlanetImpl(String parentSystem, double phase, double size, double distanceFromCenter, double relativeTime,
			double gravity, Integer atmospherePressure, double temperature, double wind, Integer dayLenght,
			Boolean breathable, Boolean solarRadiation, Boolean corrosiveAtmo, double sunBrightness,
			double starBrightness, List<Integer> sky, List<Integer> fog, WorldDataImpl worldData,
			List<BiomeImpl> biomes, float sun_size, boolean precipitation)
	{
		super();
		this.parentSystem = parentSystem;
		this.phase = phase;
		this.size = size;
		this.distanceFromCenter = distanceFromCenter;
		this.relativeTime = relativeTime;
		this.gravity = gravity;
		this.atmospherePressure = atmospherePressure;
		this.temperature = temperature;
		this.wind = wind;
		this.dayLenght = dayLenght;
		this.breathable = breathable;
		this.solarRadiation = solarRadiation;
		this.corrosiveAtmo = corrosiveAtmo;
		this.sunBrightness = sunBrightness;
		this.starBrightness = starBrightness;
		this.sky = sky;
		this.fog = fog;
		this.worldData = worldData;
		this.biomes = biomes;
		this.sun_size = sun_size;
		this.precipitation = precipitation;
	}

	public String getParentSystem()
	{
		return parentSystem;
	}

	public void setParentSystem(String parentSystem)
	{
		this.parentSystem = parentSystem;
	}

	public PlanetImpl withParentSystem(String parentSystem)
	{
		this.parentSystem = parentSystem;
		return this;
	}

	public Float getPhase()
	{
		return (float) phase;
	}

	public void setPhase(double phase)
	{
		this.phase = phase;
	}

	public PlanetImpl withPhase(double phase)
	{
		this.phase = phase;
		return this;
	}

	public Float getSize()
	{
		return (float) size;
	}

	public void setSize(double size)
	{
		this.size = size;
	}

	public PlanetImpl withSize(double size)
	{
		this.size = size;
		return this;
	}

	public Float getDistanceFromCenter()
	{
		return (float) distanceFromCenter;
	}

	public void setDistanceFromCenter(double distanceFromCenter)
	{
		this.distanceFromCenter = distanceFromCenter;
	}

	public PlanetImpl withDistanceFromCenter(double distanceFromCenter)
	{
		this.distanceFromCenter = distanceFromCenter;
		return this;
	}

	public Float getRelativeTime()
	{
		return (float) relativeTime;
	}

	public void setRelativeTime(double relativeTime)
	{
		this.relativeTime = relativeTime;
	}

	public PlanetImpl withRelativeTime(double relativeTime)
	{
		this.relativeTime = relativeTime;
		return this;
	}

	public Float getGravity()
	{
		return (float) gravity;
	}

	public void setGravity(double gravity)
	{
		this.gravity = gravity;
	}

	public PlanetImpl withGravity(double gravity)
	{
		this.gravity = gravity;
		return this;
	}

	public Float getAtmospherePressure()
	{
		return (float) atmospherePressure;
	}

	public void setAtmospherePressure(Integer atmospherePressure)
	{
		this.atmospherePressure = atmospherePressure;
	}

	public PlanetImpl withAtmospherePressure(Integer atmospherePressure)
	{
		this.atmospherePressure = atmospherePressure;
		return this;
	}

	public Float getTemperature()
	{
		return (float) temperature;
	}

	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}

	public PlanetImpl withTemperature(double temperature)
	{
		this.temperature = temperature;
		return this;
	}

	public Float getWind()
	{
		return (float) wind;
	}

	public void setWind(double wind)
	{
		this.wind = wind;
	}

	public PlanetImpl withWind(double wind)
	{
		this.wind = wind;
		return this;
	}

	public Integer getDayLenght()
	{
		return dayLenght;
	}

	public void setDayLenght(Integer dayLenght)
	{
		this.dayLenght = dayLenght;
	}

	public PlanetImpl withDayLenght(Integer dayLenght)
	{
		this.dayLenght = dayLenght;
		return this;
	}

	public Boolean getBreathable()
	{
		return breathable;
	}

	public void setBreathable(Boolean breathable)
	{
		this.breathable = breathable;
	}

	public PlanetImpl withBreathable(Boolean breathable)
	{
		this.breathable = breathable;
		return this;
	}

	public Boolean getSolarRadiation()
	{
		return solarRadiation;
	}

	public void setSolarRadiation(Boolean solarRadiation)
	{
		this.solarRadiation = solarRadiation;
	}

	public PlanetImpl withSolarRadiation(Boolean solarRadiation)
	{
		this.solarRadiation = solarRadiation;
		return this;
	}

	public Boolean getCorrosiveAtmo()
	{
		return corrosiveAtmo;
	}

	public void setCorrosiveAtmo(Boolean corrosiveAtmo)
	{
		this.corrosiveAtmo = corrosiveAtmo;
	}

	public PlanetImpl withCorrosiveAtmo(Boolean corrosiveAtmo)
	{
		this.corrosiveAtmo = corrosiveAtmo;
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

	public PlanetImpl withSunBrightness(double sunBrightness)
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

	public PlanetImpl withStarBrightness(double starBrightness)
	{
		this.starBrightness = starBrightness;
		return this;
	}

	public Vec3i getSky()
	{
		return new Vec3i(sky.get(0), sky.get(1), sky.get(2));
	}

	public void setSky(List<Integer> sky)
	{
		this.sky = sky;
	}

	public PlanetImpl withSky(List<Integer> sky)
	{
		this.sky = sky;
		return this;
	}

	public Vec3i getFog()
	{
		return new Vec3i(fog.get(0), fog.get(1), fog.get(2));
	}

	public void setFog(List<Integer> fog)
	{
		this.fog = fog;
	}

	public PlanetImpl withFog(List<Integer> fog)
	{
		this.fog = fog;
		return this;
	}

	public WorldDataImpl getWorldData()
	{
		return worldData;
	}

	public void setWorldData(WorldDataImpl worldData)
	{
		this.worldData = worldData;
	}

	public PlanetImpl withWorldData(WorldDataImpl worldData)
	{
		this.worldData = worldData;
		return this;
	}

	public List<BiomeImpl> getBiomes()
	{
		return biomes;
	}

	public void setBiomes(List<BiomeImpl> biomes)
	{
		this.biomes = biomes;
	}

	public PlanetImpl withBiomes(List<BiomeImpl> biomes)
	{
		this.biomes = biomes;
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
	
	public PlanetImpl withSunSize(float size)
	{
		this.sun_size = size;
		return this;
	}
	
	public void setPrecipitation(boolean precipitation)
	{
		this.precipitation = precipitation;
	}
	
	public boolean getPrecipitation()
	{
		return this.precipitation;
	}
	
	public PlanetImpl withPrecipitation(boolean precipitation)
	{
		this.precipitation = precipitation;
		return this;
	}
}
