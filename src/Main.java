import com.nagarro.NagData.NTree;

public class Main {

    public static void main(String[] args) {

        /*
                  0
                / | \
               /  |  \
              1   2   3
             / \    / | \
            4   5  6  7  8
                      |
                      9
         */

        NTree<Integer> r = new NTree<>(0);
        NTree<Integer> r1 = r.addChild(1);
        r.addChild(2);
        NTree<Integer> r3 = r.addChild(3);
        r1.addChild(4);
        r1.addChild(5);
        r3.addChild(6);
        NTree<Integer> r31 = r3.addChild(7);
        r3.addChild(8);
        r31.addChild(9);

        System.out.println(r.strBreadthFirst());
        System.out.println(r.strDepthFirstPreOrder());
        System.out.println(r.strDepthFirstPostOrder());

    }

}
