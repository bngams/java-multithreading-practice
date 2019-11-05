package exo1;

public class Exo1 {

	public static void main(String[] args) {
		Counter[] compteurs = {
	      new Counter("C1"),
	      new Counter("C2"),
	      new Counter("C3")
	    };
	    for (int i = 0; i < compteurs.length; i++) {
	      new Thread(compteurs[i]).start();
	    }
	}

}
