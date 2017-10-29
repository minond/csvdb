package csvdb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
  public static void main(String[] args) throws Exception {
    System.out.println("Running tests");
    (new ParserTests()).runTests();
    (new SourceTests()).runTests();
    System.out.println("Done, all tests passed!");
  }

  static class Tester {
    public void runTests() throws Exception {
      String klass = this.getClass().getName();

      for (String method : this.getTests()) {
        System.out.printf("Running %s.%s... ", klass, method);
        Method m = this.getClass().getMethod(method);
        m.invoke(this);
        System.out.printf("OK\n");
      }
    }

    public List<String> getTests() {
      List<String> tests = new ArrayList<String>();

      Class klass = this.getClass();
      Method[] methods = klass.getDeclaredMethods();

      for (int i = 0; i < methods.length; i++) {
        if (methods[i].getName().startsWith("test")) {
          tests.add(methods[i].getName());
        }
      }

      return tests;
    }
  }

  static class ParserTests extends Tester {
    protected Parser parser = new Parser();

    public void testSimpleList() {
      assert Arrays.asList("one", "two", "three")
        .equals(parser.parseLine("one,two,three"));
    }

    public void testSpacedElements() {
      assert Arrays.asList("one", "two", "three")
        .equals(parser.parseLine("   one   ,   two  ,  three  "));
    }

    public void testEscapedCharacters() {
      assert Arrays.asList("one", "two", "three")
        .equals(parser.parseLine("  o\\ne         ,  \\two ,    thre\\e  "));
    }

    public void testDelimeterInQuotes() {
      assert Arrays.asList("one", "tw,o", "three", ",,,", "four")
        .equals(parser.parseLine("one, \"tw,o\",three,\",,,\",four"));
    }
  }

  static class SourceTests extends Tester {
    protected Source makefile;

    public SourceTests() throws Exception {
      makefile = new Source("./Makefile");
      makefile.open();
    }

    public void testReadingLines() throws Exception {
      assert makefile.readLine().equals(".PHONY: build test");
      assert makefile.readLine().equals("");
      assert makefile.readLine().equals("build: prep");
      assert makefile.readLine().equals("\tjavac -d classes src/main/csvdb/*.java");
      assert makefile.readLine().equals("\tjava -cp ./classes csvdb/Main");
      assert makefile.readLine().equals("");
    }
  }
}
