module elokuvaArvostelu {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens fxElokuva to javafx.graphics, javafx.fxml;
}
