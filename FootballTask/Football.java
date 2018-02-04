import java.io.*;

class Football {
	private String strPosition;
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/FootballTask/FileData.txt";
	
	Football() {
		setFile(this.localPath);
	}
	
	Football(String newPathName) {
		setFile(newPathName);
	}
	
	private void setFile(String PathName) {
		try {
			this.f = new File(PathName);
			this.buff = new BufferedReader(new FileReader(this.f));
			String str = buff.readLine();
			if (str != null)
				this.strPosition = str;
			else
				System.out.println("ERROR! FILE DATA NOT FOUND!");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! FILE NOT FOUND!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O stream - " + e);
		}
	}
	
	protected String checkPosition() {
		int count = 0;
		for (int i = 0; i < this.strPosition.length(); i++) {
			count++;
			for (int j = i + 1; j < this.strPosition.length(); j++) {
				if (this.strPosition.charAt(j) != this.strPosition.charAt(i))
					break;
				count++;
				if (count == 7)
					return "YES";
			}
			count = 0;
		}
		return "NO";
	}
	
	protected String getPosition() {
		return this.strPosition;
	}
}
