
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
	}
	
	protected void calculateAnswer() {
		for (int i = 0; i <= trees.length - 1; i++) {
			if (i == trees.length -1 ) {
				countBirds(i);
				continue;
			}
			boolean flag = false;
			int[] forwardTree = countBirdsOnTree(trees[i + 1], imp.get_Mana(), this.countPlusMana);
			int[] thisTree = countBirdsOnTree(trees[i], imp.get_Mana(), 0);
			if (thisTree[0] < forwardTree[0]) {
				if (trees[i].get_CountBirds() > 0 && imp.get_Mana() > trees[i].get_countMana()) {
					int for_forward = forwardTree[1] - countPlusMana;
					imp.minus_mana(for_forward);
					countBirds(i);
					imp.plus_mana(forwardTree[1]);
					flag = true;
				}
			}
			else if (thisTree[0] == forwardTree[0]) {
				if (thisTree[1] < forwardTree[1]) {
					if (trees[i].get_CountBirds() > 0 && imp.get_Mana() > trees[i].get_countMana())
						countBirds(i);
				}
				else if (imp.get_Mana() + this.countPlusMana > thisTree[1] + forwardTree[1])
					countBirds(i);
				else {
					int for_forward = forwardTree[1] - countPlusMana;
					imp.minus_mana(for_forward);
					countBirds(i);
					imp.plus_mana(forwardTree[1]);
					flag = true;
				}
			}
			else
				countBirds(i);
			if (flag == false) imp.plus_mana(this.countPlusMana);
		}
	}
	
	private void countBirds(int indexTree) {
		int n = trees[indexTree].get_CountBirds();
		for (int i = 0; i < n; i++) {
			if (imp.get_Mana() < trees[indexTree].get_countMana() 
					|| trees[indexTree].get_CountBirds() == 0)
				break;
			CountBirds++;
			trees[indexTree].minus_CountBirds();
			imp.minus_mana(trees[indexTree].get_countMana());
			imp.upgrade_Mana(callBirdMana);
		}
	}
	
	private int[] countBirdsOnTree(Tree Forward, int countMana, int plusMana) {
		if (countMana + plusMana > imp.maxLevelMana)
			countMana = imp.maxLevelMana;
		else
			countMana += plusMana;
		
		int BirdsMana = Forward.get_CountBirds() * Forward.get_countMana();
		
		if (BirdsMana > countMana) {
			int count = 0;
			for (int i = 0; i < Forward.get_CountBirds(); i++) {
				if (countMana < Forward.get_countMana() || Forward.get_CountBirds() == 0) break;
				count++;
				countMana -= Forward.get_countMana();
			}
			int[] answer = {count, count * Forward.get_countMana(),Forward.get_countMana() };
			return answer;
		}
		else {
			int[] answer = {Forward.get_CountBirds(), Forward.get_CountBirds() * Forward.get_countMana(),
					Forward.get_countMana()};
			return answer;
		}
		
	}
	
	protected void coutTrees() {
		for (int i = 0; i < trees.length; i++) {
			System.out.println("Tree #" + (i + 1));
			System.out.println("Count birds = " + trees[i].get_CountBirds());
			System.out.println("Count mana = " + trees[i].get_countMana());
			System.out.println("Summa = " + (trees[i].get_countMana() * trees[i].get_CountBirds()));
			System.out.println();
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
		TM.coutTrees();
		TM.calculateAnswer();
		pw.println("Birds count = " + TM.get_answer());
	}
}
