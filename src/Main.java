import com.nagarro.NagData.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        System.out.println(stack);
        System.out.println(stack.getCapacity());

        for (int i = 0; i < 10; ++i)
            stack.push(i);

        System.out.println(stack);
        System.out.println(stack.getCapacity());

        stack.push(10);
        System.out.println(stack);
        System.out.println(stack.getCapacity());

    }

}
