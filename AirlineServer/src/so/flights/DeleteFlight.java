/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.flights;

import db.DatabaseBroker;
import domain.Airplane;
import domain.Airport;
import domain.Flight;
import domain.GeneralEntity;
import domain.Passenger;
import domain.Reservation;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import so.passengers.DeletePassenger;
import transfer.Response;

/**
 *
 * @author User
 */
public class DeleteFlight extends AbstractGenericOperation {

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response response = new Response();
        try {
            Flight f = (Flight) entity;
            Reservation r = new Reservation(-1, 0, 0.0, 0.0, 0.0, 
                    new Flight(f.getFlightID(), null, null, 0.0, 0, 0, 
                            new Airplane(-1, null, 0, null), 
                            new Airport(-1, null, null, null),
                            new Airport(-1, null, null, null)), 
                    new Passenger(null, null, null, 0));
            
            List<GeneralEntity> list = r.getList(DatabaseBroker.getInstance().select(r));
            
            for (GeneralEntity gen : list) {
                r = (Reservation) gen;
                if(r.getFlight().getFlightID().equals(f.getFlightID())){
                    int collectedPoints = r.getPassenger().getCollectedPoints() - r.getFlight().getPoints();
                    r.getPassenger().setCollectedPoints(collectedPoints);
                    DatabaseBroker.getInstance().update(r.getPassenger());
                
                    DatabaseBroker.getInstance().delete(r);
                }
            }
            
            DatabaseBroker.getInstance().delete(entity);
            response.setData(null);
            response.setSuccessful(true);
            response.setMessage("System has deleted the flight.");
        } catch (SQLException ex) {
            Logger.getLogger(DeletePassenger.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System has not deleted the flight.");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity entity) throws Exception {
    }
    
}
