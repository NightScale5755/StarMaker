package starmaker.utils.json;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonParser;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import asmodeuscore.core.prefab.TeleportTypeBody;
import asmodeuscore.core.utils.Utils;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import starmaker.CoreConfig;
import starmaker.StarMaker;
import starmaker.dimension.WorldProviderPlanet;
import starmaker.utils.MakerUtils;
import starmaker.utils.data.BiomeData;
import starmaker.utils.data.DimData;
import starmaker.utils.data.GrassGenData;
import starmaker.utils.data.OreGenData;
import starmaker.utils.data.TreeGenData;
import starmaker.utils.json.body.MoonImpl;
import starmaker.utils.json.body.PlanetImpl;
import starmaker.utils.json.data.BiomeImpl;
import starmaker.utils.json.data.GrassGenImpl;
import starmaker.utils.json.data.OreGenImpl;
import starmaker.utils.json.data.WorldDataImpl;

public class ParseFiles
{

	public static ParseFiles instance = new ParseFiles();
	private static int dimID = CoreConfig.startIDs;

	private static final int LIMIT_SYSTEM = 2;
	private static final int LIMIT_PLANETS = 10;
	private static final int LIMIT_MOONS = 10;

	public void parse(File file)
	{
		JsonParser parser = new JsonParser();
		parseSystems(new File(StarMaker.assetRoot), parser);
		parsePlanets(new File(StarMaker.planetDir), parser);
		parseMoons(new File(StarMaker.moonDir), parser);

	}

	private static void parseSystems(File file, JsonParser parser)
	{
		File systems = new File(file, "systems.json");
		int count = 1;

		try
		{
			if (systems.exists() && systems.isFile())
			{
				Reader reader = new FileReader(systems);
				SolarSystemObjects solarSystemObjects = MakerUtils.gson.fromJson(reader, SolarSystemObjects.class);
				for (SystemImpl systemImpl : solarSystemObjects.getSystems())
				{
					if (count > LIMIT_SYSTEM)
					{
						StarMaker.info("Limit system = " + LIMIT_SYSTEM);
						break;
					}
					
					// Solar System Data
					String name = systemImpl.getName();
					String galaxy = systemImpl.getGalaxy();
					String star_name = systemImpl.getStarName();
					float posX = systemImpl.getPosX();
					float posY = systemImpl.getPosY();
					float star_size = systemImpl.getStarSize();
					ClassBody star_class = ClassBody.values()[systemImpl.getStarClass()];
					StarColor star_color = StarColor.values()[systemImpl.getStarColor()];

					ResourceLocation icon = new ResourceLocation(StarMaker.ASSET_PREFIX,
							"textures/gui/celestialbodies/" + star_color.name().toLowerCase() + ".png");
					SolarSystem system = BodiesRegistry.registerSolarSystem(StarMaker.ASSET_PREFIX, name,
							BodiesRegistry.getGalaxy(galaxy), new Vector3(posX, posY, 0.0F), star_name, star_size,
							icon);
					GalaxyRegistry.registerSolarSystem(system);

					BodiesData data = new BodiesData(TypeBody.STAR, star_class).setStarColor(star_color);
					BodiesRegistry.registerBodyData(system.getMainStar(), data);
					StarMaker.LOG.debug("Registered New Solar System: %s", system.getName());
					count++;
				}

			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void parsePlanets(File file, JsonParser parser)
	{
		int count = 1;

		try
		{

			FilenameFilter filter = (file1, name) -> name.endsWith(".json");

			File[] files = file.listFiles(filter);
			StarMaker.LOG.info("#  of Planet Jsons: " + files.length);

			for (File planetFile : files)
			{
				if (count > LIMIT_PLANETS)
				{
					StarMaker.info("Ignore: " + planetFile.getName() + ". Limit planets = " + LIMIT_PLANETS);
					break;
				}

				
				if (!planetFile.isFile())
					continue;
				
				StarMaker.LOG.info("FileName: " + planetFile.getName());

				Reader reader = new FileReader(planetFile);
				PlanetImpl impl = MakerUtils.gson.fromJson(reader, PlanetImpl.class);

				if (!GalaxyRegistry.getRegisteredSolarSystems().containsKey(impl.getParentSystem()))
					continue;

				SolarSystem system = GalaxyRegistry.getRegisteredSolarSystems().get(impl.getParentSystem());
				String planet_name = planetFile.getName().replaceAll(".json", "");

				Planet planet = BodiesRegistry.registerExPlanet(system, planet_name, CoreConfig.resourceDomain, impl.getDistanceFromCenter());
				BodiesRegistry.setOrbitData(planet, impl.getPhase(), impl.getSize(), impl.getRelativeTime());
				BodiesRegistry.setPlanetData(planet, impl.getAtmospherePressure(), impl.getDayLenght(),	impl.getGravity(), impl.getSolarRadiation());
				BodiesRegistry.setProviderData(planet, WorldProviderPlanet.class, dimID, impl.getWorldData().getTier(), ACBiome.ACSpace);
				planet.setAtmosphere(new AtmosphereInfo(impl.getBreathable(), impl.getPrecipitation(), impl.getCorrosiveAtmo(), impl.getTemperature(), impl.getWind(), 0.0F));

				StarMaker.LOG.debug("Registered New Planet: %s", planet.getName());

				Vec3d skyColor = new Vec3d(impl.getSky());
				Vec3d fogColor = new Vec3d(impl.getFog());

				List<BiomeData> biomes = new ArrayList<BiomeData>();
				List<BiomeImpl> biomesToParse = impl.getBiomes();
				for (int i = 0; i < biomesToParse.size(); i++)
				{
					if (i > 5)
						break;
					BiomeImpl biomeImpl = biomesToParse.get(i);

					int water = Utils.getIntColor(biomeImpl.getWaterColor().intX(), biomeImpl.getWaterColor().intY(), biomeImpl.getWaterColor().intZ());
					int foliage = Utils.getIntColor(biomeImpl.getFoliageColor().intX(),	biomeImpl.getFoliageColor().intY(), biomeImpl.getFoliageColor().intZ());
					int grass = Utils.getIntColor(biomeImpl.getGrassColor().intX(), biomeImpl.getGrassColor().intY(), biomeImpl.getGrassColor().intZ());

					List<OreGenData> oregen = new ArrayList();
					for(OreGenImpl data : biomeImpl.getOreGenList())
					{
						oregen.add(new OreGenData(data.getOreBlock(), data.getReplacedBlock(), data.getBlockCount(), data.getMinY(), data.getMaxY(), data.getAmountPerChunk()));
					}
					TreeGenData treegen = null;
					if(biomeImpl.getTreeGen() != null) 
						treegen = new TreeGenData(biomeImpl.getTreeGen().getLog(), biomeImpl.getTreeGen().getLeaves(), biomeImpl.getTreeGen().getSapling(), biomeImpl.getTreeGen().getMinHeight(), biomeImpl.getTreeGen().getVines(), biomeImpl.getTreeGen().getQuantity());
					
					List<GrassGenData> grassgen = new ArrayList();
					if(biomeImpl.getGrassGenList() != null)
						for(GrassGenImpl data : biomeImpl.getGrassGenList())
						{
							if(data != null)
								grassgen.add(new GrassGenData(data.getGrassBlock(), data.getGrassCount(), data.onWater(), data.getGroundBlock()));
						}

					biomes.add(new BiomeData("biome_" + i, biomeImpl.getBiomeSize())
							.setData(biomeImpl.getPersistance(), biomeImpl.getHeight(), biomeImpl.getOctaves(), biomeImpl.getIntquility())
							.setBlocks(biomeImpl.getSurfaceBlock(), biomeImpl.getSubsurfaceBlock())
							.setColors(water, foliage, grass).setOreGenData(oregen).setTreeGenData(treegen).setGrassGenData(grassgen));

				}

				WorldDataImpl dataImpl = impl.getWorldData();

				DimData data = new DimData(planet, dataImpl.getStoneBlock(), dataImpl.getMapSize())
						.setSkyFogColor(skyColor, fogColor).setSkyFogColor(skyColor, fogColor)
						.setBrightness(impl.getSunBrightness(), impl.getStarBrightness())
						.setGenCavesRavines(dataImpl.getGenCave(), dataImpl.getGenRavine(), dataImpl.getCrateProb(), dataImpl.getWaterBlock())
						.setBiomes(biomes).setSunSize(impl.getSunSize())
						.setWaterY(dataImpl.getWaterY());

				regDim(getAvailableID(), data);

				count++;
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void parseMoons(File file, JsonParser parser)
	{
		int count = 1;

		try
		{
			FilenameFilter filter = (file1, name) -> name.endsWith(".json");

			File[] files = file.listFiles(filter);
			if(files == null) return;
			
			StarMaker.LOG.info("#  of Moons Jsons: " + files.length);
			
			for (File moonsFile : files)
			{
				if (count > LIMIT_MOONS)
				{
					StarMaker.info("Ignore: " + moonsFile.getName() + ". Limit moons = " + LIMIT_MOONS);
					break;
				}
				
				if (!moonsFile.isFile())
					continue;
				
				Reader reader = new FileReader(moonsFile);
				MoonImpl impl = MakerUtils.gson.fromJson(reader, MoonImpl.class);
				
				if (!GalaxyRegistry.getRegisteredPlanets().containsKey(impl.getParentPlanet()))
					continue;

				Planet planet = GalaxyRegistry.getRegisteredPlanets().get(impl.getParentPlanet());
				String moon_name = moonsFile.getName().replaceAll(".json", "");

				
				Moon moon = BodiesRegistry.registerExMoon(planet, moon_name, CoreConfig.resourceDomain, impl.getDistanceFromCenter());
				BodiesRegistry.setOrbitData(moon, impl.getPhase(), impl.getSize(), impl.getRelativeTime());
				BodiesRegistry.setPlanetData(moon, impl.getAtmospherePressure(), impl.getDayLenght(),	impl.getGravity(), impl.getSolarRadiation());
				BodiesRegistry.setProviderData(moon, WorldProviderPlanet.class, dimID, impl.getWorldData().getTier(), ACBiome.ACSpace);
				moon.setAtmosphere(new AtmosphereInfo(impl.getBreathable(), impl.getPrecipitation(), impl.getCorrosiveAtmo(), impl.getTemperature(), impl.getWind(), 0.0F));

				StarMaker.LOG.debug("Registered New Moon: %s", moon.getName());
				
				Vec3d skyColor = new Vec3d(impl.getSky());
				Vec3d fogColor = new Vec3d(impl.getFog());

				List<BiomeData> biomes = new ArrayList<BiomeData>();
				for (int i = 0; i < impl.getBiomes().size(); i++)
				{
					if (i > 5) break;
					BiomeImpl biomeImpl = impl.getBiomes().get(i);

					int water = Utils.getIntColor(biomeImpl.getWaterColor().intX(), biomeImpl.getWaterColor().intY(), biomeImpl.getWaterColor().intZ());
					int foliage = Utils.getIntColor(biomeImpl.getFoliageColor().intX(),	biomeImpl.getFoliageColor().intY(), biomeImpl.getFoliageColor().intZ());
					int grass = Utils.getIntColor(biomeImpl.getGrassColor().intX(), biomeImpl.getGrassColor().intY(), biomeImpl.getGrassColor().intZ());

					List<OreGenData> oregen = new ArrayList();
					for(OreGenImpl data : biomeImpl.getOreGenList())					
						oregen.add(new OreGenData(data.getOreBlock(), data.getReplacedBlock(), data.getBlockCount(), data.getMinY(), data.getMaxY(), data.getAmountPerChunk()));
				
					TreeGenData treegen = null;
					if(biomeImpl.getTreeGen() != null) 
						treegen = new TreeGenData(biomeImpl.getTreeGen().getLog(), biomeImpl.getTreeGen().getLeaves(), biomeImpl.getTreeGen().getSapling(), biomeImpl.getTreeGen().getMinHeight(), biomeImpl.getTreeGen().getVines(), biomeImpl.getTreeGen().getQuantity());
					
					List<GrassGenData> grassgen = new ArrayList();
					if(biomeImpl.getGrassGenList() != null)
						for(GrassGenImpl data : biomeImpl.getGrassGenList())						
							if(data != null)
								grassgen.add(new GrassGenData(data.getGrassBlock(), data.getGrassCount(), data.onWater(), data.getGroundBlock()));
						
					biomes.add(new BiomeData("biome_" + i, biomeImpl.getBiomeSize())
							.setData(biomeImpl.getPersistance(), biomeImpl.getHeight(), biomeImpl.getOctaves(), biomeImpl.getIntquility())
							.setBlocks(biomeImpl.getSurfaceBlock(), biomeImpl.getSubsurfaceBlock())
							.setColors(water, foliage, grass).setOreGenData(oregen).setTreeGenData(treegen).setGrassGenData(grassgen));
				}
				
				WorldDataImpl dataImpl = impl.getWorldData();

				DimData data = new DimData(moon, dataImpl.getStoneBlock(), dataImpl.getMapSize())
						.setSkyFogColor(skyColor, fogColor).setSkyFogColor(skyColor, fogColor)
						.setBrightness(impl.getSunBrightness(), impl.getStarBrightness())
						.setGenCavesRavines(dataImpl.getGenCave(), dataImpl.getGenRavine(), dataImpl.getCrateProb(), dataImpl.getWaterBlock())
						.setBiomes(biomes).setSunSize(impl.getSunSize())
						.setWaterY(dataImpl.getWaterY());

				regDim(getAvailableID(), data);

				count++;
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void regDim(int dimID, DimData data)
	{
		Class<? extends WorldProvider> provider = WorldProviderPlanet.class;
		StarMaker.bodies.put(dimID, data);
		// StarMaker.bodies.forEach((dimID, data) -> {

		data.getBody().setBiomeInfo(ACBiome.ACSpace);
		data.getBody().setDimensionInfo(dimID, provider, true);
		data.getBody().addMobInfo(new Biome.SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));

		// BodiesRegistry.registerBodyData(data.getBody(), data.getData());
		if (data.getBody() instanceof Moon)
			GalaxyRegistry.registerMoon((Moon) data.getBody());
		else
			GalaxyRegistry.registerPlanet((Planet) data.getBody());
		// WorldUtil.registerPlanet(dimID, true, -1100);
		GalacticraftRegistry.registerTeleportType(provider, new TeleportTypeBody());

		// });
	}
	
	public static IBlockState getBlock(String par1)
	{
		String[] meta = par1.split(":");
		Block blocks = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(meta[0] + ":" + meta[1]));
		if (meta.length > 2)
		{
			return blocks.getStateFromMeta(Integer.parseInt(meta[2]));
		}
		return blocks.getDefaultState();
	}
	
	private static int getAvailableID()
	{
		return dimID--;
	}

}
