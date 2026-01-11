package prac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PracCRUD implements ICRUD{
    ArrayList<ParcTasks> list;
    Scanner s;
    int id = 1;

    PracCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("\n>> 할 일 입력: ");
        String task = s.nextLine();
        return new ParcTasks(id ++, task, false);
    }

    public void addTask(){
        ParcTasks one = (ParcTasks) add();
        list.add(one);
        System.out.println("\n할 일이 추가되었습니다.");
    }

    public void listTask(){
        System.out.println("\n**할 일 목록**");
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).toString() + "\n");
        }
    }

    @Override
    public int update(Object obj) {
        for (ParcTasks t : list) {
            if (t.getId() == (int)obj) {
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
    public int delete(Object obj){
        int targetId = (int) obj;
        Iterator<ParcTasks> it = list.iterator();
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
