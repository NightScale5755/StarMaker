package starmaker.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.filefilter.DirectoryFileFilter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starmaker.CoreConfig;
import starmaker.StarMaker;

@SideOnly(Side.CLIENT)
public class StarMakerAssets implements IResourcePack {

	boolean debug = CoreConfig.debugLogging;
	
	private static String loc = "config/StarMaker/resources";

	@Override
	public InputStream getInputStream(ResourceLocation rl) throws IOException {
		if (!resourceExists(rl)) {
			return null;
		} else {

			File file = new File(new File(Minecraft.getMinecraft().gameDir, loc + "/" + rl.getNamespace()),rl.getPath());
			String realFileName = file.getCanonicalFile().getName();
			if (!realFileName.equals(file.getName()))
			{
				StarMaker.LOG.debug("[StarResources] Resource Location " + rl.toString() + " only matches the file " + realFileName + " : We are running a Non-Case Sensitive OS Environment(regarding file name cases");
			}
			return new FileInputStream(file);
		}
	}

	@Override
	public boolean resourceExists(ResourceLocation rl) {
		File fileRequested = new File(new File(Minecraft.getMinecraft().gameDir, loc + "/" + rl.getNamespace()),rl.getPath());
		if (debug && !fileRequested.isFile())
		{
			StarMaker.LOG.debug("[StarResources] Looking For: " + rl.toString() + " But Cannot Find At: " + fileRequested.getAbsolutePath());
		}

		return fileRequested.isFile();
	}

	@Override
	public Set<String> getResourceDomains() {
		File folder = new File(Minecraft.getMinecraft().gameDir, loc);
		if (!folder.exists()) {
			folder.mkdir();
		}
		HashSet<String> folders = new HashSet<String>();
		StarMaker.LOG.debug("[StarResources] Domains: ");
		File[] resourceDomains = folder.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		
		if(resourceDomains == null) 
			throw new RuntimeException("Folder '" + loc + "' is empty! Please add some config files or delete 'StarMaker' folder from configs.");
		
		for (File resourceFolder : resourceDomains)
		{
			StarMaker.LOG.debug("[StarResources]  - " + resourceFolder.getName() + " | " + resourceFolder.getAbsolutePath());
			folders.add(resourceFolder.getName());
		}

		return folders;
	}

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
