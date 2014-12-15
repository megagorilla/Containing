package nhl.containing.client.entities.cranes;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.entities.vehicles.AGV;
import nhl.containing.client.entities.vehicles.Truck;
import nhl.containing.client.materials.PlainMaterial;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
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
     * @param truckCraneNR the number of this truckCrane
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
                
        Node subNodes = (Node) children.get(0);
        subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //Wheel Colour
        ContainingClient.getMyRootNode().attachChild(this);
    }
    
//    public void pickupFromTruck(AGV agv, Truck truck, Container container)
//    {
//    	craneToTruck(agv, truck, container);
//    }
    
    public void fromTruck(final AGV agv, final Container container)
    {
    	container.rotate(0, FastMath.HALF_PI, 0);
        craneToTruck = new MotionPath();
        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
        craneToTruck.addWayPoint(new Vector3f(400,0,-750 + 25 * truckCraneNR));
        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
        truckToCraneMotion = new MotionEvent(this, craneToTruck);
        craneToTruck.addListener(new MotionPathListener()
        {
			@Override
			public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
			{
				if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
				{
					motionControl.pause();
			        dropContainer = new MotionPath();
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0));
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addListener(new MotionPathListener()
			        {
						@Override
						public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
						{
							if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
							{
								getGrabber().attachChild(container);
								container.setLocalTranslation(new Vector3f(0, 6.0f, 0));
					        }
							else if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
							{
								truckToCraneMotion.play();
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
				else if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
				{
			        dropContainer = new MotionPath();
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0));
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addListener(new MotionPathListener()
			        {
						@Override
						public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
						{
							if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
							{
								agv.attachChild(container);
								container.setLocalTranslation(new Vector3f(0, 1.2f, 0));
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
			}
        });
        truckToCraneMotion.setDirectionType(MotionEvent.Direction.PathAndRotation);
        truckToCraneMotion.setInitialDuration(10f);
        truckToCraneMotion.setSpeed(1f);
        truckToCraneMotion.play();
    }
     
    
    public void toTruck(final AGV agv, final Container container, final Truck truck)
    {        
        container.rotate(0, FastMath.HALF_PI, 0);
        dropContainer = new MotionPath();
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0));
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addListener(new MotionPathListener()
                                {
						@Override
						public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
						{
							if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
							{
								getGrabber().attachChild(container);
								container.setLocalTranslation(new Vector3f(0, -1f, 0));
					        }
							else if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
							{
								truckToCraneMotion.play();
							}
						}	
			        });   
        
        
        craneToTruck = new MotionPath();
        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
        craneToTruck.addWayPoint(new Vector3f(400,0,-750 + 25 * truckCraneNR));
        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
        truckToCraneMotion = new MotionEvent(this, craneToTruck);
        craneToTruck.addListener(new MotionPathListener()
        {   
            @Override
            public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
            {
                if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
				{
                                motionControl.pause();
			        dropContainer = new MotionPath();
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0));
			        dropContainer.addWayPoint(new Vector3f(0, 1, 0));
			        dropContainer.addListener(new MotionPathListener()
			        {
						@Override
						public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
						{
							if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
							{
								truck.attachChild(container);
								container.setLocalTranslation(new Vector3f(0, 1.5f, 0));
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
			}
        });
        truckToCraneMotion.setDirectionType(MotionEvent.Direction.PathAndRotation);
        truckToCraneMotion.setInitialDuration(10f);
        truckToCraneMotion.setSpeed(1f);
        truckToCraneMotion.play();
    }
    
    /**
     * This method is for dropping the container on the truck or on the AGV
     */
//    public void down(final AGV agv, final Truck truck, final Container container, final boolean shouldGrab) 
//    {
//        dropContainer = new MotionPath();
//        dropContainer.addWayPoint(new Vector3f(0, 1, 0 + 25 * truckCraneNR));
//        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0 + 25 * truckCraneNR));
//        dropContainer.addWayPoint(new Vector3f(0, 1, 0 + 25 * truckCraneNR));
//        dropContainer.addListener(new MotionPathListener()
//        {
//			@Override
//			public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
//			{
//				if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 2)
//				{
//					if(shouldGrab)
//						getGrabber().attachChild(container);
//					else
//						agv.attachChild(container);
//		        }
//				else if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
//				{
//					craneToAGV(agv, container);
//				}
//			}	
//        });
//        dropContainer.setCurveTension(0);
//        motionControl3 = new MotionEvent(grabber2, dropContainer);
//        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
//        motionControl3.setInitialDuration(10f);
//        motionControl3.setSpeed(1f);
//        motionControl3.play();        
//    }
//    
//    /**
//     * This method is for the crane to move back from truck, back to its starting point: the AGV parkinglot
//     */
//    public void craneToAGV(final AGV agv, final Container container)
//    {
//        moveBack = new MotionPath();
//        moveBack.addWayPoint(new Vector3f(400, 0, -750 + 25 * truckCraneNR));
//        moveBack.addWayPoint(new Vector3f(379.95f, 0, -750 + 25 * truckCraneNR));
//        moveBack.setCurveTension(0);
//        moveBack.addListener(new MotionPathListener()
//        {
//			@Override
//			public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
//			{
//				if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
//				{
//					down(agv, null, container, false);
//				}
//			}	
//        });
//        motionControl4 = new MotionEvent(this, moveBack);
//        motionControl4.setDirectionType(MotionEvent.Direction.PathAndRotation);
//        motionControl4.setInitialDuration(10f);
//        motionControl4.setSpeed(1f);
//        motionControl4.play();
//    }
//    
//    /**
//     * method for movement without container, from AGV to truck
//     */
//    public void craneToTruck(final AGV agv, final Truck truck, final Container container)
//    {
//        craneToTruck = new MotionPath();
//        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
//        craneToTruck.addWayPoint(new Vector3f(400,0,-750 + 25 * truckCraneNR));
//        craneToTruck.setCurveTension(0);
//        craneToTruck.addListener(new MotionPathListener()
//        {
//			@Override
//			public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
//			{
//				if(motionControl.getPath().getNbWayPoints() == wayPointIndex + 1)
//				{
//					down(agv, truck, container, false);
//				}
//			}	
//        });
//        truckToCraneMotion = new MotionEvent(this, craneToTruck);
//        truckToCraneMotion.setDirectionType(MotionEvent.Direction.PathAndRotation);
//        truckToCraneMotion.setInitialDuration(10f);
//        truckToCraneMotion.setSpeed(1f);
//        truckToCraneMotion.play();
//    }
    
    /**
     * this method is for picking up a container from the truck.
     */
//    public void down2() 
//    {
//        dropContainer = new  MotionPath();
//        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
//        dropContainer.addWayPoint(new Vector3f(0, -4.8f, 0 + 25 * truckCraneNR));
//        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
//        dropContainer.setCurveTension(0);        
//        motionControl3 = new MotionEvent(grabber2, dropContainer);
//        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
//        motionControl3.setInitialDuration(10f);
//        motionControl3.setSpeed(1f);
//        motionControl3.play();        
//    }
    
    @Override
    public Node getGrabber() 
    {
    	return grabber2;
    }
}
