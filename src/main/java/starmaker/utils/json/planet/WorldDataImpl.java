
package starmaker.utils.json.planet;

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
	@SerializedName("mapSiZE")
	@Expose
	private double mapSize;

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
			double mapSize)
	{
		super();
		this.tier = tier;
		this.genCave = genCave;
		this.genRavine = genRavine;
		this.crateProb = crateProb;
		this.stoneBlock = stoneBlock;
		this.mapSize = mapSize;
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

}
