/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.utils.Column;
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

    /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels() {
        System.out.println("getLabels");
        Dataframe instance = null;
        List<String> expResult = null;
        List<String> result = instance.getLabels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsLabel method, of class Dataframe.
     */
    @Test
    public void testContainsLabel() {
        System.out.println("containsLabel");
        String label = "";
        Dataframe instance = null;
        boolean expResult = false;
        boolean result = instance.containsLabel(label);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Dataframe.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Dataframe instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test
    public void testGetColumn_String() throws Exception {
        System.out.println("getColumn");
        String label = "";
        Dataframe instance = null;
        Column expResult = null;
        Column result = instance.getColumn(label);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test
    public void testGetColumn_int() throws Exception {
        System.out.println("getColumn");
        int index = 0;
        Dataframe instance = null;
        Column expResult = null;
        Column result = instance.getColumn(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAll method, of class Dataframe.
     */
    @Test
    public void testFetchAll() throws Exception {
        System.out.println("fetchAll");
        Dataframe instance = null;
        instance.fetchAll();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test
    public void testFetchFromTo() throws Exception {
        System.out.println("fetchFromTo");
        int start = 0;
        int end = 0;
        Dataframe instance = null;
        instance.fetchFromTo(start, end);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of head method, of class Dataframe.
     */
    @Test
    public void testHead() throws Exception {
        System.out.println("head");
        int nbline = 0;
        Dataframe instance = null;
        instance.head(nbline);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tail method, of class Dataframe.
     */
    @Test
    public void testTail() throws Exception {
        System.out.println("tail");
        int nbline = 0;
        Dataframe instance = null;
        instance.tail(nbline);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertRow method, of class Dataframe.
     */
    @Test
    public void testInsertRow() throws Exception {
        System.out.println("insertRow");
        List<Object> row = null;
        Dataframe instance = null;
        instance.insertRow(row);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertColumn method, of class Dataframe.
     */
    @Test
    public void testInsertColumn() throws Exception {
        System.out.println("insertColumn");
        Column column = null;
        Dataframe instance = null;
        instance.insertColumn(column);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_String() throws Exception {
        System.out.println("dropColumn");
        String label = "";
        Dataframe instance = null;
        instance.dropColumn(label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_int() throws Exception {
        System.out.println("dropColumn");
        int index = 0;
        Dataframe instance = null;
        instance.dropColumn(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pop method, of class Dataframe.
     */
    @Test
    public void testPop() throws Exception {
        System.out.println("pop");
        String label = "";
        Dataframe instance = null;
        Column expResult = null;
        Column result = instance.pop(label);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sum method, of class Dataframe.
     */
    @Test
    public void testSum() throws Exception {
        System.out.println("sum");
        String label = "";
        Dataframe instance = null;
        double expResult = 0.0;
        double result = instance.sum(label);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of min method, of class Dataframe.
     */
    @Test
    public void testMin() throws Exception {
        System.out.println("min");
        String label = "";
        Dataframe instance = null;
        double expResult = 0.0;
        double result = instance.min(label);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of max method, of class Dataframe.
     */
    @Test
    public void testMax() throws Exception {
        System.out.println("max");
        String label = "";
        Dataframe instance = null;
        double expResult = 0.0;
        double result = instance.max(label);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mean method, of class Dataframe.
     */
    @Test
    public void testMean() throws Exception {
        System.out.println("mean");
        String label = "";
        Dataframe instance = null;
        double expResult = 0.0;
        double result = instance.mean(label);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Dataframe.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Dataframe.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
