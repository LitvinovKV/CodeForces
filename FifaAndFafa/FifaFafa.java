
import java.io.PrintWriter;
import java.util.Scanner;

class Flat {
	double x1, y1, R;
	
	Flat() {
		x1 = 0; y1 = 0; R = 0;
	}
	
	protected boolean inFlat(double newX, double newY) {
		double l = Math.sqrt( Math.pow(this.x1 - newX, 2) + Math.pow(this.y1 - newY, 2) );
		if (l < this.R) //in Flat
			return true;
		else
			return false;
	}
	
	protected void getAnswer(double newX, double newY) {
		double D = 0, x = 0, y = 0;
		D = Math.sqrt( Math.pow((this.x1 - newX), 2) + Math.pow((this.y1 - newY), 2) ) + this.R;
		double r = D / 2.0;
		
		if ((newX > this.x1) && (newY > this.y1)) { // first quarter
			System.out.println("first");
			x = newX - r; 
			y = newY - r;
		}
		else if ((newX < this.x1) && (newY > this.y1)) { // second quarter
			System.out.println("second");
			x = newX + r;
			y = newY - r;
		}
		else if ((newX < this.x1) && (newY < this.y1)) { // third quarter
			System.out.println("third");
			x = newX + r;
			y = newY + r;
		}
		else if ((newX > this.x1) && (newY < this.y1)) { // fourth quarter
			System.out.println("fourth");
			x = newX - r;
			y = newY + r;
		}
		else if (newX == this.x1 && newY == this.y1) { //on middle in Flat
			System.out.println("on middle in Flat");
			r = this.R / 2;
			x = newX + r;
			y = newY;
		}
		else if (newX == this.x1) { //x equals
			x = newX;
			System.out.println("x equals");
			if (newY > this.y1)
				y = newY - r;
			else
				y = newY + r;
		}
		else if (newY == this.y1) { //y equals
			y = newY;
			System.out.println("y equals");
			if (newX > this.x1)
				x = newX - r;
			else
				x = newX + r;
		}
		
		System.out.println("x = " + x + " y = " + y + " r = " + r);
	}
}

class FifaFafa {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		Flat fl = new Flat();
		double x2 = 0, y2 = 0;
		
		
		String[] str = in.nextLine().split(" ");
		if (Math.abs(Double.parseDouble(str[0])) >= 1 && 
				Math.abs(Double.parseDouble(str[0])) <= Math.pow(10, 5)) {
			fl.x1 = Math.abs(Double.parseDouble(str[1])); 
			fl.y1 = Math.abs(Double.parseDouble(str[2]));
			fl.R = Math.abs(Double.parseDouble(str[0]));
		}
		else {
			out.println("ERROR! 'R' must be >= 1 and <= 10^5");
			return;
		}
		if (Math.abs(Double.parseDouble(str[4])) > Math.pow(10, 5)) {
			out.println("ERROR! y2 must be <= 10^5");
			return;
		}
		else
			y2 = Double.parseDouble(str[4]);

		x2 = Math.abs(Double.parseDouble(str[3]));
		
		
		if (fl.inFlat(x2, y2) == true)
			fl.getAnswer(x2, y2);
		else {
			out.print("Fafa's out");
			System.out.println(fl.x1 + " " + fl.y1 + " " + fl.R);
		}
	}
}
