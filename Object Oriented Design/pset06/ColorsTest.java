import org.junit.Test;


import static org.junit.Assert.assertEquals;



/**
 * Created by zeqingzhang on 11/4/14.
 */
public class ColorsTest {

    public static void assertNearby(Color c1,Color c2){
        assertEquals(c1.red(),c2.red(),0.001);
        assertEquals(c1.green(),c2.green(),0.001);
        assertEquals(c1.blue(),c2.blue(),0.001);
        assertEquals(c1.alpha(),c2.alpha(),0.001);



    }

    @Test
    public void testColorDouble(){
      assertEquals(0.2,Colors.sample(0.2,0.3,0.4,0.5).red(),0);
      assertEquals(0.3,Colors.sample(0.2,0.3,0.4,0.5).green(),0);
      assertEquals(0.4,Colors.sample(0.2,0.3,0.4,0.5).blue(),0);
      assertEquals(0.5,Colors.sample(0.2,0.3,0.4,0.5).alpha(),0);


  }



    @Test
    public void testOverlay(){
        assertNearby(Colors.sample(0.155,0.441,0.368,0.73),
                Colors.sample(0.5,0.7,0.8,0.1).overlay(Colors.sample(0.1,0.4,0.3,0.7)));
    }



    @Test
    public void testInterpolate(){

        assertNearby(Colors.sample(0.245,0.509,0.482,0.22),
                Colors.sample(0.5,0.7,0.8,0.1).interpolate(0.2,Colors.sample(0.1,0.4,0.3,0.7)));
    }



    @Test
    public void testRgbDouble(){
        assertNearby(Colors.sample(0.2,0.3,0.4,1),Colors.rgb(0.2,0.3,0.4));
        assertNearby(Colors.sample(0.5,0.6,0.7,1),Colors.rgb(0.5,0.6,0.7));
        assertNearby(Colors.sample(1,0.2,0,1),Colors.rgb(2,0.2,0.0));
        assertNearby(Colors.sample(0.1,0,1,1),Colors.rgb(0.1,-1,2));
    }

    @Test
    public void testRgbaDouble(){
        assertNearby(Colors.sample(0.1,0.2,0.3,0.4),Colors.rgba(0.1,0.2,0.3,0.4));
        assertNearby(Colors.sample(0,0,0.5,1),Colors.rgba(0,0,0.5,1));
        assertNearby(Colors.sample(1,0.2,0,0.3),Colors.rgba(2,0.2,0.0,0.3));
        assertNearby(Colors.sample(0.1,0,1,0.4),Colors.rgba(0.1,-1,2,0.4));
        assertNearby(Colors.sample(1,0.2,0,0.1),Colors.rgba(2,0.2,0,0.1));
        assertNearby(Colors.sample(0,0,0,0),Colors.rgba(2,0.2,0.3,0));
    }

    @Test
    public void testRgbInt(){
        assertNearby(Colors.sample(0.196,0.235,0.275,1),Colors.rgb(50,60,70));
        assertNearby(Colors.sample(1,0.235,0.275,1),Colors.rgb(300,60,70));
        assertNearby(Colors.sample(0,0.235,0.275,1),Colors.rgb(-1,60,70));


    }
    @Test
    public void testRgbaInt(){

        assertNearby(Colors.sample(0.196,0.235,0.275,0.314),Colors.rgba(50,60,70,80));
        assertNearby(Colors.sample(1,0.235,0.275,0.314),Colors.rgba(500,60,70,80));
        assertNearby(Colors.sample(0,0,0,0),Colors.rgba(50,60,70,0));


    }

    @Test
    public void testRgbOneInt(){
        assertNearby(Colors.sample(0.0667,0.133,0.2,1),Colors.rgb(0x112233));
        assertNearby(Colors.sample(0,0,0.125,1),Colors.rgb(32));
        assertNearby(Colors.sample(1,1,1,1),Colors.rgb(-1));
    }


    @Test
    public void testRgbaOneInt(){
        assertNearby(Colors.sample(0.0667,0.133,0.2,0.0667),Colors.rgba(0x11112233));
        assertNearby(Colors.sample(1,1,1,1),Colors.rgba(-1));
    }








}
