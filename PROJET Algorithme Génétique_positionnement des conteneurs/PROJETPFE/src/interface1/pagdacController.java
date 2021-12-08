package interface1;

import java.net.URL;

import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import PFE.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class pagdacController implements Initializable {

	@FXML
	private Button btn_entrer;
	@FXML
	private Button btn_quitter;
	@FXML
	private StackPane pane;
	@FXML
	private JFXButton btn_Lam_Quitter;
	@FXML
	private JFXButton btn_Mel_Quitter;

	DropShadow ds = new DropShadow();

	@FXML
	void affiche1(MouseEvent event) {
		btn_entrer.setEffect(null);
	}

	@FXML
	void affiche2(MouseEvent event) {
		btn_quitter.setEffect(null);
	}

	@FXML
	void affiche11(MouseEvent event) {
		btn_entrer.setEffect(ds);
	}

	@FXML
	void affiche22(MouseEvent event) {
		btn_quitter.setEffect(ds);
	}

	@FXML
	void buttonClicked(ActionEvent event) throws IOException {

		Stage appStage;
		Parent root;
		if (event.getSource() == btn_entrer) {
			appStage = (Stage) btn_entrer.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/interface2/pagebuttonx.fxml"));
			Scene scene = new Scene(root);
			appStage.setScene(scene);
			appStage.show();

		}
		if (event.getSource() == btn_quitter) {
			Platform.exit();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
	    	


