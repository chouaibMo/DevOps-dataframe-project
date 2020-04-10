/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chouaib
 * @param <T>
 */
public class Column<T> {
    
    private String colName;
    private String colType;
    private List<T> values;
    
    
    public Column(String colname, String type, List<T> values){
        this.colName = colname;
        this.colType = type;
        this.values = values;
    }

    public String getColName() {
        return colName;
    }

    public String getColType() {
        return colType;
    }

    public List<T> getValues() {
        return values;
    }
    
}
