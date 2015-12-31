package ru.fewizz.idextender;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class IEConfig {
	static Configuration config;
	public static boolean removeInvalidBlocks = false;
	public static boolean oldWorldsSupport = true;
	public static boolean extendDataWatcher = false;
	
	public static void init(File file){
		File newFile;
		if(file.getPath().contains("bin"))
			newFile = new File(file.getParent() + "/eclipse/config", "NEID.cfg");
		else
			newFile = new File(file.getParentFile().getParent() + "/config", "NEID.cfg");
		config = new Configuration(newFile);
		
		removeInvalidBlocks = config.getBoolean("RemoveInvalidBlocks", "NEID", false, "Remove invalid (corrupted) blocks from the game.");
		oldWorldsSupport = config.getBoolean("OldWorldsSupport", "NEID", true, "Enable support for pre-NEID worlds (worlds which were created without NEID).");
		extendDataWatcher = config.getBoolean("ExtendDataWatcher", "NEID", false, "Extend DataWatcher IDs. Vanilla limit is 31, new limit is 63.");
		
		config.save();
	}
}
