package javaexam2.plane;

public class Airplane extends Plane {
    public Airplane(){

    }

    public Airplane(String planeName, int fuelSize){
        super(planeName, fuelSize);
    }

    @Override
    public void flight(int distance){
        fuelSize -= distance * 3;
    }
}
