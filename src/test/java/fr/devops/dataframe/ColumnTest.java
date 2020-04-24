/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import java.util.Arrays;
import java.util.List;
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
public class ColumnTest {
    
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);
    static Column Prenom, Numero, Admis, Moyenne, Taille;
    
    @BeforeClass
    public static void setUpClass() {
        List<String> prenom = Arrays.asList("Léa", "Claude", "Tony", "Emma", "Ali", "Sarah");
        List<Integer> numero = Arrays.asList(10, 11, 15, 9, 2, 6);                         //sum 53 mean 8,83 min 2 max 6
        List<Boolean> estAdmis = Arrays.asList(false, true, true, true, false, true);      
        List<Double> moyenne = Arrays.asList(9.50, 8.0, 12.0, 11.0, 9., 10.5);             //sum 60 mean 10   min 8 max 12
        List<Float> taille = Arrays.asList(1.60f, 1.75f, 1.90f, 1.54f, 1.79f, 1.62f); 
        
        Prenom  = new Column("prenom" , "String" , prenom);
        Numero  = new Column("numero" , "Integer", numero);
        Admis   = new Column("admis"  , "Boolean", estAdmis);
        Moyenne = new Column("moyenne", "Double" , moyenne);
        Taille = new Column("taille", "Float" , taille);
    }
    
    @AfterClass
    public static void tearDownClass() {
        Prenom  = null;
        Numero  = null;
        Admis   = null;
        Moyenne = null;
        Taille = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
        
    } 
    
    /**
     * Test of getName method, of class Column.
     */
    @Test
    public void testConstructor() {
        Column col = new Column("col1", "integer");
        assertEquals(col.size(), 0);
        assertEquals(Prenom.size(), 6);
        col.addValue(10);
        col.addValue(7);
        assertEquals(col.size(), 2);     
    }

    /**
     * Test of getName method, of class Column.
     */
    @Test
    public void testGetName() {
        assertEquals("prenom", Prenom.getName());
        assertEquals("numero", Numero.getName());
        assertEquals("admis", Admis.getName());
        assertEquals("moyenne", Moyenne.getName());
        assertEquals("taille", Taille.getName());
    }

    /**
     * Test of getType method, of class Column.
     */
    @Test
    public void testGetType() {
        assertEquals("String" , Prenom.getType());
        assertEquals("Integer", Numero.getType());
        assertEquals("Boolean", Admis.getType());
        assertEquals("Double" , Moyenne.getType());
        assertEquals("Float" , Taille.getType());
    }

    /**
     * Test of getValues method, of class Column.
     */
    @Test
    public void testGetValues() {
        assertEquals(Arrays.asList("Léa", "Claude", "Tony", "Emma", "Ali", "Sarah"), Prenom.getValues());
        assertEquals(Arrays.asList(10, 11, 15, 9, 2, 6), Numero.getValues());
        assertEquals(Arrays.asList(false, true, true, true, false, true), Admis.getValues());
        assertEquals(Arrays.asList(9.50, 8.0, 12.0, 11.0, 9., 10.5), Moyenne.getValues());
        assertEquals(Arrays.asList(1.60f, 1.75f, 1.90f, 1.54f, 1.79f, 1.62f), Taille.getValues());
    }

    /**
     * Test of isDigit method, of class Column.
     */
    @Test
    public void testIsDigit() {
        assertTrue(Moyenne.isDigit());
        assertTrue(Numero.isDigit());
        assertTrue(Taille.isDigit());
    }
    
    @Test
    public void testIsDigitFalse() {
        assertFalse(Prenom.isDigit());
        assertFalse(Admis.isDigit());
        
    }
    
    /**
     * Test of isDigit method, of class Column.
     */
    @Test
    public void testContains() {
        assertTrue(Moyenne.contains(9.50));
        assertTrue(Moyenne.contains(12.));  
        assertTrue(Admis.contains(false));
        assertTrue(Admis.contains(true));      
        assertTrue(Prenom.contains("Tony"));
        assertTrue(Prenom.contains("Sarah"));
        assertTrue(Numero.contains(11));
        assertTrue(Numero.contains(6));
    }
    
    @Test
    public void testContainsFalse() {
        assertFalse(Moyenne.contains(9.44));
        assertFalse(Moyenne.contains(0));       
        assertFalse(Prenom.contains("Toto"));
        assertFalse(Prenom.contains("Sara"));
        assertFalse(Numero.contains(1));
        assertFalse(Numero.contains(8));
    }
    
}
