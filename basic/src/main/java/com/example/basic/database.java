package com.example.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database
{
    public Connection conn;
    public database()
    {

    }
    public void connectDb()
    {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "tanbir114";

        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, pass);
            System.out.println("done");
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
