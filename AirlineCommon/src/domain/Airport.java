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
public class Airport implements GeneralEntity{
    private Integer airportID;
    private String airportName;
    private String state;
    private String city;

    public Airport() {
    }

    public Airport(Integer airportID, String airportName, String state, String city) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.state = state;
        this.city = city;
    }

    public Integer getAirportID() {
        return airportID;
    }

    public void setAirportID(Integer airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return airportName + "," + state + "," + city;
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
        final Airport other = (Airport) obj;
        if (!Objects.equals(this.airportID, other.airportID)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String getTableName() {
        return "airports";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> airports = new ArrayList<>();
        while (rs.next()) { 
            airports.add(new Airport(rs.getInt("airport_id"), rs.getString("airport_name"), rs.getString("state"), rs.getString("city")));
        }
        
        return airports;
    }

    @Override
    public String getColumnNames() {
        return "airport_id, airport_name, state, city";
    }

    @Override
    public String getInsertValues() {
        return "'"+airportID+","+airportName+","+state+","+city+"'";

    }

    @Override
    public String getUpdateValues() {
        return " airport_name="+airportName+",state="+state+",city="+city+"'";
    }

    @Override
    public String getWhereCondition() {
        return " airport_id="+airportID;

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
        return "";
    }
    
}
