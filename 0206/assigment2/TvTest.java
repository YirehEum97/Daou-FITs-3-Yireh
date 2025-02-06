package assigment2;

public class TvTest extends TV{

    public static void main(String[] args) {
        TV tvArray [] = new TV[3];
        tvArray[0] = new TV("INFINIA", 1500000, "LED TV");
        tvArray[1] = new TV("XCANVAS", 1000000, "LCD TV");
        tvArray[2] = new TV("CINEMA", 2000000, "3D TV");


        for (TV tv : tvArray) {
            System.out.println(tv.toString());
        }

        int sum = 0;
        for (TV tv : tvArray) {
            sum += tv.getPrice();
        }

        System.out.println("가격의 합 : " + sum);
    }
}
