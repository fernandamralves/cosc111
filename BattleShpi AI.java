//Fernanda Malheiros Rodrigues Alves
//Student ID#300326557
//Lab 8 - Battleship - Part II

class RandomFe {
    public static void  main(String[] args){
        char guesses[][] = new char[10][10]; //guesses

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                guesses[row][col] = '.';
            }

        }   

        //System.out.println(makeGuess(guesses));
    }

    public static String makeGuess(char[][] guesses) {
        int gameBoard[][] = new int [10][10]; //heapmap

        int r =0;
        int c = 0;
        isValid(r, c);
        int row1, col1;

        int highest = 0;
        int guessRow = 0;
        int guessCol = 0;

        //Creating the Heapmap
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                //Heapmap length 2 - Patrol Boat
                if ((guesses[row][col] == '.') && isValid(row, col+1) && (guesses[row][col +1] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row][col +1]++;
                } 
                if ((guesses[row][col] == '.') && isValid(row+1, col) && (guesses[row +1][col] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row +1][col]++;
                } 

                //Heapmap length 3 - Submarine
                if ((guesses[row][col] == '.') && isValid(row, col+1) && isValid(row, col+2) && (guesses[row][col +1] == '.') && (guesses[row][col +2] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row][col +1]++;
                    gameBoard[row][col +2]++;
                } 
                if ((guesses[row][col] == '.') && isValid(row+1, col) && isValid(row+2, col) && (guesses[row +1][col] == '.')  && (guesses[row +2][col] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row +1][col]++;
                    gameBoard[row +2][col]++;
                }

                //Heapmap length 3 - Destroyer
                if ((guesses[row][col] == '.') && isValid(row, col+1) && isValid(row, col+2) && isValid(row, col+3) && isValid(row, col+4) && (guesses[row][col +1] == '.') && (guesses[row][col +2] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row][col +1]++;
                    gameBoard[row][col +2]++;
                } 
                if ((guesses[row][col] == '.') && isValid(row+1, col) && isValid(row+2, col) && isValid(row+3, col) && isValid(row+4, col) && (guesses[row +1][col] == '.') && (guesses[row +2][col] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row +1][col]++;
                    gameBoard[row +2][col]++;
                }

                //Heapmap length 4 - Battleship
                if ((guesses[row][col] == '.') && isValid(row, col+1) && isValid(row, col+2) && isValid(row, col+3) && (guesses[row][col +1] == '.') && (guesses[row][col +2] == '.') && (guesses[row][col +3] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row][col +1]++;
                    gameBoard[row][col +2]++;
                    gameBoard[row][col +3]++;
                } 
                if ((guesses[row][col] == '.') && isValid(row+1, col) && isValid(row+2, col) && isValid(row+3, col) && (guesses[row +1][col] == '.') && (guesses[row +2][col] == '.') && (guesses[row +3][col] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row +1][col]++;
                    gameBoard[row +2][col]++;
                    gameBoard[row +3][col]++;
                }

                //Heapmap length 5 - Aircraft
                if ((guesses[row][col] == '.') && isValid(row, col+1) && isValid(row, col+2) && isValid(row, col+3) && isValid(row, col+4) && (guesses[row][col +1] == '.') && (guesses[row][col +2] == '.') && (guesses[row][col +3] == '.') && (guesses[row][col +4] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row][col +1]++;
                    gameBoard[row][col +2]++;
                    gameBoard[row][col +3]++;
                    gameBoard[row][col +4]++;
                } 
                if ((guesses[row][col] == '.') && isValid(row+1, col) && (guesses[row +1][col] == '.') && isValid(row+2, col) && isValid(row+3, col) && isValid(row+4, col) && (guesses[row +1][col] == '.') && (guesses[row +2][col] == '.') && (guesses[row +3][col] == '.') && (guesses[row +4][col] == '.')){
                    gameBoard[row][col]++;
                    gameBoard[row +1][col]++;
                    gameBoard[row +2][col]++;
                    gameBoard[row +3][col]++;
                    gameBoard[row +4][col]++;
                }
            }
        }

        //Finding the highest number on the Heapmap
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] > highest){
                    highest = gameBoard[i][j];
                    guessRow = i;
                    guessCol = j;
                }
            } 
        }
        
        //Printing to check if the highest is right
        // System.out.println();
        // System.out.println(highest);
        // System.out.println(guessRow);
        // System.out.println(guessCol);
        // System.out.println();

        char a = (char)((int)'A' + guessRow);
        String guess = a + Integer.toString(guessCol+1);

        //Printing the probability map
        //printArray(gameBoard);

        //Guessing around a Hit
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                if (guesses[row][col] == 'X' && col <= 10){
                    if (isValid(row, col+1) && guesses[row][col+1] == '.' ) {
                        char letter = (char)('A' + row);
                        String num = Integer.toString(col+1+1);
                        guess = letter + "" + num;  
                    }
                    if (isValid(row, col-1) && guesses[row][col-1] == '.' ) {
                        char letter = (char)('A' + row);
                        String num = Integer.toString(col);
                        guess = letter + "" + num;  
                    }
                    if (isValid(row+1, col) && guesses[row+1][col] == '.') {
                        char letter = (char)('A' + row+1);
                        String num = Integer.toString(col+1);
                        guess = letter + "" + num;  
                    }
                    if (isValid(row-1, col) && guesses[row-1][col] == '.' ) {
                        char letter = (char)('A' + row-1);
                        String num = Integer.toString(col+1);
                        guess = letter + "" + num;                        
                    }
                }
            } 
        }

        //System.out.println(guess); //Shows the guess of the turn
        
        return guess;
    } 

    public static int[][] printArray(int[][] gameBoard){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.printf("%3d ", gameBoard[i][j]);
            }
            System.out.println();
        }
        return(gameBoard);
    }

    public static boolean isValid(int r, int c) {
        //Preventing form going out of bounds
        return r >= 0 && r < 10 && c >= 0 && c < 10;
    }
}