import java.lang.reflect.Array;

/**
 * Created by Yule Cat on 16.10.2017.
 */
public class LinearEquationSolver {
    public static void main(String[] args){
        LinearEquation le = new LinearEquation();
        le.readDataFromFile("data.txt");
        //le.setMatrixWithRandom();
        System.out.println("Linear equation:");
        le.printLinearEquation();

        System.out.println();

        System.out.println("Answer:");
        le.printAnswer();
    }
}
