import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Graph.java
 * This class could be much easier to implement,
 * if I could add some other attributes to it and BST.java class.
 * But the problem specification said no other attributes in this class.
 * That's why graph class implementation may seem a bit non-practical.
 * However, I tried my best to make it understandable.
 * @version 1.0.0
 * @author musslimaz
 */

public class Graph {

    BST tree;

    /**
     * Create a Graph.
     * @param edgeListFileName name of the file which contains collaborations.
     * @param tree reference to the binary tree.
     */
    public Graph(String edgeListFileName, BST tree) {
        this.tree = tree;
        fileReader(edgeListFileName);
    }


    /**
     * Method to find suitable candidate for the researcher to collaborate with.
     * @param familyNames Family name of the researcher.
     * @return Suitable researcher's profile, if there is no one, return supplied researcher.
     */
    public Profile findInfluencer(String familyNames) {

        // list of all the profiles in the binary tree
        ArrayList<Profile> profilesList = new ArrayList<>();
        makeListOfAllProfiles(tree.root, profilesList);

        // List of the suitable candidates
        ArrayList<Profile> suitableCandidates = new ArrayList<>();
        // List of how many times each collaborated
        ArrayList<Integer> collaborationNumbersList = new ArrayList<>();

        // index of the current researcher's profile in the all profiles list
        int profileIndex;
        // Throw an exception if such researcher doesn't exist
        if (getProfileIndexByName(familyNames, profilesList) == -1) {
            System.out.println("This researcher doesn't exist");
        } else {
            profileIndex = getProfileIndexByName(familyNames, profilesList);


            // for each profile in the all profiles list
            for (Profile profile: profilesList) {
                // if current researcher never collaborated with the supplied researcher
                if (!profilesList.get(profileIndex)
                        .hasCollaboratedWith(profile)) {
                    // and current researcher is not same as the supplied researcher
                    if (profileIndex != profilesList.indexOf(profile)) {
                        // add him to the list of the suitable candidates
                        suitableCandidates.add(profile);
                        // and his number of collabs to another list
                        collaborationNumbersList.add(profile.numOfCollabs());
                    }
                }
            }

            // if there are no suitable candidates, return supplied researcher's profile
            if (suitableCandidates.isEmpty()) {
                return profilesList.get(profileIndex);
            } else {
                // otherwise, return one with the max number of collabs among them
                return suitableCandidates.get(collaborationNumbersList.indexOf(Collections.max(collaborationNumbersList)));
            }


        }

    return null;

    }

    /**
     * Read from the file.
     * @param fileName File name.
     */
    public void fileReader(String fileName) {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            populateCollaboratorsList(in); // pass the file scanner

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Populate collaborators list of each profile from the file.
     * @param in file scanner
     */
    public void populateCollaboratorsList(Scanner in) {

        // list of all the profiles in the binary tree
        ArrayList<Profile> profilesList = new ArrayList<>();
        makeListOfAllProfiles(tree.root, profilesList);

        // two-dimensional graph of ints to store whether researchers collaborated or not
        // 0 = no collaboration; 1 = collaboration exists
        int[][] collaborationGraph = new int[profilesList.size()][profilesList.size()];
        initializeGraph(collaborationGraph, profilesList.size());
        fillCollaborationGraph(collaborationGraph, profilesList);

        // read each line of the file
        while (in.hasNextLine()) {
            // two researchers' family names
            String[] familyNames = in.nextLine().split(",");
            String familyName1 = familyNames[0];
            String familyName2 = familyNames[1];

            // if two researcher profiles exist
            if (hasProfiles(familyName1, familyName2, profilesList)) {
                // if those two researchers don't have collaboration with each other
                if (!haveCollaboration(familyName1, familyName2, collaborationGraph, profilesList)) {

                    //collaborate
                    profilesList.get(getProfileIndexByName(familyName1, profilesList))
                        .collaborate(profilesList.get(getProfileIndexByName(familyName2, profilesList)));

                }

            } else {
                System.out.println("Researcher does not exist");
            }
        }


    }


    /**
     * Add all existing profiles in the binary tree to the one list.
     * Recursive method.
     * @param node node being visited.
     * @param profiles profiles list.
     */
    public void makeListOfAllProfiles(BSTNode node, ArrayList<Profile> profiles) {
        if (!(node == null)) {
            makeListOfAllProfiles(node.getL(), profiles); // visit left node
            profiles.add(node.getResearcher()); // add current node profile
            makeListOfAllProfiles(node.getR(), profiles); // visit right node
        }
    }

    /**
     * Fill the collaboration graph with zeros.
     * @param graph Collaborations graph.
     * @param graphSize Graph size (a*a graph)
     */
    public void initializeGraph(int[][] graph, int graphSize) {
        for(int i = 0; i < graphSize; i++){
            for(int j = 0; j < graphSize; j++){
                graph[i][j] = 0;
            }
        }
    }

    /**
     * Fill the collaboration graph according to the data that already exists in the tree profiles.
     * @param graph Collaborations graph.
     * @param profiles List of profiles.
     */
    public void fillCollaborationGraph(int[][] graph, ArrayList<Profile> profiles) {
        for (Profile profile: profiles) {

            for (int i = 0; i < profile.numOfCollabs(); i++) {
                graph[profiles.indexOf(profile)][profiles.indexOf(profile.collaborators.get(i))] = 1;
            }

        }
    }

    /**
     *  Check if the two researchers both exist in the profiles list.
     * @param familyName1 Researcher1 family name.
     * @param familyName2 Researcher2 family name.
     * @param profiles Profiles List.
     * @return true if the researchers exist, otherwise false
     */
    public boolean hasProfiles(String familyName1, String familyName2, ArrayList<Profile> profiles) {
        return getProfileIndexByName(familyName1, profiles) != -1
                && getProfileIndexByName(familyName2, profiles) != -1;
    }

    /**
     * Check if two researchers have a collaboration with each other.
     * @param familyName1 Researcher1 family name.
     * @param familyName2 Researcher2 family name.
     * @param graph Collaboration graph.
     * @param profiles List of profiles.
     * @return true if researchers have a collaboration, otherwise false.
     */
    public boolean haveCollaboration(String familyName1, String familyName2,
                                     int[][] graph, ArrayList<Profile> profiles) {
        int x = getProfileIndexByName(familyName1, profiles); // researcher 1 index
        int y = getProfileIndexByName(familyName2, profiles); // researcher 2 index
        if (graph[x][y] == 1) {
            return true;
        } else {
            graph[x][y] = 1; // mark a collaboration on the graph
            return false;
        }
    }

    /**
     * Get profile index by researcher's family name.
     * @param name Researcher's familyName.
     * @param profiles List of profiles.
     * @return the index of the profile from the list, -1 if it doesn't exist.
     */
    public int getProfileIndexByName(String name, ArrayList<Profile> profiles) {
        for (Profile profile: profiles) {
            if (profile.getFamilyNames().equals(name)) {
                return profiles.indexOf(profile);
            }
        }
        return -1; // profile not found in the list
    }

}
