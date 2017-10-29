package csvdb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    System.out.println("Running tests");
    (new ParserTests()).runTests();
    System.out.println("Done, all tests passed!");
  }

  static class Tester {
    public void runTests() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
      String klass = this.getClass().getName();

      for (String method : this.getTests()) {
        System.out.printf("Running %s.%s... ", klass, method);
        Method m = this.getClass().getMethod(method);
        System.out.printf("OK\n");
        m.invoke(this);
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
}
