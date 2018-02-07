
import java.io.*;

class PS {
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/PeterAndStrings/FileData.txt";
	private String str1 = "";
	private String str2 = "";
	
	PS() {
		setFile(localPath);
	}
	
	PS(String newPath) {
		setFile(newPath);
	}
	
	private void setFile(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			set_String(buff.readLine(), 1);
			set_String(buff.readLine(), 2);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems: " + e);
		}
	}
	
	private void set_String(String newString, int numberString) {
		if (newString == null || newString == " " || newString.length() > 100)
			System.out.println("String shouldn't be empty and < 100 symbols!");
		else {
			if (numberString == 1)
				this.str1 = newString.toLowerCase();
			else
				this.str2 = newString.toLowerCase();
		}
	}
	
	protected String get_String1() {
		return this.str1;
	}
	
	protected String get_String2() {
		return this.str2;
	}
	
	protected int compareStrings() {
		for (int i = 0; i < this.str1.length(); i++) {
			if ( (int) str1.charAt(i) > (int) str2.charAt(i) )
				return 1;
			else if( (int) str1.charAt(i) < (int) str2.charAt(i) )
				return -1;
		}
		return 0;
	}
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
}

class PSMain {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		PS p = new PS();
		pw.println(p.get_String1());
		pw.println(p.get_String2());
		pw.println(p.compareStrings());
	}
}
