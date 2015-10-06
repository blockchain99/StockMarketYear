package chapter1;
// git version updated oct 6 2015
public class StockMarketYear {

	private int startingBalance;
	private int interestRate;
	private int totalWithdrawals;
	private int startingPrincipal;
	 	 
	
	
	public StockMarketYear(int startBalance, int startingPrincipal, int interestRate){
		this.startingBalance = startBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.totalWithdrawals = 0;
	}	
	  
	public int startingBalance() {
		return startingBalance;
	}
	
	public int startingPrincipal() {
	//	return startingBalance - capitalGainsAmount;
		return startingPrincipal;
	}
	
	
	public int interestRate() {
		return interestRate;
	}
	
	public void withdraw(int amount) {
		this.totalWithdrawals += amount;	
	}
	
	// change from totalWithdrawn()
	
	public int capitalGainsWithdrawn() {
		//int result = totalWithdrawals - startingPrincipal();
		int result = -1 * (startingPrincipal() - totalWithdrawals);
		return  zeroOrPositive(result);
	}

	private int zeroOrPositive(int result) {
		return Math.max(0, result);
	}
		
	public int capitalGainsTaxIncurred(int taxRate) {
		double dblTaxRate = taxRate / 100.0;
		double dblCapGains = capitalGainsWithdrawn();
		return (int)((dblCapGains / (1 - dblTaxRate)) - dblCapGains);
	}
	
	/* (1) totalWithdrawnExceptCapitalGains(){~} delete => using this method part error correction.
	// refactor from totalWithdrawnExcpetCapitalGains)()
	// refactor from totalWithdrawn()
	public int totalWithdrawnExceptCapitalGains(){
		return totalWithdrawn;
	}
	*/
	    // refactor  from totalWithdrawnIncludingCapitalGains(int capitalGainsTax)
		// refactor from totalwithdrawnIncluudingCapitalGainsTaxWithdrawn(int capitalGainsTax)
		// refactor totalWithdrawn to totalWithdrawals
	public int totalWithdrawn(int capitalGainsTax) {
		return totalWithdrawals + capitalGainsTaxIncurred(capitalGainsTax);
	}
	
	public int endingingPrincipal() {
		int result = startingPrincipal() - totalWithdrawals;
		return  zeroOrPositive(result);
	}

	
	// change from totalWithdrawnExceptCapitalGains() to totalWithdrawn (2)
	// substitute "- totalWithdrawals - capitalGainsTaxIncurred(capitalGainsTaxRate))" with "totalWithdrawn(capitalGainsTax)
	public int interestEarned(int capitalGainsTaxRate) {
		return (startingBalance - totalWithdrawn(capitalGainsTaxRate)) * interestRate / 100;
	}
	
	// substitute "- totalWithdrawals - capitalGainsTaxIncurred(capitalGainsTaxRate))" with "totalWithdrawn(capitalGainsTax)
	public int endingBalance(int capitalGainsTaxRate) {
		int modifiedStart = startingBalance - totalWithdrawn(capitalGainsTaxRate);
		return modifiedStart + interestEarned(capitalGainsTaxRate);
	}
	
	public StockMarketYear nextYear(int capitalGainsTaxRate) {
	//	return new StockMarketYear(this.endingBalance(capitalGainsTaxRate),0, this.interestRate);
		return new StockMarketYear(this.endingBalance(capitalGainsTaxRate),this.endingingPrincipal(), this.interestRate());
	}
}
