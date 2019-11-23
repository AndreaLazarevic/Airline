/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.Reservation;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ReservationsTableModel extends AbstractTableModel {
    List<Reservation> reservations;

    public ReservationsTableModel(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    
    
    @Override
    public int getRowCount() {
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Reservation r = reservations.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");

        /*System.out.println(r.getFlight().getAirportDeparture() + " - " + r.getFlight().getAirportArrival()
        + ", " + sdf.format(r.getFlight().getDepartureTime()) + ", " + r.getFlight().getStatus());*/
        
        switch(column){
            case 0: return r.getReservationID();
            case 1: return r.getPassenger();
            case 2: return r.getFlight();
            case 3: return r.getSeatNumber();
            case 4: return r.getPriceWithDiscount();
            
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Passenger";
            case 2: return "Flight";
            case 3: return "Seat no.";
            case 4: return "Price with discount (€)";
            
            default: return "";
        }
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        fireTableDataChanged();
    }
    
    public Reservation getReservation(int row){
        return reservations.get(row);
    }
    
}
