package JDBC;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksCRUD implements ICRUD{
    ArrayList<Tasks> list;
    Connection conn;
    Scanner s;

    TasksCRUD(Scanner s){
        this.s = s;
        list = new ArrayList<>();
        conn = DBConnection.getConnection();
    }

    public void loadData(){
        list.clear();
        String selectALl = new String("select * from tasks");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectALl);
            while(true){
                if(!rs.next()) break;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean done = false;
                if(rs.getInt("done") == 0) done = false;
                else if(rs.getInt("done") == 1) done = true;
                String created_at = rs.getString("created_at");

                list.add(new Tasks(id, title, done, created_at));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void listTasks(){
        for(Tasks t : list){
            System.out.println(t);
        }
    }

    @Override
    public Object add() {
        return null;
    }

    @Override
    public int update(long id) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
