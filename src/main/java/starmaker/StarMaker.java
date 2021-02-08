package starmaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import starmaker.events.SMEventHandler;
import starmaker.proxy.CommonProxy;
import starmaker.resources.StarMakerAssets;
import starmaker.utils.ExampleConfig;
import starmaker.utils.Log;
import starmaker.utils.ParseConfig;

@Mod(
		   modid = StarMaker.MODID,
		   version = StarMaker.VERSION,
		   dependencies = Constants.DEPENDENCIES_FORGE + "required-after:galacticraftcore; required-after:galacticraftplanets; required-after:asmodeuscore@[0.0.6,)",
		   acceptedMinecraftVersions = Constants.MCVERSION,
		   name = StarMaker.NAME
		   //guiFactory = "galaxyspace.core.client.gui.GSConfigGuiFactory"
		)
public class StarMaker {
	
	public static final int major_version = 1;
	public static final int minor_version = 0;
	public static final int build_version = 0;
	
	public static final String NAME = "StarMaker";
	public static final String MODID = "starmaker";
    public static final String VERSION = major_version + "." + minor_version + "." + build_version;
    public static final String ASSET_PREFIX = MODID;
    public static final String TEXTURE_PREFIX = ASSET_PREFIX + ":";
    
    public static String systemsDir;
    public static String planetDir;
    
    public static String assetRoot;
    
    public static DimensionType dimType;
    
    public static int dims = 0;

    @Instance(StarMaker.MODID)
    public static StarMaker instance;
    
    public static final Log LOG = new Log();
    
    @SidedProxy(clientSide=MODID+".proxy.ClientProxy", serverSide=MODID+".proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static Map<Integer, DimData> bodies = new HashMap();
    
    public static boolean debug = true;
    
    static StarMakerAssets starMakerResources;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {   
    	assetRoot = event.getModConfigurationDirectory() + "/StarMaker/resources/" + CoreConfig.resourceDomain;
    	systemsDir = event.getModConfigurationDirectory() + "/StarMaker";
    	planetDir = event.getModConfigurationDirectory() + "/StarMaker/planets";
    	
    	
    	new ExampleConfig();

    	proxy.preload();
		
		proxy.register_event(new SMEventHandler());
		
    	
		ParseConfig.instance.parse(event.getModConfigurationDirectory());		
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {  
    	proxy.load();  	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	proxy.postload();
    }
    
	public static void defineResourcePack(List resourceList)
	{
		if (starMakerResources == null)
		{
			starMakerResources = new StarMakerAssets();
		}

		resourceList.add(starMakerResources);
	}
   
    public static void info(Object message)
   	{ 
   		LOG.info(message.toString());
   	}  
    
    public static void debug(Object message)
   	{ 
   		if(debug) 
   			LOG.debug("[DEBUG] ",  message.toString());
   	}  
    
    
    public static class DimData
    {
    	private final CelestialBody body;
    	private Vec3d skyColor, fogColor;
    	private boolean genCaves, genRavines;
    	private int crateprob = 0;
    	private String stone_block;
    	private List<BiomeData> getBiomes = new ArrayList<BiomeData>();
    	private float sunBrightness, starBrightness;
    	private double mapsize;
    	
    	public DimData(CelestialBody body, String stone, double size)
    	{
    		this.body = body;
    		this.stone_block = stone;
    		this.mapsize = size;
    	}
    	
    	public DimData setSkyFogColor(Vec3d sky, Vec3d fog)
    	{
    		this.skyColor = sky;
    		this.fogColor = fog;
    		return this;
    	}   	
    	
    	public DimData setGenCavesRavines(boolean cave, boolean ravine, int crateprob)
    	{
    		this.genCaves = cave;
    		this.genRavines = ravine;
    		this.crateprob = crateprob;
    		return this;
    	}
    	
    	public DimData setBiomes(List<BiomeData> biome)
    	{
    		this.getBiomes = biome;    		
    		return this;
    	}
    	
    	public DimData setBrightness(float sun, float star)
    	{
    		this.sunBrightness = sun;
    		this.starBrightness = star;
    		return this;
    	}
    	
    	public CelestialBody getBody() 	{ return this.body;	}
    	public Vec3d getSkyColor() { return this.skyColor; }    	
    	public Vec3d getFogColor() { return this.fogColor; }    	
    	public String getStoneBlock() { return this.stone_block; }
    	public boolean getGenCaves() { return this.genCaves; }
    	public boolean getGenRavines() { return this.genRavines; }
    	public int getCrateProb() { return this.crateprob; }
    	public List<BiomeData> getBiomes() { return this.getBiomes; }
    	public float getSunBrightness() { return this.sunBrightness; }
    	public float getStarBrightness() { return this.starBrightness; }
    	public double getMapSize() { return this.mapsize; }
    }
    
    public static class BiomeData
    {
    	private final String biomename;
    	private float persistance, biomesizex;
    	private int octaves, height, intquility, watercolor, foliagecolor, grasscolor;
    	private String surface_block, subsurface_block;
    	
    	public BiomeData(String name, float biomesizex)
    	{
    		this.biomename = name;
    		this.biomesizex = biomesizex;
    	}
    	
    	public BiomeData setData(float persistance, int height, int octaves, int intquility)
    	{
    		this.persistance = persistance;
    		this.height = height;
    		this.octaves = octaves;
    		this.intquility = intquility;
    		return this;
    	}
    	
    	public BiomeData setBlocks(String surface, String subsurface)
    	{
    		this.surface_block = surface;
    		this.subsurface_block = subsurface;    		
    		return this;
    	}
    	
    	public BiomeData setColors(int watercolor, int foliagecolor, int grasscolor)
    	{
    		this.watercolor = watercolor;
    		this.foliagecolor = foliagecolor;
    		this.grasscolor = grasscolor;
    		return this;
    	}
    	
    	public float getPersistance() { return this.persistance; }
    	public float getBiomeSizeX() { return this.biomesizex; }
    	public int getOctaves() { return this.octaves; }
    	public int getHeight() { return this.height; }
    	public int getIntquility() { return this.intquility; }
    	public int getWaterColor() { return this.watercolor; }
    	public int getFolageColor() { return this.foliagecolor; }
    	public int getGrassColor() { return this.grasscolor; }
    	
    	public String getSurfaceBlock() { return this.surface_block; }    	
    	public String getSubsurfaceBlock() { return this.subsurface_block; }    	    	
    }
}
