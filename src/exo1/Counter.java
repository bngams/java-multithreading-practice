package exo1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter implements Runnable {
	
	private static AtomicInteger rank = new AtomicInteger(0);
	
	private String name;
	private int max;
	
	public Counter(String name, int max) {
	    this.name = name;
	    this.max = max;
	}
	
	public Counter(String name) {
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
		System.out.println("*** " + name + " a fini de compter jusqu'à " + max);
		System.out.println("*** rank is " + rank.incrementAndGet() );
		
	}

	
}
