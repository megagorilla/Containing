/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server;

import com.jme3.math.Vector3f;
import nhl.containing.server.network.ConnectionManager;
import nhl.containing.server.network.SeaShipCraneData;
import nhl.containing.server.util.XMLFileReader.Container;

/**
 *
 * @author Sander
 */
public class ShipCrane {
    final int ID;
    Vector3f location;
    Container container;
    boolean Unloading;
    public ShipCrane(Vector3f location, int ID) {
         this.location= new Vector3f(location);
         this.ID = ID;
         Unloading = false;
    }

    public boolean isUnloading() {
        return Unloading;
    }

    public void setUnloading(boolean Unloading) {
        this.Unloading = Unloading;
    }
    
    public void startUnloading(Container container){
        Unloading = true;
        this.container = container;
        SeaShipCraneData data = new SeaShipCraneData(container.getPositie(), ID, this.container.getContainerNumber());
        ConnectionManager.sendCommand(data);
    }
    
}
