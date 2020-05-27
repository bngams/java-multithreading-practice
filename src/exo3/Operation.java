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
		int balance;  
		int randomAmount = (int) (Math.random() * 10000);
	    // create syncrhonized 
	    synchronized(account) {
		 System.out.println(name);
	     account.credit(randomAmount);
	     account.debit(randomAmount);
	     balance = account.getBalance();
	    }
//	    synchronized(account) {
//	        System.out.println(name);
//		    account.blankOperation(randomAmount);
//		    balance = account.getBalance();
//	    }
		if (balance != 0) {
			System.out.println(name + ":**solde=" + balance);
			System.exit(1);
		}
	}
	}
	
	public String getName() {
		return name;
	}
	
	public static void main(String[] args) {
	    Account acc = new Account();
	    for (int i = 0; i < 20; i++) {
	      Operation operation = new Operation("" + (char)('A' + i), acc);
	      new Thread(operation).start();
	    }
	  }
}
