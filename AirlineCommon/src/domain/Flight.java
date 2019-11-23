/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Flight implements GeneralEntity {
    private Integer flightID;
    private Date departureTime;
    private String status; //delayed, canceled, on time
    private Double price;
    private Integer points;
    private Integer takenSeats;
    private Airplane airplane;
    private Airport airportDeparture;
    private Airport airportArrival;
     
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
    public Flight() {
    }

    public Flight(Integer flightID, Date departureTime, String status, Double price, Integer points, Integer takenSeats, Airplane airplane, Airport airportDeparture, Airport airportArrival) {
        this.flightID = flightID;
        this.departureTime = departureTime;
        this.status = status;
        this.price = price;
        this.points = points;
        this.takenSeats = takenSeats;
        this.airplane = airplane;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Integer getTakenSeats() {
        return takenSeats;
    }

    public void setTakenSeats(Integer takenSeats) {
        this.takenSeats = takenSeats;
    }

    
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public Airport getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(Airport airportArrival) {
        this.airportArrival = airportArrival;
    }

    @Override
    public String toString() {
        return "" + airportDeparture.getAirportName() + " - " + airportArrival.getAirportName()+ 
                ", " + sdf.format(departureTime) + ", " + status;
    }
    
    @Override
    public String getTableName() {
        return "flights";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> flights = new ArrayList<>();
        while (rs.next()) { 
            Airplane a = new Airplane(rs.getInt("a.airplane_id"), rs.getString("a.type"), rs.getInt("a.capacity"), null);
            Airport aDeparture = new Airport(rs.getInt("a1.airport_id"), rs.getString("a1.airport_name"), rs.getString("a1.state"), rs.getString("a1.city"));
            Airport aArrival = new Airport(rs.getInt("a2.airport_id"), rs.getString("a2.airport_name"), rs.getString("a2.state"), rs.getString("a2.city"));
            
            flights.add(new Flight(rs.getInt("flight_id"), new Date(rs.getTimestamp("departure_time").getTime()), rs.getString("status"), 
                    rs.getDouble("price"), rs.getInt("points"), rs.getInt("taken_seats"), a, aDeparture, aArrival));
        }
        
        return flights;
    }

    @Override
    public String getColumnNames() {
        return "departure_time, status, price, points, taken_seats, airplane_id, airport_departure_id, airport_arrival_id";
    }

    @Override
    public String getInsertValues() {
        return "'"+new java.sql.Timestamp(departureTime.getTime())+"', '"+status+"', "+price+", "+points+", " + takenSeats + ", "+airplane.getAirplaneID()+", "
                +airportDeparture.getAirportID()+", "+airportArrival.getAirportID();
    }

    @Override
    public String getUpdateValues() {
        return " departure_time='"+new java.sql.Timestamp(departureTime.getTime())+"',status='"+status+"',price="+price+",points="+points+
                " ,taken_seats=" + takenSeats +
                ",airplane_id="+airplane.getAirplaneID()+",airport_departure_id="+airportDeparture.getAirportID()+
                ",airport_arrival_id="+airportArrival.getAirportID()+" ";
    }

    @Override
    public String getWhereCondition() {
        return " flight_id="+flightID;

    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " f ";
    }

    @Override
    public String getJoinCondition() {
        return "join airplanes a on f.airplane_id=a.airplane_id join airports a1 on f.airport_departure_id=a1.airport_id "
                + "join airports a2 on f.airport_arrival_id=a2.airport_id";
    }

    @Override
    public String getWhereConditionSelect() {       
        if(airportArrival.getAirportName() != null && airportArrival.getCity() != null && airportArrival.getState() != null)
            return " where a2.airport_name LIKE '%"+ airportArrival.getAirportName() + "%' OR a2.state LIKE '%" + airportArrival.getState()
                    + "%' OR a2.city LIKE '%" + airportArrival.getCity() + "%'";
        
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.flightID, other.flightID)) {
            return false;
        }
        /*if (!Objects.equals(this.getDepartureTime(), other.getDepartureTime())) {
            return false;
        }
        if (!Objects.equals(this.airplane.getAirplaneID(), other.airplane.getAirplaneID())) {
            return false;
        }
        if (!Objects.equals(this.airportDeparture.getAirportID(), other.airportDeparture.getAirportID())) {
            return false;
        }
        if (!Objects.equals(this.airportArrival.getAirportID(), other.airportArrival.getAirportID())) {
            return false;
        }*/
        return true;
    }
    
    
}
