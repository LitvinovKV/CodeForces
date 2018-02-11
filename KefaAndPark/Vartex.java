
class Vartex {
	protected Vartex[] parent_Vartex;
	protected Vartex[] children_Vartex;
	protected Color cl;
	protected String name;
	
	Vartex(String newName) {
		this.name = newName;
		this.cl = Color.WHITE; 
	}
	
	static Vartex[] add_newVartex(Vartex[] array, Vartex newVartex) {
		int lengthArray;
		try {
			lengthArray = array.length;
		} catch (NullPointerException e) {
			lengthArray = 0;
		}
		int newLength = lengthArray + 1;
		Vartex[] newMass = new Vartex[newLength];
		
		for (int i = 0; i < lengthArray; i++)
			newMass[i] = array[i];
		
		newMass[newLength - 1] = newVartex;
		array = newMass;
		newMass = null;
		return array;
	}
	
	static int checkRed_Parents(Vartex v, int count) {
		if (v.parent_Vartex != null) {
			if (v.cl == Color.RED) {
				count++;
				count += checkRed_Parents(v.parent_Vartex[0], count);
			}
			else {
				return 0;
			}
		}
		if (v.parent_Vartex == null && v.cl == Color.RED) {
			return 1;
		}
		return count;
	}
	
	protected void cout_childrens() {
		if (children_Vartex == null) return;
		System.out.print("CHILDRENS for " + this.name + " : ");
		for (int i = 0; i < children_Vartex.length; i++)
			System.out.print(children_Vartex[i].name + "\t");
		System.out.println();
	}
	
	protected void cout_parents() {
		if (parent_Vartex == null) return;
		System.out.print("PARENTS for " + this.name + " : ");
		for (int i = 0; i < parent_Vartex.length; i++)
			System.out.print(parent_Vartex[i].name + "\t");
		System.out.println();
	}
	
	protected void cout_Vartexs(Vartex[] array) {
	
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}
	
}