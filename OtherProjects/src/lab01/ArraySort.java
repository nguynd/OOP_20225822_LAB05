package lab01;
import java.util.Arrays;

public class ArraySort {
	 public static void main(String[] args) {
	        int[] myArray = {1789, 2035, 1899, 1456, 2013};

	        Arrays.sort(myArray);
	        System.out.println("Sorted Array: " + Arrays.toString(myArray));

	        int sum = 0;
	        for (int num : myArray) {
	            sum += num;
	        }
	        System.out.println("Sum: " + sum);

	        double average = (double) sum / myArray.length;
	        System.out.println("average value: " + average);
	    }
}
