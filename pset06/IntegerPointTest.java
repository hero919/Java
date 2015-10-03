import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 10/31/14.
 */
public class IntegerPointTest extends AbstractTest{
    @Override
    protected Point<?> Center() {
        return new IntegerPoint(0,0);
    }

    protected Point<? > point(int x, int y){
        return IntegerPoint.point(x, y);
    }

























    /**
     *

    IntegerPoint i1= new IntegerPoint(2,3);

    IntegerPoint i2= new IntegerPoint(5,6);

    IntegerPoint i3= new IntegerPoint(5,10);

    @Test
    public void testPointInt(){
        assertEquals(i1,IntegerPoint.point(2,3));
        assertEquals(i2,IntegerPoint.point(5,6));
    }

    @Test
    public void testPointDouble(){
        assertEquals(i1,IntegerPoint.point(2.0,3.0));
        assertEquals(i2,IntegerPoint.point(5.0,6.0));

    }

    @Test
    public void testX(){
        assertEquals(2.0,i1.x(),0);
        assertEquals(5.0,i2.x(),0);
    }

    @Test
    public void testY(){
        assertEquals(3.0,i1.y(),0);
        assertEquals(6.0,i2.y(),0);
    }

    @Test
    public void testIntX(){
        assertEquals(2,i1.intX());
        assertEquals(5,i2.intX());
    }

    @Test
    public void testIntY(){
        assertEquals(3,i1.intY());
        assertEquals(6,i2.intY());
    }

    @Test
    public void testBoxedX(){
        assertEquals(Integer.valueOf(2),i1.boxedX());
        assertEquals(Integer.valueOf(5),i2.boxedX());
    }

    @Test
    public void testBoxedY(){
        assertEquals(Integer.valueOf(3),i1.boxedY());
        assertEquals(Integer.valueOf(6),i2.boxedY());
    }

    @Test
    public void testScareBy(){
        assertEquals(new IntegerPoint(4,6),i1.scaleBy(2.1));
        assertEquals(new IntegerPoint(11,13),i2.scaleBy(2.1));
    }


    @Test
    public void testAdd(){
        assertEquals(new IntegerPoint(4,6),i1.add(new IntegerPoint(2,3)));
        assertEquals(new IntegerPoint(10,12),i2.add(new IntegerPoint(5,6)));
        assertEquals(new IntegerPoint(4,6),i1.add(new DoublePoint(2.0,3.0)));
        assertEquals(new IntegerPoint(10,12),i2.add(new DoublePoint(5.0,6.0)));
    }

    @Test
    public void testSubtract(){
        assertEquals(new IntegerPoint(0,0),i1.subtract(new IntegerPoint(2, 3)));
        assertEquals(new IntegerPoint(4,4),i2.subtract(new IntegerPoint(1, 2)));

    }

    @Test
    public void testToDoublePoint(){
        assertEquals(new DoublePoint(2.0,3.0),i1.toDoublePoint());
        assertEquals(new DoublePoint(5.0,6.0),i2.toDoublePoint());

    }
    @Test
    public void testToIntegerPoint(){
        assertEquals(new IntegerPoint(2,3),i1.toIntegerPoint());
        assertEquals(new IntegerPoint(5,6),i2.toIntegerPoint());

    }

    @Test
    public void testInterpolate(){
        assertEquals(new IntegerPoint(5,6),i2.interpolate(0.5,new IntegerPoint(5,6)));

        assertEquals(new IntegerPoint(7,12),i3.interpolate(0.4,new DoublePoint(10,15)));

    }

    @Test
    public void testEquals1(){
        assertEquals(true,i1.equals(2,3));
        assertEquals(false,i2.equals(2,3));

    }
    @Test
    public void testEquals2(){
        assertEquals(true,i1.equals(i1));
        assertEquals(false,i2.equals(i1));

    }

    @Test
    public void testHashCode(){
        assertEquals(65,i1.hashCode());
        assertEquals(161,i2.hashCode());

    }





    @Test
    public void testGetBoundingBox(){
        assertEquals(6.0,i2.getBoundingBox().top(),0);
        assertEquals(6.0,i2.getBoundingBox().bottom(),0);
        assertEquals(5.0,i2.getBoundingBox().left(),0);
        assertEquals(5.0,i2.getBoundingBox().right(),0);
    }

     */



}
