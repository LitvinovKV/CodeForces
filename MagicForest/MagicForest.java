
import java.io.*;

class MagicForest {
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/MagicForest/FileData.txt";
	private int n;
	private int count = 0;
	
	MagicForest() {
		set_File(localPath);
	}
	
	MagicForest(String newPath) {
		set_File(newPath);
	}
	
	private void set_File(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String str = buff.readLine();
			if (str != null)
				set_n(Integer.parseInt(str));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems: " + e);
		}
	}
	
	private void set_n(int newN) {
		if (newN <= 1 || newN >= 2500) {
			System.out.println("ERROR! Parametr N must be >= 1 and <= 2500.");
			return;
		}
		this.n = newN;
	}
	
	protected void init_answer() {
		for (int i = 1; i <= this.n; i++) {
			for (int j = i; j <= this.n; j++) {
				for (int k = j; k <= this.n; k++) {
					if ( (this.xorABC(i, j, k) == true) && 
							(this.isTriangle(i, j, k) == true) ) {
						System.out.println("Correct: " + i + " " +
												" " + j + " " + k);
						count++;
					}
				}
			}
		}
	}
	
	protected boolean xorABC(int a, int b, int c) {
		return (a ^ b ^ c) == 0 ? true : false;
	}
	
	protected boolean isTriangle(int a, int b, int c) {
		if ((a + b <= c) || (a + c <= b) || (b + c <= a))
			return false;
		return true;
	}
	
	protected int get_n() {
		return this.n;
	}
	
	protected int get_count() {
		return this.count;
	}
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		MagicForest MF = new MagicForest();
		MF.init_answer();
		pw.println("Answer: " + MF.get_count());
	}
}
