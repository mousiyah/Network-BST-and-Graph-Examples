/**
 * BSTNode.java
 * This class is binary tree node implementation.
 * @version 1.0.0
 * @author musslimaz
 */

public class BSTNode {

    Profile researcher;

    // Left and right nodes
    BSTNode l;
    BSTNode r;

    /**
     * Creates new binary tree node
     * @param researcher Reference to a Profile
     */
    public BSTNode(Profile researcher) {
        this.researcher = researcher;
    }

    /**
     * @return researcher's profile associated with this node
     */
    public Profile getResearcher() {
        return researcher;
    }

    /**
     * @return left node
     */
    public BSTNode getL() {
        return l;
    }

    /**
     * @param l left node
     */
    public void setL(BSTNode l) {
        this.l = l;
    }

    /**
     * @return right node
     */
    public BSTNode getR() {
        return r;
    }

    /**
     * @param r right node
     */
    public void setR(BSTNode r) {
        this.r = r;
    }
}
