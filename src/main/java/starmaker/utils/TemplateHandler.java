package starmaker.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Maps;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.world.gen.structure.template.Template;

public class TemplateHandler {
	
	private final Map<String, Template> templates = Maps.<String, Template>newHashMap();
	private final String baseFolder;
	private final DataFixer fixer;
	    
	public TemplateHandler(String p_i47239_1_, DataFixer p_i47239_2_)
    {
        this.baseFolder = p_i47239_1_;
        this.fixer = p_i47239_2_;
    }

	public Template getTemplate(@Nullable MinecraftServer server, ResourceLocation id) {
		Template template = this.get(server, id);

		if (template == null) {
			template = new Template();
			this.templates.put(id.getPath(), template);
		}

		return template;
	}
	 
	@Nullable
    public Template get(@Nullable MinecraftServer server, ResourceLocation templatePath)
    {
        String s = templatePath.getPath();

        if (this.templates.containsKey(s))
        {
            return this.templates.get(s);
        }
        else
        {
            if (server == null)
            {
                //this.readTemplateFromJar(templatePath);
            }
            else
            {
                this.readTemplate(templatePath);
            }

            return this.templates.containsKey(s) ? (Template)this.templates.get(s) : null;
        }
    }

	public boolean readTemplate(ResourceLocation server) {
		String s = server.getPath();
		File file1 = new File(this.baseFolder, s + ".nbt");
		System.out.println(file1.getAbsolutePath() + " | " + file1.exists());
		if (!file1.exists()) {
			 return false;//this.readTemplateFromJar(server);
		} else {
			InputStream inputstream = null;
			boolean flag;

			try {
				inputstream = new FileInputStream(file1);
				this.readTemplateFromStream(s, inputstream);
				return true;
			} catch (Throwable var10) {
				flag = false;
			} finally {
				IOUtils.closeQuietly(inputstream);
			}

			return flag;
		}
	}
	
	private void readTemplateFromStream(String id, InputStream stream) throws IOException {
		NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(stream);

		if (!nbttagcompound.hasKey("DataVersion", 99)) {
			nbttagcompound.setInteger("DataVersion", 500);
		}

		Template template = new Template();
		template.read(this.fixer.process(FixTypes.STRUCTURE, nbttagcompound));
		this.templates.put(id, template);
	}

	public void remove(ResourceLocation templatePath)
    {
        this.templates.remove(templatePath.getPath());
    }
}
