package Interface5;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import BDD.ConnectionBD;
import PFE.Conteneur;
import PFE.DATE;
import PFE.PRODUIT;
import PFE.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class updateContoller implements Initializable {

    @FXML
    private JFXTextField filedID;

    @FXML
    private JFXTextField fieldProd;

    @FXML
    private JFXTextField fieldType;

    @FXML
    private JFXTextField fielddarr;

    @FXML
    private JFXTextField fielddd;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supp;
    @FXML
    private JFXButton btn_affiche;

	DropShadow ds = new DropShadow();
	@FXML
	void Affiche1 (MouseEvent event)  {

		if (event.getSource() == btn_affiche) {
			btn_affiche.setEffect(null);
       }
		if (event.getSource() == btn_supp) {
			btn_supp.setEffect(null);
       }
		if (event.getSource() == btn_modifier) {
			btn_modifier.setEffect(null);
       }
		
	}
	@FXML
	void Affiche11 (MouseEvent event) {
		if (event.getSource() == btn_affiche) {
			btn_affiche.setEffect(ds);
       }
		if (event.getSource() == btn_supp) {
			btn_supp.setEffect(ds);
       }
		if (event.getSource() == btn_modifier) {
			btn_modifier.setEffect(ds);
       }
		
	}
@FXML
    void getConteneur(ActionEvent event) throws IOException, ParseException {
  		  String L = filedID.getText().trim();
  		  int N = Integer.parseInt(L);
          Conteneur cont = ConnectionBD.getConteneur(N);
          fieldType.setText(cont.getDimension().toString().substring(5).replaceAll("pieds", " Pieds"));
          fieldProd.setText(cont.getP().toString().replaceAll("_", " "));
          fielddarr.setText(cont.getD_arrive().toString());
          fielddd.setText(cont.getD_depart().toString());
    }

    @FXML
    void Modifier(ActionEvent event) throws ParseException {
    		      String L = filedID.getText();
    		      String p=fieldProd.getText().replaceAll(" ", "_");
    		   	  String t=fieldType.getText().replaceAll(" Pieds","pieds" );
    		   	  String DateA= fielddarr.getText();
    		      String Dated =fielddd.getText();
    		      Conteneur cont = new Conteneur();
    		    cont.setNumero(L);
    		    PRODUIT pp = PRODUIT.valueOf(p);
    		   	cont.setP(pp);
    		   	Type tt = Type.valueOf("type_".concat(t));
    		   	cont.setDimension(tt);
    		   	DATE dar = new DATE(DateA);
    		   	cont.setD_arrive(dar);
    		   	DATE ddp = new DATE(Dated);
    		   	cont.setD_depart(ddp);
    		   	
    			int status= ConnectionBD.modifier(cont);
    			if (status > 0) {
    				 Alert alert = new Alert(AlertType.INFORMATION);
    					DialogPane dialogPane = alert.getDialogPane();
    					dialogPane.setPrefSize(500, 200);
    					dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
    					dialogPane.getStyleClass().add("myDialog");
    			        alert.setTitle(" Modifier Un conteneur ! ");
    			        alert.setHeaderText(" Information :");
    			        alert.setContentText(" Le conteneur bien changer !");
    			        alert.showAndWait();
    		 }else {
    			    Alert alert = new Alert(AlertType.ERROR);
    			    DialogPane dialogPane = alert.getDialogPane();
    				dialogPane.setPrefSize(500, 200);
    				dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
    				dialogPane.getStyleClass().add("myDialog");
    			        alert.setTitle("  Modifier Un conteneur ! ");
    			        alert.setHeaderText(" Information :");
    			        alert.setContentText(" Impossible de changer le conteneur !");
    			        alert.showAndWait();
    			    	   
    			       }
    			filedID.setText(null);
    			fieldType.setText(null);
    			fieldProd.setText(null);
    			fielddarr.setText(null);
    			fielddd.setText(null);
    			
    }

	@FXML
	void Supprimer(ActionEvent event) {
		String L = filedID.getText();
		int N = Integer.valueOf(L);
		int status = ConnectionBD.DELETE(N);
		if (status > 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
          	DialogPane dialogPane = alert.getDialogPane();
          	dialogPane.setPrefSize(500, 200);
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle(" Supprimer Un conteneur ! ");
			alert.setHeaderText(" Information :");
			alert.setContentText(" Le conteneur bien supprimer !");
			alert.showAndWait();
		} else {

			Alert alert = new Alert(AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setPrefSize(500, 200);
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle("  Supprimer Un conteneur ! ");
			alert.setHeaderText(" Information :");
			alert.setContentText(" Impossible de supprimer le conteneur !");
			alert.showAndWait();

		}
		filedID.setText(null);
		fieldType.setText(null);
		fieldProd.setText(null);
		fielddarr.setText(null);
		fielddd.setText(null);
		
	}

   
	


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
}