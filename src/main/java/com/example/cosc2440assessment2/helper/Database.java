package com.example.cosc2440assessment2.helper;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;

public class Database {
    private static Database INSTANCE;
    private Connection db;

    public Database() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String[] serverAddresses = { "localhost" };
        dataSource.setServerNames( serverAddresses );
        int[] serverPortNumbers = { 5000 };
        dataSource.setPortNumbers( serverPortNumbers );
//        dataSource.setSslCert( cert );
        dataSource.setDatabaseName( "postgres" );
        dataSource.setUser( "postgres" );
        dataSource.setPassword( "admin" );
        try {
            db = dataSource.getConnection();
        } catch (SQLException e) {
            db = null;
            System.err.println( "DEBUG - Postgres connection error. " + e.getMessage() + " " + Instant.now() );
        }
        System.out.println( "DEBUG - Postgres connection made. " + Instant.now() );
    }

    public static Database getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Database();
        return INSTANCE;
    }

    public Connection getDb() {
        return db;
    }
}
