import com.sun.org.apache.xml.internal.security.signature.reference
        .ReferenceNodeSetData;

/**
 * Renders several circles using {@link RasterApplication} and {@link
 * Sampleables}.
 */
public final class CirclesDemo extends RasterApplication {
  public static void main(String[] args) {
    new CirclesDemo().launch("Circles", 1000, 800);
  }

  @Override
  protected void render(Raster raster) {
    // Uncomment the next line after OverlayRaster is implemented:
    raster = new OverlayRaster(raster);
      circles().render(raster);
/**
    Sampleable s1 = circles2().scale(4);
      for(int i=0;i< raster.width();i++){
          for (int j=0; j< raster.height();j++){
              if (s1.getBoundingBox().contains(IntegerPoint.point(i,j))){

                  raster.setColor(IntegerPoint.point(i,j),s1.getColorAt(DoublePoint.point(i,j)));
              }
          }
      }
 */
  }

  /**
   * Returns a sampleable of circles.
   * @return a sampleable
   */
  private Renderable circles() {
    return
    // A visual example:

      Renderables.overlay(
        Renderables.renderable(
          Sampleables.circle(DoublePoint.point(600, 300),
                             200, Colors.rgb(0x99, 0x99, 0x00))),
        Renderables.circle(DoublePoint.point(400, 300),
                           200, Colors.rgba(0x00, 0x99, 0x99, 0x80))
         .scale(DoublePoint.point(400, 300), .75),
        Renderables.circle(DoublePoint.point(500, 500),
                           200, Colors.rgb(0x00, 0x00, 0x00))
      );

  }
    private Sampleable circles2() {
        return
                // A visual example:

                Sampleables.sampleable(Renderables.overlay(
                        Renderables.renderable(
                                Sampleables.circle(DoublePoint.point(600, 300),
                                        200, Colors.rgb(0x99, 0x99, 0x00))),
                        Renderables.circle(DoublePoint.point(400, 300),
                                200, Colors.rgba(0x00, 0x99, 0x99, 0x80))
                                .scale(DoublePoint.point(400, 300), .75),
                        Renderables.circle(DoublePoint.point(500, 500),
                                200, Colors.rgb(0x00, 0x00, 0x00)))
                );

    }







}
