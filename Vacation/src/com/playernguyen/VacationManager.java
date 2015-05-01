package com.playernguyen;



public class VacationManager{

	
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
	
	
	
}
