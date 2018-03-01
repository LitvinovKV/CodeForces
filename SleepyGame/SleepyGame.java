
import java.io.PrintWriter;
import java.util.Scanner;

enum State { WHITE, GRAY, BLACK }

class Vertex {
	int name;
	State CurrentState;
	Vertex[] childrenVertex;
	boolean hasChildren = false;
	int countHere, beforeCount;
	String beforeWay;
	
	Vertex(int newName) {
		this.CurrentState = State.WHITE;
		this.name = newName;
		countHere = 0;
		beforeCount = 0;
		beforeWay = "";
	}
	
	protected void setChildrensVertex(Vertex v) {
		if (childrenVertex != null) {
			Vertex[] newChildrensVertex = new Vertex[this.childrenVertex.length + 1];
			for (int i = 0; i < childrenVertex.length; i++)
				newChildrensVertex[i] = childrenVertex[i];
			newChildrensVertex[childrenVertex.length] = v;
			childrenVertex = newChildrensVertex;
		}
		else {
			hasChildren = true;
			childrenVertex = new Vertex[1];
			childrenVertex[0] = v;
		}
	}
	
	protected void coutChildrens() {
		if (hasChildren == false) {
			System.out.println("Vertex #" + name + " has not got any childrens.");
			return;
		}
		System.out.print("Childerens for " + this.name + " : ");
		for (int i = 0; i < childrenVertex.length; i++)
			System.out.print(childrenVertex[i].name + " \t");
		System.out.println();
	}
}

public class SleepyGame {
	Vertex[] allVertex;
	int n, m, count;
	String way;
	boolean flagEnd = false, countFlag = false;
	
	SleepyGame(int newN, int newM) {
		this.n = newN;
		this.m = newM;
		count = 0;
		way = "";
		allVertex = new Vertex[n];
		for (int i = 0; i < n; i++)
			allVertex[i] = new Vertex(i + 1);
	}
	
	protected void finalize()  {
		for (int i = 0; i < n; i++)
			allVertex[i] = null;
		allVertex = null;
	}
	
	protected void setAnswer(Vertex v) {
//		if (count == Math.pow(10, 6)) {
		if (count == 100) {
			flagEnd = true;
			return;
		}
		v.countHere++;
		way += v.name + " ";
		if (v.hasChildren == true) {
			for (int i = 0; i < v.childrenVertex.length; i++) {
				if (flagEnd == true) return;
//				if (v.childrenVertex[i].CurrentState == State.GRAY) {
//					if (!( v.childrenVertex[i].hasChildren == false && ((count % 2) == 0) )) break;
//				}
				for (int j = i + 1; j < v.childrenVertex.length; j++) {
					if ( (v.childrenVertex[j].hasChildren == false) && ((count & 1) == 0) )
						i = j;
				}
				for (int j = i + 1; j < v.childrenVertex.length; j++) {
					if (v.childrenVertex[j].countHere < v.childrenVertex[i].countHere) {
						v.childrenVertex[i].countHere = count;
						v.childrenVertex[i].beforeWay = way;
						count++;
						setAnswer(v.childrenVertex[j]);
						if (flagEnd == false) {
							count = v.childrenVertex[i].countHere;
							way = v.childrenVertex[i].beforeWay;
						}
						else return;
					}
				}
				count++;
				setAnswer(v.childrenVertex[i]);
				if (flagEnd == false) {
					count--;
					String[] answer = way.split(" ");
					way = "";
					for (int j = 0; j <= count; j++)
						way += answer[j] + " ";
				}
			}
//			v.CurrentState = State.GRAY;
		}
		else {
//			v.CurrentState = State.GRAY;
			if ((count & 1) == 1) flagEnd = true;
		}
		if (flagEnd == true) return;
		return;
	}
	
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		Scanner in = new Scanner(System.in);
		
		String[] str = in.nextLine().split(" ");
		if (!( Integer.parseInt(str[0]) >= 2 && Integer.parseInt(str[0]) <= Math.pow(10, 5) )) {
			System.out.println("ERROR! 'n' must be >= 2 and <= 10^5");
			return;
		}
		if (!( Integer.parseInt(str[1]) >= 0 && Integer.parseInt(str[1]) <= (2 * Math.pow(10, 5)) )) {
			System.out.println("ERROR! 'm' must be >= 2 and <= 10^5");
			return;
		}
		
		SleepyGame SG = new SleepyGame(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
		for (int i = 0; i < SG.n; i++) {
			str = in.nextLine().split(" ");
			int count = Integer.parseInt(str[0]);
			for (int j = 1; j <= count; j++) {
				SG.allVertex[i].setChildrensVertex(SG.allVertex[Integer.parseInt(str[j]) - 1]);
			}
		}
		
		for (int i = 0; i < SG.n; i++) {
			SG.allVertex[i].coutChildrens();
		}
		
		int start = Integer.parseInt(in.nextLine());
		SG.setAnswer(SG.allVertex[start - 1]);
		
		if (SG.flagEnd == true) {
//			if (SG.count != Math.pow(10, 6)) {
			if (SG.count != 100) {
				out.println("Win");
				out.println(SG.way);
			}
			else
				out.println("Draw");
		}
		else {
			out.println("Lose");
		}
	}
}
