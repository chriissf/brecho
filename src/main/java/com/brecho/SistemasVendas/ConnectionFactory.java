/*package com.brecho.SistemasVendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao() {

        try {
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/brecho?user=christian&password=mysql");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}*/