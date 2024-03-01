package fxElokuva;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author Eelis, Tero
 * @version 1.2.2024
 *
 */
public class ElokuvaikkunaGUIController {
    
    
    /**
     * 
     */
    @FXML public void handleJata() {
        Dialogs.showMessageDialog("En osaa",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
  }
    
    /**
     * 
     */
    @FXML public void handleMuokkaa() {
        Dialogs.showMessageDialog("En osaa",
                dlg -> dlg.getDialogPane().setPrefWidth(400));
  }
}
