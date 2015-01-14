package nhl.containing.client.entities.cranes;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Yannick
 */
public class StorageCrane extends Crane {

    //private static final Container container;
    private MotionPath path;
    private MotionPath craneMove;
    private MotionEvent motionControl;
    private MotionEvent motionControl3;

    /**
     * creates the storageCrane and loads the models for the grabber and the
     * crane
     */
    public StorageCrane() {
        super();
        Node subNode = new Node();
        subNode.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/crane.j3o"));
        Node grabber3 = new Node();
        grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/grabbingGear.j3o"));
        grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/grabbingGearHolder.j3o"));
        grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/hookLeft.j3o"));
        grabber3.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/storagecrane/hookRight.j3o"));
        grabber3.setLocalTranslation(0, -1, 0);
        grabber.attachChild(grabber3);
        subNode.attachChild(grabber);
        
        //set colour of the crane
//        Node subNodes = (Node) children.get(0);
//        subNodes.getChild(0).setMaterial(new PlainMaterial(new ColorRGBA(1, 20f / 255f, 147f / 255f, 1f))); //mainBodyColour
//        subNodes.getChild(1).setMaterial(new PlainMaterial(ColorRGBA.White)); //wheelColour
        subNode.rotate(0, FastMath.HALF_PI, 0);
        attachChild(subNode);
        ContainingClient.getMyRootNode().attachChild(this);
    }

    
    /**
     * store Container at the right side of the storageplatform
     * @param container
     * @param rootNode
     * @param location
     * @param i
     * @param StorageCranes 
     */
    public void StoreRight(final Container container, final Node rootNode, final Vector3f location, final int i, final Crane StorageCranes)
    {
        craneMove = new MotionPath();
        craneMove.addWayPoint(this.getLocalTranslation());
        craneMove.addWayPoint(new Vector3f(245, 0, this.getLocalTranslation().z));
        craneMove.addWayPoint(new Vector3f(215 - location.x * 13.4f, 0, this.getLocalTranslation().z));
//        craneMove.addWayPoint(new Vector3f(0, 0, -760));

        motionControl = new MotionEvent(this, craneMove);
                
        craneMove.addListener(new MotionPathListener() 
        {
            @Override
            public void onWayPointReach(MotionEvent control, int wayPointIndex) 
            {
              if (control.getPath().getNbWayPoints() == wayPointIndex + 2) {
                    control.pause();
                    
                    path = new MotionPath();
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - (i * 3.3f), 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - (i * 3.3f), -21.8f, 0));
                    path.addWayPoint(new Vector3f(8.2f - (i * 3.3f), 0, 0));
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    
                    path.addListener(new MotionPathListener() {
                        
                    @Override    
                    public void onWayPointReach(MotionEvent Motioncontrol, int wayPointIndex) {
                   
                    if (path.getNbWayPoints() == wayPointIndex + 3) 
                    {
                        getGrabber().attachChild(container); 
                        container.setLocalTranslation(0, 23f, 0);
                    }  
                    else if(path.getNbWayPoints() == wayPointIndex + 1)
                    {
                        motionControl.play();
                    }                
            }           
        });
                 path.setCurveTension(0);
		 motionControl3 = new MotionEvent(grabber, path);
		 motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
		 motionControl3.setInitialDuration(10f);
		 motionControl3.setSpeed(ContainingClient.getSpeed());
		 motionControl3.play(); 
                }  
              else if(control.getPath().getNbWayPoints() == wayPointIndex + 1)
                {
                    path = new MotionPath();
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, -23f + location.y * 3.0f, 0));
                    path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, 0, 0));
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    
                    path.addListener(new MotionPathListener() 
                    {
                           
                        @Override
                            public void onWayPointReach(MotionEvent Motioncontrol, int wayPointIndex) 
                        {
                            if (path.getNbWayPoints() == wayPointIndex + 3) 
                            {
                               rootNode.attachChild(container); 
                               container.rotate(0, FastMath.HALF_PI, 0);
                               container.setLocalTranslation(getLocalTranslation().x + getGrabber().getLocalTranslation().z , location.y * 2.9f, getLocalTranslation().z - getGrabber().getLocalTranslation().x);
                            }               
                        }	           
                    });     
                    
                    path.setCurveTension(0);
                    motionControl3 = new MotionEvent(grabber, path);
					motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
					motionControl3.setInitialDuration(10f);
					motionControl3.setSpeed(ContainingClient.getSpeed());
					motionControl3.play(); 
                }  
            }           
        });
        craneMove.setCurveTension(0);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(ContainingClient.getSpeed());
        motionControl.play();
    }
    
    /**
     * store Container at the left side of the storageplatform
     * @param container
     * @param rootNode
     * @param location
     * @param i
     * @param StorageCranes 
     */
    public void StoreLeft(final Container container, final Node rootNode, final Vector3f location, final int i, final Crane StorageCranes)
    {
        craneMove = new MotionPath();
        craneMove.addWayPoint(new Vector3f(0, 0, -720));
        craneMove.addWayPoint(new Vector3f(-245, 0, -720));
        craneMove.addWayPoint(new Vector3f(-215 + location.x * 13.4f, 0, -720));
//        craneMove.addWayPoint(new Vector3f(0, 0, -720));

        motionControl = new MotionEvent(this, craneMove);
                
        craneMove.addListener(new MotionPathListener() 
        {
            @Override
            public void onWayPointReach(MotionEvent control, int wayPointIndex) 
            {
              if (control.getPath().getNbWayPoints() == wayPointIndex + 2) {
                    control.pause();
                    
                    path = new MotionPath();
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - (i * 3.3f), 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - (i * 3.3f), -21.8f, 0));
                    path.addWayPoint(new Vector3f(8.2f - (i * 3.3f), 0, 0));
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    
                    path.addListener(new MotionPathListener() {
                        
                    @Override    
                    public void onWayPointReach(MotionEvent Motioncontrol, int wayPointIndex) {
                   
                    if (path.getNbWayPoints() == wayPointIndex + 3) 
                    {
                        getGrabber().attachChild(container); 
                        container.setLocalTranslation(0, 23f, 0);
                    }  
                    else if(path.getNbWayPoints() == wayPointIndex + 1)
                    {
                        motionControl.play();
                    }                
            }           
        });
                 path.setCurveTension(0);
		 motionControl3 = new MotionEvent(grabber, path);
		 motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
		 motionControl3.setInitialDuration(10f);
		 motionControl3.setSpeed(1f);
		 motionControl3.play(); 
                }  
              else if(control.getPath().getNbWayPoints() == wayPointIndex + 1)
                {
                    path = new MotionPath();
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, 0, 0));
                    path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, -23f + location.y * 3.0f, 0));
                    path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, 0, 0));
                    path.addWayPoint(new Vector3f(0, 0, 0));
                    
                    path.addListener(new MotionPathListener() {
                           
                        @Override
                            public void onWayPointReach(MotionEvent Motioncontrol, int wayPointIndex) 
                        {
                            if (path.getNbWayPoints() == wayPointIndex + 3) 
                            {
                               rootNode.attachChild(container);     
                               container.setLocalTranslation(ContainingClient.StorageCranes.get(1).getLocalTranslation().x + getGrabber().getLocalTranslation().z ,0 , ContainingClient.StorageCranes.get(1).getLocalTranslation().z - getGrabber().getLocalTranslation().x);
                               container.rotate(0, FastMath.HALF_PI, 0);
                            }               
            }           
        });     
                    
        path.setCurveTension(0);
        motionControl3 = new MotionEvent(grabber, path);
	motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
	motionControl3.setInitialDuration(10f);
	motionControl3.setSpeed(1f);
	motionControl3.play(); 
                }  
            }           
        });
        craneMove.setCurveTension(0);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.play();
    }
      
    //opladen AGV voor vertrek container
    public void LoadAGV(final Container container, final Node rootNode, final Vector3f location, final int i, final Crane StorageCranes)
    {
          path = new MotionPath();
          path.addWayPoint(new Vector3f(0, 0, 0));
          path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, 0, 0));
          path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, -21.8f + location.y * 3.0f, 0));
          path.addWayPoint(new Vector3f(8.2f - location.z * 3.3f, 0, 0));
          path.addWayPoint(new Vector3f(0, 0, 0));
          
          motionControl = new MotionEvent(this, craneMove);
         
          
          path.addListener(new MotionPathListener() {
                         
                    @Override    
                    public void onWayPointReach(MotionEvent Motioncontrol, int wayPointIndex) {
                   
                    if (path.getNbWayPoints() == wayPointIndex + 3) 
                    {
                        getGrabber().attachChild(container); 
                        container.setLocalTranslation(0, 23f, 0);
                        container.rotate(0, FastMath.HALF_PI, 0);
                    }     
                        
                    else if(path.getNbWayPoints() == wayPointIndex + 1)
                    {
                        
                        craneMove = new MotionPath();
                        craneMove.addWayPoint(new Vector3f(0, 0, -680));
                        craneMove.addWayPoint(new Vector3f(245, 0, -680));
                        craneMove.addWayPoint(new Vector3f(0, 0, -680));
                        
                        craneMove.addListener(new MotionPathListener(){
                            
                        @Override
                        public void onWayPointReach(MotionEvent Motioncontrol, int wayPointIndex)
                        {
                            
                        }
                        
                        });
                        //motionControl.play();
                    }     
                        
                    
            }     
                    
        });
                 path.setCurveTension(0);
		 motionControl3 = new MotionEvent(grabber, path);
		 motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
		 motionControl3.setInitialDuration(10f);
		 motionControl3.setSpeed(1f);
		 motionControl3.play(); 
    }
   
    @Override
    public Node getGrabber() {
        return grabber;
    }
}
