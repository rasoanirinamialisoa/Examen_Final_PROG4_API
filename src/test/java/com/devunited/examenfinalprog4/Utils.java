package com.devunited.examenfinalprog4;

import java.sql.Connection;

public class Utils {
    private static Connection connection;

    public static void getMockConnection(Connection mockConnection) {
        connection = mockConnection;
    }

    public static void resetConnection() {
        connection = null;
    }
}
