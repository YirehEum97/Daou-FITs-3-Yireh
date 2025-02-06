package assigment2;

public class TV {
    private String name;
    private int price;
    private String description;

    public TV(){

    }

    public TV(String name, int price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String toString(){
        String str = new String();
        str = name + "\t" + price + "\t" + description;
        return str;
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