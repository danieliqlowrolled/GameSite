package gymhum.de;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

import gymhum.de.model.minesweeper.minefeld;
import gymhum.de.model.minesweeper.Ende;

@Controller
public class MineSweeperController {

    minefeld[][] minefelder;
    Ende ende;
    int totalbombs;
    int expectedbombs;

    Random rand = new Random();
    
    public MineSweeperController(){
        setMinefelder(new minefeld[10][10]);
        setEnde(new Ende());
        setExpectedbombs(20); // Repräsentiert die Gesamtanzahl an Bomben auf dem Spielfeld
        setTotalbombs(0);
        initFeldandBombs();
        Anzeige();
    }

    @GetMapping("/minesweeper")
    public String MineSweeper(@RequestParam(name="activePage", required = false, defaultValue = "minesweeper") String activePage, Model model){
        model.addAttribute("activePage", "minesweeper");
        model.addAttribute("minefelder", getMinefelder());
        model.addAttribute("ende", getEnde());
        return "index.html";
    }

    @GetMapping("/reveal")
    public String reveal(@RequestParam(name="activePage", required = true, defaultValue = "minesweeper") String activePage, @RequestParam(name="hoehe", required = true) int hoehe, @RequestParam(name="breite", required = true) int breite, Model model){
        getMinefelder()[hoehe][breite].setIstFreigelegt(true);
        System.out.println("Feld " + hoehe + " " + breite +" wurde freigelegt");
        if(getMinefelder()[hoehe][breite].getIstBombe() == true){
            getEnde().setVerloren(true);
        }
        return "redirect:/minesweeper";
    }

    private void initFeldandBombs(){
        int counter = 0;
        for(int h = 0; h < 10; h++){
            for(int b = 0; b < 10; b++){
                getMinefelder()[h][b] = new minefeld();
                getMinefelder()[h][b].setHoehe(h);
                getMinefelder()[h][b].setBreite(b);
                
                System.out.println("Minesweeper-Feld mit Höhe " + h + " und Breite " + b +" wurde erstellt " + counter);
                counter += 1;
            }
        }
        for(int i = 1; i <= (expectedbombs); i++){
            int randomhoehe = rand.nextInt(10);
            int randombreite = rand.nextInt(10);
            if(getMinefelder()[randomhoehe][randombreite].getIstBombe() == false){
                getMinefelder()[randomhoehe][randombreite].setIstBombe(true);
                System.out.println("Minesweeper-Feld mit Höhe " + randomhoehe + " und Breite " + randombreite +" wurde in Bombe " + i + " umgewandelt");
            }
        }
    }

    private void Anzeige(){

        
        // Anzeige Randstücke
        for(int hoehe = 0; hoehe < 10; hoehe++){
            for(int breite = 0; breite < 10; breite ++){
                if((getMinefelder()[hoehe][breite].getHoehe() == 0) && (getMinefelder()[hoehe][breite].getBreite() == 0)){
                    int bombsnearby = 0;
                    for(int c = 0; c <= 1; c++){
                        for(int d = 0; d <= 1; d++){
                            if(getMinefelder()[hoehe+c][breite+d].getIstBombe() == true){
                                bombsnearby += 1;
                            }
                        }
                    }
                    getMinefelder()[hoehe][breite].setNearBombs(bombsnearby);
                    System.out.println("Minefeld " + hoehe + breite + " ist von " + bombsnearby + " umgeben");
                }

                if((getMinefelder()[hoehe][breite].getHoehe() == 0) && (getMinefelder()[hoehe][breite].getBreite() == 9)){
                    int bombsnearby = 0;
                    for(int c = 0; c <= 1; c++){
                        for(int d = -1; d <= 0; d++){
                            if(getMinefelder()[hoehe+c][breite+d].getIstBombe() == true){
                                bombsnearby += 1;
                            }
                        }
                    }
                    getMinefelder()[hoehe][breite].setNearBombs(bombsnearby);
                    System.out.println("Minefeld " + hoehe + breite + " ist von " + bombsnearby + " umgeben");
                }

                if((getMinefelder()[hoehe][breite].getHoehe() == 9) && (getMinefelder()[hoehe][breite].getBreite() == 0)){
                    int bombsnearby = 0;
                    for(int c = -1; c <= 0; c++){
                        for(int d = 0; d <= 1; d++){
                            if(getMinefelder()[hoehe+c][breite+d].getIstBombe() == true){
                                bombsnearby += 1;
                            }
                        }
                    }
                    getMinefelder()[hoehe][breite].setNearBombs(bombsnearby);
                    System.out.println("Minefeld " + hoehe + breite + " ist von " + bombsnearby + " umgeben");
                }

                if((getMinefelder()[hoehe][breite].getHoehe() == 9) && (getMinefelder()[hoehe][breite].getBreite() == 9)){
                    int bombsnearby = 0;
                    for(int c = -1; c <= 0; c++){
                        for(int d = -1; d <= 0; d++){
                            if(getMinefelder()[hoehe+c][breite+d].getIstBombe() == true){
                                bombsnearby += 1;
                            }
                        }
                    }
                    getMinefelder()[hoehe][breite].setNearBombs(bombsnearby);
                    System.out.println("Minefeld " + hoehe + breite + " ist von " + bombsnearby + " umgeben");
                }
            }
        }

        
        // Anzeige oberer Rand ohne Endstücke
        for(int breite = 1; breite < 9; breite ++){
            int bombsnearby = 0;
            for(int c = 0; c <= 1; c++){
                for(int d = -1; d <= 1; d++){
                    if(getMinefelder()[0+c][breite+d].getIstBombe() == true){
                        bombsnearby += 1;
                    }
                }
            }
            getMinefelder()[0][breite].setNearBombs(bombsnearby);
            System.out.println("Minefeld 0 " + breite + " ist von " + bombsnearby + " umgeben");
        }
        
        
        // Anzeige unterer Rand ohne Endstücke
        for(int breite = 1; breite < 9; breite ++){
            int bombsnearby = 0;
            for(int c = -1; c <= 0; c++){
                for(int d = -1; d <= 1; d++){
                    if(getMinefelder()[9+c][breite+d].getIstBombe() == true){
                        bombsnearby += 1;
                    }
                }
            }
            getMinefelder()[9][breite].setNearBombs(bombsnearby);
            System.out.println("Minefeld 9 " + breite + " ist von " + bombsnearby + " umgeben");
        }

        // Anzeige Rand links ohne Endstücke
        for(int hoehe = 1; hoehe < 9; hoehe ++){
            int bombsnearby = 0;
            for(int c = -1; c <= 1; c++){
                for(int d = 0; d <= 1; d++){
                    if(getMinefelder()[hoehe+c][0+d].getIstBombe() == true){
                        bombsnearby += 1;
                    }
                }
            }
            getMinefelder()[hoehe][0].setNearBombs(bombsnearby);
            System.out.println("Minefeld " + hoehe + " 0 ist von " + bombsnearby + " umgeben");
        }

        // Anzeige Rand rechts ohne Endstücke
        for(int hoehe = 1; hoehe < 9; hoehe ++){
            int bombsnearby = 0;
            for(int c = -1; c <= 1; c++){
                for(int d = -1; d <= 0; d++){
                    if(getMinefelder()[hoehe+c][9+d].getIstBombe() == true){
                        bombsnearby += 1;
                    }
                }
            }
            getMinefelder()[hoehe][9].setNearBombs(bombsnearby);
            System.out.println("Minefeld " + hoehe + " 9 ist von " + bombsnearby + " umgeben");
        }
        
        // Anzeige restliche Felder
        for(int hoehe = 1; hoehe < 9; hoehe++){
            for(int breite = 1; breite < 9; breite ++){
                int bombsnearby = 0;
                for(int c = -1; c <= 1; c++){
                    for(int d = -1; d <= 1; d++){
                        if(getMinefelder()[hoehe+c][breite+d].getIstBombe() == true){
                            bombsnearby += 1;
                        }
                    }
                }
                getMinefelder()[hoehe][breite].setNearBombs(bombsnearby);
                System.out.println("Minefeld " + hoehe + breite + " ist von " + bombsnearby + " umgeben");
            }
        }
    }

    // Setter und Getter
    public void setMinefelder(minefeld[][] minefelder) {
        this.minefelder = minefelder;
    }
    public void setEnde(Ende ende) {
        this.ende = ende;
    }
    public void setExpectedbombs(int expectedbombs) {
        this.expectedbombs = expectedbombs;
    }
    public void setTotalbombs(int totalbombs) {
        this.totalbombs = totalbombs;
    }
    public minefeld[][] getMinefelder() {
        return minefelder;
    }
    public Ende getEnde() {
        return ende;
    }
    public int getExpectedbombs() {
        return expectedbombs;
    }
    public int getTotalbombs() {
        return totalbombs;
    }
}
