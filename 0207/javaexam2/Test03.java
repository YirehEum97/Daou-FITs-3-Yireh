package javaexam2;

import java.util.StringTokenizer;

public class Test03 {
    public static void main(String[] args) {
        String str = "1.22,4.12,5.93,8.71,9.34";
        double[] date = new double[5];
        double sum = 0;

        StringTokenizer st = new StringTokenizer(str, ",");

        for (int i=0; st.hasMoreElements(); i++) {
            date[i] = Double.parseDouble(st.nextToken());
            sum += date[i];
        }

        System.out.println("합계: " + sum);
        System.out.println("평균: " + sum / date.length);
    }
}
