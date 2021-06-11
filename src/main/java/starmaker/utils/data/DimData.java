package starmaker.utils.data;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.util.math.Vec3d;

public class DimData
{
	private final CelestialBody body;
	private Vec3d skyColor, fogColor;
	private boolean genCaves, genRavines;
	private int crateprob = 0;
	private String stone_block, water_block;
	private List<BiomeData> getBiomes = new ArrayList<BiomeData>();
	private float sunBrightness, starBrightness, sun_size;
	private double mapsize;
	private int waterY = 64;
	
	public DimData(CelestialBody body, String stone, double size)
	{
		this.body = body;
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
	
	public DimData setWaterY(int y)
	{
		this.waterY = y;
		return this;
	}
	
	public CelestialBody getBody() 	{ return this.body;	}
	public Vec3d getSkyColor() { return this.skyColor; }    	
	public Vec3d getFogColor() { return this.fogColor; }    	
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
}
