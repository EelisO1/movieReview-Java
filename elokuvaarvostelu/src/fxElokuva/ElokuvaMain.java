package fxElokuva;
	
import elokuva.KokoElokuva;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author eelis, Tero
 * @version 31.1.2024
 *
 */
public class ElokuvaMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("Paaikkuna.fxml"));
		    
	        if (ldr.getLocation() == null) {
	            System.err.println("FXML file not found!");
	            Platform.exit();
	            return;
	        }
		    final Pane root = (Pane)ldr.load();
		    final ElokuvaGUIController ElokuvaCtrl = (ElokuvaGUIController)ldr.getController();
		    
			Scene scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("elokuva.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Elokuva");
			primaryStage.show();
			
			KokoElokuva elokuva = new KokoElokuva();
			ElokuvaCtrl.setElokuva(elokuva);
			
			if ( !ElokuvaCtrl.avaa() ) Platform.exit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei käyt.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
