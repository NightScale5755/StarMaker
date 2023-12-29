package starmaker.utils.data;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import starmaker.CoreConfig;

public class DimData
{
	private final CelestialBody body;
	private Vec3d skyColor, fogColor, cloudColor;
	private boolean genCaves, genRavines;
	private int crateprob = 0;
	private String stone_block, water_block;
	private List<BiomeData> getBiomes = new ArrayList<BiomeData>();
	private float sunBrightness, starBrightness, sun_size, planet_size;
	private double mapsize;
	private int waterY = 64;
	private int lander_type = -1;
	private int cloudHeight;
	private float temp_mod = 0.5F;
	private List<String> asteroid_blocks = new ArrayList<>();
	private List<String> ores = new ArrayList<>();
	private String ringTextureOnMap, ringTextureOnSky;
	private String sunTexture;
	private float gravity;
	private long dayLenght;
	private float fallDamage;
	private float meteorFrequency;
	private boolean tidallyLocked;

	public DimData(CelestialBody body) {
		this.body = body;
	}
	
	public DimData(CelestialBody body, String stone, double size)
	{
		this(body);
		this.stone_block = stone;
		this.mapsize = size;
	}
	
	public DimData setSkyFogColor(Vec3d sky, Vec3d fog)
	{
		this.skyColor = sky;
		this.fogColor = fog;
		return this;
	}   	
	
	public DimData setGenCavesRavines(boolean cave, boolean ravine, int crateprob, String water)
	{
		this.genCaves = cave;
		this.genRavines = ravine;
		this.crateprob = crateprob;
		this.water_block = water;
		return this;
	}
	
	public DimData setBiomes(List<BiomeData> biome)
	{
		this.getBiomes = biome;    		
		return this;
	}
	
	public DimData setBrightness(float sun, float star)
	{
		this.sunBrightness = sun;
		this.starBrightness = star;
		return this;
	}
	
	public DimData setSunSize(float size)
	{
		this.sun_size = size;
		return this;
	}
	
	public DimData setPlanetSize(float size) {
		this.planet_size = size;
		return this;
	}
	
	public DimData setWaterY(int y)
	{
		this.waterY = y;
		return this;
	}
	
	public DimData setCloudColor(Vec3d color) {
		this.cloudColor = color;
		return this;
	}
	
	public DimData setLanderType(int type) {
		this.lander_type = type;
		return this;
	}
	
	public DimData setMeteorFrequency(float frequency) {
		this.meteorFrequency = frequency;
		return this;
	}
	
	public DimData setCloudHeight(int height) {
		this.cloudHeight = height;
		return this;
	}
	
	public DimData setTemperatureMod(float mod) {
		this.temp_mod = mod;
		return this;
	}
	
	public DimData setListAsteroidsOres(List<String> list) {
		this.ores = list;
		return this;
	}
	
	public DimData setRingTexture(String textureOnMap, String textureOnSky) {
		this.ringTextureOnMap = textureOnMap;
		this.ringTextureOnSky = textureOnSky;
		return this;
	}
	
	public DimData setSunTexture(String texture) {
		
		this.sunTexture = texture;
		return this;
	}
	
	public DimData setGravity(float gravity) {
		this.gravity = gravity;
		return this;
	}
	
	public DimData setDayLenght(long lenght) {
		this.dayLenght = lenght;
		return this;
	}
	
	public DimData setAsteroidBlocks(List<String> blocks) {
		this.asteroid_blocks = blocks;
		return this;
	}

	public DimData setFallDamageModifier(float modifier)
	{
		this.fallDamage = modifier;
		return this;
	}

	public DimData setTidallyLocked(boolean locked)
	{
		this.tidallyLocked = locked;
		return this;

	}

	public CelestialBody getBody() 	{ return this.body;	}
	public Vec3d getSkyColor() { return this.skyColor; }    	
	public Vec3d getFogColor() { return this.fogColor; }    	
	public Vec3d getCloudColor() { return this.cloudColor; }    	
	public String getStoneBlock() { return this.stone_block; }
	public boolean getGenCaves() { return this.genCaves; }
	public boolean getGenRavines() { return this.genRavines; }
	public String getWaterBlock() { return this.water_block; }
	public int getCrateProb() { return this.crateprob; }
	public int getWaterY() { return this.waterY; }
	public List<BiomeData> getBiomes() { return this.getBiomes; }
	public float getSunBrightness() { return this.sunBrightness; }
	public float getStarBrightness() { return this.starBrightness; }
	public double getMapSize() { return this.mapsize; }
	public float getSunSize() { return this.sun_size; }
	public int getLanderType() { return this.lander_type; }
	public float getMeteorFrequency() { return this.meteorFrequency; }
	public int getCloudHeight() { return this.cloudHeight; }
	public float getTemperatureMod() { return this.temp_mod; }
	public List<String> getAsteroidBlocks() { return this.asteroid_blocks; }
	public List<String> getAsteroidsOres() { return this.ores; }	
	public ResourceLocation getRingOnMapTexture() {
		if(this.ringTextureOnMap == null) return null;
		return new ResourceLocation(CoreConfig.resourceDomain, "textures/" + this.ringTextureOnMap + ".png");
	}
	public ResourceLocation getRingOnSkyTexture() {
		if(this.ringTextureOnMap == null) return null;
		return new ResourceLocation(CoreConfig.resourceDomain, "textures/" + this.ringTextureOnSky + ".png");
	}
	
	public ResourceLocation getSunTexture() { 
		if(this.sunTexture == null) return null;
		return new ResourceLocation(CoreConfig.resourceDomain, "textures/" + this.sunTexture + ".png"); 
	}
	public float getGravity() {	return this.gravity; }
	@Deprecated
	public long getDayLenght() { return this.dayLenght; }
	public float getPlanetSize() { return this.planet_size; }
	public float getFallDamageModifier() { return  this.fallDamage; }
	public boolean getTidallyLocked() { return this.tidallyLocked; }
}
