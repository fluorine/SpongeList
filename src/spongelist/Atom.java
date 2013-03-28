package spongelist;


/**
 * This is the base case of a SpongeList.
 * @author fluorine (github)
 * @param <T>
 */
public class Atom<T> extends SpongeList<T> {
	T value;
	
	//Constructor
	public Atom(T _value) {
		value = _value;
	}
	
	@Override
	public int size() {
		return 1;
	}

	@Override
	public int levels() {
		return 1;
	}

	@Override
	public void draw(StringBuilder text) {
		text.append(value);
	}

	@Override
	public T get(int index) {
		if(index != 0)
			throw new IndexOutOfBoundsException();
		
		//System.out.print(" <" + index + ">");
		
		return value;
	}

	@Override
	public SpongeList<T> add(int index, T value) {
		SpongeList<T> item = new Atom<T>(value);
		
		if(index == 0)
			return append(item, this);

		if(index == 1)
			return append(this, item);
			
		throw new IndexOutOfBoundsException();
	}

	@Override
	protected SpongeList<T>[] getLevels() {
		@SuppressWarnings("unchecked")
		SpongeList<T>[] temp = new SpongeList[1];
		temp[0] = this;
		
		return temp;
	}
}
