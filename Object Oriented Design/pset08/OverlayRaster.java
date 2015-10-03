import java.util.Objects;

/**
 * Created by zeqingzhang on 11/30/14.
 */
public final class OverlayRaster extends Object implements Raster{

    Raster base;


    OverlayRaster(int width, int height){

        if (width>=0 || height>=0) {
            base = new ArrayRaster(width, height);
        }
        else throw new NullPointerException("This is not a valid raster");

    }

    OverlayRaster(Raster base) {
        if (base.width() >= 0 || base.height() >= 0||base != null) {
            this.base = base;
        } else throw new NullPointerException("The base is not valid");
    }




    /**
     * Gets the width of the surface, in pixels.
     *
     * @return the width
     */
    @Override
    public int width() {
        return base.width();
    }

    /**
     * Gets the height of the surface, in pixels.
     *
     * @return the height
     */
    @Override
    public int height() {
        return base.height();
    }

    /**
     * Sets the pixel at column {@code point.intX()} and row {@code point.intY()}
     * to color {@code colorValue}.
     * <p>
     * <p>
     * <strong>Preconditions:</strong>
     * <ul>
     * <li>{@code 0} ≤ {@code point.intX()} &lt; {@code width()}
     * <li>{@code 0} ≤ {@code point.intY()} &lt; {@code height()}
     * </ul>
     *
     * @param point      the location of the pixel (zero-based, top to bottom)
     * @param colorValue the 32-bit RGBA color to set the pixel to
     * @throws IndexOutOfBoundsException if the pixel is out of bounds
     */
    @Override
    public void setRGBA(Point<Integer> point, int colorValue) {

        if (this.base.width() <= point.intX()){
            throw new IndexOutOfBoundsException("The given point is out of bound");
        }

        else if (this.base.height()<= point.intY()){
            throw new IndexOutOfBoundsException("The given point is out of bound");
        }

        base.setColor(point,Colors.rgba(colorValue).overlay(base.getColor(point)));


    }

    /**
     * Gets the 32-bit RGBA color of the specified pixel. This is not strictly
     * required for rendering, but it can make it easier to implement a raster in
     * terms of another raster.
     * <p>
     * <p>
     * <strong>Preconditions:</strong>
     * <ul>
     * <li>{@code 0} ≤ {@code point.intX()} &lt; {@code width()}
     * <li>{@code 0} ≤ {@code point.intY()} &lt; {@code height()}
     * </ul>
     *
     * @param point the location of the pixel
     * @return the 32-bit RGBA color value of the pixel
     * @throws IndexOutOfBoundsException if the pixel is out of bounds
     */
    @Override
    public int getRGBA(Point<Integer> point) {
        if (this.base.width() <= point.intX()){
            throw new IndexOutOfBoundsException("The given point is out of bound");
        }

        else if (this.base.height()<= point.intY()){
            throw new IndexOutOfBoundsException("The given point is out of bound");
        }
        else return base.getColor(point).intValue();

    }


}
