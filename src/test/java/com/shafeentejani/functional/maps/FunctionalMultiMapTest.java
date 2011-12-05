package com.shafeentejani.functional.maps;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.shafeentejani.functional.lists.FunctionalList;
import com.shafeentejani.functional.maps.FunctionalMultiMap;

public class FunctionalMultiMapTest {

	private FunctionalMultiMap<Integer, String> map;
	
	@Before
	public void setUp(){
		map = new FunctionalMultiMap<Integer, String>();
	}

	@Test
	public void testMapStoresMultipleEntries(){
		assertTrue(listsAreEqual(FunctionalList.of("one","two","three"),map.put(3, "one").put(3, "two").put(3, "three").get(3)));
	}
	
	@Test
	public void testGetNonExistantEntry(){
		assertTrue(listsAreEqual(FunctionalList.<String>nil(),map.get(1)));
	}
	
	@Test
	public void testRemoveEntry(){
		assertTrue(listsAreEqual(FunctionalList.<String>nil(),map.put(1, "one").put(1, "two").remove(1).get(1)));
	}
	
	private <V> boolean listsAreEqual(FunctionalList<V> one, FunctionalList<V> two){
		
		return (one == null || two == null) ?
				(one == null && two == null) :
					( one.isEmpty() || two.isEmpty() ) ?
							(one.isEmpty() && two.isEmpty()) :
								(one.head().equals(two.head())) ?
										listsAreEqual(one.tail(),two.tail()) :
											false;
	}
	
	
}
