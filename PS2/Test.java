class Parent
{
  static int value1 = 1;
  static
  {
    System.out.print(value1);
  }
  int value2 = 2;
  {
    System.out.print(value2);
  }
}
class Child extends Parent
{
  static int value3 = 3;
  static
  {
    System.out.print(value3);
  }
  int value4 = 4;
  {
    System.out.print(value4);
  }
}
class Test
{
  public static void main(String... args){
  Child c = new Child();
  }
}