package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.util.ControlHandler;

import com.jme3.math.Vector3f;

public class TrainPlatformHandler 
{
	private final float baseX = 0, baseY = 0, baseZ = 0; // TODO: set proper base coords
	private HashMap<Vector3f,Boolean> positions = new HashMap<Vector3f,Boolean>();
	private static TrainPlatformHandler instance;
	
	public TrainPlatformHandler ()
	{
		instance = this;
		for (int i = 0; i < 30; i++)
		{
			positions.put(new Vector3f(baseX,baseY,baseZ+(i*25)),false);
		}
	}
	
	public static TrainPlatformHandler getInstance ()
	{
		return instance;
	}
	
	public void handleAGV(AGV agv)
	{
		Vector3f location = getPosition();
		ArrayList<Vector3f> list = new ArrayList<>();
		list.add(location);
//		ControlHandler.getInstance().sendAGV(agv.agvId, list);
		positions.replace(location, false);
	}
	
	private Vector3f getPosition()
	{
		for (Map.Entry<Vector3f, Boolean> e : positions.entrySet())
		{
			if (e.getValue())
				return e.getKey();
		}
		return null;
	}

	public Map<Vector3f,Boolean> getPositions()
	{
		return positions;
	}
}
