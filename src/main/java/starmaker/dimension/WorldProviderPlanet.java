package starmaker.dimension;

import java.util.List;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProviderSpace;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace;
import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.WE_ChunkProvider;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_CaveGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_RavineGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_TerrainGenerator;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starmaker.StarMaker;
import starmaker.StarMaker.BiomeData;
import starmaker.StarMaker.DimData;
import starmaker.dimension.sky.SkyProviderPlanet;
import starmaker.utils.ParseConfig;
import starmaker.utils.we.WE_BaseBiome;

public class WorldProviderPlanet extends WE_WorldProviderSpace{
	
	private static int getId;
	
	private DimData getDimData()
	{
		return StarMaker.bodies.get(this.getDimension());
	}
	
	@Override
	public CelestialBody getCelestialBody() {
		if(getDimData() != null)
		{
			//StarMaker.debug(StarMaker.bodies.get(this.getDimension()).getBody().getName());
			return getDimData().getBody(); 
		}
		return GalacticraftCore.moonMoon;
	}
	
	@Override
	public double getFuelUsageMultiplier() {
		return 1.0D;
	}

	@Override
	public float getFallDamageModifier() {
		return 1.0F;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public double getMeteorFrequency() {
		return (3 - (getSkyColor().x + getSkyColor().y + getSkyColor().z)) * 10;
	}
	 
	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(getDimData().getFogColor().x / 255.0F * f, getDimData().getFogColor().y / 255.0F * f, getDimData().getFogColor().z / 255.0F * f);
	}

	@Override
	public Vector3 getSkyColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(getDimData().getSkyColor().x / 255.0F * f, getDimData().getSkyColor().y / 255.0F * f, getDimData().getSkyColor().z / 255.0F * f);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
    	float f = this.world.getCelestialAngle(par1);
        float f1 = 1.0F - (MathHelper.cos(f * ((float)Math.PI * 2F)) * 2.0F + 0.25F);
        f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
        return f1 * f1 * 0.5F + getDimData().getStarBrightness();   	
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1) {
       float f1 = this.world.getCelestialAngle(1.0F);
       float f2 = 1.0F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
       f2 = MathHelper.clamp(f2, 0.0F, 1.0F);

       f2 = 1.0F - f2;
       return f2 * getDimData().getSunBrightness();
    }
    
	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return WE_ChunkProviderSpace.class;
	}

	@Override
	public DimensionType getDimensionType() {		
		return WorldUtil.getDimensionTypeById(this.getDimension());
	}

	@Override
	public boolean enableAdvancedThermalLevel() {
		return true;
	}
	 
	@Override
	public IRenderHandler getCloudRenderer() {
		return new CloudRenderer();
	}
	
	@Override
	@SideOnly(Side.CLIENT)	
    public IRenderHandler getSkyRenderer()
    {
    	if (super.getSkyRenderer() == null)
		{
			this.setSkyRenderer(new SkyProviderPlanet(getDimData()));
		}
    	
		return super.getSkyRenderer();
    }
    

	@Override
	public void onChunkProvider(int cX, int cZ, ChunkPrimer primer) {
	
	}

	@Override
	public void onPopulate(int cX, int cZ) {
		
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}

	@Override
	public void genSettings(WE_ChunkProvider cp) {
		cp.createChunkGen_List .clear(); 
		cp.createChunkGen_InXZ_List .clear(); 
		cp.createChunkGen_InXYZ_List.clear(); 
		cp.decorateChunkGen_List .clear(); 
		cp.biomesList.clear();
		((WE_ChunkProviderSpace)cp).worldGenerators.clear();		
		((WE_ChunkProviderSpace)cp).CRATER_PROB = getDimData().getCrateProb();
		
		WE_Biome.setBiomeMap(cp, 1.4D, 4, getDimData().getMapSize(), 1.0D);		
		
		WE_TerrainGenerator terrainGenerator = new WE_TerrainGenerator(); 
		terrainGenerator.worldStoneBlock = ParseConfig.getBlock(getDimData().getStoneBlock()); 
		terrainGenerator.worldSeaGen = false;
		terrainGenerator.worldSeaGenBlock = Blocks.WATER.getDefaultState();
		terrainGenerator.worldSeaGenMaxY = 64;
		cp.createChunkGen_List.add(terrainGenerator);
		
		if(getDimData().getGenCaves()) {
			WE_CaveGen cg = new WE_CaveGen(); 
			cg.replaceBlocksList.clear(); 
			cg.addReplacingBlock(terrainGenerator.worldStoneBlock); 
			cg.lavaMaxY = 15;
			cp.createChunkGen_List.add(cg); 
		}
		
		if(getDimData().getGenRavines()) {
			WE_RavineGen rg = new WE_RavineGen();
			rg.replaceBlocksList.clear();
			rg.addReplacingBlock(terrainGenerator.worldStoneBlock);
			rg.lavaBlock = Blocks.LAVA.getDefaultState();
			rg.lavaMaxY = 15;		
			cp.createChunkGen_List.add(rg);
		}
		
		double distance = 0D;
		for(BiomeData biome : getDimData().getBiomes()) {
			WE_BiomeLayer layer = new WE_BiomeLayer();		
			layer.add(ParseConfig.getBlock(biome.getSubsurfaceBlock()), terrainGenerator.worldStoneBlock, -256, 0, -4, -2, true);
			layer.add(ParseConfig.getBlock(biome.getSurfaceBlock()), ParseConfig.getBlock(biome.getSubsurfaceBlock()), -256, 0, -1, 0, false);
			layer.add(Blocks.BEDROCK.getDefaultState(), 0, 0, 1, 2, true);
		
			WE_Biome.addBiomeToGeneration(cp, new WE_BaseBiome(distance, biome.getPersistance(), biome.getOctaves(), biome.getHeight(), biome.getIntquility(), layer).setSize(biome.getBiomeSizeX(), 1.5D).setBaseSpawn());
			distance += 0.5D;
		}
	}

}
