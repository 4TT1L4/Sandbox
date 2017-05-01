package MinefueldMinimumSearch.strategy;

import java.util.ArrayList;
import java.util.List;

import MinefieldMinimumSearch.Minefield;
import MinefieldMinimumSearch.Path;

public class Bruteforce implements SearchStategy {
    /**
     * Implements a brute force strategy for searching the safest route (the one with the least mines).
     */
    @Override
    public Path findOptimalPathFromGivenPosition(Minefield field, int xCoord, int yCoord, Path currentPath)
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
