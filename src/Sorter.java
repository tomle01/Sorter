import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Phuc Le
 * @version 2018.3.24
 * Continue working on formatting and showAll for Stack
 */
public class Sorter {
    public static void main(String[] args) {

        boolean exit = false, sorted = false;
        int choice, numOfBill;
        double maxWeight;
        ArrayList<Stack> stackArray = new ArrayList<>();

        List<Box> BoxList = new ArrayList<>();
        List<Box> OneEachlist = new ArrayList<>();

        Sorting sorting = new Sorting();
        do {
            System.out.println("---------------------MENU-------------------");
            System.out.println("1. Insert to Structure\n2. Sort\n3. Show what is in List" +
                    "\n4. Show Sorted Stack\n5. Reset List content\n0. Exit");
            Scanner keyboard = new Scanner(System.in);
            choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice) {
                case 1:
                    //OneEachlist = readOneEachList();
                    BoxList = readBoxList();
                    break;
                case 2:
                    // --------------- Done with list ----------------- //
                    System.out.println("How many bills? Max weight each?");
                    numOfBill = keyboard.nextInt();
                    maxWeight = keyboard.nextDouble();
                    stackArray = sorting.listToStack(OneEachlist, BoxList, numOfBill, maxWeight);
                    sorted = true;
                    break;
                case 3: // Work on this later, total weight and milk left does not work
                    //System.out.println(OneEachlist.toString());
                    System.out.println(BoxList.toString());
                    break;
                case 4:
                    if (!sorted)
                        System.out.println("Please choose option 2 to sort the list into stacks first");
                    else {
                        int count = 1;
                        for (Stack stack : stackArray) {
                            System.out.println("Stack " + count + ":");
                            System.out.println(stack.showAll());
                            count++;
                        }
                    }
                    break;
                case 5:
                    BoxList.clear();
                    OneEachlist.clear();
                    sorted = false;
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    static List readOneEachList() {
        List<Box> list = new ArrayList<>();
        String csvFile = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\BoxListCSV.csv";
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] boxInfo = line.split(cvsSplitBy);
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
                if (!list.add(Box.newBox(Integer.parseInt(boxInfo[0]), Double.parseDouble(boxInfo[1]), boxInfo[2]))) {
                    System.out.println("Insert failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    static List readBoxList() {
        List<Box> list = new ArrayList<>();
        String csvFile = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\BoxListCSV.csv";
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] boxInfo = line.split(cvsSplitBy);
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
                if (!list.add(Box.newBox(Integer.parseInt(boxInfo[0]), Double.parseDouble(boxInfo[1]), boxInfo[2]))) {
                    System.out.println("Insert failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
