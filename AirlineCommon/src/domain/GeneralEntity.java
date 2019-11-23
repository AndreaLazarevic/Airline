/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface GeneralEntity extends Serializable{
    public String getTableName();
    
    public String getColumnNames();
    
    public String getInsertValues();
    
    public String getUpdateValues();
    
    public String getWhereCondition();
    
    public String getSelectColumns();
    
    public String getAlias();
    
    public String getJoinCondition();
    
    public String getWhereConditionSelect();
    
    public String getGroupBy();

    public List<GeneralEntity> getList(ResultSet resultSet) throws SQLException;
        
    public String getMaxPrimary();
}
