import java.util.ArrayList;
import java.util.Arrays;

public class BigBox extends Box {
    private String[] idContents; // contains a String array with IDs of all the boxes in it

    public BigBox(String[] idContents) {
        this.idContents = idContents;
    }

    public BigBox(String iD, int milk, double weight, String[] idContents) {
        super(iD, milk, weight);
        this.idContents = idContents;
    }

    public ArrayList<String> setStackiD(ArrayList<String> list) {
        for (String i : idContents) {
            list.add(i);
        }
        list.add(this.getiD());
        return list;
    }

    public String[] getIdContents() {
        return idContents;
    }

    public void setIdContents(String[] idContents) {
        this.idContents = idContents;
    }


    // Do this later
    @Override
    public String toString() {
        String str = "";
        str =  str.concat(super.toString()).concat("> Contents: "
                + Arrays.toString(idContents) + System.lineSeparator());
        return str;
    }
}
