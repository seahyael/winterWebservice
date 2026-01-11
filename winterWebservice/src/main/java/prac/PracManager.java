package prac;

import java.util.Scanner;

public class PracManager {
    Scanner s = new Scanner(System.in);
    PracCRUD tasksCRUD;
    public PracManager(){
        tasksCRUD = new PracCRUD(s);
    }
    public int selectMenu(){
        System.out.println("------------------------------");
        System.out.println("To-do List 프로그램");
        System.out.println("------------------------------");
        System.out.print("1)추가 2)목록 3)수정 4)삭제 0)종료\n> 원하는 메뉴는? ");
        return Integer.parseInt(s.nextLine());
    }

    public void start() {
        while (true) {
            int menu = selectMenu();
            if(menu == 0) {
                System.out.println("\n프로그램을 종료합니다.\n");
                break;
            }

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
            else if(menu == 4){
                tasksCRUD.listTask();
                tasksCRUD.deleteTask();
            }
        }
    }
}
