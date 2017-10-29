package csvdb;

import java.util.ArrayList;
import java.util.List;

public class Parser {
  protected char delimeter;

  public Parser() {
    this(',');
  }

  public Parser(char delimeter) {
    this.delimeter = delimeter;
  }

  public List<String> parseLine(String line) {
    int len = line.length();
    boolean open = false;

    StringBuilder buf = new StringBuilder();
    List<String> parts = new ArrayList<String>();

    for (int i = 0; i < len; i++) {
      char letter = line.charAt(i);

      if (letter == '"') {
        open = !open;
      } else if (letter == delimeter) {
        if (open) {
          buf.append(letter);
        } else {
          parts.add(buf.toString().trim());
          buf = new StringBuilder();
        }
      } else if (letter == '\\') {
        // Escape, but can we peek at the next letter? I don't know why we
        // would have an escape char at EOL, so let's just ignore it in this
        // case.
        if (i + 1 < len) {
          buf.append(line.charAt(++i));
        }
      } else {
        buf.append(letter);
      }

      if (i + 1 == len) {
        parts.add(buf.toString().trim());
      }
    }

    return parts;
  }
}
