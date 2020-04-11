/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.utils.Column;
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
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<String> prenom = Arrays.asList("Léa", "Claude", "Régis", "Emma", "Ali", "Ines");
        List<Integer> numEtudiant = Arrays.asList(118823, 112893, 112534, 113090, 115368, 114982);   
        List<Boolean> estAdmis = Arrays.asList(false, true, true, true, false, true);      
        List<Double> moyenne = Arrays.asList(9.73, 13.28, 12.07, 14.90, 9.45, 15.15);  
        
        dataset = new HashMap<>();
        dataset.put("prenom", prenom);
        dataset.put("num Etudiant", numEtudiant);
        dataset.put("admis", estAdmis);
        dataset.put("moyenne", moyenne);
        
        trees = new Dataframe("src/main/ressources/trees.csv");
        oscars = new Dataframe("src/main/ressources/oscars.csv");
        cities = new Dataframe("src/main/ressources/cities.csv");
        students = new Dataframe(dataset);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels() {
        assertEquals(students.getLabels().size(), 4);
        assertEquals(trees.getLabels().size(), 4);
        assertEquals(oscars.getLabels().size(), 5);
        assertEquals(cities.getLabels().size(), 10); 
    }
    
        /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels2() {
        assertTrue(students.getLabels().equals(Arrays.asList("admis","prenom","num Etudiant","moyenne")));
        assertTrue(trees.getLabels().equals(Arrays.asList("Index", "Girth (in)", "Height (ft)", "Volume(ft^3)")));
        assertTrue(oscars.getLabels().equals(Arrays.asList("Index", "Year", "Age", "Name", "Movie")));
        assertTrue(cities.getLabels().equals(Arrays.asList("LatD", "LatM", "LatS", "NS", "LonD", "LonM", "LonS", "EW", "City", "State")));
    }

   /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels3() {
        
    }
    
   /**
     * Test of getTypes method, of class Dataframe.
     */
    @Test
    public void testGetTypes() {
        
    }
    
    /**
     * Test of containsLabel method, of class Dataframe.
     */
    @Test
    public void testContainsLabel() {
 
    }

    /**
     * Test of size method, of class Dataframe.
     */
    @Test
    public void testSize() {
        assertEquals(students.size(),6);
        assertEquals(trees.size(),31);
        assertEquals(oscars.size(),89);
        assertEquals(cities.size(),128);
    }
    

    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test
    public void testGetColumn_String() throws Exception {
        
    }

    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test
    public void testGetColumn_int() throws Exception {
    }

    /**
     * Test of fetchAll method, of class Dataframe.
     */
    @Test
    public void testFetchAll() throws Exception {
    }

    /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test
    public void testFetchFromTo() throws Exception {
    }

    /**
     * Test of head method, of class Dataframe.
     */
    @Test
    public void testHead() throws Exception {
    }

    /**
     * Test of tail method, of class Dataframe.
     */
    @Test
    public void testTail() throws Exception {
    }

    /**
     * Test of insertRow method, of class Dataframe.
     */
    @Test
    public void testInsertRow() throws Exception {
    }

    /**
     * Test of insertColumn method, of class Dataframe.
     */
    @Test
    public void testInsertColumn() throws Exception {
    }

    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_String() throws Exception {
    }

    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_int() throws Exception {
    }

    /**
     * Test of pop method, of class Dataframe.
     */
    @Test
    public void testPop() throws Exception {
    }

    /**
     * Test of sum method, of class Dataframe.
     */
    @Test
    public void testSum() throws Exception {
    }

    /**
     * Test of min method, of class Dataframe.
     */
    @Test
    public void testMin() throws Exception {
    }

    /**
     * Test of max method, of class Dataframe.
     */
    @Test
    public void testMax() throws Exception {
    }

    /**
     * Test of mean method, of class Dataframe.
     */
    @Test
    public void testMean() throws Exception {
    }
   
}
