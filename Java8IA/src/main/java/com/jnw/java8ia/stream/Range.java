package com.jnw.java8ia.stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Range {

	public static void main(String[] args ) {
		IntStream.rangeClosed(1,100).boxed()
			.flatMap( a ->
				IntStream.rangeClosed(a, 100).mapToObj( b->new double[]{a,b,Math.sqrt(a*a+b*b)})
				.filter( t -> t[2] % 1 == 0 ))
			.collect(Collectors.toList());
	}
}
