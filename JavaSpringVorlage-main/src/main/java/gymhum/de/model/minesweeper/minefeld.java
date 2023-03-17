package gymhum.de.model.minesweeper;

public class minefeld {
    int hoehe;
    int breite;
    int nearBombs;
    boolean istFreigelegt;
    boolean istBombe;

    public minefeld(){
        setIstFreigelegt(false);
        setIstBombe(false);
    }
    public void setBreite(int breite) {
        this.breite = breite;
    }
    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }
    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }
    public void setIstFreigelegt(boolean istFreigelegt) {
        this.istFreigelegt = istFreigelegt;
    }
    public void setIstBombe(boolean istBombe) {
        this.istBombe = istBombe;
    }
    public int getBreite() {
        return breite;
    }
    public int getHoehe() {
        return hoehe;
    }
    public int getNearBombs() {
        return nearBombs;
    }
    public boolean getIstFreigelegt() {
        return istFreigelegt;
    }
    public boolean getIstBombe(){
        return istBombe;
    }
}
