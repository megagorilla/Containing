/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.platforms;

import com.jme3.material.Material;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import nhl.containing.client.entities.cranes.StorageCrane;

/**
 * 
 * @author Sander
 */
public class TruckPlatform extends Platform
{
	Node groundBox = new Node();
	Node parkingBox = new Node();
        Node sideWayBox = new Node();
        Node AGVparkingBox = new Node();

	public TruckPlatform()
	{
		Ground();
		ParkingSpace();
		ContainingClient.getMyRootNode().attachChild(this);
                SideWay();
                AGVparking();
	}

	private void Ground()
	{
		Box Ground = new Box(50, 5f, 280);
		Geometry groundGeom = new Geometry("Box", Ground);
		groundGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		groundBox.attachChild(groundGeom);
		groundGeom.setLocalTranslation(370, -5, -515);
		ContainingClient.getMyRootNode().attachChild(groundBox);
	}

	private void ParkingSpace()
	{
		for (int i = 0; i < 20; i++)
		{
			Box ParkingSpace = new Box(10, 0.1f, 5);
			Geometry parkingGeom = new Geometry("Box", ParkingSpace);
			parkingGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
			parkingBox.attachChild(parkingGeom);
			parkingGeom.setLocalTranslation(380, 0.01f, -750 + 25 * i);

		}
		attachChild(parkingBox);
	}
        
        private void SideWay()
        {
            for(int i = 0; i <2; i++)
            {
                Box SideWay = new Box(15,0.1f,3);
                Geometry SideWayGeom = new Geometry("Box", SideWay);
                SideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
                sideWayBox.attachChild(SideWayGeom);
                SideWayGeom.setLocalTranslation(335, 0, -780+530*i);
            }
            attachChild(sideWayBox);
        }
        
        private void AGVparking()
        {
            Box AGVParking = new Box(3,0.1f,268);
            Geometry AGVParkingGeom = new Geometry("Box", AGVParking);
            AGVParkingGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
            AGVparkingBox.attachChild(AGVParkingGeom);
            AGVParkingGeom.setLocalTranslation(353f, 0, -515);
            attachChild(AGVparkingBox);
        }
        
      
}
