package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    private static final long ORDER_ID = 1050;
    public static void main(String[] args) {
        DatabaseConnectionManager connectionManager =
                new DatabaseConnectionManager("localhost",
                        "jdbc_app", "postgres", "password");
        try {
            Connection connection = connectionManager.getConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1050);
            connection.close();
            System.out.println(order);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
