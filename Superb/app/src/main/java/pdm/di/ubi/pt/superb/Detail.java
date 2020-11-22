package pdm.di.ubi.pt.superb;

import java.util.ArrayList;
import java.util.List;

public class Detail {
    private ArrayList<String> salutation;
    private ArrayList<String> icebreak;
    private ArrayList<String> vows;
    private ArrayList<String> dismissal;

    public Detail(ArrayList<String> sal, ArrayList<String> ib, ArrayList<String> vow, ArrayList<String> dism ) {


        salutation = (ArrayList<String>) sal.clone();
        icebreak = (ArrayList<String>) ib.clone();
        vows = (ArrayList<String>) vow.clone();
        dismissal = (ArrayList<String>) dism.clone();

    }

    public ArrayList<String> getSalutation() {
        return salutation;
    }

    public ArrayList<String> getIcebreaks() {
        return icebreak;
    }

    public void setIcebreak(ArrayList<String> icebreak) {
        this.icebreak = icebreak;
    }

    public ArrayList<String> getVows() {
        return vows;
    }

    public void setVows(ArrayList<String> vows) {
        this.vows = vows;
    }

    public ArrayList<String> getDismissal() {
        return dismissal;
    }

    public void setDismissal(ArrayList<String> dismissal) {
        this.dismissal = dismissal;
    }

    @Override
    public String toString() {
        return "Detail {" +
                "salutation=" + salutation.toString() +
                ", icebreak=" + icebreak.toString() +
                ", vows=" + vows.toString() +
                ", dismissal=" + dismissal.toString() +
                '}';
    }
}
