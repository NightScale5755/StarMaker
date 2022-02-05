package starmaker.utils.data;

import java.util.ArrayList;
import java.util.List;

import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.Vec3d;

public class DimData
{
	private final CelestialBody body;
	private Vec3d skyColor, fogColor, cloudColor;
	private boolean genCaves, genRavines, throwMeteors;
	private int crateprob = 0;
	private String stone_block, water_block;
	private List<BiomeData> getBiomes = new ArrayList<BiomeData>();
	private float sunBrightness, starBrightness, sun_size;
	private double mapsize;
	private int waterY = 64;
	private int lander_type = -1;
	private int cloudHeight;
	private float temp_mod = 0.5F;
	private List<IBlockState> ores = new ArrayList<>();
		
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
	
	public DimData setThrowMeteors(boolean flag) {
		this.throwMeteors = flag;
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
	
	public DimData setListAsteroidsOres(List<IBlockState> list) {
		this.ores = list;
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
	public boolean getThrowMeteors() { return this.throwMeteors; }
	public int getCloudHeight() { return this.cloudHeight; }
	public float getTemperatureMod() { return this.temp_mod; }
	public List<IBlockState> getAsteroidsOres() { return this.ores; }
}
