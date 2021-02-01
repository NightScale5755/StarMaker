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

	public static File exampleSystemsJson = new File(StarMaker.systemsDir + "/systems.json");
	public static File exmaplePlanetJson = new File(StarMaker.planetDir + "/example_planet.json");
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	// Resource Downloading
//	private static File langFile = new File(StarMaker.assetRoot + "/lang/en_US.lang");
//	private static File pngFile = new File(StarMaker.assetRoot + "/textures/gui/celestialbodies/example_system/example_planet.png");
//
//	private static void getFiles(String url, File file) throws IOException
//	{
//		try
//		{
//			FileUtils.copyURLToFile(new URL(url), file, 10000, 10000);
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public static void genExampleResources() throws IOException
//	{
//		MakerUtils.getFiles("https://maven.romvoid.dev/example_planet.png", MakerUtils.pngFile);
//		MakerUtils.getFiles("https://maven.romvoid.dev/en_US.lang", MakerUtils.langFile);
//	}

}
