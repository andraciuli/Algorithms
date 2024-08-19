import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Servere {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("servere.in"));
		// Number of servers
		int n = scanner.nextInt();
		scanner.nextLine();

		int[] p = new int[n];
		int[] c = new int[n];

		// Read the power and the consumption of each server
		for (int i = 0; i < n; i++) {
			p[i] = scanner.nextInt();
		}
		scanner.nextLine();
		for (int i = 0; i < n; i++) {
			c[i] = scanner.nextInt();
		}

		// Calculate the minimum power
		double result = minPower(n, p, c);

		PrintWriter pw = new PrintWriter("servere.out");
		pw.printf("%.1f\n", result);
		pw.close();

		scanner.close();
	}

	private static double minPower(int n, int[] p, int[] c) {
		// Calculate the limits for each server
		ArrayList<Double> negativeLimits = new ArrayList<>();
		ArrayList<Double> positiveLimits = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			// The negative limit is the difference between the consumption and the power
			negativeLimits.add((double) (c[i] - p[i]));
			// The positive limit is the sum of the consumption and the power
			positiveLimits.add((double) (c[i] + p[i]));
		}
		// Sort the negative limits in descending order
		Collections.sort(negativeLimits, Comparator.reverseOrder());
		// Sort the positive limits in ascending order
		Collections.sort(positiveLimits);

		// Calculate the limit by geting the maximum from the
		// negative limits and the minimum from the positive limits
		double limit = (negativeLimits.get(0) + positiveLimits.get(0)) / 2;

		// Calculate the power for each server
		ArrayList<Double> powers = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			powers.add(p[i] - Math.abs(limit - c[i]));
		}

		// Sort the powers in ascending order
		Collections.sort(powers);

		// Return the minimum power
		return powers.get(0);
	}
}
