package nhl.containing.server.platformhandlers;

import java.util.Stack;

import nhl.containing.server.util.XMLFileReader.Container;

import com.jme3.math.Vector3f;


public class Storage {
	private final int storageId;
	private final int storageX = 15;
	private final int storageY = 6;
	private final int storageZ = 6;
	private final Stack<Container>[][] containerStorage;
		
	public Storage(int storageId) {
		this.storageId = storageId;
		containerStorage = new Stack[storageX][storageZ];
		InitContainerStorage();
	}
	
	public void InitContainerStorage() {
		for (int z = 0; z < storageZ; z++) {
			for (int x = 0; x < storageX; x++) {
				containerStorage[x][z] = new Stack<Container>();
			}
		}
	}
	
	public Boolean StorageSpace(Container container) {
		for (int x = storageX; x <= 0; x--) {
			for (int z = storageZ; z <= 0; z--) {
				if(containerStorage[x][z].empty() == true || 
				   containerStorage[x][z].size() != storageY && 
				   container.getDeparture().getDay() <= containerStorage[x][z].peek().getDeparture().getDay())
				   
						return true;
			}
		}
		return false;
	}
	
	public Vector3f PushContainer(Container container) {
		for (int x = 0; x < storageX; x++) {
			for (int z = 0; z < storageZ; z++) {
				if(containerStorage[x][z].isEmpty() || 
				   container.getDeparture().getDay() <= containerStorage[x][z].peek().getDeparture().getDay() && 
				   containerStorage[x][z].size() < storageY) {
					
						containerStorage[x][z].push(container);
						return new Vector3f(x,containerStorage[x][z].size(),z);
				}
			}
		}
		return null;
	}
	
	public Container PopContainer(int x, int z) {
		return containerStorage[x][z].pop();
	}
	
	public int getStorageId() {
		return this.storageId;
	}
}