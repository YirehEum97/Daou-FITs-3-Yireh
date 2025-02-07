package javaexam2.plane;

public class PlaneTest {
    public static void main(String[] args) {
        Plane[] planes = {
            new Airplane("L747", 1000),
            new Cargoplane("C40 ", 1000)
        };

        String divider = "Plane\tfuelSize\n-------------------";
        System.out.println(divider);

        for (Plane plane : planes) {
            plane.printValue();
        }

        System.out.println("\n100 운항");
        System.out.println(divider);

        for (Plane plane : planes) {
            plane.flight(100);
            plane.printValue();
        }

        System.out.println("\n200 주유");
        System.out.println(divider);

        for (Plane plane : planes) {
            plane.refuel(200);
            plane.printValue();
        }
    }
}
