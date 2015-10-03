import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeqingzhang on 12/3/14.
 */
public class RenderablesTest {

    @Test
    public void testRenderables() {
        Raster raster = new OverlayRaster(400, 300);

        Renderable rect1 = Renderables.rectangle(100, 200, 100, 300,
                Colors.rgb(0x00000060));
        Renderable rect2 = Renderables.rectangle(100, 200, 100, 300,
                Colors.rgb(0x00000070));
        Renderable rect3 = Renderables.rectangle(100, 200, 100, 300,
                Colors.rgb(0x00000080));
        Renderable scene = Renderables.overlay(rect1, rect2,rect3);
        scene.render(raster);
        for (int i=0;i<rect1.getBoundingBox().intRight();i++){
            for (int j=0;j<rect1.getBoundingBox().intBottom();j++){
                if (rect1.getBoundingBox().contains(IntegerPoint.point(i,j))) {
                    assertEquals(-16777120, raster.getRGBA(IntegerPoint.point(i,j)));
                }
            }
        }

    }


}
