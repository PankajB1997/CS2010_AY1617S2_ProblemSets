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
    //System.out.println("----------------------------");
    return ans;
  }
  
  public int getMaleCount(String START, String END)
  {
    START = malePatients.findMinMatch(START);
    END = malePatients.findMaxMatch(END);
    int endRank = malePatients.rank(END);
    int startRank = malePatients.rank(START);
    //System.out.println("1:-  "+START+": "+startRank+"    "+END+": "+endRank);
    if(START.equals("") || END.equals("")) return 0;
    if(START.equals(END)) return 1;
    return endRank - startRank + 1;
  }
  
  public int getFemaleCount(String START, String END)
  {
    START = femalePatients.findMinMatch(START);
    END = femalePatients.findMaxMatch(END);
    int endRank = femalePatients.rank(END);
    int startRank = femalePatients.rank(START);
    //System.out.println("2:-  "+START+": "+startRank+"    "+END+": "+endRank);
    if(START.equals("") || END.equals("") || START.equals("blah") || END.equals("blah")) return 0;
    if(START.equals(END)) return 1;
    return endRank - startRank + 1;
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
  int balanceFactor;
  public Node(String value)
  {
    this.value = value;
    this.weight = 1;
    this.height = 0;
    this.balanceFactor = 0;
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
    if(root == null) {
      root = newElement;
    }
    else 
    {
      while(true) 
      {
         boolean insertLeft = start.value.compareTo(newElement.value) > 0;
         Node parent = start;
         start = insertLeft ? start.left : start.right;
         if (start == null) 
         {
           if (insertLeft)
              parent.left = newElement;
            else parent.right = newElement;
            newElement.parent = parent;
            //System.out.println(parent.value+": "+parent.balanceFactor+" "+(parent.parent==null));
            Node temp = parent;
            while(temp!= null){
               temp.weight++;
               temp = temp.parent;
            }
            balanceTree(parent);
            break;
         }
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
    if(root==null) return;
    //System.out.println(newElement + " " + root.value);
    Node nodeToRemove = search(root, newElement);
    remove(nodeToRemove);
  }
  private void remove(Node nodeToRemove)
  {
    if(nodeToRemove == null) return;
    if(nodeToRemove.left == null && nodeToRemove.right == null)
    {
       if(nodeToRemove.parent == null) 
         root = null;
       else
       {
         Node parent = nodeToRemove.parent;
         if(parent.left == nodeToRemove)
            parent.left = null;
         else parent.right = null;
         balanceTree(parent);
       }
       return;
    }
    if(nodeToRemove.left!=null)
    {
       Node child = nodeToRemove.left;
       while (child.right!=null) 
         child = child.right;
       nodeToRemove.value = child.value;
       remove(child);
    }
    else
    {
       Node child = nodeToRemove.right;
       while (child.left!=null) child = child.left;
       nodeToRemove.value = child.value;
       remove(child);
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
    if (element.equals("")) return 0;
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
  }
  
  public boolean isEmpty()
  {
    return root==null;
  }
  
  private int height(Node node)
  {
    return node==null ? -1 : node.height;
  }
  
  public void setBalance(Node... nodes)
  {
    for (Node node : nodes)
    {
        setHeight(node);
        setWeight(node);
        node.balanceFactor = height(node.left) - height(node.right);
    }
  }
  
  private void setHeight(Node node)
  {
    if(node != null)
      node.height = 1 + Math.max(height(node.left), height(node.right));
  }
  
  private void setWeight(Node node)
  {
    if(node != null)
      node.weight = 1 + weight(node.left) + weight(node.right);
  }
  
  private int weight(Node node)
  {
    return node==null ? 0 : node.weight;
  }
  
  public void balanceTree(Node start)
  {
  //  System.out.println(start.value+": "+start.balanceFactor+" "+(start.parent==null));
    setBalance(start); //joshua.bf = -2, peter.bf = 1
  //  System.out.println(start.value+": "+start.balanceFactor+" "+(start.parent==null));
    if(start.balanceFactor==2)
    {
      if (start.left.balanceFactor>=0 && start.left.balanceFactor<=1)
          start = rotateRight(start);
      else if(start.left.balanceFactor==-1)
          start = rotateLeftThenRight(start);
    }
    else if(start.balanceFactor==-2)
    {
      //System.out.println(start.right.balanceFactor);
      if (start.right.balanceFactor>=-1 && start.right.balanceFactor<=0)
          start = rotateLeft(start);
      else if(start.right.balanceFactor==1)
          start = rotateRightThenLeft(start);
    }
    if (start.parent != null)
      balanceTree(start.parent);
    else root = start;
  }
  
  private Node rotateRight(Node node)
  {
    Node leftNode = node.left;
    leftNode.parent = node.parent;
    node.left = leftNode.right;
    if(node.left != null) node.left.parent = node;
    leftNode.right = node;
    node.parent = leftNode;
    if (leftNode.parent != null) 
    {
      if (leftNode.parent.right == node)
        leftNode.parent.right = leftNode;
      else leftNode.parent.left = leftNode;
    }
    setBalance(node, leftNode);
    return leftNode;
  }
  
  private Node rotateLeft(Node node)
  {
    Node rightNode = node.right;
    rightNode.parent = node.parent;
    node.right = rightNode.left;
    if(node.right != null)
       node.right.parent = node;
    rightNode.left = node;
    node.parent = rightNode;
    if(rightNode.parent != null)
    {
       if(rightNode.parent.right == node)
           rightNode.parent.right = rightNode;
       else rightNode.parent.left = rightNode;
    }
    setBalance(node, rightNode);
    return rightNode;
  }
  
  private Node rotateLeftThenRight(Node node)
  {
    node.left = rotateLeft(node.left);
    return rotateRight(node);
  }
  
  private Node rotateRightThenLeft(Node node) 
  {
    node.right = rotateRight(node.right);
    return rotateLeft(node);
  }
  
  public String findMinMatch(String element)
  {
    return findMinMatch(element, root);
  }
  private String findMinMatch(String element, Node start)
  {
    if(start==null) return "";
    if(start.value.equals(element)) return start.value;
    else if(start.value.compareTo(element) > 0)
    {
      if(start.left==null) return start.value;
      else return findMinMatch(element, start.left);
    }
    else
    {
      String str = (start.parent==null) ? "" : start.parent.value;
      if (start.right==null) 
      {
        if(start.parent!=null && start.parent.value.compareTo(element) > 0) return start.parent.value;
        else return "";
      }
      else
      {
        String str2 = findMinMatch(element, start.right);
        if(!str2.equals("") && str2.compareTo(element) >= 0) return str2;
        else return str;
      }
    }
  }
  
  public String findMaxMatch(String element)
  {
    return findMaxMatch(element, root);
  }
  private String findMaxMatch(String element, Node start)
  {
    if(start==null) return "";
    if(start.value.equals(element)) {
      //System.out.println("1");
      if(start.parent != null) return start.parent.value;
      else return "";
    }
    else if(start.value.compareTo(element) < 0)
    {
      if(start.right==null) return start.value;
      else return findMaxMatch(element, start.right);
    }
    else 
    {
      String str = (start.parent==null) ? "" : start.parent.value;
      if (start.left==null){
        if(start.parent!=null && start.parent.value.compareTo(element) < 0) return start.parent.value;
        else {
          return "";
        }
      }
      else{
        String str2 = findMaxMatch(element, start.left);
        if(!str2.equals("") && str2.compareTo(element) < 0)
          return str2;
        else {
          //System.out.println("3");
          return str;
        }
      }
    }
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