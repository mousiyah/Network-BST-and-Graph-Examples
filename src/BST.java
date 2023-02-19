/**
 * BSTNode.java
 * This class is binary search tree implementation.
 * @version 1.0.0
 * @author musslimaz
 */

public class BST {

    // binary tree root
    BSTNode root;

    /**
     * Creates new binary search tree.
     */
    public BST() {

    }

    /**
     * Inserts a profile to the binary search tree.
     * @param p Profile that should be inserted in the binary search tree.
     */
    public void insertResearcher(Profile p) {
        // If binary tree is empty, the profile is inserted in a root node
        if (root == null) {
            root = new BSTNode(p);
        } else {
            // otherwise, we call a method to insert it in it's right place
            insertNodeInOrder(root, new BSTNode(p));
        }
    }

    /**
     * Purpose of the method is to insert the new node
     * in the binary search tree in its correct order.
     *
     * The node is inserted as a left child or right child depending on
     * whether the researcher's family name comes alphabetically before or after
     * the family name of the current node's researcher (respectively).
     *
     * This is a recursive method.
     * @param currentNode Current node that we are comparing our new node to
     * @param newNode Node that should be inserted
     */
    public void insertNodeInOrder(BSTNode currentNode, BSTNode newNode) {
        // if current node < new node family name alphabetically
        if (currentNode.getResearcher().getFamilyNames().compareTo(
                newNode.getResearcher().getFamilyNames()) <= 0) {

            // Base case, where we insert new node, when the right child is null
            if (currentNode.getR() == null) {
                currentNode.setR(newNode);
            } else {
                // call this method again with the right child as a current node
                insertNodeInOrder(currentNode.getR(), newNode);
            }

        // if current node > new node family name alphabetically
        } else {

            // Base case, where we insert new node, when the left child is null
            if (currentNode.getL() == null) {
                currentNode.setL(newNode);
            } else {
                // call this method again with the left child as a current node
                insertNodeInOrder(currentNode.getL(), newNode);
            }

        }
    }

    /**
     * Print out profiles in the binary tree in order
     * Format <family names>, <given names>
     * @return String made by another method
     */
    public String printAlphabetical() {
        return inOrderTraversal(root); // we path the root node to start with
    }

    /**
     * In order traversal method for tracing binary tree
     * Recursive method
     * @param node the node being traced at the moment
     * @return traced tree output
     */
    public String inOrderTraversal(BSTNode node) {
        String value = "";

        // base case
        if (node == null) {
            return "";
        }

        value += inOrderTraversal(node.getL()) // trace left subtree
                // family and given name of the profile in the node being traced
                + node.getResearcher().getFamilyNames() + ", "
                + node.getResearcher().getGivenNames() + "\n"
                + inOrderTraversal(node.getR()); // trace right subtree

        return value;
    }

}
