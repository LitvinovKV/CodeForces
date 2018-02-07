
import java.io.*;

public class MHTHR {
	
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/Math.HurriesToTheRescue/FileData.txt";
	private int[] mass;
	
	public MHTHR() {
		set_File(localPath);
	}
	
	MHTHR(String newPath) {
		set_File(newPath);
	}
	
	private void set_File(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String str = buff.readLine();
			if (str != null)
				init_mass(str);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems : " + e);
		}
	}
	
	private void init_mass(String str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '+')
				length++;
		}
		mass = new int[++length];
		int index_i = 0;
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '+')
				result += str.charAt(i);
			else {
				mass[index_i++] = Integer.parseInt(result);
				result = "";
			}
		}
		mass[index_i] = Integer.parseInt(result);
	}
	
	protected void cout_Array() {
		System.out.println("Array:");
		for (int i = 0; i < mass.length; i++) {
			System.out.println(mass[i]);
		}
	}
	
	protected void sort_Array () {
		for (int i = 0; i < mass.length; i++) {
			for (int j = mass.length - 1; j > i; j--) {
				if (mass[j] < mass[j - 1]) {
					int tmp = mass[j];
					mass[j] = mass[j - 1];
					mass[j - 1] = tmp;
				}
			}
		}
	}
	
	protected String get_Summ() {
		String result = "";
		for (int i = 0; i < mass.length; i++)
			if ((i + 1) != mass.length) {
				result += String.valueOf(mass[i]);
				result += "+";
			}
			else
				result += String.valueOf(mass[i]);
		return result;
	}
	
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
			this.mass = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		MHTHR mh = new MHTHR();
		mh.cout_Array();
		mh.sort_Array();
		mh.cout_Array();
		pw.println("New Sum : " + mh.get_Summ());
	}
}
