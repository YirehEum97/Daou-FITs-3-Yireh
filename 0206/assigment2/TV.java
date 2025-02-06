package assigment2;

public class Tv {
    private String name;
    private int price;
    private String description;

    public Tv(){

    }

    public Tv(String name, int price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString(){
        return name + "\t" + price + "\t" + description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public String getDescription(){
        return this.description;
    }
}