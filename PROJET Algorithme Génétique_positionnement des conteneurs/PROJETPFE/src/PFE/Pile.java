package PFE;



import java.text.ParseException;
import java.util.Vector;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public interface Pile {
	 public Object empiler(Object item);
	    public Object depiler();
	    public boolean estVide();
	    public Object  remplir_pile(Object p,Vector<Conteneur>  item) throws ParseException;
		VBox Afficher_Pile(MaPile p) throws ParseException;
		public Conteneur[] remplir_pile1(Object p, Object[] item,int n) throws ParseException;
}
