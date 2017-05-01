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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xCoord;
        result = prime * result + yCoord;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (xCoord != other.xCoord)
            return false;
        if (yCoord != other.yCoord)
            return false;
        return true;
    }
    
    public boolean equals(int x, int y)
    {
        return (this.xCoord == x) && (this.yCoord == y);
    }
}
