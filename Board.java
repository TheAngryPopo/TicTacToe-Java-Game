import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {

    private char playerTurn = 'X';
    private int turnCount = 0; 
    private Scanner input = new Scanner(System.in);
    private int position;
    // array of the board
    static char[] board = {'1','|','2','|','3','\n','-','+','-','+','-','\n','4','|','5','|','6','\n','-','+','-','+','-','\n','7','|','8','|','9','\n'};
    static int[] PosToIndex = { -1, 0, 2, 4, 12, 14, 16, 24, 26, 28 };
    // the first element is -1 because it is irrelevent since the board starts at 1 not 0
    
    public void nextTurn() {
        // if it is X's turn
        if (playerTurn == 'X') {

            // make it is O's turn next
            playerTurn = 'O';

        } else { // if it is O's turn

            // make it X's turn next
            playerTurn = 'X';

        }
        turnCount++;
    }

    public void printBoard() {
        // print the board
        System.out.println(board);
    }

    private boolean validSquare() {
        
        if (position < 1 || position > 9) {
            return false; // it is an invalid position
        }

        if (board[PosToIndex[position]] == 'X' ||
            board[PosToIndex[position]] == 'O' ) {
            return false; // it is an invalid position
        }

        // since it is not an invalid position, it must be a valid one
        return true;
    }

    private void updateBoard() {
        board[PosToIndex[position]] = playerTurn;
    }

    private void getPosition() {
        try {
            position = input.nextInt();
        } catch( InputMismatchException e ) {
            input.nextLine();
        }
    }

    public void doTurn() {

        System.out.println("It is now " + playerTurn + "'s Turn");
        
        // asks for a position
        System.out.print("Enter a Position from 1 to 9: ");
        // get the input
        getPosition();
        

        // keep asking until the input is valid
        while (!validSquare()) {
            // ask for a valid position again
            System.out.print("Please enter a valid position: ");
            getPosition();
        }

        //System.out.println("The user position is "+position); //debug

        updateBoard(); // changes the position to an X or O
    }

    public boolean winner() {

        char winner = '?';
        for (int i = 1; i < 4; i++){
          if (board[PosToIndex[i]] == board[PosToIndex[i+3]] && board[PosToIndex[i+3]]== board[PosToIndex[i+6]]) {
            winner = board[PosToIndex[i]];
            break;
          }
        }
        if(winner == '?'){
          if (board[PosToIndex[1]] == board[PosToIndex[5]] && board[PosToIndex[5]] == board[PosToIndex[9]]) winner = board[PosToIndex[1]];
          if (board[PosToIndex[3]] == board[PosToIndex[5]] && board[PosToIndex[5]] == board[PosToIndex[7]]) winner = board[PosToIndex[3]];
        }
    
        if(winner == '?'){
          for (int i = 1; i <= 7; i+=3){
            if (board[PosToIndex[i]] == board[PosToIndex[i+1]] && board[PosToIndex[i+1]] == board[PosToIndex[i+2]]) {
              winner = board[PosToIndex[i]];
              break;
            }
          }
        }
        if (turnCount == 9) {
            System.out.println("The Game was a Tie!");
            return true;
        } else if (winner != '?'){
            System.out.println("The winner is " + winner +"!");
            System.out.println("Congratulations!");
            return true;
        }
        return false;
    }
}