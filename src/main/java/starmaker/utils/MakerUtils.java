package starmaker.utils;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import starmaker.StarMaker;

public class MakerUtils
{

	public static File exampleSystemsJson = new File(StarMaker.assetRoot + "/systems.json");
	public static File exmaplePlanetJson = new File(StarMaker.planetDir + "/example_planet.json");
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

}
