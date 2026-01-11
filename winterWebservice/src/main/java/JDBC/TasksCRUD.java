package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TasksCRUD implements ICRUD{
    ArrayList<Tasks> list;
    Scanner s;
    Connection conn;

    TasksCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
        conn = DBConnection.getConnection();
    }
    @Override
    public Object add() {
        System.out.print("\n>> 할 일 입력: ");
        String task = s.nextLine();
        return new Tasks();
    }

    public void addTask(){
        Tasks one = (Tasks) add();
        list.add(one);
        System.out.println("\n할 일이 추가되었습니다.");
    }

    public void loadData(){
        list.clear();
        String selectAll = "select * from tasks";
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
    public int update(long i) {
        for (Tasks t : list) {
            if (t.getId() == i) {
                System.out.print("\n1) 할 일 수정 2) 완료 / 미완료 변경\n> 원하는 메뉴는? ");
                int menu = s.nextInt();
                s.nextLine();
                if (menu == 1) {
                    System.out.print(">> 새 할 일 입력: ");
                    t.setTitle(s.nextLine());
                } else if (menu == 2) {
                    t.setDone(!t.isDone());
                }
                return 1;
            }
        }
        return 0;
    }

    public void updateTask(){
        System.out.print("> 수정하고 싶은 할 일은?: ");
        int target = Integer.parseInt(s.nextLine());
        int result =  update(target);
        if(result == 0){
            System.out.println("\n존재하지 않는 id입니다.\n");
        }
        else if(result == 1){
            System.out.println("\n할 일이 수정되었습니다.\n");
        }
    }


    @Override
    public int delete(long i){
        long targetId = i;
        Iterator<Tasks> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == targetId) {
                it.remove();
                return 1;
            }
        }
        return 0;
    }

    public void deleteTask(){
        System.out.print("> 삭제하고 싶은 할 일은?: ");
        int target = Integer.parseInt(s.nextLine());
        int result =  delete(target);
        if(result == 0){
            System.out.println("\n존재하지 않는 id입니다.\n");
        }
        else if(result == 1){
            System.out.println("\n할 일이 삭제되었습니다.\n");
        }
    }
}
