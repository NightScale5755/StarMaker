package starmaker.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonParser;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
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
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import starmaker.CoreConfig;
import starmaker.StarMaker;
import starmaker.StarMaker.BiomeData;
import starmaker.StarMaker.DimData;
import starmaker.StarMaker.OreGenData;
import starmaker.dimension.TeleportTypePlanet;
import starmaker.dimension.WorldProviderPlanet;
import starmaker.utils.json.SolarSystemObjects;
import starmaker.utils.json.SystemImpl;
import starmaker.utils.json.planet.BiomeDecoratorImpl;
import starmaker.utils.json.planet.BiomeImpl;
import starmaker.utils.json.planet.PlanetImpl;
import starmaker.utils.json.planet.WorldDataImpl;

public class ParseConfig
{

	public static ParseConfig instance = new ParseConfig();
	private static int dimID = -1100;

	private static final int LIMIT_SYSTEM = 2;
	private static final int LIMIT_PLANETS = 10;

	public void parse(File file)
	{
		JsonParser parser = new JsonParser();
		parseSystems(new File(StarMaker.assetRoot), parser);
		parsePlanets(new File(StarMaker.planetDir), parser);

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
				if (count > LIMIT_SYSTEM)
				{
					StarMaker.info("Limit planets = " + LIMIT_PLANETS);
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
					for(BiomeDecoratorImpl data : biomeImpl.getOreGenList())
					{
						oregen.add(new OreGenData(data.getOreBlock(), data.getReplacedBlock(), data.getBlockCount(), data.getMinY(), data.getMaxY(), data.getAmountPerChunk()));
					}
					biomes.add(new BiomeData("biome_" + i, biomeImpl.getBiomeSize())
							.setData(biomeImpl.getPersistance(), biomeImpl.getHeight(), biomeImpl.getOctaves(), biomeImpl.getIntquility())
							.setBlocks(biomeImpl.getSurfaceBlock(), biomeImpl.getSubsurfaceBlock())
							.setColors(water, foliage, grass).setOreGenData(oregen));

				}

				WorldDataImpl dataImpl = impl.getWorldData();

				DimData data = new DimData(planet, dataImpl.getStoneBlock(), dataImpl.getMapSize())
						.setSkyFogColor(skyColor, fogColor).setSkyFogColor(skyColor, fogColor)
						.setBrightness(impl.getSunBrightness(), impl.getStarBrightness())
						.setGenCavesRavines(dataImpl.getGenCave(), dataImpl.getGenRavine(), dataImpl.getCrateProb(), dataImpl.getWaterBlock())
						.setBiomes(biomes).setSunSize(impl.getSunSize())
						.setWaterY(dataImpl.getWaterY());

				regDim(dimID--, data);

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
		GalacticraftRegistry.registerTeleportType(provider, new TeleportTypePlanet());

		// });
	}

	public static IBlockState getBlock2(String par1)
	{
		String[] meta = par1.split(":");
		Block blocks = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(meta[0] + ":" + meta[1]));
		if (meta.length > 2)
		{
			return blocks.getStateFromMeta(Integer.parseInt(meta[2]));
		}
		return blocks.getDefaultState();
	}
	
	public static IBlockState getBlock(String par1)
	{
		// String string = e.getAsJsonObject().get(par1).getAsString();
		/*String[] meta = par1.split(":");
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(meta[0] + ":" + meta[1]));

		Block blocks = Block.getBlockFromItem(item);
		if (meta.length > 2)
		{
			return blocks.getStateFromMeta(Integer.parseInt(meta[2]));
		}
		return blocks.getDefaultState();*/
		String[] meta = par1.split(":");
		Block blocks = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(meta[0] + ":" + meta[1]));
		if (meta.length > 2)
		{
			return blocks.getStateFromMeta(Integer.parseInt(meta[2]));
		}
		return blocks.getDefaultState();
	}

}
