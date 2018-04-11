import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack {
    private static DecimalFormat df2 = new DecimalFormat(".##");

    static final int MAX = 20;
    private int top, ordinalNumber, totBox, totMilk;
    private Box a[] = new Box[MAX]; // Maximum size of Stack
    private double totWeight;
    private ArrayList<Box> BoxesInStack = new ArrayList<>(); // Use to store info on what box is in stack
    private ArrayList<String> idInStack = new ArrayList<>(); // Use to store info on what id is in stack
    private double maxWeight;

    Stack() {
        ordinalNumber = -1;
        top = -1;
        totWeight = 0;
        totMilk = 0;
        maxWeight = 0;
    }

    Stack(int number, List<Box> list, double maxWeight) {
        this.ordinalNumber = number;
        top = -1;
        totWeight = 0;
        totMilk = 0;
        this.maxWeight = maxWeight;
    }

    boolean isEmpty() {
        return (top < 0);
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
     * Push box with milk into box // Fix logic to continue pushing weight
     */
    void pushBox(List<Box> list) {
        // list should be sorted light weight first
        Sorting.sortMilkHeavyFirst(list);
        Iterator i = list.iterator();
        while (i.hasNext() && this.totMilk < 24 && this.totWeight < (this.maxWeight + 5)) {
            Box box = (Box) i.next();
            // Go to next box if there is already a box with same ID
            if (!box.containsMilk() || contains(box)) {
                continue;
            }
            if ((totWeight + box.getWeight()) < (maxWeight + 5)) {
                if ((this.totMilk + box.getMilk()) <= 24) {
                    push(box);
                    totBox++;
                    idInStack = box.setStackiD(idInStack);
                    BoxesInStack.add(box);
                    this.totMilk = this.totMilk + box.getMilk();
                    this.totWeight = this.totWeight + box.getWeight();
                    i.remove();
                }
            }
        }
        Sorting.sortWeightHeavyFirst(list);
        i = list.iterator();
        while (i.hasNext() && this.totWeight < (this.maxWeight + 5)) {
            Box box = (Box) i.next();
            // Go to next box if there is already a box with same ID
            if (box.containsMilk() || contains(box)) {
                continue;
            }

            if ((totWeight + box.getWeight()) < (maxWeight + 5)) {
                push(box);
                totBox++;
                idInStack = box.setStackiD(idInStack);
                BoxesInStack.add(box);
                this.totMilk = this.totMilk + box.getMilk();
                this.totWeight = this.totWeight + box.getWeight();
                i.remove();
            }
        }

    }

    public String toString() {
        return ("\nTotal Milk in Stack: " + this.totMilk + "\nTotal Weight in Stack: "
                + String.format(Double.toString(this.totWeight), "#,00"));
    }

    public String toFile() {
        String str = "STACK " + ordinalNumber + ":" + System.lineSeparator();
        str = str.concat("Total Box: " + this.totBox  + "     Total Milk: " + this.totMilk +
                "     Total Weight: " + df2.format(this.totWeight) + System.lineSeparator());
        for (Box box : BoxesInStack) {
            str = str.concat(box.toString() + " ");
        }
        return str;
    }

    public String showAll() {
        String str = "";
        String str2 = "";
        for (Box box : BoxesInStack) {
            str = str.concat(box.toString() + " ");
        }

        for (String s : idInStack) {
            str2 = str2.concat(s + " ");
        }
        str = str + "\n" + str2 + "\n";
        return str;
    }

    private boolean contains(Object box) {
        if (((Box) box).getiD().endsWith("m"))
            return false;
        // Check if object is instance of subclass or class
        if (box.getClass() == Box.class) {
            for (String s : idInStack) {
                if (s.equalsIgnoreCase(((Box) box).getiD())) {
                    return true;
                }
            }
        } else if (box.getClass() == BigBox.class) {
            String[] idInBox = ((BigBox) box).getIdContents();
            for (String s : idInStack) {
                if (s.equalsIgnoreCase(((BigBox) box).getiD())) {
                    return true;
                }
                // Working as intended
                for (String s2 : idInBox) {
                    if (s.equalsIgnoreCase(s2)) {
                        return true;
                    }
                }
            }
        }
        return false;
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

    public void setTotMilk(int totMilk) {
        this.totMilk = totMilk;
    }
}
