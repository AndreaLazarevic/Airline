/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.GeneralEntity;
import forms.ServerMainForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.airplanes.GetAirplaneClasses;
import so.airplanes.GetAllAirplanes;
import so.airplanes.GetAssociation;
import so.airplanes.SaveAirplane;
import so.airports.GetAllAirports;
import so.flights.DeleteFlight;
import so.flights.SaveFlight;
import so.flights.SearchFlights;
import so.flights.UpdateFlight;
import so.passengers.DeletePassenger;
import so.passengers.SavePassenger;
import so.passengers.SearchPassengers;
import so.passengers.UpdatePassenger;
import so.reservations.DeleteReservation;
import so.reservations.SaveReservation;
import so.reservations.SearchReservations;
import so.reservations.UpdateReservation;
import so.users.UserLogInSO;
import transfer.Response;

/**
 *
 * @author User
 */
public class Controller {
    private static Controller instance;
    private ServerMainForm form;

    private Controller() {
    }

    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public ServerMainForm getForm() {
        return form;
    }

    public void setForm(ServerMainForm form) {
        this.form = form;
    }


    public Response userLogIn(GeneralEntity entity){
        return new UserLogInSO().templateExecute(entity);
    }

    public Response getAllPassengers(GeneralEntity entity){
        return new SearchPassengers().templateExecute(entity);
        
    }

    public Response deletePassenger(GeneralEntity entity){
        return new DeletePassenger().templateExecute(entity);
    }

    public Response updatePassenger(GeneralEntity entity) {
        return new UpdatePassenger().templateExecute(entity);
    }

    public Response savePassenger(GeneralEntity entity) {
        return new SavePassenger().templateExecute(entity);
    }

    public Response getAllAirplanes(GeneralEntity entity) {
        return new GetAllAirplanes().templateExecute(entity);
    }

    public Response getAllAirports(GeneralEntity entity) {
        return new GetAllAirports().templateExecute(entity);
    }

    public Response getAllFlights(GeneralEntity entity) {
        return new SearchFlights().templateExecute(entity);
    }

    public Response SaveFlight(GeneralEntity entity) {
        return new SaveFlight().templateExecute(entity);
    }

    public Response UpdateFlight(GeneralEntity entity) {
        return new UpdateFlight().templateExecute(entity);
    }

    public Response DeleteFlight(GeneralEntity entity) {
        return new DeleteFlight().templateExecute(entity);
    }

    public Response GetAirplaneClasses(GeneralEntity entity) {
        return new GetAirplaneClasses().templateExecute(entity);
    }

    public Response GetAssociation(GeneralEntity entity) {
        return new GetAssociation().templateExecute(entity);
    }

    public Response getAllReservations(GeneralEntity entity) {
        return new SearchReservations().templateExecute(entity);
    }

    public Response saveReservation(GeneralEntity entity) {
        return new SaveReservation().templateExecute(entity);
    }

    public Response deleteReservation(GeneralEntity entity) {
        return new DeleteReservation().templateExecute(entity);
    }

    public Response updateReservation(GeneralEntity entity) {
        return new UpdateReservation().templateExecute(entity);
    }

    public Response saveAirplane(GeneralEntity entity) {
        return new SaveAirplane().templateExecute(entity);
    }

    
    
    
    
}
