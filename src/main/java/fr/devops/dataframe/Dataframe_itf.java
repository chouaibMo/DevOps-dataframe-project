/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.operations.Arithmetics;
import fr.devops.utils.Column;
import fr.devops.operations.Statistics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chouaib
 */
public interface Dataframe_itf{
    
    

    public int size();   
    
    
  /**
    * This method is used to get the list of labels of the Dataframe.
    * Each label represents the name of a Dataframe's column.
    * 
    * @return List<String> the list of Dataframe's labels.
    * @See Column
    */
    public List<String> columns();
    
    
  /**
    * This method is used to display all the rows of a Dataframe.
    * The rows are printed line by line in stdout.
    */
    public void fetchAll();
    
  /**
    * This method is used to display the rows of a Dataframe between start and end.
    * The rows are printed line by line in stdout.
    * 
    * @param start the row where display is started
    * @param end  the row where .
    * @exception Exception if nbLine is invalid 
    */
    public void fetchFromTo(int start, int end) throws Exception;
    
  /**
    * This method is used to display the first n rows of a Dataframed.
    * The rows are printed line by line in stdout.
    * 
    * @param nbLine the number row to display
    * @exception Exception if nbLine is invalid 
    */
    public void head(int nbline) throws Exception;
    
  /**
    * This method is used to display the last n rows of a Dataframed.
    * The rows are printed line by line in stdout.
    * 
    * @param nbLine the number row to display
    * @exception Exception if nbLine is invalid 
    */
    public void tail(int nbline) throws Exception;
    
    
  /**
    * This method is used to insert a row in a Dataframed.
    * 
    * @param row the row to insert
    * @exception Exception
    */
    public void insertRow(List<Object> row) throws Exception;
    
  /**
    * This method is used to insert a column in a Dataframed.
    * 
    * @param column the column to insert
    * @exception Exception
    */
    public void insertColumn(Column<?> column) throws Exception;
    
  /**
    * This method is used to remove a column from a Dataframed.
    * 
    * @param label the column to remove
    * @exception Exception
    */
    public void dropColumn(String label) throws Exception;
    
  /**
    * This method is used to extract a column from a Dataframed.
    * 
    * @param label the name of the column  to extract
    * @return a column object
    * @exception Exception if label if not a valid column name
    */
    public Column<?> pop(String label) throws Exception;
    
    
    public void stats(Statistics f, String label);
    
    public void arithmetic(Arithmetics ar, String label);
    
}
