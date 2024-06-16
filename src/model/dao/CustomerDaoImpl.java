package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "keoratana13$";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public int addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email, password, is_deleted, create_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setBoolean(4, customer.getIsDeleted());
            ps.setTimestamp(5, new Timestamp(customer.getCreateDate().getTime()));

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        customer.setId(rs.getInt(1));
                    }
                }
            }

            return rowsInserted;
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public int updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name = ?, email = ?, password = ?, is_deleted = ?, create_date = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setBoolean(4, customer.getIsDeleted());
            ps.setTimestamp(5, new Timestamp(customer.getCreateDate().getTime()));
            ps.setInt(6, customer.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customers WHERE id = ?";
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
                            rs.getTimestamp("create_date")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("is_deleted"),
                        rs.getTimestamp("create_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all customers: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public void clearAllCustomers() {
        String sql = "DELETE FROM customers";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error clearing all customers: " + e.getMessage());
        }
    }
}
