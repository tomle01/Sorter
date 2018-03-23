public class Box {
    private double weight;
    private int milk, id;

    public Box(int milk, double weight, int id) {
        this.weight = weight;
        this.milk = milk;
        this.id = id;
    }

    public static Box newBox(int m, double w, int i) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Box deepCopy() {
        return new Box(this.milk, this.weight, this.id);
    }

    @Override
    public String toString() {
        return "Box{" +
                "weight=" + weight +
                ", milk=" + milk +
                ", id=" + id +
                '}';
    }

    public boolean equals(Box box) {
        if (this.id == box.getId())
            return true;
        else return false;
    }
}
