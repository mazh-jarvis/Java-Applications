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
                        "jdbc_app", "postgres", "petrop");
        try {
            Connection connection = connectionManager.getConnection();
            /*CustomerDAO customerDAO = new CustomerDAO(connection);*/
            /*Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Smith");
            customer.setEmail("john.smith@fake.com");
            customer.setPhone("(555) 432-1234");
            customer.setAddress("123 Main St");
            customer.setCity("York");
            customer.setState("Eoferwic");
            customer.setZipCode("95723");
            customerDAO.create(customer);
*/
           /*
            Customer customer = customerDAO.findById(1000);
            System.out.println(customer);*/
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1050);
            connection.close();
            System.out.println(order);
            /*Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER;");*/

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
