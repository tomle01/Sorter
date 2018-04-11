import java.util.ArrayList;

public class Box {
    private double weight;
    private int milk;
    private String iD;

    public Box() {
        this.milk = 0;
        this.weight = 0;
        this.iD = "0";
    }

    public Box(String iD, int milk, double weight) {
        this.milk = milk;
        this.weight = weight;
        this.iD = iD;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public ArrayList<String> setStackiD(ArrayList<String> list) {
        list.add(this.iD);
        return list;
    }

    public Box deepCopy() {
        return new Box(this.iD, this.milk, this.weight);
    }

    public boolean containsMilk() {
        return this.milk != 0;
    }

    @Override
    public String toString() {
        return
                //System.out.printf("| %-8s %5s | \n" ,"HC: ", iD) +
                //System.out.printf("| %-8s %5d |\n","Milk: ", milk) +
                //System.out.printf("| %-8s %5.2f |\n","Weight: ", weight);

                "----------------" + System.lineSeparator() +
                "> HC: " + iD + System.lineSeparator() +
                "> Milk: " + milk  + System.lineSeparator() +
                "> Weight: " + weight + System.lineSeparator();
    }

}
