package starmaker.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;
import starmaker.CoreConfig;

public class StarMakerAssets implements IResourcePack {

	boolean debug = CoreConfig.debugLogging;

	@Override
	public InputStream getInputStream(ResourceLocation resourceLocation) throws IOException {
		if (!resourceExists(resourceLocation)) {
			return null;
		} else {

			File file = new File(new File(Minecraft.getMinecraft().mcDataDir, "config/StarMaker/resources/" + resourceLocation.getResourceDomain()),
					resourceLocation.getResourcePath());
			return new FileInputStream(file);
		}
	}

	@Override
	public boolean resourceExists(ResourceLocation rl) {
		File fileRequested = new File(
				new File(Minecraft.getMinecraft().mcDataDir, "config/StarMaker/resources/" + rl.getResourceDomain()),
				rl.getResourcePath());

		return fileRequested.isFile();
	}

	@Override
	public Set<String> getResourceDomains() {
		File folder = new File(Minecraft.getMinecraft().mcDataDir, "config/StarMaker/resources");
		if (!folder.exists()) {
			folder.mkdir();
		}
		HashSet<String> folders = new HashSet<String>();
		return folders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMetadataSection getPackMetadata(MetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
		return null;
	}

	@Override
	public BufferedImage getPackImage() throws IOException {
		return null;
	}

	@Override
	public String getPackName() {
		return "StarMakerAssets";
	}
}
