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
    
      public static Double Min(Column column){
          return (Double) Collections.min(column.getValues());
      }
      
      public static Double Max(Column column){
          return (Double) Collections.max(column.getValues());
      }
      
      public static Double Sum(Column column){
          Double sum = 0.;
          for(int i=0; i<column.getValues().size(); i++)
              sum += (Double) column.getValues().get(i);
          return sum;
      }
      
      public static Double Mean(Column column){
          Double mean = 0.;
          for(int i=0; i<column.getValues().size(); i++)
              mean += (Double) column.getValues().get(i);
          return mean/column.getValues().size();
      }
}
