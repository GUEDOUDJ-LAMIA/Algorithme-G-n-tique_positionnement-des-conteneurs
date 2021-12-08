package PFE;
public enum Type {	
		type_10pieds(10), type_20pieds(20), type_40pieds(40);

			
			
			
			int dim;//cette enum utilisé pour conteneur et pile
		    private Type() {
				// TODO Auto-generated constructor stub
			}
		    	
			Type(int dim) {
				this.dim = dim;
			}

			Type returndim(int N) {
				for (Type s : Type.values()) {
					if (s.dim == N)
						return (s);
				}
				return null;

			}
			
	
	
}
