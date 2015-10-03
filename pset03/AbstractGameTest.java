import junit.framework.TestCase;
import org.junit.Test;

public class AbstractGameTest extends TestCase {
    //give some examples
    CoinGame g1 = new LaxCoinGame("OO-");

    CoinGame g2 = new StrictCoinGame("--O-O");
    CoinGame g3 = new StrictCoinGame("OOOOO");
    CoinGame g4 = new StrictCoinGame("----O");
    CoinGame g5 = new LaxCoinGame("OOOOO");
    CoinGame g6 = new LaxCoinGame("--OO");
    CoinGame g10 = new StrictCoinGame("------");


    /**
     * test for boardsize method
     */
    @Test
    public void testboardsize() {
        assertEquals(3, g1.boardSize());
        assertEquals(5, g2.boardSize());
        assertEquals(6,g10.boardSize());
        //assertEquals(1, g20.boardSize());

    }

    /**
     * test for coincount method
     */
    @Test
    public void testcoincount() {
        assertEquals(2, g1.coinCount());
        assertEquals(2, g2.coinCount());
        assertEquals(5,g3.coinCount());
        assertEquals(0,g10.coinCount());
    }

    /**
     * test for getcoinposition method
     */
    @Test
    public void testgetpostion(){
        assertEquals(0,g1.getCoinPosition(0));
        assertEquals(1,g1.getCoinPosition(1));
        assertEquals(4,g2.getCoinPosition(1));
        assertEquals(4,g4.getCoinPosition(0));
        assertEquals(4,g3.getCoinPosition(4));


    }

    /**
     * test isgameover method
     */
    @Test
    public void testisover(){
        assertEquals(true,g5.isGameOver());
        assertEquals(true,g1.isGameOver());
        assertEquals(false,g6.isGameOver());
        assertEquals(false,g4.isGameOver());
        assertEquals(true,g10.isGameOver());
        CoinGame g= new StrictCoinGame("O-O--");
        assertEquals(false,g.isGameOver());

    }

    /**
     * test for move method
     */
    @Test
    public void testmove(){
        CoinGame g7 = new LaxCoinGame("--O-O-");
        g7.move(1,0);
        assertEquals(false, g7.isGameOver());
        g7.move(1,1);
        assertEquals(true, g7.isGameOver());
    }

    /**
     * test tostring method
     */
    @Test
    public void testtostring(){
        assertEquals("--O-O", g2.toString());
        assertEquals("OOOOO", g3.toString());
        assertEquals("------", g10.toString());

    }

    @Test
    public void testmove2(){
        CoinGame g8 = new StrictCoinGame("-O-O--O");
        g8.move(0,0);
        assertEquals("O--O--O",g8.toString());
        g8.move(1,1);
        assertEquals("OO----O",g8.toString());
        CoinGame g9 = new StrictCoinGame("----O");
        g9.move(0,3);
        assertEquals("---O-",g9.toString());
        CoinGame g11 = new LaxCoinGame("-O-O");
        g11.move(1,0);
        assertEquals("OO--", g11.toString());
        assertEquals(true,g11.isGameOver());







    }

    /**
     * test for throw IllegeLArgumentExpect
     */

    @Test(expected = IllegalArgumentException.class)
    public void testmoveill(){




        g6.move(0,10);
        g5.move(1,2);
        g4.move(3,2);
        g2.move(-1,-2);
        CoinGame g26 = new LaxCoinGame("KOKO--");
        g26.move(0,0);
        g2.move(0,3);
        g2.move(1,0);
        CoinGame g27 = new StrictCoinGame("O--O");
        g27.move(1,3);
        g10.move(0,0);
        g6.isGameOver();
        g6.getCoinPosition(0);






    }


}