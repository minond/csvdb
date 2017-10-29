package csvdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Source {
  protected File file;
  protected RandomAccessFile raf;

  public Source(String path) {
    file = new File(path);
  }

  public void open() throws IOException {
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }

    raf = new RandomAccessFile(file, "rw");
  }

  public void close() throws IOException {
    raf.close();
  }

  public String readLine() throws IOException {
    return raf.readLine();
  }
}
