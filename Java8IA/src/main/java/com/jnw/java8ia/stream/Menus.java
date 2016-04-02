package com.jnw.java8ia.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menus {
	private final static List<Dish> menus = Arrays.asList(
		new Dish("pork"			, false	, 800, Dish.Type.META),
		new Dish("beef"			, false	, 700, Dish.Type.META),
		new Dish("chicken"		, false	, 400, Dish.Type.META),
		new Dish("french fries"	, true	, 530, Dish.Type.OTHER),
		new Dish("rice"			, true	, 350, Dish.Type.OTHER),
		new Dish("season fruit"	, true	, 120, Dish.Type.OTHER),
		new Dish("pizza"		, true	, 550, Dish.Type.OTHER),
		new Dish("prawns"		, false	, 300, Dish.Type.META),
		new Dish("salmon"		, false	, 450, Dish.Type.META)
	);
	public static void main(String[] args ) {
		Menus menus = new Menus();
		//1. Print n=5 High( >300) Caloric Dish Name
		//menus.nHighCaloricDishName(5,300).forEach( System.out::println );
		//2. All Dish Names
		//menus.allDishNames().forEach( System.out::println);
		//3. Summarize all calories
		System.out.println(menus.sumCalories() );
	}
	private List<String> nHighCaloricDishName( int n, int limit ) {
		return menus.stream().filter( dish -> dish.getCalories() > limit )
				.map( d->d.getName())
				.limit(n) 	//upto here, still a stream, need convert to a List ( limited size ), 
							//methods have not been invoked until now
				.collect( Collectors.toList() );
	}
	private List<String> allDishNames() {
		return menus.stream().map(Dish::getName)
				.collect( Collectors.toList());
	}
	
	private int sumCalories() {
		return menus.stream().map(Dish::getCalories).reduce(0, Integer::sum).intValue();
	}
}
