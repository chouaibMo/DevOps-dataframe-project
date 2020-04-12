/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.Exceptions.LabelNotFoundException;
import fr.devops.Exceptions.NotaNumberException;
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
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 *
 * @author chouaib
 */
public class DataframeTest {
    
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);
    
    private Dataframe students, cities, oscars;
    private static Map<String,List<?>> dataset;
    
    static List<String> prenom;
    static List<Integer> numEtudiant;
    static List<Boolean> estAdmis;
    static List<Double> moyenne;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        prenom = Arrays.asList("Léa", "Claude", "Tony", "Emma", "Ali", "Sarah");
        numEtudiant = Arrays.asList(118823, 112893, 112534, 113090, 115368, 114982);   
        estAdmis = Arrays.asList(false, true, true, true, false, true);      
        moyenne = Arrays.asList(9.73, 13.28, 12.07, 14.90, 9.45, 15.15); 
    }
    
    @AfterClass
    public static void tearDownClass() {
        prenom      = null;
        numEtudiant = null;  
        estAdmis    = null;     
        moyenne     = null;
    }
    
    @Before
    public void setUp() throws Exception {
        
        dataset = new HashMap<>();
        dataset.put("prenom", prenom);
        dataset.put("num Etudiant", numEtudiant);
        dataset.put("admis", estAdmis);
        dataset.put("moyenne", moyenne);
        
        oscars = new Dataframe("src/main/ressources/oscars.csv");
        cities = new Dataframe("src/main/ressources/cities.csv");
        students = new Dataframe(dataset);
        
    }
    
    @After
    public void tearDown() {
        dataset = null;
        oscars = null;
        cities = null;
        students = null;
    }

    /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels() {
        assertEquals(students.getLabels().size(), 4);
        assertEquals(oscars.getLabels().size(), 5);
        assertEquals(cities.getLabels().size(), 10); 
    }
    
        /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels2() {
        assertTrue(students.getLabels().equals(Arrays.asList("admis","prenom","num Etudiant","moyenne")));
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
     * @throws java.lang.Exception
     */
    @Test
    public void testPop() throws Exception {
    }

    /**
     * Test of sum method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test
    public void testSum() throws Exception {
        assertEquals(4005, oscars.sum("Index"),0.01);
        assertEquals(74.58, students.sum("moyenne"),0.01);
    }
    
    @Test(expected = LabelNotFoundException.class)
    public void testSumLabelNotFoundException() throws Exception {
        oscars.sum("test");
        cities.sum("test");
        students.sum("test");
    }
    
    @Test(expected = NotaNumberException.class)
    public void testSumNotaNumberException() throws Exception {
        oscars.sum("Movie");
        cities.sum("City");
        students.sum("prenom");
    }

    /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test
    public void testMin() throws Exception {
        assertEquals(1,oscars.min("Index"),0.01);
        assertEquals(9.45,students.min("moyenne"),0.01);
    }
    
   /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test(expected = LabelNotFoundException.class)
    public void testMinLabelNotFoundException() throws Exception {
        oscars.min("test");
        cities.min("test");
        students.min("test");
    }
    
   /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test(expected = NotaNumberException.class)
    public void testMinNotaNumberException() throws Exception {
        oscars.min("Movie");
        cities.min("City");
        students.min("prenom");
    }

    /**
     * Test of max method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test
    public void testMax() throws Exception {
        assertEquals(89,oscars.max("Index"),0.01);
        assertEquals(15.15,students.max("moyenne"),0.01);
    }
    
   /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test(expected = LabelNotFoundException.class)
    public void testMaxLabelNotFoundException() throws Exception {
        oscars.max("test");
        cities.max("test");
        students.max("test");
    }
    
   /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test(expected = NotaNumberException.class)
    public void testMaxNotaNumberException() throws Exception {
        oscars.max("Movie");
        cities.max("City");
        students.max("prenom");
    }

    /**
     * Test of mean method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test
    public void testMean() throws Exception {
        assertEquals(45,oscars.mean("Index"),0.01);
        assertEquals(12.43,students.mean("moyenne"),0.01);
    }
    
   /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test(expected = LabelNotFoundException.class)
    public void testMeanLabelNotFoundException() throws Exception {
        oscars.mean("test");
        cities.mean("test");
        students.mean("test");
    }
    
   /**
     * Test of min method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test(expected = NotaNumberException.class)
    public void testMeanNotaNumberException() throws Exception {
        oscars.mean("Movie");
        cities.mean("City");
        students.mean("prenom");
    }
}
