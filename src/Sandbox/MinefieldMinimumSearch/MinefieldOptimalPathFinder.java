package MinefieldMinimumSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implements a brute force solution to find the optimal path from the top-left corner
 * to the bottom-right corner of the Minefield.
 * 
 * @author Attila
 */
public class MinefieldOptimalPathFinder {

    /**
     * @param minefield The Minefield to find the optimal path in
     * 
     * @return A Path from the top-left corner to the bottom-right corner, with the lowest possible number of mines included.
     */
    public Path findOptimalPathFor(Minefield minefield) {
        Path path = new Path(minefield);
        path.add(0, 0);
        return findOptimalPathFromGivenPosition(minefield, 0, 0, path);
    }
    
    private Path findOptimalPathFromGivenPosition(Minefield field, int xCoord, int yCoord, Path currentPath)
    {
        if (xCoord == field.getWidth() -1 && yCoord == field.getHeight() - 1)
        {
            // Arrived at the target position.
            return currentPath;
        }
        
        List<Path> possiblePath = new ArrayList<Path>();
        if (xCoord < field.getWidth() - 1)
        {
            // There is space to go to right.
            Path newPath = currentPath.clone();
            newPath.add(xCoord + 1, yCoord);
            Path bestPathGoingRight = findOptimalPathFromGivenPosition(field, xCoord + 1, yCoord, newPath);
            possiblePath.add(bestPathGoingRight);
        }
        else
        {
            // We are on the right end. Nothing to do.
        }
        
        if(yCoord < field.getHeight() - 1)
        {
            // There is still space to go down.
            Path newPath = currentPath.clone();
            newPath.add(xCoord, yCoord + 1);
            Path bestPathGoingDown = findOptimalPathFromGivenPosition(field, xCoord, yCoord + 1, newPath);
            possiblePath.add(bestPathGoingDown);
        }
        else
        {
            // We are on the bottom. Nothing to do.
        }
        
        // Return the minimum of the possible ways to go from this position.
        return possiblePath.stream()
                .min(
                  (path1, path2) -> (int)path1.getBombCount() - (int)path2.getBombCount()
                )
                .get();
    }
}
