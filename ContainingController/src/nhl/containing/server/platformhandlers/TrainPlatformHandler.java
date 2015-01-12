package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.TrainCraneData;
import nhl.containing.server.network.TrainSpawnData;
import nhl.containing.server.pathfinding.AGV;
import nhl.containing.server.pathfinding.AGVHandler;
import nhl.containing.server.pathfinding.CMotionPathListener;
import nhl.containing.server.util.ControlHandler;
import nhl.containing.server.util.ServerSpatial;
import nhl.containing.server.util.XMLFileReader.Container;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class TrainPlatformHandler {
	private static final float baseX = -334f,
						baseY = 0f,
						baseZ = 725f,
						containerOffset = 25f;

	private static boolean trainStationed = false;
	private static ArrayList<Container> containersOnPlatform;
	private static ArrayList<Container> containersOnPlatform1, containersOnPlatform2, containersOnPlatform3, containersOnPlatform4;

	public TrainPlatformHandler() {
		spawnTrain(new ArrayList<Container>());
	}

	public static void spawnTrain (ArrayList<Container> containers) {
		TrainSpawnData spawnData = new TrainSpawnData(0, containers.stream().mapToInt(c -> c.getContainerNumber()).toArray(), false);
		ConnectionManager.sendCommand(spawnData);
		containersOnPlatform = containers;
		trainStationed = true;
	}
	
	public static void despawnTrain () {
		TrainSpawnData spawnData = new TrainSpawnData(0,new int[0],true);
		ConnectionManager.sendCommand(spawnData);
		containersOnPlatform = null;
		trainStationed = false;
	}

	public static void unloadContainer(Container c) {
		ControlHandler.getInstance().requestAGVToTrain(c.getContainerNumber());
	}
	
	public static void splitContainers() {
		int length = Math.floorDiv(getContainerCount(), 4);
		int rest = Math.floorMod(getContainerCount(), 4);
		containersOnPlatform1 = (ArrayList<Container>) containersOnPlatform.subList(0, length);
		containersOnPlatform2 = (ArrayList<Container>) containersOnPlatform.subList(length, length*2);
		containersOnPlatform3 = (ArrayList<Container>) containersOnPlatform.subList(length*2, length*3);
		containersOnPlatform4 = (ArrayList<Container>) containersOnPlatform.subList(length*3, length*4+rest);
	}
	
	public static void update() {
		if (trainStationed) {
			unloadContainer(containersOnPlatform.get(containersOnPlatform.size()-1));
		}
	}
	
	public static int getContainerCount() {
		return containersOnPlatform.size();
	}
}