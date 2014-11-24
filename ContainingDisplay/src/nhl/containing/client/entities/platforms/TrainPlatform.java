/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.platforms;

import java.util.ArrayList;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.rails.CraneRails;
import nhl.containing.client.entities.rails.TrainRails;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Sander
 */
public class TrainPlatform extends Platform{

	ArrayList<TrainRails> TrainRailsLst = new ArrayList<TrainRails>();
	ArrayList<CraneRails> CraneRailsLst = new ArrayList<CraneRails>();

	Node groundBox = new Node();
	Node parkingBox = new Node();

	public TrainPlatform()
	{
		super();
		for (int i = 0; i < 243; i++)
		{
			TrainRailsLst.add(new TrainRails(new Vector3f(-725, 0f, 1400 - 11.25f * i), 0f));
			attachChild(TrainRailsLst.get(TrainRailsLst.size() - 1));
		}

		Ground();
		ParkingSpace();
		CraneRails();
		ContainingClient.getMyRootNode().attachChild(this);
                ContainingClient.getMyRootNode().attachChild(this);
	}

	private void Ground()
	{
		Box Ground = new Box(20, 5f, 1550);
		Geometry groundGeom = new Geometry("Box", Ground);
		groundGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		groundGeom.setLocalTranslation(-722, -5, 0);
		attachChild(groundGeom);
	}

	private void CraneRails()
	{
		for (int i = 0; i < 150; i++)
		{
			CraneRailsLst.add(new CraneRails(new Vector3f(-717, 0f, 1400 - 18.3f * i), 1f, 0f));
			attachChild(CraneRailsLst.get(CraneRailsLst.size() - 1));
		}
	}

	private void ParkingSpace()
	{
		Box ParkingSpace = new Box(3, 0.1f, 1500);
		Geometry parkingGeom = new Geometry("Box", ParkingSpace);
		parkingGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
		parkingGeom.setLocalTranslation(-717, 0.01f, 0);
		attachChild(parkingGeom);
		ContainingClient.getMyRootNode().attachChild(this);
        
        public void Sideway()
        {
            for(int i = 0; i <2; i++)
            {
            Box SideWay = new Box(6,0.1f,3);
            Geometry sideWayGeom = new Geometry("Box", SideWay);
            sideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.Gray));
            sideWayGeom.setLocalTranslation(-708, 0.01f, 1497-2994*i);
            attachChild(sideWayGeom);
            }
        }
}
