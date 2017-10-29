package csvdb;

import java.util.Arrays;

public class Test {
  protected SvParser parser = new SvParser();

  public static void main(String[] args) {
    Test test = new Test();

    test.testSimpleList();
    test.testSpacedElements();
    test.testEscapedCharacters();
    test.testDelimeterInQuotes();

    System.out.println("Done, all tests passed!");
  }

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
