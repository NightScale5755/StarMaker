package starmaker.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraftforge.fml.common.FMLCommonHandler;
import starmaker.StarMaker;
import starmaker.utils.data.DimData;

public class MakerUtils
{

    public static boolean debug = true;
    
 	//public static DimensionType dimType;
    //public static int dims = 0;
        
	public static File systemsJson = new File(StarMaker.assetDir + "/systems.json");
	public static File examplePlanetJson = new File(StarMaker.planetDir + "/example_planet.json");
	public static File exampleBiomeJson = new File(StarMaker.biomesDir + "/example_biome.json");
	
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static Map<Integer, DimData> bodies = new HashMap<Integer, DimData>();
	public static Map<CelestialBody, DimData> unreachable_bodies = new HashMap();
	
	public static TemplateHandler templates = new TemplateHandler(new File(StarMaker.assetDir, "structures").toString(), FMLCommonHandler.instance().getDataFixer());
}
