/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.flights;

import db.DatabaseBroker;
import domain.GeneralEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author User
 */
public class UpdateFlight extends AbstractGenericOperation {

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response response = new Response();
        try {
            DatabaseBroker.getInstance().update(entity);
            response.setData(null);
            response.setSuccessful(true);
            response.setMessage("System successfully updated the flight.");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFlight.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't update the flight.");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity entity) throws Exception {
    }
    
}
