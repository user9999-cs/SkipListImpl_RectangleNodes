import student.TestCase;

/**
 * test case for the rectangle list
 * @author karthik
 * @version 03/09/2022
 *
 */
public class RectangleListTest extends TestCase {
    
    private RectangleList commander;
    
    /**
     * set up the commander to perform operations
     */
    public void setUp() {
        commander = new RectangleList();
    }
    
    /**
     * test the insert operation
     */
    public void testInsert() {
        commander.insert("Square", "10", "10", "20", "20");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (Square, 10, 10, 20, 20)"));
        commander.insert("BigSquare", "0", "0", "999", "999");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (BigSquare, 0, 0, 999, 999)"));
        commander.insert("badRect", "-20", "-30", "25", "100");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (badRect, -20, -30, 25, 100)"));
        commander.insert("anotherBadRect", "0", "0", "250", "-150");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (anotherBadRect, 0, 0, 250, -150)"));
    }
    
    /**
     * test dump()
     */
    public void testDump() {
        commander.insert("BigSquare", "0", "0", "999", "999");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (BigSquare, 0, 0, 999, 999)"));
        commander.insert("Square", "10", "10", "900", "990");
        commander.insert("TimeSquare", "0", "0", "1000", "1000");
        commander.dump();
        assertTrue(contains(systemOut().getHistory(),
            "Value (BigSquare, 0, 0, 999, 999)"));
        assertTrue(contains(systemOut().getHistory(), "SkipList size is: 3"));
    }
    
    /**
     * test both remove() and remove-by-coords
     */
    public void testRemove() {
        commander.insert("BigSquare", "0", "0", "999", "999");
        commander.insert("Square", "10", "10", "900", "990");
        commander.insert("TimeSquare", "0", "0", "1000", "1000");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (TimeSquare, 0, 0, 1000, 1000)"));
        commander.insert("TimeSquare", "10", "10", "990", "990");
        commander.search("TimeSquare");
        assertTrue(contains(systemOut().getHistory(),
            "(TimeSquare, 0, 0, 1000, 1000)"));
        assertTrue(contains(systemOut().getHistory(),
            "(TimeSquare, 10, 10, 990, 990)"));
        commander.remove("Square");
        assertTrue(contains(systemOut().getHistory(),
            "(Square, 10, 10, 900, 990)"));
        commander.removeByCoords("0", "0", "1025", "999");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (0, 0, 1025, 999)"));
        commander.removeByCoords("0", "0", "999", "999");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle removed: (BigSquare, 0, 0, 999, 999)"));
        
    }
    
    /**
     * test search()
     */
    public void testSearch() {
        commander.insert("BigSquare", "0", "0", "999", "999");
        commander.insert("Square", "10", "10", "900", "990");
        commander.insert("TimeSquare", "10", "10", "990", "990");
        commander.insert("TimeSquare", "0", "0", "1000", "1000");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (TimeSquare, 0, 0, 1000, 1000)"));
        
        commander.search("TimeSquare");
        assertTrue(contains(systemOut().getHistory(),
            "(TimeSquare, 0, 0, 1000, 1000)"));
        assertTrue(contains(systemOut().getHistory(),
            "(TimeSquare, 10, 10, 990, 990)"));
        commander.search("?Square");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle not found: ?Square"));
    }
    
    /**
     * test intersections
     */
    public void testIntersections() {
        commander.insert("BigSquare", "0", "0", "1000", "1001");
        commander.insert("Square", "10", "10", "900", "990");
        commander.insert("TimeSquare", "10", "10", "990", "990");
        commander.insert("TimeSquare", "0", "0", "1000", "1000");
        commander.intersections();
        assertTrue(contains(systemOut().getHistory(),
            "(BigSquare, 0, 0, 1000, 1001 | TimeSquare, 0, 0, 1000, 1000)"));
        
    }
    
    /**
     * test regionsearch
     */
    public void testRegionsearch() {
        commander.insert("BigSquare", "0", "0", "1000", "1001");
        commander.insert("Square", "10", "10", "900", "990");
        commander.insert("TimeSquare", "10", "10", "990", "990");
        commander.insert("TimeSquare", "0", "0", "1000", "1000");
        commander.regionSearch("10", "10", "5", "5");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangles intersecting region (10, 10, 5, 5):"));
    }

}
