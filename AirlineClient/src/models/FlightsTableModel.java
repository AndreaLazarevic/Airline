/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.Flight;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class FlightsTableModel extends AbstractTableModel {
    List<Flight> flights;

    public FlightsTableModel(List<Flight> flights) {
        this.flights = flights;
    }

    
    @Override
    public int getRowCount() {
        return flights.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Flight f = flights.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
        
        switch(column){
            case 0: return f.getFlightID();
            case 1: return sdf.format(f.getDepartureTime());
            case 2: return f.getStatus();
            case 3: return f.getPrice();
            case 4: return f.getAirportDeparture();
            case 5: return f.getAirportArrival();
            
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Departure time";
            case 2: return "Status";
            case 3: return "Price (€)";
            case 4: return "Airport departure";
            case 5: return "Airport arrival";
            
            default: return "";
        }
    }

    public List<Flight> getFlights() {
        return flights;
    }
    
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
        fireTableDataChanged();
    }
    
    public Flight getFlight(int row){
        return flights.get(row);
    }
}
