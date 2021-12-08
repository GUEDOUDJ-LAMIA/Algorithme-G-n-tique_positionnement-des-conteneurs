package PFE;
import java.text.ParseException;
import java.util.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MaPile implements Pile{
final SimpleStringProperty num;
final SimpleIntegerProperty nbrCo;
	String numero;
	static int cpt=0;
	int nbrC;
	int taille=0;
  public Vector<Conteneur> items;
  Type p;
  public MaPile(int nbrC1){
	  this.nbrCo = new SimpleIntegerProperty();
	this.num = new SimpleStringProperty();
	nbrC=nbrC1;
	  cpt++;
	  this.numero = "P".concat(Integer.toString(cpt));
	  items = new Vector<Conteneur>(nbrC);
	  }
  public MaPile( Type p, int nbrc){
	
	   this.nbrCo = new SimpleIntegerProperty();
	this.num = new SimpleStringProperty();
	nbrC=nbrc;
	  cpt++;
	  this.numero = "P".concat(Integer.toString(cpt));;
	  items = new Vector<Conteneur>(nbrC);
	  this.p=p;
	  }
  public MaPile( String n,Type p, int nbrc){
	  this.nbrCo = new SimpleIntegerProperty(nbrc);
	this.num = new SimpleStringProperty(n);
	  items = new Vector<Conteneur>(nbrc);
	  this.p=p;
	  }
	  
  public Object empiler(Object item){
	    items.addElement((Conteneur) item);
	    taille++;
	   
	    return item;
	  }
	  public synchronized Object depiler(){
	    int len = items.size();
	    Object item = null;
	    if (len == 0)
	      throw new EmptyStackException();
	    item = items.elementAt(len - 1);
	    items.removeElementAt(len - 1);
	    return item;
	  }
	  
	  public boolean estVide(){
		    return (items.size() == 0);
		  }
	  public boolean pilePlein(){
		    return (items.size() == nbrC);
		  }
		  protected void finalize() throws Throwable {
		    items = null; super.finalize();
		  }

	@Override
	public Object remplir_pile(Object p, Vector<Conteneur>  item) throws ParseException {
	 for(int i=0;i<item.size();i++) {//nbr de conteneurs	
		 if(!pilePlein()) {
		
			 int len = item.size();//taille tableua a conteneurs
			    Object item1 = null;
			    item1 = item.elementAt(i);
			    ((MaPile) p).empiler(item1);
			    if (len == 0) {
				      throw new EmptyStackException();}
			    else { item.removeElementAt(i);i--;}
			   
			    }
		 } 
				  
		return p;

	}

	@Override
	public VBox Afficher_Pile(MaPile p) throws ParseException {
		// TODO Auto-generated method stub
		final Color[] colorspile = new Color[] { Color.RED, Color.YELLOW, Color.ORANGE, Color.DARKGREEN, Color.DEEPPINK,
				Color.VIOLET, Color.SADDLEBROWN, Color.GRAY, Color.BROWN, Color.DARKOLIVEGREEN, Color.GOLDENROD,
				Color.INDIGO, Color.PLUM, Color.MEDIUMSPRINGGREEN, Color.CRIMSON, Color.CORNFLOWERBLUE, Color.OLIVE,
				Color.AQUA, Color.DARKGREEN, Color.DEEPSKYBLUE, Color.DARKORCHID, Color.DARKGREY, Color.MAGENTA,
				Color.BLANCHEDALMOND };
		int i = p.items.size() - 1;
		int j = Integer.parseInt(p.numero.substring(1)) - 1;
		VBox pilef = new VBox(6);
		VBox pile = new VBox();
		StackPane pane = null;
		Label text;
		Rectangle rect, rectdeb = null;
		switch (p.p) {
		case type_10pieds:
			pane = new StackPane();
			text = new Label(numero);
			text.setTextFill(Color.web("#6699CC"));
			rect = new Rectangle(30, 20);
            rect.setFill(colorspile[j]);
			rectdeb = new Rectangle(30, 5);
			rectdeb.setFill(colorspile[j]);
			// add to stackpane
			pane.getChildren().addAll(rect, text);

			while (i >= 0) {
				p.items.get(i);
				pile.getChildren().add(p.items.get(i).Affiche());

				i--;
				;

			}
			break;
		case type_20pieds:
			pane = new StackPane();
			text = new Label(numero);
			text.setTextFill(Color.web("#EE6677"));
			rect = new Rectangle(30, 20);
			 rect.setFill(colorspile[j]);
				rectdeb = new Rectangle(30, 5);
				rectdeb.setFill(colorspile[j]);
			// add to stackpane
			pane.getChildren().addAll(rect, text);

			while (i >= 0) {
				p.items.get(i);
				pile.getChildren().add(p.items.get(i).Affiche());

				i--;

			}
			break;
		case type_40pieds:
			pane = new StackPane();
			text = new Label(numero);
			text.setTextFill(Color.web("#CCBB44"));
			rect = new Rectangle(30, 20);
            rect.setFill(colorspile[j]);
			rectdeb = new Rectangle(30, 5);
			rectdeb.setFill(colorspile[j]);
			// add to stackpane
			pane.getChildren().addAll(rect, text);

			while (i >= 0) {
				p.items.get(i);
				pile.getChildren().add(p.items.get(i).Affiche());

				i--;

			}

			break;
		}

		pilef.getChildren().addAll(rectdeb, pile, pane);
		pilef.setAlignment(Pos.BOTTOM_CENTER);
		return pilef;
	}

	public Object remplir(Object p, Object item) throws ParseException {
		((MaPile) p).empiler((Conteneur) item);
		 return p;}

	@Override
	public Conteneur[] remplir_pile1(Object p, Object[] item, int n) throws ParseException {
		// TODO Auto-generated method stub
			//remplir la pile
				int i=1;  int j=0;
				  ((MaPile) p).empiler((Conteneur)item[0]);
				 Conteneur[] obj= new Conteneur [10];
				while(i<n) {
					Conteneur c =(Conteneur)item[i];
					if ((items.lastElement().d_depart.compareTo(c.d_depart)==0 )||(items.lastElement().d_depart.compareTo(c.d_depart)==1)){
					  ((MaPile) p).empiler((Conteneur)item[i]);} else {
						obj[j]=((Conteneur)item[i]);
						j++;
					  }
						  
					 i++; }
				 
			      return obj  ; 
	} 
	int nombreConteneurs(MaPile p) throws ParseException {
		int i = p.items.size() - 1;
		 int cptc=0;
		while (i >= 0) {
			p.items.get(i);
			p.items.get(i).Affiche();
         cptc++;
         i--;
		}
		return cptc;
}

	int index (Conteneur c) {
		return items.indexOf(c);
	}

public String getNumero() {
	return num.get();
}
public void setNumero(String numero) {
	this.num.set(numero);
}

public String getNbrC() {
	return Integer.toString(nbrCo.get());
}
public int getIntNbrC() {
	return nbrCo.get();
}
public void setNbrC(int nbrC) {
	this.nbrCo.set(nbrC);
}
public Type getP() {
	return p;
}
public String gettype() {
	return p.toString();
}
public void setP(Type p) {
	this.p = p;
}


	}
