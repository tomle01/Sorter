import java.util.*;

public class Sorting {

    public Sorting() {
    }

    static void sortWeightHeavyFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                // avoiding NullPointerException in case name is null ? What does this mean
                double idea1 = box1.getWeight();
                double idea2 = box2.getWeight();
                return Double.compare(idea2, idea1);
            }
        });
    }

    static void sortWeightLightFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                double idea1 = box1.getWeight();
                double idea2 = box2.getWeight();
                return Double.compare(idea1, idea2);
            }
        });
    }

    static void sortMilkLightFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                double idea1 = box1.getMilk();
                double idea2 = box2.getMilk();
                return Double.compare(idea1, idea2);
            }
        });
    }

    static void sortMilkHeavyFirst(List<Box> list) {
        Collections.sort(list, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                double idea1 = box1.getMilk();
                double idea2 = box2.getMilk();
                return Double.compare(idea2, idea1);
            }
        });
    }

    ArrayList listToStack(List<Box> OneEachAndMilkList, List<Box> BoxList, int numberOfBill, double maxWeight) {
        // Create necessary amount of stacks first
        ArrayList<Stack> stackArray = new ArrayList<>();
        for (int i = 0; i < numberOfBill; i++) {
            stackArray.add(new Stack(BoxList, maxWeight));
        }
        // Deal with OneEachAndMilkList
        for (Stack i : stackArray) {
            System.out.println("------------------------------------------------------------------\n"
                    + "Begin pushing box into Stack");
            i.pushBox(OneEachAndMilkList);
        }

        sortWeightHeavyFirst(BoxList);
        for (Stack i : stackArray) {
            i.pushBox(BoxList);
        }
        int i = 1; // counter
        for (Stack s : stackArray) {
            System.out.print("\nInformation for Stack number: " + i);
            System.out.print(s.toString());
            i++;
        }
        return stackArray;
    }
}
