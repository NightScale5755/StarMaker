package starmaker;

import net.minecraftforge.common.config.Config;

@Config(name = "/StarMaker/starmaker", modid = StarMaker.MODID)
@Config.LangKey("starmaker.config.title")
public class CoreConfig
{

	@Config.Comment("Enable Debug log output. NOTE: Can cause large log file sizes")
	public static boolean debugLogging = true;

	@Config.Comment("Defines the folder name StarMaker will search for assets")
	public static String resourceDomain = "starsources";

}
