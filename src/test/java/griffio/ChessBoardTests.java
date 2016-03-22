package griffio;

import com.google.common.io.Resources;
import com.google.common.truth.Truth;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChessBoardTests {

  static String fixture(String resource) throws IOException {
    return Resources.toString(Resources.getResource(resource), StandardCharsets.UTF_8);
  }

  @DataProvider(name = "fixtures")
  static Object[][] fixtures() {
    return new Object[][] {
        {"input0.txt", "output0.txt"},
        {"input1.txt", "output1.txt"},
        {"input2.txt", "output2.txt"},
        {"input3.txt", "output3.txt"},
        {"input4.txt", "output4.txt"},
        {"input5.txt", "output5.txt"}
    };
  }

  @Test(groups = "slash-delimiter") void splits_rows() throws Exception {

    String fixture = fixture("input1.txt");

    Scanner scanner = new Scanner(fixture);

    scanner.useDelimiter("/");

    while (scanner.hasNext()) {
      Truth.assertThat(scanner.next()).isEqualTo("r1bk3r");
      Truth.assertThat(scanner.next()).isEqualTo("p2pBpNp");
      Truth.assertThat(scanner.next()).isEqualTo("n4n2");
      Truth.assertThat(scanner.next()).isEqualTo("1p1NP2P");
      Truth.assertThat(scanner.next()).isEqualTo("6P1");
      Truth.assertThat(scanner.next()).isEqualTo("3P4");
      Truth.assertThat(scanner.next()).isEqualTo("P1P1K3");
      Truth.assertThat(scanner.next()).isEqualTo("q5b1");
    }
  }

  @Test(groups = "slash-delimiter") void characters_match_first_row() throws Exception {

    String fixture = fixture("input1.txt");

    String[] rows = fixture.split("/");

    Scanner scanner = new Scanner(rows[0]);

    scanner.useDelimiter("");
    Truth.assertThat(scanner.next()).isEqualTo("r");
    Truth.assertThat(scanner.nextInt()).isEqualTo(1);
    Truth.assertThat(scanner.next()).isEqualTo("b");
    Truth.assertThat(scanner.next()).isEqualTo("k");
    Truth.assertThat(scanner.nextInt()).isEqualTo(3);
    Truth.assertThat(scanner.next()).isEqualTo("r");
  }

  @Test(groups = "intToDots")
  public void repeats_3_dots() throws IOException {
    Truth.assertThat("...").isEqualTo(ChessBoard.intToDots(3));
  }

  @Test(groups = "intToDots")
  public void repeats_8_dots() throws IOException {
    Truth.assertThat("........").isEqualTo(ChessBoard.intToDots(8));
  }

  @Test
  public void must_be_valid_chess_piece() {
    Truth.assertThat(ChessBoard.valid.matcher("r").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("n").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("b").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("q").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("k").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("p").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("R").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("N").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("B").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("Q").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("K").matches()).isTrue();
    Truth.assertThat(ChessBoard.valid.matcher("w").matches()).isFalse();
    Truth.assertThat(ChessBoard.valid.matcher("x").matches()).isFalse();
  }

  @Test(groups = "slash-delimiter")
  public void replace_int_with_dots() throws IOException {

    String fixture = fixture("input1.txt");

    String[] rows = fixture.split("/");

    Scanner scanner = new Scanner(rows[0]);

    scanner.useDelimiter("");

    while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        int n = scanner.nextInt();
        Truth.assertThat(n).isEqualTo(ChessBoard.intToDots(n).length());
      } else {
        scanner.next();
      }
    }
  }

  @Test(groups = "slash-delimiter")
  public void create_output_row() throws IOException {

    String fixture = fixture("input1.txt");

    String[] rows = fixture.split("/");

    Scanner scanner = new Scanner(rows[0]);

    scanner.useDelimiter("");

    StringBuilder actual = new StringBuilder();

    while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        int n = scanner.nextInt();
        actual.append(ChessBoard.intToDots(n));
      } else {
        actual.append(scanner.next());
      }
    }

    Truth.assertThat(actual.toString()).isEqualTo("r.bk...r");
  }

  @Test
  public void testName() throws Exception {
    String s = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR").toString();
    System.out.println("s = " + s);
  }

  @Test(dataProvider = "fixtures")
  public void chessboard_produces_expected_output(String input, String output)
      throws IOException {

    String fixture = fixture(input);

    String expected = fixture(output);

    ChessBoard chessBoard = new ChessBoard(fixture);

    String actual = chessBoard.toString();

    Truth.assertThat(actual).isEqualTo(expected);
  }
}