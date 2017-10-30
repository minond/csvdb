package csvdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Source {
  protected File file;
  protected RandomAccessFile raf;
  protected String eol = System.getProperty("line.separator");

  public Source(String path) {
    file = new File(path);
  }

  public void open() throws IOException {
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }

    raf = new RandomAccessFile(file, "rw");
    raf.getChannel().lock();
  }

  public void close() throws IOException {
    raf.close();
  }

  public String readLine() throws IOException {
    return raf.readLine();
  }

  public String readLine(long offset) throws IOException {
    raf.seek(offset);
    return raf.readLine();
  }

  public void writeLine(String update, int row) throws IOException {
    raf.seek(0);

    StringBuilder buf = new StringBuilder();
    String line = "";
    int lineCounter = 0;

    for (; (line = readLine()) != null; lineCounter++) {
      buf.append(lineCounter == row ? update : line);
      buf.append(eol);
    }

    raf.seek(0);
    raf.writeBytes(buf.toString().trim());
  }

  public void appendLine(String line) throws IOException {
    raf.seek(raf.length());
    raf.writeBytes(eol + line.trim());
  }
}
