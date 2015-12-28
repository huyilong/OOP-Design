//Othello :: jump piece
public enum Direction{
	up, down, left, right
}//using ,,,,,,,,!!!!! no ;;;;;;; !!!
public enum Color{
	White, Black///capitalize!!!!!!
}

Color.White
Color.Black

public class Game{
	private Player[] players;
	private boolean whoseTurn;
	private GameBoard board;
	private final int ROWS = 10;
	private final int COLS = 10;

	private Game(){
		board = new GameBoard(ROWS, COLS);
		players = new Player[2];
		Player[0] = new Player(Color.White);
		Player[1] = new Player(Color.Black);
	}


	public GameBoard getBoard(){
		return board;
	}

}

public class GameBoard{
	private int blackcount =0 ;
	private int whitecount =0;
	private Piece[][] gameboard;

	public GameBoard(int rows, int cols){
		gameBoard = new Piece[rows][cols];
	}

	public void init(){

	}

	public boolean placePiece(int x, int y, Color c){
		//piece just contains color
	}

	public boolean hasWon(){
		logic staffs???
	}
	public boolean validMove(){
		//not occupied and inside the boundary
	}
	public boolean stalemate(){

	}
}

public class Piece{
	private Color color;
	public Piece(Color c){
		color = c;
	}
	public void flip(){
		if(color == Color.Black){
			color = Color.White;
		}else{
			color = Color.Black;
		}
	}
	public Color getColor(){
		return color;
	}
}

public class Player{
	private Color color;//identify each player
	public Player(Color c){
		color = c;
	}

	public int getScore(){

	}

	public boolean move(int x , int y){
		return game.getBoard().placePiece(x,y,color);
	}

	public Color getColor(){
		return color;
	}
}

