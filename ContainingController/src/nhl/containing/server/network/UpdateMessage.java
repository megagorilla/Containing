package nhl.containing.server.network;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class UpdateMessage extends AbstractMessage
{

	public String msg;
	public Set<AGVData> data = new HashSet<AGVData>();

	public UpdateMessage() {}

	/**
	 * Create a new instance of UpdateMessage.
	 * 
	 * @param msg
	 */
	public UpdateMessage(String msg)
	{
		super();
		this.msg = msg;
	}

	/**
	 * Create a new instance of UpdateMessage.
	 * 
	 * @param msg
	 * @param data
	 */
	public UpdateMessage(String msg, Set<AGVData> data)
	{
		super();
		this.msg = msg;
		this.data = data;
	}

	/**
	 * Add new data to the message.
	 * 
	 * @param name
	 * @param location
	 */
	public void addData(int id, List<Vector3f> list)
	{
	
		data.add(new AGVData(id, list));
	}

	/**
	 * Get the message string.
	 * 
	 * @return the message string
	 */
	public String getMsg()
	{
		return msg;
	}
}
