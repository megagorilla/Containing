package nhl.containing.client.entities.cranes;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Crane;

/**
 * 
 * @author Yannick
 */
public class DockingCrane extends Crane
{

	/**
	 * Loads the models for the DockingCrane and puts them in a node
	 * 
	 * @param qualtiy Changes the qualtiy of the crane (LOW,MEDIUM,HIGH)
	 */
	public DockingCrane(ContainingClient.Quality qualtiy)
	{
		String craneBasePath = "Models/high/crane/dockingcrane/crane.j3o";
		String grabbingGearPath = "Models/high/crane/dockingcrane/grabbingGear.j3o";
		String grabbingGearHolderPath = "Models/high/crane/dockingcrane/grabbingGearHolder.j3o";
		String hookLeftPath = "Models/high/crane/dockingcrane/hookLeft.j3o";
		String hookRightPath = "Models/high/crane/dockingcrane/hookRight.j3o";

		switch (qualtiy)
		{
			case HIGH:
				craneBasePath = "Models/high/crane/dockingcrane/crane.j3o";
				grabbingGearPath = "Models/high/crane/dockingcrane/grabbingGear.j3o";
				grabbingGearHolderPath = "Models/high/crane/dockingcrane/grabbingGearHolder.j3o";
				hookLeftPath = "Models/high/crane/dockingcrane/hookLeft.j3o";
				hookRightPath = "Models/high/crane/dockingcrane/hookRight.j3o";
				break;
			case MEDIUM:
				craneBasePath = "Models/medium/crane/dockingcrane/crane.j3o";
				grabbingGearPath = "Models/medium/crane/dockingcrane/grabbingGear.j3o";
				grabbingGearHolderPath = "Models/medium/crane/dockingcrane/grabbingGearHolder.j3o";
				hookLeftPath = "Models/medium/crane/dockingcrane/hookLeft.j3o";
				hookRightPath = "Models/medium/crane/dockingcrane/hookRight.j3o";
				break;
			case LOW:
				craneBasePath = "Models/low/crane/dockingcrane/crane.j3o";
				grabbingGearPath = "Models/low/crane/dockingcrane/grabbingGear.j3o";
				grabbingGearHolderPath = "Models/low/crane/dockingcrane/grabbingGearHolder.j3o";
				hookLeftPath = "Models/low/crane/dockingcrane/hookLeft.j3o";
				hookRightPath = "Models/low/crane/dockingcrane/hookRight.j3o";
				break;
		}

		attachChild(ContainingClient.getMyAssetManager().loadModel(craneBasePath));
		grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(grabbingGearPath));
		grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(grabbingGearHolderPath));
		grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(hookLeftPath));
		grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(hookRightPath));

		attachChild(grabber);
		ContainingClient.getMyRootNode().attachChild(this);
	}
}
