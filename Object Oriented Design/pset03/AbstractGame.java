/**
 * Create a abstract class which implements the interface and write
 * the common method covered by both subclasses.
 *
 *
 *
 *
 */

//Class Invariant: The board should only be consist of
//"-" ,"O" or Empty.
abstract class AbstractGame implements CoinGame {
    public String board;

    /**
     * Make an abstractGame a constructor.
     *
     * @param board represent the whole game board.
     */
    public AbstractGame(String board) {
        if (board == "") {
            this.board = board;
        }
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == 'O' || board.charAt(i) == '-') {

            } else throw new IllegalArgumentException("not valid");

        }


        this.board = board;


    }

    /**
     * Get the board represented string
     *
     * @return the represented string
     */
    @Override
    public String toString() {
        return this.board;
    }


    /**
     * Gets the size of the board (the number of squares)
     *
     * @return the board size
     */
    @Override
    public int boardSize() {
        return this.board.length();
    }

    /**
     * Gets the number of coins.
     *
     * @return the number of coins
     */
    @Override
    public int coinCount() {
        int number = 0;
        for (int i = 0; i < this.toString().length(); i++) {
            if (this.toString().substring(i, i + 1).equals("O"))
                number = number + 1;

        }
        return number;
    }

    /**
     * Gets the (zero-based) position of coin number {@code coinIndex}.
     *
     * @param coinIndex which coin to look up
     * @return the coin's position
     * <p>
     * <p>
     * <p>
     * Precondition: coinIndex should be smaller than coinCount
     */

    @Override
    public int getCoinPosition(int coinIndex) {
        if (coinIndex < this.coinCount()) {
            int a = -1;
            if (this.coinCount() - 1 >= coinIndex && coinIndex >= 0) {
                for (int i = 0; i < board.length(); i++) {
                    if (board.charAt(i) == 'O') {
                        a++;
                        if (a == coinIndex) {
                            return i;
                        }
                    }
                }
            }

        }
        throw new IllegalArgumentException("Can't find the given coin");

    }


    /**
     * Returns whether the current game is over. The game is over if there are
     * no valid moves.
     *
     * @return whether the game is over
     */


    @Override
    public boolean isGameOver() {

        int position = 1;
        String s;
        int coinNumber = this.coinCount();
        if (coinNumber == 0) return true;

        while (position <= coinNumber) {
            s = this.board.substring(position - 1, position);
            if (s.equals("O")) {
                position++;
            } else
                return false;
        }
        return true;

    }


    public void remove(int coinIndex, int newPosition) {
        int a = 0;
        String result = "";
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == 'O') {
                if (a == coinIndex) {
                    result = result + "-";
                    a = a + 1;
                }


            }
            result = result + board.charAt(i);
        }
        board=result;
    }

    /**
     * Return the new board which has already put the coin in.
     *
     * @param coinIndex   the coin index which I want to move.
     * @param newPosition the position which I want to put the coin in.
     *                    Precondition: The new position can't be occupied by the
     *                    existed coin.
     */
    public void put(int coinIndex, int newPosition) {
        String result = "";
        for (int i = 0; i < board.length(); i++) {
            if (i == newPosition) {
                result = result + "O";


            }
            result = result+board.charAt(i);

        }
        board=result;
    }

}








