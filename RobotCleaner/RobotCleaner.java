
import java.io.*;

class RobotCleaner {
	private File f = null;
	private BufferedReader buff = null;
	private StringH[] arrayString;
	private String t = "";
	private int countNoise;
	private int countString;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/RobotCleaner/FileData.txt";
	
	RobotCleaner() {
		set_File(localPath);
	}
	
	RobotCleaner(String newPath) {
		set_File(newPath);
	}
	
	private void set_File(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String str = buff.readLine();
			if (str != null)
				set_countString(Integer.parseInt(str));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems: " + e);
		}
	}
	
	private void set_countString(int count) {
		if (count >= 1 && count <= Math.pow(10, 5)) {
			this.countString = count;
			arrayString = new StringH[count];
			return;
		}
		System.out.println("ERROR! Count Strings must be >= 1 and <= 10 ^ 5");
	}
	
	protected void init_array() {
		for (int i = 0; i < this.countString; i++) {
			String str = "";
			try {
				str = buff.readLine();
			} catch (IOException e) {
				System.out.println("ERROR! I/O problems: " + e);
			}
			arrayString[i] = new StringH(str);
		}
	}
	
	protected StringH[] sort_arrayStringH(StringH[] array) {
		for (int i = 0; i < array.length; i++) {
			int new_index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[new_index].get_countH() > array[j].get_countH()) 
					new_index = j;
				else if (array[new_index].get_countH() == array[j].get_countH()) {
					if (array[new_index].get_countS() < arrayString[j].get_countS())
						new_index = j;
				}
			}
			StringH tmp = array[i];
			array[i] = array[new_index];
			array[new_index] = tmp;
		}
		
		for (int i = 0; i < array.length; i++)
			t += array[i].get_str();
		
		return array;
	}
	
	protected void set_countNoise() {
		for (int i = 0; i < t.length(); i++) {
			if (t.charAt(i) == 's') {
				for (int j = i + 1; j < t.length(); j++) {
					if (t.charAt(j) == 'h')
						countNoise++;
				}
			}
		}
	}
	
	protected int get_countStrings() {
		return this.countString;
	}
	
	protected int get_countNoise() {
		return this.countNoise;
	}
	
	protected String get_t() {
		return this.t;
	}
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
			arrayString = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		
		RobotCleaner RC = new RobotCleaner();
		pw.println("Count strings = " + RC.get_countStrings());
		RC.init_array();
		RC.arrayString = RC.sort_arrayStringH(RC.arrayString);
		pw.println("End line = " + RC.get_t());
		RC.set_countNoise();
		pw.println("Count noise = " + RC.get_countNoise());
	}
}
