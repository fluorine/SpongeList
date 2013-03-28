package spongelist;


public class Sublist<T> extends SpongeList<T> {
	SpongeList<T>[] levels;
	int size;
	
	//Constructor
	public Sublist(SpongeList<T>[] _levels) {
		levels = _levels;
		
		//Update counter
		size = 0;
		for(SpongeList<T> i: levels)
			size += i.size();
	}

	//Properties
	@Override
	public int size() {
		return size;
	}

	@Override
	public int levels() {
		return levels.length;
	}

	@Override
	public void draw(StringBuilder text) {
		text.append("(");
		
		int i;
		for(i = 0; i < levels() - 1; i++) {
			levels[i].draw(text);
			text.append(" ");
		}
		
		levels[i].draw(text);
		
		text.append(")");
	}

	//Operations
	@Override
	public T get(int index) {
		int accum = 0;
		
		for(int i = 0; i < levels(); i++) {
			int size = levels[i].size();
			if(accum +  size > index)
				return levels[i].get(index - accum);
			
			accum += size;
		}
		
		throw new IndexOutOfBoundsException();
	}

	@Override
	public SpongeList<T> add(int index, T value) {
		//Add to edge
		if(index == size()) {
			SpongeList<T> last = levels[levels() - 1];
			return unfoldItem(levels() - 1, last.add(last.size(), value));
		}
		
		//Add inside list
		int accum = 0;		
		for(int i = 0; i < levels(); i++) {
			int size = levels[i].size();
			if(accum +  size > index)
				return unfoldItem(i, levels[i].add(index - accum, value));
			
			accum += size;
		}
		
		throw new IndexOutOfBoundsException();
	}

	//Internal operations
	
	@Override
	protected SpongeList<T>[] getLevels() {
		return levels;
	}

}
