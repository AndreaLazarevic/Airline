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
public class SavePassenger extends AbstractGenericOperation{

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response response = new Response();
        try {
            Passenger p = (Passenger) entity;
                        
            List<GeneralEntity> list = p.getList(DatabaseBroker.getInstance().select(p));
            
            if(list.isEmpty()){
                DatabaseBroker.getInstance().insert(entity);
                response.setData(null);
                response.setSuccessful(true);
                response.setMessage("System successfully saved new passenger.");
            } else {
                response.setData(null);
                response.setSuccessful(false);
                response.setMessage("Passenger with provided passport number already exists.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavePassenger.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System failed to save new passenger.");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }
    
}
