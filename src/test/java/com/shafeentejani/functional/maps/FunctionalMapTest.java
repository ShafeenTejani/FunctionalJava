package com.shafeentejani.functional.maps;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.shafeentejani.functional.maps.FunctionalMap;


public class FunctionalMapTest {

	FunctionalMap<Integer,String> map;
	
	@Before
	public void setUp(){
		map = new FunctionalMap<Integer,String>();
	}
	
	@Test
	public void testMapStoresEntry(){
		assertEquals("one",map.put(1, "one").get(1));
	}
	
	@Test
	public void testEntryCanBeRemoved(){
		assertNull(map.put(1, "one").remove(1).get(1));
	}
	
	@Test
	public void testGettingNonExistantEntry(){
		assertNull(map.get(1));
	}
}
