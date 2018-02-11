
import java.io.*;


class KefaPark {
	
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/KefaAndPark/FileData.txt";
	protected Vartex[] allVartex;
	private int countCat = 0;
	private int countRestaurant = 0;
	
	KefaPark() {
		set_File(localPath);
	}
	
	KefaPark(String newPath) {
		set_File(newPath);
	}
	
	private void set_File(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String[] str = buff.readLine().split(" ");
			if (str.length == 2) {
				set_Vartex(Integer.parseInt(str[0]));
				set_Cats(Integer.parseInt(str[1]));
				set_VartexWithCats();
				set_vartexRelations();
			}
			else
				System.out.println("ERROR! First line should consist of two numbers.");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found.");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems : " + e);
		}
	}
	
	private void set_Vartex(int count) {
		if (count <= 2 && count >= Math.pow(10, 5)) {
			System.out.println("ERROR! Count vartex must be >= 2 and <= 10^5");
			return;
		}
		this.allVartex = new Vartex[count];
		for (int i = 0; i < count; i++)
			allVartex[i] = new Vartex(String.valueOf(i + 1));
		System.out.println("Count vartex = " + count);
	}
	
	private void set_Cats(int count) {
		if (count <= 1 && count >= this.allVartex.length) {
			System.out.println("ERROR! Cats count must be >= 1 and <= Vartex count");
			return;
		}
		this.countCat = count;
		System.out.println("Count cats = " + count);
	}
	
	private void set_VartexWithCats() throws IOException {
		String[] str = buff.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals("1"))
				allVartex[i].cl = Color.RED;
			else if (str[i].equals("0") == false) {
				System.out.println("ERROR! Second line should consist to two values = 0 or 1.");
			}
		}
	}
	
	private void set_vartexRelations() throws IOException {
		String str;
		while ((str = buff.readLine()) != null) {
			String[] arrayStr = str.split(" ");
			int parentIndex = Integer.parseInt(arrayStr[0]) - 1;
			int childrenIndex = Integer.parseInt(arrayStr[1]) - 1;
			allVartex[parentIndex].children_Vartex = Vartex.add_newVartex(
							allVartex[parentIndex].children_Vartex,allVartex[childrenIndex]);
			allVartex[childrenIndex].parent_Vartex = Vartex.add_newVartex(
							allVartex[childrenIndex].parent_Vartex, allVartex[parentIndex]);
		}
		
		for (int i = 0; i < allVartex.length; i++) {
			allVartex[i].cout_childrens();
			allVartex[i].cout_parents();
		}
	}
	
	protected void calculateAnswer(Vartex v) {
		System.out.println("YOU ARE IN " + v.name);
		if (v.cl == Color.WHITE)
			v.cl = Color.GRAY;
		if ( v.checkRed_Parents(v, 0) > countCat)
			return;
		if (v.children_Vartex == null)
			countRestaurant++;
		else {
			for (int i = 0; i < v.children_Vartex.length; i++) {
				if (v.children_Vartex[i].cl != Color.GRAY)
					calculateAnswer(v.children_Vartex[i]);
			}
		}
		return;
	}
	
	protected int getAnswer() {
		return this.countRestaurant;
	}
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
			allVartex = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		KefaPark kf = new KefaPark();
		pw.println("-----------------------------");
		kf.calculateAnswer(kf.allVartex[0]);
		System.out.println("Count restarant = " + kf.getAnswer());
	}
}
