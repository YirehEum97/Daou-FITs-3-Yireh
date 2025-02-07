package javaexam2.plane;

public abstract class Plane {
    String planeName;
    int fuelSize;

    public Plane(){

    }

    public Plane(String planeName, int fuelSize){
        this.planeName = planeName;
        this.fuelSize = fuelSize;
    }

    public void refuel(int fuel){
        fuelSize += fuel;
    }

    public abstract void flight(int distance);

    public void printValue(){
        System.out.println(planeName+"\t"+fuelSize);
    }
}
