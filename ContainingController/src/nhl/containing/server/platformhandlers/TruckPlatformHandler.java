package nhl.containing.server.platformhandlers;

import nhl.containing.server.pathfinding.AGV;

public class TruckPlatformHandler
{
	private static TruckPlatformHandler instance;
	
	public TruckPlatformHandler()
	{
		instance = this;
	}
	
	public static TruckPlatformHandler getInstance()
	{
		return instance;
	}
	
	public void handleAGV(AGV agv)
	{
		
	}
}
