package exo2;

import exo2.CounterRank;

public class Exo2 {

	public static void main(String[] args) {
		CounterRank[] compteurs = {
	      new CounterRank("C1", 14),
	      new CounterRank("C2"),
	      new CounterRank("C3")
	    };
	    for (int i = 0; i < compteurs.length; i++) {
	      new Thread(compteurs[i]).start();
	    }
	}
}
