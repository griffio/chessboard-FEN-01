package griffio;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class ChessBoard {

  public static String intToDots(int digit) {
    return new String(new char[digit]).replace("\0", ".");
  }

  public static Pattern valid = Pattern.compile("[rnbqkp12345678]", Pattern.CASE_INSENSITIVE);

  private final String encoded;

  public ChessBoard(String encoded) {
    this.encoded = encoded;
  }

  @Override
  public String toString() {
    String[] rows = this.encoded.split("/");
    if (rows.length != 8) {
      return String.format("Wrong number of rows. [%d] found. 8 expected.", rows.length);
    }
    try {
      return scanRows(rows);
    } catch (IllegalStateException e) {
      return e.getMessage();
    }
  }

  protected String scanRows(String[] rows) {
    StringJoiner joiner = new StringJoiner("\n");
    for (String row : rows) {
      joiner.merge(scanRow(row));
    }
    return joiner.toString();
  }

  protected StringJoiner scanRow(String row) throws IllegalStateException {
    StringJoiner joiner = new StringJoiner("");
    Scanner scanner = new Scanner(row);
    scanner.useDelimiter("");
    int checksum = 0;

    while (scanner.hasNext()) {
      if (!scanner.hasNext(valid)) {
        throw new IllegalStateException(
            String.format("Invalid encoded position : Unexpected character [%s]", scanner.next()));
      }

      if (scanner.hasNextInt()) {
        int n = scanner.nextInt();
        joiner.add(ChessBoard.intToDots(n));
        checksum += n;
      } else {
        String next = scanner.next();
        joiner.add(next);
        checksum++;
      }
    }

    if (checksum != 8) {
      throw new IllegalStateException(
          String.format("Wrong number of squares on row. [%d] found. 8 expected.", checksum));
    }

    return joiner;
  }
}