package JDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TasksCRUD implements ICRUD{
    final String selectAll = "select * from tasks";
    final String insertData = "insert into tasks(title) values(?)";
    final String updateDone = "UPDATE tasks SET done = NOT done WHERE id = ?";
    final String updateTitle = "update tasks set title = ? where id = ?";
    final String deleteData = "delete from tasks where id = ?";
    ArrayList<Tasks> list;
    Scanner s;
    Connection conn;

    TasksCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
        conn = DBConnection.getConnection();
    }
    @Override
    public int add(String title) {
        int retval = 0;
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(insertData);
            pstmt.setString(1, title);
            retval = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retval;
    }

    public void addTask(){
        System.out.print("\n>> 할 일 입력: ");
        String task = s.nextLine();
        int result = add(task);
        if(result > 0) System.out.println("\n할 일이 추가되었습니다.");
        else if(result == 0) System.out.println("\n할 일 추가에 실패하였습니다.");
    }

    public void loadData(){
        list.clear();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectAll);

            while (rs.next()){
                long id = rs.getInt("id");
                String title = rs.getString("title");
                boolean done = rs.getBoolean("done");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                list.add(new Tasks(id, title, done, created_at));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listTask(){
        loadData();
        System.out.println("\n**할 일 목록**");
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).toString() + "\n");
        }
    }

    @Override
    public int update(long id) {
        int retval = 0;
        PreparedStatement pstmt;
        System.out.print(">> 새 할 일 입력: ");
        String title = s.nextLine();
        try {
            pstmt = conn.prepareStatement(updateTitle);
            pstmt.setString(1, title);
            pstmt.setLong(2, id);
            retval = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retval;
    }

    public int toggleDone(long id){
        int retval = 0;
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(updateDone);
            pstmt.setLong(1, id);
            retval = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retval;
    }

    public void updateTask(){
        System.out.print("> 수정하고 싶은 할 일은?: ");
        int target = s.nextInt();

        System.out.print("\n1) 할 일 수정 2) 완료 / 미완료 변경\n> 원하는 메뉴는? ");
        int menu = s.nextInt();
        s.nextLine();

        int result = 0;
        if(menu == 1) result = update(target);
        else if(menu == 2) result = toggleDone(target);


        if(result == 0){
            System.out.println("\n존재하지 않는 id입니다.\n");
        }
        else if(result > 0){
            System.out.println("\n할 일이 수정되었습니다.\n");
        }
    }


    @Override
    public int delete(long id){
        int retval = 0;
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(deleteData);
            pstmt.setLong(1, id);
            retval = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retval;
    }

    public void deleteTask(){
        System.out.print("> 삭제하고 싶은 할 일은?: ");
        int target = Integer.parseInt(s.nextLine());
        int result =  delete(target);
        if(result == 0){
            System.out.println("\n존재하지 않는 id입니다.\n");
        }
        else if(result > 0){
            System.out.println("\n할 일이 삭제되었습니다.\n");
        }
    }
}
