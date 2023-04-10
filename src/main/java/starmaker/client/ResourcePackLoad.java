package starmaker.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import starmaker.utils.zip.ZipUtils;

public class ResourcePackLoad {

	public static void defineResourcePack(List<IResourcePack> resourceList)
	{
		
		if (FMLLaunchHandler.side().isClient())
		{			
			for(Map.Entry<File, ZipFile> zips : ZipUtils.celestial_packs.entrySet())
			{
				FileResourcePack pack = new FileResourcePack(zips.getKey()) {
					@Override
					public IMetadataSection getPackMetadata(MetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
						return null;
					}

					@Override
					public BufferedImage getPackImage() throws IOException {
						return null;
					}
				};
				
				boolean canAdd = true;
				for(IResourcePack packs : resourceList)
					if(packs.getPackName().equals(pack.getPackName()))
						canAdd = false;
				
				if(canAdd)
					resourceList.add(pack);
			}
			
		}

	}
}
