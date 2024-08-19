import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Colorare {
	private static final int MOD = 1000000007;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("colorare.in"));
		// Number of rectangles
		int K = scanner.nextInt();
		// Read the number of rectangles and their orientation
		int[][] dreptunghiuri = new int[K][2];
		int j = 0;
		for (int i = 0; i < 2 * K; i += 2) {
			dreptunghiuri[j][0] = scanner.nextInt();
			// 0 - horizontal, 1 - vertical
			dreptunghiuri[j][1] = (scanner.next().charAt(0) == 'H') ? 0 : 1;
			j++;
		}

		long result = numberModels(dreptunghiuri);

		PrintWriter writer = new PrintWriter("colorare.out");
		writer.printf("%d\n", result);
		writer.close();

		scanner.close();
	}

	private static long power(long x, long y) {
		long res = 1;
		// Calculate x^y % MOD
		while (y > 0) {
			// If y is odd, multiply x with result
			if ((y & 1) == 1) {
				res = (res * x) % MOD;
			}
			// y must be even now
			y = y >> 1;
			x = (x * x) % MOD;
		}
		return res;
	}

	private static long numberModels(int[][] dr) {
		int n = dr.length;
		long[] dp = new long[n];
		// Base case
		// If the first rectangle is horizontal
		if (dr[0][1] == 0) {
			// There are 6 possibilities
			dp[0] = 6;
			dp[0] = (dp[0] * power(3, dr[0][0] - 1)) % MOD;
		}
		// If the first rectangle is vertical
		if (dr[0][1] == 1) {
			// There are 3 possibilities
			dp[0] = 3;
			dp[0] = (dp[0] * power(2, dr[0][0] - 1)) % MOD;
		}

		// Calculate the number of models for each rectangle
		for (int i = 1; i < n; i++) {
			// If the rectangle is horizontal
			if (dr[i][1] == 0) {
				// If the previous rectangle is horizontal
				if (dr[i - 1][1] == 0) {
					dp[i] = (dp[i - 1] * 3) % MOD;
				}
				// If the previous rectangle is vertical
				if (dr[i - 1][1] == 1) {
					dp[i] = (dp[i - 1] * 2) % MOD;
				}
				dp[i] = (dp[i] * power(3, dr[i][0] - 1)) % MOD;
			}
			// If the rectangle is vertical
			if (dr[i][1] == 1) {
				// If the previous rectangle is horizontal
				if (dr[i - 1][1] == 0) {
					dp[i] = dp[i - 1] % MOD;
				}
				// If the previous rectangle is vertical
				if (dr[i - 1][1] == 1) {
					dp[i] = (dp[i - 1] * 2) % MOD;
				}
				dp[i] = (dp[i] * power(2, dr[i][0] - 1)) % MOD;
			}
		}
		
		return dp[n - 1];
	}
}
