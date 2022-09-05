

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * 
 * Rectangle1 file with the main function
 * 
 * @author karthik
 * @version 03/09/2022
 */


public class Rectangle1 {
    
    /**
     * @param args - parameters passed through CLI
     * @throws FileNotFoundException - if file not found
     * @throws IOException - if input/ output is wrong
     */
    public static void main(String[] args)
        throws IOException,
        FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Try again");
        }
        else {
            FileReader reader = new FileReader();
            reader.readFile(args[0]);
        }
    }

}
