package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

    private static final String GET_ORDER = "SELECT " +
            "c.first_name, c.last_name, c.email, o.order_id" +
            "o.creation_date, o.total_date, o.status," +
            "s.first_name, s.last_name, s.email," +
            "ol.quantity, p.code, p.name, p.size, p.variety, p.price" +
        "FROM orders o" +
            "join customer c on o.customer_id=c.customer_id" +
            "join salesperson s on o.salesperson_id=s.salesperson_id" +
            "join order_item ol on ol.order_id=o.order_id" +
            "join product p on ol.product_id=p.product_id" +
        "WHERE o.order_id=?";

    public OrderDAO(Connection connection) { super(connection); }

    @Override
    public Order findById(long id) {
        Order order = new Order();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ORDER)) {
            statement.setLong(1, id);   // set sql query's positional parameter
            ResultSet rs = statement.executeQuery();
/*

            while (rs.next()) {

            }
*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order update(Order dto) {
        return null;
    }

    @Override
    public Order create(Order dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
