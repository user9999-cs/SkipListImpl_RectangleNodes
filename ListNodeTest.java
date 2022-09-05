

import student.TestCase;

/**
 * test the listNode class
 * @author karthik
 * @version 03/09/2022
 *
 */

public class ListNodeTest extends TestCase {
    
    private ListNode<String, String> skipNode;
    
    /**
     * setup the node with info
     */
    
    public void setUp() {
        skipNode = new ListNode<String, String>("9", "18", 4);
    }
    
    /**
     * test getKey
     */
    public void testKey() {
        assertEquals("9", skipNode.key());
    }
    
    /**
     * test getValue
     */
    public void testValue() {
        assertEquals("18", skipNode.value());
    }
    
    /**
     * test getNodeLevel
     */
    public void testGetNodeLevel() {
        assertEquals(4, skipNode.getNodeLevel());
    }
    
    /**
     * test the toString
     */
    public void testGetString() {
        assertEquals("[9, 18]", skipNode.toString());
    }
    
    /**
     * test the length of the skiplis
     */
    public void testListLength() {
        assertEquals(5, skipNode.front().length);
    }

}
