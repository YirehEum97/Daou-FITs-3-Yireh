package javaexam2.company;

public class Secretary extends Employee implements Bonus{
    public Secretary(){
        super();
    }

    public Secretary(String name, int number, String department, int salary){
        super(name, number, department, salary);
    }

    public double tax(){
        double t = this.getSalary() * 0.1;
        return t;
    }

    public void incentive(int pay){
        int s = this.getSalary();
        this.setSalary((int) (s + pay*0.8));
    }
}
