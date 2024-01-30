package myid.chiqors.wasteland.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import javax.annotation.Nullable;
import java.util.Locale;

public class ConfigHelper {


	public static int createConfigInt(Configuration config, String category, String name, String comment, int def) {
		Property prop = config.get(category, name, def);
		prop.comment = comment;
		return prop.getInt();
	}

	public static int createConfigInt(Configuration config, String category, String name, int def) {
		Property prop = config.get(category, name, def);
		return prop.getInt();
	}

	public static double createConfigDouble(Configuration config, String category, String name, String comment, double def) {
		Property prop = config.get(category, name, def);
		prop.comment = comment;
		return prop.getDouble();
	}

	public static boolean createConfigBool(Configuration config, String category, String name, boolean def) {
		Property prop = config.get(category, name, def);
		return prop.getBoolean();
	}

	public static String createConfigString(Configuration config, String category, String name, String def) {
		Property prop = config.get(category, name, def);
		return prop.getString();
	}
    public static int[] createConfigIntList(Configuration config, String category, String name, String comment, int[] def){
		Property prop = config.get(category, name, def);
		prop.comment = comment;
		return prop.getIntList();
	}
	public static String[] createConfigStringList(Configuration config, String category, String name, String comment) {
		Property prop = config.get(category, name, new String[] { "PLACEHOLDER" });
		prop.comment = comment;
		return prop.getStringList();
	}

}
