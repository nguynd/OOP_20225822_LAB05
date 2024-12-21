package lab01;
import java.util.Scanner;

public class LinearEquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input coefficients for the linear equation
        System.out.print("a (a != 0): ");
        double a = scanner.nextDouble();
        System.out.print("b: ");
        double b = scanner.nextDouble();

        // Check if a is zero
        if (a == 0) {
            System.out.println("a != 0.");
        } else {
            // Calculate the solution
            double x = -b / a;
            System.out.println("x = " + x);
        }

        scanner.close();
    }
}
