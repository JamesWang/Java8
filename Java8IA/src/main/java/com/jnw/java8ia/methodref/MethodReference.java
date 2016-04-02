package com.jnw.java8ia.methodref;

import java.util.Arrays;
import java.util.List;

public class MethodReference {
	public static void main( String[] args ) {
		List<String> strList = Arrays.asList("a","b","c","d","A","D");
		strList.sort( ( s1, s2 ) -> s1.compareToIgnoreCase(s2));
		strList.sort( String::compareToIgnoreCase);
	}
}
