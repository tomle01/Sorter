import java.util.*;

/**
 * @author Phuc Le
 * @version 2018.3.20
 * Finished with logic to ignore identical boxes
 * Added sort by amount of milk
 * Will continue working with case 1 for menu
 */
public class Sorter {
    public static void main(String[] args){
        boolean exit = false;
        int choice, numOfBill;
        double maxWeight;

        List<Box> list = new ArrayList<>();
        Stack stack = new Stack();
        Sorting sorting = new Sorting();
        System.out.println("--------------------------------------------");
        do{
            System.out.println("---------------------MENU-------------------");
            System.out.println("1. Insert to Structure\n2. Sort\n3. Show what is in List\n4. Reset\n0. Exit");
            Scanner keyboard = new Scanner(System.in);
            choice = keyboard.nextInt();
            switch(choice){
                case 1:
                    int numOfBox;
                    ///////// WORK ON THIS /////////////
                    System.out.println("Milk Weight ID");
                    int m = keyboard.nextInt();
                    double w = keyboard.nextDouble();
                    int i = keyboard.nextInt();
                    if(!list.add( Box.newBox(m,w,i)))
                        System.out.println("Insert failed");
                    break;
                case 2:
                    // --------------- Done with list ----------------- //
                    System.out.println("How many bills? Max weight each?");
                    numOfBill = keyboard.nextInt(); maxWeight = keyboard.nextDouble();
                    sorting.listToStack(list, numOfBill, maxWeight);
                    break;
                case 3: // Work on this later, total weight and milk left does not work
                    System.out.println(list.toString());
                    break;
                case 4:
                    list.clear();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);

    }
}
