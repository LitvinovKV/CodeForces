
import java.io.*;

class CavePainting {
	private File f;
	private BufferedReader buff;
	private String localFile = "/home/icqking/eclipse-workspace/CodeForces/CavePainting/FileData.txt";
	private int n, k;
	private int[] arrayValue;
	
	CavePainting() {
		set_File(localFile);
	}
	
	CavePainting(String newPath) {
		set_File(newPath);
	}
	
	private void set_File(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String[] strArray = buff.readLine().split(" ");
			if (strArray.length != 0) {
				set_n(Integer.parseInt(strArray[0]));
				set_k(Integer.parseInt(strArray[1]));
				init_array();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/o problems : " + e);
		}
	}
	
	private void set_n(int count) {
		if (count >= 1) {
			this.n = count;
			return;
		}
		System.out.println("ERROR! Wrong value 'n'! Must be >= 1.");
	}
	
	private void set_k(int count) {
		if (count <= Math.pow(10, 18)) {
			this.k = count;
			this.arrayValue = new int[k];
			return;
		}
		System.out.println("ERROR! Wrong value 'k'! Must be <= 10^18");
	}
	
	private void init_array() {
		for (int i = 1; i <= this.k; i++)
			arrayValue[i - 1] = this.n % i;
	}
	
	private String check_array() {
		for (int i = 0; i < arrayValue.length; i++) {
			for (int j = 0; j < arrayValue.length; j++) {
				if (i == j) continue;
				if (arrayValue[i] == arrayValue[j]) return "NO";
			}
		}
		return "YES";
	}
	
	protected void cout_array() {
		System.out.print("Array values = ");
		for (int i = 0; i < arrayValue.length; i++)
			System.out.print(arrayValue[i] + "\t");
		System.out.println();
	}
	
	protected int get_n() {
		return this.n;
	}
	
	protected int get_k() {
		return this.k;
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		CavePainting CP = new CavePainting();
		pw.println("N = " + CP.get_n());
		pw.println("K = " + CP.get_k());
		CP.cout_array();
		pw.println(CP.check_array());
	}
}
