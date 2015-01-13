/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server;

import nhl.containing.server.network.BargeCraneData;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.SeaShipCraneData;
import nhl.containing.server.util.ServerSpatial;
import nhl.containing.server.util.XMLFileReader.Container;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;

/**
 *
 * @author Sander
 */
public class ShipCrane {

    final int ID;
    boolean isSeaShipCrane;
    boolean requestedAGV;
    Vector3f location;
    Container container;
    boolean unloading;

    public ShipCrane(Vector3f location, int ID, boolean isSeaShipCrane) {
        this.location = new Vector3f(location);
        this.ID = ID;
        this.requestedAGV = false;
        this.unloading = false;
        this.isSeaShipCrane = isSeaShipCrane;
    }
    
    public Container getContainer() {
    	Container c = container;
    	container = null;
		return c;
	}

    public void SetUnloading(boolean unloading) {
        this.unloading = unloading;
    }
    
    public Vector3f getLocation() {
		return location;
	}

    public int getID() {
		return ID;
	}
    
    public boolean isUnloading()
    {
    	return this.unloading;
    }
    
    public boolean getRequestedAGV(){
    	return this.requestedAGV;
    }
    
    public void setRequestedAGV(boolean requestedAGV) {
		this.requestedAGV = requestedAGV;
	}

    public void startUnloading(final int agvId, final Container container) {
        unloading = true;
        this.container = container;
        if (isSeaShipCrane)
        {
            SeaShipCraneData data = new SeaShipCraneData(agvId, container.getPositie(), ID, this.container.getContainerNumber());
            ConnectionManager.sendCommand(data);
            MotionPath path = new MotionPath();
            path.addWayPoint(new Vector3f());
            path.addWayPoint(new Vector3f(1, 0, 0));
            ServerSpatial spatial = new ServerSpatial(null, Integer.toString(ID));
            path.addListener(new MotionPathListener(){
				@Override
				public void onWayPointReach(MotionEvent motionEvent, int wayPointIndex) {
					if(motionEvent.getPath().getNbWayPoints() == wayPointIndex + 1)
					{
						unloading = false;
					}
				}    
            });
            MotionEvent event = new MotionEvent(spatial, path);
            event.setInitialDuration(90f);
            event.setSpeed(ContainingServer.getSpeed());
        }
        else
        {
            BargeCraneData data = new BargeCraneData(agvId, container.getPositie(), ID, this.container.getContainerNumber());
            ConnectionManager.sendCommand(data);
        }
    }

}
