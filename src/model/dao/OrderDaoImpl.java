package model.dao;

import model.entity.Customer;
import model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "keoratana13$";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public int addOrder(Order order) {
        String sql = "INSERT INTO order (id,order_name, order_description, cus_id,ordered_at) VALUES (?,?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Customer customer = order.getCustomer();
            if (customer == null || customer.getId() == null) {
                throw new IllegalArgumentException("Customer or Customer ID cannot be null");
            }

            ps.setString(1, order.getOrder_name());
            ps.setString(2, order.getOrderDescription());
            ps.setTimestamp(3, new Timestamp(order.getOrderdAt().getTime()));
            ps.setInt(4, customer.getId());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setId(rs.getInt(1));
                    }
                }
            }

            return rowsInserted;
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
            return -1;
        }

    }

    @Override
    public int updateOrder(Order order) {
        String sql = "UPDATE order SET order_name=?, order_description=?, ordered_at=?, customer_id=? WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, order.getOrder_name());
            ps.setString(2, order.getOrderDescription());
            ps.setTimestamp(3, new Timestamp(order.getOrderdAt().getTime()));
            ps.setInt(4, order.getCustomer().getId());
            ps.setInt(5, order.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM order WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM order WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = getCustomerById(rs.getInt("customer_id"));
                    return new Order(
                            rs.getInt("id"),
                            rs.getString("order_name"),
                            rs.getString("order_description"),
                            rs.getTimestamp("ordered_at"),
                            customer
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting order by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM order";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = getCustomerById(rs.getInt("customer_id"));
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getString("order_name"),
                        rs.getString("order_description"),
                        rs.getTimestamp("ordered_at"),
                        customer
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all orders: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public void clearAllOrders() {
        String sql = "DELETE FROM order";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error clearing all orders: " + e.getMessage());
        }
    }

    private Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getBoolean("is_deleted"),
                            rs.getTimestamp("created_date")
                    );
                } else {
                    System.out.println("No customer found with ID: " + customerId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer by ID: " + customerId + " - " + e.getMessage());
        }
        return null;
    }

}
