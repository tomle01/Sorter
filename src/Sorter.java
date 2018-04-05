import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Phuc Le
 * @version 2018.4.5
 * TODO: Work on Equals method on Monday and the following
 * contains() method, check if it uses parent or child equals method
 */
public class Sorter {
    public static void main(String[] args) {

        boolean exit = false, sorted = false;
        int choice, numOfBill;
        double maxWeight;
        ArrayList<Stack> stackArray = new ArrayList<>();

        List<Box> BoxList = new ArrayList<>();
        List<Box> OneEachList = new ArrayList<>();

        Sorting sorting = new Sorting();
        do {
            System.out.println("\n---------------------MENU-------------------");
            System.out.println("1. Insert to Structure\n2. Sort\n3. Show what is in List"
                    + "\n4. Show Sorted Stack\n5. Reset List content\n6. Export to Eclipse/Exported\n0. Exit");
            Scanner keyboard = new Scanner(System.in);
            choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice) {
                case 1:
                    OneEachList = readOneEachList();
                    BoxList = readBoxList();
                    break;
                case 2:
                    // --------------- Done with list ----------------- //
                    System.out.println("How many bills? Max weight each?");
                    numOfBill = keyboard.nextInt();
                    maxWeight = keyboard.nextDouble();
                    stackArray = sorting.listToStack(OneEachList, BoxList, numOfBill, maxWeight);
                    sorted = true;
                    break;
                case 3: // Work on this later, total weight and milk left does not work
                    System.out.println("Contents in One Each List: ");
                    System.out.println(OneEachList.toString());
                    System.out.println("Contents in Box List: ");
                    System.out.println(BoxList.toString());
                    break;
                case 4:
                    if (!sorted)
                        System.out.println("Please choose option 2 to sort the list into stacks first");
                    else {
                        int count = 1;
                        for (Stack stack : stackArray) {
                            System.out.print("\nStack " + count + ":");
                            System.out.print(stack.showAll());
                            count++;
                        }
                    }
                    break;
                case 5:
                    BoxList.clear();
                    OneEachList.clear();
                    sorted = false;
                    break;
                case 6:
                    exportToFile(stackArray);
                    System.out.println("Done");
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private static Box newBox(int m, double w, String i) {
        return new Box(m, w, i);
    }

    private static Box newBox(int m, double w, String i, String[] idContents) {
        return new BigBox(m, w, i, idContents);
    }

    private static List readOneEachList() {
        List<Box> list = new ArrayList<>();
        String csvFile = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\OneEachListCSV.csv";
        String line;
        String cvsSplitBy = ",";
        String idContentsSplitBy = "\\s";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] boxInfo = line.split(cvsSplitBy);
                if (boxInfo.length == 3) {
                    if (!list.add(
                            Sorter.newBox(Integer.parseInt(boxInfo[0]), Double.parseDouble(boxInfo[1]), boxInfo[2]))) {
                        System.out.println("Insert failed");
                    }
                } else {
                    String[] idContentsInfo = boxInfo[3].split(idContentsSplitBy);
                    if (!list.add(Sorter.newBox(Integer.parseInt(boxInfo[0]), Double.parseDouble(boxInfo[1]),
                            boxInfo[2], idContentsInfo))) {
                        System.out.println("Insert failed");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List readBoxList() {
        List<Box> list = new ArrayList<>();
        String csvFile = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\BoxListCSV.csv";
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] boxInfo = line.split(cvsSplitBy);
                // System.out.println("Country [code= " + country[4] + " , name=" + country[5] +
                // "]");
                if (!list
                        .add(Sorter.newBox(Integer.parseInt(boxInfo[0]), Double.parseDouble(boxInfo[1]), boxInfo[2]))) {
                    System.out.println("Insert failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void exportToFile(ArrayList<Stack> stackArray) {
        final String FILENAME = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\Exported\\"
                + java.time.LocalDate.now() + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            int count = 1;

            for (Stack stack : stackArray) {
                bw.write(System.lineSeparator());
                bw.write("Stack " + count);
                bw.write(stack.toFile());
                count++;
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
