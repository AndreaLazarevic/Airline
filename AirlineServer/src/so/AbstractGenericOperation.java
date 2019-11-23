/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domain.GeneralEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;

/**
 *
 * @author User
 */
public abstract class AbstractGenericOperation {
    
    public Response templateExecute(GeneralEntity entity){
        Response response = new Response();
        try {
            DatabaseBroker.getInstance().openConnection();
            validate(entity);
            response = execute(entity);
            DatabaseBroker.getInstance().commitTransaction();
        } catch (Exception ex) {
            Logger.getLogger(AbstractGenericOperation.class.getName()).log(Level.SEVERE, null, ex);
            DatabaseBroker.getInstance().rollbackTransaction();
            response.setSuccessful(false);
            response.setMessage(ex.getMessage());
        }finally {
            try {
                DatabaseBroker.getInstance().closeConnection();
            } catch (SQLException ex) {
                response.setSuccessful(false);
                response.setMessage(ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(AbstractGenericOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return response;
    }
    public abstract Response execute(GeneralEntity entity) throws Exception;
    public abstract void validate(GeneralEntity entity) throws Exception;
}
