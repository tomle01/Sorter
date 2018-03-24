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

    ArrayList listToStack(List<Box> OneEachList, List<Box> BoxList, int numberOfBill, double maxWeight) {
        // Create necessary amount of stacks first
        ArrayList<Stack> stackArray = new ArrayList<>();
        for (int i = 0; i < numberOfBill; i++) {
            stackArray.add(new Stack(BoxList, maxWeight));
        }
        // Deal with first list with lots of duplicate box first


        // Process the BoxList
        sortMilkHeavyFirst(BoxList);
        for (Stack i: stackArray) {
            i.pushMilk();
        }
        // Sort list heavy weight first
        sortWeightHeavyFirst(BoxList);
        for (Stack i: stackArray) {
            i.pushWeight();
        }
        int i = 1; // counter
        for (Stack s : stackArray) {
            System.out.println("Information for Stack number: " + i);
            System.out.print(s.toString());
            i++;
        }
        return stackArray;
    }
}
