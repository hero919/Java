

public final class LaxCoinGame extends AbstractGame {
    /**
     * Make a constructor for LaxCoinGame.
     *
     * @param board Represent the board of LaxCoinGame.
     */
    public LaxCoinGame(String board) {
        super(board);
    }


    /**
     * Move the given coin to the new position under the rules
     * of LaxCoinGame.
     *
     * @param coinIndex   which coin to move
     * @param newPosition where to move it to
     *                    Precondition: One square can't be occupied by more than one coin
     *                    The given index can't be greater or equal than
     *                    the number of coins.The given coinIndex and new position
     *                    also can't be negative number. The coin can only move to left.
     *                    The coin can't move to its previous position.
     */

    @Override
    public void move(int coinIndex, int newPosition) {
        StringBuilder sb = new StringBuilder(this.board);

        if (this.board.charAt(newPosition) == 'O') {
            throw new IllegalMoveException("The new position has already be occupied");
        }
        if (this.coinCount() <= coinIndex) {
            throw new IllegalMoveException("Can't find the given coinIndex");
        }
        if (newPosition > this.getCoinPosition(coinIndex)) {
            throw new IllegalMoveException("You can't move to right.");
        }
        if (newPosition == this.getCoinPosition(coinIndex)) {
            throw new IllegalMoveException("You don't move.");
        }



        else {

            remove(coinIndex,newPosition);
            put(coinIndex,newPosition);
        }


    }
}



		