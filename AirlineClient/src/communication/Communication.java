/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author User
 */
public class Communication {
    private static Communication instance;
    Socket s;

    private Communication() {
        try {
            s = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Communication getInstance() {
        if(instance == null)
            instance = new Communication();
        return instance;
    }
    
    public void sendRequest(Request request){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(request);
        } catch (Exception ex) {
            System.out.println("Can't send request.");

            JOptionPane.showMessageDialog(null, "Server is down!");
            System.exit(0);
        }
    }
    
    public Response getResponse(){
        Response response = new Response();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            response = (Response) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Can't get the response from server.");

            JOptionPane.showMessageDialog(null, "Server is down!");
            System.exit(0);
        }
        
        return response;
    }
}
