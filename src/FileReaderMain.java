import java.util.ArrayList;

/**
 * Stage 4 FileReader class test.
 */

public class FileReaderMain {

    public static void main(String[] args){

        BST tree = FileReader.readResearcherProfiles("researchers.txt");

        System.out.println(tree.root.researcher);
        System.out.println(tree.root.getR().researcher);
        System.out.println(tree.root.getL().researcher);

        System.out.println("\n");

    }

}
