import com.nagarro.NagData.map.HashMap;
import com.nagarro.NagData.map.MapEntry;

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

//        NTree<Integer> r = new NTree<>(0);
//        NTree<Integer> r1 = r.addChild(1);
//        r.addChild(2);
//        NTree<Integer> r3 = r.addChild(3);
//        r1.addChild(4);
//        r1.addChild(5);
//        r3.addChild(6);
//        NTree<Integer> r31 = r3.addChild(7);
//        r3.addChild(8);
//        r31.addChild(9);
//
//        System.out.println(r.strBreadthFirst());
//        System.out.println(r.strDepthFirstPreOrder());
//        System.out.println(r.strDepthFirstPostOrder());

        HashMap<Integer, String> map = new HashMap<>();

        map.put(3, "Manan");
        map.put(10, "Nice");
        map.put(7, "Bruh");
        map.put(2, "lalalala");
        map.put(19, "ok pls work");
        map.put(13, "Damn i hope this works");


        for (MapEntry<Integer, String> mapEntry : map) {
            System.out.println(mapEntry);
        }

    }

}
