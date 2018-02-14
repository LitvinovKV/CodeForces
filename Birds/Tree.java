
class Tree {
	private int countBirds;
	private int countMana;
	
	Tree(int newCB, int newCM) {
		if (newCB >= Math.pow(10, 4) || newCB < 0) {
			System.out.println("ERROR! Count birds on tree must be >= 0 and <= 10^4.");
			return;
		}
		if (newCM >= Math.pow(10, 9) || newCM < 0) {
			System.out.println("ERROR! Count mana for tree must be >= 0 and <= 10^9.");
			return;
		}
		this.countBirds = newCB;
		this.countMana = newCM;
	}
	
	protected int get_CountBirds() {
		return this.countBirds;
	}
	
	protected int get_countMana() {
		return this.countMana;
	}
	
	protected void minus_CountBirds() {
		this.countBirds--;
	}
	
	protected void cout_Tree() {
		System.out.println("Count birds on tree = " + this.countBirds);
		System.out.println("Count mana on tree = " + this.countMana);
	}
}