package nhl.containing.client.entities.platforms;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * This is the truckplatform
 * @author Yannick
 */
public class TruckPlatform extends Platform
{
	Node groundBox = new Node();
	Node parkingBox = new Node();
    Node sideWayBox = new Node();
    Node AGVparkingBox = new Node();
    private static final int ParkingSpaceAmount = 20;

	public TruckPlatform()
	{
		Ground();
		ParkingSpace();
		ContainingClient.getMyRootNode().attachChild(this);
        SideWay();
        AGVparking();
	}
        
    /**
     * This is the ground box, with width 100, height 10 & length 560
     * Color dark gray
     */
        
	private void Ground()
	{
		Box Ground = new Box(50, 5f, 280);
		Geometry groundGeom = new Geometry("Box", Ground);
		groundGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		groundBox.attachChild(groundGeom);
		groundGeom.setLocalTranslation(370, -5, -515);
		ContainingClient.getMyRootNode().attachChild(groundBox);
	}
        
    /**
     * here i make the parking spaces, 20, significant tot the amount of trucks.
     */

	private void ParkingSpace()
	{
		for (int i = 0; i < ParkingSpaceAmount; i++)
		{
			Box ParkingSpace = new Box(10, 0.1f, 5);
			Geometry parkingGeom = new Geometry("Box", ParkingSpace);
			parkingGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
			parkingBox.attachChild(parkingGeom);
			parkingGeom.setLocalTranslation(380, 0.01f, -750 + 25 * i);

		}
		attachChild(parkingBox);
	}
        
    /**
     * updriving roads, sideways next to the AGVParking
     */
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
    
    /**
     * road next to the parking space
     */
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
