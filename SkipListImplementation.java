
import java.util.Random;
/**
 * Skip list class implmentation
 * reference from course and DSA
 * @author karthik
 * @version 03/09/2022
 * 
 * @param <K> - key
 * @param <V> - value
 */

public class SkipListImplementation<K extends Comparable<K>, V> {
    
    private int listSize;
    private int currentLevel;
    private ListNode<K, V> head;
    static private Random random = new Random();
    /**
     * Constructor for the skiplist class
     */
    public SkipListImplementation() {
        listSize = 0;
        currentLevel = -1;
        head = new ListNode<>(null, null, 0);
    }
    
    /**
     * get the skiplist size
     * @return listSize
     */
    public int getListSize() {
        return listSize;
        
    }
    
    /**
     * get the skiplist level
     * @return currentLevel
     */
    public int getLevel() {
        return currentLevel;
        
    }
    
    /**
     * search for the node with given key
     * @param key - node key to be found
     * @return value of the node
     */
    public V findValueOf(K key) {
        ListNode<K, V> current = head; 
        for (int i = currentLevel; i >= 0; i--)
        {
            while ((current.front()[i] != null) && 
                (current.front()[i].key().compareTo(key) < 0))
            {
                current = current.front()[i];
            }
        }
        current = current.front()[0];
        if ((current == null) || (current.key().compareTo(key) != 0)) {
            return null;
        }
        else {
            return current.value();
        }
    }
    
    /**
     * move to the head of the given level
     * 
     * @param toThisLevel - adjust to the head of this level
     */
    private void adjustHead(int toThisLevel) {
        ListNode<K, V> temp = head;
        head = new ListNode<K, V>(null, null, toThisLevel);
        for (int i = 0; i <= currentLevel; i++) {
            head.front()[i] = temp.front()[i];
        }
        currentLevel = toThisLevel;
    }
    
    /**
     * randomization for picking the level
     * @return currentNode level
     */
    public int coinTossLevel() {
        int currLevel;
        //we basically do nothing in the below for loop
        for (currLevel = 0; Math.abs(random.nextInt()) % 2 == 0; currLevel++)
        {
        }
        return currLevel;
    }
    
    /**
     * Insert a KV pair in the skipList
     * @param key - key of the node
     * @param value - value of the node
     */
    @SuppressWarnings("unchecked")
    public void insertKeyValuePair(K key, V value) {
        int newLevel = coinTossLevel(); 
        if (newLevel > currentLevel) { 
            adjustHead(newLevel);
        }
        ListNode<K, V>[] update = new ListNode[currentLevel + 1];
        ListNode<K, V> current = head;
        for (int i = currentLevel; i >= 0; i--) {
            while ((current.front()[i] != null) 
                && (current.front()[i].key().compareTo(
                key) < 0)) {
                current = current.front()[i];
            }
            update[i] = current;
        }
        current = new ListNode<K, V>(key, value, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            current.front()[i] = update[i].front()[i];
            update[i].front()[i] = current;
        }
        listSize++;
    }
    
    /**
     * adjust the removed nodes bindings
     * 
     * @param modList list
     * @param currNode - current node 
     */
    public void adjustNodeBindings(ListNode<K, V>[] modList,
        ListNode<K, V> currNode) {
        for (int i = 0; i <= currNode.getNodeLevel(); i++) {
            modList[i].front()[i] = currNode.front()[i];
            currNode.front()[i] = null;
        }
    }
    
    /** 
     * remove the node with given key
     * @param key - key of the node to be deleted          
     * @return the removed node
     */
    public V remove(K key) {
        @SuppressWarnings("unchecked")
        ListNode<K, V>[] adjustLevel = new ListNode[currentLevel + 1];
        ListNode<K, V> currNode = head;
        for (int i = currentLevel; i >= 0; i--) {
            while ((currNode.front()[i] != null) 
                && (currNode.front()[i].key().compareTo(
                key) < 0)) {
                currNode = currNode.front()[i];
            }
            adjustLevel[i] = currNode;
        }
        V valueRemoved = null;
        if (currNode.front()[0] != null && currNode.front()[0].key().compareTo(
            key) == 0) {
            valueRemoved = currNode.front()[0].value();
            adjustNodeBindings(adjustLevel, currNode.front()[0]);
            listSize--;
        }
        return valueRemoved;
    }
    
    /**
     * dump the info of the current listNode
     */
    public void dump() {
        ListNode<K, V> currentNode = head;
        System.out.println("SkipList dump:");
        System.out.println("Node has depth " + currentNode.front().length
            + ", " + "Value (" + currentNode.key() + ")");
        while (currentNode.front()[0] != null) {
            currentNode = currentNode.front()[0];
            InfoRectangle element = (InfoRectangle)currentNode.value(); 
            System.out.println("Node has depth " + currentNode.front().length
                + ", " + "Value (" + element.getTitle() + ", " + 
                element.partialRectInfo() + ")");

        }
        System.out.println("SkipList size is: " + listSize);
    }  
}
