package starmaker.utils.data;

public class LakesGenData {
	
	private String liquid;
	private int quantity;
	
	public LakesGenData(String liqiud_block, int quantity) {
		this.liquid = liqiud_block;
		this.quantity = quantity;
	}
	
	public String getLiquidBlock() {return this.liquid;}
	public int getQuantity() {return this.quantity;}
}
