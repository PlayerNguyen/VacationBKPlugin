package com.playernguyen;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingManager {

	
	private File file;
	
	private FileConfiguration fc;
	
	private SettingManager(){ }
	
	private static SettingManager sm = new SettingManager();
	
	public static SettingManager getSetting(){
		return sm;
	}
	
	
	public void setup(Plugin p){
		file = new File(p.getDataFolder(), "vacation.yml");
		fc  = p.getConfig();
		try{fc.save(file);}
		catch(Exception e){e.printStackTrace();}
	}
	
	public FileConfiguration getConfig(){
		return fc;
	}
	
	public void saveConfig(){
		try{fc.save(file);}
		catch(Exception e){e.printStackTrace();}
	}
	
	public PluginDescriptionFile getDesc(Plugin p) {
        return p.getDescription();
    }
	
}
