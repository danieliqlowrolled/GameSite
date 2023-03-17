package gymhum.de.model.tictactoe;

public class Gewonnen {
    boolean gewonnen;

    public Gewonnen(boolean gewonnen){
        setGewonnen(false);
    }
    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }

    public boolean getGewonnen() {
        return gewonnen;
    }
}
