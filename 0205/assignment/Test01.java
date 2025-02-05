package assignment;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("첫번째 정수를 입력하세요.");
        int a = scanner.nextInt();

        System.out.println("두번째 정수를 입력하세요.");
        int b = scanner.nextInt();

        int r = a%b;

        if (r>1){
            System.out.print("나머지가 1보다 크다!");
        } else {
            System.out.print("나머지가 1보다 작거나 같다!");
        }
    }
}
