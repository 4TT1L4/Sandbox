package MinefieldMinimumSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represent a Path in the Minefield.
 * 
 * @author Attila
 */
public class Path{
    
    final List<Position> positions = new ArrayList();
    final Minefield minefield;
    
    public Path(Minefield minefield)
    {
        this.minefield = minefield;
    }

    /**
     * @return the number of mines on the Path in the Minefield passed in the constructor.
     */
    public long getBombCount() {
        return this.positions
                .stream()
                .filter(
                        (position) -> minefield.hasBombAt(position.getXCoord(), position.getYCoord())
                       )
                .count();
    }

    /**
     * Extends the Path with a new Position (Step) at the end of the Path.
     * 
     * @param coordX The X coordinate of the new last step of the Path.
     * @param coordY The Y coordinate of the new last step of the Path.
     */
    public void add(int coordX, int coordY) {
        // TODO: Add assertion about the coordinates. There should be no "jumps" in the path.
        //       The positions next to each other in the path should be really next to each other in the Minefield.
        
        this.positions.add(new Position(coordX, coordY));
    }
    
    /**
     * Clones the Path object.
     */
    public Path clone() {
        Path clone = new Path(minefield);
        
        clone.positions.addAll(this.positions);
        
        return clone;
    }
    
    /**
     * Creates a String representation of the Path.
     */
    @Override
    public String toString()
    {
        return "Path[" + positions.toString() + "]";
    }
    

}
