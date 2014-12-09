/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.cranes;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Yannick
 * In deze klasse wordt de beweging van de truckcrane gemaakt.
 */
public class TruckCrane extends Crane {
    private MotionPath grabContainer;
    private MotionPath moveToTruck;
    private MotionPath dropContainer;
    private MotionPath moveBack;
    private MotionEvent motionControl;
    private MotionEvent motionControl2;
    private MotionEvent motionControl3;
    private MotionEvent motionControl4;
    int truckCraneNR;
    
    private MotionPath craneToTruck;
    private MotionEvent truckToCraneMotion;

    /**
     * creates the TruckCrane and loads all the models
     * @param truckCraneNR the number of this truck
     */
    public TruckCrane(int truckCraneNR)
    {
        super();
        this.truckCraneNR = truckCraneNR;
        attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/crane.j3o"));
		grabber2.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/grabbingGear.j3o"));
		grabber2.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/hookLeft.j3o"));
		grabber2.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/truckcrane/hookRight.j3o"));
                grabber2.setLocalTranslation(0, 1, 0);
		attachChild(grabber2);
                
        //Fabulous Colours *Sparkle*        
        Node subNodes = (Node) children.get(0);
        subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //Wheel Colour
        ContainingClient.getMyRootNode().attachChild(this);
    }
    
    public void startMovement(final AGV agv, final Container container, final boolean shouldPickupFromTruck)
    {
    	if(shouldPickupFromTruck)
    		craneToTruck(agv, container, shouldPickupFromTruck);
    	else
    		down(agv, container, false, false);
    }
    
    /**
     * This method is for dropping the container on the truck or on the AGV
     */
    public void down(final AGV agv, final Container container, final boolean atTruck, final boolean finalMove) 
    {
        dropContainer = new  MotionPath();
        dropContainer.addWayPoint(new Vector3f(0, 1, 0 + 25 * truckCraneNR));
        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0 + 25 * truckCraneNR));
        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        dropContainer.addListener(new MotionPathListener()
        {
			@Override
			public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
			{
				if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
				{
					if(!atTruck && container != null)
						agv.setContainer(container);
					else if(atTruck && container != null)
						truck.setContainer(container);
					else
						getGrabber().attachChild(container);
		        }
				else if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
				{
					if(!atTruck && !finalMove)
						craneToTruck(agv, container, false);
					else if(!atTruck && finalMove)
						;
					else if(atTruck && container != null)
						craneToAGV(agv, container, false);
					else if(atTruck && container == null)
						craneToAGV(agv, container, true);
				}
			}	
        });
        dropContainer.setCurveTension(0);
        motionControl3 = new MotionEvent(grabber2, dropContainer);
        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl3.setInitialDuration(10f);
        motionControl3.setSpeed(1f);
        motionControl3.play();        
    }
    
    /**
     * This method is for the crane to move back from truck, back to its starting point: the AGV parkinglot
     */
    public void craneToAGV(final AGV agv, final Container container, final boolean shouldPickupFromTruck)
    {
        moveBack = new MotionPath();
        moveBack.addWayPoint(new Vector3f(400, 0, -750 + 25 * truckCraneNR));
        moveBack.addWayPoint(new Vector3f(379.5f, 0, -750 + 25 * truckCraneNR));
        moveBack.setCurveTension(0);
        
        motionControl4 = new MotionEvent(this, moveBack);
        motionControl4.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl4.setInitialDuration(10f);
        motionControl4.setSpeed(1f);
        motionControl4.play();
    }
    
    /**
     * method for movement without container, from AGV to truck
     */
    public void craneToTruck(final AGV agv, final Container container, final boolean shouldPickupFromTruck)
    {
        craneToTruck = new MotionPath();
        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
        craneToTruck.addWayPoint(new Vector3f(400,0,-750 + 25 * truckCraneNR));
        craneToTruck.setCurveTension(0);
        craneToTruck.addListener(new MotionPathListener()
        {
			@Override
			public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
			{
				if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
				{
					if(container == null)
						down(agv, container, true, false);
					else if(container != null)
						down(agv, container, true, true);
		        }
			}	
        });
        truckToCraneMotion = new MotionEvent(this, craneToTruck);
        truckToCraneMotion.setDirectionType(MotionEvent.Direction.PathAndRotation);
        truckToCraneMotion.setInitialDuration(10f);
        truckToCraneMotion.setSpeed(1f);
        truckToCraneMotion.play();
    }
    
    /**
     * this method is for picking up a container from the truck.
     */
    public void down2() 
    {
        dropContainer = new  MotionPath();
        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        dropContainer.addWayPoint(new Vector3f(0, -4.8f, 0 + 25 * truckCraneNR));
        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        dropContainer.setCurveTension(0);        
        motionControl3 = new MotionEvent(grabber2, dropContainer);
        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl3.setInitialDuration(10f);
        motionControl3.setSpeed(1f);
        motionControl3.play();        
    }
    
    @Override
    public Node getGrabber() 
    {
    	return grabber2;
    }
}