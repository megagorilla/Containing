/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.util.List;

/**
 *
 * @author Sander
 */
@Serializable
public class SeaShipSpawnData extends AbstractMessage {
    public int seaShipID;
    public List<ContainerData> containers;
    public boolean shouldDespawn;
    
    public SeaShipSpawnData(){}
    
    public SeaShipSpawnData(int seaShipID, List<ContainerData> containers, boolean shouldDespawn){
        this.seaShipID = seaShipID;
        this.containers = containers;
        this.shouldDespawn = shouldDespawn;
    }
}
