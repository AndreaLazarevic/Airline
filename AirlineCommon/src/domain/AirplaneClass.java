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
public class AirplaneClass implements GeneralEntity {
    private Integer airplaneClassID;
    private String airplaneClassName;
    private Integer firstSeat;
    private Integer lastSeat;

    public AirplaneClass() {
    }

    public AirplaneClass(Integer airplaneClassID, String airplaneClassName, Integer firstSeat, Integer lastSeat) {
        this.airplaneClassID = airplaneClassID;
        this.airplaneClassName = airplaneClassName;
        this.firstSeat = firstSeat;
        this.lastSeat = lastSeat;
    }

    public Integer getAirplaneClassID() {
        return airplaneClassID;
    }

    public void setAirplaneClassID(Integer airplaneClassID) {
        this.airplaneClassID = airplaneClassID;
    }

    public String getAirplaneClassName() {
        return airplaneClassName;
    }

    public void setAirplaneClassName(String airplaneClassName) {
        this.airplaneClassName = airplaneClassName;
    }

    public Integer getFirstSeat() {
        return firstSeat;
    }

    public void setFirstSeat(Integer firstSeat) {
        this.firstSeat = firstSeat;
    }

    public Integer getLastSeat() {
        return lastSeat;
    }

    public void setLastSeat(Integer lastSeat) {
        this.lastSeat = lastSeat;
    }

    @Override
    public String toString() {
        return airplaneClassName;
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
        final AirplaneClass other = (AirplaneClass) obj;
        if (!Objects.equals(this.airplaneClassID, other.airplaneClassID)) {
            return false;
        }
        
        return true;
    }
    
    
    @Override
    public String getTableName() {
        return "airplane_classes";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> airplaneClasses = new ArrayList<>();
        while (rs.next()) { 
            airplaneClasses.add(new AirplaneClass(rs.getInt("airplane_class_id"), rs.getString("airplane_class_name"), 
                    rs.getInt("first_seat"), rs.getInt("last_seat")));
        }
        
        return airplaneClasses;
    }

    @Override
    public String getColumnNames() {
        return "airplane_class_id, airplane_class_name, first_seat, last_seat";
    }

    @Override
    public String getInsertValues() {
        return "'"+airplaneClassID+","+airplaneClassName+","+firstSeat+","+lastSeat+"'";
    }

    @Override
    public String getUpdateValues() {
        return " airplane_class_name="+airplaneClassName+",first_seat="+firstSeat+",last_seat="+lastSeat+"'";
    }

    @Override
    public String getWhereCondition() {
        return " airplane_class_id="+airplaneClassID;
    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " ac ";
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
        return "";
    }
    
}
