package starmaker.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import starmaker.CoreConfig;
import starmaker.utils.MakerUtils;

public class NBTStructureGenerator implements IWorldGenerator {

	private final NBTStructureConfiguration settings;
	public NBTStructureGenerator(NBTStructureConfiguration nbt_config) {
		this.settings = nbt_config;
	}
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(!(world instanceof WorldServer))
			return;
		
		WorldServer server_world = (WorldServer) world;

		int x = chunkX + random.nextInt(16);
		int z = chunkZ + random.nextInt(16);
		BlockPos xzPos = new BlockPos(x, 1, z);		
		BlockPos pos = world.getTopSolidOrLiquidBlock(xzPos);

		pos = new BlockPos(pos.getX() + this.settings.getOffsetPos().getX(), pos.getY() + this.settings.getOffsetPos().getY(), pos.getZ() + this.settings.getOffsetPos().getZ());
		

		if(this.settings.getAmountPerChunk() > 0) {
			for(int i = 0; i < this.settings.getAmountPerChunk(); i++) {
				generateStructureAt(this.settings.getStructureName(), server_world, random, pos);
			}
		}
		else if(this.settings.getGenChance() > 0 && this.settings.getGenChance() <= 100) {
			if(random.nextInt(100) <= this.settings.getGenChance()) {
				generateStructureAt(this.settings.getStructureName(), server_world, random, pos);
			}
		}
		
	}

	private void generateStructureAt(String name, WorldServer world, Random random, BlockPos pos) {
		MinecraftServer server = world.getMinecraftServer();
		ResourceLocation loc = new ResourceLocation(CoreConfig.resourceDomain, name);
		Template template = MakerUtils.templates.get(server, loc);
		//Template template = world.getSaveHandler().getStructureTemplateManager().get(server, loc);
		PlacementSettings settings = new PlacementSettings();
		if(template != null) {		
			BlockPos size = template.getSize();
			for(int x = 0; x < size.getX(); x++)
				for(int y = 0; y < size.getY(); y++)
					for(int z = 0; z < size.getZ(); z++) {
						BlockPos checkPos = pos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
						IBlockState checkState = world.getBlockState(checkPos);
						if(!checkState.getBlock().isAir(checkState, world, checkPos) && this.settings.getIgnoreAir())
							return; // Obstructed, can't generate here
					}		
	
			template.addBlocksToWorld(world, pos, settings);
		}
	}
}
