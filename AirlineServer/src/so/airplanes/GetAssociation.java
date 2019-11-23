/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.airplanes;

import db.DatabaseBroker;
import domain.GeneralEntity;
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
public class GetAssociation extends AbstractGenericOperation{

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response response = new Response();
        try {
            List<GeneralEntity> list = entity.getList(DatabaseBroker.getInstance().select(entity));
            response.setData(list);
            response.setSuccessful(true);
            response.setMessage("System found all associations.");
        } catch (SQLException ex) {
            Logger.getLogger(GetAllAirplanes.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't find any association.");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity entity) throws Exception {
    }
    
}
