
import java.io.*;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TypeHost;

import sun.nio.cs.ext.TIS_620;

class Taxi {
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/TaxiTask/FileData.txt";
	int count_4 = 0, count_3 = 0, count_2 = 0, count_1 = 0;
	int GroupCount = 0;
	int TaxiCount = 0;
	
	
	Taxi() {
		set_Path(localPath);
	}
	
	Taxi(String newPath) {
		set_Path(newPath);
	}
	
	private void set_Path(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String str = buff.readLine();
			if (str != null) {
				set_GroupCount(str);
				set_data(buff.readLine());
				calculateTaxiCount();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems : " + e);
		}
	}
	
	private void set_GroupCount(String str) {
		int count = Integer.parseInt(str);
		if (count >= 1 && count <= Math.pow(10, 5))
			this.GroupCount = count;
		else
			System.out.println("ERROR! Group count must be >= 1 and <= 10^5");
	}
	
	protected int get_GroupCount() {
		return this.GroupCount;
	}
	
	private void set_data(String str) {
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case '1':
				this.count_1++;
				break;
			case '2':
				this.count_2++;
				break;
			case '3':
				this.count_3++;
				break;
			case '4':
				this.count_4++;
				break;
			case ' ':
				break;
			default:
				System.out.println("ERROR! Don't correct symbol!");
				break;
			}
		}
	}
	
	
	private void calculateTaxiCount() {
		TaxiCount += count_4;
		if (count_3 >= count_1) {
			TaxiCount += count_3;
			count_1 = 0;
		}
		else {
			TaxiCount += count_3;
			count_1 -= count_3;
		}
		TaxiCount += count_2 / 2;
		count_2 %= 2;
		TaxiCount += count_1 / 4;
		count_1 %= 4;
		if (count_1 != 0) {
			if (count_2 == 1 && count_1 == 3)
				TaxiCount += 2;
			else
				TaxiCount++;
		}
		if (count_2 != 0)
			TaxiCount++;
	}
	
	protected int get_CountTaxi() {
		return this.TaxiCount;
	}
	
	protected int get_TaxiCount() {
		return this.TaxiCount;
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
		Taxi t = new Taxi();
		pw.println(t.get_CountTaxi());
	}
}
