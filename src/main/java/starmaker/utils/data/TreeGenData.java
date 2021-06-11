package starmaker.utils.data;

public class TreeGenData
{
	private String log, leaves, sapling;
	private int minTreeHeight, quantity;
	private boolean vinesGrow;
	
	public TreeGenData(String log, String leaves, String sapling, int minHeight, boolean vines, int quantity) {
		this.log = log;
		this.leaves = leaves;
		this.sapling = sapling;
		this.minTreeHeight = minHeight;
		this.vinesGrow = vines;	
		this.quantity = quantity;
	}
	
	public String getLog() {return this.log;}
	public String getLeaves() {return this.leaves;}
	public String getSapling() {return this.sapling;}
	public int getMinHeight() {return this.minTreeHeight;}
	public boolean getVines() {return this.vinesGrow;}
	public int getQuantity() {return this.quantity;}
	
}