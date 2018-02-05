
import java.io.*;

class SumReplase {
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/SumReplaceTask/FileData.txt";
	private File f = null;
	private BufferedReader buff = null;
	private int[] mass;
	private int CountElements = 0;
	private int CountCommand = 0;
	private int l = 0;
	private int r = 0;
	
	
	SumReplase() {
		setFile(this.localPath);
	}
	
	SumReplase(String newPathConst) {
		setFile(newPathConst);
	}
	
	private void setFile(String newPath) {
		try {
			this.f = new File(newPath);
			this.buff = new BufferedReader(new FileReader(this.f));
			String str = new String(buff.readLine());
			if (str != null) {
				this.set_CountElements(Character.digit(str.charAt(0), 10));
				this.set_CountCommand(Character.digit(str.charAt(2), 10));
				str = buff.readLine();
				this.set_Array(str);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! FILE NOT FOUND!");
		} catch (Exception e) {
			System.out.println("ERROR! I/O STREAM : " + e);
		}
	}
	
	private void set_CountElements(int Count) {
		if (Count >= 1)
			this.CountElements = Count;
		else
			System.out.println("ERROR! COUNT ARRAY ELEMENTS MUST BE >= 1!");
	}
	
	private void set_CountCommand(int Count) {
		if (Count <= (3 * Math.pow(10, 5)))
			this.CountCommand = Count;
		else
			System.out.println("ERROR! COUNT COMMAND MUST BE <= 3 * 10^5!");
	}
	
	private void set_Array(String Elements) {
		mass = new int[this.CountElements];
		int indexArray = 0;
		String strElement = "";
		for (int i = 0; i < Elements.length(); i++) {
			if (Elements.charAt(i) != ' ')
					strElement += Elements.charAt(i);
			else {
				mass[indexArray] = Integer.parseInt(strElement);
				indexArray++;
				strElement = "";
			}
		}
		mass[indexArray] = Integer.parseInt(strElement); // don't miss last element!
	}
	
	protected void changeArray() {
		for (int i = 0; i < this.CountCommand; i++) {
			try {
				String str = new String(this.buff.readLine());
				if (str != null) {
					this.set_LeftRight(str.substring(2, str.length()));
					switch (str.charAt(0)) {
					case '1':
						this.ReplaceElements(this.l, this.r);
						break;
					case '2':
						this.SummEllements(this.l, this.r);
						break;
					default:
						System.out.println("ERROR! NUMBER COMMAND MUST BE 1 OR 2!");
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("ERROR! I/O STREAM : " + e);
			}
		}
	}
	
	private void ReplaceElements(int l, int r) {
		int count = 0;
		for (int i = l - 1; i < r; i++) {
			for (int j = 1; j <= this.mass[i]; j++) {
				if (this.mass[i] % j == 0)
					count++;
			}
			this.mass[i] = count;
			count = 0;
		}
//		this.cout_Array();
	}
	
	private void SummEllements(int l, int r) {
		int sum = 0;
		for (int i = l - 1; i < r; i++)
			sum += this.mass[i];
		System.out.println(sum);
	}
	
	private void set_LeftRight(String str) {
		String strLR = "";
		int left = 0;
		int right = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ')
				strLR += str.charAt(i);
			else {
				left = Integer.parseInt(strLR);
				strLR = "";
			}
		}
		right = Integer.parseInt(strLR);
		if (left >= 1 && right >= left && this.CountElements >= right) {
			this.l = left;
			this.r = right;
		}
		else
			System.out.println("ERROR! COMMAND RANGE MUST BE 1 <= left <= right <= SizeArray");
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
	
	protected int get_CountElements() {
		return this.CountElements;
	}
	
	protected int get_CountCommand() {
		return this.CountCommand;
	}
	
	
	protected void cout_Array() {
		for (int i = 0; i < this.CountElements; i++)
			System.out.print(this.mass[i] + " ");
		System.out.println();
	}
}