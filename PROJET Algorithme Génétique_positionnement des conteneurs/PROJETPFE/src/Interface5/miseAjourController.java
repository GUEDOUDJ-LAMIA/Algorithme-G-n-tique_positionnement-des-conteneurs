package Interface5;
import java.io.IOException;



import java.text.ParseException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import BDD.ConnectionBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import PFE.Conteneur;
import PFE.DATE;
import PFE.Type;
import PFE.PRODUIT;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class miseAjourController implements Initializable {

	
	@FXML
	private JFXComboBox<String> combo_type;

	@FXML
	private JFXComboBox<String> combo_produit;

	@FXML
	private JFXDatePicker date_arrivee;

	@FXML
	private JFXDatePicker date_depart;

	@FXML
	private TableView<Conteneur> tableview_Conteneurs;

	@FXML
	private TableColumn<Conteneur, String> idConteneur;

	@FXML
	private TableColumn<Conteneur, String> type;

	@FXML
	private TableColumn<Conteneur, String> Produit;

	@FXML
	private TableColumn<Conteneur, Date> d_arrive;

	@FXML
	private TableColumn<Conteneur, Date> d_depart;
	
	public ObservableList<Conteneur> Data = FXCollections.observableArrayList();

	public ObservableList<Conteneur> DataList;
	

	@FXML
	private Button btn_retour;

    @FXML
    private JFXTextField search;

    @FXML
    private Button btn_réinit;
    @FXML
    private JFXButton btn_ajouter;

    @FXML
    private JFXButton btn_suppetmod;
    
    int index = -1;
    ObservableList<Conteneur> listM;

	@FXML
	void buttonClicked(ActionEvent event) throws IOException {

		Stage appStage;
		Parent root;
		if (event.getSource() == btn_retour) {
			appStage = (Stage) btn_retour.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/interface2/pagebuttonx.fxml"));
			Scene scene = new Scene(root);
			appStage.setScene(scene);
			appStage.show();

		}
	} 
	 @FXML
	    void buttonClicked2() throws IOException{
		    	Stage appStage = new Stage();
		    	Parent root = FXMLLoader.load(getClass().getResource("/interface5/suppetmod.fxml"));
		        Scene scene = new Scene(root);
                appStage.initStyle(StageStyle.UTILITY);
		        appStage.setTitle(" Information ");
				appStage.setScene(scene);
				
				appStage.show();
		    }

		DropShadow ds = new DropShadow();
		@FXML
		void Affiche1 (MouseEvent event)  {

			if (event.getSource() == btn_retour) {
				btn_retour.setEffect(null);
           }
			if (event.getSource() == btn_réinit) {
				btn_réinit.setEffect(null);
           }
			if (event.getSource() == btn_ajouter) {
				btn_retour.setEffect(null);
           }
			if (event.getSource() == btn_suppetmod) {
				btn_réinit.setEffect(null);
           }
		}
		@FXML
		void Affiche11 (MouseEvent event) {
			if (event.getSource() == btn_retour) {
				btn_retour.setEffect(ds);
                   }
			if (event.getSource() == btn_réinit) {
				btn_réinit.setEffect(ds);
                   }
			if (event.getSource() == btn_ajouter) {
				btn_retour.setEffect(ds);
           }
			if (event.getSource() == btn_suppetmod) {
				btn_réinit.setEffect(ds);
           }
		}

	 //__________________________________________ Recharcher ________________________________________//

		FilteredList<Conteneur> filter = new FilteredList<Conteneur> (Data , e-> true);
    @FXML
    void search_data(){
     	
    	search.textProperty().addListener((observable,oldValue,newValue)->{
   		  filter.setPredicate((Predicate <? super Conteneur>)(Conteneur cont )->{
   		
   			if (newValue.isEmpty () ||newValue==null )  {
   				return true;
   			} String s = newValue.toLowerCase();
   			if (cont.getNumero().contains(newValue.toUpperCase())) {
   				return true;
   			}else if (cont.getDimension().toString().toLowerCase().contains(s)) {
   				return true;
   			}else if (cont.getP().toString().toLowerCase().indexOf(s) != -1) {
   				return true;
   			} else
				try {
					if (cont.getD_arrive().toString().toLowerCase().contains(s) ){
						return true;
					}else if (cont.getD_depart().toString().toLowerCase().contains(s)) {
						return true;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			  
   			  return false;
   		  });
   		  
   	  });
   	 SortedList<Conteneur> sort = new SortedList<Conteneur>(filter);
   	 sort.comparatorProperty().bind( tableview_Conteneurs.comparatorProperty());
   	 tableview_Conteneurs.setItems(sort);
   
 }
    
    //__________________________________________ Updete________________________________________//
    
    public void UpdateTable(){
    	idConteneur.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("idConteneur"));
        type.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("Type"));
        Produit.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("Produit"));
        d_arrive.setCellValueFactory(new PropertyValueFactory<Conteneur, Date>("d_arrive"));
        d_depart.setCellValueFactory(new PropertyValueFactory<Conteneur, Date>("d_depart"));
        listM = ConnectionBD.getDatausers();
        tableview_Conteneurs.setItems(listM);
    	
        idConteneur.setCellValueFactory(Data_Conteneur ->
        {
            return new ReadOnlyStringWrapper(Data_Conteneur .getValue().getNumero());
        });
    	
    	 type.setCellValueFactory(Data_Conteneur ->
        {
            return new ReadOnlyStringWrapper(Data_Conteneur .getValue().getDimension().toString().substring(5).replaceAll("pieds", "  Pieds"));
        });
    	 
    	 Produit.setCellValueFactory(Data_Conteneur ->
    	    {
    	        return new ReadOnlyStringWrapper(Data_Conteneur .getValue().getP().toString().replaceAll("_", "  "));
    	    });

    }
    //__________________________________________ dateFormat ________________________________________//
    
    String dateformat(LocalDate date) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	return date.format(formatter);
    	}
//__________________________________________ Conventir String à LocaleDate ________________________________________//
    LocalDate string_to_local(String date ) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy"); 

    	//convert String to LocalDate
    	LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
    }

    //__________________________________________ length ________________________________________//
    int  length () {
    	 int count = 0;
   	 try {
       java.sql.Connection con1 =  ConnectionBD.Connect();
   	 String sql1 =" SELECT COUNT(*) FROM conteneur ";
   	   PreparedStatement stat1 =con1.prepareStatement(sql1);
   	   ResultSet rs1 =stat1.executeQuery();
   	   rs1.next();
   	      count = rs1.getInt(1);
   	 } catch (SQLException e) {
   			//  Auto-generated catch block
   			e.printStackTrace();}
	return count;
		
    	
    }
 //__________________________________________ Ajouter________________________________________//
    
	@FXML
	void Ajouter(ActionEvent event) throws ParseException {
		Boolean b = false;
		if ((combo_type.getItems().isEmpty()) ||
				(combo_produit.getItems().isEmpty())
				|| (date_arrivee.getValue() == null) 
				|| (date_depart.getValue() == null)) {
			b = true;
		}
		if (b == true) {
			Alert alert = new Alert(AlertType.WARNING);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setPrefSize(500, 200);
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle(" Ajouter Un conteneur ! ");
			alert.setHeaderText("   Avertissement :");
			alert.setContentText("Manque d'information à insérer !");
			alert.showAndWait();
		}
		String t = combo_type.getValue().replaceAll(" Pieds", "pieds");
		String p = combo_produit.getValue().replaceAll(" ", "_");
		LocalDate da = date_arrivee.getValue();
		String DateA = dateformat(da);
		LocalDate dd = date_depart.getValue();
		String Dated = dateformat(dd);
		Conteneur cont = new Conteneur();
		cont.setNumero(Integer.toString(length() + 1));
		Type tt = Type.valueOf("type_".concat(t));
		cont.setDimension(tt);
		PRODUIT pp = PRODUIT.valueOf(p);
		cont.setP(pp);
		DATE dar = new DATE(DateA);
		cont.setD_arrive(dar);
		DATE ddp = new DATE(Dated);
		cont.setD_depart(ddp);

		int status = ConnectionBD.save(cont);
		if (status > 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setPrefSize(500, 200);
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle(" Ajouter Un conteneur ! ");
			alert.setHeaderText(" Information :");
			alert.setContentText(" Le conteneur bien ajouter !");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle(" Ajouter Un conteneur ! ");
			alert.setHeaderText(" Information :");
			alert.setContentText(" Impossible d'ajouter le conteneur !");
			alert.showAndWait();

		}

		combo_type.setValue(null);
		combo_produit.setValue(null);
		date_arrivee.setValue(null);
		date_depart.setValue(null);
		UpdateTable();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        try {
        java.sql.Connection con =  ConnectionBD.Connect();
        String sql ="select * from conteneur";
		   PreparedStatement stat =con.prepareStatement(sql);
		   ResultSet rs =stat.executeQuery();
		   while (rs.next()) { 
			   DATE d  = new DATE(rs.getString(4));
			   DATE d1= new DATE(rs.getString(5));
			   
			   Data.add(new Conteneur("C".concat(rs.getString(1)),PFE.PRODUIT.valueOf(rs.getString(2)),Type.valueOf(rs.getString(3)),d,d1));
		   } con.close();
		} catch (SQLException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        idConteneur.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("idConteneur"));
        type.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("Type"));
        Produit.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("Produit"));
        d_arrive.setCellValueFactory(new PropertyValueFactory<Conteneur, Date>("d_arrive"));
        d_depart.setCellValueFactory(new PropertyValueFactory<Conteneur, Date>("d_depart"));
        tableview_Conteneurs.setItems(Data);
    	
        idConteneur.setCellValueFactory(Data->
        {
            return new ReadOnlyStringWrapper(Data .getValue().getNumero());
        });
    	
    	 type.setCellValueFactory(Data ->
        {
            return new ReadOnlyStringWrapper(Data.getValue().getDimension().toString().substring(5).replaceAll("pieds", "  Pieds"));
        });
    	 
    	 Produit.setCellValueFactory(Data ->
    	    {
    	        return new ReadOnlyStringWrapper(Data.getValue().getP().toString().replaceAll("_", "  "));
    	    });
    
    	

		 //configuration de comboBox
    	 combo_type.getItems().addAll("10 Pieds", "20 Pieds", "40 Pieds");
    	combo_produit.getItems().addAll("Produit 1","Produit 2","Produit 3");
    	 length();
	
}}


