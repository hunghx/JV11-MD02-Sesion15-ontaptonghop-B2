package ra.bussiness.daoimpl;

import ra.bussiness.config.ConnectDB;
import ra.bussiness.dao.IProductDao;
import ra.bussiness.dto.ProductDto;
import ra.bussiness.entity.Categories;
import ra.bussiness.entity.Products;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    @Override
    public List<ProductDto> findAllProductDetail() {
        List<ProductDto> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pre = conn.prepareStatement("select p.product_id,p.product_name,p.price,p.status,c.catalog_name from products p join categories c on  p.catalog_id = c.catalog_id;");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ProductDto p = new ProductDto(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("price"),
                        rs.getByte("status"),
                        rs.getString("catalog_name")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<Products> findAll() {
        List<Products> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pre = conn.prepareStatement("SELECT * from Products");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("catalog_id"),
                        rs.getString("description"),
                        rs.getByte("status"),
                        rs.getDate("created")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Products findById(String id) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Products where product_id = ?");
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                Products p = new Products(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("catalog_id"),
                        rs.getString("description"),
                        rs.getByte("status"),
                        rs.getDate("created")
                );
                return p;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    @Override
    public void save(Products entity) {
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pre;
            if (findById(entity.getProductId()) != null) {
                // Update
                pre = conn.prepareStatement("UPDATE Categories set  catalog_id = ?, product_name=?, price=?, description=?, created=?, status=? WHERE product_id = ?");
                pre.setInt(1, entity.getCategoryId());
                pre.setString(2, entity.getProductName());
                pre.setBigDecimal(3, entity.getPrice());
                pre.setString(4, entity.getDescription());
                pre.setDate(5, new Date(entity.getCreatedAt().getTime()));
                pre.setByte(6, entity.getStatus());
                pre.setString(7, entity.getProductId());

            } else {
                // thêm mới
                pre = conn.prepareStatement("INSERT INTO Categories (product_id, catalog_id, product_name, price, description, created, status) VALUES(?,?,?,?,?,?,?)");
                pre.setInt(2, entity.getCategoryId());
                pre.setString(3, entity.getProductName());
                pre.setBigDecimal(4, entity.getPrice());
                pre.setString(5, entity.getDescription());
                pre.setDate(6, new Date(entity.getCreatedAt().getTime()));
                pre.setByte(7, entity.getStatus());
                pre.setString(1, entity.getProductId());
            }
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteById(String id) {
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pre = conn.prepareStatement("DELETE FROM Products WHERE product_id = ?");
            pre.setString(1, id);  // set id cần xóa vào parameter 1  của preparedStatement
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public List<Products> filterByPrice(BigDecimal start, BigDecimal end) {
        List<Products> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pre = conn.prepareStatement("SELECT * from Products Where price between ? and ?");
            pre.setBigDecimal(1, start);
            pre.setBigDecimal(2, end);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Products p = new Products(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("catalog_id"),
                        rs.getString("description"),
                        rs.getByte("status"),
                        rs.getDate("created")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }
}
