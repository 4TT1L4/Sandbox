package MinefieldMinimumSearch;

/**
 * Represents a MineField and provides access to the Field set of the maze.
 * 
 * @author Attila
 */
public class Minefield {
    
    /**
     * Stores the field references according to their coordinates.
     */
    private Field[][] fields;

    public Minefield(int width, int heigth) {
        fields = new Field[width][heigth];
        
        // Initialize the fields of the Minefield.
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < width; j++)
            {
                fields[i][j] = new Field();
            }            
        }
    }

    /**
     * Provides access to the Field at the coordinates passed as parameters.
     * 
     * @param xCoord The X coordinate of the Field.
     * @param yCoord The Y coordinate of the Field.
     * 
     * @return the Field of the Minefield on the position passed as parameter.
     */
    public Field get(int xCoord, int yCoord) {
        return fields[xCoord][yCoord];
    }
    
    /**
     * Tells if there is a mine at a given position.
     * 
     * @param xCoord The x coordinate of the position.
     * @param yCoord The y coordinate of the position.
     * 
     * @return true, when there is a mine at the given position, otherwise false.
     */
    public boolean hasBombAt(int xCoord, int yCoord) {
        return fields[xCoord][yCoord].hasMine();
    }

    /**
     * @return the width of the Minefield.
     */
    public int getWidth()
    {
        return fields.length;
    }
    
    /**
     * @return the height of the Minefield.
     */
    public int getHeight()
    {
        return fields[0].length;
    }

}
