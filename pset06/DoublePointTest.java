import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 10/31/14.
 */
public class DoublePointTest extends AbstractTest {
    @Override
    protected Point<?> Center() {
        return new DoublePoint(0,0);
    }


    /**
     DoublePoint i1= new DoublePoint(2,3);

     DoublePoint i2= new DoublePoint(5,6);

     DoublePoint i3= new DoublePoint(5,10);





     @Test public void testPointInt(){
     assertEquals(i1,DoublePoint.point(2,3));
     assertEquals(i2,DoublePoint.point(5,6));
     }

     @Test public void testPointDouble(){

     }

     @Test public void testX(){
     assertEquals(2.0,i1.x(),0);
     assertEquals(5.0,i2.x(),0);
     }

     @Test public void testY(){
     assertEquals(3.0,i1.y(),0);
     assertEquals(6.0,i2.y(),0);
     }

     @Test public void testIntX(){
     assertEquals(2,i1.intX());
     assertEquals(5,i2.intX());
     }

     @Test public void testIntY(){
     assertEquals(3,i1.intY());
     assertEquals(6,i2.intY());
     }

     @Test public void testBoxedX(){
     assertEquals(Double.valueOf(2),i1.boxedX());
     assertEquals(Double.valueOf(5),i2.boxedX());
     }

     @Test public void testBoxedY(){
     assertEquals(Double.valueOf(3),i1.boxedY());
     assertEquals(Double.valueOf(6),i2.boxedY());
     }

     @Test public void testScaleBy(){
     assertEquals(new DoublePoint(4,6),i1.scaleBy(2.0));
     assertEquals(new DoublePoint(10,12),i2.scaleBy(2.0));
     }


     @Test public void testAdd(){
     assertEquals(new DoublePoint(4,6),i1.add(new IntegerPoint(2,3)));
     assertEquals(new DoublePoint(10,12),i2.add(new IntegerPoint(5,6)));
     assertEquals(new DoublePoint(4,6),i1.add(new DoublePoint(2.0,3.0)));
     assertEquals(new DoublePoint(10,12),i2.add(new DoublePoint(5.0,6.0)));
     }

     @Test public void testSubtract(){
     assertEquals(new DoublePoint(0,0),i1.subtract(new IntegerPoint(2, 3)));
     assertEquals(new DoublePoint(4,4),i2.subtract(new IntegerPoint(1, 2)));
     assertEquals(new DoublePoint(0,0),i1.subtract(new DoublePoint(2.0,3.0)));
     assertEquals(new DoublePoint(4,4),i2.subtract(new DoublePoint(1.0,2.0)));
     }

     @Test public void testToDoublePoint(){
     assertEquals(new DoublePoint(2,3),i1.toDoublePoint());
     assertEquals(new DoublePoint(5,6),i2.toDoublePoint());

     }
     @Test public void testToIntegerPoint(){
     assertEquals(new IntegerPoint(2,3),i1.toIntegerPoint());
     assertEquals(new IntegerPoint(5,6),i2.toIntegerPoint());

     }

     @Test public void testInterpolate(){
     assertEquals(new DoublePoint(2,3),i1.interpolate(0.5,new IntegerPoint(2,3)));
     assertEquals(new DoublePoint(7,12),i3.interpolate(0.4,new DoublePoint(10,15)));

     }

     @Test public void testHashCode() {

     assertEquals(524288, i1.hashCode());
     }

     @Test public void testEquals(){
     assertEquals(true,i1.equals((double)2,(double)3));
     assertEquals(false,i2.equals((double)2,(double)3));

     }



     @Test public void testGetBoundingBox(){
     assertEquals(3.0,i1.getBoundingBox().top(),0);
     assertEquals(3.0,i1.getBoundingBox().bottom(),0);
     assertEquals(2.0,i1.getBoundingBox().left(),0);
     assertEquals(2.0,i1.getBoundingBox().right(),0);


     }




     */
}