package com.nhl.containing;

public class ListData {
	public long time;
	public int aantal;
	
	/**
	 * Constructor
	 * @param time The current Time in UNIX-format
	 * @param aantal The current number of Containers
	 */
	public ListData(long time, String aantal) {
		this.time = time;
		this.aantal = Integer.parseInt(aantal);
	}
	
	/**
	 * Gets the time of the current object
	 * @return the current time
	 */
	public long getTime() {
		return this.time;
	}
	
	/**
	 * Gets the current number of containers
	 * @return the current number of containers
	 */
	public int getAantal() {
		return this.aantal;
	}
}