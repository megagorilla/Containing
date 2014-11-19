/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author snyx
 */
public class Data {
    public String name;
    public Vector3f pos;
    public Quaternion rot;

    Data(String name, Vector3f location, Quaternion rotation) {
        this.name = name;
        this.pos = location;
        this.rot = rotation;
    }
}
