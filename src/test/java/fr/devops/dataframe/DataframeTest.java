/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.utils.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chouaib
 */
public class DataframeTest {
     
    Dataframe students;
    Dataframe cities;
    Dataframe oscars;
    Dataframe trees;
    static Map<String,List<?>> dataset;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        List<String> prenom = Arrays.asList("Léa", "Claude", "Régis", "Emma", "Ali", "Ines");
        List<Integer> numEtudiant = Arrays.asList(118823, 112893, 112534, 113090, 115368, 114982);   
        List<Boolean> estAdmis = Arrays.asList(false, true, true, true, false, true);      
        List<Double> moyenne = Arrays.asList(9.73, 13.28, 12.07, 14.90, 9.45, 15.15);   
        dataset = new HashMap<>();
        dataset.put("prenom", prenom);
        dataset.put("num Etudiant", numEtudiant);
        dataset.put("admis", estAdmis);
        dataset.put("moyenne", moyenne);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        trees = new Dataframe("src/main/ressources/trees.csv");
        oscars = new Dataframe("src/main/ressources/oscars.csv");
        cities = new Dataframe("src/main/ressources/cities.csv");
        students = new Dataframe(dataset);
        
    }
    
    @After
    public void tearDown() {
        System.out.println("After");
    }

    /**
     * Test of size method, of class Dataframe.
     */
    @Test
    public void testSize1() {
        assertEquals(students.size(),6);
        assertEquals(trees.size(),31);
        assertEquals(oscars.size(),89);
        assertEquals(cities.size(),128);
        
    }
    
}
