/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.AirplaneClass;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class AirplaneClassesTableModel extends AbstractTableModel {

    List<AirplaneClass> airplaneClasses;

    public AirplaneClassesTableModel(List<AirplaneClass> airplaneClasses) {
        this.airplaneClasses = airplaneClasses;
    }

    public List<AirplaneClass> getAirplaneClasses() {
        return airplaneClasses;
    }

    public void setAirplaneClasses(List<AirplaneClass> airplaneClasses) {
        this.airplaneClasses = airplaneClasses;
        fireTableDataChanged();

    }
    
      
    @Override
    public int getRowCount() {
        return airplaneClasses.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        AirplaneClass ac = airplaneClasses.get(row);
        switch (column) {
            case 0: return ac.getAirplaneClassID();
            case 1: return ac.getAirplaneClassName();

            default: return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Name";
                       
            default: return "";
        }
    }
    
    public AirplaneClass getAirplaneClass(int row) {
        return airplaneClasses.get(row);
    }
    
    public List<AirplaneClass> getSelectedAirplaneClasses(int[] rows) {
        List<AirplaneClass> list = new LinkedList<AirplaneClass>();

        for (int i = 0; i < rows.length; i++) {
            list.add(getAirplaneClass(rows[i]));
        }
        return list;
    }

    public int getIndexOfSelectedAirplaneClass(AirplaneClass ac) {

        for (AirplaneClass airplaneClass : airplaneClasses) {
            if (airplaneClass.getAirplaneClassID()== ac.getAirplaneClassID()) {
                return airplaneClasses.indexOf(airplaneClass);
            }
        }
        return 0;
    }
}
