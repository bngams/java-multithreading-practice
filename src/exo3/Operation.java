package exo3;

public class Operation implements Runnable {
	private Account account;
	private String name;
	
	public Operation(String name) {
		this.name = name;
	}

	public Operation(String name, Account account) {
		this(name);
		this.account = account;
	}
	
	public void run() {
	while(true) {
	    int randomAmount = (int) (Math.random() * 10000);
	    System.out.println(name);
	    int balance;
	    synchronized(account) {
	     account.credit(randomAmount);
	     account.debit(randomAmount);
	     balance = account.getBalance();
	    }
//	    account.blankOperation(randomAmount);
//	    balance = account.getBalance();
		if (balance != 0) {
			System.out.println(name + ":**solde=" + balance);
			System.exit(1);
		}
	}
	}
	
	public String getName() {
		return name;
	}
}
