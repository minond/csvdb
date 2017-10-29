package csvdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Source {
  protected BufferedReader buf;

  public Source(String path) {
    try {
      FileReader fread = new FileReader(path);
      buf = new BufferedReader(fread);
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  public String readLine() {
    try {
      return buf.readLine();
    } catch (Exception err) {
      return null;
    }
  }
}
