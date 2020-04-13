/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.main;

import fr.devops.dataframe.Dataframe;
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
        //Dataframe df = new Dataframe("src/main/ressources/oscars.csv");
        Map<String,List<?>> dataset;
        List<String> prenom = Arrays.asList("Léa", "Claude", "Régis", "Emma", "Ali", "Sarah");
        List<Integer> numEtudiant  = Arrays.asList(10, 11, 15, 9, 2, 6);    
        List<Boolean> estAdmis = Arrays.asList(false, true, true, true, false, true);      
        List<Double> moyenne = Arrays.asList(9.73, 13.28, 12.07, 14.90, 9.45, 15.15); 
        dataset = new HashMap<>();
        dataset.put("prenom", prenom);
        dataset.put("num Etudiant", numEtudiant);
        dataset.put("admis", estAdmis);
        dataset.put("moyenne", moyenne);
        
        Dataframe df = new Dataframe(dataset);
        
        df.fetchAll();
        df.stats("moyenne");
    }
}
