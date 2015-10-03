import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 11/11/14.
 */
public class SampleablesTest {

   Sampleable s1 = Sampleables.circle(DoublePoint.point(5,5),3.0,Colors.rgba(0.5,0.5,0.5,0.5));
   Sampleable s2 = Sampleables.circle(DoublePoint.point(10,5),5.0,Colors.rgba(0.3,0.4,0.5,0.9));
   Sampleable s3 = Sampleables.circle(DoublePoint.point(10,5),5.0,Colors.rgba(0,0,0,0));




   Sampleable s4= Sampleables.addTransparency(1,s1);
   Sampleable s5= Sampleables.addTransparency(0.5,s2);
   Sampleable s6= Sampleables.addTransparency(0.3,s3);



    Sampleable s7= Sampleables.circle(DoublePoint.point(50,50),5.0,Colors.rgba(0.3,0.4,0.5,0));





  Sampleable s8= Sampleables.circle(DoublePoint.point(5,5),5.0,Colors.rgba(1,0,0,0.6));
  Sampleable s9= Sampleables.circle(DoublePoint.point(5,5),5.0,Colors.rgba(0,1,0,0.6));

    Sampleable s10= Sampleables.addTransparency(0.4,s8);


    @Test
   public void testAddTranparency(){
       assertEquals(Colors.rgba(0.5,0.5,0.5,0.5),s4.getColorAt(DoublePoint.point(3,3)));
       assertEquals(Colors.rgba(0.3,0.4,0.5,0.45),s5.getColorAt(DoublePoint.point(8,8)));
       assertEquals(Colors.rgba(0,0,0,0),s6.getColorAt(DoublePoint.point(7,8)));
       assertEquals(Colors.rgba(1,0,0,0.24),s10.getColorAt(DoublePoint.point(5,5)));
   }



    @Test
    public void testOverlay(){
        assertEquals(s2.getColorAt(DoublePoint.point(10,5)),
              Sampleables.overlay(s2, s3).getColorAt(DoublePoint.point(10,5)));

        assertEquals(Sampleables.overlay(s2,s3,s3).getColorAt(DoublePoint.point(10,5)),
               s2.getColorAt(DoublePoint.point(10,5)));


        assertEquals(1,Sampleables.overlay(s8,s9,s8).getColorAt(DoublePoint.point(5,5)));





    }





}
