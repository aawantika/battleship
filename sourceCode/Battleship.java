import java.util.*;

/**
 * Basic Battleship game using 2d arrays
 * Written in September 2012 for AP Computer Science
 */
public class Battleship {

	public static void main(String[] args) {
		do {
			String[] compcoor = new String[4];
			generateShip(compcoor);

			int[][] user = new int[8][8]; 
			int count = 0; 

			for (int i = 15; i>0; i--) {
				System.out.println("Keys: 0's represent empty, no fire; 1's represents empty, fired upon; 2's represent a hit.");
				System.out.print("\nYou have " + i + " tries left.\n");
				int x = getPlaceX();
				int y = getPlaceY();
				String userchoice = x + " " + y;
				int checkerShip = checkShip(compcoor, userchoice);

				if (user[x][y] == 1 || user[x][y] == 2) {
					System.out.println("\nRepeated coordinate. Try again.\n");
					i++;
				} else if (checkerShip== 1) {
					System.out.println("\nA HIT!\n");
					user[x][y] = 2;	
					count++;
				} else {
					user[x][y] = 1;
					System.out.println("\nYou missed :( Try again!\n");
				}

				for(int row=0; row<user.length; row++) {	
					for(int col=0; col<user[0].length; col++)
						System.out.print(user[row][col] + " ");
					System.out.println(" ");
				}

				if (count == 4)
					break;
			}

			if (count == 4) {
				System.out.println("\nYou win.");
			} else {
				System.out.println("\nYou lose.");
			}

		} while(repeat());
	}

	public static boolean repeat() {
		Scanner in = new Scanner(System.in);
		System.out.println("\nDo you wish to repeat the program?\n1. Yes. \n2. No.");
		int choice;
		do {
			choice = in.nextInt();
			if (choice < 1 || choice > 2)
				System.out.println("The number entered is invalid." +
					"\nDo you wish to repeat the program?\n1. Yes. \n2. No.");
		} while (choice < 1 || choice > 2);
		return (choice == 1);
	}

	// Gets the direction of X based on user's input
	public static int getPlaceX() {
		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter in a number from 1-8 for vertical orientation.");
		int x;
		do {
			x = in.nextInt();
			if (x < 1 || x > 8)
				System.out.println("The number entered is invalid." +
					"\nEnter in a number from 1-8.");
		} while (x < 1 || x > 8);
		return x-1;
	}

	// Gets the direction of Y based on user's input
	public static int getPlaceY() //MENU OR BUTTONS. DECIDE.
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in a number from 1-8 for horizontal orientation.");
		int y;
		do {
			y = in.nextInt();
			if (y < 1 || y > 8)
				System.out.println("The number entered is invalid." +
					"\nEnter in a number from 1-8.");
		} while (y < 1 || y > 8);
		return y-1;
	}

	// Randomly generates a ship
	// Makes sure the ship isn't out of bounds
	public static String[] generateShip(String[] compcoor) //COMPLETED.
	{
		double gridx = Math.random();
		int posx = (int)(gridx*8);
		while (posx>=5) {
			gridx = Math.random();
			posx = (int)(gridx*8);
		}    

		int[][] matrix = new int[8][8];
		double gridy = Math.random();
		int posy = (int)(gridy*8);
		while (posy>=5) {
			gridy = Math.random();
			posy = (int)(gridy*8);
		}

		int posy0 = posy;
		int posy1 = posy;
		int posy2 = posy;
		int posx0 = posx;
		int posx1 = posx;
		int posx2 = posx;

		double position = Math.random();
		int numgen =(int)(position*2);

		if (numgen == 0) {
			posx0 = posx0+1;
			posx1 = posx1+2;
			posx2 = posx2+3;         
		} else  {
			posy0 = posy0+1;
			posy1 = posy1+2;
			posy2 = posy2+3;
		} 

		String string1 = posy + " " + posx;
		String string2 = posy0 + " " + posx0;
		String string3 = posy1 + " " + posx1; 
		String string4 = posy2 + " " + posx2;

		compcoor[0] = string1;
		compcoor[1] = string2;
		compcoor[2] = string3;
		compcoor[3] = string4;

		return compcoor;
	}

	// Checks if the user-selected location is a hit or a miss
	public static int checkShip(String[] compcoor, String userchoice) {
		int tf = 0;

		for (int j = 0; j<4; j++) {
			if (compcoor[j].equals(userchoice)) {
				tf = 1;
				break;
			}
		}

		return tf;
	}	
}