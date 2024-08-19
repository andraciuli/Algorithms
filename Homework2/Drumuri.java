import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Drumuri {
	public static final int NMAX = (int) 1e5 + 5;
	private static int n;
	private static int m;

	public static class Pair implements Comparable<Pair> {
		public int destination;
		public long cost;

		Pair(int _destination, long _cost) {
			destination = _destination;
			cost = _cost;
		}

		@Override
		public int compareTo(Pair rhs) {
			return Long.compare(cost, rhs.cost);
		}
	}

	private static ArrayList<Pair>[] adj = new ArrayList[NMAX];
	private static ArrayList<Pair>[] adjReverse = new ArrayList[NMAX];

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("drumuri.in"));
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++) {
			adjReverse[i] = new ArrayList<>();
		}

		// Read the graph and the reversed graph
		for (int i = 1; i <= m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			long w = sc.nextInt();
			adj[x].add(new Pair(y, w));
			adjReverse[y].add(new Pair(x, w));
			sc.nextLine();
		}
		// Read x, y, z numbers
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		sc.close();

		long result = drumuri(x, y, z);

		PrintWriter pw = new PrintWriter("drumuri.out");
		pw.println(result);
		pw.close();
	}

	public static long drumuri(int x, int y, int z) {
		long[] distFromX = dijkstra(x, adj);  // Get distance array from x
		long[] distFromY = dijkstra(y, adj);  // Get distance array from y
		long[] distFromZ = dijkstra(z, adjReverse);  // Get distance array to z

		long minCost = Long.MAX_VALUE;
		// Iterate through all nodes
		for (int i = 1; i <= n; i++) {
			// If the node is reachable from x, y and z
			if (distFromX[i] != -1 && distFromY[i] != -1 && distFromZ[i] != -1) {
				// Compute the cost of the path
				long currentCost = distFromX[i] + distFromY[i] + distFromZ[i];
				// Update the minimum cost
				if (currentCost < minCost) {
					minCost = currentCost;
				}
			}
		}
		// If the minimum cost is still Long.MAX_VALUE, it means there is no path
		// Set it to -1
		if (minCost == Long.MAX_VALUE) {
			return -1;
		}

		return minCost;
	}

	/**
		Dijkstra's algorithm to find the shortest path from a source node to all other nodes
		implemented using a priority queue like in the lab
	   * @param source - the source node
	   * @param adj - the adjacency list of the graph
	   * @return - the distance array from the source node to all other nodes
	 **/

	private static long[] dijkstra(int source, ArrayList<Pair>[] adj) {
		long[] dist = new long[n + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[source] = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(source, 0));

		while (!pq.isEmpty()) {
			Pair current = pq.poll();
			int node = current.destination;
			long cost = current.cost;

			if (cost > dist[node]) {
				continue;
			}

			for (Pair neighbor : adj[node]) {
				long newCost = dist[node] + neighbor.cost;
				if (newCost < dist[neighbor.destination]) {
					dist[neighbor.destination] = newCost;
					pq.add(new Pair(neighbor.destination, newCost));
				}
			}
		}

		// If the distance is still Long.MAX_VALUE, it means the node is unreachable
		// Set it to -1
		for (int i = 1; i <= n; i++) {
			if (dist[i] == Long.MAX_VALUE) {
				dist[i] = -1;
			}
		}

		return dist;
	}
}
