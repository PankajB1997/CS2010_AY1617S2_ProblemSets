import java.util.*;
import java.io.*;

// write your matric number here: A0144919W
// write your name here: PANKAJ BHOOTRA
// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)

class EmergencyRoom {
  
  BinaryHeap patients;
  
  public EmergencyRoom() {
    patients = new BinaryHeap();
  }

  void ArriveAtHospital(String patientName, int emergencyLvl) {
    Patient newPatient = new Patient(patientName, emergencyLvl);
    patients.Insert(newPatient);
  }

  void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
    Patient patientToUpdate = patients.findPatientWithName(patientName);
    if (patientToUpdate != null)
    {
        patientToUpdate.setEmergencyLevel(patientToUpdate.getEmergencyLevel() + incEmergencyLvl);
        patients.shiftUp(patientToUpdate.getIndex());
        patients.shiftDown(patientToUpdate.getIndex());
    }
  }

  void Treat(String patientName) {
    //Patient patientToBeTreated = patients.findPatientWithName(patientName);
    //patients.Remove(patientToBeTreated);
    UpdateEmergencyLvl(patientName, 10000);
    patients.ExtractMax();
  }

  String Query() {
    String ans = "The emergency room is empty";

    if (!patients.isEmpty()) {
        ans = patients.getPatientsList().get(1).getPatientName();
    }
    return ans;
  }

  void run() throws Exception {
    // do not alter this method

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
    while (numCMD-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      switch (command) {
        case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 2: Treat(st.nextToken()); break;
        case 3: pr.println(Query()); break;
      }
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    EmergencyRoom ps1 = new EmergencyRoom();
    ps1.run();
  }
}

class Patient
{
  private String patientName;
  private int emergencyLevel;
  private int queueNumber;
  private int index;
  private static int queue = 1;
  
  public Patient()
  {
    patientName = "";
    emergencyLevel = 0;
    index = 0;
    queueNumber = queue++;
  }
  
  public Patient(String name, int emergency)
  {
    patientName = name;
    emergencyLevel = emergency;
    index = -1;
    queueNumber = queue++;
  }
  
  public void setPatientName(String name)
  {
    patientName = name;
  }
  
  public void setEmergencyLevel(int level)
  {
    emergencyLevel = level;
  }
  
  public String getPatientName()
  {
    return patientName;
  }
  
  public int getEmergencyLevel()
  {
    return emergencyLevel;
  }
  
  public int getQueueNumber()
  {
    return queueNumber;
  }
  
  public int getIndex()
  {
    return index;
  }
  
  public void setIndex(int index)
  {
    this.index = index;
  }
  
  public String toString()
  {
    return "["+patientName+", "+emergencyLevel+", "+queueNumber+"]";
  }
}

class BinaryHeap {
  private ArrayList<Patient> A;
  private HashMap<String, Patient> patientNames;
  private int BinaryHeapSize;

  BinaryHeap() {
    A = new ArrayList<Patient>();
    A.add(new Patient()); // dummy
    BinaryHeapSize = 0;
    patientNames = new HashMap<String, Patient>();
  }

  BinaryHeap(Patient[] array) {
    CreateHeap(array);
    patientNames = new HashMap<String, Patient>();
  }

  int parent(int i) { return i>>1; } // shortcut for i/2, round down
  
  int left(int i) { return i<<1; } // shortcut for 2*i
  
  int right(int i) { return (i<<1) + 1; } // shortcut for 2*i + 1
  
  void shiftUp(int i) {
    while (i > 1 && (A.get(parent(i)).getEmergencyLevel() < A.get(i).getEmergencyLevel() || (A.get(parent(i)).getEmergencyLevel()==A.get(i).getEmergencyLevel() && A.get(parent(i)).getQueueNumber()>A.get(i).getQueueNumber()))) {
      Patient temp = A.get(i);
      Patient patient = A.get(parent(i));
      temp.setIndex(parent(i));
      patient.setIndex(i);
      A.set(i, patient);
      A.set(parent(i), temp);
      i = parent(i);
    }
  }

  void Insert(Patient key) {
    BinaryHeapSize++;
    patientNames.put(key.getPatientName(), key);
    if (BinaryHeapSize >= A.size())
      A.add(key);
    else
      A.set(BinaryHeapSize, key);
    key.setIndex(BinaryHeapSize);
    shiftUp(BinaryHeapSize);
  }

  void shiftDown(int i) {
    Patient patient = A.get(i);
    Patient temp;
    patient.setIndex(i);
    while (i <= BinaryHeapSize) {
      int maxV, leftChildVal, rightChildVal, max_id, max_left_id, max_right_id;
      A.get(i).setIndex(i);
      maxV = A.get(i).getEmergencyLevel();
      leftChildVal = A.get(i).getEmergencyLevel();
      rightChildVal = A.get(i).getEmergencyLevel();
      max_id = i;
      max_left_id = i;
      max_right_id = i;
      if (left(i) <= BinaryHeapSize && (maxV < A.get(left(i)).getEmergencyLevel() || (A.get(left(i)).getEmergencyLevel()==maxV && A.get(left(i)).getQueueNumber()<A.get(i).getQueueNumber()))) { // compare value of this node with its left subtree, if possible
        leftChildVal = A.get(left(i)).getEmergencyLevel();
        max_left_id = left(i);
      }
      if (right(i) <= BinaryHeapSize && (maxV < A.get(right(i)).getEmergencyLevel() || (A.get(right(i)).getEmergencyLevel()==maxV && A.get(right(i)).getQueueNumber()<A.get(i).getQueueNumber()))) { // now compare with its right subtree, if possible
        rightChildVal = A.get(right(i)).getEmergencyLevel();
        max_right_id = right(i);
      }
      //choose the larger of the two children as maxV and max_id
      if (leftChildVal < rightChildVal) {
        maxV = rightChildVal;
        max_id = max_right_id;
      }
      else if (leftChildVal > rightChildVal) {
        maxV = leftChildVal;
        max_id = max_left_id;
      }
      else {
        if (A.get(max_left_id).getQueueNumber() < A.get(max_right_id).getQueueNumber()) {
          maxV = leftChildVal;
          max_id = max_left_id;
        }
        else {
          maxV = rightChildVal;
          max_id = max_right_id;
        }
      }
      if (max_id != i) {
        patient = A.get(i);
        temp = A.get(max_id);
        patient.setIndex(max_id);
        temp.setIndex(i);
        A.set(i, temp);
        A.set(max_id, patient);
        i = max_id;
      }
      else
        break;
    }
  }
  
  Patient ExtractMax() {
    Patient maxV = A.get(1);    
    maxV.setIndex(1);
    patientNames.remove(maxV.getPatientName());
    A.set(1, A.get(BinaryHeapSize));
    BinaryHeapSize--; // virtual decrease
    shiftDown(1);  
    return maxV;
  }
  
  void CreateHeapSlow(Patient[] arr) { // the O(N log N) version, array arr is 0-based
    A = new ArrayList<Patient>();
    A.add(new Patient()); // dummy, this BinaryHeap is 1-based
    for (int i = 1; i <= arr.length; i++)
      Insert(arr[i-1]);
  }
  
  void CreateHeap(Patient[] arr) { // the O(N) version, array arr is 0-based
    BinaryHeapSize = arr.length;
    A = new ArrayList<Patient>();
    A.add(new Patient()); // dummy, this BinaryHeap is 1-based
    for (int i = 1; i <= BinaryHeapSize; i++) // copy the content
      A.add(arr[i-1]);
    for (int i = parent(BinaryHeapSize); i >= 1; i--)
      shiftDown(i);
  }

  ArrayList<Patient> HeapSort(Patient[] arr) { // array arr is 0-based
    CreateHeap(arr);
    int N = arr.length;
    for (int i = 1; i <= N; i++)
      A.set(N-i+1, ExtractMax());
    return A; // ignore the first index patient
  }
  
  //new method added to BinaryHeap code to look for a particular Patient object given the patient name
  //Pankaj Bhootra - A0144919W
  Patient findPatientWithName(String patientName) {
    return patientNames.get(patientName);
  }
  
  ArrayList<Patient> getPatientsList() {
    return A;
  }    
  
  //for testing only
  void Print() {
    for(int i=1; i<=BinaryHeapSize; i++)
      System.out.print(A.get(i)+", ");
    System.out.println();
  }
  
  int size() { return BinaryHeapSize; }
  
  boolean isEmpty() { return BinaryHeapSize == 0; }
}