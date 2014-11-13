/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenery;

import Controller.DisplayController;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.water.SimpleWaterProcessor;

/**
 *
 * @author Sander
 */
public class SeaNode extends Node{

    public SeaNode(DisplayController main) {
        // we create a water processor
        SimpleWaterProcessor waterProcessor = new SimpleWaterProcessor(main.getAssetManager());
        waterProcessor.setReflectionScene(this);
 
        // we set the water plane
        Vector3f waterLocation=new Vector3f(0,-6,0);
        waterProcessor.setPlane(new Plane(Vector3f.UNIT_Y, waterLocation.dot(Vector3f.UNIT_Y)));
        main.getViewPort().addProcessor(waterProcessor);
 
        // we set wave properties
        waterProcessor.setWaterDepth(40);         // transparency of water
        waterProcessor.setDistortionScale(0.05f); // strength of waves
        waterProcessor.setWaveSpeed(0.05f);       // speed of waves
 
        // we define the wave size by setting the size of the texture coordinates
        Quad quad = new Quad(400,400);
        quad.scaleTextureCoordinates(new Vector2f(6f,6f));
 
        // we create the water geometry from the quad
        Geometry water=new Geometry("water", quad);
        water.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
        water.setLocalTranslation(-200, -6, 250);
        water.setShadowMode(ShadowMode.CastAndReceive);
        water.setMaterial(waterProcessor.getMaterial());
        attachChild(water);
    }
    
}
