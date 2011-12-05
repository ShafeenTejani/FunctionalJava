package com.shafeentejani.cards.poker;

import com.shafeentejani.cards.Card;
import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.lists.FunctionalList;
import static com.shafeentejani.cards.poker.PokerHandEvaluator.*;

public class PokerHand {

	private static final FunctionalList<Category> categories = FunctionalList.of(Category.ROYAL_FLUSH,
			Category.STRAIGHT_FLUSH, Category.FOUR_OF_A_KIND, Category.FULL_HOUSE,
			Category.FLUSH, Category.STRAIGHT, Category.THREE_OF_A_KIND,
			Category.TWO_PAIR, Category.ONE_PAIR, Category.HIGH_CARD);

	private final FunctionalList<Card> cards;
	
	public PokerHand(final FunctionalList<Card> cards){
		this.cards = cards;
	}
	
	
	public static PokerHand of(final Card...cards){
		 return new PokerHand(FunctionalList.<Card>of(cards));
	}
	
	
	public static PokerHand of(final String representation){
		return new PokerHand(FunctionalList.of(representation.split(" ")).map(new Function<String, Card>() {

			@Override
			public Card apply(String input) {
				return Card.fromString(input);
			}
		}));
	}
	
	
	public FunctionalList<Card> getCards(){
		return cards;
	}
	
	public Category category(){
		return determineCategory(categories);
	}
	
	private Category determineCategory(FunctionalList<Category> categories){
		return categories.head().evaluate(this) ?
				categories.head() :
					determineCategory(categories.tail());
	}
	
	public enum Category{
		
		ROYAL_FLUSH("Royal Flush",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasRoyalFlush(input.getCards());
			}}),
		
		STRAIGHT_FLUSH("Straight Flush",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasStraightFlush(input.getCards());
			}}),
		
		FOUR_OF_A_KIND("Four Of A Kind",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasFourOfAKind(input.getCards());
			}}),
		
		FULL_HOUSE("Full House",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasFullHouse(input.getCards());
			}}),
			
		FLUSH("Flush",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasFlush(input.getCards());
			}}),

		STRAIGHT("Straight",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasStraight(input.getCards());
			}}),

		THREE_OF_A_KIND("Three Of A Kind",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasThreeOfAKind(input.getCards());
			}}),
			
		TWO_PAIR("Two Pair",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasTwoPair(input.getCards());
			}}),
		
		ONE_PAIR("One Pair",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return hasPair(input.getCards());
			}}),
			
		HIGH_CARD("High Card",new Function<PokerHand,Boolean>(){
			@Override
			public Boolean apply(PokerHand input) {
				return true;
			}});

		
		private final String rep;
		private final Function<PokerHand,Boolean> categoryEvaluator;
		
		private Category(String rep, Function<PokerHand,Boolean> categoryEvaluator){
			this.rep = rep;
			this.categoryEvaluator = categoryEvaluator;
		}
		
		public boolean evaluate(PokerHand hand){ return categoryEvaluator.apply(hand);}
		
		@Override
		public String toString(){
			return rep;
		}
		
	}
}
