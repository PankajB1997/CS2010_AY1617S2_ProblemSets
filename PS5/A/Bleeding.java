import java.util.*;
import java.io.*;

//write your matric number here: A0144919W
//write your name here: PANKAJ BHOOTRA
//write list of collaborators here:
//year 2017 hash code: x8DYWsALaAzykZ8dYPZP (do NOT delete this line)

class Bleeding {
	private int V; // number of vertices in the graph (number of junctions in
					// Singapore map)
	private int Q; // number of queries
	private ArrayList<ArrayList<IntegerPair>> AdjList; // the weighted graph
														// (the
														// Singapore map), the
														// length of each edge
														// (road) is stored here
														// too, as the weight of
														// edge

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------

	private int[][] D;
	PriorityQueue<IntegerPair> queue;

	// --------------------------------------------

	public Bleeding() {
		// Write necessary code during construction
		//
		// write your answer here

	}

	void PreProcess() {
		// Write necessary code to preprocess the graph, if needed
		//
		// write your answer here
		// -------------------------------------------------------------------------
		D = new int[V][V];
		for (int x = 0; x < V; x++) {
			for (int y = 0; y < V; y++) {
				D[x][y] = 1000000000;
			}
		}
		for(int i=0; i<V; i++)
		{
					queue = new PriorityQueue<IntegerPair>();
					runModifiedDijkstra(i);
		}
		// -------------------------------------------------------------------------
	}

	int Query(int s, int t, int k) {
		// You have to report the shortest path from Ket Fah's current position s
	    // to reach the chosen hospital t, output -1 if t is not reachable from s
	    // with one catch: this path cannot use more than k vertices      
	    //
	    // write your answer here

		return (D[s][t]==1000000000)?-1:D[s][t];
	}

	// You can add extra function if needed
	// --------------------------------------------

	private int runModifiedDijkstra(int s) {
		//if (s==t) return 0;
		int distance, destination;

		D[s][s] = 0;

		queue.offer(new IntegerPair(D[s][s], s));

		while (!queue.isEmpty()) {
			IntegerPair element = queue.poll();
			distance = element.first();
			destination = element.second();
			if (D[s][destination] == distance) {
					for (IntegerPair v : AdjList.get(destination)) {
						int dest = v.first();
						int wt = v.second();
						if (D[s][dest] > distance + wt) {
							D[s][dest] = distance + wt;
							queue.offer(new IntegerPair(D[s][dest], dest));
						}
					}
			}
		}
		return -1;
	}

	// --------------------------------------------

	void run() throws Exception {
		// you can alter this method if you need to do so
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pr = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt();

			// clear the graph and read in a new graph as Adjacency List
			AdjList = new ArrayList<ArrayList<IntegerPair>>();
			for (int i = 0; i < V; i++) {
				AdjList.add(new ArrayList<IntegerPair>());

				int k = sc.nextInt();
				while (k-- > 0) {
					int j = sc.nextInt(), w = sc.nextInt();
					AdjList.get(i).add(new IntegerPair(j, w)); // edge (road)
																// weight (in
																// minutes) is
																// stored here
				}
			}

			PreProcess(); // optional

			Q = sc.nextInt();
			while (Q-- > 0)
				pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

			if (TC > 0)
				pr.println();
		}

		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		Bleeding ps5 = new Bleeding();
		ps5.run();
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
		if (!this.second().equals(o.second()))
			return this.second() - o.second();
		else
			return this.first() - o.first();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}
}

class IntegerTriple implements Comparable<IntegerTriple> {
	Integer _first, _second, _third;

	public IntegerTriple(Integer f, Integer s, Integer t) {
		_first = f;
		_second = s;
		_third = t;
	}

	public int compareTo(IntegerTriple o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else if (!this.second().equals(o.second()))
			return this.second() - o.second();
		else
			return this.third() - o.third();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}

	Integer third() {
		return _third;
	}
}