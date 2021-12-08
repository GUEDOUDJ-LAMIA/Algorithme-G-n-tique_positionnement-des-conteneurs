package Interface4;
import java.text.ParseException;


import java.util.Vector;

import BDD.ConnectionBD;
public class Population {
	
	int nbrINd=ConnectionBD.nombindiv();
	int numZone=0;
	 int popSize = nbrINd;
	    Individual[] individuals ;
	    int fittest = 0;
	      Vector<MaPile [] > pil;
              Vector< Integer > t;
              public Population() {
      			// TODO Auto-generated constructor stub
                              
      		}
      	    void SetIndividual( Vector<Individual> v, Vector<MaPile [] > pill){
                      individuals = new Individual[v.size()];
                      
                      for (int i = 0; i < individuals.length; i++) {
                          individuals[i]=v.get(i);
                          individuals[i].selectionnee=false;
                      }
                      
                      int x=pill.size()-v.size();
                    
                      for (int i = 0; i < x; i++) {
                         pill.remove(i); 
                       
                      }
                      
                      
                  }
                  
                  int getINdex_Indiv(Individual x){int n=0;int m=0;
                  Boolean b=true;
                      for (int i = 0; i < individuals.length; i++) {
                           m=0;
                           b=true;
                     for (int l = 0; l <2 ; l++) {
                            for (int k = 0; k <individuals[i].genes[l].length ; k++) {
                         
                                if(!individuals[i].genes[l][k].equals(x.genes[l][k])){
                            m++;
                            b=false;
                            }
                              
                          } }
                  
                         if(b==true)n=i;  
                  
                          // if(m==x.genes[0].length*2)n=i;  
                      }//System.err.println(n);
                      return n;
                  }
                  
      	    //Initialize population
          public void initializePopulation(Conteneur [] t, int nbrC,int nbrpile) throws ParseException {
      	   pil= new Vector<MaPile [] >();
                 individuals = new Individual[nbrINd];
      	   
          	for (int i = 0; i < individuals.length; i++) {
                      
          	  //je remplie l individu
                      Individual idividualTmp = new Individual(t,nbrC);
                      idividualTmp.numero=i;
                      idividualTmp.init(t, nbrC,nbrpile);
                      individuals[i] = idividualTmp;
                
                     // System.out.println("maxFit2"+individuals[i]);
                  MaPile []  pile = idividualTmp.getPile();
                  
                      //je remplie les pile et je les sauvegarde   
                           pil.add(i, pile);
                         
                  }
          	

      	    }
                  
                  
                  
      	  	    //Get the fittest individual
      	    public Individual getFittest(int ind) {
      	        int maxFit = Integer.MAX_VALUE;
      	        int maxFitIndex = 0;
                      //le tableau de fitness et les individus ont le meme index
      	        for (int i = 0; i < t.size(); i++) {//parcourir les fitness des individus
                          
      	            if (maxFit > t.get(i)) {
      	                maxFit = t.get(i); //on cherche l'individu qui a le plus grand fitness
      	                maxFitIndex = i;
                              
      	            }
      	            
      	        }
                      
                      
                      ind=maxFitIndex;
                     
                    //  System.out.println(t.size());
      	        fittest = t.get(maxFitIndex); //fitnes de fittest 
      	       
      	        try {
                 
      	            return (Individual) individuals[maxFitIndex].clone();//return un individu il clone l indiv avec maxFit
      	        } catch (CloneNotSupportedException e) {
      	            e.printStackTrace();
      	        }
      	        return null;
      	    }
           //Calculate fitness of each individual
      	    public void calculateFitness(Vector<MaPile [] > v,int nbrc) throws ParseException {
              Individual[] indiv=new Individual[nbrINd] ;
              t= new Vector<Integer >();
                Individual idividualTmp;
      	        for (int i = 0; i < individuals.length; i++) {
                          // idividualTmp = new Individual();
      	                idividualTmp= individuals[i];
                             idividualTmp.calcFitness(v,nbrc,i);
                             indiv[i]=idividualTmp;
                          indiv[i].fitness=idividualTmp.getFitness();
                          t.add(indiv[i].fitness);
                      
      	        }
      	     
                     
      	    }
                    
                    //Get the fittest individual
      	    public Vector<Individual>  get_List_Fittest(int ind ,int nbrInd) {
                      
                      
                      
      	        int maxFit = Integer.MAX_VALUE;
      	        int maxFitIndex = 0;
                      Vector<Individual> tab = new  Vector<Individual>();
                       
                      for (int j = 0; j < nbrInd; j++) {
                         maxFitIndex = 0;
                         maxFit = Integer.MAX_VALUE;
                         
      	        for (int i = 0; i < t.size(); i++) {//parcourir les fitness des individus
                     
                          if(individuals[i].selectionnee==false){
                           if ((maxFit > t.get(i))  ){
                             maxFit = t.get(i); //on cherche l'individu qui a le plus grand fitness
      	               maxFitIndex = i;
                          }
                          }
      	     
                      }
                        
                        individuals[maxFitIndex].selectionnee=true;
                      tab.add(individuals[maxFitIndex]);//  n/2 individus
                         
                             
                      }
                   
                      
                      return tab;
                  }
      }
