package org.sqlite;

import com.sun.jna.Pointer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/*
 blob   0         offset    size
 byte[] 0   offset        length
 */
public class Blob {
  private final Conn c;
  private Pointer pBlob;
  private int readOffset;
  private int writeOffset;
  private int size;

  Blob(Conn c, Pointer pBlob) {
    this.c = c;
    this.pBlob = pBlob;
  }

  /**
   * @return the size of an opened BLOB
   */
  public int getBytes() throws SQLiteException {
    checkOpen();
    if (size < 0) {
      size = SQLite.sqlite3_blob_bytes(pBlob);
    }
    return size;
  }

  public int read(ByteBuffer b) throws SQLiteException {
    if (b == null) {
      throw new NullPointerException();
    }
    checkOpen();
    final int n = b.remaining();
    final int res = SQLite.sqlite3_blob_read(pBlob, b, n, readOffset);
    if (res != SQLite.SQLITE_OK) {
      throw new SQLiteException(c, "error while reading blob", res);
    }
    readOffset += n;
    return n;
  }

  public int write(ByteBuffer b) throws SQLiteException {
    if (b == null) {
      throw new NullPointerException();
    }
    checkOpen();
    final int n = b.remaining();
    final int res = SQLite.sqlite3_blob_write(pBlob, b, n, writeOffset);
    if (res != SQLite.SQLITE_OK) {
      throw new SQLiteException(c, "error while writing blob", res);
    }
    writeOffset += n;
    return n;
  }

  public void reopen(long iRow) throws SQLiteException {
    checkOpen();
    final int res = SQLite.sqlite3_blob_reopen(pBlob, iRow);
    if (res != SQLite.SQLITE_OK) {
      throw new SQLiteException(c, "error while reopening blob", res);
    }
    readOffset = 0;
    writeOffset = 0;
    size = -1;
  }

  public int close() {
    if (pBlob == null) {
      return SQLite.SQLITE_OK;
    }
    final int res = SQLite.sqlite3_blob_close(pBlob);
    if (res == SQLite.SQLITE_OK) {
      pBlob = null;
    }
    return res;
  }

  public boolean isClosed() {
    return pBlob == null;
  }
  public void checkOpen() throws SQLiteException {
    if (isClosed()) {
      throw new SQLiteException(c, "blob already closed", ErrCodes.WRAPPER_SPECIFIC);
    }
  }

  private void check(int res, String reason) throws SQLiteException {
    if (res != SQLite.SQLITE_OK) {
      throw new SQLiteException(c, reason, res);
    }
  }

  private class BlobInputStream extends InputStream {
    private int mark;
    @Override
    public int read() throws IOException {
      if (isEOF()) {
        return -1;
      }
      byte[] b = new byte[1];
      final int i = read(b);
      if (i < 0) {
        return i;
      }
      return b[0];
    }
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
      if (b == null) {
        throw new NullPointerException();
      } else if (off < 0 || len < 0 || len > b.length - off) {
        throw new IndexOutOfBoundsException();
      }
      if (isEOF()) {
        return -1;
      }
      int avail = available();
      if (len > avail) {
        len = avail;
      }
      if (len <= 0) {
        return 0;
      }
      try {
        return Blob.this.read(ByteBuffer.wrap(b, off, len));
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }

    @Override
    public long skip(long n) throws IOException {
      try {
        long k = getBytes() - readOffset;
        if (n < k) {
          k = n < 0 ? 0 : n;
        }

        readOffset += k;
        return k;
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }

    @Override
    public int available() throws IOException {
      try {
        return getBytes() - readOffset;
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }
    @Override
    public void close() throws IOException {
      try {
        check(Blob.this.close(), "error while closing blob input stream");
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }
    private boolean isEOF() throws IOException {
      try {
        return readOffset >= getBytes();
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }

    @Override
    public boolean markSupported() {
      return true;
    }
    @Override
    public synchronized void mark(int _) {
      mark = readOffset;
    }
    @Override
    public synchronized void reset() throws IOException {
      readOffset = mark;
    }
  }

  private class BlobOutputStream extends OutputStream {
    @Override
    public void write(int b) throws IOException {
      write(new byte[]{(byte) b});
    }
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
      try {
        Blob.this.write(ByteBuffer.wrap(b, off, len));
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }

    @Override
    public void close() throws IOException {
      try {
        check(Blob.this.close(), "error while closing blob output stream");
      } catch (SQLiteException e) {
        throw new IOException(e);
      }
    }
  }
}
