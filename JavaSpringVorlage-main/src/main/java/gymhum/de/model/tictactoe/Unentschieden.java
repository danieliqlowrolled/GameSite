package gymhum.de.model.tictactoe;

public class Unentschieden {
    boolean unentschieden;

    public Unentschieden(boolean unentschieden){
        setUnentschieden(false);
    }
    public void setUnentschieden(boolean unentschieden) {
        this.unentschieden = unentschieden;
    }
    public boolean getUnentschieden(){
        return unentschieden;
    }
}
