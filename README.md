## Chessboard

A chessboard is an 8x8 grid of squares where each square may be occupied by a chess piece. Pieces are either white or black, and include pawns, kings, queens, bishops, knights and rooks. The position of pieces on a chessboard can be encoded as a compact string. For example, the starting position of a chess game is encoded as rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR. The encoding works as follows:
Starting at the top of the board, each row is encoded in turn and separated by ‘/’ character

A row is encoded by considering each square from left to right

Where a piece occupies a square, it is represented by its initial character, except knights which use the character ‘n’ or ‘N’. White pieces use uppercase, black pieces use lowercase. For example, the black queen is ‘q’ and a white pawn is ‘P’
Empty squares which are not occupied by any piece are encoded as digits. Runs of empty squares are coalesced, the digit value indicates the number of empty squares in each run.

Example data

---

Input 0:

rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR

Output 0:

rnbqkbnr
pppppppp
........
........
........
........
PPPPPPPP
RNBQKBNR

---

Input 1:

r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1

Output 1:

r.bk...r
p..pBpNp
n....n..
.p.NP..P
......P.
...P....
P.P.K...
q.....b.

---

Input 2:

3w4/7p/7p/7p/8/8/8/8

Output 2:

Invalid encoded position : Unexpected character

---

Input 4:

rnbqkbnr/pppppppp/7/8/8/8/PPPPPPPP/RNBQKBNR

Output 4:

Wrong number of squares on row. [7] found. 8 expected.

---