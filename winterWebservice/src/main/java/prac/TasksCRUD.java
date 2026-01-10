package prac;

import java.util.ArrayList;
import java.util.Scanner;

public class TasksCRUD implements ICRUD{
    ArrayList<Tasks> list;
    Scanner s;
    int id = 1;

    TasksCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("할 일 입력: ");
        String task = s.nextLine();
        return new Tasks(id ++, task, false);
    }

    public void addTask(){
        Tasks one = (Tasks) add();
        list.add(one);
        System.out.println("할 일이 추가되었습니다.\n");
    }

    public void listTask(){
        System.out.println("    **할 일 목록**");
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).toString() + "\n");
        }
    }

    @Override
    public int update(Object obj) {
        for (Tasks t : list) {
            if (t.getId() == (int)obj) {
                System.out.println("1) 할 일 수정 2) 완료 / 미완료 변경\n 원하는 메뉴는?");
                int menu = s.nextInt();
                s.nextLine();
                if (menu == 1) {
                    System.out.print("새 할 일 입력: ");
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
        System.out.print("수정하고 싶은 할 일은?: ");
        int target = Integer.parseInt(s.nextLine());
        int result =  update(target);
        if(result == 0){
            System.out.println("존재하지 않는 id입니다.\n");
        }
        else if(result == 1){
            System.out.println("할 일이 수정되었습니다.\n");
        }
    }


    @Override
    public int delete(Object obj) {
        return 0;
    }
}
