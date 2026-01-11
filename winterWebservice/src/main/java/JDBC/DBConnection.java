package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Connection conn = null;
    public static Connection getConnection(){
        Properties props = DBConfig.load();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        if(conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                System.out.println("DB connection error");
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}