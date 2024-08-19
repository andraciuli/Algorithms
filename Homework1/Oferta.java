import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Oferta {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("oferta.in"));

		// Number of products
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		scanner.nextLine();

		// Read the prices of the products
		int[] prices = new int[n];
		for (int i = 0; i < n; i++) {
			prices[i] = scanner.nextInt();
		}

		double result = minPrice(prices, n);

		PrintWriter pw = new PrintWriter("oferta.out");
		pw.printf("%.1f\n", result);
		pw.close();

		scanner.close();
	}

	private static double minPrice(int[] prices, int n) {
		double[] totalPrice = new double[n];

		// If there is only one product, return its price
		if (n == 1) {
			return prices[0];
		}

		// Base cases
		totalPrice[0] = prices[0];
		double price = Math.min(prices[0], prices[1]);
		// If there are two products, apply the offer with 50% discount
		totalPrice[1] = price / 2 + prices[0] + prices[1] - price;
		for (int i = 2; i < n; i++) {
			// Calculate the total price for the current product
			// The first option: we just add the price from the current product
			totalPrice[i] = totalPrice[i - 1] + prices[i];
			double minPrice = Math.min(prices[i], prices[i - 1]);
			// The second option: we apply the offer with 50% discount
			// Then chose the minimum between the first option and the second option
			totalPrice[i] = Math.min(totalPrice[i],
					totalPrice[i - 2] + prices[i - 1] + prices[i]  - minPrice / 2);
			if (i == 2) {
				// The third option: we apply the offer with 100% discount for the first 3 products
				minPrice = Math.min(prices[i] , Math.min(prices[i - 1], prices[i - 2]));
				totalPrice[i] = Math.min(totalPrice[i],
						prices[i - 2] + prices[i - 1] + prices[i] - minPrice);
			}
			if (i >= 3) {
				// The third option: we apply the offer with 100% discount if there are more
				// than 3 products
				// Then chose the minimum between the price calculated before and the third option
				minPrice = Math.min(prices[i] , Math.min(prices[i - 1], prices[i - 2]));
				totalPrice[i] = Math.min(totalPrice[i],
						totalPrice[i - 3] + prices[i - 2] + prices[i - 1] + prices[i] - minPrice);
			}
		}

		return totalPrice[n - 1];
	}
}
