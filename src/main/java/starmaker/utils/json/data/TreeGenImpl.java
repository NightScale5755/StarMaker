package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreeGenImpl {

	@SerializedName("log_block")
	@Expose
	private String logBlock;
	@SerializedName("leaves_block")
	@Expose
	private String leavesBlock;
	@SerializedName("sapling_block")
	@Expose
	private String saplingBlock;
	@SerializedName("minTreeHeight")
	@Expose
	private int minHeight;
	@SerializedName("vines_gen")
	@Expose
	private boolean vinesGen;
	@SerializedName("quantity")
	@Expose
	private int quantity;
	
	public TreeGenImpl(String log, String leaves, String sapling, int minHeight, boolean vinesGen, int quantity)
	{
		this.logBlock = log;
		this.leavesBlock = leaves;
		this.saplingBlock = sapling;
		this.minHeight = minHeight;
		this.vinesGen = vinesGen;
		this.quantity = quantity;
	}
	
	public String getLog() {return this.logBlock;}
	public String getLeaves() {return this.leavesBlock;}
	public String getSapling() {return this.saplingBlock;}
	public int getMinHeight() {return this.minHeight;}
	public boolean getVines() {return this.vinesGen;}
	public int getQuantity() { return this.quantity;}
}
