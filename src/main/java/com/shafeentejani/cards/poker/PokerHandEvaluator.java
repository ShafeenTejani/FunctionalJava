package com.shafeentejani.cards.poker;

import com.shafeentejani.cards.Card;
import com.shafeentejani.cards.Card.Rank;
import com.shafeentejani.cards.Card.Suit;
import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.lists.FunctionalList;


public class PokerHandEvaluator {
	
	
	public static boolean hasPair(final FunctionalList<Card> cards){
		return cards.groupBy(rank()).filterValuesOn(size(equalTo(2))).size() >= 1;
	}
	
	
	public static boolean hasTwoPair(final FunctionalList<Card> cards){
		return cards.groupBy(rank()).filterValuesOn(size(equalTo(2))).size() >= 2;
	}
	
	
	public static boolean hasThreeOfAKind(final FunctionalList<Card> cards){
		return cards.groupBy(rank()).filterValuesOn(size(equalTo(3))).size() >= 1;
	}
	

	public static boolean hasStraight(final FunctionalList<Card> cards){
		return cards.isEmpty() ?
				false :
					hasConsecutiveRanks(cards.head(), cards.dropWhile(equalRanks(cards.head())), 5);
	}
	
	
	public static boolean hasFlush(final FunctionalList<Card> cards){
		return cards.groupBy(suit()).filterValuesOn(size(equalTo(5))).size() >= 1;
	}
	
	
	public static boolean hasFullHouse(final FunctionalList<Card> cards){
		return hasThreeOfAKind(cards) && hasPair(cards);
	}
	
	
	public static boolean hasFourOfAKind(final FunctionalList<Card> cards){
		return cards.groupBy(rank()).filterValuesOn(size(equalTo(4))).size() >= 1;
	}
	
	
	public static boolean hasStraightFlush(final FunctionalList<Card> cards){
		return checkForStraights(cards.groupBy(suit()).filterValuesOn(size(greaterThanEqualTo(5))));
	}
	
	
	public static boolean hasRoyalFlush(final FunctionalList<Card> cards){
		return hasStraightFlush(cards.dropWhile(rankLessThan(Rank.TEN)));
	}
	
	
	// helper methods
	
    // Determines if a there are num cards with consecutive ranks - assumes input is sorted on ranks  
	private static boolean hasConsecutiveRanks(final Card first, final FunctionalList<Card> remaining,final int num){
		return (remaining.isEmpty() || num == 1) ?
				(num == 1) :
					areConsecutive(first,remaining.head()) ?
							hasConsecutiveRanks(remaining.head(),remaining.dropWhile(equalRanks(remaining.head())),num-1):
								hasConsecutiveRanks(remaining.head(),remaining.dropWhile(equalRanks(remaining.head())),num);
					
	}
	
	private static boolean areConsecutive(Card card1, Card card2){
		return card1.rank().order() == (card2.rank().order() - 1);
	}
	

	private static boolean checkForStraights(final FunctionalList<FunctionalList<Card>> listsToCheck) {
		return listsToCheck.isEmpty() ?
				false :
					hasStraight(listsToCheck.head()) || checkForStraights(listsToCheck.tail());
	}

	
	
	private static Function<Card,Rank> rank(){
		return new Function<Card, Rank>() {
			@Override
			public Rank apply(Card input) {
				return input.rank();
			}
		};
	}
	
	private static Function<Card,Suit> suit(){
		return new Function<Card, Suit>() {
			@Override
			public Suit apply(Card input) {
				return input.suit();
			}
		};
	}
	
	
	private static Function<Card,Boolean> rankLessThan(final Rank r){
		return new Function<Card, Boolean>() {
			@Override
			public Boolean apply(Card input) {
				return input.rank().order() < r.order();
			}
		};
	}
	
	private static Function<Card,Boolean> equalRanks(final Card card){
		return new Function<Card, Boolean>() {

			@Override
			public Boolean apply(Card input) {
				return input.rank().equals(card.rank());
			}
		};
	}
	
	private static Function<FunctionalList<Card>,Boolean> size(final Function<Integer,Boolean> function){
		return new Function<FunctionalList<Card>, Boolean>() {
			
			@Override
			public Boolean apply(FunctionalList<Card> input) {
				return function.apply(input.size());
			}
		};
	}
	
	private static Function<Integer,Boolean> greaterThanEqualTo(final int size){
		return new Function<Integer, Boolean>() {

			@Override
			public Boolean apply(Integer input) {
				return input >= size;
			}
		};
	}
	
	private static <V> Function<V,Boolean> equalTo(final V expected){
		return new Function<V, Boolean>() {

			@Override
			public Boolean apply(V actual) {
				return expected.equals(actual);
			}
		};
	}
	
}
