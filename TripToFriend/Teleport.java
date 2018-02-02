
class Teleport {
	private int leftLimit, rightLimit;
	
	Teleport() {
		this.leftLimit = 0;
		this.rightLimit = 0;
	}
	
	Teleport(int newLeft, int newRight) {
		this.leftLimit = newLeft;
		this.rightLimit = newRight;
	}
	
	public void setRange(int newLeft, int newRight) {
		this.leftLimit = newLeft;
		this.rightLimit = newRight;
	}
	
	public int getLeft() {
		return this.leftLimit;
	}
	
	public int getRight() {
		return this.rightLimit;
	}
	
	public boolean IsIn(int n) {
		if (this.leftLimit <= n || this.rightLimit >= n)
			return true;
		return false;
	}
}
