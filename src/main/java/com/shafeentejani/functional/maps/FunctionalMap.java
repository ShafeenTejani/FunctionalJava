package com.shafeentejani.functional.maps;
import static com.shafeentejani.functional.lists.FunctionalList.*;

import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.lists.FunctionalList;

public class FunctionalMap<K,V> {

	private final FunctionalList<Entry<K,V>> entries;
	
	public FunctionalMap(){
		entries = FunctionalList.nil();
	}
	
	private FunctionalMap(final FunctionalList<Entry<K,V>> entries){
		this.entries = entries;
	}
	
	public FunctionalMap<K,V> put(final K key,final V value){
		
		return containsKey(key,entries) ?
				new FunctionalMap<K, V>(FunctionalList.cons(new Entry<K,V>(key,value), removeKey(key,entries))) :
					new FunctionalMap<K, V>(FunctionalList.cons(new Entry<K,V>(key,value), entries));
				
	};
	
	public V get(final K key){
		return getValueFromEntries(key,entries);
	}
	
	public FunctionalMap<K,V> remove(final K key){
		return new FunctionalMap<K,V>(removeKey(key, entries));
	}


	public FunctionalList<K> keySet() {
		return entriesToKeySet(entries);
	}
	

	public FunctionalList<V> filterValuesOn(final Function<V,Boolean> matcher){
		return filterValuesOn(matcher,keySet());
	}
	
	private FunctionalList<V> filterValuesOn(final Function<V, Boolean> matcher,final FunctionalList<K> keySet) {
		return keySet.isEmpty() ?
				FunctionalList.<V>nil() :
					matcher.apply(get(keySet.head())) ?
							cons(get(keySet.head()),filterValuesOn(matcher,keySet.tail())) :
								filterValuesOn(matcher,keySet.tail());
	}

	private boolean containsKey(final K key, final FunctionalList<Entry<K, V>> entries) {
		
		return entries.isEmpty() ?
				false :
					entries.head().key.equals(key) ?
							true :
								containsKey(key, entries.tail());
	}

	private FunctionalList<Entry<K, V>> removeKey(final K key, final FunctionalList<Entry<K, V>> entries) {

		return entries.isEmpty() ?
				entries :
					entries.head().key.equals(key) ?
							entries.tail() :
								FunctionalList.cons(entries.head(), removeKey(key, entries.tail()));
	}

	private V getValueFromEntries(final K key,final FunctionalList<Entry<K,V>> entries) {
		
		return entries.isEmpty() ?
				null :
					entries.head().key.equals(key) ?
							entries.head().value :
								getValueFromEntries(key, entries.tail());
	}
	
	
	private FunctionalList<K> entriesToKeySet(final FunctionalList<Entry<K,V>> entries){
		return entries.isEmpty() ? 
				FunctionalList.<K>nil() :
					cons(entries.head().key,entriesToKeySet(entries.tail()));
	}
	

	public static final class Entry<K,V>{
		
		final K key;
		final V value;
		
		public Entry(final K key,final V value){ 
			this.key = key;
			this.value = value;
		}
	}
		
}
