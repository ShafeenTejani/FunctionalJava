package com.shafeentejani.cards;

import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.lists.FunctionalList;

import static com.shafeentejani.functional.lists.FunctionalList.*;

public class Card {

	private final Rank rank;
	private final Suit suit;
	
	public Card(final Rank rank,final Suit suit){
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card(final char rank,final char suit){
		this.rank = Rank.fromChar(rank);
		this.suit = Suit.fromChar(suit);
	}
	
	public static Card fromString(final String s){
		return new Card(s.charAt(0), s.charAt(1));
	}
	
	public Rank rank(){
		return rank;
	}
	
	public Suit suit(){
		return suit;
	}
	
	@Override
	public String toString(){
		return rank.toString() + suit.toString();
	}
	
	@Override
	public boolean equals(final Object o){
		return (o != null && o instanceof Card) ?
				((Card)o).suit.equals(suit) && ((Card)o).rank.equals(rank) :
					false;
	}
	
	private static <V> V replaceNullWith(final V nullable,final V replacement) {
		return nullable == null ? replacement : nullable;
	}
	
	public enum Suit{
		NULL('\0'),
		HEARTS('h'),
		CLUBS('c'),
		DIAMONDS('d'),
		SPADES('s');
		
		private final char rep;
		private Suit(final char rep){this.rep = rep;}
		public static Suit fromChar(final char c){
			return replaceNullWith(suits.firstMatch(matchesRep(c)),NULL);
		}
		
		@Override
		public String toString(){
			return ""+rep;
		}
		
		private static final FunctionalList<Suit> suits = of(Suit.values());
		
		private static Function<Suit,Boolean> matchesRep(final char c){
			return new Function<Suit, Boolean>() {
				@Override
				public Boolean apply(final Suit input) {
					return input.rep == c;
				}
			};
		}
	}
	
	public enum Rank {
		NULL('\0',-1),
		TWO('2',1),
		THREE('3',2),
		FOUR('4',3),
		FIVE('5',4),
		SIX('6',5),
		SEVEN('7',6),
		EIGHT('8',7),
		NINE('9',8),
		TEN('T',9),
		JACK('J',10),
		QUEEN('Q',11),
		KING('K',12),
		ACE('A',13);
		
		private final char rep;
		private final int order;
		private Rank(final char rep,final int order){
			this.rep = rep;
			this.order = order;
		}
		
		public static Rank fromChar(final char c){
			return replaceNullWith(ranks.firstMatch(matchesRep(c)),NULL);
		}
		
		public int order(){ return order;}
		
		@Override
		public String toString(){
			return ""+rep;
		}
		
		private static Function<Rank,Boolean> matchesRep(final char c){
			return new Function<Rank, Boolean>() {
				@Override
				public Boolean apply(final Rank input) {
					return input.rep == c;
				}
			};
		}
		
		private static final FunctionalList<Rank> ranks = of(Rank.values());
	}
	
}
