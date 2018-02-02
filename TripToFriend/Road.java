
import java.io.*;

class Road {
	private int countTeleport;
	private int Dislocation;
	private Teleport[] tp;
	private File f = null;
	private String LocalPathName = "/home/icqking/eclipse-workspace/CodeForces/TripToFriend/FileData.txt";
	private BufferedReader buff = null;
	
	Road() {
		setFile(this.LocalPathName);
	}
	
	Road(String PathName) {
		setFile(PathName);
	}
	
	private void setFile(String newPath) {
		try {
			this.f = new File(newPath);
			this.buff = new BufferedReader(new FileReader(this.f));
			String str = buff.readLine();
			if (str != null) {
				setCountTeleport( Character.digit(str.charAt(0), 10) );
				setDislocation( Character.digit(str.charAt(2), 10) );
			}
			if (this.Dislocation != 0) {
				this.tp = new Teleport[this.countTeleport];
				for (int i = 0; i < this.countTeleport; i++) {
					try {
						String strRange = buff.readLine();
						this.tp[i] = new Teleport(Character.digit(strRange.charAt(0), 10), 
											Character.digit(strRange.charAt(2), 10));
					} catch (IOException e) {
						System.out.println("ERROR input/output file - " + e);
					}
				}
			}
			else {
				System.out.println("YES!");
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR open file!");
		} catch (IOException e) {
			System.out.println("ERROR input/output file - " + e);
		}
	}
	
	private void setCountTeleport(int newCount) {
		if (newCount >= 0 && newCount <= 100)
			this.countTeleport = newCount;
		else {
			System.out.println("ERROR entered range! Must be >= 0 and <= 100");
			this.countTeleport = 0;
		}
	}
	
	private void setDislocation(int newDislocation) {
		if (newDislocation >= 0 && newDislocation <= 100)
			this.Dislocation = newDislocation;
		else {
			System.out.println("ERROR entered dislocation range! Must be >= 0 and <= 100");
			this.Dislocation = 0;
		}
	}
	
	public void getAnswer() {
		if (this.Dislocation != this.tp[this.countTeleport - 1].getRight())
			System.out.println("NO");
		else {
			if (SearchWay() == true)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	private boolean SearchWay() {
		for (int i = this.countTeleport - 1; i > 0; i--) {
			if (!(this.tp[i].getLeft() >= this.tp[i - 1].getLeft() &&
					this.tp[i].getLeft() <= this.tp[i - 1].getRight()))
				return false; // NO
		}
		return true; // YES
	}
	
	public void showTelepots() {
		if (this.Dislocation == 0) return;
		for (int i = 0; i < this.countTeleport; i++)
			System.out.println("Left = " + this.tp[i].getLeft() + " " + 
								"Right = " + this.tp[i].getRight());
	}
	
	public int getDislocation() {
		return this.Dislocation;
	}
	
	public int getCountTeleports() {
		return this.countTeleport;
	}
}
