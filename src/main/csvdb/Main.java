package csvdb;

public class Main {
  public static void main(String[] args) {
    SvParser parser = new SvParser();
    // System.out.println(parser.parseLine("one,two,three"));
    // System.out.println(parser.parseLine("  one         ,    two ,    three    "));
    // System.out.println(parser.parseLine("  o\\ne         ,  \\two ,    thre\\e  "));
    // System.out.println(parser.parseLine("one, \"tw,o\",three,\",,,\",four"));
  }
}
