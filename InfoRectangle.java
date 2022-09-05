

import java.awt.Rectangle;

/**
 * Information class for the rectangle objects
 * @author karthik
 * @version 03/09/2022
 *
 */

public class InfoRectangle {
    
    private Rectangle rectangle;
    
    private String title;
    
    /**
     * Constructor to initialize the rectangle
     * 
     * @param title - title of the rectangle
     * @param x - distance along the x axis
     * @param y - distance along the y axis
     * @param w - width if the rectangle
     * @param h - height of the rectangle
     */
    
    public InfoRectangle(String title, int x, int y, int w, int h) {
        this.title = title;
        rectangle = new Rectangle(x, y, w, h);
    }
    
    
    /**
     * set the rectangle
     * @param title - title of the rectangle
     * @param rectangle - rectangle
     */
    public InfoRectangle(String title, Rectangle rectangle) {
        this.title = title;
        this.rectangle = rectangle;
    }
    
    /**
     * get the rectangle
     * @return the rectangle
     */
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    /**
     * get the rect title
     * @return the rectangle title
     */
    
    public String getTitle() {
        return title;
    }
    
    /**
     * get the x coordinate
     * @return the x coord
     */
    public int getXcoord() {
        return (int)rectangle.getMinX();
    }
    
    /**
     * get the y coordinate
     * @return the y coord
     */
    public int getYcoord() {
        return (int)rectangle.getMinY();
    }
    
    /**
     * get the width of rect
     * @return the width
     */
    public int getWidth() {
        return (int)rectangle.width;
    }
    
    /**
     * get the height of rect
     * @return the height
     */
    public int getHeight() {
        return (int)rectangle.height;
    }
    
    /**
     * get the information related to the rectangle
     * @return string format of the dimensions of the rect
     */
    
    public String partialRectInfo() {
        return (int)rectangle.getMinX() + ", " + (int)rectangle.getMinY() +
            ", " + (int)rectangle.getWidth() + ", " 
            + (int)rectangle.getHeight();
    }
    
    /**
     * get complete info of the rectangle
     * @return - info string including title
     */
    
    public String completeRectInfo() {
        return title + ", " + partialRectInfo();
    }
    
    

}
