/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.operations;

import fr.devops.Exceptions.NotaNumberException;
import fr.devops.utils.Column;

/**
 *
 * @author chouaib
 */
public abstract class Statistics {
    
    public static double Min(Column column) throws NotaNumberException{
        if( column.isDigit() ){
            double min = Float.MAX_VALUE;
            for(int i=0; i<column.getValues().size(); i++)
                if (min > Float.parseFloat(column.getValues().get(i).toString()))
                    min = Float.parseFloat( column.getValues().get(i).toString() );
            return min;
        }
        else
            throw new NotaNumberException("column values is not a list of numbers");
      }
      
    public static double Max(Column column) throws NotaNumberException{
        if( column.isDigit()){
            double max = Float.MIN_VALUE;
            for(int i=0; i<column.getValues().size(); i++)
                if (max < Float.parseFloat(column.getValues().get(i).toString()))
                  max = Float.parseFloat( column.getValues().get(i).toString() );
            return max;
        }
        else
            throw new NotaNumberException("column values is not a list of numbers");
    }
      
    public static double Sum(Column column) throws NotaNumberException{
        if( column.isDigit()){
            double sum = 0;
            for(int i=0; i<column.getValues().size(); i++)
                    sum += Double.parseDouble( column.getValues().get(i).toString() );
            return sum;
        }
        else
            throw new NotaNumberException("column values is not a list of numberse");
    }
      
    public static double Mean(Column column) throws NotaNumberException {
        if( column.isDigit()){
            double sum = 0;
            for(int i=0; i<column.getValues().size(); i++)
                    sum += Double.parseDouble( column.getValues().get(i).toString() );
            
            double roundOff = Math.round(sum/column.getValues().size()* 100.0) / 100.0;
            return roundOff;
        }
        else
            throw new NotaNumberException("column values is not a list of numbers");
    }
    
    

}
