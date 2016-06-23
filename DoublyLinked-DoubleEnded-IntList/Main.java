
public class Main {
    public static void main(String[] args) {

        Deque d = new Deque();

        d.addFirst(1);
        d.addFirst(2);
        d.addLast(3);
        d.addLast(5);
        d.addLast(1);
        System.out.println(d);

        d.removeFirstInstanceOf(1);
        System.out.println(d);
    }
}
