package exo3;

public class Account {

	private int balance = 0;
	
	public synchronized void credit(int amount) {
	    balance += amount;
	    System.out.println(" ajoute " + amount);
	}
	
	public synchronized void debit(int amount) {
	    balance -= amount;
	    System.out.println(" retire " + amount);
	}
	
	public synchronized void blankOperation(int amount) {
	    balance += amount;
	    System.out.println(" ajoute " + amount);
	    balance -= amount;
	    System.out.println(" retire " + amount);
	}
	
	public synchronized int getBalance() {
	    return balance;
	}
}
