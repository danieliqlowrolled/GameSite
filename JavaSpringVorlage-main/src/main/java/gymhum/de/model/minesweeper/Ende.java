package gymhum.de.model.minesweeper;

public class Ende {
    boolean gewonnen;
    boolean verloren;

    public Ende(){
        setGewonnen(false);
        setVerloren(false);
    }
    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }
    public void setVerloren(boolean verloren) {
        this.verloren = verloren;
    }
    public boolean getGewonnen(){
        return gewonnen;
    }
    public boolean getVerloren(){
        return verloren;
    }
}
