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
public class Passenger implements GeneralEntity {
    String passportNo; //length 8
    String firstName;
    String lastName;
    int collectedPoints;

    public Passenger() {
    }

    public Passenger(String passportNo, String firstName, String lastName, int collectedPoints) {
        this.passportNo = passportNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.collectedPoints = collectedPoints;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCollectedPoints() {
        return collectedPoints;
    }

    public void setCollectedPoints(int collectedPoints) {
        this.collectedPoints = collectedPoints;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    
    @Override
    public String getTableName() {
        return "passengers";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> passengers = new ArrayList<>();
        while (rs.next()) { 
            passengers.add(new Passenger(rs.getString("passport_no"), rs.getString("first_name"), rs.getString("last_name"), 
                    rs.getInt("collected_points")));
        }
        
        return passengers;
    }

    @Override
    public String getColumnNames() {
        return "passport_no, first_name, last_name, collected_points";
    }

    @Override
    public String getInsertValues() {
        return "'"+passportNo+"','"+firstName+"','"+lastName+"',"+collectedPoints;

    }

    @Override
    public String getUpdateValues() {
        return "first_name='"+firstName+"', last_name='"+lastName+"' ,collected_points="+collectedPoints;
    }

    @Override
    public String getWhereCondition() {
        return "passport_no='"+passportNo +"'";
    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " p ";
    }

    @Override
    public String getJoinCondition() {
        return "";
    }

    @Override
    public String getWhereConditionSelect() {
        if(passportNo!=null && firstName!= null && lastName != null){
            return " where passport_no LIKE '%"+passportNo+"%' OR first_name LIKE '%"+firstName+"%' OR last_name LIKE '%"+lastName+"%'";
        }
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
        final Passenger other = (Passenger) obj;
        if (!Objects.equals(this.passportNo, other.passportNo)) {
            return false;
        }
        return true;
    }
    
    
    
}
