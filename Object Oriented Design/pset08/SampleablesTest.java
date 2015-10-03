import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 12/3/14.
 */
public class SampleablesTest {




    @Test
    public void testSampleable(){
        Renderable r1 = Renderables.circle(DoublePoint.point(5,5),5,Colors.rgba(200));
        Raster or1 = new OverlayRaster(10,10);
        r1.render(or1);

        for (int i=0;i<r1.getBoundingBox().intRight();i++){
            for (int j=0;j<r1.getBoundingBox().intBottom();j++){
                if (r1.getBoundingBox().contains(IntegerPoint.point(i,j))) {
                    assertEquals(Sampleables.sampleable(r1).getColorAt(DoublePoint.point(i, j)),
                            Colors.rgba(or1.getRGBA(IntegerPoint.point(i, j)))
                    );
                }
            }
        }





    }



}
