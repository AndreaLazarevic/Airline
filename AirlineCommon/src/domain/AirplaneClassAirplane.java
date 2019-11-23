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
public class AirplaneClassAirplane implements GeneralEntity {
    private AirplaneClass airplaneClass;
    private Airplane airplane;

    public AirplaneClassAirplane() {
    }

    public AirplaneClassAirplane(AirplaneClass airplaneClass, Airplane airplane) {
        this.airplaneClass = airplaneClass;
        this.airplane = airplane;
    }

    public AirplaneClass getAirplaneClass() {
        return airplaneClass;
    }

    public void setAirplaneClass(AirplaneClass airplaneClass) {
        this.airplaneClass = airplaneClass;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Airplane:" + airplane.getType() + " ,airplane class:" + airplaneClass.getAirplaneClassName();
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
        final AirplaneClassAirplane other = (AirplaneClassAirplane) obj;
        if (!Objects.equals(this.airplaneClass.getAirplaneClassID(), other.airplaneClass.getAirplaneClassID())) {
            return false;
        }
        
        if (!Objects.equals(this.airplane.getAirplaneID(), other.airplane.getAirplaneID())) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getTableName() {
        return "airplane_class_airplane";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> list = new ArrayList<>();
        while (rs.next()) { 
            Airplane a = new Airplane(rs.getInt("a.airplane_id"), rs.getString("a.type"), rs.getInt("a.capacity"), null);
            AirplaneClass ac = new AirplaneClass(rs.getInt("ac.airplane_class_id"), rs.getString("ac.airplane_class_name"), 
                    rs.getInt("ac.first_seat"), rs.getInt("ac.last_seat"));
            
            list.add(new AirplaneClassAirplane(ac, a));
        }
        
        return list;
    }

    @Override
    public String getColumnNames() {
        return "airplane_class_id, airplane_id";
    }

    @Override
    public String getInsertValues() {
        return " " + airplaneClass.getAirplaneClassID() + ", " + airplane.getAirplaneID() + " ";
    }

    @Override
    public String getUpdateValues() {
        return "";
    }

    @Override
    public String getWhereCondition() {
        return " airplane_class_id="+airplaneClass.getAirplaneClassID()+"and airplane_id="+airplane.getAirplaneID();
    }

    @Override
    public String getSelectColumns() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " aca ";
    }

    @Override
    public String getJoinCondition() {
        return " join airplane_classes ac on aca.airplane_class_id=ac.airplane_class_id join airplanes a on aca.airplane_id=a.airplane_id ";
        //return "";
    }

    @Override
    public String getWhereConditionSelect() {
        /*if(airplaneClass!=null)
            return " where airplane_class_id="+airplaneClass.getAirplaneClassID();
        
        if(airplane!=null)
            return " where airplane_id="+airplane.getAirplaneID();*/
        
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
