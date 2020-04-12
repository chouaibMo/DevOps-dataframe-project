/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.Exceptions.BadArgumentException;
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
    
    private Dataframe students, cities, oscars, emptyDf;
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
        emptyDf = new Dataframe();
        
    }
    
    @After
    public void tearDown() {
        dataset = null;
        oscars = null;
        cities = null;
        students = null;
    }
    
    
    /**
     * Test of size method, of class Dataframe.
     */
    @Test
    public void testSize() {
        //assertEquals(emptyDf.size(),0);
        assertEquals(students.size(),6);
        assertEquals(oscars.size(),89);
        assertEquals(cities.size(),128);
    }

    /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels() {
        assertEquals(emptyDf.getLabels().size(), 0);
        assertEquals(students.getLabels().size(), 4);
        assertEquals(oscars.getLabels().size(), 5);
        assertEquals(cities.getLabels().size(), 10); 
    }
    
        /**
     * Test of getLabels method, of class Dataframe.
     */
    @Test
    public void testGetLabels2() {
        assertTrue(emptyDf.getLabels().isEmpty());
        assertTrue(students.getLabels().equals(Arrays.asList("admis","prenom","num Etudiant","moyenne")));
        assertTrue(oscars.getLabels().equals(Arrays.asList("Index", "Year", "Age", "Name", "Movie")));
        assertTrue(cities.getLabels().equals(Arrays.asList("LatD", "LatM", "LatS", "NS", "LonD", "LonM", "LonS", "EW", "City", "State")));
    }
    
   /**
     * Test of getTypes method, of class Dataframe.
     */
    @Test
    public void testGetTypes() {
        assertTrue(emptyDf.getTypes().isEmpty());        
        assertTrue(students.getTypes().equals(Arrays.asList("Boolean", "String","Integer","Double")));        
        assertTrue(cities.getTypes().equals(Arrays.asList("Integer", "Integer", "Integer","Character", "Integer", "Integer","Integer","Character","String","String")));
        assertTrue(oscars.getTypes().equals(Arrays.asList("Integer", "Integer", "Integer","String", "String")));
    }
    
   /**
     * Test of getTypes method, of class Dataframe.
     */
    @Test
    public void testGetTypes2() {
        assertEquals(emptyDf.getTypes().size(), 0);
        assertEquals(students.getTypes().size(), 4);
        assertEquals(oscars.getTypes().size(), 5);
        assertEquals(cities.getTypes().size(), 10); 
    }
    
    /**
     * Test of containsLabel method, of class Dataframe.
     */
    @Test
    public void testContainsLabel() {
        assertTrue(students.containsLabel("prenom"));
        assertTrue(students.containsLabel("num Etudiant"));
        assertTrue(cities.containsLabel("City"));
        assertTrue(cities.containsLabel("State"));
        assertTrue(oscars.containsLabel("Year"));
        assertTrue(oscars.containsLabel("Movie"));
        
    }
    
   /**
     * Test of containsLabel method, of class Dataframe.
     */
    @Test
    public void testContainsLabel2() {
        assertFalse(emptyDf.containsLabel("test"));
        assertFalse(emptyDf.containsLabel("test"));
        assertFalse(students.containsLabel("test"));
        assertFalse(students.containsLabel("numEtudiant"));
        assertFalse(cities.containsLabel("Cities"));
        assertFalse(cities.containsLabel("States"));
        assertFalse(oscars.containsLabel("Years"));
        assertFalse(oscars.containsLabel("Movies"));
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
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2));
        
        emptyDf.insertColumn(col1);
        assertEquals(emptyDf.getTypes().size(), 1);
        assertEquals(emptyDf.getLabels().size(), 1);
        assertTrue(emptyDf.getLabels().contains("testCol1"));
        
        emptyDf.insertColumn(col2);
        assertEquals(emptyDf.getTypes().size(), 2);
        assertEquals(emptyDf.getLabels().size(), 2);
        assertTrue(emptyDf.getLabels().contains("testCol2"));
    }
    
    /**
     * Test of insertColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testInsertColumnBadArgumentException() throws Exception {
        Column col1 = null;
        Column col2 = null;
        
        emptyDf.insertColumn(col1);
        assertEquals(emptyDf.getTypes().size(), 0);
        assertEquals(emptyDf.getLabels().size(), 0);
        
        emptyDf.insertColumn(col2);
        assertEquals(emptyDf.getTypes().size(), 0);
        assertEquals(emptyDf.getLabels().size(), 0);
        
    }
    

    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_String() throws Exception {
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2));
        
        emptyDf.insertColumn(col1);
        emptyDf.insertColumn(col2);
        
        emptyDf.dropColumn("testCol1");
        assertEquals(emptyDf.getLabels().size(), 1);
        emptyDf.dropColumn("testCol2");
        assertEquals(emptyDf.getLabels().size(), 0);
    
    }

    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_int() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2));
        
        emptyDf.insertColumn(col);
        emptyDf.insertColumn(col2);
        
        emptyDf.dropColumn(0);
        assertEquals(emptyDf.getLabels().size(), 1);
        emptyDf.dropColumn(0);
        assertEquals(emptyDf.getLabels().size(), 0);
    }
    
    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test
    public void testDropColumn_int2() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2));
        
        emptyDf.insertColumn(col);
        emptyDf.insertColumn(col2);
        
        emptyDf.dropColumn(1);
        assertEquals(emptyDf.getLabels().size(), 1);
        emptyDf.dropColumn(0);
        assertEquals(emptyDf.getLabels().size(), 0);
    }
    
    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testDropColumnBadArgumentExeption() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        
        emptyDf.dropColumn(0);
        emptyDf.dropColumn(-1);
        emptyDf.insertColumn(col);
        assertEquals(emptyDf.getLabels().size(),1);
        emptyDf.dropColumn(0);
        emptyDf.dropColumn(0);
    }
    
        /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = LabelNotFoundException.class)
    public void testDropColumnLabelNotFoundException() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        
        emptyDf.dropColumn("test");
        System.out.println("suiiiiiiiite");
        emptyDf.dropColumn("testcol");
        emptyDf.insertColumn(col);
        assertEquals(emptyDf.getLabels().size(),1);
        assertTrue(emptyDf.containsLabel("testCol"));
        emptyDf.dropColumn("testCol");
        assertEquals(emptyDf.getLabels().size(),1);
        emptyDf.dropColumn("testCol");
    }

    /**
     * Test of pop method, of class Dataframe.
     * @throws java.lang.Exception
     */
    @Test
    public void testPop() throws Exception {
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2, 3));
        
        emptyDf.insertColumn(col1);
        emptyDf.insertColumn(col2);
        
        Column c = emptyDf.pop();
        assertEquals(c, col2);
        assertEquals(c.getName(), "testCol2");
        
        c = emptyDf.pop();
        assertEquals(c, col1);
        assertEquals(c.getName(), "testCol1");
    }
    
    
    /**
     * Test of pop method, of class Dataframe.
     * @throws IllegalStateException
     */
    @Test (expected = IllegalStateException.class)
    public void testPopIllegalStateException() throws Exception {
        emptyDf.pop();
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
