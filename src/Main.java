import com.nagarro.NagData.LinkedList.LinkedList;

class Program {

    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(0); // 0
        l.add(1); // 1
        l.add(2); // 2
        l.add(3); // 3
        l.add(4); // 4
        l.add(5); // 5

        System.out.println(l.getCenter());

        System.out.println(l);
    }

}
