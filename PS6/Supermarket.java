import java.io.*;

// write your matric number here: A0144919W
// write your name here: PANKAJ BHOOTRA
// write list of collaborators here:
// year 2017 hash code: mZGXoXQ2wBX0FK7WCZun (do NOT delete this line)

class Supermarket {
	private int N; // number of items in the supermarket. V = N+1
	private int K; // the number of items that Ketfah has to buy
	private int[] shoppingList; // indices of items that Ketfah has to buy
	private int[][] T; // the complete weighted graph that measures the direct
						// wheeling time to go from one point to another point
						// in seconds

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------

	// private ArrayList<ArrayList<IntegerPair>> EdgeList;
	private int[][] ans;
	private int[][] D;
	private int V, states;

	public Supermarket() {
		// Write necessary code during construction
		//
		// write your answer here

	}

	void PreProcess() {
		V = N + 1;

		D = new int[V][V];
		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				D[i][j] = T[i][j];

		// Apply Floyd Warshall's Algorithm
		for (int k = 0; k < V; k++)
			// remember, k first
			for (int i = 0; i < V; i++)
				// before i
				for (int j = 0; j < V; j++)
					// then j
					D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
	}

	int Query() {
		// You have to report the quickest shopping time that is measured
		// since Ketfah enters the supermarket (vertex 0),
		// completes the task of buying K items in that supermarket,
		// then reaches the cashier of the supermarket (back to vertex 0).
		//
		// write your answer here

		PreProcess();

		// initialize ans

		states = (int) Math.pow(2, K + 1);
		ans = new int[V][states];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < states; j++) {
				ans[i][j] = Integer.MAX_VALUE;
			}
		}

		return getDistance(0, 1);
	}

	private int getDistance(int source, int itemsTaken) {

		/*
		 * As a k-bit binary, itemsTaken stores which of the K items have been
		 * taken as 1 in binary
		 */

		// If all items are taken, go back to vertex 0
		if (itemsTaken == (1 << K + 1) - 1) {
			return D[source][0];
		}

		if (ans[source][itemsTaken] != Integer.MAX_VALUE) {
			return ans[source][itemsTaken];
		}

		for (int i = 0; i < K; i++) {
			int item = shoppingList[i];
			if (item != source && ((1 << i + 1) & itemsTaken) == 0) {
				ans[source][itemsTaken] = Math.min(
						ans[source][itemsTaken],
						D[source][item]
								+ getDistance(item, itemsTaken | (1 << i + 1)));
			}
		}
		return ans[source][itemsTaken];
	}

	// private void print() {
	// for (int i = 0; i < V; i++) {
	// for (int j = 0; j < V; j++) {
	// System.out.print(D[i][j] + " ");
	// }
	// System.out.println();
	// }
	// }

	void run() throws Exception {
		// do not alter this method to standardize the I/O speed (this is
		// already very fast)
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			// read the information of the complete graph with N+1 vertices
			N = sc.nextInt();
			K = sc.nextInt(); // K is the number of items to be bought

			shoppingList = new int[K];
			for (int i = 0; i < K; i++)
				shoppingList[i] = sc.nextInt();

			T = new int[N + 1][N + 1];
			for (int i = 0; i <= N; i++)
				for (int j = 0; j <= N; j++)
					T[i][j] = sc.nextInt();

			pw.println(Query());
		}

		pw.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		Supermarket ps6 = new Supermarket();
		ps6.run();
	}
}

class IntegerScanner { // coded by Ian Leow, using any other I/O method is not
						// recommended
	BufferedInputStream bis;

	IntegerScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public int nextInt() {
		int result = 0;
		try {
			int cur = bis.read();
			if (cur == -1)
				return -1;

			while ((cur < 48 || cur > 57) && cur != 45) {
				cur = bis.read();
			}

			boolean negate = false;
			if (cur == 45) {
				negate = true;
				cur = bis.read();
			}

			while (cur >= 48 && cur <= 57) {
				result = result * 10 + (cur - 48);
				cur = bis.read();
			}

			if (negate) {
				return -result;
			}
			return result;
		} catch (IOException ioe) {
			return -1;
		}
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Integer _first, _second;

	public IntegerPair(Integer f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(IntegerPair o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else
			return this.second() - o.second();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}
}