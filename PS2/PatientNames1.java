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

  TreeSet<String> malePatients;
  TreeSet<String> femalePatients;

  // --------------------------------------------

  public PatientNames() {
    // Write necessary code during construction;
    //
    // write your answer here

    // --------------------------------------------

    malePatients = new TreeSet<String>();
    femalePatients = new TreeSet<String>();

    // --------------------------------------------
  }

  void AddPatient(String patientName, int gender) {
    // You have to insert the information (patientName, gender)
    // into your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    
    if(gender==1)
       malePatients.add(patientName);
    else femalePatients.add(patientName);

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
    /*
    int ans = 0, flag = 1;
    String newStart, newEnd;
    while (true)
    {
      newStart = malePatients.ceiling(START);
      newEnd = malePatients.floor(END);
      if (newStart==null || newEnd==null) break;
      if(flag==0 && newStart.equals(START))
        START = malePatients.higher(START);
      else START = newStart;
      if(flag==0 && newEnd.equals(END))
        END = malePatients.lower(END);
      else END = newEnd;
      if(START == null || START.compareTo(END) >= 0) break;
      else ans+=2;
      flag = 0;
      //System.out.println(ans);
    }
    */
    return malePatients.subSet(START, END).size();
  }
  
  public int getFemaleCount(String START, String END)
  {
    /*
    int ans = 0, flag = 1;
    String newStart, newEnd;
    while (true)
    {
      newStart = femalePatients.ceiling(START);
      newEnd = femalePatients.floor(END);
      if (newStart==null || newEnd==null) break;
      if(flag==0 && newStart.equals(START))
        START = femalePatients.higher(START);
      else START = newStart;
      if(flag==0 && newEnd.equals(END))
        END = femalePatients.lower(END);
      else END = newEnd;
      if(START == null || START.compareTo(END) >= 0) break;
      else ans+=2;
      flag = 0;
      //System.out.println(ans);
    }
    return ans;
    */
    return femalePatients.subSet(START, END).size();
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
/*
class Patient
{
  private String patientName;
  private int gender;

  public Patient()
  {
    patientName = "";
    gender = 0;
  }
  
  public Patient(String name, int patientGender)
  {
    patientName = name;
    gender = patientGender;
  }
  
  public void setPatientName(String name)
  {
    patientName = name;
  }
  
  public String getPatientName()
  {
    return patientName;
  }
  
  public int getGender()
  {
    return gender;
  }
  
  public void setGender(int gender)
  {
    this.gender = gender;
  }
  
  @Override
  public int compareTo(Object obj)
  {
    if (obj instanceof Patient)
        return this.getPatientName().compareTo((Patient)(obj).getPatientName());
    else return -1;
  }
  
  public String toString()
  {
    return "["+patientName+", "+gender+"]";
  }
}
*/