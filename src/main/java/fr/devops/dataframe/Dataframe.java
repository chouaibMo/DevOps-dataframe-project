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
import fr.devops.utils.Column;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static fr.devops.operations.Statistics.*;
import java.util.Arrays;
import java.util.HashMap;

/**
* Dataframe Model Object.
* 
* This class represents a Dataframe
*  
* @author chouaib
* @version 1.0
*/

public class Dataframe {
    
    private List<String> labels;
    private List<Column> dataframe;
    
  /**
    * Constructor : 
    * To create a Dataframe from a Map data structure,
    * That contains a strings as key and list of objects as value.
    * 
    * @param data the Map of labels and its corresponding list of values.
    */
    public Dataframe(Map<String,List<?>> data){
        dataframe = new ArrayList<>();
        labels = new ArrayList<>();
        for (String colname : data.keySet()) {
            dataframe.add(new Column(colname,"",data.get(colname))); 
            labels.add(colname);
        }
    }
    
  /**
    * Constructor : 
    * To create a Dataframe from a dataset csv file.
    * All types of separators are allowed (openCSV library used).
    * 
    * @param path the path to the csv file.
    * @throws FileNotFoundException if the path is not correct
    * @throws IOException if reading the file generates an error
    */
    public Dataframe(String path) throws FileNotFoundException, IOException {
        dataframe = new ArrayList<>();
        labels = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new FileReader(path));
        String[] types = csvReader.readNext();
        String[] labels = csvReader.readNext();
        for(int i =0; i<labels.length; i++) {
            this.dataframe.add(new Column<>(labels[i],types[i],new ArrayList<>()));
            this.labels.add(labels[i]);
        }
        List<String[]> lines = csvReader.readAll();
        for (String[] row : lines) 
            for (int i=0; i<row.length;i++) 
                dataframe.get(i).getValues().add(row[i]);
    }
 
    
  /**
    * This method returns a list of a Dataframe labels
    * 
    * @return returns a list of String corresponding to Dataframe labels.
    */
    public List<String> getLabels(){
        return this.labels;
    }
    
  /**
    * This method is used to check if a label (axis) already exists
    * 
    * @param label an axis name
    * @return returns true if Dataframe contains the label, false otherwise
    */
    public boolean containsLabel(String label){
        return this.labels.contains(label);
    }
    
  /**
    * This method returns the size of the Dataframe object
    * 
    * @return int returns the size of the Dataframe.
    */
    public int size() {
        return dataframe.get(0).getValues().size();
    }
    
  /**
    * This method returns the size of the Dataframe object
    * 
    * @return int returns the size of the Dataframe.
    */
    public Column<?> getColumn(String label) throws Exception{
        if(!labels.contains(label))
            throw new Exception("bad value : number of lines");
        
        Column<?> column = getColumn(labels.indexOf(label));
        return column;
    }
    
     /**
    * This method returns a column of the Dataframe from it's index.
    * index should be equals or greater than 0 and less than Dataframe's size.
    * 
    * @return int returns the size of the Dataframe.
    * @throws Exception if index if greater than Dataframe size
    */
    public Column<?> getColumn(int index) throws Exception{
        if(index < 0 || index >= size())
            throw new Exception("bad value : number of lines");
        
        return dataframe.get(index);
    }

  /**
    * This method is used to display all the rows of a Dataframe.
    * The rows are printed line by line in stdout.
    */
    public void fetchAll() throws Exception{
        fetchFromTo(0,size());
    }
    
  /**
    * This method is used to display the rows of a Dataframe between start and end.
    * The rows are printed line by line in stdout.
    * 
    * @param start the row where display is started
    * @param end  the row where .
    * @exception Exception if nbLine is invalid 
    */
    public void fetchFromTo(int start, int end) throws Exception{
        if(start < 0 || start > size() || end < 0 || end > size() || start > end)
            throw new Exception("bad value : number of lines");
        
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
    * @exception Exception if nbLine is invalid 
    */
    public void head(int nbline) throws Exception {
        if(nbline < 0 || nbline > size())
            throw new Exception("bad value : number of lines");
        fetchFromTo(0, nbline);
    }

  /**
    * This method is used to display the last n rows of a Dataframed.
    * The rows are printed line by line in stdout.
    * 
    * @param nbline the number row to display
    * @exception Exception if nbLine is invalid 
    */
    public void tail(int nbline) throws Exception {
        if(nbline < 0 || nbline > size())
            throw new Exception("bad value : number of lines");
        fetchFromTo(size()-nbline,size());
    }

  /**
    * This method is used to insert a row in a Dataframed.
    * 
    * @param row the row to insert
    * @exception Exception
    */
    public void insertRow(List<Object> row) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
  /**
    * This method is used to insert a column in a Dataframed.
    * 
    * @param column the column to insert
    * @exception Exception
    */
    public void insertColumn(Column<?> column) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
  /**
    * This method is used to remove a column from a Dataframed.
    * 
    * @param label the column to remove
    * @exception Exception
    */
    public void dropColumn(String label) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
     /**
    * This method is used to remove a column from a Dataframed.
    * 
    * @param index the column to remove
    * @exception Exception
    */
    public void dropColumn(int index) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
  /**
    * This method is used to extract a column from a Dataframed.
    * 
    * @param label the name of the column  to extract
    * @return a column object
    * @exception Exception if label if not a valid column name
    */
    public Column<?> pop(String label) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Double sum(String label) throws Exception{
        if(!labels.contains(label))
            throw new Exception("bad value : number of lines");
        return Sum(getColumn(label));
    }
    
    public Double min(String label) throws Exception{
        if(!labels.contains(label))
            throw new Exception("bad value : number of lines");
        return Min(getColumn(label));
    }

    public Double max(String label) throws Exception{
        if(!labels.contains(label))
            throw new Exception("bad value : number of lines");
        return Max(getColumn(label));
    }
        
    public Double mean(String label) throws Exception{
        if(!labels.contains(label))
            throw new Exception("bad value : number of lines");
        return Mean(getColumn(label));
    }
    
    public static void main(String[] args) throws Exception {
        Dataframe df = new Dataframe("src/main/ressources/oscars.csv");
        Map<String,List<?>> dataset;
        List<String> prenom = Arrays.asList("Léa", "Claude", "Régis", "Emma", "Ali", "Ines");
        List<Integer> numEtudiant = Arrays.asList(118823, 112893, 112534, 113090, 115368, 114982);   
        List<Boolean> estAdmis = Arrays.asList(false, true, true, true, false, true);      
        List<Double> moyenne = Arrays.asList(9.73, 13.28, 12.07, 14.90, 9.45, 15.15);   
        dataset = new HashMap<>();
        dataset.put("prenom", prenom);
        dataset.put("num Etudiant", numEtudiant);
        dataset.put("admis", estAdmis);
        dataset.put("moyenne", moyenne);
        
        //Dataframe df = new Dataframe(dataset);
        
        df.tail(3);
    }
    
}
