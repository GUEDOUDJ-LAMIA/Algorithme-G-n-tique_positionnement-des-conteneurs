package Interface4;

import java.io.IOException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import com.jfoenix.controls.JFXTextArea;
import BDD.ConnectionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Interface4.Individual;

public class traitementController implements Initializable {

	@FXML
	private Spinner<Integer> nmbrINDV;

	@FXML
	private Label TAUXCROI;

	@FXML
	private Label TAUXMUT;

	@FXML
	private JFXTextArea tempexe;

	@FXML
	private Button btn_lancer;

	@FXML
	private TabPane tabpane;

	@FXML
	private Tab population;

	@FXML
	private ScrollPane scrollpop;

	@FXML
	private VBox vboxpop;

	@FXML
	private Tab desgine;

	@FXML
	private ScrollPane scrolldesg;

	@FXML
	private VBox vboxdesg;

	@FXML
	private Tab selection;

	@FXML
	private ScrollPane scrollselec;

	@FXML
	private VBox vboxselec;

	@FXML
	private Tab croisement;

	@FXML
	private ScrollPane scrollcroi;

	@FXML
	private VBox vboxcroi;

	@FXML
	private Tab Npopulation;

	@FXML
	private ScrollPane scrollNpop;

	@FXML
	private VBox vboxNpop;

	@FXML
	private Tab desgineN;

	@FXML
	private ScrollPane scrolldesgN;

	@FXML
	private VBox vboxdesgN;

	@FXML
	private Tab correctioncr;

	@FXML
	private ScrollPane scrollcorrcroi;

	@FXML
	private VBox vboxcorrcroi;

	@FXML
	private Tab mutation;

	@FXML
	private ScrollPane scrollmut;

	@FXML
	private VBox vboxmut;

	@FXML
	private Tab correctionmut;

	@FXML
	private ScrollPane scrollcorrmut;

	@FXML
	private VBox vboxcorrmut;

	@FXML
	private Tab mielleur;

	@FXML
	private ScrollPane scrollmind;

	@FXML
	private VBox vboxmind;

	@FXML
	private Tab SolOPti;

	@FXML
	private ScrollPane scrollsolop;

	@FXML
	private VBox vboxsolop;

	@FXML
	private Button btn_Retour;

	@FXML
	private Button btn_réinit;
	DropShadow ds = new DropShadow();
	@FXML
	void Affiche1 (MouseEvent event)  {

		if (event.getSource() == btn_Retour) {
			btn_Retour.setEffect(null);

		}
		if (event.getSource() == btn_lancer) {
			btn_lancer.setEffect(null);
		
		}
        if (event.getSource() ==  btn_réinit) {
			
        	 btn_réinit.setEffect(null);
		}
		
	}
	@FXML
	void Affiche11 (MouseEvent event) {
		if (event.getSource() == btn_Retour) {
			btn_Retour.setEffect(ds);

		}
		if (event.getSource() == btn_lancer) {
			btn_lancer.setEffect(ds);
		
		}
        if (event.getSource() ==  btn_réinit) {
			
        	 btn_réinit.setEffect(ds);
		}
	}

	@FXML
	void buttonClicked(ActionEvent event) throws IOException {

		Stage appStage;
		Parent root;
		if (event.getSource() == btn_Retour) {
			System.out.print("  *************** Button Retour de Page Traitement ************** \n\n");
			appStage = (Stage) btn_Retour.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/interface3/reglage.fxml"));
			Scene scene = new Scene(root);
			appStage.setScene(scene);
			appStage.show();
		}
	}

	@FXML
	void reinitialiser(ActionEvent event) {
		vboxpop.getChildren().clear();
		vboxdesg.getChildren().clear();
		vboxselec.getChildren().clear();
		vboxcroi.getChildren().clear();
		vboxcorrcroi.getChildren().clear();
		vboxmut.getChildren().clear();
		vboxcorrmut.getChildren().clear();
		vboxNpop.getChildren().clear();
		vboxdesgN.getChildren().clear();
		vboxmind.getChildren().clear();
		vboxsolop.getChildren().clear();
		tempexe.clear();
		btn_lancer.setDisable(false);
	}

	@FXML
	void lancer(ActionEvent event) throws ParseException, CloneNotSupportedException {
		ConnectionBD.modifiernombrINdiv(nmbrINDV.getValue());
		long start = System.nanoTime();
		Population population = new Population();
		Individual fittest;
		Individual Resulta;
		Genetique G = new Genetique();
		Individual Solution;
		int nbrC;
		// System.out.println("<------------------------ALGORITHME GENETIQUE
		// ------------------------>");
		Random rn = new Random();
		nbrC = ConnectionBD.nombpile();
		Vector<Conteneur> t = new Vector<Conteneur>();
		System.out.println(ConnectionBD.nombcont());
		try {
			java.sql.Connection con = ConnectionBD.Connect();
			String sql = ("select * from conteneur LIMIT " + ConnectionBD.nombcont());
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				DATE d = new DATE(rs.getString(4));
				DATE d1 = new DATE(rs.getString(5));

				t.add(new Conteneur("C".concat(rs.getString(1)), Interface4.PRODUIT.valueOf(rs.getString(2)),
						Type.valueOf(rs.getString(3)), d, d1));

			}
			con.close();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Initialize population //nbrc de chaq pile
		Vector<Conteneur[]> v = new Vector<Conteneur[]>();

		int c1 = 0, c2 = 0, c3 = 0;
		for (int i = 0; i < t.size(); i++) {

			PRODUIT myVar = t.get(i).p;

			switch (myVar) {
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
		}
		Conteneur[] t1 = new Conteneur[c1];// z1
		Conteneur[] t2 = new Conteneur[c2];// Z2
		Conteneur[] t3 = new Conteneur[c3];// Z3
		int j = 0, y = 0, z = 0;
		for (int i = 0; i < t.size(); i++) {

			PRODUIT myVar = t.get(i).p;

			switch (myVar) {
			case Produit_1:

				t1[j] = t.get(i);
				j++;

				break;
			case Produit_2:

				t2[y] = t.get(i);
				y++;

				break;
			case Produit_3:

				t3[z] = t.get(i);
				z++;

				break;
			}
		}

		v.add(t1);
		v.add(t2);
		v.add(t3);
		Vector<Individual> H = new Vector<Individual>();// LES IND SELECTIONNEE1
		Vector<Individual> H1 = new Vector<Individual>();
		Vector<Individual> w = new Vector<Individual>();

		// NOMBRE DE PILE
		int nbrpile = ConnectionBD.lengthP();
		int nbrPileType = 8;
		final Color[] colors = new Color[] { Color.LIGHTPINK, Color.LIGHTSALMON, Color.LIGHTBLUE, Color.LIGHTGREEN,
				Color.CORAL };
		for (int i = 0; i < v.size(); i++) {

			/*
			 * System.out.println( "------------------------------------ZONE_" + i +
			 * "-------------------------------------------");
			 */

			Text zone = new Text("ZONE -" + (i + 1) + "- ");
			zone.setFont(Font.font("Georgia Bold", 20));
			zone.setFill(colors[i]);
			Text Z1 = new Text(" La Population initiale ");
			Z1.setFont(Font.font("Georgia Bold", 20));
			Z1.setFill(colors[i]);
			Text Zs = new Text(" La Population initiale ");
			Zs.setFont(Font.font("Georgia Bold", 20));
			Zs.setFill(colors[i]);
			Text zones = new Text("ZONE -" + (i + 1) + "- ");
			zones.setFont(Font.font("Georgia Bold", 20));
			zones.setFill(colors[i]);
			Text Z2 = new Text(" La Sélection");
			Z2.setFont(Font.font("Georgia Bold", 20));
			Z2.setFill(colors[i]);
			Text zonec = new Text("ZONE -" + (i + 1) + "- ");
			zonec.setFont(Font.font("Georgia Bold", 20));
			zonec.setFill(colors[i]);
			Text zonecc = new Text("ZONE -" + (i + 1) + "- ");
			zonecc.setFont(Font.font("Georgia Bold", 20));
			zonecc.setFill(colors[i]);
			Text Z3 = new Text(" Le Croisement ");
			Z3.setFont(Font.font("Georgia Bold", 20));
			Z3.setFill(colors[i]);
			Text Z8 = new Text("La Correction du Croisement");
			Z8.setFont(Font.font("Georgia Bold", 20));
			Z8.setFill(colors[i]);
			Text zonem = new Text("ZONE -" + (i + 1) + "- ");
			zonem.setFont(Font.font("Georgia Bold", 20));
			zonem.setFill(colors[i]);
			Text zonecm = new Text("ZONE -" + (i + 1) + "- ");
			zonecm.setFont(Font.font("Georgia Bold", 20));
			zonecm.setFill(colors[i]);
			Text Z4 = new Text(" La Mutation  ");
			Z4.setFont(Font.font("Georgia Bold", 20));
			Z4.setFill(colors[i]);
			Text zonemi = new Text("ZONE -" + (i + 1) + "- ");
			zonemi.setFont(Font.font("Georgia Bold", 20));
			zonemi.setFill(colors[i]);
			Text Z6 = new Text(" Les Meilleurs Individus ");
			Z6.setFont(Font.font("Georgia Bold", 20));
			Z6.setFill(colors[i]);
			Text zonesf = new Text("ZONE -" + (i + 1) + "- ");
			zonesf.setFont(Font.font("Georgia Bold", 20));
			zonesf.setFill(colors[i]);
			Text Z7 = new Text(" La Solution Final");
			Z7.setFont(Font.font("Georgia Bold", 20));
			Z7.setFill(colors[i]);
			Text zoned = new Text("ZONE -" + (i + 1) + "- ");
			zoned.setFont(Font.font("Georgia Bold", 20));
			zoned.setFill(colors[i]);
			Text zonedn = new Text("ZONE -" + (i + 1) + "- ");
			zonedn.setFont(Font.font("Georgia Bold", 20));
			zonedn.setFill(colors[i]);
			Text Z9 = new Text(" Nouvelles Populations ");
			Z9.setFont(Font.font("Georgia Bold", 20));
			Z9.setFill(colors[i]);
			Text zonen = new Text("ZONE -" + (i + 1) + "- ");
			zonen.setFont(Font.font("Georgia Bold", 20));
			zonen.setFill(colors[i]);
			Text Z5 = new Text(" Nouvelles Populations ");
			Z5.setFont(Font.font("Georgia Bold", 20));
			Z5.setFill(colors[i]);
			Text Z = new Text(" La correction de la Mutation");
			Z.setFont(Font.font("Georgia Bold", 20));
			Z.setFill(colors[i]);
			VBox p = new VBox();
			VBox ps, pc, pm, pcm, pcc = null, pmi, pn = null, pnn=null, pdn,pcr;
			VBox Vs = new VBox();
			VBox Vc = new VBox();
			VBox Vcm = new VBox();
			VBox Vn = new VBox();
			VBox Vm = new VBox();
			VBox Vdn = new VBox();
			VBox Vcc = new VBox();
			VBox Vmi = new VBox();
			VBox psf = new VBox();
			VBox pd = new VBox();
			int gn = 0;
			c1 = 0;
			c2 = 0;
			c3 = 0;
			Vector<Conteneur> t11 = new Vector<Conteneur>();
			Vector<Conteneur> t22 = new Vector<Conteneur>();
			Vector<Conteneur> t33 = new Vector<Conteneur>();
			// calculer le nombre des conteneurs selon le type
			int h = 0;
			while (h < v.get(i).length) {

				Type myVar = v.get(i)[h].dimension;

				switch (myVar) {
				case type_10pieds:
					if (t11.size() <= nbrC * nbrPileType) {

						t11.add(v.get(i)[h]);
						h++;
						c1++;
					}
					break;
				case type_20pieds:
					if (t22.size() <= nbrC * nbrPileType) {

						t22.add(v.get(i)[h]);
						h++;
						c2++;
					}
					break;
				case type_40pieds:
					if (t33.size() <= nbrC * nbrPileType) {

						t33.add(v.get(i)[h]);
						h++;
						c3++;
					}
					break;
				}
			}
			// LES CONTENEURS à empiler
			Conteneur[] tt = new Conteneur[t11.size() + t22.size() + t33.size()];
			int m = 0;
			for (int l = 0; l < t11.size(); l++) {
				tt[m] = t11.get(l);
				m++;
			}

			for (int k = 0; k < t22.size(); k++) {
				tt[m] = t22.get(k);
				m++;
			}
			for (int k = 0; k < t33.size(); k++) {
				tt[m] = t33.get(k);
				m++;
			}

			// INITIALISER LA POPULATION
			population.initializePopulation(tt, nbrC, nbrpile);

			// Calculate fitness of each individual
			population.calculateFitness(population.pil, nbrC);

			// AFFICHER LES INDIVIDUS INITIALIS2

			/*
			 * **************************************************** Intialisation
			 * ****************************************************
			 */
			for (int s = 0; s < population.individuals.length; s++) {
				VBox T = new VBox();
				// System.out.println("l'individu : N°" + s);
				Text lb = new Text("l'individu N°" + (s + 1));
				lb.setFont(Font.font("Arial Rounded MT Bold", 15));
				lb.setFill(Color.DIMGREY);
				// System.out.println("Le Fitness : " + population.t.get(s));
				Text lb1 = new Text("Le Fitness de l'individu N°" + (s + 1) + " : " + population.t.get(s));
				lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
				lb1.setFill(Color.DIMGREY);
				// for (int l = 0; l <2 ; l++) {
				VBox indv = new VBox(2);
				HBox hbc = new HBox(2);
				HBox hbp = new HBox(2);

				for (int k = 0; k < population.individuals[s].genes[0].length; k++) {
					// System.out.prinGeorgia Boldt(population.individuals[s].genes[0][k] + "|");
					StackPane pane = new StackPane();
					Label text = new Label(population.individuals[s].genes[0][k]);
					text.setTextFill(Color.WHITE);
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.web("#882255"));
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbc.getChildren().add(pane);

				}
				// System.out.println();
				indv.getChildren().add(hbc);
				for (int k = 0; k < population.individuals[s].genes[0].length; k++) {
					// System.out.print(population.individuals[s].genes[1][k] + "|");
					StackPane pane = new StackPane();
					Label text = new Label(population.individuals[s].genes[1][k]);
					text.setTextFill(Color.web("#882255"));
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.web("#BBBBBB"));
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp.getChildren().add(pane);

				}
				// System.out.println();
				indv.getChildren().add(hbp);
				indv.setSpacing(5);
				indv.setPadding(new Insets(15, 20, 10, 10));
				T.getChildren().addAll(lb, indv, lb1);
				T.setPadding(new Insets(10, 10, 10, 10));
				T.setSpacing(5);
				p.getChildren().addAll(T);
				p.setPadding(new Insets(10, 10, 10, 10));
				p.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

				
			}
			vboxpop.getChildren().addAll(zone, Z1, p);
			vboxpop.setSpacing(10);
			vboxpop.setPadding(new Insets(15, 20, 10, 10));

			// AFFICHER LES PILES INITIALISES

			for (int k = 0; k < population.pil.size(); k++) {
				// System.out.println("individu" + " " + k);
				HBox TT = new HBox();
				Text lb = new Text("l'individu N°" + (k + 1));
				lb.setFont(Font.font("Arial Rounded MT Bold", 15));
				lb.setFill(Color.DIMGREY);
				;
				for (int l = 0; l < population.pil.get(k).length; l++) {

					// population.pil.get(k)[l].Afficher_Pile(population.pil.get(k)[l]);
					TT.getChildren().addAll(population.pil.get(k)[l].Afficher_Pile(population.pil.get(k)[l]));
					TT.setPadding(new Insets(10, 10, 10, 10));
					TT.setSpacing(5);
				}
				pd.getChildren().addAll(lb, TT);
				pd.setSpacing(5);
				pd.setPadding(new Insets(10, 10, 10, 10));
				pd.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

			}

			vboxdesg.getChildren().addAll(zoned, Zs, pd);
			vboxdesg.setSpacing(10);
			vboxdesg.setPadding(new Insets(15, 20, 10, 10));

			/*
			 * **************************************************** Selection
			 * ****************************************************
			 */
			int ind = 0;
               Vector<Individual> tab4=new Vector<Individual>();

			do {
				/*
				 * System.out.println(
				 * "*************************************************************************GENERATION N°"
				 * + gn +
				 * "************************************************************************************************************************************************"
				 * );
				 */
				ps = new VBox();
				pn = new VBox();
				pnn = new VBox();
				pdn = new VBox();
				pc = new VBox();
				pcr = new VBox();
				pcm = new VBox();
				pcc = new VBox();
				pm = new VBox();
				pmi = new VBox();
				Text gs = new Text("Génération -" + (gn + 1) + "- ");
				gs.setFont(Font.font("Georgia Bold", 18));
				gs.setFill(Color.web("#882255"));
				Text gc = new Text("Génération -" + (gn + 1) + "- ");
				gc.setFont(Font.font("Georgia Bold", 18));
				gc.setFill(Color.web("#882255"));
				Text gcc = new Text("Génération -" + (gn + 1) + "- ");
				gcc.setFont(Font.font("Georgia Bold", 18));
				gcc.setFill(Color.web("#882255"));
				Text gm = new Text("Génération -" + (gn + 1) + "- ");
				gm.setFont(Font.font("Georgia Bold", 18));
				gm.setFill(Color.web("#882255"));
				Text gnp = new Text("Génération -" + (gn + 1) + "- ");
				gnp.setFont(Font.font("Georgia Bold", 18));
				gnp.setFill(Color.web("#882255"));
				Text gmi = new Text("Génération -" + (gn + 1) + "- ");
				gmi.setFont(Font.font("Georgia Bold", 18));
				gmi.setFill(Color.web("#882255"));
				Text gdn = new Text("Génération -" + (gn + 1) + "- ");
				gdn.setFont(Font.font("Georgia Bold", 18));
				gdn.setFill(Color.web("#882255"));
				Text gcm = new Text("Génération -" + (gn + 1) + "- ");
				gcm.setFont(Font.font("Georgia Bold", 18));
				gcm.setFill(Color.web("#882255"));
				// Do selection the N/2 of population
				G.selection(H, population);
				// System.out.println("les individus selectionnés : ");
				Text lb1 = new Text("Les individus selectionnés : ");
				lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
				lb1.setFill(Color.DIMGREY);
				for (int s = 0; s < H.size(); s++) {
					VBox T = new VBox();
					// System.out.println("l'individu : N°" + s);
					Text lb = new Text("l'individu N°" + (s + 1));
					lb.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb.setFill(Color.DIMGREY);
					// System.out.println("Le Fitness : " +
					// population.t.get(population.getINdex_Indiv(H.get(s))));
					Text F = new Text("Le Fitness de l'individu N°" + (s + 1) + " : " + population.t.get(population.getINdex_Indiv(H.get(s))));
					F.setFont(Font.font("Arial Rounded MT Bold", 15));
					F.setFill(Color.DIMGREY);
					VBox indv = new VBox();
					HBox hbc = new HBox(2);
					HBox hbp = new HBox(2);
					for (int k = 0; k < H.get(s).genes[0].length; k++) {
						// System.out.print(H.get(s).genes[0][k] + "|");
						StackPane pane = new StackPane();
						Label text = new Label(H.get(s).genes[0][k]);
						text.setTextFill(Color.WHITE);
						Rectangle rect = new Rectangle(30, 20);
						rect.setFill(Color.web("#882255"));
						// add to stackpane
						pane.getChildren().addAll(rect, text);
						hbc.getChildren().add(pane);
					}
					// System.out.println();
					for (int k = 0; k < H.get(s).genes[0].length; k++) {
						// System.out.print(H.get(s).genes[1][k] + "|");
						StackPane pane = new StackPane();
						Label text = new Label(H.get(s).genes[1][k]);
						text.setTextFill(Color.web("#882255"));
						Rectangle rect = new Rectangle(30, 20);
						rect.setFill(Color.web("#BBBBBB"));
						// add to stackpane
						pane.getChildren().addAll(rect, text);
						hbp.getChildren().add(pane);
					}
					// System.out.println();

					indv.getChildren().add(hbc);
					indv.getChildren().add(hbp);
					indv.setSpacing(5);
					indv.setPadding(new Insets(15, 20, 10, 10));
					T.getChildren().addAll(lb, indv, F);
					T.setPadding(new Insets(10, 10, 10, 10));
					T.setSpacing(5);
					ps.getChildren().addAll(T);
					ps.setPadding(new Insets(10, 10, 10, 10));
					ps.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

				}
				population.SetIndividual(H, population.pil);
				// EMPILER LES IND SELECTIONNEE
				G.ModifierSelect(population.individuals, tt, population.pil, population);

				// SUPPRIMMER TOUTES LES VALEURS ANCIENNES DE FITNESS
				population.t.clear();

				population.calculateFitness(population.pil, nbrC);

				// VIDER LES TABLEAU POUR CHAQUE GENERATION

				H1.clear();

				// les individus selectionnee
				for (int k = 0; k < population.individuals.length; k++) {
					H1.add(population.individuals[k]);
				}
                  tab4.clear();            
				  /*
				 * **************************************************** Croisement
				 * ****************************************************
				 */
				// croisement
					int q1 = rn.nextInt(100);
					// System.out.println("Le pourcentage de croisement: " + q1 + "%");
					Text poc = new Text("Le pourcentage de croisement: " + q1 + "%");
					poc.setFont(Font.font("Arial Rounded MT Bold", 20));
					poc.setFill(Color.GRAY);

					if (q1 >= 80) {
						// croisement
						// System.out.println(" On fait le Croisement : ");
						Text fc = new Text(" On fait le Croisement : ");
						fc.setFont(Font.font("Arial Rounded MT Bold", 20));
						fc.setFill(Color.GRAY);
					   pcr =G.crossover(H1, tab4);// retourner le resultats fils de croisement
						H = tab4; pc.getChildren().addAll(poc, fc, pcr );
						pc.setPadding(new Insets(10, 10, 10, 10));
						pc.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY))); // AFFECTER LA NOUVELLE POPULATION
                   population.SetIndividual(H, population.pil);
                    // REEMPILER LES NV INDIV
					Text lbcc = new Text("La correction Aprés Croisement");
					lbcc.setFont(Font.font("Arial Rounded MT Bold", 20));
					lbcc.setFill(Color.DIMGREY);
					pcc.getChildren().addAll(lbcc,
							G.modifierPop(population.individuals, tt, population.pil, population));
					pcc.setPadding(new Insets(10, 10, 10, 10));
					pcc.setSpacing(5);
					pcc.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

					// SUPPRIMMER TOUTES LES VALEURS ANCIENNES DE FITNESS
					population.t.clear();

					population.calculateFitness(population.pil, nbrC);

					// System.out.println();
					// System.out.println("La population Aprés Croisement et Correction :");
					for (int s = 0; s < population.individuals.length; s++) {
						VBox Tcr = new VBox();
						VBox indv = new VBox();
						HBox hbc = new HBox(2);
						HBox hbp = new HBox(2);

						// System.out.println("l'individu :" + s);
						Text lb = new Text("l'individu N° " + (s + 1));
						lb.setFont(Font.font("Arial Rounded MT Bold", 15));
						lb.setFill(Color.DIMGREY);

						// System.out.println("Le Fitness : " + population.t.get(s));
						Text lbf = new Text("Le Fitness de l'individu N°" + (s + 1) + " : " + population.t.get(s));
						lbf.setFont(Font.font("Arial Rounded MT Bold", 15));
						lbf.setFill(Color.DIMGREY);

						for (int k = 0; k < population.individuals[s].genes[0].length; k++) {
							// System.out.print(population.individuals[s].genes[0][k] + "|");
							StackPane pane = new StackPane();
							Label text = new Label(population.individuals[s].genes[0][k]);
							text.setTextFill(Color.WHITE);
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#882255"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbc.getChildren().add(pane);
						}
						// System.out.println();
						for (int k = 0; k < population.individuals[s].genes[1].length; k++) {
							// System.out.print(population.individuals[s].genes[1][k] + "|");
							StackPane pane = new StackPane();
							Label text = new Label(population.individuals[s].genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#BBBBBB"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp.getChildren().add(pane);
						}
						// System.out.println();
						indv.getChildren().add(hbc);
						indv.getChildren().add(hbp);
						indv.setSpacing(5);
						indv.setPadding(new Insets(15, 20, 10, 10));
						Tcr.getChildren().addAll(lb, indv, lbf);
						Tcr.setSpacing(5);
						Tcr.setPadding(new Insets(15, 20, 10, 10));
						pn.getChildren().addAll(Tcr);
						pn.setPadding(new Insets(10, 10, 10, 10));
						pn.setBackground(
								new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

					}
				} else {
					// System.out.println("Y'a pas de Croisement .");
					Text lbc1 = new Text(" Le pourcentage du croisement choisi aleatoirement " + q1
							+ "%  n'appartient pas a l'intervalle [ 80% - 100% ] ... \n  Donc il n'y a pas du Croisement ... ");
					lbc1.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbc1.setFill(Color.DIMGREY);
					pc.getChildren().addAll(lbc1);
					pc.setPadding(new Insets(10, 10, 10, 10));
					pc.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
					Text lbc2 = new Text("Il n'y a pas du Croisement, Alors il n'y a pas de correction ...");
					lbc2.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbc2.setFill(Color.DIMGREY);
					pcc.getChildren().addAll(lbc2);
					pcc.setPadding(new Insets(10, 10, 10, 10));
					pcc.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
					Text lbc3 = new Text(
							" Tant qu'on n'a pas fait le croisement, La nouvelle population est celle de la séléction ...");
					lbc3.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbc3.setFill(Color.DIMGREY);
					for (int s = 0; s < population.individuals.length; s++) {
						VBox Tcr = new VBox();
						VBox indv = new VBox();
						HBox hbc = new HBox(2);
						HBox hbp = new HBox(2);

						// System.out.println("l'individu :" + s);
						Text lb = new Text("l'individu N° " + (s + 1));
						lb.setFont(Font.font("Arial Rounded MT Bold", 15));
						lb.setFill(Color.DIMGREY);

						// System.out.println("Le Fitness : " + population.t.get(s));
						Text lbf = new Text("Le Fitness de l'individu N°" + (s + 1) + " : " + population.t.get(s));
						lbf.setFont(Font.font("Arial Rounded MT Bold", 15));
						lbf.setFill(Color.DIMGREY);

						for (int k = 0; k < population.individuals[s].genes[0].length; k++) {
							// System.out.print(population.individuals[s].genes[0][k] + "|");
							StackPane pane = new StackPane();
							Label text = new Label(population.individuals[s].genes[0][k]);
							text.setTextFill(Color.WHITE);
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#882255"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbc.getChildren().add(pane);
						}
						// System.out.println();
						for (int k = 0; k < population.individuals[s].genes[1].length; k++) {
							// System.out.print(population.individuals[s].genes[1][k] + "|");
							StackPane pane = new StackPane();
							Label text = new Label(population.individuals[s].genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#BBBBBB"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp.getChildren().add(pane);
						}
						// System.out.println();
						indv.getChildren().add(hbc);
						indv.getChildren().add(hbp);
						indv.setSpacing(5);
						indv.setPadding(new Insets(15, 20, 10, 10));
						Tcr.getChildren().addAll(lb, indv, lbf);
						Tcr.setSpacing(5);
						Tcr.setPadding(new Insets(15, 20, 10, 10));
						pnn.getChildren().addAll(Tcr);
						pnn.setSpacing(5);
						pnn.setPadding(new Insets(15, 20, 10, 10));
					}
					pn.getChildren().addAll(lbc3,pnn);
					pn.setPadding(new Insets(10, 10, 10, 10));
					pn.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
				}
				for (int k = 0; k < population.pil.size(); k++) {
					// System.out.println("individu" + " " + k);
					HBox TT = new HBox();
					Text lb11 = new Text("l'individu " + (k + 1));
					lb11.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb11.setFill(Color.DIMGREY);
					;
					for (int l = 0; l < population.pil.get(k).length; l++) {

						// population.pil.get(k)[l].Afficher_Pile(population.pil.get(k)[l]);
						TT.getChildren().addAll(population.pil.get(k)[l].Afficher_Pile(population.pil.get(k)[l]));
						TT.setPadding(new Insets(10, 10, 10, 10));
						TT.setSpacing(5);
					}
					pdn.getChildren().addAll(lb11, TT);
					pdn.setSpacing(5);
					pdn.setPadding(new Insets(10, 10, 10, 10));
					pdn.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

				}
				// System.out.println();

				Float q = rn.nextFloat();
				// DecimalFormat df = new DecimalFormat("0.00");

				// System.out.println("Le taux de mutation : " + q + "%");
				Text pom = new Text("... Le taux  de mutation : " + (((float) ((int) (q * 100))) / 100) + " ... ");
				pom.setFont(Font.font("Arial Rounded MT Bold", 15));
				pom.setFill(Color.GRAY);
				if (q >= -1 && q <= 0.5) {
					// Do mutation under a random probability
					// System.out.println(" On fait une mutattion :");
					Text fm = new Text(" On fait la mutation :");
					fm.setFont(Font.font("Arial Rounded MT Bold", 15));
					fm.setFill(Color.GRAY);
					Resulta = new Individual(tt, tt.length);
					// GET THE FITTEST
					fittest = population.getFittest(ind);
					Resulta = (Individual) fittest.clone();
					ind = population.getINdex_Indiv(fittest);

					// AFFICHER LE FITTEST
					// System.out.println("le fittest individu à muter:");
					Text lbm = new Text("Le Fittest Avant la mutation:");
					lbm.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbm.setFill(Color.DIMGREY);

					// System.out.println();

					/* secondFittest = fittest; */

					// MUTATION
					VBox indvm1 = new VBox();
					indvm1 = G.mutation(fittest, Resulta, population);
					// mise à jour du fittest
					population.individuals[ind] = fittest;
					// System.out.println("La correction après mutation : ");
					Text lbm1 = new Text("La Correction Après Mutation : ");
					lbm1.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbm1.setFill(Color.GRAY);

					pm.getChildren().addAll(pom, fm, indvm1);
					pm.setPadding(new Insets(10, 10, 10, 10));
					pm.setSpacing(5);
					pm.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
					// CORRECTION
					HBox m1 = new HBox();
					m1 = G.modifierPop(population.individuals, tt, population.pil, population);

					// SUPPRIMMER TOUTES LES VALEURS ANCIENNES DE FITNESS
					population.t.clear();

					population.calculateFitness(population.pil, nbrC);

					pcm.getChildren().addAll(lbm1, m1);
					pcm.setPadding(new Insets(10, 10, 10, 10));
					pcm.setSpacing(5);
					pcm.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
				} else {
					// System.out.println("Y'a pas de mutation.");
					Text lbm = new Text(" Le taux de mutation choisi aleatoirement " + (((float) ((int) (q * 100))) / 100) 
							+ " n'appartient pas a l'intervalle [ -1 - 0,5 ] ... \n  Donc il n'y a pas de mutation ...");
					lbm.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbm.setFill(Color.DIMGREY);
					pm.getChildren().addAll(lbm);
					pm.setPadding(new Insets(10, 10, 10, 10));
					pm.setSpacing(5);
					pm.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
					Text lbm2 = new Text("Il n'y a pas de mutation. Alors on ne fait pas de Correction ");
					lbm2.setFont(Font.font("Arial Rounded MT Bold", 15));
					lbm2.setFill(Color.DIMGREY);
					pcm.getChildren().addAll(lbm2);
					pcm.setPadding(new Insets(10, 10, 10, 10));
					pcm.setSpacing(5);
					pcm.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
					// System.out.println();
				}
				/*
				 * **************************************************** Meilleur indv
				 * ****************************************************
				 */
				// System.out.println("les individus final de la generation N° : " + gn);
				Text inn = new Text("les individus final de la generation N° : " + (gn + 1));
				inn.setFont(Font.font("Arial Rounded MT Bold", 15));
				inn.setFill(Color.DIMGREY);
				for (int s = 0; s < population.individuals.length; s++) {
					VBox T1 = new VBox();
					Text lb3 = new Text("l'individu N° " + (s + 1));
					lb3.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb3.setFill(Color.DIMGREY);
					Text lb4 = new Text("Le Fitness de l'individu N° " + (s + 1) + " : " + population.t.get(s));
					lb4.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb4.setFill(Color.DIMGREY);
					VBox indv3 = new VBox(2);
					HBox hbc3 = new HBox(2);
					HBox hbp3 = new HBox(2);
					for (int k = 0; k < population.individuals[s].genes[0].length; k++) {
						// System.out.print(population.individuals[s].genes[0][k] + "|");
						StackPane pane = new StackPane();
						Label text = new Label(population.individuals[s].genes[0][k]);
						text.setTextFill(Color.WHITE);
						Rectangle rect = new Rectangle(30, 20);
						rect.setFill(Color.web("#882255"));
						// add to stackpane
						pane.getChildren().addAll(rect, text);
						hbc3.getChildren().add(pane);

					}
					// System.out.println();
					for (int k = 0; k < population.individuals[s].genes[0].length; k++) {
						// System.out.print(population.individuals[s].genes[1][k] + "|");
						StackPane pane = new StackPane();
						Label text = new Label(population.individuals[s].genes[1][k]);
						text.setTextFill(Color.web("#882255"));
						Rectangle rect = new Rectangle(30, 20);
						rect.setFill(Color.web("#BBBBBB"));
						// add to stackpane
						pane.getChildren().addAll(rect, text);
						hbp3.getChildren().add(pane);
					}
					// System.out.println();

					indv3.getChildren().add(hbc3);
					indv3.getChildren().add(hbp3);
					indv3.setSpacing(5);
					indv3.setPadding(new Insets(15, 20, 10, 10));
					T1.getChildren().addAll(lb3, indv3, lb4);
					T1.setPadding(new Insets(10, 10, 10, 10));
					T1.setSpacing(5);
					pmi.getChildren().addAll(T1);
					pmi.setPadding(new Insets(10, 10, 10, 10));
					pmi.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));

				}
				

				Vs.setSpacing(10);
				Vs.getChildren().addAll(gs, ps);
				Vs.setPadding(new Insets(10, 10, 10, 10));
				Vs.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));

				Vc.setSpacing(10);
				Vc.getChildren().addAll(gc, pc);
				Vc.setPadding(new Insets(10, 10, 10, 10));
				Vc.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));

				Vcc.setSpacing(10);
				Vcc.getChildren().addAll(gcc, pcc);
				Vcc.setPadding(new Insets(10, 10, 10, 10));
				Vcc.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));

				Vm.setSpacing(10);
				Vm.getChildren().addAll(gm, pm);
				Vm.setPadding(new Insets(10, 10, 10, 10));
				Vm.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));

				Vcm.setSpacing(10);
				Vcm.getChildren().addAll(gcm, pcm);
				Vcm.setPadding(new Insets(10, 10, 10, 10));
				Vcm.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));

				Vn.setSpacing(10);
				Vn.getChildren().addAll(gnp, pn);
				Vn.setPadding(new Insets(10, 10, 10, 10));
				Vn.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));
				Vdn.setSpacing(10);
				Vdn.getChildren().addAll(gdn, pdn);
				Vdn.setPadding(new Insets(10, 10, 10, 10));
				Vdn.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));

				Vmi.setSpacing(10);
				Vmi.getChildren().addAll(gmi, inn, pmi);
				Vmi.setPadding(new Insets(10, 10, 10, 10));
				Vmi.setBackground(
						new Background(new BackgroundFill(Color.web("#BBBBBB"), CornerRadii.EMPTY, Insets.EMPTY)));
				// pour chaq zone
				H.removeAllElements();
				w.removeAllElements();
				gn++;
			} while (population.individuals.length > 2);

			vboxselec.setSpacing(10);
			vboxselec.getChildren().addAll(zones, Z2, Vs);
			vboxselec.setPadding(new Insets(15, 20, 10, 10));

			vboxcroi.setSpacing(10);
			vboxcroi.getChildren().addAll(zonec, Z3, Vc);
			vboxcroi.setPadding(new Insets(15, 20, 10, 10));

			vboxcorrcroi.setSpacing(10);
			vboxcorrcroi.getChildren().addAll(zonecc, Z8, Vcc);
			vboxcorrcroi.setPadding(new Insets(15, 20, 10, 10));

			vboxmut.setSpacing(10);
			vboxmut.getChildren().addAll(zonem, Z4, Vm);
			vboxmut.setPadding(new Insets(15, 20, 10, 10));

			vboxcorrmut.setSpacing(10);
			vboxcorrmut.getChildren().addAll(zonecm, Z, Vcm);
			vboxcorrmut.setPadding(new Insets(15, 20, 10, 10));

			vboxNpop.setSpacing(10);
			vboxNpop.getChildren().addAll(zonen, Z5, Vn);
			vboxNpop.setPadding(new Insets(15, 20, 10, 10));

			vboxdesgN.setSpacing(10);
			vboxdesgN.getChildren().addAll(zonedn, Z9, Vdn);
			vboxdesgN.setPadding(new Insets(15, 20, 10, 10));

			vboxmind.setSpacing(10);
			vboxmind.getChildren().addAll(zonemi, Z6, Vmi);
			vboxmind.setPadding(new Insets(15, 20, 10, 10));
			/*
			 * **************************************************** SOl Final
			 * ****************************************************
			 */
			Solution = population.getFittest(ind);
			ind = population.getINdex_Indiv(Solution);
			Text lb5 = new Text("La solution Final :");
			lb5.setFont(Font.font("Arial Rounded MT Bold", 15));
			lb5.setFill(Color.DIMGREY);
			VBox indv4 = new VBox(2);
			HBox hbc4 = new HBox(2);
			HBox hbp4 = new HBox(2);

			for (int k = 0; k < Solution.genes[0].length; k++) {
				// System.out.print(Solution.genes[0][k] + "|");
				StackPane pane = new StackPane();
				Label text = new Label(Solution.genes[0][k]);
				text.setTextFill(Color.WHITE);
				Rectangle rect = new Rectangle(30, 20);
				rect.setFill(Color.web("#882255"));
				// add to stackpane
				pane.getChildren().addAll(rect, text);
				hbc4.getChildren().add(pane);

			}
			// System.out.println("");
			for (int k = 0; k < Solution.genes[0].length; k++) {
				// System.out.print(Solution.genes[1][k] + "|");
				StackPane pane = new StackPane();
				Label text = new Label(Solution.genes[1][k]);
				text.setTextFill(Color.web("#882255"));
				Rectangle rect = new Rectangle(30, 20);
				rect.setFill(Color.web("#BBBBBB"));
				// add to stackpane
				pane.getChildren().addAll(rect, text);
				hbp4.getChildren().add(pane);
			}
			// System.out.println("");
			indv4.getChildren().add(hbc4);
			indv4.getChildren().add(hbp4);
			indv4.setSpacing(5);
			indv4.setPadding(new Insets(15, 20, 10, 10));
			HBox TT = new HBox();

			for (int l = 0; l < population.pil.get(ind).length; l++) {

				// population.pil.get(ind)[l].Afficher_Pile(population.pil.get(ind)[l] );
				TT.getChildren().addAll(population.pil.get(ind)[l].Afficher_Pile(population.pil.get(ind)[l]));
				TT.setPadding(new Insets(10, 10, 10, 10));
				TT.setSpacing(5);
			}
			Text lb6 = new Text("Le Fitness de la solution final :" + population.t.get(ind));
			lb6.setFont(Font.font("Arial Rounded MT Bold", 15));
			lb6.setFill(Color.DIMGREY);
			VBox T2 = new VBox();
			T2.getChildren().addAll(lb5, indv4, lb6);
			T2.setPadding(new Insets(10, 10, 10, 10));
			T2.setSpacing(5);
			psf.getChildren().addAll(T2, TT);
			psf.setPadding(new Insets(10, 10, 10, 10));
			psf.setSpacing(5);
			psf.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
			vboxsolop.setSpacing(10);
			vboxsolop.getChildren().addAll(zonesf, Z7, psf);
			vboxsolop.setPadding(new Insets(15, 20, 10, 10));
		}
		long tempsFin = System.nanoTime();
		double seconds = (tempsFin - start) / 1e9;
		tempexe.appendText("           " + (double) Math.round(seconds * 100) / 100 + " secondes.");
		btn_lancer.setDisable(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// configuration de spinner
		int n = ConnectionBD.nombindiv();
		SpinnerValueFactory<Integer> gardesValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,30, n);
		this.nmbrINDV.setValueFactory(gardesValueFactory5);
		nmbrINDV.setEditable(true);
	//	btn_réinit.setDisable(true);

	}

	public class Genetique implements Cloneable {
		Individual secondFittest;
		Vector<Individual> Crois = new Vector<Individual>();

		HBox modifierPop(Individual[] H, Conteneur[] t, Vector<MaPile[]> pil, Population population)
				throws ParseException {
			Vector<Conteneur> t1 = new Vector<Conteneur>();
			// VIDER LES PILES AVANT L EMPILEMENT
			for (int k = 0; k < pil.size(); k++) {
				for (int l = 0; l < pil.get(k).length; l++) {// CHAQ PILE
					pil.get(k)[l].taille = 0;
					for (int m = 0; m < pil.get(k)[l].items.size(); m++) {
						pil.get(k)[l].depiler();
						m--;
					}

				}
			}
			VBox F = new VBox();
			int s = 0;
			for (int i = 0; i < H.length; i++) {// les individus
				// System.out.println("l'individu : N°" + i);
				Text lb = new Text("l'individu : N°" + (i + 1));
				lb.setFont(Font.font("Arial Rounded MT Bold", 15));
				lb.setFill(Color.web("#882255"));
				VBox T = new VBox();
				VBox T1 = new VBox();
				VBox T2 = new VBox();
				Text lbb = null;
				s = 0;
				while (s < t.length) {

					for (int k = 0; k < H[i].genes[0].length; k++) {

						for (int l = 0; l < pil.get(i).length; l++) {

							if (H[i].genes[1][k].equals(pil.get(i)[l].numero)) {// CHERCHE PILE
								if ((pil.get(i)[l].taille < pil.get(i)[l].nbrC)
										&& pil.get(i)[l].p.equals(t[s].dimension)) {// VERIFIER LA PLACE

									pil.get(i)[l].empiler(t[s]);
									s++;

								} else {
									if (!(pil.get(i)[l].taille < pil.get(i)[l].nbrC)) {
										
										Text lb1 = new Text("le conteneur à courriger ça position :" + " "
												+ t[s].getNumero() + " type :" + t[s].dimension
												+ "pile plaine  et incompatible type : " + " " + pil.get(i)[l].numero
												+ " type : " + pil.get(i)[l].p);
										lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
										lb1.setFill(Color.RED);
										T1.getChildren().add(lb1);
									} else if (!pil.get(i)[l].p.equals(t[s].dimension)) {// cas type incompatible
									
										Text lb1 = new Text("Conteneur incompatible : " + t[s].getNumero() + " type : "
												+ t[s].dimension + " pile : " + pil.get(i)[l].numero + " type : "
												+ pil.get(i)[l].p);
										lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
										lb1.setFill(Color.RED);
										T1.getChildren().add(lb1);
									} else if (!(pil.get(i)[l].taille < pil.get(i)[l].nbrC)) {
										// cas pile plaine
										/*
										 * System.out.println("le conteneur à courriger ça position :" + " " +
										 * t[s].getNumero() + " " + "pile plaine :" + " " + pil.get(i)[l].numero);
										 */
										Text lb1 = new Text("Conteneur incompatible : " + t[s].getNumero() + " type : "
												+ t[s].dimension + " pile : " + pil.get(i)[l].numero + " type : "
												+ pil.get(i)[l].p);
										lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
										lb1.setFill(Color.RED);
										T1.getChildren().add(lb1);
									}

									t1.add(t[s]);
									s++;
								}
							}
						}
					}
				}
				// empiler les conteneurs mal placé
				int x = 0;
				// afficher si y a pas de correction

				if (t1.size() == 0) {
					// System.out.println(" Y'a pas de correction");
					lbb = new Text("\n \t Y'a pas de correction les conteneur sont compatibles et bien placés ");
				} else {
					lbb = new Text("La correction :");
				}
				;
				// La correction
				while (!t1.isEmpty()) {

					for (int l = 0; l < pil.get(i).length; l++) {// NBR DE PILE DS CHAQUE INDIV

						if (!t1.isEmpty()) {

							if (pil.get(i)[l].p.equals(t1.get(x).dimension)) {

								if ((pil.get(i)[l].taille < pil.get(i)[l].nbrC)) {
									/*
									 * System.out.println("la pile dans on va empiler le conteneur " +
									 * t1.get(x).getNumero() + " est : " + pil.get(i)[l].numero);
									 */
									Text lb2 = new Text("la pile dans on va empiler le conteneur "
											+ t1.get(x).getNumero() + " est : " + pil.get(i)[l].numero);
									lb2.setFont(Font.font("Arial Rounded MT Bold", 15));
									lb2.setFill(Color.GREEN);
									T2.getChildren().addAll(lb2);

									pil.get(i)[l].empiler(t1.remove(x));
								}

							}
						}
					}

				}
				lbb.setFont(Font.font("Arial Rounded MT Bold", 15));
				lbb.setFill(Color.web("#882255"));
				T.getChildren().addAll(lb, T1, lbb, T2);
				T.setPadding(new Insets(10, 10, 10, 10));
				T.setSpacing(5);

				T.setBackground(
						new Background(new BackgroundFill(Color.web("#bbbbbb"), CornerRadii.EMPTY, Insets.EMPTY)));
				F.getChildren().add(T);
				F.setPadding(new Insets(10, 10, 10, 10));
				F.setSpacing(5);

			}
			// COURRIGER L'INDIVIDU SELON LA CORRECTION
			for (int i = 0; i < population.individuals.length; i++) { // les individus

				this.ModINdividu(population.individuals[i].genes, t, population.pil.get(i));
			}

			HBox hbox = new HBox();
			hbox.getChildren().add(F);
			hbox.setPadding(new Insets(10, 10, 10, 10));
			hbox.setSpacing(5);
			return hbox;
		}

		void ModINdividu(String[][] genes, Conteneur[] t, MaPile[] pile) {

			int l = 0;
			while (l < genes[0].length) {
				for (int m = 0; m < t.length; m++) {// LA LISTE DES CONTENEURS

					for (int n = 0; n < pile.length; n++) {// PARCOURS DES PILES
						for (int o = 0; o < pile[n].taille; o++) {// TAILLE DE LA PILE

							if (pile[n].items.get(o).getNumero().equals(t[m].getNumero())) {

								genes[0][l] = t[m].getNumero();
								genes[1][l] = pile[n].numero;
								l++;
							}
						}
					}
				}
			}

		}

		// Selection
		void selection(Vector<Individual> m, Population pop) {// m c est les individus selectionnées
			Population p = new Population();
			p = pop;
			int ind = 0;
			Vector<Individual> m1;
			m1 = p.get_List_Fittest(ind, pop.individuals.length / 2);
			for (int i = 0; i < m1.size(); i++) {

				m.add(m1.get(i));
			}

		}

		// Crossover
			public VBox crossover(Vector<Individual> H, Vector<Individual> tab4) throws CloneNotSupportedException {
			int x1, x2;
			Vector<Individual> w = new Vector<Individual>();
			int crossOverPoint = 0;
			Random rn = new Random();
			int z = H.size() / 2;
			VBox V = null;
			VBox V1 = new VBox();
			VBox pcr = new VBox();
			VBox TOT = new VBox();
			// select rndom for individual
			// select rndom for individual
			int i = 0;
			while (i < z) { // select n/2 fois pour croiser
                Individual I1 = new Individual();
				Individual I2 = new Individual();
				Individual x = new Individual();
				Individual y = new Individual();
				while (crossOverPoint == 0) {// choisir le point de croisement pour chaque deux prents
					crossOverPoint = rn.nextInt(H.get(0).genes[0].length - 1);
				} // choisir les 2 parents
				x1 = rn.nextInt(H.size());
				x2 = rn.nextInt(H.size());

				while (x1 == x2) {// pour ne pas croiser entre 2 mm ind
					x2 = rn.nextInt(H.size());
				}
				I1 = (Individual) H.get(x1).clone();
				I2 = (Individual) H.get(x2).clone();
				x=(Individual) H.get(x1).clone();
                y=(Individual)H.get(x2).clone();
				// coisement
                for (int j = 0; j < crossOverPoint; j++) {//JE CROISE QUE LES PILE LA LISTE DES CONTENEURS EST LA MM
    	            String temp = I1.genes[1][j];
    	            I1.genes[1][j]= I2.genes[1][j];
    	            I2.genes[1][j] = temp;

    	        }
                   
                  int m=0;  int m1=0;
                   
                  m=this.EgalInd((Individual) I1.clone(), w);
                  m1=this.EgalInd((Individual) I2.clone(), w);
				
				Text lb = null, lb1 = null, lb2 = null;
				if (m == 0 && m1 == 0) {// si iindividu apparait plus d une fois
					V = new VBox();
					VBox indv = new VBox(2);
					HBox hbp = new HBox(2);
					VBox indv1 = new VBox(2);
					HBox hbp1 = new HBox(2);
					VBox indv2 = new VBox(2);
					HBox hbp2 = new HBox(2);
					VBox indv3 = new VBox(2);
					HBox hbp3 = new HBox(2);
					// System.out.println("le point de croisement :" + crossOverPoint);
					lb = new Text("Le Point du Croisement :" + (crossOverPoint + 1));
					lb.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb.setFill(Color.YELLOW);
					// System.out.println("les individus à croiser : ");
					lb1 = new Text("Les individus à croiser :" + (i + 1));
					lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb1.setFill(Color.DIMGREY);

					for (int k = 0; k < ((Individual) x.clone()).genes[1].length; k++) {
						// System.out.print(((Individual)x.clone()).genes[1][k]+"|");
						if (k == crossOverPoint) {
							StackPane pane = new StackPane();
							Label text = new Label(((Individual) x.clone()).genes[1][k]);
							text.setTextFill(Color.YELLOW);
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.GRAY);
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp.getChildren().add(pane);
						} else {
							StackPane pane = new StackPane();
							Label text = new Label(((Individual) x.clone()).genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.GRAY);
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp.getChildren().add(pane);
						}
					}
					indv.getChildren().add(hbp);
					indv.setSpacing(5);
					indv.setPadding(new Insets(10, 10, 10, 10));
					// System.out.println("");
					for (int k = 0; k < ((Individual) y.clone()).genes[1].length; k++) {
						if (k == crossOverPoint) {
							StackPane pane = new StackPane();
							Label text = new Label(((Individual) y.clone()).genes[1][k]);
							text.setTextFill(Color.YELLOW);
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#BBBBBB"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp1.getChildren().add(pane);
						} else {
							StackPane pane = new StackPane();
							Label text = new Label(((Individual) y.clone()).genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#BBBBBB"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp1.getChildren().add(pane);
						}

					}
					indv1.getChildren().add(hbp1);
					indv1.setSpacing(5);
					indv1.setPadding(new Insets(10, 10, 10, 10));
					lb2 = new Text("Les individus aprés croisement: ");
					lb2.setFont(Font.font("Arial Rounded MT Bold", 15));
					lb2.setFill(Color.DIMGREY);
					for (int k = 0; k < I1.genes[1].length; k++) {
						if (k < crossOverPoint) {
							StackPane pane = new StackPane();
							Label text = new Label(I1.genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#BBBBBB"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp2.getChildren().add(pane);
						} else {
							StackPane pane = new StackPane();
							Label text = new Label(I1.genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.GRAY);
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp2.getChildren().add(pane);
						}
					}
					indv2.getChildren().add(hbp2);
					indv2.setSpacing(5);
					indv2.setPadding(new Insets(10, 10, 10, 10));
					w.add((Individual) I1.clone());
					w.add((Individual) I2.clone());
					i++;
					for (int k = 0; k < I2.genes[1].length; k++) {
						if (k < (crossOverPoint)) {
							StackPane pane = new StackPane();
							Label text = new Label(I2.genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.GRAY);
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp3.getChildren().add(pane);
						} else {
							StackPane pane = new StackPane();
							Label text = new Label(I2.genes[1][k]);
							text.setTextFill(Color.web("#882255"));
							Rectangle rect = new Rectangle(30, 20);
							rect.setFill(Color.web("#BBBBBB"));
							// add to stackpane
							pane.getChildren().addAll(rect, text);
							hbp3.getChildren().add(pane);

						}
					}
					indv3.getChildren().add(hbp3);
					indv3.setSpacing(5);
					indv3.setPadding(new Insets(10, 10, 10, 10));

					V.getChildren().addAll(lb, lb1, indv, indv1, lb2, indv2, indv3);
					V.setSpacing(5);
					V1.getChildren().add(V);
					V1.setSpacing(5);
					V1.setPadding(new Insets(10, 10, 10, 10));
				}
			}
			for (int j = 0; j < w.size(); j++) {
				tab4.add((Individual) w.get(j).clone());
			} 
			Text lbc = new Text("Resultat Du Croisement");
			lbc.setFont(Font.font("Arial Rounded MT Bold", 20));
			lbc.setFill(Color.DIMGREY);
			for (int s = 0; s < tab4.size(); s++) {
				VBox Tcr = new VBox();
				VBox indv = new VBox(2);
				HBox hbp = new HBox(2);
				Text lbc1 = new Text("l'individu N° " + (s + 1));
				lbc1.setFont(Font.font("Arial Rounded MT Bold", 15));
				lbc1.setFill(Color.DIMGREY);
				for (int k = 0; k <((Individual) tab4.get(s).clone()).genes[1].length; k++) {
					// System.out.print(tab4.get(s).genes[1][k] + "|");
					StackPane pane = new StackPane();
					Label text = new Label(((Individual) tab4.get(s).clone()).genes[1][k]);
					text.setTextFill(Color.web("#882255"));
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.web("#BBBBBB"));
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp.getChildren().add(pane);
				}
				indv.getChildren().add(hbp);
				indv.setSpacing(5);
				indv.setPadding(new Insets(10, 10, 10, 10));
				Tcr.getChildren().addAll(lbc1, indv);
				Tcr.setSpacing(5);
				Tcr.setPadding(new Insets(10, 10, 10, 10));
				pcr.getChildren().addAll(Tcr);
				pcr.setPadding(new Insets(10, 10, 10, 10));
				pcr.setBackground(
						new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			}
			TOT.getChildren().addAll(V1, lbc, pcr);
			TOT.setPadding(new Insets(10, 10, 10, 10));
			return TOT;
			}

		        
		     Integer EgalInd(Individual I ,  Vector<Individual> w) throws CloneNotSupportedException{
		           int   m=0;
		            Boolean b1=false;
		      
		         for (int i = 0; i < w.size(); i++) {//LES INDIVIDUS
		                    
		                     b1=this.FindInd(I, (Individual) w.get(i).clone());
		                  if(b1==true)m++;
		           
		         }
		                return m;
		        }

		        Boolean FindInd(Individual I,Individual I2){
		         Boolean b=true;
		        for (int l = 0; l <2 ; l++) {
		                      for (int k = 0; k <I.genes[l].length ; k++) {
		                   
		                          if(!I.genes[l][k].equals(I2.genes[l][k])){
		                   
		                      b=false;//break;
		                      }
		                        
		                    } }
		        return b;
		        }
		        
		// Mutation
		VBox mutation(Individual x, Individual result, Population pop) throws CloneNotSupportedException {

			String s;
			Text lb, lb2, lb1;
			VBox mut = new VBox();

			Random rn = new Random();
			int mutationPoint, mutationPoint2 = 0;
			// Select a random mutation point
			mutationPoint = rn.nextInt(x.genes[0].length);
			mutationPoint2 = rn.nextInt(x.genes[0].length);
			lb = new Text("Le Fittest avant la mutation:");
			lb.setFont(Font.font("Arial Rounded MT Bold", 15));
			lb.setFill(Color.DIMGREY);
			VBox indv = new VBox(2);
			HBox hbc = new HBox(2);
			HBox hbp = new HBox(2);
			for (int k1 = 0; k1 < x.genes[0].length; k1++) {
				// System.out.print(x.genes[0][k1] + "|");
				StackPane pane = new StackPane();
				Label text = new Label(x.genes[0][k1]);
				text.setTextFill(Color.WHITE);
				Rectangle rect = new Rectangle(30, 20);
				rect.setFill(Color.web("#882255"));
				// add to stackpane
				pane.getChildren().addAll(rect, text);
				hbc.getChildren().add(pane);

			}
			// System.out.println("");
			for (int k1 = 0; k1 < x.genes[1].length; k1++) {
				if ((k1 == mutationPoint)) {
					StackPane pane = new StackPane();
					Label text = new Label(x.genes[1][k1]);
					text.setTextFill(Color.YELLOW);
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.DIMGRAY);
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp.getChildren().add(pane);
				} else if (k1 == mutationPoint2) {
					StackPane pane = new StackPane();
					Label text = new Label(x.genes[1][k1]);
					text.setTextFill(Color.YELLOW);
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.CRIMSON);
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp.getChildren().add(pane);
				}else {
					StackPane pane = new StackPane();
					Label text = new Label(x.genes[1][k1]);
					text.setTextFill(Color.web("#882255"));
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.web("#bbbbbb"));
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp.getChildren().add(pane);

				}
			}
			// Flip values at the mutation point
			while (mutationPoint == mutationPoint2)
				mutationPoint2 = rn.nextInt(x.genes[0].length);
			s = x.genes[1][mutationPoint];
			x.genes[1][mutationPoint] = x.genes[1][mutationPoint2];
			x.genes[1][mutationPoint2] = s;
			// System.out.println("Les deux points de mutation sont :" + mutationPoint + "
			// Et " + mutationPoint2);
			lb2 = new Text("Les deux points de mutation sont : " + (mutationPoint + 1) + " et " + (mutationPoint2 + 1));
			lb2.setFont(Font.font("Arial Rounded MT Bold", 15));
			lb2.setFill(Color.YELLOW);
			x = (Individual) x.clone();

			// System.out.println("le fittest apres mutation:");
			lb1 = new Text("Le Fittest aprés la mutation:");
			lb1.setFont(Font.font("Arial Rounded MT Bold", 15));
			lb1.setFill(Color.DIMGREY);
			VBox indv1 = new VBox(2);
			HBox hbc1 = new HBox(2);
			HBox hbp1 = new HBox(2);
			for (int k1 = 0; k1 < x.genes[0].length; k1++) {
				// System.out.print(x.genes[0][k1] + "|");
				StackPane pane = new StackPane();
				Label text = new Label(x.genes[0][k1]);
				text.setTextFill(Color.WHITE);
				Rectangle rect = new Rectangle(30, 20);
				rect.setFill(Color.web("#882255"));
				// add to stackpane
				pane.getChildren().addAll(rect, text);
				hbc1.getChildren().add(pane);

			}
			// System.out.println("");
			for (int k1 = 0; k1 < x.genes[1].length; k1++) {
				if ((k1 == mutationPoint)) {
					StackPane pane = new StackPane();
					Label text = new Label(x.genes[1][k1]);
					text.setTextFill(Color.YELLOW);
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.CRIMSON);
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp1.getChildren().add(pane);
				} else if (k1 == mutationPoint2) {
					StackPane pane = new StackPane();
					Label text = new Label(x.genes[1][k1]);
					text.setTextFill(Color.YELLOW);
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.DIMGRAY);
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp1.getChildren().add(pane);
				}else {
					StackPane pane = new StackPane();
					Label text = new Label(x.genes[1][k1]);
					text.setTextFill(Color.web("#882255"));
					Rectangle rect = new Rectangle(30, 20);
					rect.setFill(Color.web("#bbbbbb"));
					// add to stackpane
					pane.getChildren().addAll(rect, text);
					hbp1.getChildren().add(pane);

				}
			}

			indv.getChildren().add(hbc);
			indv.getChildren().add(hbp);
			indv.setSpacing(5);
			indv.setPadding(new Insets(10, 10, 10, 10));
			indv1.getChildren().add(hbc1);
			indv1.getChildren().add(hbp1);
			indv1.setSpacing(5);
			indv1.setPadding(new Insets(10, 10, 10, 10));
			
			// System.out.println("");
			// System.out.println();
			 mut.getChildren().addAll(lb, indv, lb2, lb1, indv1);
             mut.setPadding(new Insets(10, 10, 10, 10));
             mut.setSpacing(10);
			return mut;

		}

		public void ModifierSelect(Individual[] H, Conteneur[] t, Vector<MaPile[]> pil, Population population)
				throws ParseException {

			{
				Vector<Conteneur> t1 = new Vector<Conteneur>();
				// VIDER LES PILES AVANT L EMPILEMENT
				for (int k = 0; k < pil.size(); k++) {
					for (int l = 0; l < pil.get(k).length; l++) {// CHAQ PILE
						pil.get(k)[l].taille = 0;
						for (int m = 0; m < pil.get(k)[l].items.size(); m++) {
							pil.get(k)[l].depiler();
							m--;
						}
					}
				}

				int s = 0;
				for (int i = 0; i < H.length; i++) {// les individus

					s = 0;
					while (s < t.length) {

						for (int k = 0; k < H[i].genes[0].length; k++) {

							for (int l = 0; l < pil.get(i).length; l++) {

								if (H[i].genes[1][k].equals(pil.get(i)[l].numero)) {// CHERCHE PILE
									if ((pil.get(i)[l].taille < pil.get(i)[l].nbrC)
											&& pil.get(i)[l].p.equals(t[s].dimension)) {// VERIFIER LA PLACE

										pil.get(i)[l].empiler(t[s]);
										s++;

									} else {

										t1.add(t[s]);
										s++;
									}
								}
							}
						}
					}
					// empiler les conteneurs mal placer
					int x = 0;
					// La correction
					while (!t1.isEmpty()) {

						for (int l = 0; l < pil.get(i).length; l++) {// NBR DE PILE DS CHAQUE INDIV

							if (!t1.isEmpty()) {

								if (pil.get(i)[l].p.equals(t1.get(x).dimension)) {

									if ((pil.get(i)[l].taille < pil.get(i)[l].nbrC)) {
										pil.get(i)[l].empiler(t1.remove(x));

									}

								}
							}
						}

					}

				}

				// COURRIGER L'INDIVIDU SELON LA CORRECTION
				for (int i = 0; i < population.individuals.length; i++) { // les individus

					this.ModINdividu(population.individuals[i].genes, t, population.pil.get(i));
				}
			}
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			Individual individual = new Individual();
			individual.genes = new String[2][];
			for (int i = 0; i < individual.genes.length; i++) {
				individual.genes[i] = this.secondFittest.genes[i];
			}
			return individual;
		}
	}
}