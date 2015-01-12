package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import nhl.containing.server.ContainingServer;
import nhl.containing.server.entities.Train;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.TrainCraneData;
import nhl.containing.server.network.TrainSpawnData;
import nhl.containing.server.network.TruckCraneData;
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

/**
 * Server-side handler for the train platform
 * @author rogier
 */
public class TrainPlatformHandler {
	private static final float baseX = -334f,
						baseY = 0f,
						baseZ = 725f,
						containerOffset = 25f;
	private static final String location = "d2";
	private static int currTrainID = 0;
	private static HashMap<Integer,TrainLocation> locations = new HashMap<Integer,TrainLocation>();

	private static boolean trainStationed = false;
	private static Stack<Container> containersOnPlatform1, containersOnPlatform2, containersOnPlatform3, containersOnPlatform4;

	public TrainPlatformHandler() {
		//spawnTrain(new ArrayList<Container>());
	}
	
	public static void init() {
		for (int i = 0; i < 30; i++) {
			locations.put(i, new TrainLocation(i, new Vector3f(baseX,baseY,baseZ+containerOffset*i)));
		}
	}
	
	public static TrainLocation getFirstFreeLocation() {
		for (TrainLocation l : locations.values()) {
			if (l.needsAGV) return l;
		}
		return locations.get(0);
	}
	
	public static void handleAGV(AGV agv) {
		TrainLocation l = getFirstFreeLocation();
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f(baseX, baseY, baseZ));
		list.add(new Vector3f(baseX, baseY, l.location.z));
		list.add(new Vector3f(l.location));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, "trainLocation_" + l.id);
		l.needsAGV = false;
		locations.put(l.id, l);
	}

	public static void spawnTrain (ArrayList<Container> containers) {
		TrainSpawnData spawnData = new TrainSpawnData(++currTrainID, containers.stream().mapToInt(c -> c.getContainerNumber()).toArray(), false);
		ConnectionManager.sendCommand(spawnData);
		splitContainers(containers);
		trainStationed = true;
		for (int i = 0; i < containers.size(); i++) {
			TrainLocation l = locations.get(i);
			l.c = containers.get(i);
			l.needsAGV = true;
			ControlHandler.getInstance().requestAGVToTrain(i);
		}
	}
	
	public static void despawnTrain () {
		TrainSpawnData spawnData = new TrainSpawnData(currTrainID-1,new int[0],true);
		ConnectionManager.sendCommand(spawnData);
		containersOnPlatform1 = null;
		containersOnPlatform2 = null;
		containersOnPlatform3 = null;
		containersOnPlatform4 = null;
		trainStationed = false;
		for (TrainLocation l : locations.values()) {
			l.needsAGV = false;
			locations.put(l.id, l);
		}
	}
	
	public static TrainLocation containerLocation(Container c) {
		for (TrainLocation l : locations.values()) {
			if (l.c == c) {
				return l;
			}
		}
		return locations.get(0);
	}
	
	public static void unloadContainer(int agvId, int craneId, Container c) {
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(new Vector3f());
		list.add(new Vector3f(1, 0, 0));
		ConnectionManager.sendCommand(new TrainCraneData(agvId, craneId, c.getContainerNumber()));
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		AGV agv = AGVHandler.getInstance().getAGV(agvId);
		agv.setContainer(locations.get(craneId).c);
		AGVHandler.getInstance().setAGV(agv.agvId, agv);
		ServerSpatial spatial = new ServerSpatial(AGVHandler.getInstance().getAGV(agvId), "truckLocation_" + String.valueOf(craneId) + "_loaded");
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(30f);
        motionControl.setSpeed(ContainingServer.getSpeed());
        motionControl.play();
	}
	
	public static void splitContainers(List<Container> containers) {
		int length = Math.floorDiv(containers.size(), 4);
		int rest = Math.floorMod(containers.size(), 4);
		containersOnPlatform1 = (Stack<Container>) containers.subList(0, length);
		containersOnPlatform2 = (Stack<Container>) containers.subList(length, length*2);
		containersOnPlatform3 = (Stack<Container>) containers.subList(length*2, length*3);
		containersOnPlatform4 = (Stack<Container>) containers.subList(length*3, length*4+rest);
	}
	
	
	
	public static void update() {
		
	}
	
	public static int getContainerCount() {
		return (containersOnPlatform1.size() + 
				containersOnPlatform2.size() + 
				containersOnPlatform3.size() + 
				containersOnPlatform4.size());
	}
	
	/**
	 * Location next to the train
	 * @author rogier
	 */
	public static class TrainLocation {
		public int id;
		public Vector3f location;
		public boolean needsAGV;
		public Container c;
		
		public TrainLocation (int id, Vector3f location) {
			this.id = id;
			this.location = location;
			this.needsAGV = false;
		}
	}
}