package nhl.containing.server.util;

import nhl.containing.server.pathfinding.AGV;

import com.jme3.scene.Node;

public class ServerSpatial extends Node
{
	public AGV agv;
	public String destination;
	
	public ServerSpatial(AGV agv, String destination)
	{
		super();
		this.agv = agv;
		this.destination = destination;
	}
}
