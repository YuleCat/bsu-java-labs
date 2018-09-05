/**
 * Created by Yule Cat on 13.11.2017.
 */
package series;

import java.io.*;

public abstract class Series {
    protected double first;
    protected int n;
    protected double step;

    public Series(){
    }

    public Series(double first, int n, double step) {
        this.first = first;
        this.n = n;
        this.step = step;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getFirst() {

        return first;
    }

    public int getN() {
        return n;
    }

    public double getStep() {
        return step;
    }

    public abstract double calc(int j);

    public double sum(){
        double sum = 0;
        for (int i = 1; i <= n; ++i) sum += calc(i);
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 1; i <= n; ++i) res.append(calc(i) + " ");
        return String.valueOf(res);
    }

    public void saveInFile(String filename) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(new File(filename + ".txt"));
        pw.print(getClass().getSimpleName() + ": ");
        pw.println(toString());
        pw.close();
    }
}
