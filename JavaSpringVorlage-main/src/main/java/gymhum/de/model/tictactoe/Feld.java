package gymhum.de.model.tictactoe;

public class Feld {
    int hoehe;
    int breite;
    boolean istFrei;
    boolean zustand;
    // Zustand true=O und false=X

    public Feld(){
        setIstFrei(true);
        setZustand(true);
    }
    public void setBreite(int breite) {
        this.breite = breite;
    }
    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }
    public void setIstFrei(boolean istFrei) {
        this.istFrei = istFrei;
    }
    public void setZustand(boolean zustand) {
        this.zustand = zustand;
    }
    public int getBreite() {
        return breite;
    }
    public int getHoehe() {
        return hoehe;
    }
    public boolean getIstFrei() {
        return istFrei;
    }
    public boolean getZustand() {
        return zustand;
    }
}
