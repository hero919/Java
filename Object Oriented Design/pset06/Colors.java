import java.util.Objects;

/**
 * Created by zeqingzhang on 11/2/14.
 */
public class Colors extends Object {




    public static Color sample(double red,double green,double blue,double alpha){
        return new RGB(red,green,blue,alpha);
    }


    private static class RGB implements Color{
        double red;
        double green;
        double blue;
        double alpha;
        RGB(double red,double green,double blue,double alpha){

            if(0<=red&&red<=1&&0<=green&&green<=1&&0<=blue&&blue<=1&&0<=alpha&&alpha<=1){
               this.red=red;
                this.green=green;
                this.blue=blue;
                this.alpha=alpha;
            }
            else throw new IllegalStateException("The color can't be represent in this way.");

        }


        /**
         * Gets the red component of this color.
         *
         * @return 0.0 â‰¤ <em>red()</em> â‰¤Â 1.0
         */
        @Override
        public double red() {
            return this.red;
        }

        /**
         * Gets the green component of this color.
         *
         * @return 0.0 â‰¤ <em>green()</em> â‰¤Â 1.0
         */
        @Override
        public double green() {
            return this.green;
        }

        /**
         * Gets the blue component of this color.
         *
         * @return 0.0 â‰¤ <em>blue()</em> â‰¤Â 1.0
         */
        @Override
        public double blue() {
            return this.blue;
        }

        /**
         * Gets the opacity component of this color.
         *
         * @return 0.0 â‰¤ <em>alpha()</em> â‰¤Â 1.0
         */
        @Override
        public double alpha() {
            return this.alpha;
        }

        /**
         * Overlays this color (the foreground color) over the {@code background}
         * color. This calculates the resulting color when a semi-transparent
         * foreground color is placed in front of some background color, which lets
         * some of the background show through.
         * <p>
         * <p>The result is a weighted average (interpolation) between the
         * foreground color made opaque (that is, with alpha set to 1.0) and the
         * background color, where the foreground alpha determines the weight:
         * {@code this.alpha()} is the weight of foreground, and
         * {@code 1 - this.alpha()} is the weight of the background.
         *
         * @param background the background color
         * @return the combined color
         */
        @Override
        public Color overlay(Color background) {
            return Colors.rgba(this.red,this.green,this.blue,1).
                    interpolate(1-this.alpha(),background);

        }

        /**
         * Interpolates a color between {@code this} and {@code other}. The {@code
         * weight} parameter controls the weight of the average: 0.0 returns {@code
         * this}, 1.0 returns {@code other}, and weights in between result in some
         * linear interpolation of the two.
         * <p>
         * For opaque colors, this is merely the weighted average of each color
         * component. When there is non-opaque alpha, however, we need to use a more
         * general formula. Suppose that {@code c == c1.interpolate(weight, c2)} The
         * alpha of the {@code c} is just the weighted average of the alpha of each
         * contributing color:
         * <pre><code>    c.alpha() == Interpolable.interpolate(c1.alpha(),
         * weight, c2.alpha())</code></pre>
         * <p>
         * The correct linear interpolation for the color components with transparency
         * is between each color (or component multiplied) by its alpha, rather than
         * between the colors directly. That is,
         * <pre><code>    c.red() * c.alpha() == Interpolable.interpolate(c1.red() * c1.alpha,
         *                                                    weight,
         *                                                    c2.red() * c2.alpha())
         *                                                    </code></pre>
         * and likewise for the green and blue components. Then we can compute the
         * components of the result by dividing out {@code c.alpha()}.
         * <p>
         * <strong>Precondition: </strong>0.0 â‰¤ {@code weight} â‰¤ 1.0
         *
         * @param weight the proportion of the distance to go from {@code this} color
         *               to the {@code other} color
         * @param other  the other color
         * @return the interpolated color
         */
        @Override
        public Color interpolate(double weight, Color other) {
            double newAlpha=(1 - weight) * this.alpha() + weight * other.alpha();
            double red=Interpolable.interpolate(this.red()*this.alpha(),
                    weight,other.red()*other.alpha())/newAlpha;
            double green=Interpolable.interpolate(this.green()*this.alpha(),
                    weight,other.green()*other.alpha())/newAlpha;
            double blue=Interpolable.interpolate(this.blue()*this.alpha(),
                    weight,other.blue()*other.alpha())/newAlpha;
            return new RGB(red,green,blue,newAlpha);
        }
    }


    public static final Color TRANSPARENT = rgba(0,0,0,0);

    public static final Color BLACK = rgba(0,0,0,1);

    public static final Color WHITE =  rgba(1,1,1,1);




    public static Color rgb(double red, double green, double blue){
        if (red>1.0){
            red=1.0;
        }
        if (red<0.0){
            red=0.0;
        }
        if (green>1.0){
            green=1.0;
        }
        if (green<0.0){
            green=0.0;
        }
        if (blue>1.0){
            blue=1.0;
        }
        if (blue<0.0){
            blue=0.0;
        }

           return new RGB(red,green,blue,1);
    }



    public static Color rgba(double red, double green, double blue, double alpha){
        if (red>1.0){
            red=1.0;
        }
        if (red<0.0){
            red=0.0;
        }
        if (green>1.0){
            green=1.0;
        }
        if (green<0.0){
            green=0.0;
        }
        if (blue>1.0){
            blue=1.0;
        }
        if (blue<0.0){
            blue=0.0;
        }
        if (alpha<=0.0){
            return new RGB(0,0,0,0);
        }
        if (alpha>1.0){
            alpha=1.0;
        }
        return new RGB(red,green,blue,alpha);

    }

    public static Color rgb(int red, int green, int blue){
        if (red>255){
            red=255;
        }
        if (red<0){
            red=0;
        }
        if (green>255){
            green=255;
        }
        if (green<0){
            green=0;
        }
        if (blue>255){
            blue=255;
        }
        if (blue<0){
            blue=0;
        }
        return new RGB(red/255.0,green/255.0,blue/255.0,1);

    }

    public static Color rgba(int red, int green, int blue, int alpha){
        if (red>255){
            red=255;
        }
        if (red<0){
            red=0;
        }
        if (green>255){
            green=255;
        }
        if (green<0){
            green=0;
        }
        if (blue>255){
            blue=255;
        }
        if (blue<0){
            blue=0;
        }
        if (alpha>255){
            alpha=255;
        }
        if (alpha<=0){
            return new RGB(0,0,0,0);
        }
        return new RGB(red/255.0,green/255.0,blue/255.0,alpha/255.0);

    }



    public static Color rgb(int rgb){

        return new RGB(((rgb&0x00FF0000) >>> 16)/255.0,
                ((rgb&0x0000FF00) >>> 8)/255.0,(rgb&0x000000FF)/255.0,1);
        }


    public static Color rgba(int rgba){

        return new RGB(((rgba&0x00FF0000)>>>16)/255.0,((rgba&0x0000FF00)>>>8)/255.0,
                (rgba&0x000000FF)/255.0,((rgba&0xFF000000)>>>24)/255.0);


    }





}
