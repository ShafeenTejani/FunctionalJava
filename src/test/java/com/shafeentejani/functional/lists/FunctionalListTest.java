package com.shafeentejani.functional.lists;

import org.junit.Before;
import org.junit.Test;

import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.lists.FunctionalList;
import com.shafeentejani.functional.maps.FunctionalMultiMap;

import static com.shafeentejani.functional.lists.FunctionalList.*;
import static org.junit.Assert.*;

public class FunctionalListTest {

	private FunctionalList<String> list;
	private Function<String,Integer> rank = new Function<String, Integer>() {
		
		@Override
		public Integer apply(String input) {
			return Integer.valueOf(input.substring(0,1));
		}
	};
	
	@Before
	public void setUp(){

	}
	
	@Test
	public void testGroupBy(){
		list = FunctionalList.of("1one","1two","1three","2a","2b","2c");
		FunctionalMultiMap<Integer,String> map = list.groupBy(rank);
		
		assertEquals(of("1one","1two","1three"),map.get(1));
		assertEquals(of("2a","2b","2c"),map.get(2));
	}
	
	@Test
	public void testSize(){
		list = FunctionalList.of("1one","1two","1three","2a","2b","2c");
		assertEquals(6,list.size());
		assertEquals(0,FunctionalList.<String>nil().size());
	}
	
	@Test
	public void testAppendSingleElement(){
		list = of("one","two","three");
		assertEquals(of("one","two","three","four"), list.append("four"));
	}
	
	@Test
	public void testAppendList(){
		list = of("one","two","three");
		assertEquals(of("one","two","three","four","five"), list.append(of("four","five")));
	}
	
	@Test
	public void testToString(){
		
		assertEquals("one two three ",of("one","two","three").toString());
	}
	
	@Test
	public void testFirstMatch(){
		assertEquals("two",of("one","two","three").firstMatch(firstCharIs('t')));
		assertNull("two",of("one","two","three").firstMatch(firstCharIs('a')));
	}
	
	
	private Function<String, Boolean> firstCharIs(final char c){
		return new Function<String, Boolean>() {
			@Override
			public Boolean apply(String input) {
				return input.charAt(0) == c;
			}
		};
	}
	
}
