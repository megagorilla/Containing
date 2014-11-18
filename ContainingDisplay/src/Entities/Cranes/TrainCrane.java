/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Cranes;

import Controller.DisplayController;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import sun.awt.DisplayChangedListener;

/**
 *
 * @author Sander
 */
public class TrainCrane extends Crane{

    public TrainCrane(DisplayController main) {
        super();
        try{
            Node craneBase = (Node)main.getAssetManager().loadModel("Models/high/crane/traincrane/crane.j3o");
            attachChild(craneBase);
        }catch(Exception e){
            Geometry craneBaseGeom = (Geometry)main.getAssetManager().loadModel("Models/high/crane/traincrane/crane.j3o");
            attachChild(craneBaseGeom);
        }
        
        try{
            Node grabbingGear = (Node)main.getAssetManager().loadModel("Models/high/crane/traincrane/grabbingGear.j3o");
            grabber.attachChild(grabbingGear);
        }catch(Exception e){
            Geometry grabbingGearGeom = (Geometry)main.getAssetManager().loadModel("Models/high/crane/traincrane/grabbingGear.j3o");
            grabber.attachChild(grabbingGearGeom);
        }
        
        try{
            Node grabbingGearHolder = (Node)main.getAssetManager().loadModel("Models/high/crane/traincrane/grabbingGearHolder.j3o");
            grabber.attachChild(grabbingGearHolder);
        }catch(Exception e){
            Geometry grabbingGearHolderGeom = (Geometry)main.getAssetManager().loadModel("Models/high/crane/traincrane/grabbingGearHolder.j3o");
            grabber.attachChild(grabbingGearHolderGeom);
        }
        
        try{
            Node hookLeft = (Node)main.getAssetManager().loadModel("Models/high/crane/traincrane/hookLeft.j3o");
            grabber.attachChild(hookLeft);
        }catch(Exception e){
            Geometry hookLeftGeom = (Geometry)main.getAssetManager().loadModel("Models/high/crane/traincrane/hookLeft.j3o");
            grabber.attachChild(hookLeftGeom);
        }
        
        try{
            Node hookRight = (Node)main.getAssetManager().loadModel("Models/high/crane/traincrane/hookRight.j3o");
            grabber.attachChild(hookRight);
        }catch(Exception e){
            Geometry hookRightGeom = (Geometry)main.getAssetManager().loadModel("Models/high/crane/traincrane/hookRight.j3o");
            grabber.attachChild(hookRightGeom);
        }
        
        attachChild(grabber);
        main.getRootNode().attachChild(this);
    }
    
    
}
