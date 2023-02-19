import java.util.ArrayList;

/**
 * Stage 2 BSTNode class test.
 */

public class BSTNodeMain {

    public static void main(String[] args){


        ArrayList<String> arr = new ArrayList<String>();
        arr.add("a");
        arr.add("b");

        Profile a = new Profile("Nue","Mira", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile b = new Profile("Tae","Le", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile c = new Profile("Hon","Ki", 2004, 12, 8, "hdh@gmail.com", arr);

        a.collaborate(b);
        c.collaborate(a);

        BSTNode aNode = new BSTNode(a);
        BSTNode bNode = new BSTNode(b);
        BSTNode cNode = new BSTNode(c);

        aNode.setL(bNode);
        aNode.setR(cNode);

        System.out.println(aNode.getResearcher().getFamilyNames());
        System.out.println(aNode.getR().getResearcher().getFamilyNames());
        System.out.println(aNode.getL().getResearcher().getFamilyNames());


    }

}
