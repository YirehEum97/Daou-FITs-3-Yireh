package assignment;

import java.util.Scanner;

public class Test02 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("5에서 9 사이 정수를 입력하시오.");
        int n = scanner.nextInt();

        int result = 120;
        for (int i=6; i<=n; i++){
            result *= i;
        }

        System.out.print(result);
    }
}
