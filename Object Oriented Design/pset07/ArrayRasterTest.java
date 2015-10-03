import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 11/11/14.
 */
public class ArrayRasterTest {



    int[] Array1={10,20,30,40,50,60,70,80,90,100,101};
    int[] Array2={50,60,70,80,10,20,30,40,50,60,70,80,90,100,10
    ,20,30,40,50,60,70,10,203,40,2,3,3,4,5,5,6,6};
    int[] Array3=new int[9];
    ArrayRaster r1= new ArrayRaster(3,3);
    ArrayRaster r2= new ArrayRaster(4,4);
    ArrayRaster r3= new ArrayRaster(3,3,Array1);
    ArrayRaster r4= new ArrayRaster(4,4,Array2);
    ArrayRaster r5= new ArrayRaster(30,30,null);

    @Test
    public void testGetBuffer(){


       for(int i=0;i< Array3.length;i++){
       assertEquals(Array3[i],r2.getBuffer()[i]);
       }

        for (int i=0; i< r2.getBuffer().length;i++){
            assertEquals(0,r2.getBuffer()[i]);
        }

        for (int i=0; i< r5.getBuffer().length;i++){
            assertEquals(0,r5.getBuffer()[i]);
        }





    }
    @Test
    public void testGetRGBA(){

        assertEquals(50,r3.getRGBA(IntegerPoint.point(1,1)));
        assertEquals(60,r3.getRGBA(IntegerPoint.point(2,1)));
        assertEquals(100,r4.getRGBA(IntegerPoint.point(1,3)));




    }
    @Test
    public void testSetRGBA(){

        r3.setRGBA(IntegerPoint.point(2,2),50);
        assertEquals(50,r3.getRGBA(IntegerPoint.point(2,2)));

        r1.setRGBA(IntegerPoint.point(2,2),20);
        assertEquals(20,r1.getRGBA(IntegerPoint.point(2,2)));
    }


}
