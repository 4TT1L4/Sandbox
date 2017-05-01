package MinefieldMinimumSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import MinefueldMinimumSearch.strategy.SearchStategy;

/**
 * Implements a brute force solution to find the optimal path from the top-left corner
 * to the bottom-right corner of the Minefield.
 * 
 * @author Attila
 */
public class MinefieldOptimalPathFinder {

    private SearchStategy strategy;
    
    public MinefieldOptimalPathFinder(SearchStategy strategy)
    {
        this.strategy = strategy;
    }
    
    public SearchStategy getStrategy()
    {
        return strategy;
    }
    
    
    /**
     * @param minefield The Minefield to find the optimal path in
     * 
     * @return A Path from the top-left corner to the bottom-right corner, with the lowest possible number of mines included.
     */
    public Path findOptimalPathFor(Minefield minefield) {
        Path path = new Path(minefield);
        path.add(0, 0);
        return strategy.findOptimalPathFromGivenPosition(minefield, 0, 0, path);
    }
}
