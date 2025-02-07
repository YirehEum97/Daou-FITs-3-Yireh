package javaexam2;

import java.util.*;

public class Test04 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();

        for (int i=0; i<10; i++) {
            list.add(new Random().nextInt(10));
        }

        for (Integer value : list) {
            System.out.print(value + " ");
        }

        list.sort(Comparator.naturalOrder());
        System.out.println();

        for (Integer value : list) {
            System.out.print(value + " ");
        }
    }
}
