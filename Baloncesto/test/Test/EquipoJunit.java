package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Clases.Equipo;


import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author inftel06
 */

public class EquipoJunit {
    
    Equipo equipo1;
    Equipo equipo2;
    Equipo equipo3;
    Equipo equipo4;
    Equipo equipo5;
    Equipo equipo6;
    Equipo equipo7;
    Equipo equipoComp1;
    Equipo equipoComp2;
    
    
    public EquipoJunit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        equipo1=new Equipo(1,"nombreEq1","provinciaEq1",1);
        equipo2=new Equipo(1,"nombreEq1","provinciaEq1",1);
        equipo3=new Equipo(2,"nombreEq1","provinciaEq1",1);
        equipo4=new Equipo(1,"nombreEq4","provinciaEq1",1);
        equipo5=new Equipo(1,"nombreEq1","provinciaEq5",1);
        equipo6=new Equipo(1,"nombreEq1","provinciaEq1",2);
        equipo7=new Equipo(1,"nombreEq7","provinciaEq7",2);
        //Equipos que han de estar en la BBDD
        equipoComp1= new Equipo(6,"moscardo22","sevilla",1);
        equipoComp2= new Equipo(1,"madrid","madrid",2);
       
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test 
    // public void hello() {}
    
    @Test
    public void testEquals(){
        Assert.assertTrue(equipo1.equals(equipo2));
        Assert.assertTrue(!equipo1.equals(equipo3));
        Assert.assertTrue(!equipo1.equals(equipo4));
        Assert.assertTrue(!equipo1.equals(equipo5));
        Assert.assertTrue(!equipo1.equals(equipo6));
        
            
    }
    
    @Test
    public void testgetNombre(){
        String nombreE1=equipo1.getNombre();
        String nombreE2="nombreEq1";
        Assert.assertEquals(nombreE1,nombreE2);
       
    }
    
    @Test
    public void testsetNombre(){
        String nombre="Madrid";
        equipo7.setNombre(nombre);
        String nombre2=equipo7.getNombre();
        Assert.assertEquals(nombre,nombre2);
    }
    
    @Test
    public void testgetId(){
        int idE1=equipo1.getIdEquipo();
        int idE2=1;
        Assert.assertEquals(idE1,idE2);
       
    }
    
    @Test
    public void testsetId(){
        int id_equipo=13;
        equipo7.setIdEquipo(id_equipo);
        int id_equipo2=equipo7.getIdEquipo();
        Assert.assertEquals(id_equipo,id_equipo2);
    }
    
    
    @Test
    public void testgetCategoria(){
        int CatE1=equipo1.getCategoria();
        int CatE2=1;
        Assert.assertEquals(CatE1,CatE2);
       
    }
    
    @Test
    public void testsetCategoria(){
        int id_cat1=13;
        equipo7.setCategoria(id_cat1);
        int id_cat2=equipo7.getCategoria();
        Assert.assertEquals(id_cat1,id_cat2);
    }
    
        @Test
    public void testgetProvincia(){
        String provinciaE1=equipo1.getProvincia();
        String provinciaE2="provinciaEq1";
        Assert.assertEquals(provinciaE1,provinciaE2);
       
    }
    
    @Test
    public void testsetProvincia(){
        String provinciaE1="Provincia";
        equipo7.setProvincia(provinciaE1);
        String provinciaE2=equipo7.getProvincia();
        Assert.assertEquals(provinciaE1,provinciaE2);
    }
    
    
    @Test
    public void testCompareTo(){
       
       
        int comparacion_mayor=equipoComp2.compareTo(equipoComp1);
        int comparacion_menor=equipoComp1.compareTo(equipoComp2);
        int comparacion_igual=equipoComp2.compareTo(equipoComp2);
    

        Assert.assertEquals(1, comparacion_mayor);
        Assert.assertEquals(0, comparacion_igual);
        Assert.assertEquals(-1, comparacion_menor);
        
    }
}