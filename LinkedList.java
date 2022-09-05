

import java.util.NoSuchElementException;

/**
 * 
 * Linked list implementation
 * 
 * @author karthik
 * @version 03/09/2022
 * 
 * @param <T>
 */

public class LinkedList<T> implements LinkedListADT<T> {
    
    private LinkedNode head;
    private int currentNumberOfNodes;
    private LinkedNode currentNode;
    
    
    /**
     * initialize the doubly linked list
     */
    public LinkedList() {
        head = null;
        currentNumberOfNodes = 0;
        currentNode = head;
    }
    
    /**
     * Add item at the end of the list
     * also check for the current capacity
     * @param node - node to be added
     * 
     * @return true if item addition is done else false
     */
    public boolean append(T node) {
        
        LinkedNode current = new LinkedNode(node);
        
        if (isEmpty()) {
            head = current;
            currentNode = current;
            
        }
        
        else {
            moveToEnd();
            currentNode.setNext(current);
            current.setPrev(currentNode);
            currentNode = current;
        }
        
        currentNumberOfNodes++;
        return true;
    }
    
    
    /**
     * clear the entire list by clearing all the items
     */
    public void clear() {
        
        head = null;
        currentNumberOfNodes = 0;
        currentNode = head;
    }
    
    
    /**
     * current elements position
     * 
     * @return position of the current item
     */
    public int currPos() {
        int itemPosition = 0;
        LinkedNode node = head;
        while (node != currentNode) {
            node = node.getNext();
            itemPosition++;
        }
        
        return itemPosition;
    }
    
    
    /**
     * get the value of the current item
     * 
     * @return value of the item
     * 
     * @throws NoSuchElementException
     */
    public T getValue() throws NoSuchElementException {
        
        return currentNode.getData();
    }
    
    
    /**
     * insert the item at the current location
     * 
     * @param item  item to be inserted
     *       
     * @return true if insertion is done else false
     */
    public boolean insert(T item) {
        
        LinkedNode current = new LinkedNode(item);
        
        if (isEmpty()) {
            append(item);
            return true;
            
        }
        
        else if (currentNode == head) {
            current.setNext(head);
            head.setPrev(current);
            head = current;
            currentNode = current;
        }
        
        else {
            LinkedNode previousNode = currentNode.getPrev();
            previousNode.setNext(current);
            current.setNext(currentNode);
            currentNode.setPrev(current);
            current.setPrev(previousNode);
            currentNode = current;
        }
        
        currentNumberOfNodes++;
        return true;
    }
    
    
    /**
     * check if we the item is at the end of the list
     * 
     * @return true if item is at the end else false
     */
    @Override
    public boolean isAtEnd() {
        
        LinkedNode lastNode = currentNode.getNext();
        return lastNode == null;
        
    }
    
    
    /**
     * check if we the item is at the start of the list
     * 
     * @return true if item is at the start else false
     */
    @Override
    public boolean isAtStart() {
        
        LinkedNode firstNode = currentNode.getPrev();
        return firstNode == null;
        
    }
    
    
    /**
     * check if the list is empty
     * 
     * @return true if list is empty else false
     */
    public boolean isEmpty() {
        
        return currentNumberOfNodes == 0;
    }
    
    
    /**
     * current size of the list
     * 
     * @return size of the list
     */
    public int length() {
        return currentNumberOfNodes;
    }
    
    
    /**
     * move to the last item of the list
     */
    public void moveToEnd() {
        
        LinkedNode curr = head;
        while (curr != null && curr.getNext() != null) {
            curr = curr.getNext();
        }
        
        currentNode = curr;
    }
    
    
    /**
     * move the item to position
     * 
     * @param position - destination for the current item
     *           
     * @return true if item moved else false
     */
    public boolean moveToPos(int position) {
        
        if (position >= currentNumberOfNodes || position < 0) {
            return false;
        }
        
        LinkedNode curr  = head;
        
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        
        currentNode = curr;
        return true;
    }
    
    
    /**
     * move to the first item of the list
     */
    public void moveToStart() {
        
        currentNode = head;
        
    }
    
    
    /**
     * move to the next item of the current item
     */
    public void next() {
        
        if (currentNode.getNext() != null) {
            LinkedNode newCurrent = currentNode.getNext();
            currentNode = newCurrent;
        }
    }
    
    
    /**
     * move to the previous item of the current item
     */
    public void prev() {
        
        if (currentNode.getPrev() != null) {
            LinkedNode newCurrent = currentNode.getPrev();
            currentNode = newCurrent;
        }
    }
    
    
    /**
     * remove the current element and return it
     * 
     * @return - item removed
     * 
     * @throws NoSuchElementException
     */
    public T remove() throws NoSuchElementException {
        
        if (isEmpty()) {
            throw new NoSuchElementException();
            
        }
        
        LinkedNode next = currentNode.getNext();
        LinkedNode prev = currentNode.getPrev();
        
        T nodeValue = currentNode.getData();
        
        if (currentNumberOfNodes == 1) {
            clear();
            return nodeValue;
        }
       
        
        else if (next == null) {
            
        }
        
        else if (prev == null) {
            head = next;
            head.setPrev(null);
            currentNode = next;
        }
        
        else {
            next.setPrev(prev);
            prev.setNext(next);
            currentNode = prev;
            
        }
        
        currentNumberOfNodes--;
        return nodeValue;
        
    }
    
    
    /**
     * check for the item existence
     * 
     * @param value to be searched for
     * @return item's position in the list
     * 
     */
    public int search(T value) {
        
        
        int currentIndex = 0;
        moveToStart();
        boolean nodeFound = false;
        
        while (currentNode != null) {
            
            if (currentNode.getData().equals(value)) {
                nodeFound = true;
                break;
                
            }
            
            currentNode = currentNode.next;
            currentIndex++;
        }
        
        if (!nodeFound) {
            currentIndex = -1;
        }
        
        moveToStart();
        
        return currentIndex;
    }
    
    
    /**
     * Node class for linked list
     * 
     * @param <T>
     */
    private class LinkedNode {
        private T data;
        private LinkedNode next;
        private LinkedNode prev;

        public LinkedNode(T data) {
            this(data, null, null);
        }


        /**
         * Constructor for linked node - reference to this class
         * is from my 2114 course work
         * 
         * @param data - data of the node
         * @param next - next node          
         * @param prev - previous node
         *           
         */
        public LinkedNode(T data, LinkedNode next, LinkedNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }


        /**
         * gets the next node
         * 
         * @return next
         */
        public LinkedNode getNext() {
            return next;
        }


        /**
         * gets the previous node
         * 
         * @return previous
         */
        public LinkedNode getPrev() {
            return prev;
        }


        /**
         * gets the data of the current node
         * 
         * @return data
         */
        public T getData() {
            if (data != null) {
                return data;
            }
            return null;
        }
        
        
        /**
         * set the node next to be the next node
         * 
         * @param next - node to be set to next
         *            
         */
        public void setNext(LinkedNode next) {
            this.next = next;
        }
        
        
        /**
         * set the node prev to be the previous node
         * 
         * @param prev - node to be set to prev
         *            
         */
        public void setPrev(LinkedNode prev) {
            this.prev = prev;
        }
    }
}
