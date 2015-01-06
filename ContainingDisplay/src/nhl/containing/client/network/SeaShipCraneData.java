/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.network;

import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Sander
 */
@Serializable
public class SeaShipCraneData extends AbstractMessage {
    int craneID;
    int containerID;
    Vector3f location;
    float dayLength;
    public SeaShipCraneData(){}
    
}
