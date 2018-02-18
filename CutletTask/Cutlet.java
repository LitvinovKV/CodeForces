
import java.io.PrintWriter;
import java.util.Scanner;

class Cutlet {
	int count1 = 0, count2 = 0, answer = 0;
	int[] arrayTime;
	int time = 0, countTime = 0;
	boolean side = true;
	int[] AnswerArray = new int[100];
	int index = 0;
	
	protected boolean initTime(int count) {
		if (count >= 1 && count <= 100000)
			time = count;
		else {
			System.out.println("Time must be >= 1 and <= 100000");
			return false;
		}
		return true;
	}
	
	protected boolean initCountTime(int count) {
		if (count >= 1 && count <= 100)
			countTime = count;
		else {
			System.out.println("Time must be >= 1 and <= 100");
			return false;
		}
		initArrayTime();
		return true;
	}
	
	protected void initArrayTime() {
		arrayTime = new int[countTime * 2 + 1];
		arrayTime[0] = 0;
	}
	
	protected boolean initArrayValues(int left, int right, int index) {
		if (left >= 0 && right >= left && 2 * time >= right) {
			arrayTime[index] = left;
			arrayTime[index + 1] = right;
		}
		else {
			System.out.println("0 <= left <= right <= 2 * n");
			return false;
		}
		return true;
	}
	
	protected void answer() {
		for (int i = 0; i < arrayTime.length; i++) {
			if (arrayTime[i] == 0) {
				count1 = 0; side = true;
				count2 = 0; answer = 0;
			}
			else {
				count1 = arrayTime[i]; side = false;
				count2 = 0; answer = 1;
			}
//			System.out.print(arrayTime[i] + " : ");
			for (int j = i + 1; j < arrayTime.length; j++) {
				for (int k = j; k < arrayTime.length; k++) {
//					System.out.print("\t" + arrayTime[k]);
					if (side == true)
						count1 += arrayTime[k] - (count1 + count2);
					else
						count2 += arrayTime[k] - (count2 + count1);
					side = !side;
					answer++;
					//ANSWER
					if ((count1 == 10 && side == false) || 
						(count2 == 10 && side == true)) {
//						return answer;
						addToArray(answer);
						break;
					}
				}
				if (side == true) {
					if (count1 < time)
					count1 += time - count1;
				}
				else {
					if (count2 < time)
					count2 += time - count2;
				}
//				System.out.println("\ncount1 = " + count1 + " count2 = " + count2);
				// ANSWER
				if (count1 == time && count2 == time) {
					addToArray(answer);
//					return answer;
				}
				if (arrayTime[i] == 0) {
					count1 = 0; side = true;
					count2 = 0; answer = 0;
				}
				else {
					count1 = arrayTime[i]; side = false;
					count2 = 0; answer = 1;
				}
			}
//			System.out.println("----------");
		}
	}
	
	private void addToArray(int element) {
		AnswerArray[index] = element;
		index++;
	}
	
	protected int getMin() {
		int min = AnswerArray[0];
		for (int i = 1; i < AnswerArray.length; i++) {
			if (AnswerArray[i] == 0) break;
			if (AnswerArray[i] < min) min = AnswerArray[i];
		}
		return min;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		Cutlet cut = new Cutlet();
		
		String[] str = in.nextLine().split(" ");
		if (cut.initTime(Integer.parseInt(str[0])) == false)
			return;
		if (cut.initCountTime(Integer.parseInt(str[1])) == false)
			return;
		
		int index = 1;
		for (int i = 0; i < cut.countTime; i++) {
			str = in.nextLine().split(" ");
			if (cut.initArrayValues(Integer.parseInt(str[0]), 
								Integer.parseInt(str[1]), index) == false)
				return;
			index += 2;
		}
		
		cut.answer();
		int min = cut.getMin();
		if (min == 0) out.println("Hungry");
		else {
			out.println("Full");
			out.println(min);
		}
	}
}
