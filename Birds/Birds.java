
import java.io.*;

class TreesAndImp {
	private Tree[] trees;
	private Imp imp;
	private int countPlusMana;
	private int callBirdMana;
	private int CountBirds;
	
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/Birds/FileData.txt";
	
	TreesAndImp() {
		set_File(localPath);
	}
	
	TreesAndImp(String newPath) {
		set_File(newPath);
	}
	
	private void set_File(String newPath) {
		CountBirds = 0;
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String[] str = buff.readLine().split(" ");
			if (str != null) {
				init_trees(Integer.parseInt(str[0]));
				init_manaPull(Integer.parseInt(str[1]));
				init_callBirdsMana(Integer.parseInt(str[2]));
				init_plusMana(Integer.parseInt(str[3]));
				init_treesData();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems : " + e);
		}
	}
	
	private void init_trees(int count) {
		if (count < 1 || count > Math.pow(10, 3)) {
			System.out.println("ERROR! Count trees must be >= 1 and <= 10^3");
			return;
		}
		trees = new Tree[count];
	}
	
	private void init_manaPull(int count) {
		if (count < 0 ) {
			System.out.println("ERROR! Mana size must be >= 0");
			return;
		}
		imp = new Imp(count);
	}
	
	private void init_callBirdsMana(int count) {
		this.callBirdMana = count;
	}
	
	private void init_plusMana(int count) {
		if (count > Math.pow(10, 9)) {
			System.out.println("ERROR! Count mana refresh must be <= 10^9");
			return;
		}
		this.countPlusMana = count;
	}
	
	private void init_treesData() throws IOException {
		String[] countBirdsOnTree = buff.readLine().split(" ");
		String[] countMana = buff.readLine().split(" ");
		for (int i = 0; i < this.trees.length; i++)
			trees[i] = new Tree(Integer.parseInt(countBirdsOnTree[i]), 
							Integer.parseInt(countMana[i]));
		sortTreesToMana();
	}
	
	protected void calculateAnswer() {
		for (int i = 0; i < trees.length; i++) {
			while (imp.get_Mana() >= trees[i].get_countMana() && 
					trees[i].get_CountBirds() != 0) {
				imp.minus_mana(trees[i].get_countMana());
				imp.upgrade_Mana(this.callBirdMana);
				trees[i].minus_CountBirds();
				CountBirds++;
			}
			imp.plus_mana(this.countPlusMana);
		}
	}
	
	private void sortTreesToMana() {
		for (int i = 0; i < trees.length; i++) {
			for (int j = trees.length - 1; j > i; j--) {
				if (trees[i].get_countMana() > trees[j].get_countMana()) {
					Tree tree_tmp = trees[i];
					trees[i] = trees[j];
					trees[j] = tree_tmp;
				}
			}
		}
	}
	
	protected int get_CountTrees() {
		return this.trees.length;
	}
	
	protected int get_manPullImp() {
		return this.imp.get_Mana();
	}
	
	protected int get_manaCostBirds() {
		return this.callBirdMana;
	}
	
	protected int get_manaRecoverImp() {
		return this.countPlusMana;
	}
	
	protected int get_answer() {
		return this.CountBirds;
	}
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
			trees = null;
			imp = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
}

class Birds {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		TreesAndImp TM = new TreesAndImp();
		pw.println("Count trees = " + TM.get_CountTrees());
		pw.println("Mana pull imp = " + TM.get_manPullImp());
		pw.println("Mana cost birds = " + TM.get_manaCostBirds());
		pw.println("Mana recorev imp = " + TM.get_manaRecoverImp());
		TM.calculateAnswer();
		pw.println("Birds count = " + TM.get_answer());
	}
}
