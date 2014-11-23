package nhl.containing.client.network;

import java.util.Set;

import javax.vecmath.Vector3f;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class UpdateMessage extends AbstractMessage
{

	protected String msg;
	protected Set<Data> data;

	public UpdateMessage()
	{
	}

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
	public UpdateMessage(String msg, Set<Data> data)
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
	public void addData(String name, Vector3f location)
	{
		data.add(new Data());
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
