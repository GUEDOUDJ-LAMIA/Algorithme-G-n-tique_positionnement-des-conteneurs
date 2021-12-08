
package PFE;
import java.text.ParseException;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Conteneur {
	// *************************ATTRIBUTS**************************//

	private final SimpleStringProperty  num;
	 Type dimension;
     PRODUIT p;
     DATE d_depart;
     DATE d_arrive;
     String nembre;
 	static int cpt=0;
 	boolean traite=false;
	// *********************** CONSTRUCERS ************************//

	// *********************** CONSTRUCERS SANS PARAMETRES ************************//
	public Conteneur() throws ParseException {
		this.num = new SimpleStringProperty();
		
	}

	// *********************** CONSTRUCERS  AVEC 4 parama ************************//

	public Conteneur(Type Type , DATE d_arrive, DATE d_depart,PRODUIT p) throws ParseException {
		 this.num = new SimpleStringProperty();
		cpt++;
		  this.nembre = "C".concat(Integer.toString(cpt));;
		  this.p = p;
		this.dimension=Type;
		if (d_arrive .avant_Date(d_depart) == true){
		this.d_arrive = d_arrive;
		this.d_depart = d_depart;} 
		else { this.d_arrive = d_depart; 
		this.d_depart = d_arrive; }
		
	}
	// *********************** CONSTRUCERS avec 5 param ************************//
	public Conteneur(String num1,PRODUIT p,Type Type , DATE d_arrive, DATE d_depart) throws ParseException {
		 
		this.num = new SimpleStringProperty(num1);
		this.p = p;
		this.dimension=Type;
		this.d_arrive = d_arrive;
		this.d_depart = d_depart;
		
	}
	
/* int incrt() {
	numero ++;
	return numero;
}*/



    public String getNumero() {
		return num.get();
	}
	public void setNumero(String numero) {
		num.set(numero);
	}
	public  Type getDimension() {
		return dimension;
	}
	public void setDimension(Type dimension) {
		this.dimension = dimension;
	}
	public String getD_depart() throws ParseException {
		return d_depart.getDate();
	}
	public void setD_depart(DATE d_depart) {
		this.d_depart = d_depart;
	}
	public  String getD_arrive() throws ParseException {
		return d_arrive.getDate();
	}
	public void setD_arrive(DATE d_arrive) {
		this.d_arrive = d_arrive;
	}
	public  PRODUIT getP() {
		return p;
	}
	public  String getProduit() {
		return p.toString();
	}
	public void setP(PRODUIT p) {
		this.p = p;
	}

//************************** METHODES **************************//
	StackPane Affiche() throws ParseException {
		StackPane pane = null;
		Label text;
		Rectangle rect;
//	System.out.println("|"+ getNumero() + "|" );
		switch (this.getDimension()) {
		case type_10pieds:
			pane = new StackPane();
			text = new Label(getNumero());
			text.setTextFill(Color.web("#882255"));
			rect = new Rectangle(30, 20);
			rect.setFill(Color.web("#6699CC"));
			// add to stackpane
			pane.getChildren().addAll(rect, text);
			break;
		case type_20pieds:
			pane = new StackPane();
			text = new Label(getNumero());
			text.setTextFill(Color.web("#882255"));
			rect = new Rectangle(30, 20);
			rect.setFill(Color.web("#EE6677"));
			// add to stackpane
			pane.getChildren().addAll(rect, text);
			break;
		case type_40pieds:
			pane = new StackPane();
			text = new Label(getNumero());
			text.setTextFill(Color.web("#882255"));
			rect = new Rectangle(30, 20);
			rect.setFill(Color.web("#CCBB44"));
			// add to stackpane
			pane.getChildren().addAll(rect, text);
			break;
		}

		return pane;

	}

  
	  public int compare(Conteneur s1,Conteneur s2) throws ParseException
      {
		return s2.d_depart.compareTo(s1.d_depart);
         
      }

	@Override
	public String toString() {
		try {
			return "Conteneur [num=" + getNumero() + ", dimension=" + getDimension() + ", p=" + getP()+ ", d_depart=" + getD_depart()
					+ ", d_arrive=" + getD_arrive() + "]";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	
	
	
}
