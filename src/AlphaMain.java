import java.util.ArrayList;

/**
 * Stage 5 BST Alpabetical Order method test.
 */

public class AlphaMain {

    public static void main(String[] args){

        ArrayList<String> arr = new ArrayList<String>();
        arr.add("a");
        arr.add("b");

        Profile a = new Profile("Nue","Mira", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile b = new Profile("Tae","Le", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile c = new Profile("Hon","Ki", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile d = new Profile("Bol","Sa", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile e = new Profile("Zed","Sa", 2004, 12, 8, "hdh@gmail.com", arr);
        Profile f = new Profile("IK","Sa", 2004, 12, 8, "hdh@gmail.com", arr);

        a.collaborate(b);
        c.collaborate(d);
        c.collaborate(a);
        d.collaborate(a);

        BST bst = new BST();

        bst.insertResearcher(a);
        bst.insertResearcher(b);
        bst.insertResearcher(c);
        bst.insertResearcher(d);
        bst.insertResearcher(e);
        bst.insertResearcher(f);


        System.out.println(bst.printAlphabetical());

    }

}
