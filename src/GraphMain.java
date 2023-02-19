import java.util.ArrayList;

/**
 * Stage 6 Graph class test.
 */

public class GraphMain {

    public static void main(String[] args){


        BST tree = FileReader.readResearcherProfiles("researchers-1.txt");


        Graph g = new Graph("edges.txt", tree);

        System.out.println(g.findInfluencer("Zed").getFamilyNames());
        System.out.println(g.findInfluencer("Tae").getFamilyNames());
        System.out.println(g.findInfluencer("Nue").getFamilyNames());

    }

}
