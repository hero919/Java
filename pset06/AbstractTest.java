import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 11/21/14.
 */
public abstract class AbstractTest {



    protected abstract Point<?> Center();

    protected abstract Point<? > point(int x, int y);


    @Test
    public void testPointInt(){
        assertEquals(Center(),point(2,3));
        assertEquals(i2,IntegerPoint.point(5,6));
    }
























}
