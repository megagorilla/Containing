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
        if (!ConnectionManager.initialize(3000))
            System.err.println("Failed to start server!");
        else System.out.println("Successfully started server");
        ConnectionManager.sendCommand(new UpdateMessage("blah"));
        API.start();
        System.out.println("Type any character to quit");
        System.in.read();
    }
}
