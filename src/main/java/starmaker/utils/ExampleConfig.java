package starmaker.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import starmaker.utils.json.SolarSystemObjects;
import starmaker.utils.json.SystemImpl;
import starmaker.utils.json.planet.BiomeImpl;
import starmaker.utils.json.planet.PlanetImpl;
import starmaker.utils.json.planet.WorldDataImpl;

public class ExampleConfig
{

	private PlanetImpl examplePlanetImpl;
	private SolarSystemObjects systemsObjects;
	private boolean generateSystemsJson = false;
	private boolean generatePlanetsJson = false;

	public ExampleConfig()
	{
		// @formatter:off
		initializeExampleFiles();

		SystemImpl exampleSystemImpl = new SystemImpl("example_solarsystem", "milky_way", "example_star", 1.5, 1.5, 0.8, 3, 7);

		WorldDataImpl exampleDataImpl = new WorldDataImpl(6, true, false, 0, "minecraft:concrete:11", 1000.0);

		List<Integer> water = Arrays.asList(255, 255, 0);
		List<Integer> foliage = Arrays.asList(0, 100, 0);
		List<Integer> grass = Arrays.asList(0, 100, 100);

		BiomeImpl exampleBiomeImpl1 = new BiomeImpl(2.2, 5, 130, 10, 200.0, water, foliage, grass, "minecraft:grass", "minecraft:dirt");

		BiomeImpl exampleBiomeImpl2 = new BiomeImpl(1.8, 4, 64, 25, 280.0, water, foliage, grass, "minecraft:cobblestone", "minecraft:dirt");

		systemsObjects = new SolarSystemObjects().addSystemToList(exampleSystemImpl);

		examplePlanetImpl = new PlanetImpl()
				.withParentSystem("example_solarsystem")
				.withPhase(3.14)
				.withSize(1.2)
				.withDistanceFromCenter(2.5)
				.withRelativeTime(3.9)
				.withGravity(0.058)
				.withAtmospherePressure(1)
				.withTemperature(-1.0)
				.withWind(0.0)
				.withDayLenght(24000)
				.withBreathable(false)
				.withSolarRadiation(false)
				.withCorrosiveAtmo(false)
				.withSunBrightness(0.5)
				.withStarBrightness(0.5)
				.withSky(Arrays.asList(78, 38, 137))
				.withFog(Arrays.asList(78, 38, 137))
				.withWorldData(exampleDataImpl)
				.withBiomes(Arrays.asList(exampleBiomeImpl1, exampleBiomeImpl2));
		// @formatter:on
		
		if (this.generateSystemsJson == true)
		{
			try
			{
				genSystemsExample();
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
		if (!MakerUtils.exampleSystemsJson.exists())
		{
			try
			{
				MakerUtils.exampleSystemsJson.getParentFile().mkdirs();
				MakerUtils.exampleSystemsJson.createNewFile();
				this.generateSystemsJson = true;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (!MakerUtils.exmaplePlanetJson.exists())
		{
			try
			{
				MakerUtils.exmaplePlanetJson.getParentFile().mkdirs();
				MakerUtils.exmaplePlanetJson.createNewFile();
				this.generatePlanetsJson = true;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void genSystemsExample() throws IOException
	{
		FileWriter writer = new FileWriter(MakerUtils.exampleSystemsJson.getAbsolutePath());
		MakerUtils.gson.toJson(this.systemsObjects, writer);
		writer.close();
	}

	private void genPlanetsExample() throws IOException
	{
		FileWriter writer = new FileWriter(MakerUtils.exmaplePlanetJson.getAbsolutePath());
		MakerUtils.gson.toJson(this.examplePlanetImpl, writer);
		writer.close();
	}
}
