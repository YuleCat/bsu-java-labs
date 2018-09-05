/**
 * Created by Yule Cat on 13.11.2017.
 */
package series;

public class Exponential extends Series {
    public Exponential() {
    }

    public Exponential(double first, int n, double step) {
        super(first, n, step);
    }

    public double calc(int j){
        return first * Math.pow(step, j-1);
    }
}
