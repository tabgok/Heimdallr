package com.tabgok.database.postgres;

import com.tabgok.database.EntityDatabase;
import com.tabgok.entity.Machine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PostgresConnector implements EntityDatabase {
    private Connection connection;
    public PostgresConnector(){
        
    }
    
    private void connect(){
        try {
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "heimdallr", "heimdallr");
            Statement statement = c.createStatement();
            statement.executeUpdate("CREATE DATABASE heimdallrDB");
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void storeMachine(Machine m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
