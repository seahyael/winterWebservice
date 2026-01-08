package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionTest{
    public static void main(String[] args){
        Properties props = DBConfig.load();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB connected");
        }
        catch (SQLException e){
            System.out.println("DB connection error");
            e.printStackTrace();
        }

    }
}