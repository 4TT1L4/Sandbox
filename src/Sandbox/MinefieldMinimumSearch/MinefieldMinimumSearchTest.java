package MinefieldMinimumSearch;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import MinefueldMinimumSearch.strategy.Bruteforce;
import MinefueldMinimumSearch.strategy.DynamicProgramming;

/**
 * Tests the search implementation.
 * 
 * @author Attila
 */
@RunWith(Parameterized.class)
public class MinefieldMinimumSearchTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { new MinefieldOptimalPathFinder(new Bruteforce()) },
            { new MinefieldOptimalPathFinder(new DynamicProgramming()) },  
           });
    }

    @Parameter
    public MinefieldOptimalPathFinder pathFinder;
    
    
    /**
     * Test case:
     * - 4 x 4,
     * - no mines at all,
     *   -> 0 lifes are needed to get from start to exit.
     *       ____
     *      |S   |
     *      |    |
     *      |    |
     *      |___E|
     *      
     */
    @Test
    public void test_smallMinefieldWithoutMines_correctPathIsFound()
    {
        Minefield field = new Minefield(4, 4);
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals("It is possible to walk to the exit p", 1, lifesNeeded);
    }
    
    /**
     * Test case:
     * - 8 x 8,
     * - no mines at all,
     *   -> 1 life is needed to get from start to exit.
     *   
     *   Minefield map:
     *       ________
     *      |S       |
     *      |        |
     *      |        |
     *      |        |
     *      |        |
     *      |        |
     *      |        |
     *      |_______E|
     *      
     */
    @Test
    public void test_8x8MinefieldWithoutMines_correctPathIsFound()
    {
        Minefield field = new Minefield(8, 8);
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals("It should be possible to walk to the exit with", 1, lifesNeeded);
    }

    /**
     * Test case:
     * - 8 x 8,
     * - The optimal paths include one mine.
     *   -> 2 lifes are needed to get from start to exit.
     *       ________
     *      |SM      |
     *      |M       |
     *      |        |
     *      |        |
     *      |        |
     *      |        |
     *      |        |
     *      |_______E|
     *      
     */
    @Test
    public void test_8x8MinefieldWithTwoMines_correctPathIsFound()
    {
        Minefield field = new Minefield(8, 8);
        field.get(1, 0).plantMine();
        field.get(0, 1).plantMine();
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(2, lifesNeeded);
    }
    
    /**
     * Test case:
     * - 8 x 8,
     * - All the optimal paths include two mines.
     *   -> 3 lifes are needed to get from start to exit.
     *   
     *   Minefield map:
     *       ________
     *      |SM      |
     *      |M       |
     *      |        |
     *      |        |
     *      |        |
     *      |        |
     *      |       M|
     *      |______ME|
     *      
     */
    @Test
    public void test_8x8MinefieldWithFourMines_correctPathIsFound()
    {
        Minefield field = new Minefield(8, 8);
        field.get(1, 0).plantMine();
        field.get(0, 1).plantMine();
        field.get(6, 7).plantMine();
        field.get(7, 6).plantMine();
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(3, lifesNeeded);
    }
    
    /**
     * Test case:
     * 
     * The correct path if found, also in the case, when there are mines everywhere.
     * 
     * Minefield map:
     * 
     *      _________
     *      |SMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMME|
     *      
     */
    @Test
    public void test_8x8MinefieldFullWithMines_correctPathIsFound()
    {
        Minefield field = new Minefield(8, 8);
        System.out.println(field);
        
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j ++)
            {
                if ((i == 0 && j == 0) || (i == 7 && j == 7))
                {
                    continue;
                }
                
                field.get(i, j).plantMine();
            }
        }
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(14, lifesNeeded);
    }


    /**
     * Test case:
     * 
     * The correct value is found, when there is only one field without mine.
     * 
     * Minefield map:
     *       ________
     *      |SMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |M MMMMMM|
     *      |MMMMMMME|
     *      
     */
    @Test
    public void test_8x8MinefieldNearlyFullWithMines_correctPathIsFound()
    {
        Minefield field = new Minefield(8, 8);
        System.out.println(field);
        
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j ++)
            {
                if ((i == 0 && j == 0) || (i == 7 && j == 7))
                {
                    continue;
                }
                
                if ((i == 1 && j == 6))
                {
                    continue;
                }
                
                field.get(i, j).plantMine();
            }
        }
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(13, lifesNeeded);
    }

    /**
     * Test case:
     * 
     * Minefield is nearly full with mines -> correct optimal value is still found.
     * 
     * Minefield map:
     * 
     *       ________
     *      |SMMMMMMM|
     *      |MMMMMMMM|
     *      |M MMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMMMM|
     *      |MMMMMM M|
     *      |MMMMMMME|
     * 
     */
    @Test
    public void test_8x8MinefieldNearlyFullWithMines2_correctPathIsFound()
    {
        Minefield field = new Minefield(8, 8);
        System.out.println(field);
        
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j ++)
            {
                if ((i == 0 && j == 0) || (i == 7 && j == 7))
                {
                    continue;
                }
                
                if ((i == 1 && j == 2) || (i == 6 && j == 6))
                {
                    continue;
                }
                
                field.get(i, j).plantMine();
            }
        }
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(12, lifesNeeded);
    }
    

    /**
     * Test case:
     * 
     * Tests, if the computation finishes for a bigger maze as well.
     */
    @Test
    public void test_mineFieldIsBigger_correctPathIsFoundFor13x13SizedMinefield()
    {
        Minefield field = new Minefield(13, 13);
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(1, lifesNeeded);
    }
    /**
     * Test case:
     * 
     * Tests, if the computation finishes for a bigger maze as well.
     */
    @Test
    public void test_mineFieldIsBigger_correctPathIsFoundFor130x130SizedMinefield()
    {
        if(pathFinder.getStrategy() instanceof Bruteforce)
        {
            // This is too complex task for the bruteforce searcher.
            return;
        }
        
        Minefield field = new Minefield(130, 130);
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(1, lifesNeeded);
    }
    /**
     * Test case:
     * 
     * Tests, if the computation finishes for a bigger maze as well.
     */
    @Test
    public void test_mineFieldIsBigger_correctPathIsFoundFor1300x1300SizedMinefield()
    {
        if(pathFinder.getStrategy() instanceof Bruteforce)
        {
            // This is too complex task for the bruteforce searcher.
            return;
        }
        
        Minefield field = new Minefield(1300, 1300);
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(1, lifesNeeded);
    }
    
    /**
     * Test case:
     * 
     * Tests, if the mines at start and the target Positions are also taken into account.
     */
    @Test
    public void test_mineAtStartAndTarget_minesAreTakenIntoAccountAlsoInThisCase()
    {
        Minefield field = new Minefield(4, 4);
        field.get(0, 0).plantMine();
        field.get(3, 3).plantMine();
        
        Path path = pathFinder.findOptimalPathFor(field);
        
        long lifesNeeded = path.getBombCount() + 1;
        assertEquals(3, lifesNeeded);
    }
}
