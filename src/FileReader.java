import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * FileReader.java
 * This class is for reading data from a provided file.
 * @version 1.0.0
 * @author musslimaz
 */

public class FileReader {

    /**
     * Construct BST from the file data.
     * @param filename File name.
     * @return Built Binary Search Tree.
     */
    public static BST readResearcherProfiles(String filename){
        BST bst = new BST();

        File file = new File(filename);
        Scanner in;

        try {
            in = new Scanner(file);

            List<String> parameters;
            while (in.hasNextLine()) {

                // get all profile parameters as a list
                parameters = Arrays.asList(in.nextLine().split(","));

                // create new profile with parameters and insert it in the tree
                bst.insertResearcher(createProfile(parameters));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bst;
    }

    /**
     * Create new Profile with parameters from the file.
     * @param parameters New profile parameters.
     * @return New created profile.
     */
    public static Profile createProfile(List<String> parameters) {
        Profile p = new Profile(  parameters.get(1), // family name
                                            parameters.get(0), // given name
                                            // PHD date (YYYY-MM-DD)
                                            Integer.parseInt(parameters.get(2).split("-")[0]),
                                            Integer.parseInt(parameters.get(2).split("-")[1]),
                                            Integer.parseInt(parameters.get(2).split("-")[2]),
                                            parameters.get(3), // email address
                                            // research interests
                                            new ArrayList<>(Arrays.asList(parameters.get(4).split("/"))) );

        return p;
    }


}
