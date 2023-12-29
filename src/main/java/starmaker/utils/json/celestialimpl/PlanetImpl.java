
package starmaker.utils.json.celestialimpl;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.minecraft.util.math.Vec3i;
import starmaker.utils.json.data.OrbitDataImpl;
import starmaker.utils.json.data.WorldDataImpl;

public class PlanetImpl
{

	@SerializedName("parent_system")
	@Expose
	private String parentSystem;
	
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
	@SerializedName("world_data")
	@Expose
	private WorldDataImpl worldData;
	/*
	@SerializedName("biomes")
	@Expose
	private List<BiomeImpl> biomes = null;
	*/
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
	
	@SerializedName("ringTextureOnMap") @Expose
	private String ringTextureOnMap;
	@SerializedName("ringTextureOnSky") @Expose
	private String ringTextureOnSky;
	
	@SerializedName("sunTexture") @Expose
	private String customSunTexture;

	@SerializedName("tidallyLocked") @Expose
	private Boolean tidallyLocked;
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
	public PlanetImpl(String parentSystem, OrbitDataImpl orbitData,
			double gravity, Integer atmospherePressure, List<Float> temperature, double wind, Integer dayLenght,
			Boolean breathable, Boolean solarRadiation, Boolean corrosiveAtmo, double sunBrightness,
			double starBrightness, List<Integer> sky, List<Integer> fog, List<Integer> cloud, WorldDataImpl worldData,
			/*List<BiomeImpl> biomes*/ List<String> biomes, float sun_size, boolean precipitation, boolean unreachable)
	{
		super();
		this.parentSystem = parentSystem;		
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
	
	public PlanetImpl setRingPlanet(String textureOnMap, String textureOnSky) {
		this.ringTextureOnMap = textureOnMap;
		this.ringTextureOnSky = textureOnSky;
		return this;
	}
	
	public PlanetImpl setSunTexture(String texture) {
		this.customSunTexture = texture;
		return this;
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

	public OrbitDataImpl getOrbitData()
	{
		return orbitData;
	}

	public void setOrbitData(OrbitDataImpl orbitData)
	{
		this.orbitData = orbitData;
	}

	public PlanetImpl withOrbitData(OrbitDataImpl orbitData)
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
		return (Float) temperature.get(0);
	}
	
	public Float getTemperatureModificator()
	{
		if(temperature.size() > 1)
			return (Float) temperature.get(1);
		return 0.5F;
	}

	public void setTemperature(List<Float> temperature)
	{
		this.temperature = temperature;
	}

	public PlanetImpl withTemperature(List<Float> temperature)
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

	public PlanetImpl withCloud(List<Integer> cloud)
	{
		this.cloud = cloud;
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

	public List<String> getBiomes()
	{
		return biomes;
	}

	public void setBiomes(List<String> biomes)
	{
		this.biomes = biomes;
	}

	public PlanetImpl withBiomes(List<String> biomes)
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
	
	public void setUnreachable(boolean unreachable)
	{
		this.unreachable = unreachable;
	}
	
	public boolean getUnreachable()
	{
		return this.unreachable;
	}
	
	public PlanetImpl withUnreachable(boolean unreachable)
	{
		this.unreachable = unreachable;
		return this;
	}
	
	public String getRingOnMapTextureName() {
		return this.ringTextureOnMap;
	}
	public String getRingOnSkyTextureName() { return this.ringTextureOnSky;	}
	public String getSunTextureName() {
		return this.customSunTexture;
	}
	public Boolean getTidallyLocked(){
		if(tidallyLocked == null) return false;
		return this.tidallyLocked;
	}
	public void setTidallyLocked(Boolean locked){
		this.tidallyLocked = locked;
	}
}
