import student.TestCase;
import java.util.NoSuchElementException;

/**
 * test case for the linkedList implementation
 * @author karthik
 * @version 03/09/2022
 */

public class LinkedListTest extends TestCase {
    
    private LinkedList<String> employeeName;
    private LinkedList<String> employeeGroup;

    private LinkedList<String> nothingHere;

    /**
     * Set up the linked lists
     */
    public void setUp() {
        employeeName = new LinkedList<>();
        employeeGroup = new LinkedList<>();
        nothingHere = new LinkedList<>();
        employeeName.append("Karthik");
        employeeName.append("Shreya");
        employeeName.append("Tarak");
        employeeGroup.append("SDE-1");
        employeeGroup.append("SDE-2");
        employeeGroup.append("SDE-3");
    }
    
    /**
     * test search()
     */
    public void testSearch() {
        assertEquals(-1, employeeGroup.search("Karthik"));
        assertEquals(0, employeeName.search("Karthik"));
        assertEquals(2, employeeGroup.search("SDE-3"));
    }


    /**
     * test clear()
     */
    public void testClear() {
        assertEquals(3, employeeGroup.length());
        employeeGroup.clear();
        assertEquals(0, employeeGroup.length());
        assertEquals(3, employeeName.length());
        employeeName.clear();
        assertEquals(0, employeeName.length());
    }


    /**
     * test insert()
     */
    public void testInsert() {
        employeeGroup.insert("Senior SDE");
        assertEquals(4, employeeGroup.length());
        employeeGroup.moveToStart();
        employeeGroup.insert("New Grad");
        assertEquals(5, employeeGroup.length());
        assertEquals(0, employeeGroup.currPos());
        employeeGroup.next();
        employeeGroup.next();
        assertEquals("SDE-2", employeeGroup.getValue());
        employeeGroup.clear();
        employeeGroup.insert("New Grad");
        employeeGroup.insert("SDE-1");
        assertEquals(2, employeeGroup.length());
    }


    /**
     * test append()
     */
    public void testAppend() {
        employeeName.append("Scott");
        employeeName.append("Buxton");
        assertEquals(5, employeeName.length());
        employeeName.clear();
        employeeName.append("Karthik");
        assertEquals(1, employeeName.length());
        assertEquals("Karthik", employeeName.getValue());
    }


    /**
     * test remove()
     */
    public void testRemove() {
        employeeName.remove();
        assertEquals(2, employeeName.length());
        employeeName.remove();
        assertEquals(1, employeeName.length());
        employeeName.remove();
        assertEquals(0, employeeName.length());
        employeeName.clear();
        Exception thrown = null;
        try {
            employeeName.remove();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof NoSuchElementException);

        employeeName.append("Karthik");
        employeeName.append("Shreya");
        employeeName.append("Tarak");
        employeeName.moveToStart();
        employeeName.remove();
        assertEquals("Shreya", employeeName.getValue());
        assertEquals(2, employeeName.length());
        employeeGroup.remove();
        employeeGroup.prev();
        assertEquals("SDE-2", employeeGroup.getValue());
        employeeGroup.moveToEnd();
        employeeGroup.append("SDE-3");
        employeeGroup.moveToPos(1);
        employeeGroup.remove();
        assertEquals("SDE-1", employeeGroup.getValue());
    }


    /**
     * test moveToStart()
     */
    public void testMoveToStart() {
        assertEquals("SDE-3", employeeGroup.getValue());
        employeeGroup.moveToStart();
        assertEquals("SDE-1", employeeGroup.getValue());
        employeeGroup.moveToStart();
        assertEquals("SDE-1", employeeGroup.getValue());
        employeeGroup.next();
        assertEquals("SDE-2", employeeGroup.getValue());
        employeeGroup.moveToStart();
        assertEquals("SDE-1", employeeGroup.getValue());
    }


    /**
     * test moveToEnd()
     */
    public void testMoveToEnd() {
        employeeGroup.moveToStart();
        assertEquals("SDE-1", employeeGroup.getValue());
        employeeGroup.moveToEnd();
        assertEquals("SDE-3", employeeGroup.getValue());
        employeeGroup.moveToEnd();
        assertEquals("SDE-3", employeeGroup.getValue());
    }


    /**
     * test prev()
     */
    public void testPrev() {
        assertEquals("SDE-3", employeeGroup.getValue());
        employeeGroup.prev();
        assertEquals("SDE-2", employeeGroup.getValue());
        employeeGroup.moveToEnd();
        employeeGroup.prev();
        employeeGroup.prev();
        assertEquals("SDE-1", employeeGroup.getValue());
        employeeGroup.moveToStart();
        assertEquals("SDE-1", employeeGroup.getValue());
        employeeGroup.next();
        assertEquals("SDE-2", employeeGroup.getValue());
    }


    /**
     * test next()
     */
    public void testNext() {
        assertEquals("SDE-3", employeeGroup.getValue());
        employeeGroup.moveToStart();
        assertEquals("SDE-1", employeeGroup.getValue());
        employeeGroup.next();
        assertEquals("SDE-2", employeeGroup.getValue());
        employeeGroup.next();
        assertEquals("SDE-3", employeeGroup.getValue());
        employeeGroup.next();
        assertEquals("SDE-3", employeeGroup.getValue());
    }


    /**
     * test length()
     */
    public void testLength() {
        assertEquals(0, nothingHere.length());
    }


    /**
     * test currPos()
     */
    public void testCurrPos() {
        assertEquals(2, employeeGroup.currPos());
        employeeGroup.prev();
        assertEquals(1, employeeGroup.currPos());
        employeeGroup.prev();
        assertEquals(0, employeeGroup.currPos());
        employeeGroup.prev();
        assertEquals(0, employeeGroup.currPos());
    }


    /**
     * test moveToPos()
     */
    public void testMoveToPos() {
        assertTrue(employeeGroup.moveToPos(2));
        assertEquals("SDE-3", employeeGroup.getValue());
        assertFalse(employeeGroup.moveToPos(101));
        assertFalse(employeeGroup.moveToPos(-10));
        assertTrue(employeeGroup.moveToPos(0));
    }


    /**
     * test isAtEnd()
     */
    public void testIsAtEnd() {
        assertTrue(employeeGroup.isAtEnd());
        employeeGroup.prev();
        assertFalse(employeeGroup.isAtEnd());
        employeeGroup.prev();
        employeeGroup.next();
        employeeGroup.next();
        assertTrue(employeeGroup.isAtEnd());
    }


    /**
     * test isAtStart()
     */
    public void testIsAtStart() {
        employeeGroup.moveToStart();
        assertTrue(employeeGroup.isAtStart());
        employeeGroup.prev();
        assertTrue(employeeGroup.isAtStart());
        employeeGroup.next();
        assertFalse(employeeGroup.isAtStart());
        employeeGroup.prev();
        assertTrue(employeeGroup.isAtStart());
    }


    /**
     * test getValue()
     */
    public void testGetValue() {
        assertEquals("SDE-3", employeeGroup.getValue());
        employeeGroup.append("New role TBD");
        assertEquals("New role TBD", employeeGroup.getValue());
        employeeGroup.append(null);
        assertNull(employeeGroup.getValue());
    }


    /**
     * test isEmpty()
     */
    public void testIsEmpty() {
        assertFalse(employeeGroup.isEmpty());
        assertFalse(employeeName.isEmpty());
        assertTrue(nothingHere.isEmpty()); 
    }
}
