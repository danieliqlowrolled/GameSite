package gymhum.de;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gymhum.de.model.Feld;
import gymhum.de.model.Gewonnen;
import gymhum.de.model.Spieler;

@Controller
public class SpielController {

    Feld[][] felder;
    Spieler p1;
    Gewonnen g1;
    
    public SpielController(){
        setFelder(new Feld[3][3]);
        setP1(new Spieler(false));
        setG1(new Gewonnen(false));
        initFeld();
        TestWerte();
        Pruefung();
    }

    @GetMapping("/spiel")
    public String showSpiel(@RequestParam(name="activePage", required = false, defaultValue = "spiel") String activePage, Model model){
        model.addAttribute("activePage", "spiel");
        model.addAttribute("felder", getFelder());
        model.addAttribute("spieler", getP1());
        model.addAttribute("gewonnen", getG1());
        return "index.html";
    }

    
    @GetMapping("/addstein")
    public String addStein(@RequestParam(name="activePage", required = true, defaultValue = "spiel") String activePage, @RequestParam(name="id", required = true) int id, @RequestParam(name="hoehe", required = true) int hoehe, @RequestParam(name="breite", required = true) int breite, Model model){
        
        if(getFelder()[hoehe][id].getIstFrei()) {
            if(p1.getActiveplayer() == true) {
                getFelder()[hoehe][id].setIstFrei(false);
                getFelder()[hoehe][id].setZustand(true);
                p1.setActiveplayer(false);
                System.out.println("Feld " + hoehe + " " + id +" wurde geändert in O");   
            } 
            else if(p1.getActiveplayer()== false) {
                getFelder()[hoehe][id].setIstFrei(false);
                getFelder()[hoehe][id].setZustand(false);
                p1.setActiveplayer(true);   
                System.out.println("Feld " + hoehe + " " + id +" wurde geändert in X");          
            }             
        }
        Pruefung();
        return "redirect:/spiel";
    }

    @GetMapping("/neuesSpiel") 
    public String neuesSpiel(@RequestParam(name="activePage", required = true, defaultValue = "spiel") String activePage) {
        initFeld();
        getG1().setGewonnen(false);
        return "redirect:/spiel";
    }

    private void initFeld(){
        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 3; k++){
                getFelder()[i][k] = new Feld();
            }
        }
    }

    // Testwerte für Gewinne werden aufgerufen
    private void TestWerte(){
        // Test Horizontal
        felder[0][0].setIstFrei(false);
        felder[0][0].setZustand(false);
        felder[0][1].setIstFrei(false);
        felder[0][1].setZustand(false);
        felder[0][2].setIstFrei(false);
        felder[0][2].setZustand(false);

        // Test Vertikal
        felder[0][0].setIstFrei(false);
        felder[0][0].setZustand(false);
        felder[1][0].setIstFrei(false);
        felder[1][0].setZustand(false);
        felder[2][0].setIstFrei(false);
        felder[2][0].setZustand(false);

        // Test Schräg Unten
        felder[0][0].setIstFrei(false);
        felder[0][0].setZustand(false);
        felder[1][1].setIstFrei(false);
        felder[1][1].setZustand(false);
        felder[2][2].setIstFrei(false);
        felder[2][2].setZustand(false);

        // Test Schräg Oben
        felder[2][0].setIstFrei(false);
        felder[2][0].setZustand(false);
        felder[1][1].setIstFrei(false);
        felder[1][1].setZustand(false);
        felder[0][2].setIstFrei(false);
        felder[0][2].setZustand(false);

    }

    // Prüfungen für Gewinnszenarios werden durchgeführt
    private void Pruefung(){

        // Prüfung waagerecht
        for(int hoehe = 0; hoehe < 3; hoehe++){
            if(getFelder()[hoehe][0].getIstFrei() == false && getFelder()[hoehe][1].getIstFrei() == false && getFelder()[hoehe][2].getIstFrei() == false){
                if(getFelder()[hoehe][0].getZustand() && getFelder()[hoehe][1].getZustand() && getFelder()[hoehe][2].getZustand()){
                    System.out.println("Spieler O hat gewonnen waagerecht");
                    g1.setGewonnen(true);
                }
                else if(getFelder()[hoehe][0].getZustand() == false && getFelder()[hoehe][1].getZustand()== false && getFelder()[hoehe][2].getZustand() == false){
                    System.out.println("Spieler X hat gewonnen waagerecht");
                    g1.setGewonnen(true);
                }
            }
        }

        // Prüfung senkrecht
        
        for(int breite = 0; breite < 3; breite++){
            if(getFelder()[0][breite].getIstFrei() == false && getFelder()[1][breite].getIstFrei() == false && getFelder()[2][breite].getIstFrei() == false){
                if(getFelder()[0][breite].getZustand() && getFelder()[1][breite].getZustand() && getFelder()[2][breite].getZustand()){
                    System.out.println("Spieler O hat gewonnen senkrecht");
                    g1.setGewonnen(true);
                }
                else if(getFelder()[0][breite].getZustand() == false && getFelder()[1][breite].getZustand() == false && getFelder()[2][breite].getZustand() == false){
                    System.out.println("Spieler X hat gewonnen senkrecht");
                    g1.setGewonnen(true);
                }
            }
        }

        // Prüfung diagonal unten
        
        if(getFelder()[0][0].getIstFrei() == false && getFelder()[1][1].getIstFrei() == false && getFelder()[2][2].getIstFrei() == false){
            if(getFelder()[0][0].getZustand() && getFelder()[1][1].getZustand() && getFelder()[2][2].getZustand()){
                System.out.println("Spieler O hat gewonnen unten");
                g1.setGewonnen(true);
            }
            else if(getFelder()[0][0].getZustand() == false && getFelder()[1][1].getZustand() == false && getFelder()[2][2].getZustand() == false){
                System.out.println("Spieler X hat gewonnen unten");
                g1.setGewonnen(true);
            }
        }

        // Prüfung diagonal oben
        if(getFelder()[2][0].getIstFrei() == false && getFelder()[1][1].getIstFrei() == false && getFelder()[0][2].getIstFrei() == false){
            if(getFelder()[2][0].getZustand() && getFelder()[1][1].getZustand() && getFelder()[0][2].getZustand()){
                System.out.println("Spieler O hat gewonnen oben");
                g1.setGewonnen(true);
            }
            else if(getFelder()[2][0].getZustand() == false && getFelder()[1][1].getZustand() == false && getFelder()[0][2].getZustand() == false){
                System.out.println("Spieler X hat gewonnen oben");
                g1.setGewonnen(true);
            }
        }
    }



    
    // Setter und Getter
    public void setFelder(Feld[][] felder) {
        this.felder = felder;
    }
    
    public Feld[][] getFelder() {
        return felder;
    }

    public void setP1(Spieler p1) {
        this.p1 = p1;
    }

    public Spieler getP1() {
        return p1;
    }

    public void setG1(Gewonnen g1) {
        this.g1 = g1;
    }

    public Gewonnen getG1() {
        return g1;
    }

}