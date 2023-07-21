package pl.coderslab.taskmanager;

import java.util.Scanner;

public class preventEmptyName {
    public static void main(String[] args) {
        loopIfEmpty();
    }

    public static void loopIfEmpty(){
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();

        while(option.trim().isEmpty()){
            System.out.println("empty");
             option = scan.nextLine();
        }
    }

}


