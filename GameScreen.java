package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import java.util.*;

/**
 * description: class that contains main() and controls the game's flow.
 *
 * @author Alex Jorgensen
 * cpsc 2150
 * Project2: implementing extended ConnectX
 */
public class GameScreen {
    public static void main(String[] args) {
        // initializing required variables
        int MAX_ROWS = 100, MAX_COLUMNS = 100, MAX_NUM_TO_WIN = 25;
        int MIN_ROWS = 3, MIN_COLUMNS = 3, MIN_TO_WIN = 3;
        int num_rows = 0, num_columns = 0, num_to_win = 0, num_players = 0;
        String playerChoice;
        String gameType;
        boolean replay = true;
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);
        String input = " ";


        // printing introduction message and rules and prompting to play
        System.out.println("Welcome to ConnectX!");
        System.out.println("Each player will take turns choosing which column to place their token in.");
        System.out.println("The game is over when either the board is full and no player has won, " +
                            "or a player wins if they are the first to connect 5 of their tokens either horizontally, vertically, or diagonally.");
        System.out.println("Would you like to play? (Y/N)");
        input = sc.nextLine();

        // if statement for if they want to play
        if (input.equalsIgnoreCase("Y")){
            // while loop that controls replaying games
            while(replay){
                // getting number of players
                System.out.println("Great! How many players would you like this game to have? (Min 2, max 10)");
                num_players= sc.nextInt();
                    // error check
                while(num_players < 2 || num_players > 10){
                    System.out.println("Invalid number of players! Please enter a valid number. (Min 2, max 10)");
                    num_players = sc.nextInt();
                }
                input = sc.nextLine();
                // having players select their character
                System.out.println("Next, each player will choose what character they want to represent their tokens.");
                List<Character> players = new ArrayList<>(num_players);
                for (int i = 1; i <= num_players; ++i){
                    System.out.println("Player " + i + ", please enter a character to represent your tokens.");
                    playerChoice = sc.nextLine();
                        // error check
                    while (players.contains(playerChoice.toUpperCase().charAt(0))){
                        System.out.println("That character has already been selected. Please select an unused letter");
                        playerChoice = sc.nextLine();
                    }
                    players.add(playerChoice.toUpperCase().charAt(0));
                }

                // getting number of rows
                System.out.println("How many rows would you like the board to have? (Min 3, max 100)");
                num_rows = sc.nextInt();
                    // error check
                while(num_rows > MAX_ROWS || num_rows < MIN_ROWS){
                    System.out.println("Invalid number of rows! Please enter a valid number. (Min 3, max 100)");
                    num_rows = sc.nextInt();
                }
                // getting number of columns
                System.out.println("How many columns would you like the board to have? (Min 3, max 100)");
                num_columns = sc.nextInt();
                    // error check
                while(num_columns > MAX_COLUMNS || num_columns < MIN_COLUMNS){
                    System.out.println("Invalid number of columns! Please enter a valid number. (Min 3, max 100)");
                    num_columns = sc.nextInt();
                }
                // getting number to win
                System.out.println("How many tokens in a row should be required to win? (Min 3, max 25)");
                num_to_win = sc.nextInt();
                    // error check
                while(num_to_win > MAX_NUM_TO_WIN || num_to_win < MIN_TO_WIN || num_to_win > num_rows || num_to_win > num_columns){
                    System.out.println("Invalid number of tokens to win! Please enter a valid number. (Min 3, max 25)");
                    num_to_win = sc.nextInt();
                }
                // having players select fast or memory-efficient implementation
                System.out.println("Lastly, please select if you would like to play with the fast implementation (F/f) or the memory-efficient implementation (M/m).");
                gameType = sc.nextLine();
                gameType = sc.nextLine();
                while(!gameType.equalsIgnoreCase("F") && !gameType.equalsIgnoreCase("M")){
                    System.out.println("Invalid game type choice. Please enter either F/f for fast or M/m for memory-efficient.");
                    gameType = sc.nextLine();
                }

                IGameBoard Board = new GameBoard(num_rows, num_columns, num_to_win);

                // making gameboard into specified implementation
                if (gameType.equalsIgnoreCase("F")){
                    Board = new GameBoard(num_rows, num_columns, num_to_win);
                }
                else{
                    Board = new GameBoardMem(num_rows, num_columns, num_to_win);
                }

                int whoseTurn = 0;
                System.out.println(Board.toString());
                // while loop that controls players turns
                while (!gameOver) {
                        // while game has not ended, iterate through players turns
                        System.out.println("Player " + players.get(whoseTurn) + ", please choose which column to put a token in (0-" + (num_columns - 1) + ")");
                        input = sc.nextLine();
                        // error check
                        while(Integer.parseInt(input) < 0 || Integer.parseInt(input) > Board.getNumColumns()-1 || !Board.checkIfFree(Integer.parseInt(input))){
                            System.out.println("That column is full or is outside the bounds of the game. Please select a different column.");
                            input = sc.nextLine();
                        }
                        // place token and check for win and tie
                        Board.placeToken(players.get(whoseTurn), Integer.parseInt(input));
                        System.out.println(Board.toString());
                        if(Board.checkForWin(Integer.parseInt(input))){
                            System.out.println("Player " + players.get(whoseTurn) + " has connected " + Board.getNumToWin() + " tokens in a row and has won the game!");
                            gameOver = true;
                            break;
                        }
                        if(Board.checkTie()){
                            System.out.println("The game has resulted in a tie. No player won. Sad.");
                            gameOver = true;
                            break;
                        }
                        ++whoseTurn;
                        whoseTurn = whoseTurn%num_players;

                }
                // after game finishes, ask players to replay game
                System.out.println("Thank you for playing! Would you like to play again? (Y/N?)");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("Y")){
                    replay = true;
                    gameOver = false;
                }
                else{
                    System.out.println("Goodbye.");
                    replay = false;
                    gameOver = true;
                }
            }
        }
    }
}
