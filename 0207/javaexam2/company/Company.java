package javaexam2.company;

import java.util.HashMap;

public class Company {
    public static void main(String[] args) {
        HashMap<Integer, Employee> map = new HashMap();

        Employee e1 = new Secretary("Hilery",1,"secretary",800);
        Employee e2 = new Sales("Clinten",2,"sales",1200);

        map.put(e1.getNumber(), e1);
        map.put(e2.getNumber(), e2);

        String divider = "name\tdepartment\tsalary\n-----------------------------\n";
        System.out.print(divider);

        for (Employee e: map.values()){
            e.printValues();
        }

        System.out.println("\n인센티브 100 지급\n");

        for (Employee e: map.values()){
            if (e instanceof Bonus){
                ((Bonus) e).incentive(100);
            }
            e.printValues();
        }


    }
}
