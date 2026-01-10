package prac;

import java.util.Scanner;

public class TasksManager {
    Scanner s = new Scanner(System.in);
    TasksCRUD tasksCRUD;
    public TasksManager(){
        tasksCRUD = new TasksCRUD(s);
    }
    public int selectMenu(){
        System.out.println("To-do List 프로그램");
        System.out.print("1)추가 2)목록 3)수정 4)삭제 0)종료\n원하는 메뉴는? ");
        return Integer.parseInt(s.nextLine());
    }

    public void start() {
        while (true) {
            int menu = selectMenu();
            if(menu == 0) break;

            if(menu == 1){
                tasksCRUD.addTask();
            }
            else if(menu == 2){
                tasksCRUD.listTask();
            }
            else if(menu == 3){
                tasksCRUD.listTask();
                tasksCRUD.updateTask();
            }
        }
    }
}
