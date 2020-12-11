package starmaker.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
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
import starmaker.StarMaker;
import starmaker.StarMaker.BiomeData;
import starmaker.StarMaker.DimData;
import starmaker.dimension.TeleportTypePlanet;
import starmaker.dimension.WorldProviderPlanet;

public class ParseConfig {
	
	public static ParseConfig instance = new ParseConfig();
	private static int dimID = -1100; 	
	
	private static final int LIMIT_SYSTEM = 2;
	private static final int LIMIT_PLANETS = 10;
	
	public void parse(File file)
	{
		JsonParser parser = new JsonParser();
		File dir = new File(file, "StarMaker/jsons");
		parseSystems(dir, parser);
		parsePlanets(dir, parser);

	}
	
	private static void parseSystems(File file, JsonParser parser)
	{
		File systems = new File(file, "systems.json");
		int count = 0;
		
		try {	    		    	
	    	if(systems.exists() && systems.isFile())
	    	{
	    		JsonObject obj = (JsonObject) parser.parse(new FileReader(systems));
	    		
	    		//Systems
	    		for(int i = 0; i < obj.get("systems").getAsJsonArray().size(); i++)
	    		{
	    			if(count > LIMIT_SYSTEM) {
	    				StarMaker.info("Limit system = " + LIMIT_SYSTEM);
	    				break;
	    			}
	    			
	    			JsonObject el = obj.get("systems").getAsJsonArray().get(i).getAsJsonObject();
	    			
		    		//Solar System Data
		    		String name = el.get("name").getAsString();
		    		String galaxy = el.get("galaxy").getAsString();
		    		String star_name = el.get("star_name").getAsString();
		    		float posX = el.get("posX").getAsFloat();
		    		float posY = el.get("posY").getAsFloat();
		    		float star_size = el.get("star_size").getAsFloat();
		    		ClassBody star_class = ClassBody.values()[el.get("star_class").getAsInt()-1];
		    		StarColor star_color = StarColor.values()[el.get("star_color").getAsInt()-1];
		    		
		    		ResourceLocation icon = new ResourceLocation(StarMaker.ASSET_PREFIX, "textures/gui/celestialbodies/" + star_color.name().toLowerCase() + ".png");
		    		SolarSystem system = BodiesRegistry.registerSolarSystem(StarMaker.ASSET_PREFIX, name, BodiesRegistry.getGalaxy(galaxy), new Vector3(posX, posY, 0.0F), star_name, star_size, icon);
		    		GalaxyRegistry.registerSolarSystem(system);	
		    		
		    		BodiesData data = new BodiesData(TypeBody.STAR, star_class).setStarColor(star_color);
		    		BodiesRegistry.registerBodyData(system.getMainStar(), data);	
		    		
		    		count++;
	    		}
	    	}
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	private static void parsePlanets(File file, JsonParser parser)
	{
		File dir = new File(file, "planets");
		int count = 0;
		
		try {
			if(dir.exists())
			for(String files : dir.list())
			{
				if(count > LIMIT_SYSTEM) {
    				StarMaker.info("Limit planets = " + LIMIT_PLANETS);
    				break;
    			}
				
				File file_planet = new File(dir, files);
				if(!file_planet.isFile()) continue;
				JsonObject obj = (JsonObject) parser.parse(new FileReader(file_planet));
				
				String planet_name = file_planet.getName().replaceAll(".json", "");				
				String system_name = obj.get("parent_system").getAsString();
				
				if(!GalaxyRegistry.getRegisteredSolarSystems().containsKey(system_name)) 
					continue;
				
				SolarSystem system = GalaxyRegistry.getRegisteredSolarSystems().get(system_name);//getSystem(GalaxyRegistry.getSolarSystemID(system_name)); 
				
				float planet_phase = obj.get("phase").getAsFloat();
    			float planet_size = obj.get("size").getAsFloat();
    			float distancefromcenter = obj.get("distance_from_center").getAsFloat();
    			float relative_time = obj.get("relative_time").getAsFloat();
    			
    			float planet_gravity = obj.get("gravity").getAsFloat();
    			int planet_pressure = obj.get("atmosphere_pressure").getAsInt();
    			float planet_temperature = obj.get("temperature").getAsFloat();
    			float planet_wind = obj.get("wind").getAsFloat();
    			long planet_daylenght = obj.get("day_lenght").getAsLong();
    			boolean breath = obj.get("breathable").getAsBoolean();
    			boolean solar_radiation = obj.get("solar_radiation").getAsBoolean();
				
    			float sunBrightness = obj.get("sun_brightness").getAsFloat();
    			float starBrightness = obj.get("star_brightness").getAsFloat();
    			
    			JsonElement el3 = obj.get("world_data");
    			
    			double mapsize = 1000.0D;
    			if(el3.getAsJsonObject().get("mapsize") != null)
    				mapsize = el3.getAsJsonObject().get("mapsize").getAsDouble();
    			
    			int planet_tier = el3.getAsJsonObject().get("tier").getAsInt();
    			    			
    			
    			boolean caveGen = el3.getAsJsonObject().get("genCave").getAsBoolean();
    			boolean ravineGen = el3.getAsJsonObject().get("genRavine").getAsBoolean();
    			int crateProb = el3.getAsJsonObject().get("crateProb").getAsInt();
    			String stone = el3.getAsJsonObject().get("stone_block").getAsString();
    			
    			//IBlockState stone = getBlock(el3, "stone_block");
    			//StarMaker.info(getBlock(el3, "stone_block"));
    			
    			
    			int r = obj.get("sky").getAsJsonArray().get(0).getAsInt();
    			int g = obj.get("sky").getAsJsonArray().get(1).getAsInt();
    			int b = obj.get("sky").getAsJsonArray().get(2).getAsInt();
    			Vec3d skyColor = new Vec3d(r,g,b);
    			
    			r = obj.get("fog").getAsJsonArray().get(0).getAsInt();
    			g = obj.get("fog").getAsJsonArray().get(1).getAsInt();
    			b = obj.get("fog").getAsJsonArray().get(2).getAsInt();
    			Vec3d fogColor = new Vec3d(r,g,b);
    				
    			Planet planet = (ExPlanet) BodiesRegistry.registerExPlanet(system, planet_name, StarMaker.ASSET_PREFIX, distancefromcenter);
    			BodiesRegistry.setOrbitData(planet, planet_phase, planet_size, relative_time);
    			BodiesRegistry.setPlanetData(planet, planet_pressure, planet_daylenght, planet_gravity, solar_radiation);
    			BodiesRegistry.setProviderData(planet, WorldProviderPlanet.class, dimID, planet_tier, ACBiome.ACSpace);
    			planet.setAtmosphere(new AtmosphereInfo(breath, false, false, planet_temperature, planet_wind, 0.0F));	
    			    			
  	
    			List<BiomeData> biomes = new ArrayList<BiomeData>();
    			JsonElement biome_el = obj.get("biomes");
    			for(int i = 0; i < biome_el.getAsJsonArray().size(); i++)
    			{
    				if(i > 5) break;
    				
    				JsonObject bobj = biome_el.getAsJsonArray().get(i).getAsJsonObject();
    				float persistance = bobj.get("persistance").getAsFloat();
    				int octaves = bobj.get("octaves").getAsInt();
    				int height = bobj.get("height").getAsInt();
    				int intquility = bobj.get("intquility").getAsInt();
    				float biomeSize = bobj.get("biomeSize").getAsFloat();
    				int watercolor = Utils.getIntColor(bobj.get("water_color").getAsJsonArray().get(0).getAsInt(), bobj.get("water_color").getAsJsonArray().get(1).getAsInt(), bobj.get("water_color").getAsJsonArray().get(2).getAsInt());
    				int foliagecolor = Utils.getIntColor(bobj.get("foliage_color").getAsJsonArray().get(0).getAsInt(), bobj.get("foliage_color").getAsJsonArray().get(1).getAsInt(), bobj.get("foliage_color").getAsJsonArray().get(2).getAsInt());
    				int grasscolor = Utils.getIntColor(bobj.get("grass_color").getAsJsonArray().get(0).getAsInt(), bobj.get("grass_color").getAsJsonArray().get(1).getAsInt(), bobj.get("grass_color").getAsJsonArray().get(2).getAsInt());
    				
    				String surface = biome_el.getAsJsonArray().get(i).getAsJsonObject().get("surface_block").getAsString();
    				String subsurface = biome_el.getAsJsonArray().get(i).getAsJsonObject().get("subsurface_block").getAsString();
    				//IBlockState surface = getBlock(biome_el.getAsJsonArray().get(i), "surface_block");
    				//IBlockState subsurface = getBlock(biome_el.getAsJsonArray().get(i), "subsurface_block");
        			
        			biomes.add(new BiomeData("biome_"+ i, biomeSize).setData(persistance, height, octaves, intquility).setBlocks(surface, subsurface).setColors(watercolor, foliagecolor, grasscolor));
    			}
    			
    			
    			DimData data = new DimData(planet, stone, mapsize)
    			.setSkyFogColor(skyColor, fogColor)
    			.setSkyFogColor(skyColor, fogColor)  
    			.setBrightness(sunBrightness, starBrightness)
    			.setGenCavesRavines(caveGen, ravineGen, crateProb)
    			.setBiomes(biomes);  			
    			
    			regDim(dimID--, data);

    			count++;
			}

		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	private static void regDim(int dimID, DimData data)
    {
    	Class<? extends WorldProvider> provider = WorldProviderPlanet.class;
    	StarMaker.bodies.put(dimID, data);
    	//StarMaker.bodies.forEach((dimID, data) -> {
    		
    	data.getBody().setBiomeInfo(ACBiome.ACSpace);
    	data.getBody().setDimensionInfo(dimID, provider, true);			
    	data.getBody().addMobInfo(new Biome.SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));

    	//BodiesRegistry.registerBodyData(data.getBody(), data.getData());
    	if(data.getBody() instanceof Moon)
    		GalaxyRegistry.registerMoon((Moon) data.getBody());
    	else
    		GalaxyRegistry.registerPlanet((Planet) data.getBody());
    	//WorldUtil.registerPlanet(dimID, true, -1100);
    	GalacticraftRegistry.registerTeleportType(provider, new TeleportTypePlanet());

    	//});
    }
	
	public static IBlockState getBlock(String par1)
	{
		//String string = e.getAsJsonObject().get(par1).getAsString();
		String[] meta = par1.split(":");	
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(meta[0] + ":" + meta[1]));
		
		Block blocks = Block.getBlockFromItem(item);
		if(meta.length > 2) {			
			return blocks.getStateFromMeta(Integer.parseInt(meta[2]));
		}
		return blocks.getDefaultState();
	}

}
