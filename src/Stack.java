import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack {
    static final int MAX = 20;
    private int top;
    private Box a[] = new Box[MAX]; // Maximum size of Stack
    private double totWeight, totMilk;
    private ArrayList<Box> BoxesInStack = new ArrayList<>();

    private List<Box> list; private double maxWeight;

    boolean isEmpty() {
        return (top < 0);
    }

    Stack() {
        top = -1;
        totWeight = 0;
        totMilk = 0;
        maxWeight = 0;
        list = new ArrayList<>();
    }

    Stack(List<Box> list, double maxWeight) {
        top = -1;
        totWeight = 0;
        totMilk = 0;
        this.maxWeight = maxWeight;
        this.list = list;
    }

    boolean push(Box x) {
        if (top >= MAX) {
            System.out.println("Stack Overflow");
            return false;
        } else {
            a[++top] = x;
            return true;
        }
    }

    Box pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return null;
        } else {
            Box x = a[top--];
            return x;
        }
    }

    /**
     * Push box with milk into box
     */
    void pushMilk() {
        // list should be sorted light weight first
        // ***** List has next, Milk < 24*****");
        Iterator i = list.iterator();
        while (i.hasNext() && this.totMilk <= 24) {
            Box box = (Box) i.next();
            // Go to next box if there is already a box with same ID
            if (!BoxesInStack.contains(box)) {
                // ***** Total Weight + box Weight < Max Weight *****"
                if ((totWeight + box.getWeight()) < (maxWeight + 5)) {
                    //***** Total Milk + box Milk <= 24*****"
                    if ((this.totMilk + box.getMilk()) <= 24) {
                        push(box);
                        BoxesInStack.add(box);
                        this.totMilk = this.totMilk + box.getMilk();
                        this.totWeight = this.totWeight + box.getWeight();
                        i.remove();
                    }
                } else
                    System.out.println("Cannot add more milk because stack has reached max weight");
            }
        }
    }

    /**
     * Push boxes with weight into the passed in Stack array
     */
    void pushWeight() {
        // list should be sorted heavy weight first
        Iterator i = list.iterator();
        while (i.hasNext() && this.totWeight < (this.maxWeight + 5)) {
            Box box = (Box) i.next();
            // no duplicate ID
            if (!BoxesInStack.contains(box)) {
                if ((box.getWeight() + totWeight) < this.maxWeight + 5) {
                    if ((this.totMilk + box.getMilk()) <= 24) {
                        push(box);
                        BoxesInStack.add(box);
                        this.totWeight = this.totWeight + box.getWeight();
                        i.remove();
                    }
                }
            }
        }
    }

    public double getTotWeight() {
        return totWeight;
    }

    public void setTotWeight(double totWeight) {
        this.totWeight = totWeight;
    }

    public double getTotMilk() {
        return totMilk;
    }

    public void setTotMilk(double totMilk) {
        this.totMilk = totMilk;
    }

    public String toString(){
        return ("Total Milk in Stack: " + this.totMilk + "\nTotal Weight in Stack: " + this.totWeight);
    }

    public String showAll() {
        String str = "";
        for (Box box : BoxesInStack) {
            str = str.concat(box.toString() + " ");
        }
        return str;
    }
}
