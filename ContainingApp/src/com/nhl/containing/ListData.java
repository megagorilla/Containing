package com.nhl.containing;

public class ListData {
	public long time;
	public int aantal;
	
	public ListData(long time, String aantal) {
		this.time = time;
		this.aantal = Integer.parseInt(aantal);
	}
	
	public long getTime() {
		return this.time;
	}
	
	public int getAantal() {
		return this.aantal;
	}
}