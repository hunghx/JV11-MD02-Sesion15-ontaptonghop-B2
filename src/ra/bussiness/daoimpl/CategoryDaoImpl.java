package ra.bussiness.daoimpl;

import ra.bussiness.config.ConnectDB;
import ra.bussiness.dao.ICategoryDao;
import ra.bussiness.dto.CategoryAndCountProduct;
import ra.bussiness.entity.Categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    @Override
    public List<Categories> findAll() {
        List<Categories> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Categories");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Categories cat = new Categories(
                        rs.getInt("catalog_id"),
                        rs.getString("catalog_name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                );
                list.add(cat);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Categories findById(Integer id) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Categories where  catalog_id= ?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                Categories cat = new Categories(
                        rs.getInt("catalog_id"),
                        rs.getString("catalog_name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                );
                return cat;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    @Override
    public void save(Categories entity) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre;
            if(findById(entity.getCategoryId())!=null){
                // Update
                pre = conn.prepareStatement("UPDATE Categories set catalog_name=?, description= ?,status = ? WHERE catalog_id = ?");
                pre.setBoolean(3,entity.isStatus());
                pre.setInt(4,entity.getCategoryId());
            }else {
                // thêm mới
                pre = conn.prepareStatement("INSERT INTO Categories (catalog_name, description) VALUES(?,?)");
            }
            pre.setString(1,entity.getCategoryName());
            pre.setString(2,entity.getDescription());
            pre.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("DELETE FROM Categories WHERE catalog_id = ?");
            pre.setInt(1,id);  // set id cần xóa vào parameter 1  của preparedStatement
            pre.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void toggleStatus(int id) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("Update Category set status = !status where catalog_id = ?");
            pre.setInt(1,id);  // set id cần xóa vào parameter 1  của preparedStatement
            pre.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public List<Categories> findByName(String name) {
        List<Categories> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Categories where catalog_name like ?");
            pre.setString(1, "%"+name+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Categories cat = new Categories(
                        rs.getInt("catalog_id"),
                        rs.getString("catalog_name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                );
                list.add(cat);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean existProductByCategoryId(int categoryId) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Products where catalog_id = ?");
            pre.setInt(1, categoryId);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public List<CategoryAndCountProduct> countProductGroupByCategory() {
        List<CategoryAndCountProduct> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("Select c.catalog_name,count(p.product_id) as count_product from Categories c join Products p on c.catalog_id= p.catalog_id "+
                    " group by c.catalog_id");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                CategoryAndCountProduct cat = new CategoryAndCountProduct(
                        rs.getString("catalog_name"),
                        rs.getInt("count_product")
                );
                list.add(cat);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }
}
