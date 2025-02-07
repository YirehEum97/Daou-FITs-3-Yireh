package javaexam2.plane;

public abstract class Plane {
    private String planeName;
    private int fuelSize;

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
        System.out.printf("%-8s%s\n",planeName,fuelSize);
    }

    public String getPlaneName(){
        return this.planeName;
    }

    public void setPlaneName(String planeName){
        this.planeName = planeName;
    }

    public int getFuelSize(){
        return this.fuelSize;
    }

    public void setFuelSize(int fuelSize){
        this.fuelSize = fuelSize;
    }
}
