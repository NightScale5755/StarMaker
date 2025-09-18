
package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorldDataImpl
{

	@SerializedName("tier")
	@Expose
	private Integer tier;
	@SerializedName("genCave")
	@Expose
	private Boolean genCave;
	@SerializedName("genRavine")
	@Expose
	private Boolean genRavine;
	@SerializedName("crateProb")
	@Expose
	private Integer crateProb;
	@SerializedName("stone_block")
	@Expose
	private String stoneBlock;
	@SerializedName("mapSize")
	@Expose
	private double mapSize;
	@SerializedName("water_block")
	@Expose
	private String waterBlock;
	@SerializedName("waterY")
	@Expose
	private int waterY;
	@SerializedName("lander_type")
	@Expose
	private Integer lander_type;
	@SerializedName("meteorFrequency")
	@Expose
	private Float meteorFrequency;
	@SerializedName("fallDamageModifier")
	@Expose
	private Float fallDamageModifier;
	@SerializedName("fuelUsageModifier")
	@Expose
	private Float fuelUsageModifier;


	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public WorldDataImpl()
	{
	}

	/**
	 * 
	 * @param genRavine
	 * @param tier
	 * @param genCave
	 * @param stoneBlock
	 * @param crateProb
	 */
	public WorldDataImpl(Integer tier, Boolean genCave, Boolean genRavine, Integer crateProb, String stoneBlock,
			double mapSize, String waterBlock, int watery, int lander_type, Float meteorFrequency, Float fallDamageModifier, Float fuelUsageModifier)
	{
		super();
		this.tier = tier;
		this.genCave = genCave;
		this.genRavine = genRavine;
		this.crateProb = crateProb;
		this.stoneBlock = stoneBlock;
		this.mapSize = mapSize;
		this.waterBlock = waterBlock;
		this.waterY = watery;
		this.lander_type = lander_type;
		this.meteorFrequency = meteorFrequency;
		this.fallDamageModifier = fallDamageModifier;
		this.fuelUsageModifier = fuelUsageModifier;
	}

	public Integer getTier()
	{
		return tier;
	}

	public void setTier(Integer tier)
	{
		this.tier = tier;
	}

	public WorldDataImpl withTier(Integer tier)
	{
		this.tier = tier;
		return this;
	}

	public Boolean getGenCave()
	{
		return genCave;
	}

	public void setGenCave(Boolean genCave)
	{
		this.genCave = genCave;
	}

	public WorldDataImpl withGenCave(Boolean genCave)
	{
		this.genCave = genCave;
		return this;
	}

	public Boolean getGenRavine()
	{
		return genRavine;
	}

	public void setGenRavine(Boolean genRavine)
	{
		this.genRavine = genRavine;
	}

	public WorldDataImpl withGenRavine(Boolean genRavine)
	{
		this.genRavine = genRavine;
		return this;
	}

	public Integer getCrateProb()
	{
		return crateProb;
	}

	public void setCrateProb(Integer crateProb)
	{
		this.crateProb = crateProb;
	}

	public WorldDataImpl withCrateProb(Integer crateProb)
	{
		this.crateProb = crateProb;
		return this;
	}

	public String getStoneBlock()
	{
		return stoneBlock;
	}

	public void setStoneBlock(String stoneBlock)
	{
		this.stoneBlock = stoneBlock;
	}

	public WorldDataImpl withStoneBlock(String stoneBlock)
	{
		this.stoneBlock = stoneBlock;
		return this;
	}

	public double getMapSize()
	{
		return mapSize;
	}

	public void setMapSize(double mapSize)
	{
		this.mapSize = mapSize;
	}

	public WorldDataImpl withMapSize(double mapSize)
	{
		this.mapSize = mapSize;
		return this;
	}
	
	public void setWaterBlock(String waterBlock)
	{
		this.waterBlock = waterBlock;
	}

	public WorldDataImpl withWaterGen(String waterBlock)
	{
		this.waterBlock = waterBlock;
		return this;
	}

	public String getWaterBlock()
	{
		return waterBlock;
	}
	
	public void setWaterY(int y)
	{
		this.waterY = y;
	}

	public WorldDataImpl withWaterGen(int y)
	{
		this.waterY = y;
		return this;
	}

	public int getWaterY()
	{
		return waterY;
	}
	
	public void setLanderType(int lander_type)
	{
		this.lander_type = lander_type;
	}
	
	public WorldDataImpl withLanderType(int lander_type)
	{
		this.lander_type = lander_type;
		return this;
	}
	
	public int getLanderType()
	{
		if(lander_type == null) return -1;
		return lander_type;
	}
	
	public Float getMeteorFrequency()
	{
		if(meteorFrequency == null) return 0F;
		return meteorFrequency;
	}

	public void setMeteorFrequency(Float frequency)
	{
		this.meteorFrequency = frequency;
	}

	public WorldDataImpl withMeteorFrequency(Float frequency)
	{
		setMeteorFrequency(frequency);
		return this;
	}

	public Float getFallDamageModifier(){
		if(fallDamageModifier == null) return 1.0F;
		return fallDamageModifier;
	}

	public void setFallDamageModifier(Float modifier){
		this.fallDamageModifier = modifier;
	}

	public Float getFuelUsageModifier() {
		if(fuelUsageModifier == null) return 1.0F;
		return fuelUsageModifier;
	}

	public void setFuelUsageModifier(Float modifier) { this.fuelUsageModifier = modifier; }
}
