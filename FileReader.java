import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * File reader class to read the user passed input file
 * @author karthik
 * @version 04/09/2022
 *
 */

public class FileReader {
    private RectangleList commander;

    /**
     * Constructor for the file reader class
     */
    public FileReader() {
        commander = new RectangleList();
    }
    
    /**
     * format the string for usage in the CLI
     * 
     * @param currLine - input data
     * @return formatted string
     */
    public String stringFormat(String currLine) {
        currLine = currLine.trim();
        String[] inputData = currLine.split(" ");
        String formattedString = "";
        for (String string : inputData) {
            string = string.trim();
            if (!string.equals("")) {
                formattedString += string;
                formattedString += " ";
            }
        }
        formattedString = formattedString.trim();
        return formattedString;
    }

    /**
     * read the input file and outputs the result
     * 
     * @param inputFile - input text file
     * @throws FileNotFoundException - if the inputFile doesn't exits
     */
    public void readFile(String inputFile) throws FileNotFoundException {
        File f1 = new File(inputFile);
        Scanner sc = new Scanner(f1);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            currLine = currLine.trim();
            if (!currLine.equals("")) {
                String resultString = stringFormat(currLine);
                expressHandler(resultString);
            }
        }
        sc.close();
    }

    /**
     * perform input related operations here
     * 
     * @param userInput - input string about the operations
     */
    public void expressHandler(String userInput) {
        String[] words = userInput.split(" ");
        String operationToBePerformed = words[0];

        if (operationToBePerformed.equals("insert")) {
            if (words.length == 6) {
                commander.insert(words[1], words[2], words[3],
                    words[4], words[5]);
            }
        }
        else if (operationToBePerformed.equals("regionsearch")) {
            if (words.length == 5) {
                commander.regionSearch(words[1], words[2], words[3],
                    words[4]);
            }
        }
        else if (operationToBePerformed.equals("remove")) {
            if (words.length == 5) {
                commander.removeByCoords(words[1], words[2], words[3],
                    words[4]);
            }
            else if (words.length == 2) {
                commander.remove(words[1]);
            }
        }
        else if (operationToBePerformed.equals("search")) {
            if (words.length == 2) {
                commander.search(words[1]);
            }
        }
        else if (operationToBePerformed.equals("intersections")) {
            commander.intersections();
        }
        else if (operationToBePerformed.equals("dump")) {
            commander.dump();
        }
    }
}
