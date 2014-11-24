package nhl.containing.server.pathfinding;

import java.util.HashMap;

public class AGVHandler
{
	public static AGVHandler instance;
	public HashMap<Integer, AGV> agvs = new HashMap<Integer, AGV>();
	
	public AGVHandler()
	{
		instance = this;
	}
	
	public static AGVHandler getInstance()
	{
		return instance;
	}
	
	public void init()
	{
		addAGV(0);
	}
	
	public void addAGV(int id)
	{
		agvs.put(id, new AGV(id, "", "", 0, false));
	}
	
	public AGV getAGV(int id)
	{
		return agvs.get(id);
	}
}
