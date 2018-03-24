public class Box {
    private double weight;
    private int milk;
    private String iD;

    public Box() {
        this.milk = 0;
        this.weight = 0;
        this.iD = "0";
    }

    public Box(int milk, double weight, String iD) {
        this.milk = milk;
        this.weight = weight;
        this.iD = iD;
    }

    public static Box newBox(int m, double w, String i) {
        return new Box(m, w, i);
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

    public Box deepCopy() {
        return new Box(this.milk, this.weight, this.iD);
    }

    @Override
    public String toString() {
        return "\nBox|" +
                "iD=" + iD +
                ", milk=" + milk +
                ", weight=" + weight +
                '|';
    }

    // ArrayList uses this method in its contains()
    public boolean equals(Object box) {
        Box boxed = (Box) box;
        if (boxed.getiD().endsWith("m"))
            return false;
        else return this.iD.equals(boxed.getiD());
    }
}
