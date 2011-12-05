package com.shafeentejani.functional.maps;

import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.lists.FunctionalList;

public class FunctionalMultiMap<K,V>  {

	private final FunctionalMap<K,FunctionalList<V>> map;
	
	public FunctionalMultiMap(){
		map =  new FunctionalMap<K, FunctionalList<V>>();
	}
	
	private FunctionalMultiMap(FunctionalMap<K,FunctionalList<V>> map){ 
		this.map = map;
	}
	
	public FunctionalList<V> get(K key){
		return map.get(key) == null ?
				FunctionalList.<V>nil() :
					map.get(key);
	}
	
	public FunctionalList<K> keySet(){
		return map.keySet();
	}
	
	public FunctionalMultiMap<K,V> put(K key, V value){
		return new FunctionalMultiMap<K,V>(map.put(key, get(key).append(value)));
	}
	
	public FunctionalMultiMap<K,V> remove(K key){
		return new FunctionalMultiMap<K, V>(map.remove(key));
	}
	
	public FunctionalList<FunctionalList<V>> filterValuesOn(Function<FunctionalList<V>,Boolean> matcher){
		return map.filterValuesOn(matcher);
	}

}
