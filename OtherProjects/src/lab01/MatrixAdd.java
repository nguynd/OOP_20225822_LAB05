package lab01;

import java.util.Scanner;

public class MatrixAdd {

	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Prompt the user to enter the size of the matrices
	        System.out.print("Enter the number of rows: ");
	        int rows = scanner.nextInt();
	        System.out.print("Enter the number of columns: ");
	        int cols = scanner.nextInt();

	        // Initialize the matrices
	        int[][] matrix1 = new int[rows][cols];
	        int[][] matrix2 = new int[rows][cols];
	        int[][] sumMatrix = new int[rows][cols];

	        // Prompt the user to enter the elements of the first matrix
	        System.out.println("Enter the elements of the first matrix:");
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                matrix1[i][j] = scanner.nextInt();
	            }
	        }

	        // Prompt the user to enter the elements of the second matrix
	        System.out.println("Enter the elements of the second matrix:");
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                matrix2[i][j] = scanner.nextInt();
	            }
	        }

	        // Calculate the sum of the matrices
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
	            }
	        }

	        // Display the sum matrix
	        System.out.println("Sum of the matrices:");
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                System.out.print(sumMatrix[i][j] + " ");
	            }
	            System.out.println();
	        }
	 scanner.close();   
	 }
	}