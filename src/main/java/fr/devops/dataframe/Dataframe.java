/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;


import com.opencsv.CSVReader;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import fr.devops.Exceptions.BadArgumentException;
import fr.devops.Exceptions.LabelNotFoundException;
import fr.devops.Exceptions.NotaNumberException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static fr.devops.operations.Statistics.*;

/**
* This class represents a Dataframe object
*  
* @author chouaib
* @version 1.0
*/

public class Dataframe {
    
    private List<String> labels;
    private List<Column> dataframe;
    private int nbRows;
 
    
   /**
    * Default constructor : 
    * To create an empty Dataframe 
    */
    public Dataframe(){
        dataframe = new ArrayList<>();
        labels = new ArrayList<>();
        nbRows = 0;
    }
    
   /**
    * Create a Dataframe from a Map data structure,
    * That contains a strings as key and list of objects as value.
    * 
    * @param dataset a Map that contains the data
    * @throws BadArgumentException if dataset is null, empty or has different column sizes.
    */
    public Dataframe(Map<String,List<?>> dataset) throws BadArgumentException{
        if(dataset == null)
            throw new BadArgumentException("dataset Map is null");
        if(dataset.isEmpty())
            throw new BadArgumentException("dataset Map is empty");      
        
        List<List<?>> columns = new ArrayList<>(dataset.values());
        int size = columns.get(0).size();
        
        for(List<?> list : columns){
            if( list == null)
                throw new BadArgumentException("dataset contains a null list");
            else if( list.size() != size)
                throw new BadArgumentException("dataset columns has not the same size");
        }     
        dataframe = new ArrayList<>();
        labels = new ArrayList<>();
        for (String colname : dataset.keySet()) {
            String type = dataset.get(colname).get(0).getClass().toString();
            type = type.substring(type.lastIndexOf('.') + 1);
            dataframe.add(new Column<>(colname,type,dataset.get(colname))); 
            labels.add(colname);
        }
        nbRows = size;
    }
    
  /**
    * Create a Dataframe from a dataset csv file.All types of separators are allowed (openCSV library used).
    * 
    * @param path the path to the csv file.
    * @throws FileNotFoundException if the path is not correct
    * @throws IOException if reading the file generates an error
    * @throws BadArgumentException if the csv file contains columns of different sizes
    */
    @SuppressWarnings("unchecked")
    public Dataframe(String path) throws FileNotFoundException, IOException, BadArgumentException {
        dataframe = new ArrayList<>();
        labels = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new FileReader(path));
        String[] types = csvReader.readNext();
        String[] labels = csvReader.readNext();
        for(int i =0; i<labels.length; i++) {
            this.dataframe.add(new Column<>(labels[i].trim(),types[i].trim(),new ArrayList<>()));
            this.labels.add(labels[i].trim());
        }
        List<String[]> lines = csvReader.readAll();
        for (String[] row : lines) 
            for (int i=0; i<row.length;i++) 
                dataframe.get(i).getValues().add(row[i]);
        
        nbRows = dataframe.get(0).size();
        for(Column<?> col : dataframe)
            if(nbRows != col.size() )
                throw new BadArgumentException("columns in csv file should have the same size");
    }
 
   /**
    * This method returns the number of rows in the Dataframe.
    * @return int returns the number of rows in the Dataframe.
    */
    public int nbRows() {
        return this.nbRows;
    }
    
   /**
    * This method returns the number of columns in the Dataframe.
    * @return int returns the number of rows in the Dataframe.
    */
    public int nbColumns() {
        return this.labels.size();
    }
    
  /**
    * This method returns a list of a Dataframe labels
    * @return returns a list of String corresponding to Dataframe labels.
    */
    public List<String> getLabels(){
        return this.labels;
    }
    
   /**
    * This method set the labels list of a Dataframe
     * @param labels a list of strings
    */
    public void setLabels(List<String> labels){
        this.labels = labels;
    }
    
  /**
    * This method returns a list of the types of a Dataframe columns
    * @return returns a list of String corresponding to Dataframe types.
    */
    public List<String> getTypes(){
        List<String> types = new ArrayList<>();
        for(Column column : dataframe){
            types.add(column.getType());
        }
        return types;
    }
    
  /**
    * This method is used to check if a label (axis) already exists
    * @param label an axis name
    * @return returns true if Dataframe contains the label, false otherwise
    */
    public boolean containsLabel(String label){
        for(Column column : dataframe){
            if(column.getName().equals(label)){
                return true;
            }
        }   
        return false;
    }
    
   /**
    * This method returns the index of a column in the Dataframe
    * @param label a column name
    * @return returns the index of the column in the Dataframe. -1 if label is not in the dataframe
    * 
    */
    public int indexOfLabel(String label){
        for(int i=0; i< dataframe.size();i++)
            if(dataframe.get(i).getName().equals(label))
                return i;
         
        return -1;
    }  
    
   /**
    * returns the row associated to the index in parameter.index should be equals or greater than 0 and less than Dataframe's size.
    * 
    * @param index the row index
    * @return the row numb
    * @throws BadArgumentException if index is invalid
    */
    public List<String> getRow(int index) throws BadArgumentException{
        if(index < 0 || index >= nbRows() )
            throw new BadArgumentException("index");
        
        ArrayList<String> row = new ArrayList<>();
        for(Column col : this.dataframe)
            row.add(col.getValues().get(index).toString());
        
        return row;
    }
    
   /**
    * returns a column of the Dataframe using it's label.
    * index should be equals or greater than 0 and less than Dataframe's size.
    * 
    * @param label the column name
    * @return the column associated to the parameter
    * @throws LabelNotFoundException if label is not a valid column name
    */
    public Column getColumn(String label) throws LabelNotFoundException {
        if(!containsLabel(label))
            throw new LabelNotFoundException(label+" is not a column name");
        
        Column c = null;
        for(Column column : dataframe)
            if(column.getName().equals(label))
                c = column;
        
        return c;
    }
    
  /**
    * This method returns a column of the Dataframe using it's index.
    * index should be equals or greater than 0 and less than Dataframe's size.
    * 
    * @param index the column id in the dataframe
    * @return the column associated to the parameter
    * @throws BadArgumentException if index if greater than Dataframe size
    */
    public Column getColumn(int index) throws BadArgumentException{
        if(index < 0 || index >= getLabels().size())
            throw new BadArgumentException("bad value : number of lines");
        
        return dataframe.get(index);
    }

  /**
    * This method is used to display all the rows of a Dataframe.
    * The rows are printed line by line in stdout.
    * @throws BadArgumentException because of the ferchFromTo method
    */
    public void fetchAll() throws BadArgumentException{
        fetchFromTo(0,nbRows());
    }
    
  /**
    * This method is used to display the rows of a Dataframe between start and end.
    * The rows are printed line by line in stdout.
    * 
    * @param start the row where display is started
    * @param end  the row where .
    * @exception BadArgumentException if start or/and end are not valid
    */
    protected void fetchFromTo(int start, int end) throws BadArgumentException{
        if(start < 0 || start > nbRows() )
            throw new BadArgumentException("start");
        if(end < 0 || end > nbRows()) 
            throw new BadArgumentException("end");
        if(start > end) 
            throw new BadArgumentException("start shoud be <= end");
        
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(labels);
        for(int i=start;i<end;i++){
            at.addRule();
            List<String> row = new ArrayList<>();
            for(Column column : dataframe)
                row.add(column.getValues().get(i).toString());
            at.addRow(row);
        }
        at.addRule();
        at.setTextAlignment(TextAlignment.CENTER);
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        System.out.println(at.render());
        
    }

  /**
    * This method is used to display the first n rows of a Dataframed.
    * The rows are printed line by line in stdout.
    * 
    * @param nbline the number row to display
    * @throws BadArgumentException if nbLine is invalid 
    */
    public void head(int nbline) throws BadArgumentException {
        if(nbline < 0 || nbline > nbRows())
            throw new BadArgumentException("number of lines");
        fetchFromTo(0, nbline);
    }

  /**
    * This method is used to display the last n rows of a Dataframed.
    * The rows are printed line by line in stdout.
    * 
    * @param nbline the number row to display
    * @throws BadArgumentException if nbLine is invalid 
    */
    public void tail(int nbline) throws BadArgumentException {
        if(nbline < 0 || nbline > nbRows())
            throw new BadArgumentException("number of lines");
        fetchFromTo(nbRows()-nbline,nbRows());
    }
    
    
   /**
    * This method is get a new Dataframe that contains only a selection of rows
    * 
    * @param start index of the first row
    * @param end  index of the last row
    * @return a new Dataframe object that contains row between start and end
    * @exception BadArgumentException if start or/and end are not valid
    */
    public Dataframe selectionRow(int start, int end) throws BadArgumentException{
        if(start < 0 || start >= nbRows() )
            throw new BadArgumentException("start");
        if(end < 0 || end >= nbRows()) 
            throw new BadArgumentException("end");
        if(start > end) 
            throw new BadArgumentException("start shoud be <= end");
        
        Dataframe df = new Dataframe();
        for(Column col : this.dataframe)
            df.insertColumn( new Column(col.getName(), col.getType()) );

        for(int i=start; i<=end; i++){
            String[] row = this.getRow(i).toArray(new String[0]);
            df.insertRow(row);
        }
        return df;
    }
    
   /**
    * This method is get a new Dataframe that contains only a selection of columns
    * 
    * @param list a list of labels of columns to select
    * @return a new Dataframe object that contains columns passed in parameter
    * @exception BadArgumentException if the list of label is null, empty
    * @throws LabelNotFoundException  if a the dataframe doesn't contains one of the labels in parameter
    */
    public Dataframe selectionColumns(List<String> list) throws BadArgumentException, LabelNotFoundException{
        if(list == null)
            throw new BadArgumentException("labels list is null");
        if(list.isEmpty())
            throw new BadArgumentException("labels list is empty");  
        
        Dataframe df = new Dataframe();
        for(String label : list){
            Column col = this.getColumn(label);
            df.insertColumn(col);
        }
        df.nbRows = df.dataframe.get(0).size();
        return df;
    }

  /**
    * This method is used to insert a row in a Dataframed.
    * 
    * @param row the row to insert
    * @throws BadArgumentException if the row size is not equal to the columns number
    */
    @SuppressWarnings("unchecked")
    public void insertRow(String[] row) throws BadArgumentException{
        if(row.length != this.labels.size())
            throw new BadArgumentException("the row should contains "+this.labels.size()+" elements");
        
        for(int i=0; i<this.labels.size(); i++){
            this.dataframe.get(i).addValue(row[i]);
        }
        this.nbRows++;
            
    }
    
  /**
    * This method is used to insert a column in a Dataframed.
    * 
    * @param column the column to insert
    * @throws BadArgumentException if column is null or column name already exists
    */
    public void insertColumn(Column column) throws BadArgumentException{
        if(column == null)
            throw new BadArgumentException("column should not be null");
        if(containsLabel(column.getName()))
            throw new BadArgumentException("column name already exists");
        
        dataframe.add(column);
        labels.add(column.getName());
    }
    
  /**
    * This method is used to remove a column from a Dataframed.
    * 
    * @param label the column to remove
    * @throws LabelNotFoundException if dataframe if empty, or label is invalid
    */
    public void dropColumn(String label) throws LabelNotFoundException{
        if(dataframe.isEmpty())
            throw new LabelNotFoundException("dataframe is empty");
        if(!containsLabel(label))
            throw new LabelNotFoundException(label+ " is not a valid column name");
        
        dataframe.remove(indexOfLabel(label));
        labels.remove(label);
    }
    
     /**
    * This method is used to remove a column from a Dataframed.
    * 
    * @param index the column to remove
    * @throws BadArgumentException if the dataframe is empty of the index is invalidxs
    */
    public void dropColumn(int index) throws BadArgumentException{
        if(dataframe.isEmpty())
            throw new BadArgumentException("dataframe is empty");
        if(index < 0 || index >= dataframe.size())
            throw new BadArgumentException(index+" is not a valid column index");
        
        dataframe.remove(index);
        labels.remove(index);
    }
    
  /**
    * This method is used to extract a column from a Dataframed.
    * 
    * @return a column object
    * @throws IllegalStateException if label if not a valid column name
    */
    public Column pop() throws IllegalStateException{
        if(dataframe.isEmpty())
            throw new IllegalStateException("dataframe is empty");
        
        Column column = dataframe.get(dataframe.size()-1);
        labels.remove(dataframe.size()-1);
        dataframe.remove(dataframe.size()-1);
        return column;
    }
    
   /**
    * This method is used to compute the sum of a Dataframe's column.
    * The column should contains number values only (Integer, Double or Float).
    * 
    * @param label the name of the column
    * @return the sum of all values in the column 
    * @throws LabelNotFoundException if label if not a valid column name
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public double sum(String label) throws LabelNotFoundException, NotaNumberException {
        if(!containsLabel(label))
            throw new LabelNotFoundException(label+ " is not a valid column name");
        return Sum(getColumn(label));
    }
    
   /**
    * This method is used to compute the min of a Dataframe's column.
    * The column should contains number values only (Integer, Double or Float).
    * 
    * @param label the name of the column
    * @return the min of all values in the column 
    * @throws LabelNotFoundException if label if not a valid column name
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public double min(String label) throws LabelNotFoundException, NotaNumberException{
        if(!containsLabel(label))
            throw new LabelNotFoundException(label+ " is not a valid column name");
        return Min(getColumn(label));
    }
    
   /**
    * This method is used to compute the max of a Dataframe's column.
    * The column should contains number values only (Integer, Double or Float).
    * 
    * @param label the name of the column
    * @return the max of all values in the column 
    * @throws LabelNotFoundException if label if not a valid column name
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public double max(String label) throws LabelNotFoundException, NotaNumberException{
        if(!containsLabel(label))
            throw new LabelNotFoundException(label+ " is not a valid column name");
        return Max(getColumn(label));
    }
    
   /**
    * This method is used to compute the mean of a Dataframe's column.
    * The column should contains number values only (Integer, Double or Float).
    * 
    * @param label the name of the column
    * @return the average of all values in the column 
    * @throws LabelNotFoundException if label if not a valid column name
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public double mean(String label) throws LabelNotFoundException, NotaNumberException{
        if(!containsLabel(label))
            throw new LabelNotFoundException(label+ " is not a valid column name");
        return Mean(getColumn(label));
    }
    
   /**
    * This method is used to display stats of a Dataframe's column.
    * The column should contains number values only (Integer, Double or Float).
    * 
    * @param label the name of the column
    * @throws LabelNotFoundException if label if not a valid column name
    * @throws NotaNumberException if the column is not a column of numbers.
    */
    public void printStats(String label) throws LabelNotFoundException, NotaNumberException{
        String[] str = { "Min", "Max", "Sum", "Mean"};
        Double[] val = {min(label), max(label), sum(label), mean(label) };
        
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(str);
        at.addRule();
        at.addRow(val);
        at.addRule();
        at.setTextAlignment(TextAlignment.CENTER);
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        System.out.println(at.render());
    }
}
