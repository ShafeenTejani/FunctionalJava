package com.shafeentejani.cards.poker;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shafeentejani.cards.poker.PokerHand;

public class PokerHandTest {

	
	@Test
	public void testHighCard(){
		assertEquals("High Card",PokerHand.of("2c 4d 5s 8d Tc Jh Kh As").category().toString());
	}
	
	@Test
	public void testOnePair(){
		assertEquals("One Pair",PokerHand.of("2c 4d 5s 8d Tc Th Qd Kh").category().toString());
	}
	
	@Test
	public void testTwoPair(){
		assertEquals("Two Pair",PokerHand.of("2c 2d 5s 8d Tc Th Qd Kh").category().toString());
	}
	
	@Test
	public void testThreeOfAKind(){
		assertEquals("Three Of A Kind",PokerHand.of("2c 5s 8d Ts Tc Th Qd Kh").category().toString());
	}
	
	@Test
	public void testStraight(){
		assertEquals("Straight",PokerHand.of("2c 3s 4d 4s 5c 6h 7s").category().toString());
	}
	
	@Test
	public void testFlush(){
		assertEquals("Flush",PokerHand.of("2c 3s 4s 5c 6c 7c Kc").category().toString());
	}
	
	@Test
	public void testFullHouse(){
		assertEquals("Full House",PokerHand.of("2c 4s 4d 5c Tc Td Ts").category().toString());
	}
	
	@Test
	public void testFourOfAKind(){
		assertEquals("Four Of A Kind",PokerHand.of("2c 3s 4d Tc Tc Td Ts").category().toString());
	}
	
	@Test
	public void testStraightFlush(){
		assertEquals("Straight Flush",PokerHand.of("2c 3c 4c 4s 5c 6c 7s").category().toString());
	}
	
	@Test
	public void testRoyalFlush(){
		assertEquals("Royal Flush",PokerHand.of("8c Td Ts Js Qs Ks As").category().toString());
	}
}
