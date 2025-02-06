package assigment2;

public class TvTest extends Tv {

    public static void main(String[] args) {
        Tv[] tvArray = {
            new Tv("INFINIA", 1500000, "LED TV"),
            new Tv("XCANVAS", 1000000, "LCD TV"),
            new Tv("CINEMA", 2000000, "3D TV")
        };

        for (Tv tv : tvArray) {
            System.out.println(tv);
        }

        int sum = 0;
        for (Tv tv : tvArray) {
            sum += tv.getPrice();
        }

        System.out.println("가격의 합 : " + sum);
    }
}
