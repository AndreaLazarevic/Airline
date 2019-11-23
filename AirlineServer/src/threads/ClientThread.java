/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import controller.Controller;
import domain.GeneralEntity;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;

/**
 *
 * @author User
 */
public class ClientThread extends Thread{
    Socket socket;
    boolean end = false;
    

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
    
    

    @Override
    public void run() {
        while (!end) {            
            Request request = getRequest();
            Response response = new Response();
            GeneralEntity entity = (GeneralEntity) request.getData();
            switch(request.getOperation()){
                case Operation.LOGIN:
                    response = Controller.getInstance().userLogIn(entity);
                    break;
               
                case Operation.END:
                    end=true;
                    response.setMessage("System has stopped working.");
                    break;
                    
                case Operation.GET_PASSENGERS:
                    response = Controller.getInstance().getAllPassengers(entity);
                    break;
                    
                case Operation.DELETE_PASSENGER:
                    response = Controller.getInstance().deletePassenger(entity);
                    break;
                    
                case Operation.UPDATE_PASSENGER:
                    response = Controller.getInstance().updatePassenger(entity);
                    break;
                    
                case Operation.SAVE_PASSENGER:
                    response = Controller.getInstance().savePassenger(entity);
                    break;
                    
                case Operation.GET_AIRPLANES:
                    response = Controller.getInstance().getAllAirplanes(entity);
                    break;
            
                case Operation.GET_AIRPORTS:
                    response = Controller.getInstance().getAllAirports(entity);
                    break;
                    
                case Operation.GET_FLIGHTS:
                    response = Controller.getInstance().getAllFlights(entity);
                    break;
                    
                case Operation.SAVE_FLIGHT:
                    response = Controller.getInstance().SaveFlight(entity);
                    break;
                    
                case Operation.UPDATE_FLIGHT:
                    response = Controller.getInstance().UpdateFlight(entity);
                    break;  
                    
                case Operation.DELETE_FLIGHT:
                    response = Controller.getInstance().DeleteFlight(entity);
                    break;
                    
                case Operation.GET_AIRPLANE_CLASSES:
                    response = Controller.getInstance().GetAirplaneClasses(entity);
                    break;
                    
                case Operation.GET_ASSOCIATION:
                    response = Controller.getInstance().GetAssociation(entity);
                    break;
                    
                case Operation.GET_RESERVATIONS:
                    response = Controller.getInstance().getAllReservations(entity);
                    break;    
                
                case Operation.SAVE_RESERVATION:
                    response = Controller.getInstance().saveReservation(entity);
                    break;
                    
                case Operation.DELETE_RESERVATION:
                    response = Controller.getInstance().deleteReservation(entity);
                    break;
                    
                case Operation.UPDATE_RESERVATION:
                    response = Controller.getInstance().updateReservation(entity);
                    break;
                    
                case Operation.SAVE_AIRPLANE:
                    response = Controller.getInstance().saveAirplane(entity);
                    break;    
            }
                
            send(response);

        }
            
    }
    
    public void send(Response response){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(response);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Request getRequest(){
        Request request = new Request();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            request = (Request) ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return request;
    }
}
