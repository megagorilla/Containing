/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server;

import com.jme3.math.Vector3f;
import nhl.containing.server.network.BargeCraneData;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.SeaShipCraneData;
import nhl.containing.server.util.XMLFileReader.Container;

/**
 *
 * @author Sander
 */
public class ShipCrane {

    final int ID;
    boolean isSeaShipCrane;
    Vector3f location;
    Container container;
    boolean unloading;
    float timeStartedUnloading = 0f;

    public ShipCrane(Vector3f location, int ID, boolean isSeaShipCrane) {
        this.location = new Vector3f(location);
        this.ID = ID;
        unloading = false;
        this.isSeaShipCrane = isSeaShipCrane;
    }
    
    public Container getContainer() {
    	Container c = container;
    	container = null;
		return c;
	}

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
    
    public float getTimeStartedUnloading() {
		return timeStartedUnloading;
	}
    
    public void setTimeStartedUnloading(float timeStartedUnloading) {
		this.timeStartedUnloading = timeStartedUnloading;
	}

    public void startUnloading(Container container) {
        unloading = true;
        timeStartedUnloading = ContainingServer.timeSinceStart;
        this.container = container;
        if (isSeaShipCrane) {
            SeaShipCraneData data = new SeaShipCraneData(container.getPositie(), ID, this.container.getContainerNumber());
            ConnectionManager.sendCommand(data);
        }else{
            BargeCraneData data = new BargeCraneData(container.getPositie(), ID, this.container.getContainerNumber());
            ConnectionManager.sendCommand(data);
        }
    }

}
