import java.awt.Rectangle;
import student.TestCase;


/**
 * test the skiplist emplementation class
 * @author karthik
 * @version 03/09/2022
 *
 */
public class SkipListImplementationTest extends TestCase {
    
    private SkipListImplementation<String, InfoRectangle> skipList;
    private InfoRectangle rectangle;
    private InfoRectangle rectangle1;
    
    /**
     * setup the list and rectangle
     */
    public void setUp() {
        rectangle = new InfoRectangle("Square", 18, 18, 5, 5);
        rectangle1 = new InfoRectangle("BigSquare", 36, 36, 25, 25);
        skipList = new SkipListImplementation<>();
    }
    
    /**
     * test getTitle
     */
    public void testGetTitle() {
        assertEquals("Square", rectangle.getTitle());
        assertEquals("BigSquare", rectangle1.getTitle());
    }
    
    /**
     * test getX and Y coordinates
     */
    public void testGetXandYcoord() {
        assertEquals(18, rectangle.getXcoord());
        assertEquals(36, rectangle1.getXcoord());
        assertEquals(18, rectangle.getYcoord());
        assertEquals(36, rectangle1.getYcoord());
    }
    
    /**
     * test geTwidth and height
     */
    public void testGetWidthandHeight() {
        assertEquals(5, rectangle.getWidth());
        assertEquals(25, rectangle1.getWidth());
        assertEquals(5, rectangle.getHeight());
        assertEquals(25, rectangle1.getHeight());
    }
    
    /**
     * test get partial and complete rectangle info
     */
    public void testCompleteRectInfo() {
        assertEquals("18, 18, 5, 5", rectangle.partialRectInfo());
        assertEquals("BigSquare, 36, 36, 25, 25", rectangle1.completeRectInfo());
    }
    
    /**
     * test isInstanceOf
     */
    public void testIsInstanceOf() {
        assertTrue(rectangle1.getRectangle() instanceof Rectangle);
    }
    
    /**
     * test getCurrentLevel
     */
    public void testGetCurrentLevel() {
        assertEquals(-1, skipList.getLevel());
    }
    
    /**
     * test getGetLevelSize
     */
    public void testGetLevelSize() {
        assertEquals(0, skipList.getListSize());
    }
    
    /**
     * test the insertKeyValuePair
     */
    public void testinsertKeyValuePair() {
        InfoRectangle one = new InfoRectangle("A", 18, 18, 9, 7);
        InfoRectangle two = new InfoRectangle("B", 18, 18, 9, 7);
        InfoRectangle three = new InfoRectangle("C", 18, 18, 9, 7);
        skipList.insertKeyValuePair(rectangle.getTitle(), rectangle);
        skipList.insertKeyValuePair(one.getTitle(), one);
        skipList.insertKeyValuePair(two.getTitle(), two);
        skipList.insertKeyValuePair(three.getTitle(), three);
        assertEquals(4, skipList.getListSize());
    }
    
    /**
     * test the remove()
     */
    public void testRemove() {
        InfoRectangle one = new InfoRectangle("A", 18, 18, 9, 7);
        InfoRectangle two = new InfoRectangle("B", 18, 18, 9, 7);
        InfoRectangle three = new InfoRectangle("C", 18, 18, 9, 7);
        InfoRectangle quattro = new InfoRectangle("D", 18, 18, 9, 11);
        skipList.insertKeyValuePair(rectangle.getTitle(), rectangle);
        skipList.insertKeyValuePair(one.getTitle(), one);
        skipList.insertKeyValuePair(two.getTitle(), two);
        skipList.insertKeyValuePair(three.getTitle(), three);
        skipList.insertKeyValuePair(quattro.getTitle(), quattro);
        InfoRectangle rectRemoved = skipList.remove("Square");
        assertEquals("Square", rectRemoved.getTitle());
        assertEquals(5, rectRemoved.getWidth());
        assertEquals(4, skipList.getListSize());
    }
    
    /**
     * test the dump
     */
    public void testDump() {
        InfoRectangle one = new InfoRectangle("A", 18, 18, 9, 7);
        InfoRectangle two = new InfoRectangle("B", 18, 18, 9, 7);
        InfoRectangle three = new InfoRectangle("C", 18, 18, 9, 7);
        InfoRectangle quattro = new InfoRectangle("D", 18, 18, 9, 11);
        skipList.insertKeyValuePair(one.getTitle(), one);
        skipList.insertKeyValuePair(two.getTitle(), two);
        skipList.insertKeyValuePair(three.getTitle(), three);
        skipList.insertKeyValuePair(quattro.getTitle(), quattro);
        
        skipList.dump();
        assertTrue(contains(systemOut().getHistory(), "SkipList size is: 4"));
        assertTrue(contains(systemOut().getHistory(), "D, 18, 18, 9, 11)"));
        
        skipList.insertKeyValuePair(rectangle.getTitle(), rectangle);
        skipList.dump();
        assertTrue(contains(systemOut().getHistory(), "SkipList size is: 5"));
        assertTrue(contains(systemOut().getHistory(), "Square, 18, 18, 5, 5)"));
    }
    
    /**
     * test FindvalueOf
     */
    public void testFindValueOf() {
        InfoRectangle one = new InfoRectangle("A", 18, 18, 9, 7);
        InfoRectangle two = new InfoRectangle("B", 18, 18, 9, 7);
        InfoRectangle three = new InfoRectangle("C", 18, 18, 9, 7);
        InfoRectangle quattro = new InfoRectangle("D", 18, 18, 9, 11);
        skipList.insertKeyValuePair(one.getTitle(), one);
        skipList.insertKeyValuePair(two.getTitle(), two);
        skipList.insertKeyValuePair(three.getTitle(), three);
        skipList.insertKeyValuePair(quattro.getTitle(), quattro);
        InfoRectangle target = skipList.findValueOf("C");
        assertEquals(9, target.getWidth());
        assertEquals("C", target.getTitle());
        assertEquals(7, target.getHeight());
    }
}
