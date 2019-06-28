package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

    private static final String GET_ORDER = "SELECT " +
            "c.first_name as c_first_name, c.last_name c_last_name, " +
            "c.email c_email, o.order_id," +
            "o.creation_date, o.total_due, o.status," +
            "s.first_name, s.last_name, s.email," +
            "ol.quantity, p.code, p.name, p.size, p.variety, p.price " +
        "FROM orders o " +
            "join customer c on o.customer_id=c.customer_id " +
            "join salesperson s on o.salesperson_id=s.salesperson_id " +
            "join order_item ol on ol.order_id=o.order_id " +
            "join product p on ol.product_id=p.product_id " +
        "WHERE o.order_id=?";

    public OrderDAO(Connection connection) { super(connection); }

    @Override
    public Order findById(long id) {
        Order order = new Order();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ORDER)) {
            statement.setLong(1, id);   // set sql query's positional parameter
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order.setId(rs.getLong("order_id"));
                order.setCreationDate(rs.getDate("creation_date"));
                order.setTotalDue(rs.getBigDecimal("total_due"));
                order.setStatus(rs.getString("status"));
                order.setCustomerEmail(rs.getString("c_email"));
                order.setCustomerFName(rs.getString("c_first_name"));
                order.setCustomerLName(rs.getString("c_last_name"));
                order.setSalesFName(rs.getString("first_name"));
                order.setSalesLName(rs.getString("last_name"));
                order.setSalesEmail(rs.getString("email"));
//                order.setCustomerId(rs.getLong("customer_id"));
//                order.setSalespersonId(rs.getLong("salesperson_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return order;
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
