

public class StrictCoinGame extends AbstractGame {
    /**
     * Make a constructor for StrictCoinGame.
     *
     * @param board represent the board of StrictCoinGame.
     */
    public StrictCoinGame(String board) {
        super(board);
    }


    /**
     * Move the given coin to the new position under the rules of
     * StrictCoinGame.
     *
     * @param coinIndex   which coin to move
     * @param newPosition where to move it to
     *                    <p>
     *                    Precondition: Th new position of the coin can't be
     *                    smaller or equal than tne position of the coin which
     *                    on the left of this coin. The position we want to
     *                    put can't have coin. The index of the coin number
     *                    should be smaller than the coin number of coins.
     *                    The given coinIndex and new position
     *                    also can't be negative number. The coin can only
     *                    move to left. The coin can't move to its previous
     *                    position.
     */
    @Override
    public void move(int coinIndex, int newPosition) {
        StringBuilder a = new StringBuilder(this.board);
        int oldPosition = this.getCoinPosition(coinIndex);

        if (board == "") {
            throw new IllegalMoveException("Can't find the given " +
                    "coin index and new position");
        }
        if(coinIndex>1) {
            if (newPosition < this.getCoinPosition(coinIndex - 1)) {
                throw new IllegalMoveException("You can't only move to" +
                        " left position which is left to the previous coin");
            }
        }
        if (this.board.charAt(newPosition) == '0') {
            throw new IllegalMoveException("The new position has " +
                    "already be occupied");
        }

        if (this.coinCount() < coinIndex) {
            throw new IllegalMoveException("Can't find the given" +
                    " coinIndex");
        }
        if (newPosition > this.getCoinPosition(coinIndex)) {
            throw new IllegalMoveException("You can't move to right.");
        }
        if (newPosition == this.getCoinPosition(coinIndex)) {
            throw new IllegalMoveException("You don't move.");
        } else {

            remove(coinIndex,newPosition);
            put(coinIndex,newPosition);
        }


    }
}

