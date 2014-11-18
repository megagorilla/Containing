/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Vehicles;

import Controller.DisplayController;
import static Controller.DisplayController.Quality.HIGH;
import static Controller.DisplayController.Quality.LOW;
import static Controller.DisplayController.Quality.MEDIUM;
import Materials.TexturedMaterial;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Sander
 */
public class Train extends Vehicle
{
    Node trainNode;
    Node wagonNode;
    
    public Train(DisplayController.Quality quality, DisplayController main) 
    {
        super();
        String qualityPath = "";
        switch (quality)
        {
            case HIGH:
                qualityPath = "Models/high/train/train.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/train/train.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/train/train.j3o";
                break;
        }
        trainNode = (Node)DisplayController.getmyAssetManager().loadModel(qualityPath);
        attachChild(trainNode);
        DisplayController.getMyRootNode().attachChild(this);
        
        //trainNode.setLocalTranslation(0, 0, 0);
        Wagon(quality, main);
    }
    
    public void Wagon(DisplayController.Quality quality, DisplayController main)
    {
        for(int i = 0; i < 5; i++)
        {
        String qualityPath = "";
        switch (quality)
        {
            case HIGH:
                qualityPath = "Models/high/train/wagon.j3o";
                break;
            case MEDIUM:
                qualityPath = "Models/medium/train/wagon.j3o";
                break;
            case LOW:
                qualityPath = "Models/low/train/wagon.j3o";
                break;
        }
        wagonNode = (Node)DisplayController.getmyAssetManager().loadModel(qualityPath);
        attachChild(wagonNode);
        
        
        wagonNode.setLocalTranslation(0, 0, -13.2f - 18.4f*i);
        }
        DisplayController.getMyRootNode().attachChild(this);
    }
    
}
