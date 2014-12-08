/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.client.entities.cranes;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Crane;
import nhl.containing.client.materials.PlainMaterial;

/**
 *
 * @author Yannick
 */
public class StorageCrane extends Crane {

    private MotionPath path;
    private MotionPath craneMove;
    private MotionEvent motionControl;
    private boolean up = true;
    private boolean move = false;

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

    public void MotionY() {
        path = new MotionPath();
        path.addWayPoint(new Vector3f(0, 0, 0));
        path.addWayPoint(new Vector3f(8, 0, 0));
        path.addWayPoint(new Vector3f(8, -23f, 0));
        path.addWayPoint(new Vector3f(8, 0, 0));
        path.addWayPoint(new Vector3f(0, 0, 0));

        path.setCurveTension(0);
        path.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
        motionControl = new MotionEvent(grabber, path);
        motionControl.setDirectionType(MotionEvent.Direction.PathAndRotation);
        motionControl.setInitialDuration(10f);
        motionControl.setSpeed(1.0f);
        motionControl.play();
    }

    public void CraneMovement() {
        craneMove = new MotionPath();
        craneMove.addWayPoint(new Vector3f(0, 0, -760));
        craneMove.addWayPoint(new Vector3f(290, 0, -760));
        craneMove.setCurveTension(0);
        craneMove.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());

        motionControl = new MotionEvent(this, craneMove);
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
