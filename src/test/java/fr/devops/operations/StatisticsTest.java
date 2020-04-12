/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.operations;

import fr.devops.Exceptions.NotaNumberException;
import static fr.devops.operations.Statistics.Max;
import static fr.devops.operations.Statistics.Mean;
import static fr.devops.operations.Statistics.Min;
import static fr.devops.operations.Statistics.Sum;
import fr.devops.dataframe.Column;
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
public class StatisticsTest {
    
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);
    static Column Prenom, Numero, Admis, Moyenne, Taille;
    
    
    @BeforeClass
    public static void setUpClass() {
        List<String> prenom = Arrays.asList("Léa", "Claude", "Tony", "Emma", "Ali", "Sarah");
        List<Integer> numero = Arrays.asList(10, 11, 15, 9, 2, 6);                         //sum 53 mean 8,83 min 2 max 6
        List<Boolean> estAdmis = Arrays.asList(false, true, true, true, false, true);      
        List<Double> moyenne = Arrays.asList(9.85, 8.53, 12.77, 13.20, 9.20, 10.58);             //sum 60 mean 10   min 8 max 12
        List<Float> taille = Arrays.asList(1.60f, 1.70f, 1.90f, 1.50f, 1.80f, 1.60f); 
        
        Prenom  = new Column("prenom" , "String" , prenom);
        Numero  = new Column("numero" , "Integer", numero);
        Admis   = new Column("Admis"  , "Boolean", estAdmis);
        Moyenne = new Column("moyenne", "Double" , moyenne);   
        Taille = new Column("taille"  , "Float" , taille); 
    }
    
    @AfterClass
    public static void tearDownClass() {
        Prenom  = null;
        Numero  = null;
        Admis   = null;
        Moyenne = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Min method, of class Statistics.
     * @throws java.lang.Exception
     */
    @Test
    public void testMin() throws Exception {
        assertEquals(8.53, Min(Moyenne), 0.01);
        assertEquals(2.  , Min(Numero),0.01);
        assertEquals(1.50, Min(Taille),0.01);
        
        
    }
    
    @Test(expected = NotaNumberException.class)
    public void testBooleanMinException() throws Exception {
        Min(Admis);
    }
    
    @Test(expected = NotaNumberException.class)
    public void testStringMinException() throws Exception {
        Min(Prenom);
    }

    
    /**
     * Test of Max method, of class Statistics.
     */
    @Test
    public void testMax() throws Exception {
        assertEquals(13.20, Max(Moyenne), 0.01);
        assertEquals(15.  , Max(Numero),0.01);
        assertEquals(1.90 , Max(Taille),0.01);
    }
        
    @Test(expected = NotaNumberException.class)
    public void testBooleanMaxException() throws Exception {
        Max(Admis);
    }
    
    @Test(expected = NotaNumberException.class)
    public void testStringMaxException() throws Exception {
        Max(Prenom);
    }

    /**
     * Test of Sum method, of class Statistics.
     */
    @Test
    public void testSum() throws Exception {
        assertEquals(64.13, Sum(Moyenne), 0.01);
        assertEquals(53.  , Sum(Numero) ,0.01);
        assertEquals(10.1, Sum(Taille) ,0.01);
    }
    
    @Test(expected = NotaNumberException.class)
    public void testBooleanSumException() throws Exception {
        Sum(Admis);
    }
    
    @Test(expected = NotaNumberException.class)
    public void testStringSumException() throws Exception {
        Sum(Prenom);
    }

    /**
     * Test of Mean method, of class Statistics.
     */
    @Test
    public void testMean() throws Exception {
        assertEquals(10.68, Mean(Moyenne), 0.01);
        assertEquals(8.83 , Mean(Numero),0.01);
        assertEquals(1.68 , Mean(Taille),0.01);
    }

    @Test(expected = NotaNumberException.class)
    public void testStringMeanException() throws Exception {
        Mean(Prenom);
    }
    
    @Test(expected = NotaNumberException.class)
    public void testBooleanMeanException() throws Exception {
        Mean(Admis);
    }
}
