package nhl.containing.client.entities.cranes;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.entities.vehicles.AGV;

/**
 *
 * @author Sander
 */
public class TrainCrane extends Crane {

    private MotionPath moveCrane;
    private MotionPath getContainer;
    private MotionEvent motionControl;
    private MotionEvent motionControl2;

    /**
     * creates the TrainCrane and loads the models
     */
    public TrainCrane() {
        super();
        attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/crane.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/grabbingGear.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/grabbingGearHolder.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/hookLeft.j3o"));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel("Models/high/crane/traincrane/hookRight.j3o"));
        attachChild(grabber);
        ContainingClient.getMyRootNode().attachChild(this);
    }
    
    
    /**
     * Load container on the crane, and put it on the AGV
     * @param container
     * @param agv
     * @param wagonNR 
     */
    public void loadContainer(final Container container, final AGV agv, final int wagonNR)
    {
        moveCrane = new MotionPath();
        moveCrane.addWayPoint(new Vector3f(-327.5f, 0f, 497.2f));
        moveCrane.addWayPoint(new Vector3f(-327.5f, 0f, 497.2f-(wagonNR * 18.4f)));
        
        //497.2 - (wagonNumber * 18.4)
        //423.6
        
        motionControl = new MotionEvent(this, moveCrane);
        
        moveCrane.addListener(new MotionPathListener()
        {
            @Override
            public void onWayPointReach(MotionEvent control, int wayPointIndex) 
            {
                if(moveCrane.getNbWayPoints() == wayPointIndex + 1)
                {
                    getContainer = new MotionPath();
                    getContainer.addWayPoint(new Vector3f(0,0,0));
                    getContainer.addWayPoint(new Vector3f(-6.5f,0,0));
                    getContainer.addWayPoint(new Vector3f(-6.5f,-2.8f,0));
                    getContainer.addWayPoint(new Vector3f(-6.5f,0,0));
                    getContainer.addWayPoint(new Vector3f(0,0,0));
                    getContainer.addWayPoint(new Vector3f(0,-2.8f,0));
                    getContainer.addWayPoint(new Vector3f(0,0,0));
                    
                    motionControl2 = new MotionEvent(grabber, getContainer);
                    
                    getContainer.addListener(new MotionPathListener()
                    {
                        @Override
                        public void onWayPointReach(MotionEvent control, int wayPointIndex)
                        {
                            if(getContainer.getNbWayPoints() == wayPointIndex + 5)
                            {
                               getGrabber().attachChild(container);
                               container.setLocalTranslation(0, 4, 0);
                            }
                            else if(getContainer.getNbWayPoints() == wayPointIndex + 2)
                            {
                                agv.attachChild(container);
                                container.setLocalTranslation(0, 1.2f, 0);
                            }
                        }
                    });
                }
                getContainer.setCurveTension(0);
                motionControl2.setDirectionType(MotionEvent.Direction.PathAndRotation);
                motionControl2.setInitialDuration(10f);
                motionControl2.setSpeed(1f);
                motionControl2.play();
            }
        });
        
        moveCrane.setCurveTension(0);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1f);
        motionControl.play();
    }
    
    @Override
    public Node getGrabber() {
        return grabber;
    }
}
