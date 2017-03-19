package boggle.model;

public class BoardState {
	
	private int size;
	private String boardStr;
	
	public BoardState(int size, String boardStr) {
		this.size = size;
		this.boardStr = boardStr;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		return boardStr;
	}
}
