package csvdb;

import java.util.ArrayList;
import java.util.List;

public class SvParser {
  public static final String DELIM_COMMA = ",";

  protected String delimeter;
  protected boolean escapable;

  public SvParser() {
    this(DELIM_COMMA, true);
  }

  public SvParser(String delimeter) {
    this(delimeter, true);
  }

  public SvParser(String delimeter, boolean escapable) {
    this.delimeter = delimeter;
    this.escapable = escapable;
  }

  public List<String> parseLine(String line) {
    int len = line.length();
    boolean open = true;

    StringBuilder buf = new StringBuilder();
    List<String> parts = new ArrayList<String>();

    for (int i = 0; i < len; i++) {
      char letter = line.charAt(i);

      switch (letter) {
        case '"':
          break;

        case ',':
          if (open) {
            parts.add(buf.toString().trim());
            buf = new StringBuilder();
          } else {
            open = true;
          }

          break;

        case '\\':
          // Escape, but can we peek at the next letter? I don't know why we
          // would have an escape char at EOL, so let's just ignore it in this
          // case.
          if (i + 1 < len) {
            buf.append(line.charAt(++i));
          }

          break;

        default:
          buf.append(letter);
          break;
      }

      if (i + 1 == len) {
        parts.add(buf.toString().trim());
      }
    }

    return parts;
  }
}
