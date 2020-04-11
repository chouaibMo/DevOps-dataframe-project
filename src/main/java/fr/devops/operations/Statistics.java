/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.operations;

import fr.devops.dataframe.Dataframe;
import fr.devops.utils.Column;
import fr.devops.utils.Data;
import java.util.Collections;

/**
 *
 * @author chouaib
 */
public abstract class Statistics {
    
    public static double Min(Column column) throws Exception{
        if( column.getValues().get(0) instanceof Integer || 
            column.getValues().get(0) instanceof Double  ||
            column.getValues().get(0) instanceof Float ){
            double min = Float.MAX_VALUE;
            for(int i=0; i<column.getValues().size(); i++)
                if (min > Float.parseFloat(column.getValues().get(i).toString()))
                    min = Float.parseFloat( column.getValues().get(i).toString() );
            return min;
        }
        else
            throw new Exception("bad column type");
      }
      
    public static double Max(Column column) throws Exception{
        if( column.getValues().get(0) instanceof Integer || 
            column.getValues().get(0) instanceof Double  ||
            column.getValues().get(0) instanceof Float ){
          double max = Float.MIN_VALUE;
          for(int i=0; i<column.getValues().size(); i++)
              if (max < Float.parseFloat(column.getValues().get(i).toString()))
                  max = Float.parseFloat( column.getValues().get(i).toString() );
          return max;
      }
      else
          throw new Exception("bad column type");
    }
      
    public static double Sum(Column column) throws Exception{
        if( column.getValues().get(0) instanceof Integer || 
            column.getValues().get(0) instanceof Double  ||
            column.getValues().get(0) instanceof Float ){
            double sum = 0;
            for(int i=0; i<column.getValues().size(); i++)
                    sum += Double.parseDouble( column.getValues().get(i).toString() );
            return sum;
        }
        else
            throw new Exception("bad column type");
    }
      
    public static double Mean(Column column) throws Exception {
        if( column.getValues().get(0) instanceof Integer || 
            column.getValues().get(0) instanceof Double  ||
            column.getValues().get(0) instanceof Float ){
            double sum = 0;
            for(int i=0; i<column.getValues().size(); i++)
                    sum += Double.parseDouble( column.getValues().get(i).toString() );
            return sum / column.getValues().size();
        }
        else
            throw new Exception("bad column type");
    }
}
