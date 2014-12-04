/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.cranes;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.materials.PlainMaterial;

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
    public TruckCrane(int truckCraneNR) {
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
    
    
    /**
     * This method is for the crane hook to move down. 
     * @param down 
     */
    public void MotionTruckCraneGrabber(boolean down) {
        grabContainer = new MotionPath();
        
        grabContainer.addWayPoint(new Vector3f(0, 1, 0));
        grabContainer.addWayPoint(new Vector3f(0, -4.8f, 0));
        grabContainer.addWayPoint(new Vector3f(0, 1,0));

        grabContainer.setCurveTension(0);
        grabContainer.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl = new MotionEvent(grabber2, grabContainer);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.play();
    }

    
    /**
     * This method is for the crane to move forward, from AGV parkinglot, to the truck
     */
    public void Go()
    {
        moveToTruck = new MotionPath();
        moveToTruck.addWayPoint(new Vector3f(380, 0, -750 + 25 * truckCraneNR));
        moveToTruck.addWayPoint(new Vector3f(400, 0, -750 + 25 * truckCraneNR));
        moveToTruck.setCurveTension(0);
        moveToTruck.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl2 = new MotionEvent(this, moveToTruck);
        motionControl2.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl2.setInitialDuration(10f);
        motionControl2.setSpeed(1f);
        motionControl2.play();
    }
    
    /**
     * This method is for dropping the container on the truck or on the AGV
     */
    public void down() 
    {
        dropContainer = new  MotionPath();
        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        dropContainer.addWayPoint(new Vector3f(0, -4.5f, 0 + 25 * truckCraneNR));
        dropContainer.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        dropContainer.setCurveTension(0);
        
        motionControl3 = new MotionEvent(grabber2, dropContainer);
        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl3.setInitialDuration(10f);
        motionControl3.setSpeed(1f);
        motionControl3.play();        
        System.out.println("DOWN");        
    }
    
    /**
     * This method is for the crane to move back from truck, back to its starting point: the AGV parkinglot
     */
    public void GoBack()
    {
        moveBack = new MotionPath();
        moveBack.addWayPoint(new Vector3f(400, 0, -750 + 25 * truckCraneNR));
        moveBack.addWayPoint(new Vector3f(379.95f, 0, -750 + 25 * truckCraneNR));
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
    public void CranetoTruck()
    {
        craneToTruck = new MotionPath();
        craneToTruck.addWayPoint(new Vector3f(380,0,-750 + 25 * truckCraneNR));
        craneToTruck.addWayPoint(new Vector3f(400,0,-750 + 25 * truckCraneNR));
        craneToTruck.setCurveTension(0);
        
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
        System.out.println("DOWN");        
    }
    
//    public void MotionTruckCraneGrabber2(boolean down) {
//        grabContainer = new MotionPath();
//        
//        grabContainer.addWayPoint(new Vector3f(0, 1, 0));
//        grabContainer.addWayPoint(new Vector3f(0, 0.99f, 0));
//        grabContainer.addWayPoint(new Vector3f(0, 1,0));
//
//        grabContainer.setCurveTension(0);
//        grabContainer.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
//        motionControl = new MotionEvent(grabber2, grabContainer);
//        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
//        motionControl.setInitialDuration(10f);
//        motionControl.setSpeed(1f);
//        motionControl.play();
//    }
    
        @Override
        public Node getGrabber() 
        {
            return grabber2;
        }
}

