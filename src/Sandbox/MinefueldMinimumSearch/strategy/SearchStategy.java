package MinefueldMinimumSearch.strategy;

import MinefieldMinimumSearch.Minefield;
import MinefieldMinimumSearch.Path;

public interface SearchStategy {

    Path findOptimalPathFromGivenPosition(Minefield minefield, int startCoordX, int startCoordY, Path path);

}
