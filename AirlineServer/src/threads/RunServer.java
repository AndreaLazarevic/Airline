/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import db.DBConstants;
import forms.ServerMainForm;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SettingsLoader;

/**
 *
 * @author User
 */
public class RunServer extends Thread {
    ServerMainForm form;
    ServerSocket serverSocket;
    static List<ClientThread> clientsList;


    public RunServer(ServerMainForm form) {
        this.form = form;
        clientsList = new ArrayList<>();
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    
    @Override
    public void run() {
        try {
            int port = Integer.parseInt(SettingsLoader.getInstance().getValue(DBConstants.PORT));    
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running...");
            form.serverRunning();
            
            new StopServer(this).start();
                        
            while (isInterrupted() == false) {    
                Socket socket = serverSocket.accept();
                System.out.println("Client is connected...");
                ClientThread clientThread = new ClientThread(socket);
                clientsList.add(clientThread);
                clientThread.start();

            }
        } catch (IOException ex) {
            System.out.println("Server is stopped.");
            form.serverNotRunning();
            stoppedServer();
        }
    }

    public void stoppedServer() {
        try {
            serverSocket.close();
            for (ClientThread clientThread : clientsList) {
                clientThread.getSocket().close();
            }
        } catch (IOException ex) {
            Logger.getLogger(RunServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server is stopped.");
            //form.serverNotRunning();
        }
    }
 
}
