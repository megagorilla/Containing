package nhl.containing.server.platformhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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

/**
 * Server-side handler for the train platform
 * @author rogier
 */
public class TrainPlatformHandler {
	private static final float 	baseX = -326.5f,
								baseY = 0f,
								baseZ = 711.5f,
								containerOffset = 18.4f;
	private static int currTrainID = 0;
	public static HashMap<Integer,TrainLocation> locations = new HashMap<Integer,TrainLocation>();

	public static boolean trainStationed = false;
	public static Stack<Container> containersOnPlatform1 = new Stack<Container>(),
									containersOnPlatform2 = new Stack<Container>(),
									containersOnPlatform3 = new Stack<Container>(),
									containersOnPlatform4 = new Stack<Container>();

	public TrainPlatformHandler() {
		init();
	}
	
	public static void init() {
		for (int i = 0; i < 30; i++) {
			locations.put(i, new TrainLocation(i, new Vector3f(baseX,baseY,baseZ-containerOffset*i)));
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
		list.add(l.location);
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
		setCraneNums();
	}
	
	public static void despawnTrain () {
		TrainSpawnData spawnData = new TrainSpawnData(currTrainID-1,new int[0],true);
		ConnectionManager.sendCommand(spawnData);
		containersOnPlatform1 = new Stack<>();
		containersOnPlatform2 = new Stack<>();
		containersOnPlatform3 = new Stack<>();
		containersOnPlatform4 = new Stack<>();
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
		ConnectionManager.sendCommand(new TrainCraneData(agvId, craneId, (int)c.getPositie().x, containerLocation(c).id));
		MotionPath path = new MotionPath();
		for(Vector3f v : list)
			path.addWayPoint(v);
		path.setCurveTension(0.0f);
		path.addListener(new CMotionPathListener());
		AGV agv = AGVHandler.getInstance().getAGV(agvId);
		agv.setContainer(c);
		AGVHandler.getInstance().setAGV(agv.agvId, agv);
		ServerSpatial spatial = new ServerSpatial(AGVHandler.getInstance().getAGV(agvId), "trainLocation_" + containerLocation(c).id + "_loaded");
        ContainingServer.getRoot().attachChild(spatial);

		MotionEvent motionControl = new MotionEvent(spatial, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setRotation(new Quaternion().fromAngleNormalAxis(0, Vector3f.UNIT_Y));
        motionControl.setInitialDuration(30f);
        motionControl.setSpeed(ContainingServer.getSpeed());
        motionControl.play();
	}
	
	public static void returnAGVToStorage (AGV agv, int locID) {
		TrainLocation location = locations.get(locID);
		List<Vector3f> list = new ArrayList<Vector3f>();
		list.add(location.location);
		list.add(new Vector3f(-325f,0f,-748f));
		list.add(new Vector3f(-312.5f,0f,-748f));
		list.add(new Vector3f(-312.5f,0f,-790f));
		list.add(new Vector3f(315f,0f,-790f));
		ControlHandler.getInstance().sendAGV(agv.agvId, list, "a3");
	}
	
	public static void setCraneNums() {
		for(int i = 0; i < locations.size(); i++) {
			TrainLocation l = locations.get(i);
			if(l.id < containersOnPlatform1.size()) {
				l.craneNum = 0;
			}
			else if (l.id < containersOnPlatform1.size() + containersOnPlatform2.size()) {
				l.craneNum = 1;
			}
			else if (l.id < containersOnPlatform1.size() + containersOnPlatform2.size() + containersOnPlatform3.size()) {
				l.craneNum = 2;
			}
			else {
				l.craneNum = 3;
			}
			locations.put(i, l);
		}
	}
	
	public static void splitContainers(List<Container> containers) {
		int length = Math.floorDiv(containers.size(), 4);
		int rest = Math.floorMod(containers.size(), 4);
		containersOnPlatform1.addAll(containers.subList(0, length));
		containersOnPlatform2.addAll(containers.subList(length, length*2));
		containersOnPlatform3.addAll(containers.subList(length*2, length*3));
		containersOnPlatform4.addAll(containers.subList(length*3, length*4+rest));
		return;
	}
	
	
	
	public static void update() {
		if(getContainerCount() == 0 && trainStationed) despawnTrain();
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
		public int craneNum;
		
		public TrainLocation (int id, Vector3f location) {
			this.id = id;
			this.location = location;
			this.needsAGV = false;
		}
	}
}