package spongelist;

import java.util.Arrays;


/**
 * This list type works using a Generalized List.
 * 
 * @author fluorine (github)
 * @param <T>
 */
public abstract class SpongeList<T> {
	/**
	 * @return Count of total items in the list (not levels)
	 */
	public abstract int size();
	
	/**
	 * Get a StringBuilder text representation
	 * of the list.
	 * @param text
	 */
	public abstract void draw(StringBuilder text);
	
	/**
	 * Get T object at given index.
	 * @param index
	 * @return T object
	 */
	public abstract T get(int index);
	
	public abstract SpongeList<T> add(int index, T value);
	
	//Protected operations
	protected abstract SpongeList<T>[] getLevels();
	
	//Common operations
	/**
	 * Append two arrays of SpongeList objects.
	 * @param x
	 * @param y
	 * @return New array
	 */
	protected SpongeList<T>[] append(SpongeList<T>[] x, SpongeList<T>[] y) {
		SpongeList<T>[] a = x;
		SpongeList<T>[] b = y;
		@SuppressWarnings("unchecked")
		SpongeList<T>[] c = new SpongeList[a.length + b.length];
		
		//Fill new array
		int k = 0;
		
		for(int i = 0; i < a.length; i++)
			c[k++] = a[i];
		
		for(int i = 0; i < b.length; i++)
			c[k++] = b[i];
		
		return c;
	}
	
	/**
	 * Append two SpongeLists to get a new one.
	 * @param a
	 * @param b
	 * @return New SpongeList
	 */
	protected SpongeList<T> append(SpongeList<T> a, SpongeList<T> b) {
		return new Sublist<T>(append(a.getLevels(), b.getLevels()));
	}
	
	/**
	 * This function expects a new item to replace the old one,
	 * but it can also return a "rewritten" parent list if 
	 * the new sublist has more levels than the parent list.
	 * 
	 * @param index Index to add new item.
	 * @param item New item to replace old one in 'index' position.
	 * @return New balanced SpongeList
	 */
	protected SpongeList<T> unfoldItem(int index, SpongeList<T> item) {
		SpongeList<T>[] temp;
		
		if(levels() > item.levels()) {
			//If parent list has more levels than child list,
			//then it's stable.
			temp = getLevels().clone();
			temp[index] = item;
		} else {
			//Else, parent list has to be rewritten to include
			//the internal elements of the new item.
			SpongeList<T>[] a = Arrays.copyOfRange(getLevels(), 0, index);
			SpongeList<T>[] b = item.getLevels();
			SpongeList<T>[] c = Arrays.copyOfRange(getLevels(), index + 1, getLevels().length);
			temp = append(a, b);
			temp = append(temp, c);
		}
		
		//Anyway, return a new instance of the parent list
		//to keep persistence.
		return new Sublist<T>(temp);
	}
	
	/**
	 * @return Levels of the list; the number of slots
	 * in the internal array (or 1, if Atom).
	 */
	protected abstract int levels();
	
	/**
	 * String representation of a SpongeList
	 * For example: (3 5 6 (1 6 5 (1 3)) 7 4 (2 4))
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		draw(sb);
		return sb.toString();
	}
}
