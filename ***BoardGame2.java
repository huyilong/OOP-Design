A Board ‘is’ collection of blocks (ΣBlock)
A Player ‘is’ collection of pieces ( Player = ΣPiece )
A Game has :
one board
one black player
one white player
When making a move a Player chooses a Piece
Depending on type of piece, the Player checks valid moves.Which means that validity of move is :
tightly coupled to type of piece
is a property of piece
When thinking about valid moves, the player ‘is aware of’ the board
location of all the pieces on the board
Last point implies that the move validation routine should have access location of all the pieces on the board
On the board, block ‘contains’ a piece

public abstract class Piece {
  private Player player;
  public Piece(Player p) { player = p;}
  public abstract boolean isValidMove(Block[][] board,
      int fromX, int fromY, int toX, int toY);
  public Player getPlayer() { return player; }
}


public class Knight extends Piece {
  public Knight(Player p) {
    super(p);
  }
  @Override
  public boolean isValidMove(Block[][] board,
      int fromX, int fromY, int toX, int toY) {
    /*
    Piece orig = board[fromX][fromY].getPiece();
    Piece dest = board[toX][toY].getPiece();
    */
    int x = Math.abs(formX - toX);
    int y = Math.abs(fromY - toY);
    if (x * y = 2) {
      return true;
    } else {
      return false;
    }
  }
}

public class Player {
  private boolean result;
  private List<String> history_move;
  public boolean getResult() { return result; }
  public void setResult(boolean r) { result = r; }
}


public class Block {
  private Piece piece;
  public Block(Piece p) { piece = p; }
  public Piece getPiece() {
    return piece;
  }
  public void setPiece(Piece p) {
    piece = p;
  }
}

public class Game {
  private Player black, white;
  private Block[][] board;
  private void initialize() { 
    black = new Player();
    white = new Player();
    //... 
    board[0][0] = new Block(new Rook(black));
    board[0][1] = new Block(new Knight(black));
    //...
    board[7][6] = new Block(new Knight(white));
    board[7][7] = new Block(new Rook(white));
  }
  private boolean isEnd() {
    return !(white.getResult() && black.getResult());
  }
  public Piece getPiece(int x, int y) {
    return board[x][y].getPiece();
  }
  public boolean move(int fromX, int fromY, int toX, int toY, Player player) {
    Piece piece = board[fromX][fromY].getPiece();
    if (piece == null) return false;
    if (!piece.getPlayer().equals(player)) {
      return false;
    }
    /* where to judge there is not obstacle between [fromX][fromY] and [toX][toY]? 
     * here in Game class, or in Piece class? */
    if (!piece.isValidMove(board, fromX, fromY, toX, toY)){
      return false;
    }
    /* kill? */
    Block dest = board[toX][toY];
    if (dest.getPiece() == null) {
      return true;
    }
    if (dest.getPiece() != null &&
        !dest.getPiece().getPlayer().equals(player)) {
      //TODO KILL
      Player opponent = dest.getPiece().getPlayer();
      // Checkmate 将军
      if (dest.getPiece() instanceof King) {
        opponent.setResult(false);
      }
      return true;
    }
    return false;
  }
  public void play() {
    boolean gameOver = false;
    while (!gameOver) {
      while (!move(0,0,2,1,white));
      gameOver = isEnd();
      while (!move(0,0,2,1,black));
      gameOver = isEnd();
    }
  }
}

