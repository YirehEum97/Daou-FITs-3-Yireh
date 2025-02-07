package javaexam2.company;

import java.util.HashMap;
import java.util.Map;

public class Company {

    private static void divider(int a){
        if (a == 1){
            System.out.printf("%-10s%-12s%s\n","name","department","salary");
            System.out.println("-----------------------------");
        } else {
            System.out.printf("%-10s%-12s%-10s%s\n","name","department","salary","tax");
            System.out.println("--------------------------------------");
        }
    }

    public static void main(String[] args) {

        Map<Integer, Bonus> map = new HashMap<Integer, Bonus>();

        Bonus e1 = new Secretary("Hilery",1,"secretary",800);
        Bonus e2 = new Sales("Clinten",2,"sales",1200);

        map.put(((Employee)e1).getNumber(), e1);
        map.put(((Employee)e2).getNumber(), e2);

        divider(1);

        for (Bonus e: map.values()){
            System.out.println((Employee)e);
        }

        System.out.println("\n인센티브 100 지급\n");
        divider(2);

        for (Bonus e: map.values()){
            e.incentive(100);
            System.out.print((Employee)e);
            System.out.println(((Employee) e).tax());
        }
    }
}
