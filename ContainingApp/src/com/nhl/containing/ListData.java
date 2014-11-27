package com.nhl.containing;

public class ListData {
	public int time;
	public int aantal;
	
	public ListData(int time, String type, int aantal) {
		this.time = time;
		this.aantal = aantal;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public int getAantal() {
		return this.aantal;
	}
}