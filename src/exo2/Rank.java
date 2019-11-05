package exo2;

public class Rank {
	private static int rank = 0;

    // Synchronized Method 
    public static synchronized int increment() {
    	rank = rank + 1;
    	return rank;
    }
}
