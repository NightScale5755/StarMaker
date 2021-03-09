package starmaker.utils.json.planet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BiomeDecoratorImpl {

	@SerializedName("ore_block")
	@Expose
	private String oreBlock;
	@SerializedName("replaced_block")
	@Expose
	private String replacedBlock;
	@SerializedName("block_count")
	@Expose
	private int blockCount;
	@SerializedName("minY")
	@Expose
	private int minY;
	@SerializedName("maxY")
	@Expose
	private int maxY;
	@SerializedName("amount_per_chunk")
	@Expose
	private int amountPerChunk;
	
	public BiomeDecoratorImpl()
	{
	}
	
	public BiomeDecoratorImpl(String ore, String replaced, int blockcount, int minY, int maxY, int amountperchunk)
	{
		this.oreBlock = ore;
		this.replacedBlock = replaced;
		this.blockCount = blockcount;
		this.minY = minY;
		this.maxY = maxY;
		this.amountPerChunk = amountperchunk;
	}
	
	public void setOreBlock(String ore)
	{
		this.oreBlock = ore;
	}
	
	public String getOreBlock()
	{
		return this.oreBlock;
	}
	
	public BiomeDecoratorImpl withOreBlock(String ore)
	{
		this.oreBlock = ore;
		return this;
	}
	
	public void setReplacedBlock(String replaced)
	{
		this.replacedBlock = replaced;
	}
	
	public String getReplacedBlock()
	{
		return this.replacedBlock;
	}
	
	public BiomeDecoratorImpl withReplacedBlock(String replaced)
	{
		this.replacedBlock = replaced;
		return this;
	}
	
	public void setBlockCount(int count)
	{
		this.blockCount = count;
	}
	
	public int getBlockCount()
	{
		return this.blockCount;
	}
	
	public BiomeDecoratorImpl withOreBlock(int count)
	{
		this.blockCount = count;
		return this;
	}
	
	
	
	public void setMinY(int minY)
	{
		this.minY = minY;
	}
	
	public int getMinY()
	{
		return this.minY;
	}
	
	public void setMaxY(int maxY)
	{
		this.maxY = maxY;
	}
	
	public int getMaxY()
	{
		return this.maxY;
	}
	
	public void setAmountPerChunk(int amount)
	{
		this.amountPerChunk = amount;
	}
	
	public int getAmountPerChunk()
	{
		return this.amountPerChunk;
	}
}
