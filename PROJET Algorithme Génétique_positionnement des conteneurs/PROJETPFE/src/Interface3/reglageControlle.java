package Interface3;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import BDD.ConnectionBD;
import PFE.Conteneur;
import PFE.DATE;
import PFE.MaPile;
import PFE.Type;
import PFE.PRODUIT;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class reglageControlle implements Initializable {

	@FXML
	private Spinner<Integer> fieldZONE;

	@FXML
	private Spinner<Integer> fieldPILE;

	@FXML
	private Spinner<Integer> fieldCONTENEUR;

	@FXML
	private Button btn_sauv;

	@FXML
	private Button btn_Affiche;

	@FXML
	private Button btn_traitement;

	@FXML
	private TableView<General> tableview_general;

	@FXML
	private TableColumn<General, String> description;

	@FXML
	private TableColumn<General, String> valeur;

	String S5 = "Nombres de Piles dans chaque zone";
	String S2 = "Nombres de Contenenurs Total ";
	String S3 = "Nombres de Contenenurs  utilisés";
	String S4 = "Nombres de Contenenurs dans chaque pile";

	public ObservableList<General> Data_General = FXCollections.observableArrayList(
			new General(S2, null), new General(S3, null), new General(S4, null),new General(S5, null));

	@FXML
	private TableView<Zone> tableview_zone;

	@FXML
	private TableColumn<Zone, String> numZone;

	@FXML
	private TableColumn<Zone, String> typezone;

	@FXML
	private TableColumn<Zone,String > nbrpiles;

	@FXML
	private TableColumn<Zone,String > nbreCont;

	public ObservableList<Zone> Data_Zones = FXCollections.observableArrayList();
	@FXML
	private TableView<MaPile> tableview_piles;

	@FXML
	private TableColumn<MaPile, String> numpile;

	@FXML
	private TableColumn<MaPile, String> typepile;

	@FXML
	private TableColumn<MaPile, String> nmbr_conteneurs_acteul;

	public ObservableList<MaPile> Data_Pile = FXCollections.observableArrayList();

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

	public ObservableList<Conteneur> Data_Conteneur = FXCollections.observableArrayList();

	@FXML
	private Button btn_retour;

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
	DropShadow ds = new DropShadow();
	@FXML
	void Affiche1 (MouseEvent event)  {

		if (event.getSource() == btn_retour) {
			btn_retour.setEffect(null);

		}
		if (event.getSource() == btn_sauv) {
			btn_sauv.setEffect(null);
		
		}
        if (event.getSource() == btn_Affiche) {
			
        	btn_Affiche.setEffect(null);
		}
      if (event.getSource() == btn_traitement) {
			
    	  btn_traitement.setEffect(null);
		}
		
	}
	@FXML
	void Affiche11 (MouseEvent event) {

		if (event.getSource() == btn_retour) {
			btn_retour.setEffect(ds);

		}
		if (event.getSource() == btn_sauv) {
			btn_sauv.setEffect(ds);
		
		}
        if (event.getSource() == btn_Affiche) {
			
        	btn_Affiche.setEffect(ds);
		}
      if (event.getSource() == btn_traitement) {
			
    	  btn_traitement.setEffect(ds);
		}
		
	}
	@FXML
	public void ENRG(ActionEvent event) throws ParseException {
		int status = ConnectionBD.modifiernombr(fieldPILE.getValue(),
				Integer.parseInt(fieldCONTENEUR.getEditor().getText()));

		int status1 = ConnectionBD.modifierP(fieldPILE.getValue());
		Alert alert;

		if ((status > 0) && (status1 > 0)) {
			
			alert = new Alert(AlertType.INFORMATION);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setPrefSize(500, 200);
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle(" Enregistrement de données ! ");
			alert.setHeaderText(" Information :");
			alert.setContentText(" Les données bien sauvgarder ! \n  Appuyer sur \" Afficher \"  ");
			alert.showAndWait();

		} else {

			alert = new Alert(AlertType.ERROR);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setPrefSize(500, 200);
			dialogPane.getStylesheets().add(getClass().getResource("/PFE/myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.setTitle(" Enregistrement de données ! ");
			alert.setHeaderText(" Information :");
			alert.setContentText(" Impossible de sauvgarder les données !");
			alert.showAndWait();

		}

		if (fieldPILE.getValue() <= (Integer.parseInt(fieldCONTENEUR.getEditor().getText()) / lengthP())) {
			SpinnerValueFactory<Integer> ValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,
					(Integer.parseInt(fieldCONTENEUR.getEditor().getText()) / lengthP()), fieldPILE.getValue());

			fieldPILE.setValueFactory(ValueFactory2);
			fieldPILE.setEditable(true);
		} else {
			SpinnerValueFactory<Integer> ValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,
					(Integer.parseInt(fieldCONTENEUR.getEditor().getText()) / lengthP()),
					(Integer.parseInt(fieldCONTENEUR.getEditor().getText()) / lengthP()));

			ConnectionBD.modifiernombrc((Integer.parseInt(fieldCONTENEUR.getEditor().getText()) / lengthP()));
	        ConnectionBD.modifierPc((Integer.parseInt(fieldCONTENEUR.getEditor().getText()) / lengthP()));
			fieldPILE.setValueFactory(ValueFactory2);
			fieldPILE.setEditable(true);

		}
		tableview_general.setItems(Data_General);
		tableview_Conteneurs.getItems().clear();
		tableview_piles.getItems().clear();
		tableview_zone.getItems().clear();
		btn_Affiche.setDisable(false);

		

	}

	@FXML
	void buttonClicked_trait(ActionEvent event) throws IOException {
        Stage appStage;
		Parent root;
		if (event.getSource() == btn_traitement) {
			appStage = (Stage) btn_traitement.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/interface4/traitement.fxml"));
			Scene scene = new Scene(root);
			appStage.setScene(scene);
			appStage.show();

		}
	}

	// __________________________________________ length
	// _______________________________________//
	int lengthC() {
		int count = 0;
		try {
			java.sql.Connection con1 = ConnectionBD.Connect();
			String sql1 = " SELECT COUNT(*) FROM conteneur ";
			PreparedStatement stat1 = con1.prepareStatement(sql1);
			ResultSet rs1 = stat1.executeQuery();
			rs1.next();
			count = rs1.getInt(1);
			//System.out.println("Number of records in the cricketers_data table: " + count);

		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	// __________________________________________ length
	// ________________________________________//
	int lengthP() {
		int count = 0;
		try {
			java.sql.Connection con1 = ConnectionBD.Connect();
			String sql1 = " SELECT COUNT(*) FROM pile ";
			PreparedStatement stat1 = con1.prepareStatement(sql1);
			ResultSet rs1 = stat1.executeQuery();
			rs1.next();
			count = rs1.getInt(1);
			//System.out.println("Number of records in the cricketers_data table: " + count);

		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	@FXML
	void buttonClicked_Lancer(ActionEvent event) throws IOException, ParseException {
		// _______________________________GENERAL____________________________//
		
		ObservableList<General> D = FXCollections.observableArrayList(
				new General(S2, Integer.toString(lengthC())),
				new General(S3, Integer.toString(ConnectionBD.nombcont())),
				new General(S4, Integer.toString(ConnectionBD.nombpile())),
				new General(S5, Integer.toString(lengthP()))
				);

		if (event.getSource() == btn_Affiche) {

			tableview_general.setItems(D);
			// _______________________________ CONTENEURS ____________________________//
			try {
				java.sql.Connection con = ConnectionBD.Connect();
				String sql = ("select * from conteneur LIMIT "+ ConnectionBD.nombcont());
				PreparedStatement stat = con.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
                int c1=0,c2=0,c3=0;
				while (rs.next()) { // for(int i=0;i<fieldCONTENEUR.getValue();i++ )
					
						DATE d = new DATE(rs.getString(4));
						DATE d1 = new DATE(rs.getString(5));
						switch (PRODUIT.valueOf(rs.getString(2))) {
						case Produit_1:
							c1++;

							break;
						case Produit_2:
							c2++;
							break;
						case Produit_3:
							c3++;
							break;
						}

						Data_Conteneur.add(new Conteneur("C".concat(rs.getString(1)), PRODUIT.valueOf(rs.getString(2)),
								Type.valueOf(rs.getString(3)), d, d1));
					
				}
				con.close();
				ConnectionBD.modifierZonereg(1, c1++);
				ConnectionBD.modifierZonereg(2, c2++);
				ConnectionBD.modifierZonereg(3, c3++);
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} 
			tableview_Conteneurs.setItems(Data_Conteneur);
			//_______________________________Pile____________________________//

			try {
				java.sql.Connection con = ConnectionBD.Connect();
				String sql = "select * from pile";
				PreparedStatement stat = con.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();

				while (rs.next()) {

					Data_Pile.add(new MaPile("P".concat(rs.getString(1)), Type.valueOf(rs.getString(2)), rs.getInt(3)));

				}

				con.close();
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			tableview_piles.setItems(Data_Pile);
			// _______________________________Zones____________________________//
			try {
				java.sql.Connection con = ConnectionBD.Connect();
				String sql = "select * from zonec";
				PreparedStatement stat = con.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				
				while (rs.next()) {

					Data_Zones.add(new Zone("Z".concat(rs.getString(1)), PRODUIT.valueOf(rs.getString(2)), rs.getInt(3),rs.getInt(4)));
					
				}

				con.close();
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			tableview_zone.setItems(Data_Zones );
		} btn_Affiche.setDisable(true);
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Integer x= fieldZONE.getValue();
		SpinnerValueFactory<Integer> ValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,
				(lengthC() / lengthP()), (lengthC() / lengthP()));
		fieldPILE.setValueFactory(ValueFactory2);
		fieldPILE.setEditable(true);
		SpinnerValueFactory<Integer> ValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, lengthC(),
				lengthC());
		fieldCONTENEUR.setValueFactory(ValueFactory3);
		fieldCONTENEUR.setEditable(true);
		// configuration de TableView general
		description.setCellValueFactory(new PropertyValueFactory<General, String>("description"));
		valeur.setCellValueFactory(new PropertyValueFactory<General, String>("valeur"));
		tableview_general.setItems(Data_General);

		description.setCellValueFactory(data -> {
			return new ReadOnlyStringWrapper(data.getValue().getdiscrption());
		});

		valeur.setCellValueFactory(data -> {
			return new ReadOnlyStringWrapper(data.getValue().getvaleur());
		});

		// configuration de TableView Conteneur
		idConteneur.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("idConteneur"));
		type.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("Type"));
		Produit.setCellValueFactory(new PropertyValueFactory<Conteneur, String>("Produit"));
		d_arrive.setCellValueFactory(new PropertyValueFactory<Conteneur, Date>("d_arrive"));
		d_depart.setCellValueFactory(new PropertyValueFactory<Conteneur, Date>("d_depart"));
		tableview_Conteneurs.setItems(Data_Conteneur);

		idConteneur.setCellValueFactory(Data_Conteneur -> {
			return new ReadOnlyStringWrapper(Data_Conteneur.getValue().getNumero());
		});

		type.setCellValueFactory(Data_Conteneur -> {
			return new ReadOnlyStringWrapper(Data_Conteneur.getValue().getDimension().toString().substring(5).replaceAll("pieds", "  Pieds"));
		});

		Produit.setCellValueFactory(Data_Conteneur -> {
			return new ReadOnlyStringWrapper(Data_Conteneur.getValue().getP().toString().replaceAll("_", "  "));
		});

		// configuration de TableView Pile

		numpile.setCellValueFactory(new PropertyValueFactory<MaPile, String>(" N ° Pile"));
		typepile.setCellValueFactory(new PropertyValueFactory<MaPile, String>("Type"));
		nmbr_conteneurs_acteul.setCellValueFactory(new PropertyValueFactory<MaPile, String>("Nbre. Conteneurs Actuel"));

		tableview_piles.setItems(Data_Pile);

		numpile.setCellValueFactory(Data_Pile -> {
			return new ReadOnlyStringWrapper(Data_Pile.getValue().getNumero().toString());
		});

		typepile.setCellValueFactory(Data_Pile -> {
			return new ReadOnlyStringWrapper(Data_Pile.getValue().getP().toString().substring(5).replaceAll("pieds", "  Pieds"));
		});
		nmbr_conteneurs_acteul.setCellValueFactory(Data_Pile -> {
			return new ReadOnlyStringWrapper(Data_Pile.getValue().getNbrC());
		});
		// configuration de TableView Zone
	numZone.setCellValueFactory(new PropertyValueFactory<Zone, String>(" N ° Zone"));
	typezone.setCellValueFactory(new PropertyValueFactory<Zone, String>(" Type de Zones"));
	nbrpiles.setCellValueFactory(new PropertyValueFactory<Zone,String > (" Nombre des Piles"));
	nbreCont.setCellValueFactory(new PropertyValueFactory<Zone,String > (" Nombre des Zones"));
	tableview_zone.setItems(Data_Zones );
	numZone.setCellValueFactory(Data_Zones -> {
		return new ReadOnlyStringWrapper(Data_Zones.getValue().getIdzone());
	});
	typezone.setCellValueFactory(Data_Zones -> {
		return new ReadOnlyStringWrapper(Data_Zones.getValue().getProduitz().replaceAll("_", "  "));
	});
	nbrpiles.setCellValueFactory(Data_Zones -> {
		return new ReadOnlyStringWrapper(Data_Zones.getValue().getNombrepiles());
	});
	nbreCont.setCellValueFactory(Data_Zones -> {
		return new ReadOnlyStringWrapper(Data_Zones.getValue().getNombreconteneur());
	});
	}

	public class General {

		private final SimpleStringProperty description;

		private final SimpleStringProperty valeur;

		public General(String discrp, String val) {
			description = new SimpleStringProperty(discrp);
			valeur = new SimpleStringProperty(val);
		}

		public String getdiscrption() {
			return description.get();
		}

		public void setdiscrption(String disc) {
			description.set(disc);
		}

		public String getvaleur() {
			return valeur.get();
		}

		public void setvaleur(String val) {
			valeur.set(val);
		}
	}
	public class Zone {

		private SimpleStringProperty idzone;
        private PRODUIT produit;
		private SimpleIntegerProperty nombrepiles;
		private SimpleIntegerProperty nombreconteneur;


		public Zone(String idzone, PRODUIT produit, int nombrepiles,
				int nombreconteneur) {
			super();
			this.idzone = new SimpleStringProperty(idzone);
			this.produit = produit;
			this.nombrepiles = new SimpleIntegerProperty(nombrepiles);
			this.nombreconteneur = new SimpleIntegerProperty(nombreconteneur);
			
			
		}


		public String getIdzone() {
			return idzone.get();
		}


		public String getProduitz() {
			return produit.toString();
		}


		public String getNombrepiles() {
			return Integer.toString(nombrepiles.get());
		}


		public String getNombreconteneur() {
			return Integer.toString(nombreconteneur.get());
		}


		public void setIdzone(String idzone) {
			this.idzone.set(idzone);
		}


		public void setType(PRODUIT type) {
			this.produit=type;
		}


		public void setNombrepiles(int nombrepiles) {
			this.nombrepiles.set(nombrepiles);
		}


		public void setNombreconteneur(int nombreconteneur) {
			this.nombreconteneur.set(nombreconteneur);
		}
		
	
	}

		
}

