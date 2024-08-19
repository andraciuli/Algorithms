import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Compresie {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("compresie.in"));
		// Number of elements in the first array
		int n = scanner.nextInt();
		scanner.nextLine();

		// Read the elements of the first array
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}

		scanner.nextLine();

		// Number of elements in the second array
		int m = scanner.nextInt();
		scanner.nextLine();

		// Read the elements of the second array
		int[] B = new int[m];
		for (int i = 0; i < m; i++) {
			B[i] = scanner.nextInt();
		}

		int result = finalArraySize(A, B);

		PrintWriter pw = new PrintWriter("compresie.out");
		pw.printf("%d\n", result);
		pw.close();

		scanner.close();
	}

	public static int finalArraySize(int[] a, int[] b) {
		// Calculate the sum of the elements in the first array
		int sumA = 0;
		for (int num : a) {
			sumA += num;
		}

		// Calculate the sum of the elements in the second array
		int sumB = 0;
		for (int num : b) {
			sumB += num;
		}

		// If the sums are different, return -1
		if (sumA != sumB) {
			return -1;
		}

		int indexA = 0;
		int indexB = 0;
		ArrayList<Integer> finalArr = new ArrayList<>();

		while (indexA < a.length && indexB < b.length) {
			// If the element from the first array is greater than the element from the second array
			// add the element from the second array to the next element from the second array
			// and increment the index of the second array
			if (a[indexA] > b[indexB]) {
				b[indexB + 1] += b[indexB];
				indexB++;
				continue;
			}
			// If the element from the first array is less than the element from the second array
			// add the element from the first array to the next element from the first array
			// and increment the index of the first array
			if (a[indexA] < b[indexB]) {
				a[indexA + 1] += a[indexA];
				indexA++;
				continue;
			}
			// If the elements from the first and the second array are equal
			// add the element to the final array and increment the indexes of both arrays
			if (a[indexA] == b[indexB]) {
				finalArr.add(a[indexA]);
				indexA++;
				indexB++;
			}
		}

		// Return the size of the final array
		return finalArr.size();
	}
}