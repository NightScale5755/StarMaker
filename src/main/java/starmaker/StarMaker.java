package starmaker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asmodeuscore.core.handler.ColorBlockHandler;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import starmaker.events.SMEventHandler;
import starmaker.proxy.CommonProxy;
import starmaker.resources.StarMakerAssets;
import starmaker.utils.ExampleFiles;
import starmaker.utils.Log;
import starmaker.utils.data.DimData;
import starmaker.utils.json.ParseFiles;

@Mod(
		   modid = StarMaker.MODID,
		   version = StarMaker.VERSION,
		   dependencies = Constants.DEPENDENCIES_FORGE + "required-after:galacticraftcore; required-after:galacticraftplanets; required-after:asmodeuscore@[0.0.27,)",
		   acceptedMinecraftVersions = Constants.MCVERSION,
		   name = StarMaker.NAME
		)
public class StarMaker {
	
	public static final int major_version = 0;
	public static final int minor_version = 0;
	public static final int build_version = 3;
	
	public static final String NAME = "StarMaker";
	public static final String MODID = "starmaker";
    public static final String VERSION = major_version + "." + minor_version + "." + build_version;
    public static final String ASSET_PREFIX = MODID;
    public static final String TEXTURE_PREFIX = ASSET_PREFIX + ":";
    
    public static String planetDir, moonDir;    
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
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {   
    	assetRoot = event.getModConfigurationDirectory() + "/StarMaker/resources/" + CoreConfig.resourceDomain;
    	//systemsDir = event.getModConfigurationDirectory() + "/StarMaker/resources/" + CoreConfig.resourceDomain;
    	planetDir = event.getModConfigurationDirectory() + "/StarMaker/resources/" + CoreConfig.resourceDomain + "/bodies/planets";    	
    	moonDir = event.getModConfigurationDirectory() + "/StarMaker/resources/" + CoreConfig.resourceDomain + "/bodies/moons";    	
    	
    	new ExampleFiles();

    	proxy.preload();
		
		proxy.register_event(new SMEventHandler());
		    	
		
		
		ColorBlockHandler.addLeavesBlock(Blocks.LEAVES.getDefaultState());
	    ColorBlockHandler.addLeavesBlock(Blocks.LEAVES2.getDefaultState());
	    ColorBlockHandler.addLeavesBlock(Blocks.VINE.getDefaultState());
	    ColorBlockHandler.addWaterBlock(Blocks.WATER.getDefaultState());
		ColorBlockHandler.addWaterBlock(Blocks.FLOWING_WATER.getDefaultState());
		ColorBlockHandler.addBlock(Blocks.GRASS.getDefaultState());
		ColorBlockHandler.addBlock(Blocks.TALLGRASS.getDefaultState());
		ColorBlockHandler.addBlock(Blocks.FLOWER_POT.getDefaultState());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {  
    	proxy.load();  	
    	ParseFiles.instance.parse();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	proxy.postload();
    }
    
	public static void defineResourcePack(List resourceList)
	{
		if (FMLLaunchHandler.side().isClient())
		{
			resourceList.add(new StarMakerAssets());
		}

	}
   
    public static void info(Object message)
   	{ 
   		LOG.info(message.toString());
   	}  
    
    public static void debug(Object message)
   	{ 
   		if(debug) 
   			LOG.info("[DEBUG StarMaker] ",  message.toString());
   	}  
    
}
