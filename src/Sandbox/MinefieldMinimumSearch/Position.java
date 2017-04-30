package MinefieldMinimumSearch;

/**
 * Represents a Position in the Minefield.
 * 
 * @author Attila
 */
public class Position {
    private int yCoord;
    private int xCoord;

    public Position(int xCoord, int yCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    /**
     * @return The X coordinate of the position.
     */
    public int getXCoord()
    {
        return xCoord;
    }
    
    /**
     * @return The Y coordinate of the position.
     */
    public int getYCoord()
    {
        return yCoord;
    }
    
    @Override
    public String toString()
    {
        return "[" + this.xCoord + ", " + this.yCoord + "]";
    }
}
