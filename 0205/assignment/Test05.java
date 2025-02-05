package assignment;

public class Test05 {
    public static void main(String[] args){
        int a1 = Integer.parseInt(args[0]);
        int a2 = Integer.parseInt(args[1]);
        int a3 = Integer.parseInt(args[2]);

        int M = Math.max(Math.max(a1, a2),a3);
        int m = Math.min(Math.min(a1, a2),a3);

        String str = "최대값: " + M + "\n최소값: " + m;
        System.out.print(str);
    }
}
