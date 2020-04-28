/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Column object
 * 
 * @author chouaib
 * @param <T> a type that extends Object
 */
@SuppressWarnings("unchecked")
public class Column<T extends Object>{
    
    private String name;
    private String type;
    private List<T> values;
    
    
    /**
     * Create a Column instance from a list of values
     * @param colname column name string 
     * @param type the type of column values
     * @param values values of the column as a list
     */
    public Column(String colname, String type, List<T> values){
        this.name = colname;
        this.type = type;
        this.values = values;
    }
    
    /**
     * Create an empty Column instance from a list of values
     * @param colname column name string 
     * @param type the type of column values
     */
    public Column(String colname, String type){
        this.name = colname;
        this.type = type;
        this.values = new ArrayList<>();
    }
    
    /**
     * This method is used to get the size of the column
     * @return the number of values the column contains
     */
    public int size(){
        return this.values.size();
    }
    
    /**
     * This methode is used to get the name of a Column object
     * @return the name of a Column
     */
    public String getName() {
        return name;
    }
    
    /**
     * This methode is used to get the type of a Column object
     * @return the type of a Column
     */
    public String getType() {
        return type;
    }
    
    /**
     * This methode is used to get the values of a Column object
     * @return a list of objects that represents the columns values
     */
    public List<T> getValues() {
        return values;
    }

    /**
     * This methode is used to test whether the Column is a column of numeric values
     * @return true if the column contains a list of numeric values, false otherwise
     */
    public boolean isDigit(){
        return( ( this.getType().equals("Integer")) ||
                ( this.getType().equals("Double" )) ||
                ( this.getType().equals("Float"  ))       
            );
    }
    
    /**
     * This methode is used to test whether the Column contains a value
     * @param arg value to check whether is contained in the column
     * @return true if the column contains the values arg, false otherwise
     */
    public boolean contains(T arg){
        return values.contains(arg);
        
    }
    
    /**
     * This method is used to add a value to the Column
     * @param val the value to add
     */
    public void addValue(T val){
        this.values.add(val);
    }
    
}
