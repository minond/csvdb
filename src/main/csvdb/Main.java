package csvdb;

import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner io = new Scanner(System.in);
    Source dataFile = new Source("./data.csv");
    Parser parser = new Parser();

    System.out.printf("Id: ");
    String id = io.next();
    io.close();
    System.out.printf("Searching for record with an id of %s\n", id);

    String line = "";
    int lineCounter = 0;

    dataFile.open();

    for (; (line = dataFile.readLine()) != null; lineCounter++) {
      List<String> parts = parser.parseLine(line);

      if (parts.get(0).equals(id)) {
        System.out.printf("Found record in line %s: ", lineCounter);
        System.out.println(parts);
        System.out.println("Incrementing age value by one");

        int age = Integer.parseInt(parts.get(3)) + 1;
        parts.set(3, String.valueOf(age));

        String update = parser.encodeLine(parts);
        dataFile.writeLine(update, lineCounter);

        break;
      }
    }

    dataFile.close();
  }
}
