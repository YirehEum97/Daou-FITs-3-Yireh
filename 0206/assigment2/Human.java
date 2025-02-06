package assigment2;

public class Human {
    protected String name;
    protected int age;
    protected int height;
    protected int weight;

    public Human(){

    }

    public Human(String name, int age, int height, int weight){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String printInformation(){
        return this.name + "\t" + this.age + "\t" + this.height + "\t" + this.weight;
    }
}
