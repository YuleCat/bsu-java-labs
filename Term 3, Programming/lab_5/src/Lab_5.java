import java.text.NumberFormat;

/**
 * Created by Yule Cat on 09.10.2017.
 */

// variant 7
// Horstman - Java


public class Lab_5 {
    private static double calc(double x, double eps){
        double k = 1;
        double slag = Math.pow(x, 2 * k) / Math.pow(2, k) / k,
                res = slag;
        while(Math.abs(slag) >= eps) {
            slag *= Math.pow(x, 2) / 2 / (k + 1);
            res += slag;
            k++;
        }
        return res;
    }

    public static void main(String args[]){
        double x = 0, eps = 0;
        try{
            if (args.length != 2) throw new MyException("Error! Exactly 2 arguments required.");

            x = Double.parseDouble(args[0]);
            eps = Double.parseDouble(args[1]);
            System.out.printf("Sum of the series = %.10f \n", calc(x, eps));
        }
        catch (MyException | NumberFormatException ex){
            System.out.println("exc = " + ex);
        }

    }
}
