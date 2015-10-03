import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 12/3/14.
 */
public class OverlayRasterTest {

    Raster r1 = new OverlayRaster(200,300);

    @Test(expected = NullPointerException.class)
    public void testOverlayRaster_null() {
        new OverlayRaster(null);
    }

    @Test(expected = NullPointerException.class)
    public void testOverlayRasterOverBound() {
        new OverlayRaster(-2,-4);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetColorOutOfBounds(){
        r1.setRGBA(IntegerPoint.point(1000, 100), 20);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetColorOutOfBounds(){
        r1.getRGBA(IntegerPoint.point(100, 2000));
    }


    @Test
    public void testOverlayWidth(){
        assertEquals(200,r1.width());
    }

    @Test
    public void testOverlayHeight(){
        assertEquals(300,r1.height());
    }




    @Test
    public void testGetRGBA(){
        Raster r2 = new OverlayRaster(r1);
        r2.setRGBA(IntegerPoint.point(10,10),50);
        assertEquals(50, r1.getRGBA(IntegerPoint.point(10, 10)));
    }

    @Test
    public void testSetRGBA(){

    }

}
