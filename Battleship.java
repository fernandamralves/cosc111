//Fernanda Malheiros Rodrigues Alves
//Student ID#300326557
import java.util.Scanner;

public class Lab7Battleship {
    public static void main(String[] args) {

        int size = 6;
        int[][] ships = new int[size][size]; //Ships Location - don't show user
        char[][] hits = new char[size][size]; //User Hits

        int countPatrol = 0; // To keep track of the hits on the Patrol Boat
        int countDestroyer = 0; // To keep track of the hits on the Destroyer
        int countBattleship = 0; // To keep track of the hits on Battleship
        int countTotal = 0; // To keep track of the total hits
        int rowLetter = 0;
        int colNumber = 0;
        char letter = (char)65;
        String guess = "";

        ships [0][0] = 2;
        ships [1][0] = 2;
        ships [1][4] = 3;
        ships [2][4] = 3;
        ships [3][4] = 3;
        ships [5][2] = 4;
        ships [5][3] = 4;
        ships [5][4] = 4;
        ships [5][5] = 4;

        //Initializing the hits board to be a wave
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                hits[row][col] = '~';
            }
        }
        
        // Explaining the game
        System.out.println("Your mission, should you choose to accept it, is to defeat Ken's army.\nTo do so, you must sink all of his Marine force: one Patrol Boat, one Destroyer and one Battleship.\nEven thought his army is small, it is on our way to conquer the title of best 111 class.\nAs always, should you or any of your team members be caught or killed, the Board will disavow any knowledge of your actions.\nThis message will self-destruct itself after the mission is completed.\nGood luck!\n");
        
        printUserBoard(size,hits,letter);
        //printBoardAndShips(size,ships, letter); -> shall not be printed for the user. It's here for checking if the game is correct.
        GuessesAndHits(ships, hits, countTotal, guess, rowLetter, colNumber, countPatrol,  countDestroyer, countBattleship, size, letter);

    }
    
    public static void printBoardAndShips(int size, int[][] ships, char letter) {
        // Print board with ships in it -> Don't show the user
        System.out.println("            SHIPS PLACEMENT   ");
        System.out.println("    1     2     3     4     5     6  ");
        System.out.println("  -------------------------------------");
        for (int row = 0; row < size; row++) {
            System.out.print(letter++);
            for (int col = 0; col < size; col++) {
                System.out.print(" |  " + ships[row][col] + " ");
            }
            System.out.println(" |");
            System.out.println("  -------------------------------------");
        }

    }

    public static void printUserBoard(int size, char[][] hits, char letter) {
        // Print empty board - user guesses will be added
        System.out.println("        YOUR ATTACKS ");
        System.out.println("    1   2   3   4   5   6");
        System.out.println("   === === === === === ===");
        
        for (int row = 0; row < size; row++) {
            System.out.print(letter++ + " ");
            for (int col = 0; col < size; col++) {
                System.out.print("|~" + hits[row][col] + "~");
            }
            System.out.println("|");
            //System.out.println("  -------------------------------------");
        }
    }

    public static void GuessesAndHits(int[][] ships, char[][] hits, int countTotal, String guess, int rowLetter, int colNumber, int countPatrol,  int countDestroyer, int countBattleship, int size, char letter){
        while (countTotal < 9){
            System.out.print("\nPlease enter a guess in the form 'B5': ");
            Scanner input = new Scanner(System.in);
            guess = input.nextLine();
            guess = guess.toUpperCase();
            System.out.println();
            char rowLetter1 = guess.charAt(0);
            rowLetter = rowLetter1 - 'A'; //Guessed row 
            char colNumber1 = guess.charAt(1);
            colNumber = colNumber1 - '1'; // Guesses column
            
            if(hits[rowLetter][colNumber] == 'X' || hits[rowLetter][colNumber] == 'O'){
                System.out.println("You have already send a missil in this direction. Try again and don't fail us.\n");
            } else if (ships[rowLetter][colNumber] == 2) {
                countPatrol++;
                countTotal++;
                hits[rowLetter][colNumber] = 'X';
                System.out.println(rowLetter1 + "" + colNumber1 + " is a hit.");
            } else if(ships[rowLetter][colNumber] == 3){
                countDestroyer++;
                countTotal++;
                hits[rowLetter][colNumber] = 'X';
                System.out.println(rowLetter1 + "" + colNumber1 + " is a hit");
            } else if(ships[rowLetter][colNumber] == 4){
                countBattleship++;
                countTotal++;
                hits[rowLetter][colNumber] = 'X';
                System.out.println(rowLetter1 + "" + colNumber1 + " is a hit");
            } else{
                hits[rowLetter][colNumber] = 'O';
                System.out.println(rowLetter1 + "" + colNumber1 + " is a miss");
            }
            printUserBoard(size,hits,letter);

            if (countPatrol == 2) {
                System.out.println("You sunk Patrol Boat! You are one step closer to defeat Ken's army.");
                System.out.println();
                countPatrol = 0;
            } else if (countDestroyer == 3) {
                System.out.println ("You sunk the Destroyer! You are one step closer to defeat Ken's army.");
                System.out.println();
                countDestroyer = 0;
            } else if (countBattleship == 4){
                System.out.println("You sunk Battleship! You are one step closer to defeat Ken's army.");
                System.out.println();
                countBattleship = 0;
            }
        }
        System.out.println("Congratulations!\nYou defeated Ken's army and helped us obtain the title of best 111 class!");
    }
}