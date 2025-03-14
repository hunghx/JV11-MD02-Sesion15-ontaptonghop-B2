package ra.bussiness.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/coffee_store";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456$";

    public static Connection openConnection(){
        try{
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return conn;
        }catch (ClassNotFoundException e){
            System.err.println("ko tìm thấy driver");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection conn){
        if(conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
