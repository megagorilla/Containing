package nhl.containing.client.entities.platforms;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Platform;
import nhl.containing.client.entities.rails.CraneRails;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Sander
 */
public class RiverShipPlatform extends Platform
{

	public RiverShipPlatform()
	{
		SideWay();
		HookNorth();
		HookSouth();
		littleShipRails();
		Road();
		Entry();
		Exit();
        ContainingClient.getMyRootNode().attachChild(this);
	}

        /**
         * Road and platform for the river ships, AGV's drive here, and containers will load on them
         */
	private void SideWay()
	{
		Box SideWay = new Box(20, 5, 207.5f);
		Geometry sideWayGeom = new Geometry("Box", SideWay);
		sideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(sideWayGeom);
		sideWayGeom.setLocalTranslation(370, -5.1f, 390+(395/2));
	}

        /**
         * THis is the ramp/entry for the rivership platform
         */
	private void HookNorth()
	{
		Box HookNorth = new Box(15, 5, 10);
		Geometry hookNorthGeom = new Geometry("Box", HookNorth);
		hookNorthGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(hookNorthGeom);
		hookNorthGeom.setLocalTranslation(335, -5.1f, 389.5f);
	}

        /**
         * this is the exit to the road on the rivership platform
         */
	private void HookSouth()
	{
		Box HookSouth = new Box(15, 5, 10);
		Geometry hookSouthGeom = new Geometry("Box", HookSouth);
		hookSouthGeom.setMaterial(new PlainMaterial(ColorRGBA.DarkGray));
		attachChild(hookSouthGeom);
		hookSouthGeom.setLocalTranslation(335, -5.1f, 784.5f);
	}
	
	private void Road()
	{
		Box Road = new Box(3, 0.1f, 200);
		Geometry RoadGeom = new Geometry("Box", Road);
		RoadGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
		attachChild(RoadGeom);
		RoadGeom.setLocalTranslation(360, 0.1f, 390+(395/2));
	}
	
	private void Entry()
	{
		Box Entry = new Box(20, 0.1f, 3);
		Geometry EntryGeom = new Geometry("Box", Entry);
		EntryGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
		attachChild(EntryGeom);
		EntryGeom.setLocalTranslation(340, 0.1f, 390);
	}
	
	private void Exit()
	{
		Box Exit = new Box(20, 0.1f, 3);
		Geometry ExitGeom = new Geometry("Box", Exit);
		ExitGeom.setMaterial(new PlainMaterial(ColorRGBA.White));
		attachChild(ExitGeom);
		ExitGeom.setLocalTranslation(340, 0.1f, 784f);
	}

        /**
         * 20 rails units 
         */
	private void littleShipRails()
	{
		for (int i = 1; i < 21; i++)
		{
			attachChild(new CraneRails(new Vector3f(360, 0.1f, 777.5f - 18 * i), 0.87f, 0f));
		}
	}
}
