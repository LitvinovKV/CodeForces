
class StringH {
	private String str;
	private int countH;
	private int countS;
	
	StringH(String newStr) {
		str = newStr;
		set_CountH();
		set_CountS();
	}
	
	protected void set_str(String newStr) {
		if (newStr.length() > Math.pow(10, 5)) {
			System.out.println("ERROR! String length must be <= 10 ^ 5");
			return;
		}
		this.str = newStr;
	}
	
	private void set_CountH() {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'h')
				countH++;
		}
	}
	
	private void set_CountS() {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 's')
				countS++;
		}
	}
	
	protected String get_str() {
		return this.str;
	}
	
	protected int get_countH() {
		return this.countH;
	}
	
	protected int get_countS() {
		return this.countS;
	}
}
