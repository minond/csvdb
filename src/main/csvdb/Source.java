package csvdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Source {
  protected BufferedReader buf;

  public Source(String path) throws Exception {
    FileReader fread = new FileReader(path);
    buf = new BufferedReader(fread);
  }

  public String readLine() throws Exception {
    return buf.readLine();
  }
}
