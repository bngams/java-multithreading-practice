package exo2;

public class CounterOrder implements Runnable {
	
	private String name;
	private int max;
	private static int order;
	
	public CounterOrder(String name, int max) {
	    this.name = name;
	    this.max = max;
	}
	
	public CounterOrder(String name) {
	    this(name, 10);
	}

	@Override
	public void run() {
		for (int i = 1; i <= max; i++) {
			try {
				// random sleep to simulate random treatment
				Thread.sleep((int)(Math.random() * 5000));
		    } 
		    catch(InterruptedException e) {
		    	System.err.println(name + " a ete interrompu.");
		    }
		    System.out.println(name + " : " + i);
		}
		System.out.println("*** " + name + " a fini de compter jusqu'à " + max + " en position " + order++);
	}

	
}
