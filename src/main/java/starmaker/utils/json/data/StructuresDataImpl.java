package starmaker.utils.json.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.minecraft.util.math.BlockPos;

public class StructuresDataImpl {
	
	@SerializedName("nbt_file") @Expose
	private String nbt_file;
	
	@SerializedName("amount_per_chunk") @Expose
	private int amountPerChunk;
	
	@SerializedName("gen_chance") @Expose
	private int genChance;
	
	@SerializedName("offset_position") @Expose
	private List<Integer> offsetPos = null;
	
	@SerializedName("ignore_air") @Expose
	private boolean ignoreAir;
	
	public StructuresDataImpl() {
	}
	
	public StructuresDataImpl(String nbt, int amount_per_chunk, int chance, List<Integer> offset, boolean ignoreAir) {
		this.nbt_file = nbt;
		this.amountPerChunk = amount_per_chunk;
		this.genChance = chance;
		this.offsetPos = offset;
		this.ignoreAir = ignoreAir;
	}
	
	public String getNBTFileName() {
		return this.nbt_file;
	}
	
	public int getAmountPerChunk() {
		return this.amountPerChunk;
	}
	
	public int getGenChance() {
		return this.genChance;
	}
	
	public BlockPos getOffsetPos() {
		if(offsetPos == null) return BlockPos.ORIGIN;
		return new BlockPos(offsetPos.get(0), offsetPos.get(1), offsetPos.get(2));
	}
	
	public boolean getIgnoreAir() {
		return this.ignoreAir;
	}
}
