/**
 * A continuous variable having type {@code double}. That is, a {@code
 * DoubleVar} represents the data points for some statistical variable. (It's
 * safe to think of it as a glorified array of {@code double}s.)
 *
 */
//Class Invariant: count has to be the length of
//the array. Because count and array are final. They are
//both class invariant.
public final class DoubleVar {

	
	
	
	
	
  /**
   * The sample size.
   *
   *
   *
   * NO. I don't think the public is not a problem. Because we use final here which means
   * if we give the value of the count. We can't change it anywhere.
   *
   */
  public final int count;

  // The array of data point
  private final double[] array;

  /**
   * Constructs a {@code DoubleVar} from a {@code double[]} (which may be
   * given using varargs syntax).
   *
   * @param args the array of doubles
   */

  //The class Invariant is array and count here
  public DoubleVar(double... args) {
    array = args.clone();
    count = array.length;
  }

  /**
   * Returns the sum of all the data points.
   *
   * @return the sum
   */
  public double sum() {
    double sum = 0;

    for (double d : array) {
      sum += d;
    }

    return sum;
  }

  /**
   * Returns the arithmetic mean (the average) of the data set.
   *
   * @return the mean
   *
   *
   * Precondition: Count can't be 0 here.
   */
  public double mean() {

    return sum() / count;
  }

  /**
   * Returns the (population) variance of the data set.
   *
   * @return the variance
   *
   * Precondition: Count can't be 0 here.
   */
  public double variance() {

    return varianceHelper() / count;
  }

  /**
   * Returns the sample variance of the data set.
   *
   * @return the sample variance
   *
   * Precondition: count can't be 1.
   */
  public double sampleVariance() {

    return varianceHelper() / (count - 1);
  }

  // Helper for computing variance that sums the squares of the displacements
  // from the mean, but doesn't divide by the count (- 1), since that differs
  // between population variance and sample variance.
  private double varianceHelper() {
    double mean = mean();
    double sum = 0;

    for (double f : array) {
      double df = f - mean;
      sum += df * df;
    }

    return sum;
  }

  /**
   * Returns the (population) standard deviation of the data set.
   *
   * @return the standard deviation
   *
   * Precondition: variance can't be negative.
   */
  public double stdDev() {

    return Math.sqrt(variance());
  }

  /**
   * Returns the sample standard deviation of the data set.
   *
   * @return the sample standard deviation
   *
   * Precondition: variance can't be negative.
   */
  public double sampleStdDev() {

    return Math.sqrt(sampleVariance());
  }
}
