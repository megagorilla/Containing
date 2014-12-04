package nhl.containing.server.pathfinding;

import java.util.HashMap;

import nhl.containing.server.util.XMLFileReader.Container;

/**
 * Controller for AGV's, by creating Hashmap with all the AGV's in it
 * @author Fré-Meine
 *
 */
public class AGVHandler
{
	private static AGVHandler instance;
	public HashMap<Integer, AGV> agvs = new HashMap<Integer, AGV>();
	
	public AGVHandler()
	{
		instance = this;
	}
	
	/**
	 * Retrieve the instance of the object it is called upon
	 */
	public static AGVHandler getInstance()
	{
		return instance;
	}
	
	public void init()
	{
		for(int i = 0; i < 100; i++)
			addAGV(i);
	}
	
	/**
	 * Adds a new AGV to the Hashmap
	 */
	public void addAGV(int id)
	{
		agvs.put(id, new AGV(id));
	}
	
	/**
	 * Updates the information about the AGV in the Hashmap
	 */
	public void setAGV(int id, AGV agv)
	{
		agvs.put(id, agv);
	}
	
	/**
	 * Get the information about an AGV from the Hashmap by requesting it through it's ID
	 */
	public AGV getAGV(int id)
	{
		return agvs.get(id);
	}
	
	/**
	 * Set parameters for an AGV when a container is places upon it
	 */
	public void loadAGV(int id, Container c)
	{
		AGV agv = getAGV(id);
		agv.setLoaded(true);
		agv.setContainer(c);
		setAGV(id, agv);
	}
	
	/**
	 * Returns the first idle AGV from the Hashmap
	 * @return
	 */
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
