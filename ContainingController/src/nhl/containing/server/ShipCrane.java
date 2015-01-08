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

    public ShipCrane(Vector3f location, int ID, boolean isSeaShipCrane) {
        this.location = new Vector3f(location);
        this.ID = ID;
        unloading = false;
        this.isSeaShipCrane = isSeaShipCrane;
    }

    public boolean GetUnloading() {
        return unloading;
    }

    public void SetUnloading(boolean unloading) {
        this.unloading = unloading;
    }

    public void startUnloading(Container container) {
        unloading = true;
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
