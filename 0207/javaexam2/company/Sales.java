package javaexam2.company;

public class Sales extends Employee implements Bonus{
    public Sales(){
        super();
    }

    public Sales(String name, int number, String department, int salary){
        super(name, number, department, salary);
    }

    public double tax(){
        double t = this.getSalary() * 0.13;
        return t;
    }

    public void incentive(int pay){
        int s = this.getSalary();
        this.setSalary((int) (s + pay*1.2));
    }
}
