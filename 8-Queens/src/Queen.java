
public class Queen {

	private int row, col;

	public Queen(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}

	public boolean attack(Queen q) {
		if (q.getCol() == this.col || q.getRow() == this.row
				|| Math.abs(this.row - q.getRow()) == Math.abs(this.col - q.getRow())) {
			return true;
		}
		return false;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void moveDown(int spaces) {
		this.row += spaces;
		if (this.row > 7 && this.row % 7 != 0) {
			this.row = (row % 7) - 1;
		} else if (this.row > 7 && this.row % 7 == 0) {
			this.row = 7;
		}
	}
	public void moveLeft(int spaces) {
		this.col += spaces;
		if (this.col > 7 && this.col % 7 != 0) {
			this.col = (col % 7) - 1;
		} else if (this.col > 7 && this.col% 7 == 0) {
			this.col = 7;
		}
	}

}
