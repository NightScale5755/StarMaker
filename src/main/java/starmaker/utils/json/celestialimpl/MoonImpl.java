
package starmaker.utils.json.celestialimpl;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.minecraft.util.math.Vec3i;
import starmaker.utils.json.data.OrbitDataImpl;
import starmaker.utils.json.data.WorldDataImpl;

public class MoonImpl
{

	@SerializedName("parent_planet")
	@Expose
	private String parentPlanet;
	
	@SerializedName("orbit_data")
	@Expose
	private OrbitDataImpl orbitData;
	
	@SerializedName("gravity")
	@Expose
	private double gravity;
	@SerializedName("atmosphere_pressure")
	@Expose
	private int atmospherePressure;
	@SerializedName("temperature")
	@Expose
	private List<Float> temperature;
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
	@SerializedName("cloud")
	@Expose
	private List<Integer> cloud = null;
	@SerializedName("light")
	@Expose
	private List<Integer> light = null;
	@SerializedName("world_data")
	@Expose
	private WorldDataImpl worldData;
	/*@SerializedName("biomes")
	@Expose
	private List<BiomeImpl> biomes = null;*/
	@SerializedName("biomes")
	@Expose
	private List<String> biomes = null;
	@SerializedName("sun_size")
	@Expose
	private float sun_size;	
	@SerializedName("precipitation")
	@Expose
	private boolean precipitation;
	@SerializedName("unreachable")
	@Expose
	private boolean unreachable;
	
	@SerializedName("sunTexture") @Expose
	private String customSunTexture;
	
	@SerializedName("planet_size") @Expose
	private float planet_size;

	@SerializedName("tidallyLocked") @Expose
	private Boolean tidallyLocked;

	@SerializedName("ringTextureOnMap") @Expose
	private String ringTextureOnMap;
	@SerializedName("ringTextureOnSky") @Expose
	private String ringTextureOnSky;
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public MoonImpl()
	{
	}

	public MoonImpl(String parentPlanet, OrbitDataImpl orbitData,
			double gravity, Integer atmospherePressure, List<Float> temperature, double wind, Integer dayLenght,
			Boolean breathable, Boolean solarRadiation, Boolean corrosiveAtmo, double sunBrightness,
			double starBrightness, List<Integer> sky, List<Integer> fog, List<Integer> cloud, WorldDataImpl worldData,
			/*List<BiomeImpl> biomes*/List<String> biomes, float sun_size, boolean precipitation, boolean unreachable)
	{
		super();
		this.parentPlanet = parentPlanet;
		this.orbitData = orbitData;
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
		this.worldData = worldData;
		this.biomes = biomes;
		this.sun_size = sun_size;
		this.precipitation = precipitation;
		this.unreachable = unreachable;
	}

	public MoonImpl setSunTexture(String texture) {
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

	public MoonImpl withParentPlanet(String parentPlanet)
	{
		this.parentPlanet = parentPlanet;
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

	public MoonImpl withOrbitData(OrbitDataImpl orbitData)
	{
		this.orbitData = orbitData;
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

	public MoonImpl withGravity(double gravity)
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

	public MoonImpl withAtmospherePressure(Integer atmospherePressure)
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

	public MoonImpl withTemperature(List<Float> temperature)
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

	public MoonImpl withWind(double wind)
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

	public MoonImpl withDayLenght(Integer dayLenght)
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

	public MoonImpl withBreathable(Boolean breathable)
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

	public MoonImpl withSolarRadiation(Boolean solarRadiation)
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

	public MoonImpl withCorrosiveAtmo(Boolean corrosiveAtmo)
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

	public MoonImpl withSunBrightness(double sunBrightness)
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

	public MoonImpl withStarBrightness(double starBrightness)
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

	public MoonImpl withSky(List<Integer> sky)
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

	public MoonImpl withFog(List<Integer> fog)
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

	public MoonImpl withCloud(List<Integer> cloud)
	{
		this.cloud = cloud;
		return this;
	}

	public Vec3i getLight() {
		if(light == null) return null;
		return new Vec3i(light.get(0), light.get(1), light.get(2));
	}

	public void setLight(List<Integer> light) { this.light = light; }

	public WorldDataImpl getWorldData()
	{
		return worldData;
	}

	public void setWorldData(WorldDataImpl worldData)
	{
		this.worldData = worldData;
	}

	public MoonImpl withWorldData(WorldDataImpl worldData)
	{
		this.worldData = worldData;
		return this;
	}

	public List<String> getBiomes()
	{
		return biomes;
	}

	public void setBiomes(List<String> biomes)
	{
		this.biomes = biomes;
	}

	public MoonImpl withBiomes(List<String> biomes)
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
	
	public MoonImpl withSunSize(float size)
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
	
	public MoonImpl withPrecipitation(boolean precipitation)
	{
		this.precipitation = precipitation;
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
	
	public MoonImpl withUnreachable(boolean unreachable)
	{
		this.unreachable = unreachable;
		return this;
	}
	
	public String getSunTextureName() {
		return this.customSunTexture;
	}
	
	public float getPlanetSize() {
		return this.planet_size;
	}
	
	public MoonImpl setPlanetSize(float size) {
		this.planet_size = size;
		return this;
	}
	public Boolean getTidallyLocked(){
		if(tidallyLocked == null) return false;
		return this.tidallyLocked;
	}
	public void setTidallyLocked(Boolean locked){
		this.tidallyLocked = locked;
	}

	public String getRingOnMapTextureName() {
		return this.ringTextureOnMap;
	}
	public String getRingOnSkyTextureName() { return this.ringTextureOnSky;	}
}
