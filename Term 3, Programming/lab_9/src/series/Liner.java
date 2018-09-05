/**
 * Created by Yule Cat on 13.11.2017.
 */
package series;

public class Liner extends Series {
    public Liner(){
    }

    public Liner(double first, int n, double step) {
        super(first, n, step);
    }

    public double calc(int j){
        return first + (j-1)*step;
    }
}
