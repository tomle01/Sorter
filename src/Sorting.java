import java.util.*;

public class Sorting {

    public Sorting() {
    }

    void sortWeightHeavyFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                // avoiding NullPointerException in case name is null ? What does this mean
                double idea1 = box1.getWeight();
                double idea2 = box2.getWeight();
                return Double.compare(idea2, idea1);
            }
        });
    }

    void sortWeightLightFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                // avoiding NullPointerException in case name is null ? What does this mean
                double idea1 = box1.getWeight();
                double idea2 = box2.getWeight();
                return Double.compare(idea1, idea2);
            }
        });
    }

    void sortMilkLightFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                // avoiding NullPointerException in case name is null ? What does this mean
                double idea1 = box1.getMilk();
                double idea2 = box2.getMilk();
                return Double.compare(idea1, idea2);
            }
        });
    }

    void sortMilkHeavyFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                // avoiding NullPointerException in case name is null ? What does this mean
                double idea1 = box1.getMilk();
                double idea2 = box2.getMilk();
                return Double.compare(idea2, idea1);
            }
        });
    }

    // No usage
    class Sortbyroll implements Comparator<Box> // Clueless
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Box a, Box b) {
            return a.getId() - b.getId();
        }
    }

    void listToStack(List<Box> list, int numberOfBill, double maxWeight) {
        System.out.println("Before sorting:" + list);
        sortMilkHeavyFirst(list);
        System.out.println("Descending Milk - sorted list:" + list);
        Stack[] stackArray = new Stack[numberOfBill];
        for (int i = 0; i < stackArray.length; i++) {
            stackArray[i] = new Stack(list,maxWeight);
        }
        for (Stack i: stackArray) {
            i.pushMilk();
        }
        // Sort list heavy weight first
        sortWeightHeavyFirst(list);
        for (Stack i: stackArray) {
            i.pushWeight();
        }
        int i = 1; // counter
        for (Stack s : stackArray) {
            System.out.println("Information for Stack number: " + i);
            System.out.println(s.toString());
            i++;
        }
    }
}
