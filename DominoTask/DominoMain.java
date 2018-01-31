import java.io.*;

class Domino {
	private int M,N;
	
	public Domino() {
		this.M = 0;
		this.N = 0;
	}
	
	public void size(int newM, int newN) {
		if ((newM >= 1) && (newN >= newM) && (newN <= 16)) {
			this.M = newM;
			this.N = newN;
		}
		else {
			System.out.println("Entered size is wrong! Need: 1 <= M <= N <= 16");
		}
	}
	
	public int getM() {
		return this.M;
	}
	
	public int getN() {
		return this.N;
	}
	
	public int CountDomino() {
		return this.M * this.N / 2;
	}
}

public class DominoMain {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		Domino dm = new Domino();
		dm.size(3, 5);
		pw.println("Domino count = " + dm.CountDomino());
	}
}
