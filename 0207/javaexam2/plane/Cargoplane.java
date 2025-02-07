package javaexam2.plane;

public class Cargoplane extends Plane{
    public Cargoplane(){

    }

    public Cargoplane(String planeName, int fuelSize){
        super(planeName, fuelSize);
    }

    @Override
    public void flight(int distance){
        fuelSize -= distance * 5;
    }
}
