package com.shafeentejani.cards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shafeentejani.cards.Card;
import com.shafeentejani.cards.Card.Rank;
import com.shafeentejani.cards.Card.Suit;

public class CardTest {

	@Test
	public void testFromChar(){
		assertEquals(new Card(Rank.ACE, Suit.DIAMONDS),new Card('A','d'));
		assertEquals(new Card(Rank.NULL, Suit.NULL), new Card('H','2'));
	}
	
	@Test
	public void testFromString(){
		assertEquals(new Card(Rank.ACE, Suit.SPADES), Card.fromString("As"));
		assertEquals(new Card(Rank.KING, Suit.CLUBS), Card.fromString("Kc"));
		assertEquals(new Card(Rank.NULL, Suit.NULL), Card.fromString("hello"));
	}
	
}
