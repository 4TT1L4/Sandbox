package MinefueldMinimumSearch.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import MinefieldMinimumSearch.Minefield;
import MinefieldMinimumSearch.Path;
import MinefieldMinimumSearch.Position;

public class DynamicProgramming implements SearchStategy {

    private static final int UNKNOWN = -1;
    
    /**
     * Implements a strategy for searching the safest route (the one with the least mines).
     * 
     * The algorithm is based on dynamic programming.
     */
    @Override
    public Path findOptimalPathFromGivenPosition(Minefield minefield, int startX, int startY, Path path) {
        // Setup - create table.
        Integer[][] shortestRoutes = new Integer[minefield.getWidth()][minefield.getHeight()]; 
        
        // Setup - initialize the shortest values.
        for(int i = 0; i < minefield.getWidth(); i++)
        {
            for (int j = 0; j < minefield.getHeight(); j ++)
            {
                shortestRoutes[i][j] = UNKNOWN;
            }
            
        }
        
        if (minefield.hasBombAt(0, 0))
        {
            shortestRoutes[0][0] = 1;
        }
        else
        {
            shortestRoutes[0][0] = 0;
        }

        for (int i = 0; i < minefield.getWidth(); i++) {
            for (int j = 0; j < minefield.getHeight(); j++) {
                if ((i == 0) && (j == 0)) {
                    continue;
                }
                
                List<Integer> possibleMineCounts = new ArrayList<Integer>();

                if (i - 1 >= 0 && shortestRoutes[i - 1][j] != UNKNOWN) {
                    int bombCountThisWay = shortestRoutes[i - 1][j] + (minefield.hasBombAt(i, j) ? 1 : 0);
                    possibleMineCounts.add(bombCountThisWay);
                }

                if (j - 1 >= 0 && shortestRoutes[i][j - 1] != UNKNOWN) {
                    int bombCountThisWay = shortestRoutes[i][j - 1] + (minefield.hasBombAt(i, j) ? 1 : 0);
                    possibleMineCounts.add(bombCountThisWay);
                }

                shortestRoutes[i][j] = possibleMineCounts.stream().min((int1, int2) -> Integer.compare(int1, int2))
                        .get();
            }
        }
        
        path = findPathBack(minefield, shortestRoutes);
        
        return path;
    }

    private Path findPathBack(final Minefield minefield, Integer[][] shortestRoutes) {
        Path path = new Path(minefield);
        Position currentPosition = new Position(minefield.getWidth() - 1, minefield.getHeight() - 1);
        
        while(!path.contains(0, 0))
        {
            path.add(currentPosition);

            List<Position> possibleWays = new ArrayList<Position>();
            if (currentPosition.getXCoord() - 1 >= 0)
            {
                possibleWays.add(new Position(currentPosition.getXCoord() - 1, currentPosition.getYCoord()));
            }
            
            if (currentPosition.getYCoord() - 1 >= 0)
            {
                possibleWays.add(new Position(currentPosition.getXCoord(), currentPosition.getYCoord() - 1));
            }

            Optional<Position> bestWay = possibleWays.stream().min( (pos1, pos2) -> Integer.compare(shortestRoutes[pos1.getXCoord()][pos1.getYCoord()], shortestRoutes[pos2.getXCoord()][pos2.getYCoord()]));
            
            if (bestWay.isPresent())
            {
                currentPosition = bestWay.get();
            }
            else
            {
                break;
            }
        }
        
        return path;
    }

    private void printDebugMessage(Integer[][] shortestRoutes) {
        String messsage = "";
        
        for (int i = 0; i < shortestRoutes.length; i++) {
            for (int j = 0; j < shortestRoutes[i].length; j++) {
                messsage += " " + shortestRoutes[i][j];
            }                
            messsage += "\n";
        }
        
        System.out.println(messsage);
    }

}
