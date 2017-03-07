/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teague.heimdallr;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
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
public class ActorTest {
    private Gson gson = new Gson();
    
    public ActorTest() {
        
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        Actor a = new Actor();
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
    public void testDiskInfo() throws Exception {
        String strUrl = "http://localhost:8000/DiskInfo";
    }
    
    @Test
    public void testEpoch() throws Exception {
        String strUrl = "http://localhost:8000/epoch";

        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
            Scanner scan = new Scanner(urlConn.getInputStream());
            String input = scan.nextLine();
            Data d = gson.fromJson(input, Data.class);

            assertTrue(d.getValue() instanceof Double);
            assertTrue(d.getUnit().equals("seconds"));
            assertEquals(d.getVariable(), "epoch");
            assertTrue(((Double)d.getValue()) > 1000000000);
            
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }
    
    @Test
    public void testLiveness() throws Exception {
        String strUrl = "http://localhost:8000/isAlive";

        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            
            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }
}
