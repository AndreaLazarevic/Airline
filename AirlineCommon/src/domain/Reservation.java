/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Reservation implements GeneralEntity {
    private Integer reservationID;
    private Integer seatNumber;
    private Double price;
    private Double discount;
    private Double priceWithDiscount;
    private Flight flight;
    private Passenger passenger;

    public Reservation() {
    }

    public Reservation(Integer reservationID, Integer seatNumber, Double price, Double discount, Double priceWithDiscount, Flight flight, Passenger passenger) {
        this.reservationID = reservationID;
        this.seatNumber = seatNumber;
        this.price = price;
        this.discount = discount;
        this.priceWithDiscount = priceWithDiscount;
        this.priceWithDiscount = price - (discount * price) / 100;
        this.flight = flight;
        this.passenger = passenger;
    }

    
    
    
    
    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    
    @Override
    public String getTableName() {
        return "reservations";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> reservations = new ArrayList<>();
        while (rs.next()) {
            Airplane a = new Airplane(rs.getInt("a.airplane_id"), rs.getString("a.type"), rs.getInt("a.capacity"), null);
            Airport aDeparture = new Airport(rs.getInt("a1.airport_id"), rs.getString("a1.airport_name"), rs.getString("a1.state"), rs.getString("a1.city"));
            Airport aArrival = new Airport(rs.getInt("a2.airport_id"), rs.getString("a2.airport_name"), rs.getString("a2.state"), rs.getString("a2.city"));
            
            
            Flight f = new Flight(rs.getInt("f.flight_id"), new Date(rs.getTimestamp("f.departure_time").getTime()), rs.getString("f.status"), 
                    rs.getDouble("f.price"), rs.getInt("f.points"), rs.getInt("f.taken_seats"), a, aDeparture, aArrival);
            Passenger p = new Passenger(rs.getString("p.passport_no"), rs.getString("p.first_name"), rs.getString("p.last_name"), 
                    rs.getInt("p.collected_points"));
            
            reservations.add(new Reservation(rs.getInt("reservation_id"), rs.getInt("seat_number"), rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("price_with_discount"),
                    f, p));
        }
        
        return reservations;
    }

    @Override
    public String getColumnNames() {
        return "seat_number, price, discount, price_with_discount, flight_id, passport_no";
    }

    @Override
    public String getInsertValues() {
        return ""+seatNumber+","+price+","+discount+","+priceWithDiscount+","+flight.getFlightID()+",'"+passenger.getPassportNo()+"'";
    }

    @Override
    public String getUpdateValues() {
        return " seat_number="+seatNumber+", price="+price+", discount="+discount+", price_with_discount="+priceWithDiscount+
                ", flight_id="+flight.getFlightID()+", passport_no='"+passenger.getPassportNo()+"'";

    }

    @Override
    public String getWhereCondition() {
        return " reservation_id="+reservationID;
    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " r ";
    }

    @Override
    public String getJoinCondition() {
        return " join passengers p on r.passport_no=p.passport_no join flights f on r.flight_id=f.flight_id "
                + "join airplanes a on f.airplane_id=a.airplane_id join airports a1 on f.airport_departure_id=a1.airport_id "
                + "join airports a2 on f.airport_arrival_id=a2.airport_id";

    }

    @Override
    public String getWhereConditionSelect() {
        /*if(flight!=null)
            return " where f.flight_id="+flight.getFlightID();
        
        if(passenger!=null)
            return " where p.passport_no="+passenger.getPassportNo();*/
        if(flight.getAirportArrival().getAirportName() != null && flight.getAirportArrival().getCity() != null && 
                flight.getAirportArrival().getState() != null)
            return " where a2.airport_name LIKE '%"+ flight.getAirportArrival().getAirportName() + "%' OR a2.state LIKE '%" + 
                    flight.getAirportArrival().getState()
                    + "%' OR a2.city LIKE '%" + flight.getAirportArrival().getCity() + "%'";
        
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
        int hash = 3;
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
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.reservationID, other.reservationID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(discount == 0)
            return "Reservation ID:" + reservationID + " - " + passenger.firstName + " " + passenger.lastName + ", seat number:" + 
                    seatNumber + ", price: " + price + ", flight: " + flight;
        return "Reservation ID:" + reservationID + " - " + passenger.firstName + " " + passenger.lastName + ", seat number:" + 
                seatNumber + ", price with discount: " + priceWithDiscount + ", flight: " + flight;
    }
    
    
}
