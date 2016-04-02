package com.jnw.java8ia.stream.tdm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TradingStream {
	private final static Trader raoul = new Trader("Raoul", "Cambridge");
	private final static Trader mario = new Trader("Mario", "Milan");
	private final static Trader alan  = new Trader("Alan" , "Cambridge");
	private final static Trader brian = new Trader("Brian", "Cambridge");
	
	private final static List<Transaction> transactions = Arrays.asList(
		new Transaction(brian, 2011, 300),
		new Transaction(raoul, 2012, 1000),
		new Transaction(raoul, 2011, 400),
		new Transaction(mario, 2012, 710),
		new Transaction(mario, 2012, 700),
		new Transaction(alan , 2012, 950)
	);
	
	public static void main(String[] args ) {
		//1. find all transactions in 2011 and sort by value
		transactions.stream().filter( txn -> txn.getYear() == 2011 )
			.sorted(Comparator.comparing(Transaction::getValue))
			.collect(Collectors.toList())
			.forEach( System.out::println);
		
		//2. unique cities traders work
		transactions.stream().map(txn ->txn.getTrader().getCity())
		.collect( Collectors.toSet())
		.forEach( System.out::println);
		
		//3. all traders from Cambridge and sort by name
		transactions.stream().filter( txn-> txn.getTrader().getCity().equals("Cambridge"))
		.map( Transaction::getTrader )
		.sorted(Comparator.comparing( Trader::getName))
		.collect( Collectors.toList())
		.forEach(System.out::println);
		
		//4. A string of all trader's names sorted alphabetically
		System.out.println(transactions.stream().map(txn->txn.getTrader().getName())
		.distinct()
		.sorted()
		.collect(Collectors.joining()));
		
		//5. Milan based trader?
		System.out.println("4. Any trader is in Mialn? [" +
		transactions.stream()
		.anyMatch(t -> t.getTrader().getCity().equals("Milan")) +"]");
		//6. All values from traders living in Cambridge
		System.out.println("5. All values from traders living in Cambridge");
		System.out.println(
			transactions.stream().filter( txn ->txn.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.collect( Collectors.toList())
		);
		//7. Highest value of all the transactions
		System.out.println("7. Highest value of all the transactions");
		System.out.println(
			transactions.stream().map( Transaction::getValue)
			.reduce( Integer::max).get()
		);
		//8. Smallest transaction
		System.out.println("8. Smallest transaction");
		System.out.println(
			transactions.stream().reduce((t1,t2) ->t2.getValue() < t2.getValue() ? t2:t1).get()
		);
		
	}
}
