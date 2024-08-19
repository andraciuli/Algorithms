import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;



public class Numarare {
	public static final int NMAX = (int) 1e5 + 5;
	public static final int MOD = (int) 1e9 + 7;
	// adj1 = adjacency list of the first graph
	private static ArrayList<Integer>[] adj1 = new ArrayList[NMAX];
	// adj2 = adjacency list of the second graph
	private static ArrayList<Integer>[] adj2 = new ArrayList[NMAX];
	private static int n, m;
	// dp[i] = number of paths
	private static long[] dp = new long[NMAX];
	// visited[i] = true if node i was visited
	private static boolean[] visited = new boolean[NMAX];

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("numarare.in"));
		n = sc.nextInt(); // Number of nodes
		m = sc.nextInt(); // Number of edges

		for (int i = 1; i <= n; i++) {
			adj1[i] = new ArrayList<>();
			adj2[i] = new ArrayList<>();
		}
		// Read first graph
		for (int i = 1; i <= m; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adj1[from].add(to);
		}
		// Read second graph
		for (int i = 1; i <= m; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adj2[from].add(to);
		}
		sc.close();

		dp[n] = 1;
		countPaths(1);
		PrintWriter pw = new PrintWriter("numarare.out");
		pw.println(dp[1]);
		pw.close();
	}
	
	public static void countPaths(int node) {
		Stack<Integer> stack = new Stack<>();
		// Add the current node to the stack
		stack.push(node);
		// Mark the current node as visited
		visited[node] = true;

		while (!stack.isEmpty()) {
			// Get the current node
			int currNode = stack.peek();
			// Assume the current node is a leaf
			boolean isLeaf = true;
			// Iterate over the neighbors of the current node
			for (int neighbor : adj1[currNode]) {
				// Check if the neighbor is connected to the current node in the second graph
				if (found(adj2[currNode], neighbor)) {
					// If the neighbor is not visited, add it to the stack
					// and mark it as visited and set isLeaf to false
					if (!visited[neighbor]) {
						stack.push(neighbor);
						visited[neighbor] = true;
						isLeaf = false;
					}
				}
			}
			// If the current node is a leaf, pop it from the stack
			if (isLeaf) {
				stack.pop();
				// Iterate over the neighbors of the current node
				for (int neighbor : adj1[currNode]) {
					// Check if the neighbor is connected to the current node in the second graph
					if (found(adj2[currNode], neighbor)) {
						// Update the number of paths to the current node
						dp[currNode] = (dp[currNode] + dp[neighbor]) % MOD;
					}
				}
			}
		}
	}

	public static boolean found(ArrayList<Integer> adj, int node) {
		// Check if the node is in the adjacency list
		for (int neighbor : adj) {
			// If the neighbor is the node we are looking for, return true
			if (neighbor == node) {
				return true;
			}
		}
		return false;
	}
}
