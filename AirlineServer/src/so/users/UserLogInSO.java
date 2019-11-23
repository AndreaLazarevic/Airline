/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.users;

import db.DatabaseBroker;
import domain.GeneralEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author User
 */
public class UserLogInSO extends AbstractGenericOperation {

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response response = new Response();
        try {
            List<GeneralEntity> list = entity.getList(DatabaseBroker.getInstance().select(entity));
            if(list.isEmpty())
                throw new Exception("Error while trying to log in...");
            response.setData(list);
            response.setSuccessful(true);
            response.setMessage("You have been successfully logged in..");
        } catch (Exception ex) {
            Logger.getLogger(UserLogInSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while trying to log in...");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity entity) throws Exception {
    }
    
}
