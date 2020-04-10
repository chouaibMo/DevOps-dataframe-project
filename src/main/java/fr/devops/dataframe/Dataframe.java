/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.devops.operations.Function;
import fr.devops.utils.Column;
import fr.devops.utils.ListFactory;
import static fr.devops.utils.ListFactory.createList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chouaib
 */
public class Dataframe implements Dataframe_itf{
    
    private List<Column> dataframe;
    
    public Dataframe(Map<String,List<Object>> data){
        dataframe = new ArrayList<>();
        for (String colname : data.keySet()) {
            dataframe.add(new Column(colname,"",data.get(colname))); 
        }
    }
    
    public Dataframe(String path) throws FileNotFoundException, IOException, Exception{
        dataframe = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new FileReader(path));
        String[] types = csvReader.readNext();
        String[] labels = csvReader.readNext();
        for(int i =0; i<labels.length; i++) {
            //dataframe.add(new Column<>(labels[i],types[i],createList(types[i])));
            dataframe.add(new Column<>(labels[i],types[i],new ArrayList<>()));
        }
        
        List<String[]> lines = csvReader.readAll();
        
        for (String[] row : lines) 
            for (int i=0; i<row.length;i++) 
                dataframe.get(i).getValues().add(row[i]);
  
    }

    @Override
    public int size() {
        return dataframe.get(0).getValues().size();
    }

    @Override
    public void fetchAll() {
        fetchFromTo(0,size());
    }
    
        @Override
        public void fetchFromTo(int i, int j) {
            String labels="|";
            int size = size();
            for(Column column : dataframe){
                labels += "\t"+column.getColName()+"\t|";
            } 
            String separation = new String(new char[labels.length()+8*dataframe.size()+8]).replace('\0', '-');
            System.out.println(labels);
            System.out.println(separation);

            for(int k=i; k<j;k++){
                String line =" ";
                for(Column column : dataframe){
                    line += "\t"+column.getValues().get(k)+"\t|";
                }
                System.out.println(line);
            }
        }

    @Override
    public void head(int nbline) throws Exception {
        if(nbline < 0 || nbline > size())
            throw new Exception("bad value : number of lines");
        fetchFromTo(0, nbline-1);
    }

    @Override
    public void tail(int nbline) throws Exception {
        if(nbline < 0 || nbline > size())
            throw new Exception("bad value : number of lines");
        fetchFromTo(size()-nbline,size());
    }

    @Override
    public void stats(Function func, String axis) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) throws Exception {
        Dataframe df = new Dataframe("src/main/ressources/cities.csv");
        df.fetchAll();
    }
    
}
