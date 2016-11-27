
public class Position {
	
	
	private int row;
	private int col;
	
	/**
	 * Default constructor. 
	 */
	public Position()
	{
		row = 0;
		col = 0;
	}
	
	/**
	 * Parameterized constructor
	 * @param row
	 * @param col
	 */
	public Position(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Copy constructor
	 * @param pos
	 */
	public Position(Position pos)
	{
		this(pos.getRow(), pos.getCol());
	}
	
	/*
	 * Accesor methods
	 */
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	/*
	 * Mutators
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	public String toString()
	{
		return "("+this.getRow() + ", " + this.getCol() + ")";
	}
	

}
