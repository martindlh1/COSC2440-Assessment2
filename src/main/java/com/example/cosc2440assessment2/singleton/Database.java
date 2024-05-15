package com.example.cosc2440assessment2.singleton;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;

public class Database {
    private static Database INSTANCE;
    private Connection db;

    public Database() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String[] serverAddresses = { "aws-0-ap-southeast-1.pooler.supabase.com" };
        dataSource.setServerNames( serverAddresses );
        int[] serverPortNumbers = { 5432 };
        dataSource.setPortNumbers( serverPortNumbers );
//        dataSource.setSslCert( cert );
        dataSource.setDatabaseName( "postgres" );
        dataSource.setUser( "postgres.ndvppnalkmigbljbkrxk" );
        dataSource.setPassword( "rR?reW(,CiNF/F5" );
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
