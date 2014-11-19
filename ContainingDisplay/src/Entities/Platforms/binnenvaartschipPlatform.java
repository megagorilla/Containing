/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Platforms;

import Controller.DisplayController;
import Entities.Rails.CraneRails;
import Materials.PlainMaterial;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.sun.xml.internal.ws.api.message.Attachment;

/**
 *
 * @author Sander
 */
public class binnenvaartschipPlatform extends Platform
{
    Node sideWayBox = new Node();
    Node hookNorthBox = new Node();
    Node hookSouthBox = new Node();
    Geometry craneRails;
    
    public binnenvaartschipPlatform()
    {
        SideWay();
        HookNorth();
        HookSouth();
        littleShipRails();
        DisplayController.getMyRootNode().attachChild(this);
    }
    
    private void SideWay()
    {        
        Box SideWay = new Box(20,0.1f,725);
        Geometry sideWayGeom = new Geometry("Box", SideWay);
        sideWayGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        sideWayBox.attachChild(sideWayGeom);
        sideWayGeom.setLocalTranslation(0,0,-725);
        DisplayController.getMyRootNode().attachChild(sideWayBox);        
    }
    
    private void HookNorth()
    {
        Box HookNorth = new Box(30,0.1f,20);
        Geometry hookNorthGeom = new Geometry("Box", HookNorth);
        hookNorthGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        hookNorthBox.attachChild(hookNorthGeom);
        hookNorthGeom.setLocalTranslation(-50,0.1f,-1430);
        DisplayController.getMyRootNode().attachChild(hookNorthBox); 
    }
    
    private void HookSouth()
    {
        Box HookSouth = new Box(30,0.1f,20);
        Geometry hookSouthGeom = new Geometry("Box", HookSouth);
        hookSouthGeom.setMaterial(new PlainMaterial(ColorRGBA.Black));
        hookSouthBox.attachChild(hookSouthGeom);
        hookSouthGeom.setLocalTranslation(-50,0.1f,-20);
        DisplayController.getMyRootNode().attachChild(hookSouthBox); 
    }
    
     private void littleShipRails()
     {
         for(int i = 1; i < 76; i++)
         {
            attachChild(new CraneRails(new Vector3f(-7,0.1f, -40-18*i), 1f, 0f));
         }
     }
}
