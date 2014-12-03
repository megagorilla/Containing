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
 * @author Sander
 */
public class TruckCrane extends Crane {
    private MotionPath path2;
    private MotionPath path3;
    private MotionPath path4;
    private MotionPath path5;
    private MotionEvent motionControl;
    private MotionEvent motionControl2;
    private MotionEvent motionControl3;
    private MotionEvent motionControl4;
    int truckCraneNR;
    
    private MotionPath craneToTruck;
    private MotionEvent truckToCraneMotion;

    /**
     * creates the TruckCrane and loads all the models
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
    
    public void MotionTruckCraneGrabber(boolean down) {
        path2 = new MotionPath();
        
        path2.addWayPoint(new Vector3f(0, 1, 0));
        path2.addWayPoint(new Vector3f(0, -4.8f, 0));
        path2.addWayPoint(new Vector3f(0, 1,0));

        path2.setCurveTension(0);
        path2.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl = new MotionEvent(grabber2, path2);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.play();
    }

    public void Go()
    {
        path3 = new MotionPath();
        path3.addWayPoint(new Vector3f(380, 0, -750 + 25 * truckCraneNR));
        path3.addWayPoint(new Vector3f(400, 0, -750 + 25 * truckCraneNR));
        path3.setCurveTension(0);
        path3.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl2 = new MotionEvent(this, path3);
        motionControl2.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl2.setInitialDuration(10f);
        motionControl2.setSpeed(1f);
        motionControl2.play();
    }
    
    public void down() 
    {
        path4 = new  MotionPath();
        path4.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        path4.addWayPoint(new Vector3f(0, -4.5f, 0 + 25 * truckCraneNR));
        path4.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        path4.setCurveTension(0);
        
        motionControl3 = new MotionEvent(grabber2, path4);
        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl3.setInitialDuration(10f);
        motionControl3.setSpeed(1f);
        motionControl3.play();        
        System.out.println("DOWN");        
    }
    
    public void GoBack()
    {
        path5 = new MotionPath();
        path5.addWayPoint(new Vector3f(400, 0, -750 + 25 * truckCraneNR));
        path5.addWayPoint(new Vector3f(379.5f, 0, -750 + 25 * truckCraneNR));
        path5.setCurveTension(0);
        
        motionControl4 = new MotionEvent(this, path5);
        motionControl4.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl4.setInitialDuration(10f);
        motionControl4.setSpeed(1f);
        motionControl4.play();
    }
    
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
    
    public void down2() 
    {
        path4 = new  MotionPath();
        path4.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        path4.addWayPoint(new Vector3f(0, -4.8f, 0 + 25 * truckCraneNR));
        path4.addWayPoint(new Vector3f(0,1, 0 + 25 * truckCraneNR));
        path4.setCurveTension(0);
        path4.addListener(new MotionPathListener() {
            public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) 
            {
                if(wayPointIndex == 1)
                    System.out.println("6y");
                    
            }           
        });
        motionControl3 = new MotionEvent(grabber2, path4);
        motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl3.setInitialDuration(10f);
        motionControl3.setSpeed(1f);
        motionControl3.play();        
        System.out.println("DOWN");        
    }
    
    public void MotionTruckCraneGrabber2(boolean down) {
        path2 = new MotionPath();
        
        path2.addWayPoint(new Vector3f(0, 1, 0));
        path2.addWayPoint(new Vector3f(0, 0.99f, 0));
        path2.addWayPoint(new Vector3f(0, 1,0));

        path2.setCurveTension(0);
        path2.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl = new MotionEvent(grabber2, path2);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.play();
    }
    
        @Override
        public Node getGrabber() 
        {
            return grabber2;
        }
}

