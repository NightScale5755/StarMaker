package starmaker.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import starmaker.StarMaker;

public class MakerUtils
{

	public static File exampleSystemsJson = new File(StarMaker.assetRoot + "/systems.json");
	public static File exmaplePlanetJson = new File(StarMaker.planetDir + "/example_planet.json");
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

}
