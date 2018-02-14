
class Imp {
	private int maxLevelMana;
	private int mana;
	
	Imp(int newCountMana) {
		if (newCountMana < 0) {
			System.out.println("ERROR! Count mana must be >= 0");
			return;
		}
		this.mana = newCountMana;
		this.maxLevelMana = mana;
	}
	
	protected void upgrade_Mana(int count) {
		this.maxLevelMana += count;
	}
	
	protected void minus_mana(int count) {
		this.mana -= count;
	}
	
	protected void plus_mana(int count) {
		if (this.mana + count > this.maxLevelMana)
			this.mana = this.maxLevelMana;
		else
			this.mana += count;
	}
	
	protected int get_Mana() {
		return this.mana;
	}
}