package Interface4;
import java.sql.PreparedStatement
;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import BDD.ConnectionBD;
public class Individual implements Cloneable {
	
	static int numero=0;
	boolean selectionnee=false;
	static   int fitness = 0;
	String[][] genes ;
	int geneLength ;
	
	Vector<MaPile [] > pil = new Vector<MaPile []>();

    public Individual() {
    }
	
	ArrayList<Integer> rand( Set <Integer> tab,int nc,int np) {
		Random rn = new Random();
		ArrayList<Integer> s = new ArrayList<Integer>(); 
		int x;
		for (int i = 0; i < nc; i++) {
			x=rn.nextInt(np);
			for (int j = 0; j < tab.size(); j++) {
				while(tab.contains(x)) {
					x=rn.nextInt(np);
				}//tab des val interdites
			}
		s.add(x);
		}
            return s;
	}
    
    private MaPile []  pile;
    public MaPile[] getPile() {
		return pile;
	}
     private Individual  Individu;
 public Individual getIndivudual() {
		return Individu;
	}
 public int getFitness() {
		return fitness;
	}
 public Individual(Conteneur [] t,int nbrC) throws ParseException {
     geneLength=t.length;
        numero++;
    }

 public void init(Conteneur [] t,int nbrC,int nbrpile) throws ParseException{
		pile =new MaPile[24];
		try {
			java.sql.Connection con = ConnectionBD.Connect();
			String sql = "select Typep from pile";
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int i = 0;
			while (rs.next()) {

				pile[i] = (new MaPile(Type.valueOf(rs.getString(1)), nbrC));
				i++;
			}

			con.close();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
 	//int dim=pile.length*nbrC;//la capacité de toute la zone
 	//sauvegarder les conteneurs
 	Vector<Conteneur> t1=new Vector<Conteneur>();
 	  for (int i = 0; i < t.length; i++) {
			t1.add(t[i]);
			
		}
    	  //initia les piles
    	for (int i = 0; i < pile.length; i++) {
            
			pile[i].cpt=0;
			pile[i].taille=0;
			for (int j = 0; j < pile[i].items.size(); j++) {
                            
				pile[i].depiler();j--;
			
			}
			
		}
        //partager les conteneurs selon leurs dimenssion
	Random rn = new Random();
    genes=new String[2][t.length];
     int np=pile.length;
         //random list
	ArrayList<Integer> s = new ArrayList<Integer>();
	Set <Integer> tab=new HashSet<Integer>();
  
    int x;int k=t.length;//int j=0;
    
 
      for (int i = 0; i < t1.size(); i++) {//tableau de conteneurs à empiler
            
     int   j=0;
    
    //ajouter les num qui sont deffirent de type dans on a besoin ds tab pour ne pas donner ce num en random
     tab.clear();
     
    for (int m = 0; m < pile.length; m++) {
          
            if(!t1.get(i).dimension.equals(pile[m].p)){
      
                tab.add(m );}
        }
 
        s=	this.rand(tab,k, np);
        x=s.get(j);
        
			if(pile[x].taille<pile[x].nbrC)
                        {
				pile[x].empiler(t1.get(i));
			
				
				j++;
				
			}else {
				tab.add(x);t1.add(t1.get(i));//k++;
				
			}
    }
   
   
        //Set genes randomly for each individual
      int l = 0;
            while( l < genes[0].length ) {
                for (int m = 0; m < t.length; m++) {// LA LISTE DES CONTENEURS
                    
                    for (int n = 0; n < pile.length; n++) {//PARCOURS DES PILES
                        for (int o = 0; o < pile[n].taille; o++) {//TAILLE DE LA PILE
                          
                         if (pile[n].items.get(o).getNumero().equals(t[m].getNumero())) {
                           

                                genes[0][l]=t[m].getNumero();
                                genes[1][l]=pile[n].numero;
                 l++;
                            } } 
                    }
                } }
     
      
           
       
//pour chaq indi on init fitness à 0
  Individu=this;
        //fitness = 0;
        
 }
 
 
	// Calculate fitness
	public void calcFitness(Vector<MaPile[]> v, int nbrc, int i) throws ParseException {
		fitness = 0;// fit=0;
		Vector<MaPile[]> v1 = new Vector<MaPile[]>();
		v1 = v;
		for (int j = 0; j < v.get(i).length; j++) {// piles

			for (int k = 0; k < v1.get(i)[j].items.size() - 1; k++) {// hauteur

				if (v1.get(i)[j].items.get(k).d_depart.avant_Date(v1.get(i)[j].items.get(k + 1).d_depart)) {
					// System.out.println(v1.get(i)[j].items.get(k).numero+v1.get(i)[j].items.get(k+1).numero);
					++fitness;

				}
			}
		}

	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		Individual individual = (Individual) super.clone();
		individual.genes = new String[2][geneLength];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < individual.genes[i].length; j++) {
				individual.genes[i][j] = this.genes[i][j];
			}
		}

		return individual;
	} 

}
