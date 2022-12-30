
package starmaker.utils.json.celestialimpl;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.minecraft.util.math.Vec3i;

public class SatelliteImpl
{

	@SerializedName("parent_planet") @Expose
	private String parentPlanet;
		
	@SerializedName("gravity") @Expose
	private double gravity;
	
	@SerializedName("atmosphere_pressure") @Expose
	private int atmospherePressure;
	
	@SerializedName("temperature")	@Expose
	private List<Float> temperature;
	
	@SerializedName("wind")	@Expose
	private double wind;
	
	@SerializedName("day_lenght") @Expose
	private Integer dayLenght;
	
	@SerializedName("breathable") @Expose
	private Boolean breathable;
	
	@SerializedName("solar_radiation") @Expose
	private Boolean solarRadiation;
	
	@SerializedName("corrosive_atmo") @Expose
	private Boolean corrosiveAtmo;
	
	@SerializedName("sun_brightness") @Expose
	private double sunBrightness;
	
	@SerializedName("star_brightness") @Expose
	private double starBrightness;
	
	@SerializedName("sky") @Expose
	private List<Integer> sky = null;
	
	@SerializedName("fog") @Expose
	private List<Integer> fog = null;
	
	@SerializedName("cloud") @Expose
	private List<Integer> cloud = null;
	
	@SerializedName("parachest_pos") @Expose
	private List<Integer> parachest_pos = null;
		
	@SerializedName("sun_size")	@Expose
	private float sun_size;	
	
	@SerializedName("precipitation") @Expose
	private boolean precipitation;
	
	@SerializedName("sunTexture") @Expose
	private String customSunTexture;
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public SatelliteImpl()
	{
	}

	public SatelliteImpl(String parentPlanet,
			double gravity, Integer atmospherePressure, List<Float> temperature, double wind, Integer dayLenght,
			Boolean breathable, Boolean solarRadiation, Boolean corrosiveAtmo, double sunBrightness,
			double starBrightness, List<Integer> sky, List<Integer> fog, List<Integer> cloud, List<Integer> parachest_pos,
			float sun_size, boolean precipitation)
	{
		super();
		this.parentPlanet = parentPlanet;
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
		this.cloud = cloud;
		this.parachest_pos = parachest_pos;
		this.sun_size = sun_size;
		this.precipitation = precipitation;
	}

	public SatelliteImpl setSunTexture(String texture) {
		this.customSunTexture = texture;
		return this;
	}
	
	public String getParentPlanet()
	{
		return parentPlanet;
	}

	public void setParentPlanet(String parentPlanet)
	{
		this.parentPlanet = parentPlanet;
	}

	public SatelliteImpl withParentPlanet(String parentPlanet)
	{
		this.parentPlanet = parentPlanet;
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

	public SatelliteImpl withGravity(double gravity)
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

	public SatelliteImpl withAtmospherePressure(Integer atmospherePressure)
	{
		this.atmospherePressure = atmospherePressure;
		return this;
	}

	public float getTemperature()
	{
		return (float) temperature.get(0);
	}
	
	public float getTemperatureModificator()
	{
		if(temperature.size() > 1)
			return (float) temperature.get(1);
		return 0.5F;
	}

	public void setTemperature(List<Float> temperature)
	{
		this.temperature = temperature;
	}

	public SatelliteImpl withTemperature(List<Float> temperature)
	{
		setTemperature(temperature);
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

	public SatelliteImpl withWind(double wind)
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

	public SatelliteImpl withDayLenght(Integer dayLenght)
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

	public SatelliteImpl withBreathable(Boolean breathable)
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

	public SatelliteImpl withSolarRadiation(Boolean solarRadiation)
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

	public SatelliteImpl withCorrosiveAtmo(Boolean corrosiveAtmo)
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

	public SatelliteImpl withSunBrightness(double sunBrightness)
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

	public SatelliteImpl withStarBrightness(double starBrightness)
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

	public SatelliteImpl withSky(List<Integer> sky)
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

	public SatelliteImpl withFog(List<Integer> fog)
	{
		this.fog = fog;
		return this;
	}

	public Vec3i getCloud()
	{
		if(cloud == null) return null;
		
		return new Vec3i(cloud.get(0), cloud.get(1), cloud.get(2));
	}

	public int getCloudHeight() {
		if(cloud == null) return 180;
		if(cloud.size() < 4) return 180;
		
		return cloud.get(3);
	}
	
	public void setCloud(List<Integer> cloud)
	{
		this.cloud = cloud;
	}

	public SatelliteImpl withCloud(List<Integer> cloud)
	{
		this.cloud = cloud;
		return this;
	}

	public Vec3i getParachestPos()
	{
		return new Vec3i(parachest_pos.get(0), parachest_pos.get(1), parachest_pos.get(2));
	}

	public void setParachestPos(List<Integer> parachest_pos)
	{
		this.parachest_pos = parachest_pos;
	}

	public SatelliteImpl withParachestPos(List<Integer> parachest_pos)
	{
		this.parachest_pos = parachest_pos;
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
	
	public SatelliteImpl withSunSize(float size)
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
	
	public SatelliteImpl withPrecipitation(boolean precipitation)
	{
		this.precipitation = precipitation;
		return this;
	}	
	
	public String getSunTextureName() {
		return this.customSunTexture;
	}
}
