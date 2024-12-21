package lab01;
import java.util.Scanner;

public class QuadraticEquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input coefficients for the quadratic equation
        System.out.print("a, b, c (ax^2 + bx + c = 0): ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        // Calculate the discriminant
        double delta = b * b - 4 * a * c;

        // Check the discriminant to find the roots
        if (delta > 0) {
            double root1 = (-b + Math.sqrt(delta)) / (2 * a);
            double root2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("x1 = " + root1 + ", x2 = " + root2);
        } else if (delta == 0) {
            double root = -b / (2 * a);
            System.out.println("x = " + root);
        } else {
            System.out.println("can't find x");
        }

        scanner.close();
    }
}
