package prac;

import java.util.ArrayList;
import java.util.Scanner;

public class TasksCRUD implements ICRUD{
    ArrayList<Tasks> list;
    Scanner s;
    int id = 0;

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
        System.out.println("할 일이 추가되었습니다.");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }
}
