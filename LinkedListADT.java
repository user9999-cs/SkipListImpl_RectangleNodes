

import java.util.NoSuchElementException;

/**
 * 
 * Linked list interface for implementation
 * 
 * @author karthik
 * @version 03/09/2022
 * 
 * @param <T>
 */

public interface LinkedListADT<T> {
    
    /**
     * move to the first item of the list
     */
    public void moveToStart();


    /**
     * move to the last item of the list
     */
    public void moveToEnd();
    
    
    /**
     * current size of the list
     * 
     * @return size of the list
     */
    public int length();
    
    /**
     * Add item at the end of the list
     * also check for the current capacity
     * @param node - node to be appended
     * 
     * @return true if item addition is done else false
     */
    public boolean append(T node);
    
    /**
     * insert the item at the current location
     * 
     * @param node  item to be inserted
     *       
     * @return true if insertion is done else false
     */
    public boolean insert(T node);
    
    /**
     * clear the entire list by clearing all the items
     */
    public void clear();
    
    /**
     * remove the current element and return it
     * 
     * @return - item removed
     * 
     * @throws NoSuchElementException
     */
    public Object remove() throws NoSuchElementException;
    
    /**
     * move to the previous item of the current item
     */
    public void prev();


    /**
     * move to the next item of the current item
     */
    public void next();


    /**
     * current elements position
     * 
     * @return position of the current item
     */
    public int currPos();


    /**
     * move the item to position
     * 
     * @param position - destination for the current item
     *           
     * @return true if item moved else false
     */
    public boolean moveToPos(int position);
    
    
    /**
     * check for the item existence
     * 
     * @param value to be searched
     * @return nodes position in the linkedList
     * 
     */
    public int search(T value);


    /**
     * get the value of the current item
     * 
     * @return value of the item
     * 
     * @throws NoSuchElementException
     */
    public Object getValue() throws NoSuchElementException;


    /**
     * check if the list is empty
     * 
     * @return true if list is empty else false
     */
    public boolean isEmpty();
    
    
    /**
     * check if we the item is at the start of the list
     * 
     * @return true if item is at the start else false
     */
    public boolean isAtStart();
    
    
    /**
     * check if we the item is at the end of the list
     * 
     * @return true if item is at the end else false
     */
    public boolean isAtEnd();
    
}
