/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.pathfinding;

/**
 * 
 * @author Fré-Meine
 */
public class AGV
{

	@SuppressWarnings("unused")
	private int agvId;

	private String currentLocation;
	private String destination;
	private int movementSpeed;

	@SuppressWarnings("unused")
	private boolean loaded;

	// container
	// private int simulationLocationX;
	// private final int simulationLocationY;
	// private int simulationLocationZ;
	public AGV(int agvId, String currentLocation, String destination, int movementSpeed, boolean loaded)
	{
		this.agvId = agvId;
		this.currentLocation = currentLocation;
		this.destination = destination;
		this.movementSpeed = movementSpeed;
		this.loaded = loaded;
	}

	public String getCurrentLocation()
	{
		return currentLocation;
	}

	public String getDestination()
	{
		return destination;
	}

	public int getMovementSpeed()
	{
		return movementSpeed;
	}

	public void setCurrentLocation(String currentLocation)
	{
		this.currentLocation = currentLocation;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public void setMovementSpeed(int movementSpeed)
	{
		this.movementSpeed = movementSpeed;
	}

	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}
}
