package starmaker.utils.zip;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import starmaker.StarMaker;
import starmaker.utils.MakerUtils;
import starmaker.utils.json.ParseFiles;

public class ZipUtils {

	public static Map<File, ZipFile> celestial_packs = new HashMap<File, ZipFile>();

	public static void initZipFiles() {

		File zipDir = new File("resourcepacks");

		if (!zipDir.exists())
			zipDir.mkdir();

		FilenameFilter filter = (file1, name) -> name.endsWith("_celestialpack.zip");
		File[] files = zipDir.listFiles(filter);

		for (File file : files) {
			try {
				celestial_packs.put(file, new ZipFile(file));
				StarMaker.info("Loaded celestialpack: " + file.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void parseCelestialFiles() {
		ZipUtils.celestial_packs.forEach((K, V) -> {

			Enumeration<? extends ZipEntry> entries = V.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				try {

					InputStream stream = V.getInputStream(entry);
					String[] s = entry.getName().split("/");

					if (entry.getName().contains("biomes/") && entry.getName().contains(".json")
							&& !entry.isDirectory()) {						
						ParseFiles.instance.parseBiomes(stream, s[s.length - 1].replace(".json", ""));
					}

					if (entry.getName().contains("structures/") && entry.getName().contains(".nbt")) {						
						String name = s[s.length - 1].replace(".nbt", "");
						MakerUtils.templates.readTemplateFromStream(name, stream);
					}
					
					if (entry.getName().contains("bodies/systems/") && entry.getName().contains(".json"))
						ParseFiles.instance.parseSystems(stream, s[s.length - 1].replace(".json", ""));
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		ZipUtils.celestial_packs.forEach((K, V) -> {

			Enumeration<? extends ZipEntry> entries = V.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				try {
					if (entry.getName().contains(".json") && !entry.isDirectory()) {
						InputStream stream = V.getInputStream(entry);
						String[] s = entry.getName().split("/");
					
						if (entry.getName().contains("bodies/planets/"))
							ParseFiles.instance.parsePlanets(stream, s[s.length - 1].replace(".json", ""));

						if (entry.getName().contains("bodies/asteroids/"))
							ParseFiles.instance.parseAsteroids(stream, s[s.length - 1].replace(".json", ""));

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		ZipUtils.celestial_packs.forEach((K, V) -> {

			Enumeration<? extends ZipEntry> entries = V.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				try {
					if (entry.getName().contains(".json") && !entry.isDirectory()) {
						InputStream stream = V.getInputStream(entry);
						String[] s = entry.getName().split("/");

						if (entry.getName().contains("bodies/moons/"))
							ParseFiles.instance.parseMoons(stream, s[s.length - 1].replace(".json", ""));

						if (entry.getName().contains("bodies/satellites/"))
							ParseFiles.instance.parseSatellites(stream, s[s.length - 1].replace(".json", ""));

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
