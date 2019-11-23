/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.passengers;

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
import transfer.Response;

/**
 *
 * @author User
 */
public class DeletePassenger extends AbstractGenericOperation{

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response response = new Response();
        try {
            Passenger p = (Passenger) entity;
            Reservation r = new Reservation(-1, 0, 0.0, 0.0, 0.0, 
                    new Flight(-1, null, null, 0.0, 0, 0, 
                            new Airplane(-1, null, 0, null), 
                            new Airport(-1, null, null, null),
                            new Airport(-1, null, null, null)), 
                    new Passenger(p.getPassportNo(), null, null, 0));
            
            List<GeneralEntity> list = r.getList(DatabaseBroker.getInstance().select(r));
            
            for (GeneralEntity gen : list) {
                r = (Reservation) gen;
                                
                if(r.getPassenger().getPassportNo().equals(p.getPassportNo())){
                    int takenSeats = r.getFlight().getTakenSeats() - 1;
                    r.getFlight().setTakenSeats(takenSeats);
                    DatabaseBroker.getInstance().update(r.getFlight());
                
                    DatabaseBroker.getInstance().delete(r);   
                }
            }
            
            DatabaseBroker.getInstance().delete(entity);
            response.setData(null);
            response.setSuccessful(true);
            response.setMessage("System has deleted the passenger.");
        } catch (SQLException ex) {
            Logger.getLogger(DeletePassenger.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System has not deleted the passenger.");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }
    
}
