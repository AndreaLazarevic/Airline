/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Airplane implements GeneralEntity {
    private Integer airplaneID;
    private String type;
    private Integer capacity;
    private List<AirplaneClass> airplaneClasses;

    public Airplane() {
    }

    public Airplane(Integer airplaneID, String type, Integer capacity, List<AirplaneClass> airplaneClasses) {
        this.airplaneID = airplaneID;
        this.type = type;
        this.capacity = capacity;
        this.airplaneClasses  = airplaneClasses;
    }

    public List<AirplaneClass> getAirplaneClasses() {
        return airplaneClasses;
    }

    public void setAirplaneClasses(List<AirplaneClass> airplaneClasses) {
        this.airplaneClasses = airplaneClasses;
    }
    
    
    
    public Integer getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(Integer airplaneID) {
        this.airplaneID = airplaneID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type;
    }
    
    @Override
    public String getTableName() {
        return "airplanes";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Airplane other = (Airplane) obj;
        if (!Objects.equals(this.airplaneID, other.airplaneID)) {
            return false;
        }
        
        return true;
    }
    
    

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> airplanes = new ArrayList<>();
        while (rs.next()) { 
            airplanes.add(new Airplane(rs.getInt("airplane_id"), rs.getString("type"), rs.getInt("capacity"), null));
            
        }
        
        return airplanes;
    }

    @Override
    public String getColumnNames() {
        return "type, capacity";
    }

    @Override
    public String getInsertValues() {
        return "'"+type+"',"+capacity;

    }

    @Override
    public String getUpdateValues() {
        return " type='"+type+"',capacity="+capacity;
    }

    @Override
    public String getWhereCondition() {
        return " airplane_id="+airplaneID;
    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " a ";
    }

    @Override
    public String getJoinCondition() {
        return "";
    }

    @Override
    public String getWhereConditionSelect() {
        return "";
    }

    @Override
    public String getGroupBy() {
        return "";
    }

    @Override
    public String getMaxPrimary() {
        return " airplane_id ";
    }

    
}
