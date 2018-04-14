import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SorterGUI extends JFrame {
    private JButton openCVSFilesButton;
    private JButton InsertToStructureButton;
    private JButton sortButton;
    private JButton showWhatIsInListButton;
    private JButton showSortedStackButton;
    private JButton exportToFileButton;
    private JButton openExportedFileButton;
    private JButton resetListButton;

    public SorterGUI(Sorter boxSorter) {

        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Box Sorting App");
        guiFrame.setSize(290, 370);
        // center to middle
        guiFrame.setLocationRelativeTo(null);

        // Initialize JPanel
        JPanel menuPanel = new JPanel();
        openCVSFilesButton = new JButton("Open Excel CVS files");

        InsertToStructureButton = new JButton("Insert Excel files to structure");
        sortButton = new JButton("Sort boxes");
        showWhatIsInListButton = new JButton("Show boxes in the list");
        showSortedStackButton = new JButton("Show sorted stack");
        exportToFileButton = new JButton("Export result to file");
        openExportedFileButton = new JButton("Open exported file");
        resetListButton = new JButton("Reset content of structure");

        menuPanel.setName("Box Sorting App");

        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(openCVSFilesButton, c);
        c.gridy = 1;
        menuPanel.add(InsertToStructureButton, c);
        c.gridy = 2;
        menuPanel.add(sortButton, c);
        c.gridy = 3;
        menuPanel.add(showSortedStackButton, c);
        c.gridy = 4;
        menuPanel.add(showWhatIsInListButton, c);
        c.gridy = 5;
        menuPanel.add(exportToFileButton, c);
        c.gridy = 6;
        menuPanel.add(openExportedFileButton, c);
        c.gridy = 7;
        menuPanel.add(resetListButton, c);
        guiFrame.add(menuPanel);
        // DONE WITH MENU PANEL

        guiFrame.setVisible(true);

        openCVSFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSorter.openCVSFile();
            }
        });
        InsertToStructureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSorter.insertToStructure();
            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSorter.sort();
            }
        });
        showSortedStackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Does not work
                boxSorter.showSortedStack();
            }
        });
        showWhatIsInListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSorter.printBoxesRemain();
            }
        });
        exportToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Does not work
                boxSorter.exportToFile();

            }
        });
        openExportedFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSorter.openExportedTXTFile();
            }
        });
        resetListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSorter.clearList();
            }
        });
    }
}
