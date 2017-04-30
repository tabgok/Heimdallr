/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teague.harvesters;

import com.tabgok.data.Data;
import com.tabgok.harvesters.CurrentTimeHarvester;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tbick
 */
public class CurrentTimeHarvesterTest {
    
    public CurrentTimeHarvesterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void SimpleTest(){
        CurrentTimeHarvester h = new CurrentTimeHarvester();
        
        Data timeData = h.gatherData();
        long time = System.currentTimeMillis();
        
        assert timeData.getData() instanceof Long && Math.abs(((Long)timeData.getData())-time) <= 1;
    }
}
