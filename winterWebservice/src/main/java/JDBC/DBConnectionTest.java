package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest{
    public static void main(String[] args){
        String url = "jdbc:mariadb://localhost:3306/campdb";
        String user = "root";
        String password = "camp1234";

        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println("DB connected");
        } catch (SQLException e) {
            System.out.println("DB connection error");
            e.printStackTrace();
        }
    }
}