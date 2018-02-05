
import java.io.*;

class ListNumber {
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = new String("/home/icqking/eclipse-workspace/CodeForces/NumberList/FileData.txt");
	private int countCommand = 0;
	private int x = 0, p = 0, k = 0;
	
	ListNumber() {
		setFile(localPath);
	}
	
	ListNumber(String newPath) {
		setFile(newPath);
	}
	
	private void setFile(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(this.f));
			String str = new String(buff.readLine());
			if (str != null)
				this.set_CountCommand(Integer.parseInt(str));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! FILE NOT FOUND!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O PROBLEM : " + e);
		}
	}
	
	private void set_CountCommand(int Count) {
		if (Count >= 1 && Count <= 30000)
			this.countCommand = Count;
		else
			System.out.println("ERROR! COUNT COMMAND MUST BE >= 1 AND <= 30000");
	}
	
	protected void setCommand() {
		for (int i = 0; i < this.countCommand; i++) {
			try {
				String str = buff.readLine();
				if (str != null)
					if (this.set_XPK(str) == true)
						this.getNumber();
					else continue;
			} catch (IOException e) {
				System.out.println("ERROR! I/O PROBLEM : " + e);
			}
		}
	}
	
	private void getNumber() {
		int count = 0, allCount = 0;
		int start = this.x + 1;
		while (true) {
			for (int i = 1; i <= getMin(start, this.p); i++) {
				if ((this.p % i == 0) && (start % i == 0))
					count++;
			}
			if (count > 1 || count == 0) {
				start++;
				count = 0;
			}
			else
				allCount++;
			if (allCount == this.k) {
				System.out.println(start);
				return;
			}
		}
	}
	
	private int getMin(int x, int y) {
		return (x < y) ? x : y;
	}
	
	private boolean set_XPK(String str) {
		String result = "";
		int local_x = 0, local_p = 0, local_k = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ')
				result += str.charAt(i);
			else {
				if (local_x == 0) local_x = Integer.parseInt(result);
				else local_p = Integer.parseInt(result);
				result = "";
			}
		}
		local_k = Integer.parseInt(result);
		if (local_x >= 1 && k <= Math.pow(10, 6)) {
			this.x = local_x;
			this.p = local_p;
			this.k = local_k;
			return true;
		}
		else {
			System.out.print("ERROR! 'X' Must be >= 1 AND 'K' Must be <= 10^6");
			return false;
		}
	}
	
	
	
	protected int get_CountCommand() {
		return this.countCommand;
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