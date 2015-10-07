package chapter1;

import static org.junit.Assert.*;

import org.junit.*;

public class _StockMarketYearTest {
	
	   @Test
	    public void startingValues() {
		   StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	assertEquals("starting balance", 10000, year.startingBalance());
	    	assertEquals("starting principal", 3000, year.startingPrincipal());
	    	assertEquals("interest rate", 10, year.interestRate());
	    	assertEquals("total withdrawn default", 0,year.totalWithdrawn(25));
	    }
	    
	    @Test
	    public void endingPrincipal() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	assertEquals("starting principal", 3000, year.startingPrincipal());
	    	year.withdraw(1000);
	    	assertEquals("ending principal considers withdrawals", 2000, year.endingingPrincipal());
	    	year.withdraw(500);
	    	assertEquals("ending principal considers totals mutiple withdrawals", 1500, year.endingingPrincipal());
    		year.withdraw(3000);
    		assertEquals("ending principal never goes zero", 0, year.endingingPrincipal());
	    }
	    
	    @Test
	    public void interestEarned() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	assertEquals("basic interest earned",1000, year.interestEarned(25));
	    	year.withdraw(2000);
	    	assertEquals("withdreqals don't earn interest", 800, year.interestEarned(25));
	    	
	    	
	    	
	    }
	    
	    @Test
	    public void endingPrincipalNevergoesBelowZero() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	assertEquals("starting principal", 3000, year.startingPrincipal());
	    	year.withdraw(4000);
	    	assertEquals("ending principal after withdraw(4000)", 0, year.endingingPrincipal());
	    	year.withdraw(2000);
	    	assertEquals("capital gains tax",1000, year.capitalGainsTaxIncurred(25));
	    	assertEquals("total withdrawn", 7000, year.totalWithdrawn(25));
	    	assertEquals("interest earned", 300, year.interestEarned(25));
	    }
	    
	    @Test
	    public void capitalGainsTaxesDoNotEarnInterest2() {
	    	StockMarketYear year = new StockMarketYear(10000, 0, 10);
	    	year.withdraw(1000);
	    	year.withdraw(2000);
	    	assertEquals("capital gains withdrawn", 3000, year.capitalGainsWithdrawn());
	    	assertEquals("endiing principal after total withdrawn(3000)", 0, year.endingingPrincipal());
	    	assertEquals("capital gains tax", 1000, year.capitalGainsTaxIncurred(25));
	   // 	assertEquals("total withdrawn",  1333, year.totalWithdrawnIncludingCapitalGains(25));
	    	assertEquals("total withdrawn",  4000, year.totalWithdrawn(25));
	    	assertEquals("interest earned",600, year.interestEarned(25));
	    }
	    
	    @Test
	    public void interestEarnedIsStaringBalanceCombinedWithInterestRate() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	assertEquals(1000, year.interestEarned(25));
	    }
	    
	    @Test
	    public void withdrawnFundsDoNotEarnInterest() {
	    	StockMarketYear year = newAccount();
	    	year.withdraw(1000);
	    	assertEquals(900, year.interestEarned(25));
	    }
	    
	    @Test
	    public void totalWithdrawnIncludingCapitalGains(){
	    	StockMarketYear year = new StockMarketYear(10000, 0, 10);
	    	year.withdraw(1000);
	    	assertEquals("capital gains tax", 333, year.capitalGainsTaxIncurred(25));
	 	    assertEquals("total withdrawn",  1333, year.totalWithdrawn(25));
	    	
	    }
	    
	    
	    @Test
	    public void	endingBalanceAppliesInterestRates() {
	    	assertEquals(11000, newAccount().endingBalance(25));
	    }
	    
	    @Test
	    public void multiplewithdrawalsInAYearAreTotaled() {
	    	StockMarketYear year = newAccount();
	    	year.withdraw(1000);
	    	year.withdraw(2000);
	    	// changed from year.totalWithdrawnExcptCapitalGains()
	    	assertEquals("totalwithdrawan ", 3000, year.totalWithdrawn(25));
	    //	assertEquals(7700, year.endingBalance(25));
	    }
	    
	    @Test
	    public void withdrawingMoreThanPrincipalTakesFromCapitalGains() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	year.withdraw(1000);
	    	assertEquals(0, year.capitalGainsWithdrawn());
	    	year.withdraw(3000);
	    	assertEquals(1000, year.capitalGainsWithdrawn() );
	    }
	    
	    @Test
	    public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_NAD_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	year.withdraw(5000);
	    	assertEquals(2000,year.capitalGainsWithdrawn());
	    	assertEquals(666,year.capitalGainsTaxIncurred(25));
	    }
	    
	    @Test
	    public void capitalGainsTaxIsIncludedInEndingBalance() {
	    	StockMarketYear year = new StockMarketYear(10000, 3000, 10);
	    	int amountWithdrawn = 5000;
	    	year.withdraw(amountWithdrawn);
	    	int expectedCapitalGainsTax = 666;
	    	assertEquals(expectedCapitalGainsTax, year.capitalGainsTaxIncurred(25));
	    	int expectedStaringBalanceAfterWithdrawals = 10000 - amountWithdrawn - expectedCapitalGainsTax;
	    	assertEquals((int)(expectedStaringBalanceAfterWithdrawals * 1.10), year.endingBalance(25));
	    
	    }
	    
	    
	    
	    // nextYearsStartingBalanceEqualsThisYearsEndingBlance() => nextYear()
	    // thisYear.nextYear(25) => nextYear : Extract local variable(alt shift l)
	    @Test
	    public void nextYear() {
	    	StockMarketYear thisYear = newAccount();
	    	//assertEquals(thisYear.endingBalance(), thisYear.nextYear().endingBalance());
	    	StockMarketYear nextYear = thisYear.nextYear(25);
			assertEquals("starting balance", thisYear.endingBalance(25), nextYear.startingBalance());
	    	assertEquals("starting principal", thisYear.interestRate(), nextYear.interestRate());
	    }
	    
	    private StockMarketYear newAccount() {
			StockMarketYear account = new StockMarketYear(10000, 10000, 10);
			return account;
		}
	       
	}

