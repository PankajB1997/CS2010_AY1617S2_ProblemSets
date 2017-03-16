import java.util.*;
import java.io.*;

// write your matric number here: A0144919W
// write your name here: Pankaj Bhootra
// collaborators: Shailesh Nahata, Ang Ray Yan
// year 2017 hash code: AlaYnzmQ65P4x2Uc559u (do NOT delete this line)

class HospitalRenovation {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private int[][] AdjMatrix; // the graph (the hospital)
  private int[] RatingScore; // the weight of each vertex (rating score of each room)

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  private boolean[] verticesToBeConsidered;
  private String[] AdjList;

  public HospitalRenovation() {
	  
  }

  int Query() {
	if(V==1) return -1;
	AdjList = new String[V];
	  for(int i=0; i<V; i++)
		  AdjList[i] = returnAllVerticesOfVertexI(AdjMatrix[i]);
	verticesToBeConsidered = new boolean[V];
    for(int i=0; i<V; i++) verticesToBeConsidered[i] = true;
    int ans = 1000000, val = ans;
    for(int i=0; i<V; i++)
    {
    	String connectionsOfVertexI;
    	connectionsOfVertexI = AdjList[i];
    	verticesToBeConsidered[i] = false;
    	
    	int j = Integer.parseInt(connectionsOfVertexI.substring(0, connectionsOfVertexI.indexOf(" ")));
    	ArrayList<Integer> visited = BFS(j, i);
    	verticesToBeConsidered[i] = true;
    	//if(!allVisited(visited, connectionsOfVertexI))
		if (visited.size() != V-1)
    	{
			val = RatingScore[i];
			if(val < ans) ans = val;
		}
    }
    if(ans == 1000000) return -1;
    return ans;
  }

//returns all vertices reachable from source
private ArrayList<Integer> BFS(int source, int ignore)
{
	ArrayList<Integer> visited = new ArrayList<Integer>();
	LinkedList<Integer> tempQueue = new LinkedList<Integer>();
	boolean[] visitsMade = new boolean[V];
	
	visited.add(source);
	visitsMade[source] = true;
	tempQueue.offer(source);
	
	String neighbours;
	int item, n;
	
	while(!tempQueue.isEmpty())
	{
		item = tempQueue.poll();
		neighbours = AdjList[item];
		StringTokenizer st = new StringTokenizer(neighbours);
		while(st.hasMoreTokens())
		{
			n = Integer.parseInt(st.nextToken());
			if(n!=ignore && !visitsMade[n] && verticesToBeConsidered[n])
			{
				visitsMade[n] = true;
				visited.add(n);
				tempQueue.offer(n);
			}
		}
	}
	
	return visited;
}

private String returnAllVerticesOfVertexI(int[] VertexIConnections)
  {
	  String ans = "";
	  for(int i=0; i<VertexIConnections.length; i++)
	  {
		  if(VertexIConnections[i]==1) {
			  ans += i + " ";
			  //break;
		  }
	  }
	  return ans;
  }

  // --------------------------------------------

  void run() throws Exception {
    // for this PS3, you can alter this method as you see fit

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int TC = Integer.parseInt(br.readLine()); // there will be several test cases
    while (TC-- > 0) {
      br.readLine(); // ignore dummy blank line
      V = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
      RatingScore = new int[V];
      for (int i = 0; i < V; i++)
        RatingScore[i] = Integer.parseInt(st.nextToken());

      // clear the graph and read in a new graph as Adjacency Matrix
      AdjMatrix = new int[V][V];
      for (int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
          int j = Integer.parseInt(st.nextToken());
          AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
        }
      }

      pr.println(Query());
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    HospitalRenovation ps3 = new HospitalRenovation();
    ps3.run();
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