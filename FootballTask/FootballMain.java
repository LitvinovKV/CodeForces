
import java.io.*;

class FootballMain {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		Football fb = new Football();
		pw.println(fb.getPosition());
		pw.println(fb.checkPosition());
	}
}
