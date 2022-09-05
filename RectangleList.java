

import java.awt.Rectangle;

/**
 * Commander class for the skiplist
 * @author karthik
 * @version 03/09/2022
 *
 */

public class RectangleList {
    
    // this names list helps us to identify the rectangles
    // idea behind this is to develop some lookup 
    // function like a dictionary
    private LinkedList<String> nameList;
    private LinkedList<Rectangle> rectangleList;
    
    private SkipListImplementation<String, InfoRectangle> skipList;
    
    /**
     * Constructor for the rectangle list
     */
    public RectangleList() {
        
        skipList = new SkipListImplementation<>();
        nameList = new LinkedList<>();
        rectangleList = new LinkedList<>();
        
    }
    
    /**
     * print the skipList info to the screen
     */
    public void dump()
    {
        skipList.dump();
    }
    
    /**
     * check if the rectangle with the given dimensions exists
     * 
     * @param title - title of the rectangle
     * @param flag - is a positive or a negative number
     * @param rectRemoved - rectangle removed
     * @return true if the rectangle exits else false
     */
    public boolean checkIfRectangleExists(
        String title,
        int flag,
        Rectangle rectRemoved) {
        if (flag < 0) {
            if (rectRemoved instanceof Rectangle) {
                InfoRectangle rectNotFound = new InfoRectangle("", rectRemoved);
                System.out.println("Rectangle not removed: " + "("
                    + rectNotFound.partialRectInfo() + ")");
            }
            else {
                System.out.println("Rectangle not removed: " + title);
            }
            return false;
        }
        return true;
    }
    
    /**
     * check if the rectangle is valid in the first place
     * @param title - title of the rectangle
     * @param x - distance along the x axis
     * @param y - distance along the y axis
     * @param w - width
     * @param h - height
     * @param regionsearch - regionsearch scout
     * 
     * @return True if valid, False if not valid
     */
    
    public boolean checkIfRectangleIsValid(String title, String x,
        String y, String w, String h,
        boolean regionsearch) {
        
        int xCoord = Integer.valueOf(x);
        int yCoord = Integer.valueOf(y);
        int width = Integer.valueOf(w);
        int height = Integer.valueOf(h);
        
        if (regionsearch) {
            if (width <= 0 || height <= 0) {
                InfoRectangle rectangleRejected = new InfoRectangle(
                    title, xCoord, yCoord,
                    width, height);
                System.out.println("Rectangle rejected: " + "(" 
                    + rectangleRejected.partialRectInfo() + ")");
                return false;
            }
            return true;
        }

        // check if the rectangle is in the 1024 x 1024 box
        if (xCoord < 0 || yCoord < 0 || width <= 0 || 
            height <= 0 || yCoord + height > 1024
            || xCoord + width > 1024) {
            InfoRectangle rectangleRejected = new InfoRectangle(
                title, xCoord, yCoord, width,
                height);
            if (title.equals("")) {
                System.out.println("Rectangle rejected: " + "(" +
                    rectangleRejected.partialRectInfo() + ")");
            }
            else {
                System.out.println("Rectangle rejected: " + "(" +
                    rectangleRejected.completeRectInfo() + ")");
            }
            return false;
        }
        
        return true; 
    }
    
    /**
     * Add the rectangle to the skiplist
     * 
     * @param title - title of the rectangle
     * @param x - distance along the x axis
     * @param y - distance along the y axis
     * @param w - width
     * @param h - height
     */
    public void insert(String title, String x, String y, String w, String h) {
        if (checkIfRectangleIsValid(title, x, y, w, h, false)) {
            InfoRectangle rectangleToBeAdded = new InfoRectangle(
                title, Integer.valueOf(x), Integer.valueOf(y),
                Integer.valueOf(w), Integer.valueOf(h));
            skipList.insertKeyValuePair(title, rectangleToBeAdded);
            System.out.println("Rectangle inserted: " + "(" + 
                rectangleToBeAdded.completeRectInfo() + ")");
            nameList.moveToEnd();
            rectangleList.moveToEnd();
            nameList.append(title);
            rectangleList.append(rectangleToBeAdded.getRectangle());
        }
    }
    
    /**
     * remove the node matching the passed argument
     * 
     * @param title - title of the rectangle to be removed
     */
    public void remove(String title) {
        int indexOfNodeToBeRemoved = nameList.search(title);
        if (checkIfRectangleExists(title, indexOfNodeToBeRemoved, null)) {
            InfoRectangle removedNode = skipList.remove(title);
            System.out.println("Rectangle removed: " + "(" + 
                removedNode.completeRectInfo() + ")");
            nameList.moveToPos(indexOfNodeToBeRemoved);
            rectangleList.moveToPos(indexOfNodeToBeRemoved);
            nameList.remove();
            rectangleList.remove();
        }
    }
    
    /**
     * get the index of the rectangle given its coords
     * @param rectangleToBeRemoved -rect to be removed
     * @return The position of the target
     */
    public int searchForRectangle(Rectangle rectangleToBeRemoved) {

        int idx = 0;
        rectangleList.moveToEnd();
        rectangleList.append(null);
        rectangleList.moveToStart();
        boolean rectangleFound = false;
        while (!rectangleList.isAtEnd()) {
            Rectangle currentRectangle = rectangleList.getValue();
            if (currentRectangle.x == rectangleToBeRemoved.x 
                && currentRectangle.y == rectangleToBeRemoved.y
                && currentRectangle.width == rectangleToBeRemoved.width
                && currentRectangle.height == rectangleToBeRemoved.height) {
                rectangleFound = true;
                break;
            }
            rectangleList.next();
            idx++;
        }
        if (!rectangleFound) {
            idx = -1;
        }
        rectangleList.moveToEnd();
        rectangleList.remove();
        rectangleList.moveToStart();
        return idx;
    }
    
    /**
     * remove rectangle-by-coords
     * @param x - distance along the x axis
     * @param y - distance along the y axis
     * @param w - width
     * @param h - height
     */
    public void removeByCoords(String x, String y, String w, String h) {
        if (checkIfRectangleIsValid("", x, y, w, h, false)) {
            Rectangle rectangleToBeRemoved = new Rectangle(
                Integer.valueOf(x), Integer
                .valueOf(y), Integer.valueOf(w), Integer.valueOf(h));
            int indexOfNodeToBeRemoved = searchForRectangle(
                rectangleToBeRemoved);
            if (checkIfRectangleExists("",
                indexOfNodeToBeRemoved, rectangleToBeRemoved)) {
                nameList.moveToPos(indexOfNodeToBeRemoved);
                String name = nameList.getValue();
                remove(name);
            }
        }
    }
    
    /**
     * Print reported pairs of rectangles
     * 
     * @param rectOneInfo - info of rect one 
     * @param rectTwoInfo - info of rect two
     */
    public void printToScreen(InfoRectangle rectOneInfo,
        InfoRectangle rectTwoInfo) {
        System.out.println("(" + rectOneInfo.completeRectInfo() + " | " +
            rectTwoInfo.completeRectInfo() + ")");
        
    }
    
    /**
     * find the intersections in the skip linkedlist
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        int listLength = nameList.length();
        if (listLength < 1) {
            return;
        }
        nameList.moveToEnd();
        nameList.append(null);
        rectangleList.moveToEnd();
        rectangleList.append(null);
        nameList.moveToStart();
        rectangleList.moveToStart();
        for (int i = 0; i < listLength; i++) {
            rectangleList.moveToPos(i);
            Rectangle currentRect = rectangleList.getValue();
            for (int j = 0; j < listLength; j++) {
                if (i == j) {
                    continue;
                }
                rectangleList.moveToPos(j);
                Rectangle rectToBeCompared = rectangleList.getValue();
                if (intersectionExists(currentRect, rectToBeCompared)) {
                    nameList.moveToPos(i);
                    String nameCurr = nameList.getValue();
                    nameList.moveToPos(j);
                    String titleToBeCompared = nameList.getValue();
                    InfoRectangle currentRectInfo = new InfoRectangle(nameCurr,
                        currentRect);
                    InfoRectangle rectToBeCompInfo = new InfoRectangle(
                        titleToBeCompared, rectToBeCompared);
                    printToScreen(currentRectInfo, rectToBeCompInfo);
                }
            }
        }
        
        nameList.moveToEnd();
        nameList.remove();
        nameList.moveToStart();
        rectangleList.moveToEnd();
        rectangleList.remove();
        rectangleList.moveToStart();
    }
    
    /**
     * compare rectangles to check if they intersect
     * 
     * @param rectOne - retangle one
     * @param rectTwo - rectangle two
     * @return true if they intersect else false
     */
    public boolean intersectionExists(Rectangle rectOne, Rectangle rectTwo) {
        
        return rectOne.intersects(rectTwo);
    }
    
    /**
     * perform a regionsearch
     * 
     * @param x - distance along the x axis
     * @param y - distance along the y axis
     * @param w - width
     * @param h - height
     */
    public void regionSearch(String x, String y, String w, String h) {
        if (checkIfRectangleIsValid("", x, y, w, h, true)) {
            InfoRectangle target = new InfoRectangle("", Integer.valueOf(x),
                Integer.valueOf(y), Integer.valueOf(w), Integer.valueOf(h));
            System.out.println("Rectangles intersecting region (" + target
                .partialRectInfo() + "):");
            nameList.moveToEnd();
            nameList.append(null);
            rectangleList.moveToEnd();
            rectangleList.append(null);
            nameList.moveToStart();
            rectangleList.moveToStart();
            
            while (!rectangleList.isAtEnd()) {
                if (intersectionExists(target.getRectangle(),
                    rectangleList.getValue())) {
                    nameList.moveToPos(rectangleList.currPos());
                    InfoRectangle intersect = new InfoRectangle(nameList
                        .getValue(), rectangleList.getValue());
                    System.out.println("(" + 
                        intersect.completeRectInfo() + ")");
                }
                rectangleList.next();
            }
            
            nameList.moveToEnd();
            nameList.remove();
            nameList.moveToStart();
            rectangleList.moveToEnd();
            rectangleList.remove();
            rectangleList.moveToStart();
        }
    }
    
    /**
     * search for the rectangle with the passed title
     * 
     * @param title - title of the rectangle 
     */
    public void search(String title) {

        boolean rectFound = false;
        nameList.moveToEnd();
        nameList.append(null);
        rectangleList.moveToEnd();
        rectangleList.append(null);
        nameList.moveToStart();
        rectangleList.moveToStart();

        while (!nameList.isAtEnd()) {
            if (nameList.getValue() != null && 
                nameList.getValue().equals(title))
            {
                int index = nameList.currPos();
                rectangleList.moveToPos(index);
                InfoRectangle rectInfo = new InfoRectangle(nameList
                    .getValue(), rectangleList.getValue());
                
                System.out.println("(" + rectInfo.completeRectInfo() + ")");
                rectFound = true;
            }
            nameList.next();
        }
        if (!rectFound) 
        {
            System.out.println("Rectangle not found: " + title);
        }
        
        nameList.moveToEnd();
        nameList.remove();
        nameList.moveToStart();
        rectangleList.moveToEnd();
        rectangleList.remove();
        rectangleList.moveToStart();
    }
}
