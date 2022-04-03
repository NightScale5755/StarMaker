package starmaker.utils.data;

import java.util.ArrayList;
import java.util.List;

import starmaker.utils.json.data.EntitySpawnImpl;


public class BiomeData
{
	private final String biomename;
	private float persistance, biomesize;
	private int octaves, height, intquility, watercolor, foliagecolor, grasscolor;
	private String surface_block, subsurface_block;
	
	private List<OreGenData> getOreGenData = new ArrayList<OreGenData>();
	private TreeGenData getTreeGenData;
	private List<GrassGenData> getGrassGenData = new ArrayList<GrassGenData>();
	private LakesGenData getLakesGenData;
	
	private List<EntitySpawnImpl> creatureSpawn = new ArrayList<EntitySpawnImpl>(),
			monsterSpawn = new ArrayList<EntitySpawnImpl>(), 
			water_creatureSpawn = new ArrayList<EntitySpawnImpl>();
	
	public BiomeData(String name, float biomesize)
	{
		this.biomename = name;
		this.biomesize = biomesize;
	}
	
	public BiomeData setData(float persistance, int height, int octaves, int intquility)
	{
		this.persistance = persistance;
		this.height = height;
		this.octaves = octaves;
		this.intquility = intquility;
		return this;
	}
	
	public BiomeData setBlocks(String surface, String subsurface)
	{
		this.surface_block = surface;
		this.subsurface_block = subsurface;    		
		return this;
	}
	
	public BiomeData setColors(int watercolor, int foliagecolor, int grasscolor)
	{
		this.watercolor = watercolor;
		this.foliagecolor = foliagecolor;
		this.grasscolor = grasscolor;
		return this;
	}
	
	public BiomeData setOreGenData(List<OreGenData> biome)
	{
		this.getOreGenData = biome;    		
		return this;
	}
	
	public BiomeData setTreeGenData(TreeGenData data)
	{
		this.getTreeGenData = data;
		return this;
	}
	
	public BiomeData setGrassGenData(List<GrassGenData> biome)
	{
		this.getGrassGenData = biome;    		
		return this;
	}
	
	public BiomeData setLakesGenData(LakesGenData data)
	{
		this.getLakesGenData = data;
		return this;
	}
	
	public BiomeData setSpawnLists(List<EntitySpawnImpl> creature, List<EntitySpawnImpl> monster, List<EntitySpawnImpl> water) {
		if(creature != null)
			this.creatureSpawn = creature;
		if(monster != null)
			this.monsterSpawn = monster;
		if(water != null)
			this.water_creatureSpawn = water;
		return this;
	}

	
	public float getPersistance() { return this.persistance; }
	public float getBiomeSize() { return this.biomesize; }
	public int getOctaves() { return this.octaves; }
	public int getHeight() { return this.height; }
	public int getIntquility() { return this.intquility; }
	public int getWaterColor() { return this.watercolor; }
	public int getFoliageColor() { return this.foliagecolor; }
	public int getGrassColor() { return this.grasscolor; }
	
	public String getSurfaceBlock() { return this.surface_block; }    	
	public String getSubsurfaceBlock() { return this.subsurface_block; } 
	
	public List<OreGenData> getOreGenData() { return this.getOreGenData; }
	public TreeGenData getTreeGenData() {return this.getTreeGenData; }
	public List<GrassGenData> getGrassGenData() { return this.getGrassGenData; }
	public LakesGenData getLakesGenData() {return this.getLakesGenData; }
	public List<EntitySpawnImpl> getCreatureSpawnList(){return this.creatureSpawn;}
	public List<EntitySpawnImpl> getMonsterSpawnList(){return this.monsterSpawn;}
	public List<EntitySpawnImpl> getWaterCreatureSpawnList(){return this.water_creatureSpawn;}
	
}