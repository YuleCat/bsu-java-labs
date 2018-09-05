import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Yule Cat on 23.10.2017.
 */
public class LinearEquation {
    private static final int SIZE = 5;

    private float[][] koef;
    private float[] free;
    private float[] unknown;
    private int size;

    LinearEquation(int size){
        this.size = size;

        koef = new float[size][size];
        free = new float[size];
        unknown = new float[size];
    }

    LinearEquation(){
        this.size = SIZE;
        koef = new float[size][size];
        free = new float[size];
        unknown = new float[size];
    }

    public void setMatrixWithRandom(){
        for (int i = 0; i < size; ++i){
            free[i] = (float)Math.round(Math.random() * 100) / 10;
            //koef[i] = new float[size - i];
            for(int j = i; j < size; ++j){
                koef[i][j] = (float)Math.round(Math.random() * 100) / 10;
            }
        }
    }

    public void readDataFromFile(String filename){
        try {
            Scanner in = new Scanner(new File(filename));
            if (!in.hasNextInt()) throw new Exception("Bad text file! Matrix would be filled using Math.rand().");

            int fileSize = in.nextInt();
            koef = new float[fileSize][fileSize];
            free = new float[fileSize];
            unknown = new float[fileSize];

            for (int i = 0; i < fileSize; ++i){
                for (int j = i; j < fileSize; ++j){
                    if (!in.hasNextFloat()) throw new Exception("Bad text file! Matrix would be filled using Math.rand().");
                    koef[i][j] = in.nextFloat();
                }
                if (!in.hasNextFloat()) throw new Exception("Bad text file! Matrix would be filled using Math.rand().");
                free[i] = in.nextFloat();
            }
        } catch (Exception  e){
            System.out.println(e);
            setMatrixWithRandom();9*




        }
    }

    public void solveEquation() throws Exception{
        if (koef[size - 1][size - 1] == 0 && free[size - 1] != 0) {
            throw new Exception("Equation cannot be solved!");
        }

        for (int i = size - 1; i >= 0; --i){
            unknown[i] = free[i];
            for(int j = size - 1; j > i; --j){
                unknown[i] -= koef[i][j] * unknown[j];
            }
            unknown[i] /= koef[i][i];
        }
    }

    public void printLinearEquation(){
        for (int i = 0; i < size; ++i){
            for (int j = 0; j < size; ++j){
                System.out.print(koef[i][j] + " ");
            }
            System.out.println(" " + free[i]);
        }
    }

    public void printAnswer(){
        try {
            solveEquation();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        for (int i = 0; i < size; ++i){
            System.out.println("x" + (i+1) + " = " + unknown[i] + ";");
        }
    }
}
