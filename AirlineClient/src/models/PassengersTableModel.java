/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.Passenger;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class PassengersTableModel extends AbstractTableModel {
    List<Passenger> passengers;

    public PassengersTableModel(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    
    
    @Override
    public int getRowCount() {
        return passengers.size();                
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Passenger p = passengers.get(row);
        
        switch(column){
            case 0: return p.getPassportNo();
            case 1: return p.getFirstName();
            case 2: return p.getLastName();
            case 3: return p.getCollectedPoints();
            
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Passport No";
            case 1: return "First name";
            case 2: return "Last name";
            case 3: return "Collected points";
            
            default: return "";
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
        fireTableDataChanged();
    }
    
    public Passenger getPassenger(int row){
        return passengers.get(row);
    }
    
    
    
}
