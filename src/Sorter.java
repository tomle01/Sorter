import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Phuc Le
 * @version 2018.4.13
 * TODO: Work on Equals method on Monday and the following
 * contains() method, check if it uses parent or child equals method
 */
public class Sorter {

    boolean sorted = false;
    ArrayList<Stack> stackArray = new ArrayList<>();
    List<Box> boxList = new ArrayList<>();
    List<Box> oneEachList = new ArrayList<>();

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
                // adding normal box without no box inside
                if (boxInfo.length == 3) {
                    if (!list.add(Sorter.newBox(boxInfo[0], Integer.parseInt(boxInfo[1])
                            , Double.parseDouble(boxInfo[2])))) {
                        JOptionPane.showMessageDialog(null, "Insert failed");
                    }
                } else { // Big Box
                    String[] idContentsInfo = boxInfo[3].split(idContentsSplitBy);
                    if (!list.add(Sorter.newBox(boxInfo[0], Integer.parseInt(boxInfo[1])
                            , Double.parseDouble(boxInfo[2]), idContentsInfo))) {
                        JOptionPane.showMessageDialog(null, "Insert failed");
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
                    JOptionPane.showMessageDialog(null, "Insert Failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void openCVSFile() {
        try {
            Desktop desktop = Desktop.getDesktop();
            // Open a file using the default program for the file type.
            // we will launch a default registered program to open two CVS files
            File file = new File("C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\oneEachListCSV.csv");
            File file2 = new File("C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\src\\boxListCSV.csv");
            desktop.open(file);
            desktop.open(file2);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void insertToStructure() {
        oneEachList = readOneEachList();
        boxList = readBoxList();
    }

    public void sort() {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Number of bills:"));
        myPanel.add(xField);
        myPanel.add(new JLabel("Max weight each:"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Number of Bills and Max Weight Values", JOptionPane.OK_CANCEL_OPTION);
        /**
         if (result == JOptionPane.OK_OPTION) {
         JOptionPane.showMessageDialog(null, "xField value: " + xField.getText());
         JOptionPane.showMessageDialog(null, "yField value: " + yField.getText());

         }
         **/
        int numberOfBill = Integer.parseInt(xField.getText());
        double maxWeight = Double.parseDouble(yField.getText());
        // Create necessary amount of stacks first
        for (int i = 1; i <= numberOfBill; i++) {
            stackArray.add(new Stack(i, boxList, maxWeight));
        }
        // Deal with oneEachList
        for (Stack i : stackArray) {
            i.pushBox(oneEachList);
        }
        // Deal with boxList
        for (Stack i : stackArray) {
            i.pushBox(boxList);
        }
        printBoxesRemain();

        if (oneEachList.isEmpty() && boxList.isEmpty()) {
            exportToFile();
        }
        sorted = true;
    }

    public void printBoxesRemain() {
        if (oneEachList.isEmpty() && boxList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Box remains");
        }
        if (!oneEachList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Remains in oneEachList: ");
            JOptionPane.showMessageDialog(null, oneEachList.toString());
        }
        if (!boxList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Remains in boxList");
            JOptionPane.showMessageDialog(null, boxList.toString());
        }
    }

    public void showSortedStack() {
        if (!sorted)
            JOptionPane.showMessageDialog(null, "Please sort boxes into stack first");
        else {
            int count = 1;
            for (Stack stack : stackArray) {
                JOptionPane.showMessageDialog(null, "Stack " + count + ":");
                JOptionPane.showMessageDialog(null, stack.showAll());
                count++;
            }
        }
    }

    public void exportToFile() {
        final String FILENAME = "C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\Exported\\"
                + java.time.LocalDate.now() + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Stack stack : stackArray) {
                bw.write(stack.toFile() + System.lineSeparator());
            }

            JOptionPane.showMessageDialog(null, "List Exported");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void openExportedTXTFile() {
        try {
            Desktop desktop = Desktop.getDesktop();
            // Open a file using the default program for the file type.
            // we will launch a default registered program to open two CVS files
            File file = new File("C:\\Users\\Asus2017\\Desktop\\Phuc\\Sorter\\Exported\\"
                    + java.time.LocalDate.now() + ".txt");
            desktop.open(file);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void clearList() {
        boxList.clear();
        oneEachList.clear();
        sorted = false;
    }
}
