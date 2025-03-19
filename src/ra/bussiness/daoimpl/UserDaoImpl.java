package ra.bussiness.daoimpl;

import ra.bussiness.config.ConnectDB;
import ra.bussiness.dao.IUserDao;
import ra.bussiness.dto.FormLogin;
import ra.bussiness.dto.UserInfo;
import ra.bussiness.entity.Categories;
import ra.bussiness.entity.User;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public User findByUsername(String username) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Users where username = ?");
            pre.setString(1,username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                User user =new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("full_name"),
                        rs.getDate("created_at")
                );
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    @Override
    public UserInfo login(FormLogin request) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Users where username = ? and password = ?");
            pre.setString(1, request.getUsername());
            pre.setString(2, request.getPassword());
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
               UserInfo userInfo =new UserInfo(
                       rs.getInt("user_id"),
                       rs.getString("email"),
                       rs.getString("phone"),
                       rs.getString("address"),
                       rs.getString("full_name")
               );
               return userInfo;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void save(User entity) {
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pre;
            if (findById(entity.getUserId()) != null) {
                // Update
                pre = conn.prepareStatement("UPDATE users set   full_name = ? ,phone= ?, address= ?, email =?  WHERE user_id = ?");
               pre.setString(1,entity.getFullName());
               pre.setString(2,entity.getPhoneNumber());
               pre.setString(3,entity.getAddress());
               pre.setString(4,entity.getEmail());
               pre.setInt(5,entity.getUserId());


            } else {
                // thêm mới
                pre = conn.prepareStatement("INSERT INTO users (username, password,email,address,phone, full_name) VALUES(?,?,?,?,?,?)");
                pre.setString(1,entity.getUsername());
                pre.setString(2,entity.getPassword());
                pre.setString(3,entity.getEmail());
                pre.setString(4,entity.getAddress());
                pre.setString(5,entity.getPhoneNumber());
                pre.setString(6,entity.getFullName());
            }
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public boolean existByUsername(String username) {
        Connection conn = ConnectDB.openConnection();
        try{
            PreparedStatement pre = conn.prepareStatement("SELECT * from Users where username = ?");
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
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
    public boolean existByEmail(String email) {
        return false;
    }

    @Override
    public boolean existByPhoneNumber(String phone) {
        return false;
    }
}
