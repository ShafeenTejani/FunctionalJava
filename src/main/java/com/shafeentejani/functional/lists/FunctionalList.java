package com.shafeentejani.functional.lists;

import java.util.NoSuchElementException;

import com.shafeentejani.functional.Function;
import com.shafeentejani.functional.maps.FunctionalMultiMap;

public abstract class FunctionalList<T> {
    
	public static <T> FunctionalList<T> nil() {
        return new Nil<T>();
    }

    public static <T> FunctionalList<T> cons(final T head, final FunctionalList<T> tail) {
        return new Cons<T>(head, tail);
    }
    

    public static <T> FunctionalList<T> of(final T... items) {
        return fromArray(items, 0, items.length);
    }

    private static <T> FunctionalList<T> fromArray(final T[] items, final int start, final int end) {
        return start == end
                   ? FunctionalList.<T>nil()
                   : cons(items[start], fromArray(items, start + 1, end));
    }
    
	
    public abstract boolean isEmpty();

    public abstract T head();

    public abstract FunctionalList<T> tail();

    
    public <U> FunctionalList<U> map(final Function<T, U> mapping) {
        return isEmpty()
                   ? FunctionalList.<U>nil()
                   : cons(mapping.apply(head()), tail().map(mapping));
    }
    
    public <K> FunctionalMultiMap<K,T> groupBy(Function<T,K> groupingFunction){
		return groupIntoMap(new FunctionalMultiMap<K, T>(),groupingFunction,this);
	}
	
	private <K> FunctionalMultiMap<K,T> groupIntoMap(FunctionalMultiMap<K,T> map,Function<T,K> groupingFunction, FunctionalList<T> values){
		return values.isEmpty() ?
				map :
					groupIntoMap(map.put(groupingFunction.apply(values.head()),values.head()),groupingFunction,values.tail());
	}

	public FunctionalList<T> dropWhile(Function<T, Boolean> condition){
		  
    	return isEmpty() ?
    			this:
    				condition.apply(head()) ?
    						tail().dropWhile(condition) :
    							this;
    						
    }
 
	public int size(){
		return isEmpty() ? 0 : 1 + tail().size();
	}
	
	public T firstMatch(Function<T,Boolean> matcher){
		return isEmpty() ?
				null :
					matcher.apply(head()) ?
							head():
								tail().firstMatch(matcher);
	}
	
	
	public FunctionalList<T> append(final T element){
    	return isEmpty() ?
    			cons(element,FunctionalList.<T>nil()) :
    				cons(head(),tail().append(element));
    				
    }
    
	
    public FunctionalList<T> append(final FunctionalList<T> list){
    	return isEmpty() ?
    			list :
    				cons(head(),tail().append(list));
    }
	
	@Override
	public String toString(){
		return isEmpty() ?
				"" :
					head().toString() +" "+ tail().toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o){
		return (o == null || !(o instanceof FunctionalList<?>)) ?
				false :
					(isEmpty() || ((FunctionalList)o).isEmpty() ) ?
							(isEmpty() && ((FunctionalList)o).isEmpty()) :
								(head().equals(((FunctionalList)o).head())) ?
										tail().equals(((FunctionalList)o).tail()) :
											false;
	}
	
	@Override
	public int hashCode(){
		return hashCodeHelper(17);
		
	}
	
    private int hashCodeHelper(int start) {
		return isEmpty() ? 
				start:
					tail().hashCodeHelper(31*start + head().hashCode());
	}

	public static final class Nil<T> extends FunctionalList<T> {
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public T head() {
            throw new NoSuchElementException();
        }

        @Override
        public FunctionalList<T> tail() {
            throw new NoSuchElementException();
        }
    }

    public static final class Cons<T> extends FunctionalList<T> {
        private final T head;
        private final FunctionalList<T> tail;

        public Cons(final T head, final FunctionalList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public FunctionalList<T> tail() {
            return tail;
        }
    }
    
    
}
