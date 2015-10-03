/**
 * Created by zeqingzhang on 10/30/14.
 */
public final class DoublePoint extends Object implements Point<Double> {

    double x;
    double y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }




    /**
     * Gets the x coordinate of this point as a {@code double}.
     *
     * @return x
     */
    @Override
    public double x() {
        return x;
    }

    /**
     * Gets the y coordinate of this point as a {@code double}.
     *
     * @return y
     */
    @Override
    public double y() {
        return y;
    }

    /**
     * Gets the x coordinate of this point as a {@code int}.
     *
     * @return x
     */
    @Override
    public int intX() {
        return (int) x;
    }

    /**
     * Gets the y coordinate of this point as a {@code int}.
     *
     * @return y
     */
    @Override
    public int intY() {
        return (int) y;
    }

    /**
     * Gets the x coordinate of this point as a boxed numeric value.
     *
     * @return x
     */
    @Override
    public Double boxedX() {
        return Double.valueOf(this.x());
    }

    /**
     * Gets the y coordinate of this point as a boxed numeric value.
     *
     * @return y
     */
    @Override
    public Double boxedY() {
        return Double.valueOf(this.y());
    }

    /**
     * Scales this vector by the given factor.
     * <p>
     * This operation treats points as 2-D vectors. For integral vectors, this
     * operation is not closed and will require rounding. The multiplication
     * should be performed with {@code double}s, and the result rounded with
     * {@link Math#round(double)}.
     *
     * @param factor the factor to scale by
     * @return {@code (factor * x, factor * y)}
     */
    @Override
    public Point<Double> scaleBy(double factor) {
        return new DoublePoint(this.x*factor,this.y*factor);
    }

    /**
     * Adds another vector to this vector. This operation treats points as 2-D
     * vectors.
     *
     * @param other the other vector
     * @return {@code (x + other.x, y + other.y)}
     */
    @Override
    public Point<Double> add(Point<?> other) {
        return new DoublePoint(this.x+other.x(),this.y+other.y());
    }

    /**
     * Subtracts another vector from this vector. This operation treats
     * points as 2-D vectors.
     *
     * @param other the other vector
     * @return {@code (x - other.x, y - other.y)}
     */
    @Override
    public Point<Double> subtract(Point<?> other) {
        return new DoublePoint(this.x-other.x(),this.y-other.y());

    }


    /**
     * Returns a {@code double} point equivalent to this point. If this point
     * <em>is</em> a {@code double} point, then it may return {@code this};
     * otherwise it may have to construct a new point to perform the conversion.
     *
     * @return {@code this} as a {@code Point<Double>}
     */
    @Override
    public Point<Double> toDoublePoint() {
        return  this;

    }

    /**
     * Returns a {@code int} point equivalent to this point. If this point
     * <em>is</em> an {@code int} point, then it may return {@code this};
     * otherwise it may have to construct a new point to perform the conversion.
     *
     * @return {@code this} as a {@code Point<Integer>}
     */
    @Override
    public Point<Integer> toIntegerPoint() {
        return new IntegerPoint(this.intX(),this.intY());

    }

    /**
     * Gets this object's bounding box.
     *
     * @return this object's bounding box
     */
    @Override
    public BoundingBox getBoundingBox() {
        return
                BoundingBox.fromTBLR(this.y(),this.y(),this.x(),this.x());
    }

    /**
     * Interpolates a value between {@code this} and {@code other}. The {@code
     * weight} parameter controls the weight of the average: 0.0 returns
     * {@code this}, 1.0 returns {@code other}, and weights in between result
     * in some linear interpolation of the two.
     * <p>
     * <p>
     * When interpolating numbers, this is merely a weighted average,
     * where {@code weight} is the weight of {@code other} and {@code 1 - weight}
     * is the weight of {@code this}:
     * <pre><code>    (1.0 - weight) * this + weight * other</code></pre>
     * The purpose of this interface is to generalize this concept beyond
     * numbers. For example, for vectors, we can interpolate by interpolating
     * the components pairwise. In other domains, the operation may be defined
     * differently; for example, color interpolation can treat the red, green,
     * and blue components as an ordinary vector, but alpha needs to be treated
     * specially.
     * <p>
     * <p><strong>Precondition:
     * </strong>0.0 ≤ {@code weight} ≤ 1.0
     *
     * @param weight the proportion of the distance to go from {@code this} to
     *               {@code other}
     * @param other  the other value
     * @return the interpolated value
     */
    @Override
    public Point<Double> interpolate(double weight, Point<?> other) {
        return new DoublePoint((1.0 - weight) * this.x() + weight * other.x(),
                (1.0 - weight) * this.y() + weight * other.y());

    }


    /**
     *Create a point of given coordinates.
     * @param x
     * @param y
     * @return a double point with given coordinates.
     */
    public static Point<Double> point(double x, double y){
        return new DoublePoint(x,y);
    }


    /**
     * Create a hash code for check whether two elements are equal.
     * @return  the number of hashcode.
     */
    public int hashCode(){
        return hashCodeForDoublePoint();
    }


    /**
     * Using hashcode to check whether two elements are equal.
     * @param other the other given object
     * @return whether the given object is the same thing.
     */

    public boolean equals(Object other) {
        if (other instanceof DoublePoint) {
            return this.hashCode() == other.hashCode();
        }
        return false;
    }


    /**
     * Determines whether this point is equal to the double point with the given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return whether this point is equal to the double point with the given coordinates.
     */

    public boolean equals(double x,double y){
        return  this.x==x&&this.y==y;
    }




}
