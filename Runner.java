public class Runner {

    public static void main(String[] args) {
        Board board = new Board();
        
        while (!board.winner()) {
            board.printBoard();
            board.doTurn();
            System.out.println("\n\n");

            board.nextTurn();
        }

        System.out.println("The Game is over, the final board is:");
        board.printBoard();
        System.exit(0);
    }

}