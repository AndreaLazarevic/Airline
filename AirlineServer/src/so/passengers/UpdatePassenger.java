/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.passengers;

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
public class UpdatePassenger extends AbstractGenericOperation {

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response res = new Response();
        try {
            DatabaseBroker.getInstance().update(entity);
            res.setData(null);
            res.setSuccessful(true);
            res.setMessage("System updated the passenger.");
        } catch (SQLException ex) {
            Logger.getLogger(UpdatePassenger.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't update the passenger.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }
    
}
