/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenery;

import Controller.DisplayController;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.water.SimpleWaterProcessor;

/**
 *
 * @author Sander
 */
public class SeaNode extends Node{

    public SeaNode(DisplayController main) {
        //create processor
        SimpleWaterProcessor waterProcessor = new SimpleWaterProcessor(main.getAssetManager());
        waterProcessor.setReflectionScene(main.getRootNode());
        main.getViewPort().addProcessor(waterProcessor);


        //create water quad
        Spatial waterPlane=(Spatial)  main.getAssetManager().loadModel("Models/WaterTest/WaterTest.mesh.xml");
        waterPlane.setMaterial(waterProcessor.getMaterial());
        waterPlane.setLocalScale(2000);
        this.setLocalTranslation(0, -20, 0);
        
        attachChild(waterPlane);
        main.getRootNode().attachChild(this);
    }
    
}
