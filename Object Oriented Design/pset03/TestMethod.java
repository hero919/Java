

	import static org.junit.Assert.assertEquals;

	import org.junit.Test;

    /**
     * Create a Test class for the methods.
     */
	public class TestMethod {
	CoinGame g1= new LaxCoinGame("--------");
	CoinGame g2= new LaxCoinGame("OOOOOOOO");
	CoinGame g3= new LaxCoinGame("-O--O--O-O");
	CoinGame g4= new StrictCoinGame("---------");
	CoinGame g5= new StrictCoinGame("OOOOOOOOO");
	CoinGame g6= new StrictCoinGame("-OO-O----O");	
	CoinGame g7= new LaxCoinGame("-O--O-OO--");
    CoinGame g8 = new LaxCoinGame("-OO-");
    CoinGame g9= new LaxCoinGame("OO--");
    CoinGame g10= new StrictCoinGame("-O--O-");
    CoinGame g11= new StrictCoinGame("");
    CoinGame g12= new LaxCoinGame("");
    CoinGame g13= new StrictCoinGame("");
    CoinGame g14= new StrictCoinGame("-O-O--");
    CoinGame g15= new LaxCoinGame("-OO-O----O");
    CoinGame g16= new StrictCoinGame("OO----");


        /**
         * Test toString method.
         */
    @Test
    public void toStringTest(){
        assertEquals("--------",g1.toString());
        assertEquals("OOOOOOOO",g2.toString());
        assertEquals("-O--O--O-O",g3.toString());
        assertEquals("-OO-O----O",g6.toString());
        assertEquals("-OO-",g8.toString());

        }

        /**
         * Test boardSize method.
         */
	@Test
	public void boardSizeTest(){
			assertEquals(8, g1.boardSize());
			assertEquals(8, g2.boardSize());
			assertEquals(9, g4.boardSize());
			assertEquals(10, g3.boardSize());
			assertEquals(9, g5.boardSize());
			assertEquals(10, g6.boardSize());
		}


        /**
         * Test coinCount method.
         */
	@Test
	public void coinCountTest(){
		assertEquals(0, g1.coinCount());
		assertEquals(8, g2.coinCount());
		assertEquals(0, g4.coinCount());
		assertEquals(4, g3.coinCount());
		assertEquals(9, g5.coinCount());
		assertEquals(4, g6.coinCount());
        assertEquals(0,g11.coinCount());
        assertEquals(0,g12.coinCount());
        assertEquals(0,g13.coinCount());
	}

        /**
         * Test getCoinPosition method.
         */
	@Test
	public void getCoinPositionTest(){
		assertEquals(2, g2.getCoinPosition(2));
		assertEquals(3, g2.getCoinPosition(3));
        assertEquals(2, g6.getCoinPosition(1));
        assertEquals(1, g7.getCoinPosition(0));
		
	}

        /**
         * Test the error of the conflicts.
         */
	@Test (expected=IllegalArgumentException.class)
	public void checkError(){
		g1.getCoinPosition(2);
		g2.getCoinPosition(13);
        g13.getCoinPosition(2);
        g7.move(3,7);
        g6.move(2,2);
        g6.move(20,2);
        g6.move(4,0);
        g3.move(1,1);
        g8.move(30,1);
        g5.move(3,3);
        g6.move(2,6);
        g7.move(1,5);
        g6.move(1,1);
        g7.move(1,1);
        g11.move(2,3);
        g12.move(3,4);

	}


        /**
         * Test whether the game is over.
         */
   @Test
	public void isGameOverTest(){
		assertEquals(true, g1.isGameOver());
		assertEquals(true, g2.isGameOver());
		assertEquals(false, g3.isGameOver());
		assertEquals(true, g4.isGameOver());
		assertEquals(true, g5.isGameOver());
		assertEquals(false, g6.isGameOver());
        assertEquals(true,g11.isGameOver());
        assertEquals(true,g12.isGameOver());
	}

        /**
         * Test the move method.
         */
 @Test
	public void moveTest(){

        g8.move(1,0);
        assertEquals(g9.toString(),g8.toString());
        g10.move(1,3);
        assertEquals(g14.toString(),g10.toString());
        g3.move(1,2);
        g3.move(2,4);
        assertEquals(g15.toString(),g3.toString());
        g10.move(0,0);
        g10.move(1,1);
        assertEquals(g16.toString(),g10.toString());






	}




	}

