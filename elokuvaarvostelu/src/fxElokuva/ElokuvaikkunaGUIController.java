package fxElokuva;

import java.net.URL;
import java.util.ResourceBundle;

import elokuva.Elokuva;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * @author Eelis, Tero
 * @version 1.2.2024
 *
 */
public class ElokuvaikkunaGUIController implements ModalControllerInterface<Elokuva>, Initializable{
    
    
    @SuppressWarnings("javadoc")
    @FXML public void handleJata() {
        Dialogs.showMessageDialog("En osaa",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
  }
    

    @SuppressWarnings("javadoc")
    @FXML public void handleMuokkaa() {
        Dialogs.showMessageDialog("En osaa",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
  }

    @FXML
    private Text nimi;
    
    
  //=========================================================
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("nimi: " + nimi); // Tarkista, onko nimi null
        
    }

    @Override
    public Elokuva getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Elokuva arg0) {
        // TODO Auto-generated method stub
        
    }
    
    
  //=========================================================
    
    
    /**
     * @param elokuvannimi elokuvan nimi
     */
    public void asetaTiedot(String elokuvannimi) {
        nimi.setText(elokuvannimi);
    }
    
    
    /** alustaa avatun elokuvan näytön
     * @param elokuva elokuva jota tutkitaan
     */
    public void alusta(Elokuva elokuva) {
        
        ModalController.showModal(ElokuvaikkunaGUIController.class.getResource("Elokuvaikkuna.fxml"), "Elokuva", null, null);
        
        System.out.println("MORO");
        
        String elokuvannimi = elokuva.getElokuvaNimi();
        System.out.println(elokuvannimi);
        asetaTiedot(elokuvannimi);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
