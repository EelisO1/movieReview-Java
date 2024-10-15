package fxElokuva;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

import elokuva.Elokuva;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author Eelis
 * @version 1.2.2024
 *
 */
public class lisaaelokuvaGUIController implements ModalControllerInterface<Elokuva>, Initializable{

    @FXML private Label labelVirhe;
    @FXML private TextField selectGenre;
    @FXML private TextField selectIkaraja;
    @FXML private TextField selectKesto;
    @FXML private TextArea selectKuvaus;
    @FXML private TextField selectNimi;
    @FXML private TextField selectVuosi;
    
    @FXML private GridPane gridKentat;
    
    
    /**
     * Elokuvan lisäys kun on valmista
     */
    @FXML private void handleLisaa() {
        lisaa();
    }
    

    /**
     * Peruutetaan jos ei haluta
     */
    @FXML private void handlePeruuta() {
        ModalController.closeStage(labelVirhe);
    }
    
    
    
    //=========================================================
    
    
    @Override
    public Elokuva getResult() {
        return uusiElokuva;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Elokuva oletus) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }
    
    //===========================================================
    
    
    private Elokuva uusiElokuva = new Elokuva();
    private static Elokuva apuElokuva = new Elokuva();
    private TextField[] edits;
    
    
    private void alusta() {
        edits = luoKentat(gridKentat);
        for (TextField edit : edits)
            if ( edit != null )
                edit.setOnKeyReleased( e -> kasitteleMuutos(edit));
    }
    
    
    /**
     * Luodaan GridPaneen jäsenen tiedot
     * @param gridPane mihin tiedot luodaan
     * @return luodut tekstikentät
     */
    public TextField[] luoKentat(GridPane gridPane) {
        gridPane.getChildren().clear();
        TextField[] edits1 = new TextField[apuElokuva.getKenttia()];
        
        for (int i=0, k = apuElokuva.ekaKentta(); k < apuElokuva.getKenttia(); k++, i++) {
            Label label = new Label(apuElokuva.getKysymys(k));
            gridPane.add(label, 0, i);
            TextField edit = new TextField();
            edits1[k] = edit;
            edit.setId("e"+k);
            gridPane.add(edit, 1, i);
        }
        return edits1;
    }

    
    /**
     * Palautetaan komponentin id:stä saatava luku
     * @param obj tutkittava komponentti
     * @param oletus mikä arvo jos id ei ole kunnollinen
     * @return komponentin id lukuna 
     */
    public static int getFieldId(Object obj, int oletus) {
        if ( !( obj instanceof Node)) return oletus;
        Node node = (Node)obj;
        return Mjonot.erotaInt(node.getId().substring(1),oletus);
    }

    
    
    private void kasitteleMuutos(TextField edit) {
        if (uusiElokuva == null) return;
        int k = getFieldId(edit,apuElokuva.ekaKentta());
        String s = edit.getText();
        System.out.println(s);
        String virhe = null;
        virhe = uusiElokuva.aseta(k, s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }
    

    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }

    

    private void lisaa() {
        uusiElokuva.lisaaElokuva();
        System.out.println(uusiElokuva.toString());
        
        if (labelVirhe == null) {
            System.out.println("labelVirhe is null");
        } else if (labelVirhe.getScene() == null) {
            System.out.println("labelVirhe is not attached to a scene");
        } else {
            ModalController.closeStage(labelVirhe);
        }
    }
}
