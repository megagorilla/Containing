package nhl.containing.server.pathfinding;

import java.util.HashMap;

import nhl.containing.server.util.XMLFileReader.Container;

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
		for(int i = 0; i < 100; i++)
			addAGV(i);
	}
	
	public void addAGV(int id)
	{
		agvs.put(id, new AGV(id));
	}
	
	public void setAGV(int id, AGV agv)
	{
		agvs.put(id, agv);
	}
	
	public AGV getAGV(int id)
	{
		return agvs.get(id);
	}
	
	public void loadAGV(int id, Container c)
	{
		AGV agv = getAGV(id);
		agv.setLoaded(true);
		agv.setContainer(c);
		setAGV(id, agv);
	}
	
	public AGV getFreeAGV()
	{	
		AGV agv = null;
		
		for(AGV agv1 : agvs.values())
		{
			if(!agv1.getIsMoving() && !agv1.getLoaded())
			{
				agv = agv1;
				break;
			}
		}
		agv.setIsMoving(true);
		setAGV(agv.agvId, agv);
		return agv;
	}
}
