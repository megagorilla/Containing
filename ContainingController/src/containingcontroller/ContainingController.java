/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containingcontroller;

import connectivity.API;
import connectivity.ConnectionManager;
import connectivity.UpdateMessage;
import java.io.IOException;

/**
 *
 * @author Sander
 */
public class ContainingController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Test");
        // test whether the JME server launches successfully (on port 3000)
        if (!ConnectionManager.initialize(3000))
            System.err.println("Failed to start server!");
        else System.out.println("Successfully started server");
        // test command
        ConnectionManager.sendCommand(new UpdateMessage("blah"));
        // start the API, listen on port 8080
        API.start(8080);
        // use read to keep the program active
        System.out.println("Type any character to quit");
        System.in.read();
    }
}
