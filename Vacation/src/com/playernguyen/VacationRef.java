package com.playernguyen;

import java.util.ArrayList;

import org.bukkit.Location;

public class VacationRef {

	public static ArrayList<VacationRef> vacationObj = new ArrayList<VacationRef>();
	
	private Location loc;
	
	private int money ;
	
	private String name;
	
	public VacationRef(String name, int money, Location loc){
		this.loc = loc;
		this.money = money;
		this.name = name;
		// Equals and add
		vacationObj.add(this);
		SettingManager.getSetting().getConfig().set("vacation."+name+".Nickname", name);
		SettingManager.getSetting().getConfig().set("vacation."+name+".loc.world", loc.getWorld().getName());
		SettingManager.getSetting().getConfig().set("vacation."+name+".loc.x", loc.getX());
		SettingManager.getSetting().getConfig().set("vacation."+name+".loc.y", loc.getY());
		SettingManager.getSetting().getConfig().set("vacation."+name+".loc.z", loc.getZ());
		SettingManager.getSetting().getConfig().set("vacation."+name+".money", money);
		SettingManager.getSetting().saveConfig();
	}
	
	public String getNickName(){
		return name;
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public int getMoneyLevel(){
		return money;
	}
	
}
