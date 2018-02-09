
import java.io.*;

class ToyClone {
	
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/ToyClone/FileData.txt";
	private int needCountOriginals = 0, needCountCopy = 0;
	
	ToyClone() {
		setPath(localPath);
	}
	
	ToyClone(String newPath) {
		setPath(newPath);
	}
	
	private void setPath(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String str = buff.readLine();
			if (str != null)
				init_data(str);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems: " + e);
		}
	}
	
	private void init_data(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ')
				result += str.charAt(i);
			else {
				setCountCopy( Integer.parseInt(result) );
				result = "";
			}
		}
		setCountOriginal( Integer.parseInt(result) - 1 );
	}
	
	private void setCountCopy(int count) {
		if (count >= 0)
			this.needCountCopy = count;
		else
			System.out.println("ERROR! Count copy must be >= 0");
	}
	
	private void setCountOriginal(int count) {
		if (count <= Math.pow(10, 9))
			this.needCountOriginals = count;
		else
			System.out.println("ERROR! Count originals must be <= 10^9");
	}
	
	protected int getCountCopy() {
		return this.needCountCopy;
	}
	
	protected int getCountOriginals() {
		return this.needCountOriginals;
	}
	
	protected String getAnswer() {
		this.needCountCopy -= this.needCountOriginals;
		if (this.needCountCopy % 2 == 0)
			return "YES";
		else
			return "NO";
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
		ToyClone TC = new ToyClone();
		pw.println("Need count copy toys: " + TC.getCountCopy());
		pw.println("Need count original toys: " + TC.getCountOriginals());
		pw.println("Can you do it: " + TC.getAnswer());
	}
}
