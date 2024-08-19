import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Trenuri {
	// adj = adjacency list of the graph
	private static Map<String, List<String>> adj = new HashMap<>();
	// memo = memoization table
	private static Map<String, Integer> memo = new HashMap<>();
	private static int m;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("trenuri.in"));
		String line = sc.nextLine();
		String[] parts = line.split(" ");
		final String source = parts[0];
		final String destination = parts[1];
		m = sc.nextInt();
		sc.nextLine();
		// Read the routes
		for (int i = 0; i < m; i++) {
			line = sc.nextLine();
			String[] route = line.split(" ");
			String from = route[0];
			String to = route[1];
			if (!adj.containsKey(from)) {
				adj.put(from, new ArrayList<>());
			}
			adj.get(from).add(to);
		}

		int result = routes(source, destination);
		PrintWriter pw = new PrintWriter("trenuri.out");
		pw.println(result);
		pw.close();
		sc.close();
	}

	public static int routes(String source, String destination) {
		// If the source is the destination, return 1
		if (source.equals(destination)) {
			return 1;
		}

		// Already computed this city
		if (memo.containsKey(source)) {
			// Return the result
			return memo.get(source);
		}

		// Compute the maximum path
		int maxPath = 0;
		if (adj.containsKey(source)) {
			// For each neighbor, compute the maximum path
			for (String neighbor : adj.get(source)) {
				// Maximum path from the neighbor to the destination is
				// calculated recursively by calling the function routes
				// with the neighbor as the source
				// The result is stored in the maxPath variable if it is greater
				// than the current maxPath
				maxPath = Math.max(maxPath, routes(neighbor, destination));
			}
		}

		// Store the result in the memoization table
		// +1 to count the current city
		memo.put(source, maxPath + 1);
		return memo.get(source);
	}
}
