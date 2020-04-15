/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import java.util.List;

/**
 *
 * @author chouaib
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class Column<T extends Object>{
    
    private String name;
    private String type;
    private List<T> values;
    
    
    public Column(String colname, String type, List<T> values){
        this.name = colname;
        this.type = type;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<T> getValues() {
        return values;
    }

    public boolean isDigit(){
        return( ( this.getType().equals("Integer")) ||
                ( this.getType().equals("Double" )) ||
                ( this.getType().equals("Float"  ))       
            );
    }
    
    public boolean contains(T arg){
        return values.contains(arg);
        
    }
    
    public void addValue(T val){
        this.values.add(val);
    }
}
