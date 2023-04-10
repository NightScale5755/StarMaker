package starmaker.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import starmaker.CoreConfig;
import starmaker.StarMaker;
import starmaker.utils.json.SolarSystemObjects;
import starmaker.utils.json.celestialimpl.PlanetImpl;
import starmaker.utils.json.celestialimpl.SystemImpl;
import starmaker.utils.json.data.BiomeImpl;
import starmaker.utils.json.data.GrassGenImpl;
import starmaker.utils.json.data.LakesGenImpl;
import starmaker.utils.json.data.OrbitDataImpl;
import starmaker.utils.json.data.OreGenImpl;
import starmaker.utils.json.data.TreeGenImpl;
import starmaker.utils.json.data.WorldDataImpl;

public class ExampleFiles
{

	private PlanetImpl examplePlanetImpl;
	private BiomeImpl exampleBiomeImpl;
	private SolarSystemObjects systemsObjects;
	private boolean generateSystemsJson = false;
	private boolean generatePlanetsJson = false;
	private boolean generateBiomeJson = false;

	public ExampleFiles()
	{
		// @formatter:off
		/*if(CoreConfig.generateExample)
			initializeExampleFiles();*/

		//SystemImpl exampleSystemImpl = new SystemImpl("example_system", "milky_way", "example_star", 1.5, 1.5, 0.8, 2, 6);

		
		OrbitDataImpl exampleOrbitData = new OrbitDataImpl(3.14F, 1.2F, 2.5F, 3.9F, 0.0F, 0.0F);
		WorldDataImpl exampleDataImpl = new WorldDataImpl(6, true, false, 0, "minecraft:concrete:11", 1000.0, "", 64, -1, true);

		List<Integer> water = Arrays.asList(255, 255, 0);
		List<Integer> foliage = Arrays.asList(0, 100, 0);
		List<Integer> grass = Arrays.asList(0, 100, 100);

		List<OreGenImpl> oregen = Arrays.asList(new OreGenImpl("minecraft:dirt", "minecraft:cobblestone", 5, 80, 90, 20));
		TreeGenImpl treegen = new TreeGenImpl("minecraft:log", "minecraft:leaves", "minecraft:sapling", 8, false, 3);
		List<GrassGenImpl> grassgen = Arrays.asList(new GrassGenImpl("minecraft:tallgrass:1", "minecraft:grass", 5, false));
		LakesGenImpl lakesgen = new LakesGenImpl("minecraft:lava", 20);
		
		//BiomeImpl exampleBiomeImpl1 = new BiomeImpl(2.2, 4, 130, 10, 2.0, water, foliage, grass, "minecraft:grass", "minecraft:dirt", oregen, treegen, grassgen, null);

		//BiomeImpl exampleBiomeImpl2 = new BiomeImpl(1.8, 4, 64, 25, 2.0, water, foliage, grass, "minecraft:cobblestone", "minecraft:dirt", oregen, null, null, lakesgen);
		
		//systemsObjects = new SolarSystemObjects().addSystemToList(exampleSystemImpl);

		exampleBiomeImpl = new BiomeImpl(2.2, 4, 130, 10, 2.0, water, foliage, grass, "minecraft:grass", "minecraft:dirt", oregen, treegen, grassgen, lakesgen);
		
		examplePlanetImpl = new PlanetImpl()
				.withParentSystem("example_system")
				.withOrbitData(exampleOrbitData)
				.withGravity(0.058)
				.withAtmospherePressure(1)
				.withTemperature(Arrays.asList(-1.0F, 1.0F))
				.withWind(0.0)
				.withDayLenght(24000)
				.withBreathable(false)
				.withSolarRadiation(false)
				.withCorrosiveAtmo(false)
				.withSunBrightness(0.5)
				.withStarBrightness(0.5)
				.withSky(Arrays.asList(78, 38, 137))
				.withFog(Arrays.asList(78, 38, 137))
				.withCloud(Arrays.asList(150, 150, 150, 180))
				.withWorldData(exampleDataImpl)
				.withBiomes(Arrays.asList("example_biome"))
				.withSunSize(5.0F)
				.withPrecipitation(false)
				.withUnreachable(false);
		// @formatter:on
		
		if (this.generateSystemsJson == true)
		{
			try
			{
				genSystems();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (this.generateBiomeJson == true)
		{
			try
			{
				genBiomeExample();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (this.generatePlanetsJson == true)
		{
			try
			{
				genPlanetsExample();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void initializeExampleFiles()
	{
		if(!MakerUtils.systemsJson.exists())
		{
			try
			{
				
				MakerUtils.systemsJson.getParentFile().mkdirs();
				MakerUtils.systemsJson.createNewFile();
				this.generateSystemsJson = true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File folder = new File(StarMaker.biomesDir);
		if(!MakerUtils.exampleBiomeJson.exists() && (!folder.exists() || folder.listFiles().length == 0))
		{
			try
			{
				MakerUtils.exampleBiomeJson.getParentFile().mkdirs();
				MakerUtils.exampleBiomeJson.createNewFile();
				this.generateBiomeJson = true;
				
			} catch (IOException e)	{
				e.printStackTrace();
			}
		}
		
		folder = new File(StarMaker.planetDir);
		
		if(!MakerUtils.examplePlanetJson.exists() && (!folder.exists() || folder.listFiles().length == 0))
		{
			try
			{
				MakerUtils.examplePlanetJson.getParentFile().mkdirs();
				MakerUtils.examplePlanetJson.createNewFile();
				this.generatePlanetsJson = true;				
			} catch (IOException e)	{
				e.printStackTrace();
			}
		}
		
		File assets = new File(StarMaker.assetDir);
		if(!assets.exists())
		{
			assets.mkdirs();
		}
		/*
		if(!(MakerUtils.exampleSystemsJson.exists() && MakerUtils.exmaplePlanetJson.exists())) {
			try
			{
				MakerUtils.exampleSystemsJson.getParentFile().mkdirs();
				MakerUtils.exampleSystemsJson.createNewFile();
				this.generateSystemsJson = true;
				
				MakerUtils.exmaplePlanetJson.getParentFile().mkdirs();
				MakerUtils.exmaplePlanetJson.createNewFile();
				this.generatePlanetsJson = true;
				
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}*/
	}

	private void genSystems() throws IOException
	{
		FileWriter writer = new FileWriter(MakerUtils.systemsJson.getAbsolutePath());
		MakerUtils.gson.toJson(this.systemsObjects, writer);
		writer.close();
	}

	private void genPlanetsExample() throws IOException
	{
		FileWriter writer = new FileWriter(MakerUtils.examplePlanetJson.getAbsolutePath());
		MakerUtils.gson.toJson(this.examplePlanetImpl, writer);
		writer.close();
	}
	
	private void genBiomeExample() throws IOException
	{
		FileWriter writer = new FileWriter(MakerUtils.exampleBiomeJson.getAbsolutePath());
		MakerUtils.gson.toJson(this.exampleBiomeImpl, writer);
		writer.close();
	}
}
