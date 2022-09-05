/**
 * KVPair class for the listnode
 * 
 * @author karthik
 * @version 03/09/2022
 * 
 * @param <K> - key
 * @param <V> - value
 */
class KVPair<K extends Comparable<? super K>, V> {
    private K key;
    private V value;
    
    /**
     * Constructor for the listnode KVPair
     * @param key - key of the node
     * @param value - value of the node
     */
    public KVPair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * get the key of the kvpair
     * @return key
     */
    public K getKey() {
        return key;
    }
    
    /**
     * get the value of the kvpair
     * @return key
     */
    public V getValue() {
        return value;
    }
    
    /**
     * get the string represenation of the kvpair
     * @return string format 
     */
    public String toStr() {
        return "[" + key + ", " + value + "]";
    }
}


/**
 * implementation of the list node where 
 * the key value pair exists
 * @author karthik
 * @version 03/09/2022
 * 
 * @param <K> - key
 * @param <V> - value
 *
 */
public class ListNode<K extends Comparable<? super K>, V> {
    
    private int nodeLevel;
    private KVPair<K, V> rectangle;
    private ListNode<K, V>[] front;
    
    /**
     * initialize the skiplist nodes
     * 
     * @param key - key of the node
     * @param val - value of the node
     * @param nodeLevel - level of the node
     */
    @SuppressWarnings("unchecked")
    public ListNode(K key, V val, int nodeLevel) {
        
        this.nodeLevel = nodeLevel;
        rectangle = new KVPair<>(key, val);
        front = new ListNode[nodeLevel + 1];
        for (int i = 0; i < nodeLevel; i++) {
            front[i] = null;
        }
    }
    
    /**
     * get the level of the listnode
     * @return level
     */
    
    public int getNodeLevel() {
        return nodeLevel;
    }
    
    /**
     * get the value of the listnode
     * 
     * @return value
     */
    
    public V value() {
        return rectangle.getValue();
    }
    
    /**
     * get key of the listnode
     * @return key
     */
    
    public K key() {
        return rectangle.getKey(); 
    }
    
    /**
     * get the linkedlist
     * @return linkedList
     */
    
    public ListNode<K, V>[] front() {
        return front;
    }
    
    /**
     * get the listnode in string format
     * @return string representation of the listNode
     */
    
    public String toString() {
        return rectangle.toStr();
    }
}
