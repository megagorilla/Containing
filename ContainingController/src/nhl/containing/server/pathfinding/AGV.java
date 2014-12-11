package nhl.containing.server.pathfinding;

import nhl.containing.server.util.XMLFileReader.Container;

/**
 * Creates AVG's
 * @author Fre-Meine
 *
 */
public class AGV 
{
    public int agvId;
    public boolean loaded;
    public Container container;
    public boolean isMoving;
    public String currentLocation;
    
    public AGV(int agvId)
    {
        this(agvId, "a1");
    }
    
    public AGV(int agvId, String currentLocation)
    {
    	this.agvId = agvId;
    	this.currentLocation = currentLocation;
    }
    
    /**
     * Set true when there is a container on the AGV
     */
    public void setLoaded(boolean loaded)
    {
    	this.loaded = loaded;
    }
    
    /**
     * Returns a boolean if the AGV has a container or not
     * @return true if there is a container on the AGV
     */
    public boolean getLoaded()
    {
    	return loaded;
    }
    
    /**
     * Set true when the AGV is moving
     */
    public void setIsMoving(boolean moving)
    {
    	this.isMoving = moving;
    }
    
    /**
     * Returns a boolean if the AGV is moving or not
     * @return true if the AGV is moving
     */
    public boolean getIsMoving()
    {
    	return isMoving;
    }
    
    /**
     * Set container info 
     */
    public void setContainer(Container c)
    {
    	this.container = c;
    }
    
    /**
     * Get container info
     */
    public Container getContainer()
    {
    	return container;
    }
}
