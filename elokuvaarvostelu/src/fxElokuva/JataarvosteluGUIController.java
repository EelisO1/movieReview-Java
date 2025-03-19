package fxElokuva;

import java.net.URL;
import java.util.ResourceBundle;

import elokuva.Arvostelu;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;

/**
 * @author eelis
 * @version 11.10.2024
 *
 */
public class JataarvosteluGUIController implements ModalControllerInterface<Arvostelu>, Initializable{

    @FXML private Label labelVirhe;
    @FXML private Label myLabel;
    @FXML private Slider mySlider;
    @FXML private TextArea kommenttiKentta;
    @FXML private SVGPath tahti;

    @FXML private GridPane gridKentat;
    
    @FXML
    void handleLisaa() {
        if (uusiArvostelu.getKommentit() == "") {
            naytaVirhe("Kommentit ei voi olla tyhjiä");
        return;
        }
        lisaa();
    }

    @FXML
    void handlePeruuta() {
        ModalController.closeStage(labelVirhe);
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }

    @Override
    public Arvostelu getResult() {
        if (uusiArvostelu.getTunnusNro() == 0) {
            return null;
        }
        return uusiArvostelu;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Arvostelu oletus) {
        // TODO Auto-generated method stub
        
    }
    
    
    // ==========================================================
    
    int arvio;
    private Arvostelu uusiArvostelu = new Arvostelu();
    private Node[] edits;
    
    private void alusta() {
        edits = luoKentat(gridKentat);
        uusiArvostelu.setArvosana(1);
        mySlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0,
                    Number arg1, Number arg2) {
                
                arvio = (int) mySlider.getValue();
                myLabel.setText(Integer.toString(arvio) + " Tähteä");
                kasitteleMuutos(mySlider);
            }
        });
        
        for (Node edit : edits) {            
            if ( edit != null ) {
                if (edit instanceof TextArea) {
                    ((TextArea) edit).setOnKeyReleased(e -> kasitteleMuutos(edit));
                }
            }
        }
    }
    
    
    /**
     * Luodaan GridPaneen jäsenen tiedot
     * @param gridPane mihin tiedot luodaan
     * @return luodut tekstikentät
     */
    public Node[] luoKentat(GridPane gridPane) {
        gridPane.getChildren().clear();
        Node[] edits1 = new Node[3];
        
        for (int i=0, k = 0; k < 4; k++, i++) {
            if (k==1) {
                Label label1 = new Label("Arvosana:");
                gridPane.add(label1, 0, i);
                mySlider.setSnapToTicks(true); // Miks tää ei toimi hmmmmm 
                Slider edit = mySlider;
                edits1[k] = edit;
                edit.setId("e"+k);
                gridPane.add(mySlider, 1, i);
                continue;
            }
            
            if (k==2) {
                Label label1 = new Label("Vapaat Kommentit:");
                gridPane.add(label1, 0, i);
                TextArea edit = kommenttiKentta;
                edits1[k] = edit;
                edit.setId("e"+k);
                gridPane.add(kommenttiKentta, 1, i);
                continue;
            }
            
            if (k==3) {
                gridPane.add(tahti, 0, i);
                gridPane.add(myLabel, 0, i);
            }
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
    
    
    private void kasitteleMuutos(Node edit) {
        if (uusiArvostelu == null) return;
        
        int k = getFieldId(edit, 1);
        
        String s = null;

        if (edit instanceof TextArea) {
            TextArea textArea = (TextArea) edit;
            s = textArea.getText();
            String virhe = uusiArvostelu.aseta(k, s);
            naytaVirhe(virhe);  
        }

        else if (edit instanceof Slider) {
            Slider slider = (Slider) edit;
            int sliderValue = (int) slider.getValue();
            String sliderString = Integer.toString(sliderValue);
            String virhe = uusiArvostelu.aseta(k, sliderString);
            naytaVirhe(virhe);  
            s = Integer.toString(sliderValue); 
        }


        if (s != null) {
            String virhe = uusiArvostelu.aseta(k, s);
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
        
        ModalController.closeStage(labelVirhe);
        
        uusiArvostelu.rekisteroi();
        System.out.println(uusiArvostelu.toString());
        
        if (labelVirhe == null) {
            System.out.println("labelVirhe is null");
        } else if (labelVirhe.getScene() == null) {
            System.out.println("labelVirhe is not attached to a scene");
        } else {
            ModalController.closeStage(labelVirhe);
        }
    }
    
    
}
