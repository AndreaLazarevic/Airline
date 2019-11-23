/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import domain.Airplane;
import domain.AirplaneClass;
import domain.AirplaneClassAirplane;
import domain.Airport;
import domain.Flight;
import domain.Passenger;
import domain.Reservation;
import domain.User;
import forms.ClientMainForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;

/**
 *
 * @author User
 */
public class Controller {
    private static Controller instance;
    List<Passenger> passengersList;
    List<Flight> flightsList;
    List<Reservation> reservationsList;
    ClientMainForm clientMainForm;
    
    private Controller() {
    }

    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }
    
    public ClientMainForm getClientMainForm() {
        return clientMainForm;
    }

    public void setClientMainForm(ClientMainForm clientMainForm) {
        this.clientMainForm = clientMainForm;
    }

    public boolean userLogIn(User u) {
        Request request = new Request();
        request.setOperation(Operation.LOGIN);
        request.setData(u);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        List<User> usersList = (List<User>) response.getData();
        JOptionPane.showMessageDialog(null,response.getMessage());
        if(usersList == null || usersList.isEmpty() || !usersList.contains(u)){
            return false;
        }else{
            for (User user : usersList) {
                if(user.equals(u))
                    Session.getInstance().setCurrentUser(user);
            }
            return true;
        }
    }

    

    public String end() {
        Request request = new Request();
        request.setOperation(Operation.END);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response.getMessage();
    }

    public List<Passenger> getAllPassengers(String filterText) {
        Passenger passenger = new Passenger(filterText, filterText, filterText, 0);
        Request request = new Request();
        request.setOperation(Operation.GET_PASSENGERS);
        request.setData(passenger);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        
        if(!response.isSuccessful()){
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        passengersList = (List<Passenger>) response.getData();
        
        return passengersList;
    }

    public Response deletePassenger(Passenger passenger) {
        Request request = new Request();
        request.setOperation(Operation.DELETE_PASSENGER);
        request.setData(passenger);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public Response savePassenger(Passenger p) {
        Request request = new Request();
        request.setOperation(Operation.SAVE_PASSENGER);
        request.setData(p);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public Response updatePassenger(Passenger p) {
        Request request = new Request();
        request.setOperation(Operation.UPDATE_PASSENGER);
        request.setData(p);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public List<Airplane> getAllAirplanes() {
        Airplane a = new Airplane();
        Request request = new Request();
        request.setData(a);
        request.setOperation(Operation.GET_AIRPLANES);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        if (!response.isSuccessful()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return (List<Airplane>) response.getData();
    }

    public List<Airport> getAllAirports() {
        Airport a = new Airport();
        Request request = new Request();
        request.setData(a);
        request.setOperation(Operation.GET_AIRPORTS);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        if (!response.isSuccessful()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return (List<Airport>) response.getData();
    }

    public List<Flight> getAllFlights(String filterText) {
        Flight flight = new Flight(-1, null, null, 0.0, 0, 0, null, null,
                new Airport(-1, filterText, filterText, filterText));
        Request request = new Request();
        request.setOperation(Operation.GET_FLIGHTS);
        request.setData(flight);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        
        if(!response.isSuccessful()){
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        
        flightsList = (List<Flight>) response.getData();
        
        return flightsList;
    }

    public Response saveFlight(Flight f) {
        Request request = new Request();
        request.setOperation(Operation.SAVE_FLIGHT);
        request.setData(f);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public Response updateFlight(Flight f) {
        Request request = new Request();
        request.setOperation(Operation.UPDATE_FLIGHT);
        request.setData(f);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public Response deleteFlight(Flight flight) {
        Request request = new Request();
        request.setOperation(Operation.DELETE_FLIGHT);
        request.setData(flight);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public List<AirplaneClass> getAllAirplaneClasses() {
        AirplaneClass ac = new AirplaneClass();
        Request request = new Request();
        request.setData(ac);
        request.setOperation(Operation.GET_AIRPLANE_CLASSES);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        if (!response.isSuccessful()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return (List<AirplaneClass>) response.getData();
    }

    public List<AirplaneClassAirplane> getAssociation() {
        AirplaneClassAirplane aca = new AirplaneClassAirplane();
        Request request = new Request();
        request.setData(aca);
        request.setOperation(Operation.GET_ASSOCIATION);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        if (!response.isSuccessful()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return (List<AirplaneClassAirplane>) response.getData();
    }

    public List<Reservation> getAllReservations(String filterText) {
        Reservation reservation = new Reservation(-1, 0, 0.0, 0.0, 0.0, new Flight(-1, null, null, 0.0, 0, 0, null, null, 
                new Airport(-1, filterText, filterText, filterText)), 
                new Passenger(filterText, filterText, filterText, 0));
        
        Request request = new Request();
        request.setOperation(Operation.GET_RESERVATIONS);
        request.setData(reservation);
        
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        
        if(!response.isSuccessful()){
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        
        reservationsList = (List<Reservation>) response.getData();
        
        
        return reservationsList;
    }

    public Response saveReservation(Reservation r) {
        Request request = new Request();
        request.setOperation(Operation.SAVE_RESERVATION);
        request.setData(r);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    
    public Response deleteReservation(Reservation reservation) {
        Request request = new Request();
        request.setOperation(Operation.DELETE_RESERVATION);
        request.setData(reservation);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public Response updateReservation(Reservation r) {
        Request request = new Request();
        request.setOperation(Operation.UPDATE_RESERVATION);
        request.setData(r);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    public Response saveAirplane(Airplane a) {
        Request request = new Request();
        request.setOperation(Operation.SAVE_AIRPLANE);
        request.setData(a);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().getResponse();
        return response;
    }

    

    
    
    
}
