package model.dao;

import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductDaoImpl implements ProductDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "keoratana13$";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public int addProduct(Product product) {
        String sql = "INSERT INTO product (product_name, product_code, product_description, imported_at, expired_at, is_deleted) VALUES (?,?,?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Generate a random product code
            String productCode = generateRandomProductCode(8); // Adjust length as needed

            ps.setString(1, product.getProductName());
            ps.setString(2, productCode); // Use the generated product code
            ps.setString(3, product.getProductDescription());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)); // 30 days
            ps.setBoolean(6, false);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        product.setId(rs.getInt(1));
                    }
                }
            }

            return rowsInserted;
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return -1;
        }
    }

    // Method to generate a random product code
    private String generateRandomProductCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    @Override
    public int updateProduct(Product product) {
        String sql = "UPDATE product SET product_name=?, product_code=?, product_description=?, imported_at=?, expired_at=?, is_deleted=? WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductCode()); // Assuming product code can be updated
            ps.setString(3, product.getProductDescription());
            ps.setTimestamp(4, new Timestamp(product.getImportedDate().getTime()));
            ps.setTimestamp(5, new Timestamp(product.getExpireDate().getTime()));
            ps.setBoolean(6, product.isDeleted());
            ps.setInt(7, product.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("product_name"),
                            rs.getString("product_code"),
                            rs.getBoolean("is_deleted"),
                            rs.getTimestamp("imported_at"),
                            rs.getTimestamp("expired_at"),
                            rs.getString("product_description")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting product by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("product_code"),
                        rs.getBoolean("is_deleted"),
                        rs.getTimestamp("imported_at"),
                        rs.getTimestamp("expired_at"),
                        rs.getString("product_description")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all products: " + e.getMessage());
        }
        return products;
    }

    @Override
    public void clearAllProducts() {
        String sql = "DELETE FROM product";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error clearing all products: " + e.getMessage());
        }
    }
}
