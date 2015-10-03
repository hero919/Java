import java.awt.*;
import java.util.Arrays;

/**
 * Static factories for creating {@link Sampleable}s, including several
 * {@link Sampleable} decorators.
 */
public final class Sampleables {
    /**
     * Prevents instantiation.
     */
    private Sampleables() {
    }

    /**
     * Creates a sampleable circle with the given parameters.
     *
     * @param center the center of the circle
     * @param radius the radius of the circle
     * @param color  the color of the circle
     * @return the circle
     */
    public static Sampleable circle(Point<Double> center, double radius,
                                    Color color) {
        return new AbstractSampleable(center.y() - radius, center.y() + radius,
                center.x() - radius, center.x() + radius) {
            @Override
            public Color getColorAt(Point<Double> point) {
                if (center.distanceTo(point) <= radius) {
                    return color;
                } else {
                    return Colors.TRANSPARENT;
                }
            }
        };
    }

    /**
     * Scales a sampleable by the given factor. The position of the origin is
     * fixed.
     *
     * @param factor the factor to scale by
     * @param base   the sampleable to scale
     * @return the scaled sampleable
     */
    public static Sampleable scale(double factor, Sampleable base) {
        BoundingBox bb = base.getBoundingBox();
        return new AbstractSampleable(factor * bb.top(), factor * bb.bottom(),
                factor * bb.left(), factor * bb.right()) {
            @Override
            public Color getColorAt(Point<Double> point)
            {
                return base.getColorAt(point.scaleBy(1 / factor));
            }
        };
    }

    /**
     * Overlays several sampleables from top to bottom. This method ignores the
     * alpha channel in the sense that it doesn't show lower layers through
     * semi-transparent upper layers.
     *
     * @param elements the sampleables to overlay, top first
     * @return the composed sampleable
     */
    public static Sampleable opaqueOverlay(Sampleable... elements) {
        return new AbstractSampleable(BoundingBox.containing(elements)) {
            @Override
            public Color getColorAt(Point<Double> point) {
                for (Sampleable element : elements) {
                    BoundingBox bb = element.getBoundingBox();
                    if (bb.contains(point)) {
                        Color color = element.getColorAt(point);
                        if (!color.isTransparent()) {
                            return color;
                        }
                    }
                }

                return Colors.TRANSPARENT;
            }
        };
    }

    /**
     * Adds (partial) transparency to a sampleable. This decorator multiplies the alpha
     * channel of the base sampleable by an opacity factor, alpha. If the base sampleable is
     * opaque, then alpha gives the opacity of the result,
     * but to the extent that the base sampleable
     * is semi-transparent, alpha applies
     * additional transparency on top of that by multiplying the alpha at each point.
     *
     * @param alpha the opacity of the resulting sampleable
     * @param base  the sampleable to make partially transparent
     * @return the partially transparent sampleable
     */
    public static Sampleable addTransparency(double alpha,
                                             Sampleable base) {

        return new AbstractSampleable(base.getBoundingBox()) {
            @Override
            public Color getColorAt(Point<Double> point) {
                double red=base.getColorAt(point).red();
                double green=base.getColorAt(point).green();
                double blue=base.getColorAt(point).blue();
                double alpha1=base.getColorAt(point).alpha();
                return Colors.rgba(red,green,blue,alpha1*alpha);

            }
        };


    }

  

    public static Sampleable overlay1(Sampleable... layers) {

        return new AbstractSampleable(BoundingBox.containing(layers)) {
            @Override
            public Color getColorAt(Point<Double> point) {

                Color c1 = Colors.TRANSPARENT;
                for (int i = 0; i < layers.length &&
                        layers[i].getColorAt(point).alpha() != 1; i++) {
                    BoundingBox b1 = layers[i].getBoundingBox();

                    if (b1.contains(point)) {
                       c1 = c1.overlay(layers[i].getColorAt(point));

                    }
                }
                return c1;
            }
        };
    }

    public static Sampleable overlay(Sampleable... layers) {

        return new AbstractSampleable(BoundingBox.containing(layers)) {
            @Override
            public Color getColorAt(Point<Double> point) {

                Color c1 = Colors.TRANSPARENT;
                for (int i = 0;
                     i < layers.length && layers[i].getColorAt(point).alpha() != 1; i++) {
                    BoundingBox b1 = layers[i].getBoundingBox();

                    if (b1.contains(point)) {
                        c1 = c1.overlay(layers[i].getColorAt(point));
                    }
                }

                return c1;
            }
        };
    }
}

















