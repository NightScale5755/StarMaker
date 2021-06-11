package starmaker.utils.data;

public class OreGenData
{
	private String ore, replaced;
	private int blockcount, minY, maxY, amountPerChunk;
	
	public OreGenData(String ore, String replaced, int blockcount, int minY, int maxY, int amountPerChunk) {
		this.ore = ore;
		this.replaced = replaced;
		this.blockcount = blockcount;
		this.minY = minY;
		this.maxY = maxY;
		this.amountPerChunk = amountPerChunk;
	}
	
	public String getOre()	{ return this.ore; }
	public String getReplaced() { return this.replaced; }
	public int getBlockCount() { return this.blockcount; }
	public int getMinY() { return this.minY; }
	public int getMaxY() { return this.maxY; }
	public int getAmountPerChunk() { return this.amountPerChunk; }
	
}