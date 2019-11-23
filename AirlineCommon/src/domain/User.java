/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class User implements GeneralEntity{
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(int userID, String username, String password, String firstName, String lastName, String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", name:" + firstName + " " + lastName;
    }
    
    

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> users = new ArrayList<>();
        while (rs.next()) {            
            users.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("pass"), rs.getString("first_name"), 
                    rs.getString("last_name"), rs.getString("email")));
        }
        
        return users;
    }

    @Override
    public String getColumnNames() {
        return "user_id, username, pass, first_name, last_name, email";
    }

    @Override
    public String getInsertValues() {
        return "";
    }

    @Override
    public String getUpdateValues() {
        return "";
    }

    @Override
    public String getWhereCondition() {
        return "";
    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " u ";
    }

    @Override
    public String getJoinCondition() {
        return "";
    }

    @Override
    public String getWhereConditionSelect() {
        return " where username='" + username + "' and pass='" + password + "' LIMIT 1";
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
        int hash = 5;
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
