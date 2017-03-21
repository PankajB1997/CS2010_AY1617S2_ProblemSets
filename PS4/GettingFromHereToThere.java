import java.util.*;
import java.io.*;

// write your matric number here: A0144919W
// write your name here: Pankaj Bhootra
// year 2017 hash code: x4gxK7xzMSlNvFsMEUVn (do NOT delete this line)

class GettingFromHereToThere {
  private int V; // number of vertices in the graph (number of rooms in the building)
  private ArrayList < ArrayList < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------

  //Minimum Spanning Tree to store just the easiest paths from one vertex to another, using Adjacency List DS
  private ArrayList < ArrayList < IntegerPair > > MST;
  private int[][] maxWeightsAlongEasiestPaths;
  private boolean[] visitedVertices;

  // --------------------------------------------

  public GettingFromHereToThere() {
    // Write necessary codes during construction;
    //
    // write your answer here
	  
  }

//Get MST using Prim's Algorithm
  private void getMST() {
	//initialize MST
	for(int i=0; i<V; i++)
	{
		MST.add(new ArrayList<IntegerPair>());
	}
	//process MST
	PriorityQueue<IntegerTriple> pq = new PriorityQueue<IntegerTriple>();
	boolean[] visited = new boolean[V];
	//mark starting vertex 0 as visited
	visited[0] = true;
	//fetch and add all neighbours of 0 to the queue
	ArrayList<IntegerPair> neighbours = AdjList.get(0);
	int from, to, weight, from2, to2, weight2;
	for(int i=0; i<neighbours.size(); i++)
	{
		to = neighbours.get(i).first();
		weight = neighbours.get(i).second();
		IntegerTriple set = new IntegerTriple(weight, 0, to); //weight should be first as IntegerTriple sorts in increasing order of first and we need to sort in increasing order of weight
		pq.offer(set);
	}
	//add the other nodes only if they are not yet visited
	while(!pq.isEmpty())
	{
		IntegerTriple front = pq.poll();
		weight = front.first();
		from = front.second();
		to = front.third();
		if(!visited[to])
		{
			visited[to] = true;
			MST.get(to).add(new IntegerPair(from, weight));
			MST.get(from).add(new IntegerPair(to, weight));
			neighbours = AdjList.get(to);
			for(int i=0; i<neighbours.size(); i++)
			{
				from2 = to;
				to2 = neighbours.get(i).first();
				weight2 = neighbours.get(i).second();
				if(!visited[to2])
				{
					pq.offer(new IntegerTriple(weight2, from2, to2));
				}
			}
		}
	}
  }
  
  private void DFS(int maxWeight, int source, int destination)
  {
	visitedVertices[destination] = true;
	ArrayList<IntegerPair> neighboursOfCurrentVertex = MST.get(destination);
	int neighbour, weight;
	for(int i=0; i<neighboursOfCurrentVertex.size(); i++)
	{
		neighbour = MST.get(destination).get(i).first();
		weight = MST.get(destination).get(i).second();
		if(!visitedVertices[neighbour])
		{
			maxWeightsAlongEasiestPaths[source][neighbour] = (weight > maxWeight) ? weight : maxWeight;
			DFS(maxWeightsAlongEasiestPaths[source][neighbour], source, neighbour);
		}
	}
  }

  
  void PreProcess() {
	//get minimum spanning tree using Prim's algorithm
	//get max weight along easiest path for all combinations of source and destination vertices
	//using the MST
	//and store them in the below 2D array
    MST = new ArrayList < ArrayList < IntegerPair > > ();
	maxWeightsAlongEasiestPaths = new int[10][V]; //source limited to [0...9]
	getMST();
	for(int i=0; i<Math.min(10, V); i++)
	{
		visitedVertices = new boolean[V];
		DFS(0, i, i);
	}
  }

  int Query(int source, int destination) {
    int ans = 0;
    
    ans = maxWeightsAlongEasiestPaths[source][destination];
    
    return ans;
  }

  //You can add extra function if needed
  // --------------------------------------------


  
  // --------------------------------------------
  
  void run() throws Exception {
    // do not alter this method
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // clear the graph and read in a new graph as Adjacency List
      AdjList = new ArrayList < ArrayList < IntegerPair > >();
      for (int i = 0; i < V; i++) {
        AdjList.add(new ArrayList < IntegerPair >());

        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt(), w = sc.nextInt();
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
        }
      }

      PreProcess(); // you may want to use this function or leave it empty if you do not need it

      int Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt()));
      pr.println(); // separate the answer between two different graphs
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    GettingFromHereToThere ps4 = new GettingFromHereToThere();
    ps4.run();
  }
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
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
    }
    catch (IOException ioe) {
      return -1;
    }
  }
}



class IntegerPair implements Comparable < IntegerPair > {
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

  Integer first() { return _first; }
  Integer second() { return _second; }
}



class IntegerTriple implements Comparable < IntegerTriple > {
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

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
}