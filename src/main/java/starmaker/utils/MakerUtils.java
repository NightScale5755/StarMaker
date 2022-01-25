package starmaker.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}
