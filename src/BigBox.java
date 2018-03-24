import java.util.ArrayList;

public class BigBox extends Box {
    ArrayList<String> idContents;

    public BigBox(ArrayList<String> idContents) {
        this.idContents = idContents;
    }

    public BigBox(int milk, double weight, String iD, ArrayList<String> idContents) {
        super(milk, weight, iD);
        this.idContents = idContents;
    }

    public ArrayList<String> getIdContents() {
        return idContents;
    }

    public void setIdContents(ArrayList<String> idContents) {
        this.idContents = idContents;
    }
}
