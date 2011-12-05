package com.shafeentejani.cards.poker;

import org.junit.Test;

import com.shafeentejani.cards.Card;
import com.shafeentejani.functional.lists.FunctionalList;

import static com.shafeentejani.cards.poker.PokerHandEvaluator.*;
import static com.shafeentejani.functional.lists.FunctionalList.*;
import static org.junit.Assert.*;



public class PokerHandEvaluatorTest {

	@Test
	public void testHandContainsPair(){
		
		assertTrue(hasPair(toCards("2c 7h 8c 8d Js")));
		
		assertTrue(hasPair(toCards("2c 7h 8c Td Tc Jd Js")));
		
		assertFalse(hasPair(toCards("2c 7h 8c Td Jc Qs")));
	}
	
	@Test
	public void testHandContainsTwoPair(){
		
		assertTrue(hasTwoPair(toCards("2c 2d 7h 8c 8d Js")));
		
		assertTrue(hasTwoPair(toCards("2c 2c 7h 8c Td Tc Jd Js")));
		
		assertFalse(hasTwoPair(toCards("2c 7h 8c Td Tc Jc Qs")));
	}
	
	@Test
	public void testHandContainsTrips(){
		
		assertTrue(hasThreeOfAKind(toCards("2c 7h 8c 8d 8h")));
		
		assertTrue(hasThreeOfAKind(toCards("2c 7h 8c Ts Td Tc Jc Jd Js")));
		
		assertFalse(hasPair(toCards("2c 7h 8c Td Jc Qs")));
	}
	
	@Test
	public void testHandContainsStraight(){
		
		assertTrue(hasStraight(toCards("2c 3h 4c 5d 6h")));
		
		assertTrue(hasStraight(toCards("2c 2h 3c 4s 5d 5c 6c Jd Js")));
		
		assertFalse(hasStraight(toCards("2c 7h 8c Td Jc Qs")));
	}
	
	@Test
	public void testHandContainsFlush(){
		
		assertTrue(hasFlush(toCards("2h 3h 5h 6h 8h")));
		
		assertTrue(hasFlush(toCards("2c 2h 5c 6s 8c Tc Jc Jd Js")));
		
		assertFalse(hasFlush(toCards("2c 7h 8c Td Jc Qs")));
		
	}
	
	@Test
	public void testHandContainsFullHouse(){
		
		assertTrue(hasFullHouse(toCards("3h 3d 3c Th Td")));
		
		assertTrue(hasFullHouse(toCards("2c 6h Tc Ts Td Jc Qc Qd")));
		
		assertFalse(hasFullHouse(toCards("2c 7h 7d 7c 8c Qd Qc Qs")));
		
	}
	
	@Test
	public void testHandContainsFourOfAKind(){
		
		assertTrue(hasFourOfAKind(toCards("2c 8s 8c 8d 8h")));
		
		assertTrue(hasFourOfAKind(toCards("2c 7h 8c Ts Td Tc Th Jc Js")));
		
		assertFalse(hasPair(toCards("2c 7h 8c 8s 8h Td Jc Qs")));
	}
	
	@Test
	public void testHandContainsStraightFlush(){
		
		assertTrue(hasStraightFlush(toCards("2c 3c 4c 5c 6c")));
		
		assertTrue(hasStraightFlush(toCards("2c 7h 8h 9h Ts Td Tc Th Jc Jh")));
		
		assertFalse(hasStraightFlush(toCards("2h 7h 8c 8h 9d Th Jh Qs")));
		
	}
	
	@Test
	public void testHandContainsRoyalFlush(){
		
		assertTrue(hasStraight(toCards("Tc Jc Qc Kc Ac")));
		
		assertTrue(hasRoyalFlush(toCards("2c 5d Th Tc Jd Jc Qc Kc Ac")));
		
		assertFalse(hasRoyalFlush(toCards("2c 3c 4c 5c 6c")));
		
		assertFalse(hasRoyalFlush(toCards("2c 3c 4c 5c 6c")));
		
		assertFalse(hasRoyalFlush(toCards("2c 7h 8h 9h Ts Td Tc Th Jc Jh")));

	}
	
	
	private FunctionalList<Card> toCards(String input){
		return stringListToCards(of(input.split(" ")));
	}

	private FunctionalList<Card> stringListToCards(FunctionalList<String> list) {
		return list.isEmpty() ?
				FunctionalList.<Card>nil() :
					cons(Card.fromString(list.head()),stringListToCards(list.tail()));
	}

}
