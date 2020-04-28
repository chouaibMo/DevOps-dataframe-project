/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.operations;

import fr.devops.Exceptions.NotaNumberException;
import fr.devops.dataframe.Column;

/**
 * This class contains statistic functions applicable to a numeric column
 * @author chouaib
 */
public final class Statistics {
    
    Statistics() throws Exception{
        throw new Exception("this class should'nt be instantiated.");
    }
    
   /**
    * This method is used to compute the min value of a column.The column should contains number values only (Integer, Double or Float).
    * 
    * @param column column to compute it's min
    * @return the minimum value of the column
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public static double Min(Column column) throws NotaNumberException{
        if( column.isDigit() ){
            double min = Float.MAX_VALUE;
            for(int i=0; i<column.getValues().size(); i++)
                if (min > Float.parseFloat(column.getValues().get(i).toString()))
                    min = Float.parseFloat( column.getValues().get(i).toString() );
            //return min;
            double roundOff = Math.round(min* 100.0) / 100.0;
            return roundOff;
        }
        else
            throw new NotaNumberException("column is not a list of numbers");
      }
      
    
   /**
    * This method is used to compute the max value of a column.The column should contains number values only (Integer, Double or Float).
    * 
    * @param column column to compute it's max
    * @return the maximum value of the column
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public static double Max(Column column) throws NotaNumberException{
        if( column.isDigit()){
            double max = Float.MIN_VALUE;
            for(int i=0; i<column.getValues().size(); i++)
                if (max < Float.parseFloat(column.getValues().get(i).toString()))
                  max = Float.parseFloat( column.getValues().get(i).toString() );
            //return max;
            double roundOff = Math.round(max* 100.0) / 100.0;
            return roundOff;
        }
        else
            throw new NotaNumberException("column is not a list of numbers");
    }
      
    
   /**
    * This method is used to compute the sum value of a column.The column should contains number values only (Integer, Double or Float).
    * 
    * @param column column to compute it's sum
    * @return the sum of column values
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public static double Sum(Column column) throws NotaNumberException{
        if( column.isDigit()){
            double sum = 0;
            for(int i=0; i<column.getValues().size(); i++)
                    sum += Double.parseDouble( column.getValues().get(i).toString() );
            //return sum;
            double roundOff = Math.round(sum* 100.0) / 100.0;
            return roundOff;
        }
        else
            throw new NotaNumberException("column is not a list of numbers");
    }
      
    
   /**
    * This method is used to compute the average value of a column.The column should contains number values only (Integer, Double or Float).
    * 
    * @param column column to compute it's average
    * @return the average of column values
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public static double Mean(Column column) throws NotaNumberException {
        if( column.isDigit()){
            double sum = 0;
            for(int i=0; i<column.getValues().size(); i++)
                    sum += Double.parseDouble( column.getValues().get(i).toString() );
            
            double roundOff = Math.round(sum/column.getValues().size()* 100.0) / 100.0;
            return roundOff;
        }
        else
            throw new NotaNumberException("column is not a list of numbers");
    }
    
    

}
