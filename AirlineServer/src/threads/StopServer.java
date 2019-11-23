/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author User
 */
public class StopServer extends Thread {
    boolean end = false;
    RunServer runServer;

    public StopServer(RunServer runServer)
    {
        this.runServer = runServer;
    }
    
    @Override
    public void run()
    {
        while(!end)
        {
            if(runServer.isInterrupted())
            {               
                    runServer.stoppedServer();
                    end = true;  
            }
        }
    }   
}
