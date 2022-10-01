package starmaker.world.gen;

import net.minecraft.util.math.BlockPos;
import starmaker.utils.json.data.StructuresDataImpl;

public class NBTStructureConfiguration {

	private final StructuresDataImpl structure_data;
	
	public NBTStructureConfiguration(StructuresDataImpl data) {
		this.structure_data = data;
	}
	
	public String getStructureName() {
		return this.structure_data.getNBTFileName();
	}
	
	public int getAmountPerChunk() {
		return this.structure_data.getAmountPerChunk();
	}
	
	public int getGenChance() {
		return this.structure_data.getGenChance();
	}
	
	public BlockPos getOffsetPos() {
		return this.structure_data.getOffsetPos();
	}
}
