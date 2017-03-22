# A bleeding episode, v2017 (Subtask A)

#### Released: Friday, 17th of March 2017, 12:00 noon  
Due: Friday, 31st of March 2017, 11:59 pm

> You are encouraged to work with other students or teaching staffs (inside or outside this module) on solving this problem set. However, you **must** write Java code **by yourself**. In addition, when you write your Java code, you **must** list the names of every collaborator, that is, every other person that you talked to about the problem (even if you only discussed it briefly). This list may include certain posts in [CS2010 Facebook group](https://www.facebook.com/groups/241724769269875/). If you have access to CS2010 files from senior batch (that is, CS2010 problem sets from year 2011-2016), please refrain from looking at their code verbatim or worse... submit your senior's code. Automatic special checks are done especially on the last/hardest subtask to compare older code with this year's version. Any deviation from this policy will be considered as cheating. If the offender is caught beyond reasonable doubt, he/she will be punished severely, including referral to the NUS Board of Discipline. 

## Story

Being a hemophilic, your lecturer Ket Fah will sometimes (hopefully not often) suffer from episodes of spontaneous bleeding (bleeding without trauma). This usually occur in the joint areas and the bleeding must be quickly arrested or it will become a big problem, hurt a lot and take a long time to recover. So when a bleeding episode happens, he will usually need to rush to the hospital as fast as possible to have an injection that will quickly stop the bleeding.

## The Actual Problem Description

Given a map of Singapore (as a directed weighted graph), estimated time<sup>1</sup> to travel through Singapore roads (as _non-negative_ integer weights of the corresponding directed edges -- in minutes, with value not more than 1000), Ket Fah's current position **s**, the chosen hospital **t**, determine the _shortest path_ to go from Ket Fah's current position **s** to the hospital **t** that uses _no more_ than **k** junctions and report the shortest path weight: The sum of edge weights along the shortest path. Ket Fah will call for a taxi or his brother will drive him and both will take this shortest path. It is guaranteed that **s** and **t** are two vertices in the given directed weighted graph. There will be **Q** queries with varying **s**, **t**, and **k** on the same given graph. If somehow there is no path from vertex **s** to vertex **t** that uses no more than **k** junctions in the given graph, output <samp>-1</samp>.

Ket Fah needs the shortest path/quickest way that is also the safest<sup>2</sup> (no more than **k** junctions).

The skeleton program [Bleeding.java](#template) (click to view) that can handle all input/output details is already written for you.

    // Copy paste this Java Template and save it as "Bleeding.java"
    import java.util.*;
    import java.io.*;

    // write your matric number here:
    // write your name here:
    // write list of collaborators here:
    // year 2017 hash code: x8DYWsALaAzykZ8dYPZP (do NOT delete this line)

    class Bleeding {
      private int V; // number of vertices in the graph (number of junctions in Singapore map)
      private int Q; // number of queries
      private ArrayList < ArrayList < IntegerPair > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge

      // if needed, declare a private data structure here that
      // is accessible to all methods in this class
      // --------------------------------------------

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
        //------------------------------------------------------------------------- 

        //------------------------------------------------------------------------- 
      }

      int Query(int s, int t, int k) {
        int ans = -1;

        // You have to report the shortest path from Ket Fah's current position s
        // to reach the chosen hospital t, output -1 if t is not reachable from s
        // with one catch: this path cannot use more than k vertices      
        //
        // write your answer here

        //------------------------------------------------------------------------- 

        return ans;
      }

      // You can add extra function if needed
      // --------------------------------------------

      // --------------------------------------------

      void run() throws Exception {
        // you can alter this method if you need to do so
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
              AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (in minutes) is stored here
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

You just need to implement one (or more<sup>4</sup>) method(s)/function(s):

*   `int Query(int s, int t, int k)`  
    Query the Adjacency List data structure that is already implemented in the skeleton program where the weight (in minutes) of each road (edge) is stored in the Adjacency List itself, and return the required answer.

Now, let's go back to the problem. For example, suppose Singapore<sup>3</sup> a directed weighted graph as shown below (all edges are directed; notice that the pair of directed edges 0?2 (weight 9) and 2?0 (weight 1) that connects vertex 0 and vertex 2 can have different edge weights):

![](/taskfile.php/2916/sample-bef.png)

A sample Singapore map; Create and view this directed weighted graph at [VisuAlgo](http://visualgo.net/sssp.html?create={)<a></a>

`Query(0, 3, 4)`: If Ket Fah's current position is at vertex **s=0**, the chosen hospital is at vertex **t=3**, and we can use up to **k=4** junctions, then the quickest way is this path: <samp>0 ? 1 ? 2 ? 3</samp> with total estimated traveling time of: <samp>2+3+7 = 12</samp> minutes.

![](/taskfile.php/2916/sample-aft-1.png)

The shortest path from **s=0** to **t=3** with no more than **k=4** junctions in this sample Singapore map<a></a>

`Query(1, 0, 4)`: If Ket Fah's current position is at vertex **s=1**, the chosen hospital is at vertex **t=0**, and we can use up to **k=4** junctions, then the quickest way is this path: <samp>1 ? 2 ? 0</samp> with total estimated traveling time of: <samp>3+1 = 4</samp> minutes.

![](/taskfile.php/2916/sample-aft-2.png)

The shortest path from **s=1** to **t=0** with no more than **k=4** junctions in this sample Singapore map<a></a>

`Query(3, 2, 4)`: If Ket Fah's current position is at vertex **s=3**, the chosen hospital is at vertex **t=2**, and we can use up to **k=4** junctions, then there is no path possible, so we output <samp>-1</samp> as our answer.

![](/taskfile.php/2916/sample-aft-3.png)

There is not path from **s=3** to **t=2** with no more than **k=4** junctions in this sample Singapore map<a></a>

## Subtask A Constraints (40 points)

Time Limit: 2s.

On a rather 'impossible case' that simplifies this problem, the road network in Singapore is an <span style="color: red;">undirected weighted tree, 1 = **V** = 1 000 (you can infer **E** from **V**), 1 = **Q** = 100 000, 0 = **s, t** < **V**, and **k=V**</span>. We guarantee that `weight(u, v) = weight(v, u)` for all vertices `u` and `v` in the graph.

![](/taskfile.php/2916/tree-bef.png)

A simplified Singapore Map (Tree); For your convenience, view this undirected weighted tree at [VisuAlgo](http://visualgo.net/sssp.html?create={)<a></a>; This is the visualization of the first test case in the Sample Input

`Query(0, 3, 4)`: If Ket Fah's current position is at vertex **s=0**, the chosen hospital is at vertex **t=3**, and we can use up to **k=4** junctions, then the quickest way is this path: <samp>0 ? 1 ? 2 ? 3</samp> with total estimated traveling time of: <samp>1+5+2 = 8</samp> minutes.

![](/taskfile.php/2916/tree-aft.png)

The shortest path from **s=0** to **t=3** with no more than **k=4** junctions in this simplified Singapore map (Tree)

`Query(0, 2, 4)`: If Ket Fah's current position is at vertex **s=0** and the chosen hospital is at vertex **t=2**, and we can use up to **k=4** junctions, then the quickest way is this path: <samp>0 ? 1 ? 2</samp> with total estimated traveling time of: <samp>1+5 = 6</samp> minutes. The visualization of this path is similar as above.

Hint: Have you seen a PS with insane number of queries like this before?

## Sample Input

<pre>2

4
1   1 1
2   0 1   2 5
2   1 5   3 2
1   2 2
4
0 3 4
0 2 4
0 1 4
0 0 4

2
1   1 7
1   0 7
4
0 0 2
0 1 2
1 0 2
1 1 2
</pre>

## Sample Output

<pre>8
6
1
0

0
7
7
0
</pre>

## Generating Test Data

The given sample input/output are for illustration purpose and are not enough to verify the correctness of your solution.

You are encouraged to generate and post additional test data in [CS2010 Facebook group](https://www.facebook.com/groups/241724769269875/).

Please use [BleedingVerifierA.java](#verifier) (click to view) to verify whether your custom-made test data conform with the required specifications.

    // Copy paste this Java Verifier and save it as "BleedingVerifierA.java"
    // Usage: "java BleedingVerifierA < yourproposedtestcasefilename" and see if this verifier reports anything
    import java.util.*;

    class BleedingVerifierA {
      private static ArrayList < ArrayList < IntegerPair > > AdjList;
      private static ArrayList < Integer > visited;
      private static int[][] AdjMat;

      private static void DFSrec(int u) {
        visited.set(u, 1);
        for (int j = 0; j < AdjList.get(u).size(); j++) {
          IntegerPair v = AdjList.get(u).get(j);
          if (visited.get(v.first()) == 0)
            DFSrec(v.first());
        }
      }

      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, j, k, V, E, w, TC = sc.nextInt(), Q, s, t;

        while (TC-- > 0) {
          V = sc.nextInt();
          if (V < 1 || V > 1000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, V = " + V + ", invalid for Subtask A");
            return;
          }

          AdjMat = new int[V][];
          for (i = 0; i < V; i++)
            AdjMat[i] = new int[V];
          AdjList = new ArrayList < ArrayList < IntegerPair > > ();
          for (E = i = 0; i < V; i++) {
            AdjList.add(new ArrayList < IntegerPair >());
            k = sc.nextInt();
            E += k;

            while (k-- > 0) {
              j = sc.nextInt(); w = sc.nextInt();
              if (w < 0 || w > 1000) {
                System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, EDGE WEIGHT MUST BE NON-NEGATIVE AND AT MOST 1000");
                return;
              }
              AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (length of road) is stored here
              AdjMat[i][j] = w;
            }
          }

          for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
              if (AdjMat[i][j] != AdjMat[j][i]) {
                System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, THE GRAPH IS NOT UNDIRECTED, WEIGHT(u, v) MUST BE EQUAL TO WEIGHT(v, u) FOR ALL VERTICES");
                return;
              }

          visited = new ArrayList < Integer > ();
          visited.addAll(Collections.nCopies(V, 0));
          DFSrec(0);
          for (i = 0; i < V; i++)
            if (visited.get(i) == 0) {
              System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, GRAPH (TREE) MUST BE CONNECTED");
              return;
            }

          Q = sc.nextInt();
          if (Q < 1 || Q > 100000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, TOO MANY QUERIES");
            return;
          }
          while (Q-- > 0) {
            s = sc.nextInt(); t = sc.nextInt(); k = sc.nextInt();
            if (s < 0 || s >= V || t < 0 || t >= V || k != V) {
              System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, WRONG QUERY(" + s + ", " + t + ", " + k);
              return;
            }
          }
        }

        System.out.println("Test data is valid :)");
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

## Problem Author

Dr Steven Halim/Dr Chong Ket Fah  
For CS2010.

## Footnotes

<sup>1</sup>To simplify this problem, let's assume that this time estimation is accurate and there is no traffic jam in Singapore.

<sup>2</sup>The reason of this constraint **k** is because when bleeding starts, you don't want to move the affected joint as much as possible. However with many junctions there will be much starting and stopping of the car and this will affect the injured joint. Thus Ket Fah want the taxi drive or his brother to cross _no more than_ **k** junctions (vertices in Singapore map) along the shortest path from vertex **s** to vertex **t**.

<sup>3</sup>Yes, Singapore map does not looks like this, but let's just assume it is.

<sup>4</sup>If needed, you can write additional helper methods/functions to simplify your code.


# A bleeding episode, v2017 (Subtask B)

## The Actual Problem Description

Please refer to Subtask A for the full problem description.

## Subtask B Constraints (additional 30 points)

Time Limit: 1s.

The road network in Singapore is a <span style="color: red;">directed weighted graph</span>, 1 ≤ **V** ≤ 1 000, <span style="color: red;">0 ≤ **E** ≤ 200 000, 1 ≤ **Q** ≤ 10 000, 0 ≤ **s** ≤ 9</span>, 0 ≤ **t** < **V**, and **k=V**.

In fact, the first examples shown earlier in Subtask A and replicated below fits this description (see the first graph and the first set of queries in Sample Input/Output).

![](/taskfile.php/2917/sample-bef.png)

A sample Singapore map; Create and view this directed weighted graph at [VisuAlgo](http://visualgo.net/sssp.html?create={)<a></a>

`Query(0, 3, 4)`: If Ket Fah's current position is at vertex **s=0**, the chosen hospital is at vertex **t=3**, and we can use up to **k=4** junctions, then the quickest way is this path: <samp>0 → 1 → 2 → 3</samp> with total estimated traveling time of: <samp>2+3+7 = 12</samp> minutes.

![](/taskfile.php/2917/sample-aft-1.png)

The shortest path from **s=0** to **t=3** with no more than **k=4** junctions in this sample Singapore map<a></a>

`Query(1, 0, 4)`: If Ket Fah's current position is at vertex **s=1**, the chosen hospital is at vertex **t=0**, and we can use up to **k=4** junctions, then the quickest way is this path: <samp>1 → 2 → 0</samp> with total estimated traveling time of: <samp>3+1 = 4</samp> minutes.

![](/taskfile.php/2917/sample-aft-2.png)

The shortest path from **s=1** to **t=0** with no more than **k=4** junctions in this sample Singapore map<a></a>

`Query(3, 2, 4)`: If Ket Fah's current position is at vertex **s=3**, the chosen hospital is at vertex **t=2**, and we can use up to **k=4** junctions, then there is no path possible, so we output <samp>-1</samp> as our answer.

![](/taskfile.php/2917/sample-aft-3.png)

There is not path from **s=3** to **t=2** with no more than **k=4** junctions in this sample Singapore map<a></a>

Hint: Have you seen a PS with insane number of queries like this before?

## Sample Input

<pre>2

4
3   1 2   2 9   3 15
1   2 3
2   0 1   3 7
0
3
0 3 4
1 0 4
3 2 4

4
1   1 1
2   0 1   2 5
2   1 5   3 2
1   2 2
4
0 3 4
0 2 4
0 1 4
0 0 4
</pre>

## Sample Output

<pre>12
4
-1

8
6
1
0
</pre>

## Generating Test Data

The given sample input/output are for illustration purpose and are not enough to verify the correctness of your solution.

You are encouraged to generate and post additional test data in [CS2010 Facebook group](https://www.facebook.com/groups/241724769269875/).

Please use [BleedingVerifierB.java](#verifier) (click to view) to verify whether your custom-made test data conform with the required specifications.

    // Copy paste this Java Verifier and save it as "BleedingVerifierB.java"
    // Usage: "java BleedingVerifierB < yourproposedtestcasefilename" and see if this verifier reports anything
    import java.util.*;

    class BleedingVerifierB {
      private static Vector < Vector < IntegerPair > > AdjList;
      private static Vector < Integer > visited;

      private static void DFSrec(int u) {
        visited.set(u, 1);
        for (int j = 0; j < AdjList.get(u).size(); j++) {
          IntegerPair v = AdjList.get(u).get(j);
          if (visited.get(v.first()) == 0)
            DFSrec(v.first());
        }
      }

      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, j, k, V, E, w, TC = sc.nextInt(), Q, s, t;

        while (TC-- > 0) {
          V = sc.nextInt();
          if (V < 1 || V > 1000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, V = " + V + ", invalid for Subtask B");
            return;
          }

          AdjList = new Vector < Vector < IntegerPair > > ();
          for (E = i = 0; i < V; i++) {
            AdjList.add(new Vector < IntegerPair >());
            k = sc.nextInt();
            E += k;

            while (k-- > 0) {
              j = sc.nextInt(); w = sc.nextInt();
              if (w < 0 || w > 1000) {
                System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, EDGE WEIGHT MUST BE NON-NEGATIVE AND AT MOST 1000");
                return;
              }
              AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (length of road) is stored here
            }
          }

          if (E < 0 || E > 200000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, E = " + E + ", invalid for Subtask B");
            return;
          }

          Q = sc.nextInt();
          if (Q < 1 || Q > 10000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, TOO MANY QUERIES");
            return;
          }
          while (Q-- > 0) {
            s = sc.nextInt(); t = sc.nextInt(); k = sc.nextInt();
            if (s < 0 || s >= 10 || t < 0 || t >= V || k != V) {
              System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, WRONG QUERY(" + s + ", " + t + ", " + k);
              return;
            }
          }
        }

        System.out.println("Test data is valid :)");
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

## Problem Author

Dr Steven Halim/Dr Chong Ket Fah  
For CS2010.

# A bleeding episode, v2017 (Subtask C)

## The Actual Problem Description

Please refer to Subtask A for the full problem description.

## Subtask C Constraints (additional 30 points)

Time Limit: 1s.

The road network in Singapore is a directed weighted graph, <span style="color: blue;">2</span> ≤ **V** ≤ 1 000, 0 ≤ **E** ≤ 200 000, <span style="color: red;">1 ≤ **Q** ≤ 20, 0 ≤ **s, t** < **V**, 1 ≤ **k** ≤ min(**V**, 20)</span> <span style="color: blue;">, and **s != t**</span>.

Let's take a relook at the sample graph that has been shown in Subtask A earlier:

![](/taskfile.php/2918/sample-bef.png)

A sample Singapore map; Create and view this directed weighted graph at [VisuAlgo](http://visualgo.net/sssp.html?create={)<a></a>

Now recall that the answer for `Query(0, 3, 4)` is: <samp>0 → 1 → 2 → 3</samp> with total estimated traveling time of: <samp>2+3+7 = 12</samp> minutes. Notice that this path uses 4 junctions (vertices).

![](/taskfile.php/2918/sample-aft-1.png)

The shortest path from **s=0** to **t=3** with no more than **k=4** junctions in this sample Singapore map<a></a>

Now if we have `Query(0, 3, 3)`, then path: <samp>0 → 1 → 2 → 3</samp> shown above is _invalid_ as it uses 4 junctions. The valid shortest path from **s=0** to **t=3** that uses no more than **k=3** junctions is path: <samp>0 → 3</samp> with total estimated traveling time of: <samp>15</samp> minutes. This path is <samp>3</samp> minutes longer than the true shortest path without Ket Fah's restriction of not crossing more than **k** junctions but it is now the best answer<sup>1</sup> for this Subtask C.

![](/taskfile.php/2918/sample-aft-4.png)

The _new_ valid shortest path from **s=0** to **t=3** with no more than **k=3** junctions in this sample Singapore map<a></a>

## Sample Input

<pre>1

4
3   1 2   2 9   3 15
1   2 3
2   0 1   3 7
0
12
0 3 4
0 3 3
0 3 2
0 3 1
1 0 4
1 0 3
1 0 2
1 0 1
3 2 4
3 2 3
3 2 2
3 2 1
</pre>

## Sample Output

<pre>12
15
15
-1
4
4
-1
-1
-1
-1
-1
-1
</pre>

## Generating Test Data

The given sample input/output are for illustration purpose and are not enough to verify the correctness of your solution.

You are encouraged to generate and post additional test data in [CS2010 Facebook group](https://www.facebook.com/groups/241724769269875/).

Please use [BleedingVerifierC.java](#verifier) (click to view) to verify whether your custom-made test data conform with the required specifications.

    // Copy paste this Java Verifier and save it as "BleedingVerifierC.java"
    // Usage: "java BleedingVerifierC < yourproposedtestcasefilename" and see if this verifier reports anything
    import java.util.*;

    class BleedingVerifierC {
      private static Vector < Vector < IntegerPair > > AdjList;
      private static Vector < Integer > visited;

      private static void DFSrec(int u) {
        visited.set(u, 1);
        for (int j = 0; j < AdjList.get(u).size(); j++) {
          IntegerPair v = AdjList.get(u).get(j);
          if (visited.get(v.first()) == 0)
            DFSrec(v.first());
        }
      }

      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, j, k, V, E, w, TC = sc.nextInt(), Q, s, t;

        while (TC-- > 0) {
          V = sc.nextInt();
          if (V < 2 || V > 1000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, V = " + V + ", invalid for Subtask B");
            return;
          }

          AdjList = new Vector < Vector < IntegerPair > > ();
          for (E = i = 0; i < V; i++) {
            AdjList.add(new Vector < IntegerPair >());
            k = sc.nextInt();
            E += k;

            while (k-- > 0) {
              j = sc.nextInt(); w = sc.nextInt();
              if (w < 0 || w > 1000) {
                System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, EDGE WEIGHT MUST BE NON-NEGATIVE AND AT MOST 1000");
                return;
              }
              AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (length of road) is stored here
            }
          }

          if (E < 0 || E > 200000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, E = " + E + ", invalid for Subtask B");
            return;
          }

          Q = sc.nextInt();
          if (Q < 1 || Q > 20) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, TOO MANY QUERIES");
            return;
          }
          while (Q-- > 0) {
            s = sc.nextInt(); t = sc.nextInt(); k = sc.nextInt();
            if (s < 0 || s >= V || t < 0 || t >= V || k < 0 || k > Math.min(V, 20) || s == t) {
              System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, WRONG QUERY(" + s + ", " + t + ", " + k);
              return;
            }
          }
        }

        System.out.println("Test data is valid :)");
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

## Problem Author

Dr Steven Halim/Dr Chong Ket Fah  
For CS2010.

## Footnotes

<sup>1</sup>There is one other possible path from **s=0** to **t=3** that uses no more than **k=3** junctions, path: <samp>0 → 2 → 3</samp>, but it has longer estimated traveling time of: <samp>9+7 = 16</samp> minutes.
