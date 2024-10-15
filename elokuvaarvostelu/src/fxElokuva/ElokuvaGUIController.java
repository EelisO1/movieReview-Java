package fxElokuva;

import java.io.PrintStream;
import java.net.URL;
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

/**
 * @author Eelis
 * @version 1.2.2024
 *
 */
public class ElokuvaGUIController implements Initializable{
    
    @FXML private ListChooser<Elokuva> chooserElokuvat;
    @FXML private ScrollPane panelElokuva;
    @FXML private TextArea kuvaus;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    @FXML private void handleAvaa() {
        //TODO: Poista tämä
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
    
    // TODO: Turha, kikkailtu ympäri
    @FXML private void handleNimenlaittoetusivulle() {
        // aseta nimimerkki käyttäjälle ja sen kaikille arvosteluille
    }
    
    // ======================================================================
   
    private KokoElokuva kokoelokuva;
    
    private void alusta() {
        //chooserElokuvat.clear();
        //panelElokuva.setContent(areaElokuva);
        //areaElokuva.setFont(new Font("Courier New", 12));
        //chooserElokuvat.clear();
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
        
        uusi = ModalController.showModal(ElokuvaGUIController.class.getResource("Lisaaelokuva.fxml"), "Elokuva", null, null);
        if (uusi == null) {
            System.out.println("miksi et toimi NULL");
            return;
        }
            
            /* try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lisaaelokuva.fxml"));
            Parent root = fxmlLoader.load();
 
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Elokuvan lisäys");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
            try {
                kokoelokuva.lisaa(uusi);
                //hae(uusi.getElokuvaId());
                hae(0);
            } catch (SailoException e) {
                Dialogs.showMessageDialog("Alkiot loppuivat kesken!" +e.getMessage(),
                        dlg -> dlg.getDialogPane().setPrefWidth(700));
            }
            // Dialogs.showMessageDialog("En osaa",
            //         dlg -> dlg.getDialogPane().setPrefWidth(400));      
        return;
    }

    
   private void lisaaArvostelu() {
       Elokuva elokuvaKohdalla = chooserElokuvat.getSelectedObject();
       int elokuvanID = elokuvaKohdalla.getElokuvaId();
       Arvostelu uusi = ModalController.showModal(JataarvosteluGUIController.class.getResource("JataArvostelu.fxml"), "Arvostelu", null, null);
       if (uusi == null) {
           System.out.println("miksi et toimi NULL");
           return;
       }
       uusi.setElokuvanId(elokuvanID);
       int pituus = kokoelokuva.getNimetPituus(); //Kikkakolmonen :(
       uusi.setArvostelijanId(pituus);
       
      // try {
           kokoelokuva.lisaa(uusi);
           hae(elokuvaKohdalla.getElokuvaId());
       //} catch (IOException e) {
         //  Dialogs.showMessageDialog("Alkiot loppuivat kesken!" +e.getMessage(),
           //        dlg -> dlg.getDialogPane().setPrefWidth(700));
       //}
           return;
   }
       
      /* vanha 
        if (elokuvaKohdalla == null) return;
        Arvostelu arv = new Arvostelu();
        //TODO: possauta dialogi mihin kirjoittaa arvostelu
        int pituus = kokoelokuva.getNimetPituus();
        //Nimimerkki nimi2 = new Nimimerkki(); //TODO: selvitä ID:n avulla arvostelija
        Nimimerkki nimi = kokoelokuva.annaKayttajanNimiOlio(pituus);
        arv.rekisteroi();
        arv.taytaArvostelu(elokuvaKohdalla.getElokuvaId(), nimi.getTunnusnro());
        //kokoelokuva.lisaa(nimi);
        kokoelokuva.lisaa(arv);
        hae(elokuvaKohdalla.getElokuvaId()); */
    
    /**
     * 
     */
        //elokuvaKohdalla = ModalController.showModal(ElokuvaikkunaGUIController.class.getResource("Elokuvaikkuna.fxml"), "Elokuva", null, null);
        /*if (uusi == null) {
            System.out.println("miksi et toimi NULL");
            return;*/
        
        // aseta elokuvaikkunaan tämänhetkisen elokuvan tiedot ja arvostelut
        
    /**
     * EI TARVITA EHK / pelkästään oman nimimerkin asettamista varten ´alustavasti´
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
        Elokuva elokuvaKohdalla = chooserElokuvat.getSelectedObject();
        
        if (elokuvaKohdalla == null) return;
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(kuvaus)) {
            os.println("Nimi:  " + elokuvaKohdalla.getElokuvaNimi());
            os.println("Kuvaus: " + elokuvaKohdalla.getKuvaus());
            os.println("");
            os.println("Arvostelut:");
            os.println("");
            
            List<Arvostelu> arvostelut = kokoelokuva.annaArvostelut(elokuvaKohdalla);
            
            for (Arvostelu arv: arvostelut) {
                int arvostelija = arv.getArvostelijanId();  
                String arvostelijaNimi = kokoelokuva.annaNimi(arvostelija);
                
                // Muuta allaolevaa ohjelmaa lopputuloksen näköiseksi
                arv.tulosta(os, arvostelijaNimi);
            }
            
        }
        /*
        areaElokuva.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaElokuva)) {
            os.println("--------------------------------------------");
            elokuvaKohdalla.tulosta(os);
            os.println("--------------------------------------------");
            List<Arvostelu> arvostelut = kokoelokuva.annaArvostelut(elokuvaKohdalla);
            
            for (Arvostelu arv: arvostelut) {
                int arvostelija = arv.getArvostelijanId();  
                String arvostelijaNimi = kokoelokuva.annaNimi(arvostelija);
                
                arv.tulosta(os, arvostelijaNimi);
            }
            os.println("--------------------------------------------");
        }*/
    }
    
    /**
     * @param kokoelokuva Mikä elokuva
     */
    public void setElokuva(KokoElokuva kokoelokuva) {
        this.kokoelokuva = kokoelokuva;
    }    
}
