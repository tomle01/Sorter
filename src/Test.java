import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(4);
        a.add(6);
        System.out.println(func(a));
    }

    static boolean func(ArrayList<Integer> a){
        int b = 8;
        for(int i : a){
            if(i==b){
                return true;
            }
        } return false;
    }
}
