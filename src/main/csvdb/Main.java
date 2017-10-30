package csvdb;

import java.util.List;

public class Main {
  public static final String CMD_READ = "read";
  public static final String CMD_UPDATE = "update";

  public static int getColumnIndex(Parser parser, Source file, String field) throws Exception {
    List<String> headers = parser.parseLine(file.readLine(0l));

    for (int i = 0, len = headers.size(); i < len; i++) {
      if (headers.get(i).equals(field)) {
        return i;
      }
    }

    return -1;
  }

  public static void read(String file, String id, String field) throws Exception {
    Source dataFile = new Source(file);
    Parser parser = new Parser();

    dataFile.open();
    System.out.printf("Searching for record with an id of %s in %s.\n", id, file);

    String line = "";
    int lineCounter = 0;
    int fieldColumn = getColumnIndex(parser, dataFile, field);

    for (; (line = dataFile.readLine()) != null; lineCounter++) {
      List<String> parts = parser.parseLine(line);

      if (parts.get(0).equals(id)) {
        System.out.printf("Found record in line %s\n", lineCounter);
        System.out.printf("Value for %s: %s\n", field, parts.get(fieldColumn));
        break;
      }
    }

    dataFile.close();
  }

  public static void update(String file, String id, String field, String updateValue) throws Exception {
    Source dataFile = new Source(file);
    Parser parser = new Parser();

    dataFile.open();
    System.out.printf("Searching for record with an id of %s in %s and setting %s to %s.\n",
      id, file, field, updateValue);

    String line = "";
    int lineCounter = 0;
    int fieldColumn = getColumnIndex(parser, dataFile, field);

    for (; (line = dataFile.readLine()) != null; lineCounter++) {
      List<String> parts = parser.parseLine(line);

      if (parts.get(0).equals(id)) {
        System.out.printf("Found record in line %s\n", lineCounter);
        System.out.printf("Original value of %s: %s\n", field, parts.get(fieldColumn));

        parts.set(fieldColumn, updateValue);
        String update = parser.encodeLine(parts);
        dataFile.writeLine(update, lineCounter);

        System.out.printf("Updated value of %s: %s\n", field, updateValue);

        break;
      }
    }

    dataFile.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.err.println("Usage: cmd <operation> <file> <id> <field>");
      return;
    }

    switch (args[0]) {
      case CMD_READ:
        if (args.length < 3) {
          System.err.println("Usage: cmd read <file> <id> <field>");
          return;
        } else {
          read(args[1], args[2], args[3]);
          return;
        }

      case CMD_UPDATE:
        if (args.length < 4) {
          System.err.println("Usage: cmd update <file> <id> <field> <update>");
          return;
        } else {
          update(args[1], args[2], args[3], args[4]);
          return;
        }

      default:
        System.err.println("Usage: cmd <operation> <file> <id> <field>");
        return;
    }
  }
}
