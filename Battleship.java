//author: aawantika sahu
//last update: 9/10/12 
//what: battleship

   import java.util.*;

    public class Battleship
   {
       public static void main(String[] args)
      {
         do 
         {
         //SHIPPING.
            String[] compcoor = new String[4];
            generateShip(compcoor);
         
         //USER,COUNT
            int[][] user = new int[8][8]; 
            int count = 0; 
         
         //REPEAT 15 TIMES
            for (int i = 15; i>0; i--)
            {
               System.out.println("Keys: 0's represent empty, no fire; 1's represents empty, fired upon; 2's represent a hit.");
               System.out.print("\nYou have " + i + " tries left.\n");
               int x = getPlaceX();
               int y = getPlaceY();
               String userchoice = x + " " + y;
               int checkerShip = checkShip(compcoor, userchoice);
            		 
               if (user[x][y] == 1 || user[x][y] == 2)
               {
                  System.out.println("\nRepeated coordinate. Try again.\n");
                  i++;
               } 	
               else if (checkerShip== 1)
               {
                  System.out.println("\nA HIT!\n");
                  user[x][y] = 2;	
                  count++;
               }
               else
               {
                  System.out.println("\nYou missed :( Try again!\n");
                  user[x][y] = 1;
               }
            
               for(int row=0; row<user.length; row++)
               {	
                  for(int col=0; col<user[0].length; col++)
                     System.out.print(user[row][col] + " ");
                  System.out.println(" ");
               }
            
               if (count == 4)
                  break;
            }
            if (count == 4)
               System.out.println("\nYou win.");
            else
               System.out.println("\nYou lose. Don't play again.");
         } while(repeat());
      
      }
   //************************************************************************************
   //method: repeat()
   //purpose: asks the user if he or she wants to repeat the program again after the program has run once
   //			  also error checks if the user inputs in a value that's not one or two
   //precondition: none
   //postcondition: a boolean statement will be returned
   //************************************************************************************
       public static boolean repeat()
      {
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
   
   //************************************************************************************
   //method: getPlaceX
   //purpose: asks the user for an x coordinate to fire
   //precondition: none
   //postcondition: a int will be returned
   //************************************************************************************
       public static int getPlaceX() //MENU OR BUTTONS. DECIDE.
      {
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
   	 
   //************************************************************************************
   //method: getPlaceY
   //purpose: asks the user for an y coordinate to fire
   //precondition: none
   //postcondition: a int will be returned
   //************************************************************************************
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
   	
   //************************************************************************************
   //method: generateShip
   //purpose: generates a random ship that doesn't allow for clustering or repeat coordinates; 
   //			  sends back four coordinates that are in an arrya
   //precondition: a string array that is four boxes long
   //postcondition: the said array with four different coordinates of the ship
   //************************************************************************************
       public static String[] generateShip(String[] compcoor) //COMPLETED.
      {
         double gridx = Math.random();
         int posx = (int)(gridx*8);
			//works if you multiply by 6 instead of eight
         while (posx>=5)
         {
            gridx = Math.random();
            posx = (int)(gridx*8);
         }    
        
         int[][] matrix = new int[8][8];
         double gridy = Math.random();
         int posy = (int)(gridy*8);
			//works if you multiply by 6 instead of eight// 
         while (posy>=5)
         {
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
      
         if (numgen == 0) //HORIZONTAL
         {
            posx0 = posx0+1;
            posx1 = posx1+2;
            posx2 = posx2+3;         
         }
         else //VERTICAL
         {
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
   	
   //************************************************************************************
   //method: checkShip
   //purpose: the coordinates that the user inputs in, this method checks it with the ship coordinates 
   //precondition: a string of the user's choice and the string array containing ship coordinates
   //postcondition: if ship coordinate matches user coordinate, returns a 1 else returns 0
   //************************************************************************************      
       public static int checkShip(String[] compcoor, String userchoice)
      {
         int tf = 0;
      	
         for (int j = 0; j<4; j++)
         {
            if (compcoor[j].equals(userchoice))
            {
               tf = 1;
               break;
            }
         }
         return tf;
      }	
   }