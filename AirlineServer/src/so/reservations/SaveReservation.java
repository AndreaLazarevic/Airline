/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.reservations;

import db.DatabaseBroker;
import domain.GeneralEntity;
import domain.Reservation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author User
 */
public class SaveReservation extends AbstractGenericOperation {

    @Override
    public Response execute(GeneralEntity entity) throws Exception {
        Response res = new Response();
        try {
            DatabaseBroker.getInstance().insert(entity);
            Reservation r = (Reservation) entity;
            DatabaseBroker.getInstance().update(r.getPassenger());
            DatabaseBroker.getInstance().update(r.getFlight());
            res.setData(null);
            res.setSuccessful(true);
            res.setMessage("System successfully saved the reservation.");
        } catch (SQLException ex) {
            Logger.getLogger(SaveReservation.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't save the reservation.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity entity) throws Exception {
    }
    
}
