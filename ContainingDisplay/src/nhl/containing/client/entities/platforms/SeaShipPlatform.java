package nhl.containing.client.entities.platforms;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.rails.CraneRails;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Sander
 */
public class SeaShipPlatform extends Platform
{
    //width = 300
        private static StoragePlatform s;
	public SeaShipPlatform()
	{
		SeaSideWay();
		BigShipRails();
		HookEast();
		HookWest();
                agvRoad();
                entry();
                exit();
		ContainingClient.getMyRootNode().attachChild(this);
                
                s = new StoragePlatform();
	}

        /**
         * Road for the AGV's. Here cranes can load op containers from Sea ships
         */
	private void SeaSideWay()
	{
		Box SeaSideWay = new Box(s.WIDTH + 20, 5f, 25);
		Geometry seaSideWayGeom = new Geometry("Box", SeaSideWay);
		seaSideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(seaSideWayGeom);
		seaSideWayGeom.setLocalTranslation(0, -5, 870);
	}

        /**
         * Ramp for the AGVs. From road to seashipplatform
         */
	private void HookEast()
	{
		Box HookEast = new Box(10, 5f, 25);
		Geometry hookEastGeom = new Geometry("Box", HookEast);
		hookEastGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(hookEastGeom);
		hookEastGeom.setLocalTranslation(310, -5f, 820);
	}

        /**
         * Exit for the AGV's. From seashipplatform to road.
         */
	private void HookWest()
	{
		Box HookWest = new Box(10, 5f, 25);
		Geometry hookWestGeom = new Geometry("Box", HookWest);
		hookWestGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(hookWestGeom);
		hookWestGeom.setLocalTranslation(-310, -5f, 820);
	}

        /**
         * 27 units of rails
         */
	private void BigShipRails()
	{
		for (int i = 1; i < 28; i++)
		{
			attachChild(new CraneRails(new Vector3f(-249 + 18 * i, 0f, 880), 0.87f, FastMath.HALF_PI));
		}
	}
        
        private void agvRoad()
        {
            Box Road = new Box(s.WIDTH+13.5f, 0.1f, 3); 
            Geometry RoadGeom = new Geometry("Box", Road);
            RoadGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
            attachChild(RoadGeom);
            RoadGeom.setLocalTranslation(0, 0.1f, 882.5f);
        }
        
        private void entry()
        {
            Box Entry = new Box(3,0.1f,45.25f);
            Geometry EntryGeom = new Geometry("Box", Entry);
            EntryGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
            attachChild(EntryGeom);
            EntryGeom.setLocalTranslation(316.5f, 0.1f, 840.25f);
        }
        
        private void exit()
        {
            Box Exit = new Box(3,0.1f,45.25f);
            Geometry ExitGeom = new Geometry("Box", Exit);
            ExitGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
            attachChild(ExitGeom);
            ExitGeom.setLocalTranslation(-316.5f, 0.1f, 840.25f);
        }
}
