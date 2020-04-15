/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.main;

import fr.devops.dataframe.Dataframe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chouaib
 */
public class Main {
  
    public static void main(String[] args) throws Exception {
        //Dataframe df = new Dataframe("src/main/ressources/M1.csv");
        Map<String,List<?>> dataset;
        List<String> prenom = new ArrayList<>();
        prenom.add("kopp");
        
        List<Integer> numEtudiant  = new ArrayList<>();
        numEtudiant.add(2);
        
        List<Boolean> estAdmis = new ArrayList<>();
        estAdmis.add(Boolean.TRUE);
        
        List<Double> moyenne = new ArrayList<>();
        moyenne.add(10.0);
        
        dataset = new HashMap<>();
        
        dataset.put("prenom", prenom);
        dataset.put("num Etudiant", numEtudiant);
        dataset.put("admis", estAdmis);
        dataset.put("moyenne", moyenne);
        
        Dataframe df = new Dataframe(dataset);
        String[] input = {"true", "chouaib", "1", "10.5"};
        df.insertRow(input);
        df.fetchAll();
        
        df.dropColumn("prenom");
        
        
        df.fetchAll();
    }
}
