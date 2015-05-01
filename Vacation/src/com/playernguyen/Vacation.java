package com.playernguyen;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Vacation extends JavaPlugin {

	private Economy econ;
	
	public void onEnable(){
		ConsoleCommandSender log = getServer().getConsoleSender();
		log.sendMessage(ChatColor.AQUA+"[Vacation] Loading Vacation....");
		log.sendMessage(ChatColor.AQUA+"[Vacation] Check config file and create!");
		if (!setupEconomy() ) {
            log.sendMessage(ChatColor.RED + String.format("[Vacation] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
        	log.sendMessage(ChatColor.AQUA+"[Vacation] Hooking with Vault!");
        }
		
		// Setup
		
		if(!setupPermissionsEx()){
			log.sendMessage(ChatColor.RED+"[Vacation] Hooking with PermissionsEx: FALSE.");
			log.sendMessage(ChatColor.RED+"[Vacation] Please install PermissionsEx to get permission!");
		} else {
			log.sendMessage(ChatColor.AQUA+"[Vacation] Hooking with PermissionsEx: TRUE.");
		}
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		SettingManager.getSetting().setup(this);
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

	private boolean setupPermissionsEx(){
		if(getServer().getPluginManager().getPlugin("PermissionsEx") == null){
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("vacation")
				|| label.equalsIgnoreCase("vac")){
			if(sender.hasPermission("vacation.cmd")
					|| sender.hasPermission("vacation.*")){
				if(args.length == 0){
					sender.sendMessage(ChatColor.YELLOW+"==== [Command Vacation 1/1] ====");
					sender.sendMessage(ChatColor.YELLOW+"/vac to [name]: Vacation to location. (/vac list) to view list!");
					sender.sendMessage(ChatColor.YELLOW+"/vac list: View list.");
					sender.sendMessage(ChatColor.YELLOW+"/vac reload: Reload config.");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("to")){
					if(args.length < 1){
						sender.sendMessage(ChatColor.YELLOW+"Please use /vac to [name].");
						return true;
				}

				if(SettingManager.getSetting().getConfig().getConfigurationSection(args[0])==null){
				}
					sender.sendMessage(ChatColor.YELLOW+"This vacation don't have in server!.");
					return true;
				}
				
				VacationManager.getManager().StartVacation(((Player)sender), args[0]);
				
			}
		}
		return true;
	}	
}