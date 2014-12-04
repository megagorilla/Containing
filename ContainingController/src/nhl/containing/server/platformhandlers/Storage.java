package nhl.containing.server.platformhandlers;

import nhl.containing.server.util.XMLFileReader.Container;


public class Storage {

	private final int storageId;
	private final int x = 15;
	private final int y = 6;
	private final int z = 6;
	
	public Container[][][] containerStorage;
		
	public enum ContainerType
	{
		TRUCK, SEASHIP, RIVERSHIP, TRAIN;
	}
	
	public Storage(int storageId) {
		this.storageId = storageId;
		containerStorage = new Container[x][z][y];
	}
	
	public int getStorageId() {
		return this.storageId;
	}
}