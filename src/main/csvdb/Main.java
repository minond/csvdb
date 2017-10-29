package csvdb;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("CsvDB");

    Source s = new Source("./data.csv");

    s.open();

    // s.readLine();
    // s.readLine();
    // s.readLine();
    // s.readLine();
    // s.readLine();
    s.readLine();
    s.readLine();
    // s.appendLine("S\n");
    s.writeLine("123\n", 0);
    s.writeLine("123\n", 3);

    s.close();

    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
    // System.out.println(s.readLine());
  }
}
