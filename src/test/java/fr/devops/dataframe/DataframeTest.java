/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.Exceptions.BadArgumentException;
import fr.devops.Exceptions.LabelNotFoundException;
import fr.devops.Exceptions.NotaNumberException;
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
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 *
 * @author chouaib
 */
@SuppressWarnings("unchecked")
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
        prenom = new ArrayList<String>(Arrays.asList("Léa", "Claude", "Tony", "Emma", "Ali", "Sarah"));
        numEtudiant = new ArrayList<Integer>(Arrays.asList(118823, 112893, 112534, 113090, 115368, 114982));   
        estAdmis = new ArrayList<Boolean>(Arrays.asList(false, true, true, true, false, true));      
        moyenne = new ArrayList<Double>(Arrays.asList(9.73, 13.28, 12.07, 14.90, 9.45, 15.15)); 
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
     * Test of constructor, of class Dataframe.
     * Columns of different sizes
     */
    @Test (expected = BadArgumentException.class)
    public void testConstructor() throws Exception{
        Dataframe df = new Dataframe("src/main/ressources/generateException.csv");
    }
    
    /**
     * Test of constructor, of class Dataframe.
     * Columns of different sizes
     */
    @Test (expected = BadArgumentException.class)
    public void testConstructor2() throws Exception{
        List<Integer> list1  = Arrays.asList(1, 2, 3);        
        List<Integer> list2 = Arrays.asList(4, 5); 
        
        dataset = new HashMap<>();
        dataset.put("list1", list1);
        dataset.put("list2", list2);
        
        Dataframe df = new Dataframe(dataset);
    }
    
    /**
     * Test of constructor, of class Dataframe.
     * a null column
     */
    @Test (expected = BadArgumentException.class)
    public void testConstructor3() throws Exception{
        List<Integer> list1  = Arrays.asList(1, 2, 3);        
        List<Integer> list2 = null; 
        
        dataset = new HashMap<>();
        dataset.put("list1", list1);
        dataset.put("list2", list2);
        
        Dataframe df = new Dataframe(dataset);
    }
    
    /**
     * Test of constructor, of class Dataframe.
     * Null dataset
     */
    @Test (expected = BadArgumentException.class)
    public void testConstructor4() throws Exception{
        dataset = null;
        Dataframe df = new Dataframe(dataset);
    }
    
    /**
     * Test of constructor, of class Dataframe.
     * Null dataset
     */
    @Test (expected = BadArgumentException.class)
    public void testConstructor5() throws Exception{
        dataset = new HashMap<>();
        Dataframe df = new Dataframe(dataset);
    }
    
    
    /**
     * Test of nbRows method, of class Dataframe.
     */
    @Test
    public void testnbRows() {
        assertEquals(emptyDf.nbRows(),0);
        assertEquals(students.nbRows(),6);
        assertEquals(oscars.nbRows(),89);
        assertEquals(cities.nbRows(),128);
    }
    
   /**
     * Test of nbColumns method, of class Dataframe.
     */
    @Test
    public void testnbColumns() {
        assertEquals(emptyDf.nbColumns(),0);
        assertEquals(students.nbColumns(),4);
        assertEquals(oscars.nbColumns(),5);
        assertEquals(cities.nbColumns(),10);
    }
    
   /**
     * Test of indexOfLabel method, of class Dataframe.
     */
    @Test
    public void testIndexOfLabel() {
        assertEquals(0, students.indexOfLabel("admis"));
        assertEquals(1, students.indexOfLabel("prenom"));
        assertEquals(2, students.indexOfLabel("num Etudiant"));
        assertEquals(-1,students.indexOfLabel("numEtudiant"));
    }
    
    /**
     * Test of indexOfLabel method, of class Dataframe.
     */
    @Test
    public void testIndexOfLabel2() throws Exception {
        Column<?> col = new Column<>("testCol","Integer",  Arrays.asList(1, 2));
        assertEquals(oscars.indexOfLabel("Index"),0);
        oscars.dropColumn("Age");
        assertEquals(oscars.indexOfLabel("Name"),2);
        oscars.insertColumn(col);
        assertEquals(oscars.indexOfLabel("testCol"),4);
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
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2, 3));
        
        emptyDf.insertColumn(col1);
        emptyDf.insertColumn(col2);
        assertEquals(col1, emptyDf.getColumn("testCol1"));
        assertEquals(col2, emptyDf.getColumn("testCol2"));
    }
    
    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test (expected = LabelNotFoundException.class)
    public void testGetColumn_StringException() throws Exception {
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        
        emptyDf.insertColumn(col1);
        emptyDf.getColumn("test");
    }

    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test 
    public void testGetColumn_int() throws Exception {
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2, 3));
        
        emptyDf.insertColumn(col1);
        emptyDf.insertColumn(col2);
        assertEquals(col1, emptyDf.getColumn(0));
        assertEquals(col2, emptyDf.getColumn(1));
    }
    
    /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testGetColumn_intException1() throws Exception {
        students.getColumn(5);
    }
    
   /**
     * Test of getColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testGetColumn_intException2() throws Exception {
        students.getColumn(-1);
    }

    /**
     * Test of fetchAll method, of class Dataframe.
     */
    @Test
    public void testFetchAll() throws Exception {
        students.fetchAll();
    }

    /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test
    public void testFetchFromTo() throws Exception {
        students.fetchFromTo(0,students.nbRows());
    }
    
    /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testFetchFromToException1() throws Exception {
        students.fetchFromTo(-1,4);
    }
    
    /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testFetchFromToException2() throws Exception {
        students.fetchFromTo(1,7);
    }
    
    /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testFetchFromToException3() throws Exception {
        students.fetchFromTo(5,3);
    }
    
   /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testFetchFromToException4() throws Exception {
        students.fetchFromTo(8,3);
    }
    
   /**
     * Test of fetchFromTo method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testFetchFromToException5() throws Exception {
        students.fetchFromTo(3,-1);
    }

    /**
     * Test of head method, of class Dataframe.
     */
    @Test 
    public void testHead() throws Exception {
        students.head(1); 
    }
    
    /**
     * Test of head method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testHeadException1() throws Exception {
        students.head(-1); 
        students.head(7); 
    }
    
    /**
     * Test of head method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testHeadException2() throws Exception {
        students.head(7); 
    }

    /**
     * Test of tail method, of class Dataframe.
     */
    @Test
    public void testTail() throws Exception {
        students.tail(1); 
    }
    
    /**
     * Test of tail method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testTailException1() throws Exception {
        students.tail(-1);
    }
    
    /**
     * Test of tail method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testTailException2() throws Exception {
        students.tail(7);
    }

   /**
     * Test of insertRow method, of class Dataframe.
     */
    @Test
    public void testInsertRow() throws Exception {
        assertEquals(6, students.nbRows());
        assertEquals(89, oscars.nbRows());
        
        String[] row1 = {"true", "toto", "1000", "12.5"};
        String[] row2 = {"90", "2020", "43", "test test", "test"};
        
        students.insertRow(row1);
        oscars.insertRow(row2);
        
        assertEquals(7, students.nbRows());
        assertEquals(90, oscars.nbRows());
    }
    
   /**
     * Test of insertRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testInsertRowException() throws Exception {
        String[] row1 = {"toto", "1000", "12.5"};
        students.insertRow(row1);
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
        emptyDf.insertColumn(col1);
    }
    
    /**
     * Test of insertColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testInsertColumnBadArgumentException2() throws Exception {
        Column col1 = new Column("prenom", "String");
        students.insertColumn(col1);
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
        emptyDf.dropColumn(0);
    }
    
   /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testDropColumnBadArgumentExeption1() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        emptyDf.insertColumn(col);
        emptyDf.dropColumn(1);
    }
    
    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testDropColumnBadArgumentExeption2() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        emptyDf.insertColumn(col);
        assertEquals(emptyDf.getLabels().size(),1);
        emptyDf.dropColumn(0);
        emptyDf.dropColumn(0);
    }
    
   /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testDropColumnBadArgumentExeption3() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        emptyDf.insertColumn(col);
        emptyDf.dropColumn(-1);
    }
    
    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = LabelNotFoundException.class)
    public void testDropColumnBadArgumentExeption4() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        emptyDf.insertColumn(col);
        emptyDf.dropColumn("test");
    }
    
    
    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = LabelNotFoundException.class)
    public void testDropColumnLabelNotFoundException() throws Exception {
        emptyDf.dropColumn("test");
    }
    
    /**
     * Test of dropColumn method, of class Dataframe.
     */
    @Test (expected = LabelNotFoundException.class)
    public void testDropColumnLabelNotFoundException2() throws Exception {
        Column col = new Column("testCol","Integer",  Arrays.asList(1, 2));
        emptyDf.insertColumn(col);
        assertEquals(emptyDf.getLabels().size(),1);
        assertTrue(emptyDf.containsLabel("testCol"));
        emptyDf.dropColumn("testCol");
        emptyDf.dropColumn("testCol");
    }

    /**
     * Test of pop method, of class Dataframe.
     */
    @Test
    public void testPop() throws Exception {
        Column col1 = new Column("testCol1","Integer",  Arrays.asList(1, 2));
        Column col2 = new Column("testCol2","Integer",  Arrays.asList(1, 2, 3));
        
        emptyDf.insertColumn(col1);
        emptyDf.insertColumn(col2);
        assertEquals(emptyDf.getLabels().size(),2);
        
        Column c = emptyDf.pop();
        assertEquals(c, col2);
        assertEquals(c.getName(), "testCol2");
        assertEquals(emptyDf.getLabels().size(),1);
        
        c = emptyDf.pop();
        assertEquals(c, col1);
        assertEquals(c.getName(), "testCol1");
        assertEquals(emptyDf.getLabels().size(),0);
    }
    
    
    /**
     * Test of pop method, of class Dataframe.
     */
    @Test (expected = IllegalStateException.class)
    public void testPopIllegalStateException() throws Exception {
        emptyDf.pop();
    }

    
    /**
     * Test of sum method, of class Dataframe.
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
     */
    @Test
    public void testMin() throws Exception {
        assertEquals(1,oscars.min("Index"),0.01);
        assertEquals(9.45,students.min("moyenne"),0.01);
    }
    
   /**
     * Test of min method, of class Dataframe.
     */
    @Test(expected = LabelNotFoundException.class)
    public void testMinLabelNotFoundException() throws Exception {
        oscars.min("test");
        cities.min("test");
        students.min("test");
    }
    
   /**
     * Test of min method, of class Dataframe.
     */
    @Test(expected = NotaNumberException.class)
    public void testMinNotaNumberException() throws Exception {
        oscars.min("Movie");
        cities.min("City");
        students.min("prenom");
    }

    /**
     * Test of max method, of class Dataframe.
     */
    @Test
    public void testMax() throws Exception {
        assertEquals(89,oscars.max("Index"),0.01);
        assertEquals(15.15,students.max("moyenne"),0.01);
    }
    
   /**
     * Test of min method, of class Dataframe.
     */
    @Test(expected = LabelNotFoundException.class)
    public void testMaxLabelNotFoundException() throws Exception {
        oscars.max("test");
        cities.max("test");
        students.max("test");
    }
    
   /**
     * Test of min method, of class Dataframe.
     */
    @Test(expected = NotaNumberException.class)
    public void testMaxNotaNumberException() throws Exception {
        oscars.max("Movie");
        cities.max("City");
        students.max("prenom");
    }

    /**
     * Test of mean method, of class Dataframe.
     */
    @Test
    public void testMean() throws Exception {
        assertEquals(45,oscars.mean("Index"),0.01);
        assertEquals(12.43,students.mean("moyenne"),0.01);
    }
    
   /**
     * Test of min method, of class Dataframe.
     */
    @Test(expected = LabelNotFoundException.class)
    public void testMeanLabelNotFoundException() throws Exception {
        oscars.mean("test");
        cities.mean("test");
        students.mean("test");
    }
    
   /**
     * Test of min method, of class Dataframe.
     */
    @Test(expected = NotaNumberException.class)
    public void testMeanNotaNumberException() throws Exception {
        oscars.mean("Movie");
        cities.mean("City");
        students.mean("prenom");
    }
   
   /**
     * Test of stats method, of class Dataframe.
     */
    @Test
    public void teststats() throws Exception {
        students.printStats("moyenne");
    }
   /**
     * Test of stats method, of class Dataframe.
     */
    @Test(expected = LabelNotFoundException.class)
    public void teststatsLabelNotFoundException() throws Exception {
        students.printStats("test");
    }
    
     /**
     * Test of stats method, of class Dataframe.
     */
    @Test(expected = NotaNumberException.class)
    public void teststatsNotANumberException() throws Exception {
        students.printStats("prenom");
    }

    /**
     * Test of setLabels method, of class Dataframe.
     */
    @Test
    public void testSetLabels() {
        emptyDf.setLabels(students.getLabels());
        assertEquals(students.getLabels(), emptyDf.getLabels());
    }

    /**
     * Test of getRow method, of class Dataframe.
     */
    @Test
    public void testGetRow() throws Exception {
        List<String> row1 = new ArrayList<String>(Arrays.asList("true", "Emma", "113090", "14.9"));
        List<String> row2 = students.getRow(3);
        assertEquals(row1, row2);
        
    }
    
   /**
     * Test of getRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testGetRowBadArgumentException1() throws Exception {
        students.getRow(-1);
    }
    
    /**
     * Test of getRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testGetRowBadArgumentException2() throws Exception {
        students.getRow(6);
    }

    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test
    public void testSelectionRow() throws Exception {
        Dataframe d = students.selectionRow(0, 2);
        assertEquals(3, d.nbRows());
        assertEquals(4, d.nbColumns());
    }
    
    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionRowException1() throws Exception {
        students.selectionRow(-1,4);
    }
    
    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionRowException2() throws Exception {
        oscars.selectionRow(1,89);
    }
    
    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionRowException3() throws Exception {
        students.selectionRow(5,3);
    }
    
   /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionRowException4() throws Exception {
        students.selectionRow(8,3);
    }
    
   /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionRowException5() throws Exception {
        students.selectionRow(3,-1);
    }
    
    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test 
    public void testSelectionColumns() throws Exception {
        List<String> list = new ArrayList<>(Arrays.asList("Name","Age"));
        Dataframe d = oscars.selectionColumns(list);
        assertEquals(2, d.nbColumns());
        assertEquals(89, d.nbRows());  
        assertTrue(d.containsLabel("Name"));
        assertTrue(d.containsLabel("Age"));
    }
    
    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionColumnsException1() throws Exception {
        students.selectionColumns(null);
    }
    
    /**
     * Test of selectionRow method, of class Dataframe.
     */
    @Test (expected = BadArgumentException.class)
    public void testSelectionColumnsException2() throws Exception {
        List<String> list = new ArrayList<>();
        students.selectionColumns(list);
    }
 
}

