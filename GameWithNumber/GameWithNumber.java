
import java.io.*;

class GameWithNumber {
	private File f = null;
	private BufferedReader buff = null;
	private String localPath = "/home/icqking/eclipse-workspace/CodeForces/GameWithNumber/FileData.txt";
	private int GameCount = 0;
	private int[] AliceMass = new int[8];
	private int[] BobMass = new int[8];
	private boolean turn;
	
	GameWithNumber() {
		setFile(localPath);
	}
	
	GameWithNumber(String newPath) {
		setFile(newPath);
	}
	
	private void setFile(String newPath) {
		try {
			f = new File(newPath);
			buff = new BufferedReader(new FileReader(f));
			String str = buff.readLine();
			if (str != null)
				set_GameCount(Integer.parseInt(str));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found!");
		} catch (IOException e) {
			System.out.println("ERROR! I/O problems: " + e);
		}
	}
	
	protected void startGame() {
		if (GameCount == 0) {
			System.out.println("EROR! We can't play the game.");
			return;
		}
		
		for (int i = 0; i < GameCount; i++) {
			try {
				String str = buff.readLine();
				set_Turn(str);
				str = buff.readLine();
				set_PlayersCards(AliceMass, str);
				str = buff.readLine();
				set_PlayersCards(BobMass, str);
				CalculateWhoWin(AliceMass, BobMass);
			} catch (IOException e) {
				System.out.println("ERROR! I/O problems: " + e);
			}
		}
	}
	
	private void set_GameCount(int count) {
		if (count >= 1 && count <= 100000)
			this.GameCount = count;
		else
			System.out.println("ERROR! Game count must be >= 1 and <= 100 000.");
	}
	
	private void set_Turn(String str) {
		int x = Integer.parseInt(str);
		if (x == 1)
			this.turn = true;
		else if (x == 0)
			this.turn = false;
		else
			System.out.println("ERROR! Who turn must be = 0 or 1.");
	}
	
	private void set_PlayersCards(int[] player, String str) {
		String result = "";
		int index = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ')
				result += str.charAt(i);
			else {
				if (checkValueCard(result) == true)
					player[index] = Integer.parseInt(result);
				else {
					System.out.println("ERROR! Number card must be >= 0 and <= 4.");
				}
				index++;
				result = "";
			}
		}
		player[index] = Integer.parseInt(result);
	}
	
	private boolean checkValueCard(String str) {
		if (Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 4)
			return true;
		else
			return false;
	}
	
	private void CalculateWhoWin(int[] player1, int[] player2) {
		while (true) {
			if (countWinner(AliceMass) == true) {
				System.out.println("Alice Winner!");
				return;
			}
			else if (countWinner(BobMass) == true) {
				System.out.println("Bob Winner!");
				return;
			}
			
			if (this.turn == false) 			// Alice turn
				changeCard(AliceMass, BobMass);
			else  								// Bob turn
				changeCard(BobMass, AliceMass);
			
			this.turn = !this.turn;
		}
	}
	
	private void changeCard(int[] whoTurnPayer, int[] secondPlayer) {
		int indexWho = -1, indexSecond = -1;
		
		indexWho = findCardNumber(whoTurnPayer, 4);
		indexSecond =  findCardNumber(secondPlayer, 1);
		if (indexSecond != -1 && indexWho != -1) {
			whoTurnPayer[indexWho] = (whoTurnPayer[indexWho] + secondPlayer[indexSecond]) % 5;
			return;
		}
		
		indexWho = findCardNumber(whoTurnPayer, 1);
		indexSecond =  findCardNumber(secondPlayer, 4);
		if (indexSecond != -1 && indexWho != -1) {
			whoTurnPayer[indexWho] = (whoTurnPayer[indexWho] + secondPlayer[indexSecond]) % 5;
			return;
		}
		
		indexWho = findFirstCard(whoTurnPayer);
		indexSecond = findFirstCard(secondPlayer);
		whoTurnPayer[indexWho] = (whoTurnPayer[indexWho] + secondPlayer[indexSecond]) % 5;
	}
	
	private int findCardNumber(int[] player, int Number) {
		for (int i = 0; i < player.length; i++) {
			if (player[i] == Number)
				return i;
		}
		return -1;
	}
	
	private int findFirstCard(int[] player) {
		for (int i = 0; i < player.length; i++)
			if (player[i] != 0)
				return i;
		return 0;
	}
	
	private boolean countWinner(int[] player) {
		for (int i = 0; i < player.length; i++) {
			if (player[i] != 0)
				return false;
		}
		return true;
	}
	
	protected int getCountGame() {
		return this.GameCount;
	}
	
	protected void coutPlayer(int[] player) {
		for (int i = 0; i < 8; i++) {
			System.out.print(player[i] + "\t");
		}
		System.out.println();
	}
	
	protected void finalize() {
		try {
			this.buff.close();
			f = null;
			AliceMass = null;
			BobMass = null;
		} catch (IOException e) {
			System.out.println("ERROR - close file!");
		}
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, true);
		GameWithNumber GM = new GameWithNumber();
		pw.println("Count game = " + GM.getCountGame());
		GM.startGame();
	}
}
