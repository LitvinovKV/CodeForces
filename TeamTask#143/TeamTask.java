
import java.io.*;

class TeamTask {
	
	private int numberTask = 0;
	private String fileName = "/home/icqking/eclipse-workspace/CodeForces/TeamTask#143/FileData.txt";
	private File f;
	private BufferedReader fin = null;
	protected PrintWriter pw = new PrintWriter(System.out, true);
	
	TeamTask() {
		this.setFile(this.fileName);
	}
	
	TeamTask(String newFileName) {
		this.setFile(newFileName);
	}
	
	private void setFile(String PathName) {
		try {
			this.f = new File(PathName);
			this.fin = new BufferedReader(new FileReader(this.f));
			String str  = this.fin.readLine();
			if (str != null ) 
				this.setCountTast( Character.digit(str.charAt(0), 10) ); //char -> int
		} catch (FileNotFoundException e) {
			pw.println("File not found!");
		} catch (IOException e) {
			pw.println("Error input - output : " + e);
		}
	}
	
	protected int getNumberTask() {
		return this.numberTask;
	}
	
	private void setCountTast(int newCount) {
		if ((newCount >= 1) && (newCount <= 1000))
			this.numberTask = newCount;
		else {
			this.pw.println("Count must be >= 1 and <= 1000");
			this.numberTask = 0;
		}
	}
	
	protected int getCountCompleteTask() {
		int count = 0;
		try {
			for (int i = 0; i < this.numberTask; i++) {
				String str = this.fin.readLine();
				if ( this.CountComplete(str) >= 2)
					count++;
			}
		} catch (IOException e) {
			pw.println("Error input - output : " + e);
		}
		return count;
	}
	
	private int CountComplete(String newStr) {
		int result = Character.digit(newStr.charAt(0), 10) + 
				Character.digit(newStr.charAt(2), 10) + 
				Character.digit(newStr.charAt(4), 10);
		return result;
	}
	
	protected void finalize() {
		try {
			this.fin.close();
		} catch (IOException e) {
			pw.println("Error close file!");
		}
	}
	
	
	
	public static void main(String[] args) {
		TeamTask tm = new TeamTask();
		tm.pw.println( tm.getCountCompleteTask() );
		
		
	}

}
