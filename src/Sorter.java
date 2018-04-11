import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

        List<Box> boxList = new ArrayList<>();
        List<Box> oneEachList = new ArrayList<>();

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
                    oneEachList = readOneEachList();
                    boxList = readBoxList();
                    break;
                case 2:
                    // --------------- Done with list ----------------- //
                    System.out.println("How many bills? Max weight each?");
                    numOfBill = keyboard.nextInt();
                    maxWeight = keyboard.nextDouble();
                    stackArray = sorting.listToStack(oneEachList, boxList, numOfBill, maxWeight);
                    sorted = true;
                    printBoxesRemain(oneEachList, boxList);
                    exportToFile(stackArray);
                    if(oneEachList.isEmpty() && boxList.isEmpty()) {
                        exportToFile(stackArray);
                        System.out.println("List exported");
                    }
                    break;
                case 3:
                    printBoxesRemain(oneEachList, boxList);
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
                    boxList.clear();
                    oneEachList.clear();
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

    private static Box newBox(String i, int m, double w) {
        return new Box(i, m, w);
    }

    private static Box newBox(String i, int m, double w, String[] idContents) {
        return new BigBox(i, m, w, idContents);
    }

    private static List readOneEachList() {
        List<Box> list = new ArrayList<>();
        String csvFile = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\oneEachListCSV.csv";
        String line;
        String cvsSplitBy = ",";
        String idContentsSplitBy = "\\s";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] boxInfo = line.split(cvsSplitBy);
                if (boxInfo.length == 3) {
                    if (!list.add(Sorter.newBox(boxInfo[0], Integer.parseInt(boxInfo[1])
                            , Double.parseDouble(boxInfo[2])))) {
                        System.out.println("Insert failed");
                    }
                } else {
                    String[] idContentsInfo = boxInfo[3].split(idContentsSplitBy);
                    if (!list.add(Sorter.newBox(boxInfo[0], Integer.parseInt(boxInfo[1])
                            , Double.parseDouble(boxInfo[2]), idContentsInfo))) {
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
        String csvFile = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\boxListCSV.csv";
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] boxInfo = line.split(cvsSplitBy);
                if (!list.add(Sorter.newBox(boxInfo[0], Integer.parseInt(boxInfo[1])
                        , Double.parseDouble(boxInfo[2])))) {
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
                bw.write(stack.toFile() + System.lineSeparator());
                count++;
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private static void printBoxesRemain(List<Box> oneEachList, List<Box> boxList){
        if(oneEachList.isEmpty() && boxList.isEmpty()){
            System.out.println("No box remains");
        }
        if(!oneEachList.isEmpty()) {
            System.out.println("Remains in One Each List: ");
            System.out.println(oneEachList.toString());
        }
        if(!boxList.isEmpty()){
            System.out.println("Remains in Box List: ");
            System.out.println(boxList.toString());
        }
    }
}
