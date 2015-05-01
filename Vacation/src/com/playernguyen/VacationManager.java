package com.playernguyen;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;



public class VacationManager{

	private Economy econ;
	
	private static VacationManager vm = new VacationManager();
	
	public static VacationManager getManager(){
		return vm;
	}
	
	public VacationRef getVacation(String name){
		for (VacationRef v : VacationRef.vacationObj){
			if(v.getNickName() == name){
				return v;
			}
		}
		return null;
	}
	
	public Location getLocPlayer(String name){
		World w = Bukkit.getServer().getWorld(SettingManager.getSetting().getConfig().getString("vacation."+name+".loc.world"));
		double x = SettingManager.getSetting().getConfig().getDouble("vacation."+name+".loc.x");
		double y = SettingManager.getSetting().getConfig().getDouble("vacation."+name+".loc.y");
		double z = SettingManager.getSetting().getConfig().getDouble("vacation."+name+".loc.z");
		return new Location(w, x, y, z);
	}
	
	public int getMoney(String name){
		return SettingManager.getSetting().getConfig().getInt("vacation."+name+".money");
	}
	
	public void StartVacation(Player p, String name){
		EconomyResponse r = econ.depositPlayer(p, getMoney(name));
		if(r.transactionSuccess()){
			p.teleport(getLocPlayer(name));
			p.sendMessage(ChatColor.GREEN+"You're vacation to "+name);
		} else {
			p.sendMessage(ChatColor.RED+"Error: You don't have money to vacation.");
		}
	}
	
}
