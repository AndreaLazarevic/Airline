/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.GeneralEntity;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SettingsLoader;

/**
 *
 * @author User
 */
public class DatabaseBroker implements IDatabaseBroker {

    private static DatabaseBroker instance;
    private Connection connection;

    private DatabaseBroker() {
        try {
            openConnection();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    @Override
    public List<GeneralEntity> getAll(GeneralEntity entity) throws Exception {
        List<GeneralEntity> list;
        try {
            String query = "SELECT * FROM " + entity.getTableName();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void openConnection() throws Exception {
        try {
            loadDriver();
            String url = "jdbc:mysql://localhost:3306/airline?useSSL=false";
            //String url = SettingsLoader.getInstance().getValue(DBConstants.URL);
            String user = SettingsLoader.getInstance().getValue(DBConstants.USERNAME);
            String pass = SettingsLoader.getInstance().getValue(DBConstants.PASSWORD);
            connection = DriverManager.getConnection(url, user, pass);
//            System.out.println("connected");
            connection.setAutoCommit(false);
        } catch (IOException ex) {
            throw new Exception("Error while reading properties file!");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found!");
        } catch (SQLException ex) {
            throw new Exception("Error while trying to connect to database!");
        }
    }
    
    public void closeConnection() throws Exception{
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Error while closing connection!");
        }
    }
    
    public void commitTransaction() throws SQLException {
        connection.commit();
    }

    public void rollbackTransaction(){
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDriver() throws Exception {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(SettingsLoader.getInstance().getValue(DBConstants.DRIVER));
            System.out.println("Driver succesfully loaded!");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error during driver loading!\n" + ex);
        }
    }

    public void insert(GeneralEntity entity) throws SQLException {
        String sql = "INSERT INTO " + entity.getTableName() + "(" + entity.getColumnNames() + ")" + " VALUES (" + entity.getInsertValues() + ")";
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }

    public void update(GeneralEntity entity) throws SQLException {
        String sql = "UPDATE " + entity.getTableName() + " SET " + entity.getUpdateValues() + " WHERE " + entity.getWhereCondition();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }

    public void delete(GeneralEntity entity) throws SQLException {
        String sql = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }

    public ResultSet select(GeneralEntity entity) throws SQLException {
        String sql = "SELECT " + entity.getSelectColumns()+ " FROM " + entity.getTableName() + " as " + entity.getAlias()
                + entity.getJoinCondition() + entity.getWhereConditionSelect() + entity.getGroupBy();
        System.out.println(sql);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return rs;
    }

    public Integer getID(GeneralEntity entity) throws SQLException {
        int max = 0;
        String sql = "SELECT max(" + entity.getMaxPrimary() + ") as maxKey FROM " + entity.getTableName();
        System.out.println(sql);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            max = rs.getInt("maxKey");
        }

        return max;
    }

}
