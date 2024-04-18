package fxElokuva;

import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import elokuva.Arvostelu;
import elokuva.Elokuva;
import elokuva.KokoElokuva;
import elokuva.Nimimerkki;
import elokuva.SailoException;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * @author Eelis, Tero
 * @version 1.2.2024
 *
 */
public class ElokuvaGUIController implements Initializable{
    
    @FXML private ListChooser<Elokuva> chooserElokuvat;
    @FXML private ScrollPane panelElokuva;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    @FXML private void handleAvaa() {
        avaaElokuva();
    }
    
    @FXML private void handleLisaaElokuva() {
        lisaaElokuva();
    }
    
    @FXML private void handleLisaaArvostelu() {
        lisaaArvostelu();
    }
    
    @FXML private void handleTietoja() {
        tietoja();
    }
    
    // TODO:
    @FXML private void handleNimenlaittoetusivulle() {
        // aseta nimimerkki käyttäjälle ja sen kaikille arvosteluille
    }
    
    // ======================================================================
   
    private KokoElokuva kokoelokuva;
    private TextArea areaElokuva = new TextArea(); // TODO: väliaikainen, poista myöhemmin
    // TODO: VOISI EHKÄ TEXTFIELDIN MUUTTAA PAAIKKUNA.FXML
    
    private void alusta() {
        chooserElokuvat.clear();
        panelElokuva.setContent(areaElokuva);
        areaElokuva.setFont(new Font("Courier New", 12));
        chooserElokuvat.clear();
        chooserElokuvat.addSelectionListener(e -> naytaElokuva());
    }

    /**
     * 
     */
    public void tietoja() {
        Dialogs.showMessageDialog("Täältä voit katsoa muiden jättämiä arvosteluita erilaisiin elokuviin. Voit myös lisätä uusia elokuvia tietokantaan halutessasi ",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
    }

    
    private void hae(int enro) {
        chooserElokuvat.clear();
        
        int index = 0;
        for (int i = 0; i< kokoelokuva.getElokuvat(); i++) {
            Elokuva elokuva = kokoelokuva.annaElokuva(i);
            if (elokuva.getElokuvaId() == enro) index = i;
            chooserElokuvat.add(""+elokuva.getElokuvaNimi(), elokuva);
        }
        chooserElokuvat.setSelectedIndex(index);
    }
    
    /**
     *  Luo uuden elokuvan
     */
    private void lisaaElokuva() {
        Elokuva uusi = new Elokuva();
        uusi.lisaaElokuva();
        uusi.taytaValmisLeffa();
        try {
            kokoelokuva.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Alkiot loppuivat kesken!" +e.getMessage(),
                    dlg -> dlg.getDialogPane().setPrefWidth(700));
        }
        hae(uusi.getElokuvaId());
       // Dialogs.showMessageDialog("En osaa",
       //         dlg -> dlg.getDialogPane().setPrefWidth(400));
    }

    
    private void lisaaArvostelu() {
        Elokuva elokuvaKohdalla = chooserElokuvat.getSelectedObject();
        if (elokuvaKohdalla == null) return;
        Arvostelu arv = new Arvostelu();
        //TODO: possauta dialogi mihin kirjoittaa arvostelu
        Nimimerkki nimi = new Nimimerkki();
        nimi.rekisteroi();
        nimi.taytaNimi();
        arv.rekisteroi();
        arv.taytaArvostelu(elokuvaKohdalla.getElokuvaId());
        kokoelokuva.lisaa(nimi);
        kokoelokuva.lisaa(arv);
        hae(elokuvaKohdalla.getElokuvaId());
    }
    
    
    /**
     * 
     */
    public void avaaElokuva() {
        Dialogs.showMessageDialog("En osaa",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
    }
    
    /**
     * EI TARVITA EHK
     * @return False jos painetaan cancel ja true jos painetaan avaa.
     */
    public boolean avaa() {
        String uusinimi = ElokuvaNimiController.kysyNimi(null, "elokuva");
        if (uusinimi == null) return false;
       // lueTiedosto(uusinimi);
        return true;
    }
	
    /**
     * 
     */
    public void naytaElokuva() {
        Elokuva elokuvaKohdalla = chooserElokuvat.getSelectedObject();
        
        if (elokuvaKohdalla == null) return;
        
        areaElokuva.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaElokuva)) {
            os.println("--------------------------------------------");
            elokuvaKohdalla.tulosta(os);
            os.println("--------------------------------------------");
            List<Arvostelu> arvostelut = kokoelokuva.annaArvostelut(elokuvaKohdalla);
            
            for (Arvostelu arv: arvostelut)
                arv.tulosta(os);
            os.println("--------------------------------------------");
        }
    }
    
    /**
     * @param kokoelokuva Mikä elokuva
     */
    public void setElokuva(KokoElokuva kokoelokuva) {
        this.kokoelokuva = kokoelokuva;
    }    
}
