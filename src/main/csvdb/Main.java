package csvdb;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("CsvDB");

    Source s = new Source("./Makefile");

    s.open();

    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
    System.out.println(s.readLine());
  }
}
