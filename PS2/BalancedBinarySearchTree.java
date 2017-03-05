import java.util.*;
import java.io.*;

// write your matric number here: A0144919W
// write your name here: Pankaj Bhootra
// write list of collaborators here: 
// year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class PatientNames {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  // --------------------------------------------

  BalancedBinarySearchTree malePatients;
  BalancedBinarySearchTree femalePatients;

  // --------------------------------------------

  public PatientNames() {
    // Write necessary code during construction;
    //
    // write your answer here

    // --------------------------------------------

    malePatients = new BalancedBinarySearchTree();
    femalePatients = new BalancedBinarySearchTree();

    // --------------------------------------------
  }

  void AddPatient(String patientName, int gender) {
    // You have to insert the information (patientName, gender)
    // into your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    
    if(gender==1)
       malePatients.insert(patientName);
    else femalePatients.insert(patientName);

    // --------------------------------------------
  }

  void RemovePatient(String patientName) {
    // You have to remove the patientName from your chosen data structure
    //
    // write your answer here

    // --------------------------------------------

    malePatients.remove(patientName);
    femalePatients.remove(patientName);

    // --------------------------------------------
  }

  int Query(String START, String END, int gender) {
    int ans = 0;

    // You have to answer how many patient name starts
    // with prefix that is inside query interval [START..END)
    //
    // write your answer here

    // --------------------------------------------

    if (gender==1)
       ans += getMaleCount(START, END);
    else if (gender==2)
       ans += getFemaleCount(START, END);
    else ans += getMaleCount(START, END) + getFemaleCount(START, END);

    // --------------------------------------------

    return ans;
  }
  
  public int getMaleCount(String START, String END)
  {
    //START = malePatients.findMinMatch(START);
    //END = malePatients.findMaxMatch(END);
    int endRank = malePatients.rank(END);
    int startRank = malePatients.rank(START);
    return endRank - startRank;
    /*
    if(endRank==startRank && endRank!=0)
      return 1;
    else if(endRank!=startRank && endRank!=0 && startRank!=0)
      return endRank - startRank + 1;
    else return endRank - startRank;
    */
  }
  
  public int getFemaleCount(String START, String END)
  {
    //START = femalePatients.findMinMatch(START);
    //END = femalePatients.findMaxMatch(END);
    int endRank = femalePatients.rank(END);
    int startRank = femalePatients.rank(START);
    return endRank - startRank;
    /*if(endRank==startRank && endRank!=0)
      return 1;
    else if(endRank!=startRank && endRank!=0 && startRank!=0)
      return endRank - startRank + 1;
    else return endRank - startRank;
    */
  }

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      if (command == 0) // end of input
        break;
      else if (command == 1) // AddPatient
        AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
      else if (command == 2) // RemovePatient
        RemovePatient(st.nextToken());
      else // if (command == 3) // Query
        pr.println(Query(st.nextToken(), // START
                         st.nextToken(), // END
                         Integer.parseInt(st.nextToken()))); // GENDER
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    PatientNames ps2 = new PatientNames();
    ps2.run();
  }
}


class Node
{
  Node left;
  Node right;
  Node parent;
  String value;
  int weight;
  int height;
  public Node(String value)
  {
    this.value = value;
    this.weight = 1;
    this.height = 0;
    left = right = parent = null;
  }
}
class BalancedBinarySearchTree
{
  Node root;
  
  public BalancedBinarySearchTree()
  {
    root = null;
  }
  
  public void insert(String newElement)
  {
    Node newNode = new Node(newElement);
    insert(root, newNode);
  }
  private void insert(Node start, Node newElement)
  {
    if(start==null) {
      root = newElement;
    }
    else {
    if(start.value.compareTo(newElement.value)>0)
    {
      if(start.left==null) {
        start.left = newElement;
        start.height = Math.max(height(start.left), height(start.right)) + 1;
        balanceTree(start);
        start.weight++;
      }
      else insert(start.left, newElement);
    }
    else
    {
      if(start.right==null) {
        start.right = newElement;
        start.height = Math.max(height(start.left), height(start.right)) + 1;
        balanceTree(start);
        start.weight++;
      }
      else insert(start.right, newElement);
    }
    }
  }
  
  public Node search(String searchItem)
  {
    return search(root, searchItem);
  }
  private Node search(Node start, String searchItem)
  {
    if(start.value.equals(searchItem)) {
      return start;
    }
    else if(start.value.compareTo(searchItem) > 0)
    {
      if(start.left==null)
        return null;
      else return search(start.left, searchItem);
    }
    else 
    {
      if(start.right==null)
        return null;
      else return search(start.right, searchItem);
    }
  }
  
  public void remove(String newElement)
  {
    remove(root, newElement);
  }
  private void remove(Node start, String newElement)
  {
    if(start==null) return;
    Node nodeToRemove = search(start, newElement);
    if(nodeToRemove==null) return;
    if(nodeToRemove.left==null && nodeToRemove.right==null)
    {
      if(nodeToRemove.parent==null) root = null;
      else
      {
        if(isLeftChildOfParent(nodeToRemove))
          nodeToRemove.parent.left = null;
        else nodeToRemove.parent.right = null;
        balanceTree(nodeToRemove.parent);
      }
      return;
    }
    if(nodeToRemove.left != null)
    {
      Node max = findMax(nodeToRemove.left);
      nodeToRemove.value = max.value;
      remove(max, max.value);
    }
    else
    {
      Node min = findMin(nodeToRemove.right);
      nodeToRemove.value = min.value;
      remove(min, min.value);
    }
  }
  
  private boolean isLeftChildOfParent(Node child)
  {
    if(child.parent != null)
       return child.parent.left==child;
    else return false;
  }
  
  private boolean isRightChildOfParent(Node child)
  {
    if(child.parent != null)
       return child.parent.right==child;
    else return false;
  }
  
  public Node successor(Node node)
  {
    if (node.right != null) return findMin(node.right);
    else
    {
    if(node.parent==null) return null;
    else if(isLeftChildOfParent(node)) {
      return node.parent;      
    }
    else return successor(node.parent);
    }
  }
  
  public Node predecessor(Node node)
  {
    if (node.left != null) return findMax(node.left); 
    else 
    {
    if(node.parent==null) return null;
    else if(isRightChildOfParent(node)) {
      return node.parent;      
    }
    else return predecessor(node.parent);
    }
  }
  
  public Node findMin()
  {
    return findMin(root);
  }
  private Node findMin(Node start) 
  {
    if(start.left==null) return start;
    else return findMin(start.left);
  }
  
  public Node findMax()
  {
    return findMax(root);
  }
  private Node findMax(Node start)
  {
    if(start.right==null) return start;
    else return findMax(start.right);
  }
  
  public Node select(int i)
  {
    return select(i, root);
  }
  private Node select(int i, Node start)
  {
    int q = 1 + start.left.weight;
    if(q == i)
      return start;
    else if(q > i)
      return select(i, start.left);
    else return select(i-q, start.right);
  }
  
  public int rank(String element)
  {
    return rank(element, root);
  }
  private int rank(String element, Node start)
  {
    int rank = 1;
    while(start!=null)
    {
      if(element.compareTo(start.value) < 0)
        start = start.left;
      else if(element.compareTo(start.value) > 0) {
        if(start.left==null) rank += 1;
        else rank += 1 + start.left.weight;
        start = start.right;
      }
      else {
        if(start.left==null) return rank;
        else return rank+start.left.weight;
      }
    }
    return rank;
    /*
    if(start==null || (start.right==null && element.compareTo(start.value) > 0 && element.length() < start.value.length())) return 0;
    if(element.compareTo(start.value) < 0)
    {
      if(start.left == null) return start.weight;
      else return rank(element, start.left);
    }
    else if (start.right == null) return start.weight;
    else return (start.weight - start.right.weight + rank(element, start.right));
    */
  }
  
  public boolean isEmpty()
  {
    return root==null;
  }
  
  private int height(Node node)
  {
    return node==null ? -1 : node.height;
  }
  
  private int balanceFactor(Node node)
  {
    if(node.left==null && node.right==null) return 0;
    else if(node.left==null) return (-1 * node.right.height);
    else if(node.right==null) return node.left.height;
    else return node.left.height - node.right.height;
  }
  
  public void balanceTree(Node start)
  {
        if(balanceFactor(start)==2 && balanceFactor(start.left)>=0 && balanceFactor(start.left)<=1)
        {
          rotateRight(start);
        }
        else if(balanceFactor(start)==2 && balanceFactor(start.left)==-1)
        {
          rotateLeft(start.left);
          rotateRight(start);
        }
        else if(balanceFactor(start)==-2 && balanceFactor(start.right)>=-1 && balanceFactor(start.right)<=0)
        {
          rotateLeft(start);
        }
        else if(balanceFactor(start)==-2 && balanceFactor(start.right)==1)
        {
          rotateRight(start.right);
          rotateLeft(start);
        }
  }
  
  public void rotateRight(Node node)
  {
    Node leftNode = node.left;
    leftNode.parent = node.parent;
    node.parent = leftNode;
    node.left = leftNode.right;
    if(leftNode.right != null) leftNode.right.parent = node;
    leftNode.right = node;
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    leftNode.height = Math.max(height(leftNode.left), height(leftNode.right)) + 1;
  }
  
  public void rotateLeft(Node node)
  {
    Node rightNode = node.right;
    rightNode.parent = node.parent;
    node.parent = rightNode;
    node.right = rightNode.left;
    if(rightNode.left != null) rightNode.left.parent = node;
    rightNode.left = node;
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    rightNode.height = Math.max(height(rightNode.left), height(rightNode.right)) + 1;
  }

  //print by inorder traversal, i.e. in increasingly sorted order of String type
  public void print()
  {
    print(root);
  }
  private void print(Node start)
  {
    if(start!=null){
    print(start.left);
    System.out.print(start.value+" ");
    print(start.right);
    if(start==findMax()) System.out.println();
    }
  }
}