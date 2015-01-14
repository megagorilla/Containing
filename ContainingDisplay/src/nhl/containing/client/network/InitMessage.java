package nhl.containing.client.network;

import com.jme3.network.serializing.Serializable;

@Serializable
public class InitMessage extends UpdateMessage 
{
	float speed;
	
	public InitMessage()
	{
	}
}
