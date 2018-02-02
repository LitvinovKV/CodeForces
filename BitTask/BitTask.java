
import java.io.*;

class Bit {
	private String PathName = "/home/icqking/eclipse-workspace/CodeForces/BitTask/FileData.txt";
	private File f;
	private BufferedReader buf = null;
	private int CountSentenсes;
	private int Result;
	
	Bit() {
		this.Result = 0;
		setFile(this.PathName);
	}
	
	Bit(String newPathName) {
		this.Result = 0;
		setFile(newPathName);
	}
	
	private void setFile(String newPathName) {
		try {
			this.f = new File(newPathName);
			this.buf = new BufferedReader(new FileReader(this.f));
			String str = buf.readLine();
			if (str != null)
				this.setCountSentenses( Character.digit(str.charAt(0), 10) );
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - File notfount");
		} catch (IOException e) {
			System.out.println("ERROR - Input / Output " + e);
			
		}
	}
	
	private void setCountSentenses(int Count) {
		if (Count >= 1 && Count <= 150)
			this.CountSentenсes = Count;
		else {
			System.out.println("ERROR - enter Data! Count sentenses must be >= 1 and <= 150!");
			this.CountSentenсes = 0;
		}
	}
	
	protected void CalculateResult() {
		for (int i = 0; i < this.CountSentenсes; i++) {
			try {
				String str = this.buf.readLine();
				if (str != null) {
					if (str.charAt(0) == '-' || str.charAt(2) == '-')
						this.Result--;
					else
						this.Result++;
				}
			} catch (IOException e) {
				System.out.println("ERROR - Input / Output " + e);
			}
		}
	}
	
	public int getResult() {
		return this.Result;
	}
	
	public int getCountSentenses() {
		return this.CountSentenсes;
	}
	
	protected void finalize() {
		try {
			this.buf.close();
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
}

class BitTask {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		
		Bit bt = new Bit();
		bt.CalculateResult();
		pw.println("Count sentenсes = " + bt.getCountSentenses());
		pw.println("Result = " + bt.getResult());
	}
}
