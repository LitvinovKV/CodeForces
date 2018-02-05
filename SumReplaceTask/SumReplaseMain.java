
import java.io.*;

class SumReplaseMain {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		SumReplase SR = new SumReplase();
		pw.println("Count Command = " + SR.get_CountCommand());
		pw.println("Count Elements = " + SR.get_CountElements());
		pw.println("ARRAY ELEMENTS: "); 
		SR.cout_Array();
		pw.println("Result SUMM:");
		SR.changeArray();
		pw.println("Changed Array:");
		SR.cout_Array();
	}
}
