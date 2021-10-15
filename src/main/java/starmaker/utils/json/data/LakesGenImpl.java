package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LakesGenImpl {

	@SerializedName("liquid_block")
	@Expose
	private String liquidBlock;
	@SerializedName("quantity")
	@Expose
	private int quantity;
	
	public LakesGenImpl(String liquid_block, int quantity) {
		this.liquidBlock = liquid_block;
		this.quantity = quantity;
	}
	
	public String getLiquidBlock() {return this.liquidBlock;}
	public int getQuantity() { return this.quantity;}
}
