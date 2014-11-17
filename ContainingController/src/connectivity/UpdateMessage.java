package connectivity;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.util.Map;
import javax.vecmath.Vector3f;

@Serializable
public class UpdateMessage extends AbstractMessage {
        
    String msg;
    Map<String,Vector3f> data;

    /**
     * Create a new instance of UpdateMessage.
     * @param msg 
     */
    public UpdateMessage(String msg){
        super();
        this.msg = msg;
    }
    
    /**
     * Create a new instance of UpdateMessage.
     * @param msg 
     * @param data
     */
    public UpdateMessage(String msg, Map<String,Vector3f> data) {
        super();
        this.msg = msg;
        this.data = data;
    }
    
    /**
     * Add new data to the message.
     * @param name
     * @param location 
     */
    public void addData(String name, Vector3f location) {
        data.put(name, location);
    }
    
    /**
     * Get the message string.
     * @return the message string
     */
    public String getMsg () {
        return msg;
    }
}
