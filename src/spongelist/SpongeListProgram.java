package spongelist;


public class SpongeListProgram {

	/**
	 * Testing a "SpongeList<T>'s" instance.
	 */
	public static void main(String[] args) {
		//First items
		SpongeList<Integer> x = new Atom<Integer>(100);
		x = x.add(1, 200);
		x = x.add(2, 300);
		x = x.add(3, 400);
		x = x.add(4, 500);
		x = x.add(5, 600);
		x = x.add(6, 700);
		x = x.add(7, 800);
		
		x = x.add(5, -600);
		
		//Adding other 7 items
		for(int i = 0; i < 8; i++)
			x = x.add(5, i);
		
		//Quantity of items, counting recursively
		System.out.println(x.toString() + " Count: " + x.size());
		
		//Using index to access items
		System.out.println();
		for(int i = 0; i < x.size(); i++)
			System.out.print(x.get(i) + " ");
	}

}
