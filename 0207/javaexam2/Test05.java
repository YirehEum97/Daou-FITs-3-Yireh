package javaexam2;

import java.util.*;
public class Test05 {
    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap();
        double sum = 0.0;

        for(int i=0; i<10; i++) {
            int num = new Random().nextInt(100);
            map.put(i, num);
            sum += num;
        }

        for (Integer value : map.values()) {
            System.out.print(value + " ");
        }

        System.out.print("\n합계: ");
        System.out.printf("%.2f", sum);
        System.out.print("\n평균: ");
        System.out.printf("%.2f", sum / map.size());
    }
}
