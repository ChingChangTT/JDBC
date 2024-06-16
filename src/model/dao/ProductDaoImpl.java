package model.dao;

import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {

    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<Product> queryAllProducts() {
        String sql = """
                SELECT * FROM "product"
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            List<Product> productList = new ArrayList<>();
            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("product_code"),
                        rs.getBoolean("is_deleted"),
                        rs.getDate("imported_at"),
                        rs.getDate("expired_at"),
                        rs.getString("product_description")
                ));
            }
            return productList;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int addNewProduct(Product product) {
        String sql = """
                INSERT INTO product (id, product_name, product_code, is_deleted, imported_at, expired_at, product_description)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            pre.setInt(1, product.getId());
            pre.setString(2, product.getProductName());
            pre.setString(3, product.getProdcutCode());
            pre.setBoolean(4, product.getIsDeleted());
            pre.setDate(5, product.getImportedDate());
            pre.setDate(6, product.getExpiredDate());
            pre.setString(7, product.getProductDescription());
            return pre.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateProduct(Integer id) {
        String sql = """
                UPDATE "product" 
                SET product_name=?, product_code=?, product_description=?
                WHERE id=?
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            Product product = searchProductById(id);
            if(product != null){
                System.out.print("Enter new product name: ");
                pre.setString(1, new Scanner(System.in).nextLine());
                System.out.print("Enter new product code: ");
                pre.setString(2, new Scanner(System.in).nextLine());
                System.out.print("Enter new description: ");
                pre.setString(3, new Scanner(System.in).nextLine());
                pre.setInt(4, id);
                return pre.executeUpdate();
            }
            else{
                System.out.println("Product not found");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteProduct(Integer id) {
        String sql = """
                DELETE FROM "product" WHERE id=?
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            pre.setInt(1, id);
            int rowAffected = pre.executeUpdate();
            String message = rowAffected > 0 ? "Product deleted successfully" : "Failed to delete product with id ";
            System.out.println(message);
            return rowAffected;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Product searchProductById(Integer id) {
        String sql = """
                SELECT * FROM "product" WHERE id = ?
                """;
        try(
                Connection conn = customerDao.connectionToDataBase();
                PreparedStatement pre = conn.prepareStatement(sql);
                ){
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            Product product = new Product();

            while (rs.next()) {
                product = Product.builder()
                                .id(rs.getInt("id"))
                                .productName(rs.getString("product_name"))
                                .productName(rs.getString("product_code"))
                                .isDeleted(rs.getBoolean("is_deleted"))
                                .importedDate(rs.getDate("imported_at"))
                                .expiredDate(rs.getDate("expired_at"))
                                .productDescription(rs.getString("product_description"))
                        .build();
            }
            return product;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
