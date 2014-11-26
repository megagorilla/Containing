/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.pathfinding;

import nhl.containing.server.util.XMLFileReader.Container;

/**
 *
 * @author Fré-Meine
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
        this.agvId = agvId;
    }
    
    public void setLoaded(boolean loaded)
    {
    	this.loaded = loaded;
    }
    
    public boolean getLoaded()
    {
    	return loaded;
    }
    
    public void setIsMoving(boolean moving)
    {
    	this.isMoving = moving;
    }
    
    public boolean getIsMoving()
    {
    	return isMoving;
    }
    
    public void setContainer(Container c)
    {
    	this.container = c;
    }
    
    public Container getContainer()
    {
    	return container;
    }
}
