
import java.io.*;

class ListNumberMain {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out,true);
		ListNumber LN = new ListNumber();
		pw.println("Count command = " + LN.get_CountCommand());
		pw.println("List Number:");
		LN.setCommand();
	}
}
