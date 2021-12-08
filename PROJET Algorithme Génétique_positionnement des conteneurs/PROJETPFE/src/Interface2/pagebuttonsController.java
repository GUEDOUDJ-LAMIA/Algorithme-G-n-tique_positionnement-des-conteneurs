package Interface2;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class pagebuttonsController implements Initializable   {
	
	@FXML
    private Button btn_regler;

    @FXML
    private Button btn_mise;

    @FXML
    private Button btn_retour;

    @FXML
    private Label message;
	DropShadow ds = new DropShadow();
    @FXML
    void affiche1(MouseEvent event) {
    	message.setText(null);
    	btn_regler.setEffect(null);
        btn_mise.setEffect(null);
        btn_retour.setEffect(null);
    }

    @FXML
    void affiche11(MouseEvent event) {
    	message.setText(".... Lancement et Configuration de l’algorithme génétique ....");
    	btn_regler.setEffect(ds);
    }


    @FXML
    void affiche22(MouseEvent event) {
        message.setText(".... La mise à jour des conteneurs \n (Ajouter ,Supprimer et Modifier Conteneur) ....");
        btn_mise.setEffect(ds);
    }


    @FXML
    void affiche44(MouseEvent event) {
        message.setText(".... Retourner à la Page d'accueil ....");
        btn_retour.setEffect(ds);
    }

    @FXML
    void buttonClicked(ActionEvent event)throws IOException
	    	{

	    	    Stage appStage;
	    	    Parent root;
	    	    if(event.getSource()== btn_regler)
	    	    {
	    	        appStage=(Stage) btn_regler.getScene().getWindow();
	    	        root=FXMLLoader.load(getClass().getResource("/interface3/reglage.fxml"));
	    	        Scene scene=new Scene(root);
	    	        appStage.setScene(scene);
	    	        appStage.show();
	    	        
	    	    }
	    	    if (event.getSource()== btn_mise)
	    	    { appStage=(Stage) btn_mise.getScene().getWindow();
    	        root=FXMLLoader.load(getClass().getResource("/interface5/mise.fxml"));
    	        Scene scene=new Scene(root);
    	        appStage.setScene(scene);
    	        appStage.show();
	    	    }
	    	 
	    	    if (event.getSource()== btn_retour)
	    	    {
	    	    appStage=(Stage) btn_retour.getScene().getWindow();
    	        root=FXMLLoader.load(getClass().getResource("/interface1/pagedac.fxml"));
    	        Scene scene=new Scene(root);
    	        appStage.setScene(scene);
    	        appStage.show();
	    	    }
	    	   
	    	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
