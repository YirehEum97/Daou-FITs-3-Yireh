package assignment;

public class Test04 {
    public static void main(String[] args){
        for (int x=1;x<=6;x++){
            for (int y=1;y<=6;y++){
                for(int z=1;z<=6;z++){
                    int s = x*y*z;
                    if (s%3 == 0){
                        String str = "";
                        str = x + "*" + y + "*" + z + "=" + s;
                        System.out.println(str);
                    }
                }
            }
        }
    }
}
