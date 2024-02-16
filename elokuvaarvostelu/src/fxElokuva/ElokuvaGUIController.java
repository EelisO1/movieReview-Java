package fxElokuva;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author Eelis, Tero
 * @version 1.2.2024
 *
 */
public class ElokuvaGUIController {

    @FXML private void handleAvaa() {
        avaaElokuva();
    }
    
    @FXML private void handleLisaaElokuva() {
        lisaaElokuva();
    }
    
    @FXML private void handleTietoja() {
        tietoja();
    }
    
    // ======================================================================
   

    /**
     * 
     */
    public void tietoja() {
        Dialogs.showMessageDialog("Täältä voit katsoa muiden jättämiä arvosteluita erilaisiin elokuviin. Voit myös lisätä uusia elokuvia tietokantaan halutessasi ",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
    }

    
    /**
     * 
     */
    public void lisaaElokuva() {
        Dialogs.showMessageDialog("En osaa",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
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
	
}
