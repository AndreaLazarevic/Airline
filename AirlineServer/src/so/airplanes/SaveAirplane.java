/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.airplanes;

import db.DatabaseBroker;
import domain.Airplane;
import domain.AirplaneClass;
import domain.AirplaneClassAirplane;
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
public class SaveAirplane extends AbstractGenericOperation{

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response res = new Response();
        try {
            DatabaseBroker.getInstance().insert(entity);
            System.out.println("Airplane inserted");
            Airplane a = (Airplane) entity;
            a.setAirplaneID(DatabaseBroker.getInstance().getID(entity));
            for (AirplaneClass ac : a.getAirplaneClasses()) {
                AirplaneClassAirplane aca = new AirplaneClassAirplane(ac, a);
                DatabaseBroker.getInstance().insert(aca);
                System.out.println("Association inserted");
            }
            res.setData(null);
            res.setSuccessful(true);
            res.setMessage("System successfully saved the airplane.");
        } catch (SQLException ex) {
            Logger.getLogger(SaveAirplane.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't save the airplane.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity entity) throws Exception {
    }
    
}
