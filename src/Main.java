import com.nagarro.NagData.NTree;

public class Main {

    public static void main(String[] args) {

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

        System.out.println(r.elementsAtLevel(2));

    }

}
