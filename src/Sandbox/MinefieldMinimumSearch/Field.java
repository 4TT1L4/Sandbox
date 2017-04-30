package MinefieldMinimumSearch;

/**
 * Represent a Field of a Minefield.
 * 
 * @author Attila
 */
public class Field {
    boolean hasMine = false;
    
    /**
     * Places a mine in this field.
     */
    public void plantMine()
    {
        this.hasMine = true;
    }
    
    /**
     * @return true, when the field has a mine.
     */
    public boolean hasMine()
    {
        return hasMine;
    }
}
