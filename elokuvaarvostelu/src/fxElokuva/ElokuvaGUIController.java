package fxElokuva;

import java.io.PrintStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import elokuva.Arvostelu;
import elokuva.Elokuva;
import elokuva.KokoElokuva;
import elokuva.SailoException;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @author Eelis
 * @version 1.2.2024
 *
 */
public class ElokuvaGUIController implements Initializable{
    
    @FXML private ListChooser<Elokuva> chooserElokuvat;
    @FXML private ScrollPane panelElokuva;
    @FXML private TextArea kuvaus;
    @FXML TextField hakuehto;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
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
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    

   /* @FXML
    void handlePoista() {
        poista();
    }*/
    
    //Jos jaksaa niin muuta tämä samalla tavalla kuin ylhäällä
    @FXML private void handleHakuehto() {
        haku();
    }
    
    
    @FXML private void handlePoista() {
        poista();
    }

    
    // ======================================================================
   
    private KokoElokuva kokoelokuva;
    private Elokuva elokuvaKohdalla;
    
    
    private void alusta() {
        chooserElokuvat.addSelectionListener(e -> naytaElokuva());
    }

    /**
     * Hakufunktio ohjelmaan jolla voi listasta hakea tietyllä nimellä alkavia elokuvia.
     */
    private void haku() { 
        String searchText = hakuehto.getText().trim().toLowerCase();
        chooserElokuvat.clear();
        int index = 0;
        int selectedId = -1;
        
        try {
            int elokuvaCount = kokoelokuva.getElokuvat();
            for (int i = 0; i < elokuvaCount; i++) {
                Elokuva elokuva = kokoelokuva.annaElokuva(i);
                if (elokuva == null) continue;
                String movieName = elokuva.getElokuvaNimi().toLowerCase();
                // Lisää elokuva jos se matchaa 
                if (movieName.contains(searchText)) {
                    chooserElokuvat.add(elokuva.getElokuvaNimi(), elokuva);
                    if (elokuvaKohdalla != null && elokuva.getElokuvaId() == elokuvaKohdalla.getElokuvaId()) {
                        index = chooserElokuvat.getItems().size() - 1;
                        selectedId = elokuva.getElokuvaId();
                    }
                }
            }
            // Asettaa valitun indeksin mikäli mätchää muuten jättää valitsematta
            if (selectedId != -1) {
                chooserElokuvat.setSelectedIndex(index);
            } else {
                chooserElokuvat.setSelectedIndex(-1); // Ei valintaa jos ei löydy vastaavuuksia
            }
        } catch (Exception e) {
            Dialogs.showMessageDialog("Virhe hakutoiminnossa: " + e.getMessage());
        }
    }
    

    
    /**
     * Tietoja dialog
     */
    public void tietoja() {
        Dialogs.showMessageDialog("Täältä voit katsoa muiden jättämiä arvosteluita erilaisiin elokuviin. Voit myös lisätä uusia elokuvia tietokantaan halutessasi ",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
    }

    
    /**
     * Funktio elokuvien ja siihen liittyvien arvostelujen poistoa varten.
     */
    private void poista() { 
        Elokuva selectedMovie = chooserElokuvat.getSelectedObject();
        if (selectedMovie == null) {
            Dialogs.showMessageDialog("Valitse ensin elokuva, jonka haluat poistaa!");
            return;
        }

        boolean confirm = Dialogs.showQuestionDialog("Vahvista poisto",
                "Haluatko varmasti poistaa elokuvan '" + selectedMovie.getElokuvaNimi() + "'?",
                "Kyllä", "Ei");
        if (!confirm) return;

        try {
            kokoelokuva.poista(selectedMovie);
            tallenna();
            hae(0);
            kuvaus.clear();
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Virhe elokuvan poistossa: " + e.getMessage());
        }
    }
    
    
    private void hae(int enr) {
        chooserElokuvat.clear();
        int enro = enr;
        if (enro <= 0) {
            Elokuva kohdalla = chooserElokuvat.getSelectedObject();
            if (kohdalla != null) enro = kohdalla.getElokuvaId();
        }

        int index = 0;
        try {
            int elokuvaCount = kokoelokuva.getElokuvat();
            for (int i = 0; i < elokuvaCount; i++) {
                Elokuva elokuva = kokoelokuva.annaElokuva(i);
                if (elokuva == null) continue;
                chooserElokuvat.add(elokuva.getElokuvaNimi(), elokuva);
                if (elokuva.getElokuvaId() == enro) index = i;
            }
            chooserElokuvat.setSelectedIndex(index);
        } catch (Exception e) {
            Dialogs.showMessageDialog("Virhe elokuvien haussa: " + e.getMessage());
        }
    }
    
    
    /**
     *  Luo uuden elokuvan
     */
    private void lisaaElokuva() {
        Elokuva uusi = new Elokuva();
        
        uusi = ModalController.showModal(ElokuvaGUIController.class.getResource("Lisaaelokuva.fxml"), "Elokuva", null, null);
        if (uusi == null) {
            return;
        }
            
            try {
                kokoelokuva.lisaa(uusi);
                hae(0);
            } catch (SailoException e) {
                Dialogs.showMessageDialog("Alkiot loppuivat kesken!" +e.getMessage(),
                        dlg -> dlg.getDialogPane().setPrefWidth(700));
            }
     
        return;
    }

    
   private void lisaaArvostelu() {
       elokuvaKohdalla = chooserElokuvat.getSelectedObject();
       int elokuvanID = elokuvaKohdalla.getElokuvaId();
       Arvostelu uusi = ModalController.showModal(JataarvosteluGUIController.class.getResource("JataArvostelu.fxml"), "Arvostelu", null, null);
       if (uusi == null) {
           System.out.println("Palautit tyhjän eli ei mitään :)");
           return;
       }
       uusi.setElokuvanId(elokuvanID);
       int pituus = kokoelokuva.getNimetPituus(); //Kikkakolmonen :(
       uusi.setArvostelijanId(pituus);

           kokoelokuva.lisaa(uusi);
           hae(elokuvaKohdalla.getElokuvaId());
           return;
   }
       
        
    /**
     *
     * @return False jos painetaan cancel ja true jos painetaan avaa.
     */
    public boolean avaa() {
        String uusinimi = ElokuvaNimiController.kysyNimi(null, "nimimerkki");
        if (uusinimi == null) return false; 
        lueTiedosto();
        kokoelokuva.luoKayttaja(uusinimi);
        return true;
    }
	
    
    /**
     * @return virheen jos niin käy
     */
    private String lueTiedosto() {
        try {
            kokoelokuva.lueTiedostosta("data"); //vaikka parametrinä kokoelokuva tms minkä keksii kansion nimeksi
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage();
            if (virhe != null) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }
    
    
    /**
     * tallentaa tiedot
     */
    private void tallenna() {
        try {
            kokoelokuva.tallenna("data");
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    
    /**
     * 
     */
    public void naytaElokuva() {
        kuvaus.clear();
        elokuvaKohdalla = chooserElokuvat.getSelectedObject();
        
        if (elokuvaKohdalla == null) return;
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(kuvaus)) {
            elokuvaKohdalla.tulosta(os);
            
            List<Arvostelu> arvostelut = kokoelokuva.annaArvostelut(elokuvaKohdalla);
            
            for (Arvostelu arv: arvostelut) {
                int arvostelija = arv.getArvostelijanId();  
                String arvostelijaNimi = kokoelokuva.annaNimi(arvostelija);
                
                arv.tulosta(os, arvostelijaNimi);
            }
        }
    }
    
    
    /**
     * @param kokoelokuva Mikä elokuva
     */
    public void setElokuva(KokoElokuva kokoelokuva) {
        this.kokoelokuva = kokoelokuva;
    }    
}
