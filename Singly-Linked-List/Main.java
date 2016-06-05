import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        IntList myList = new IntList();
        System.out.println(myList);
        System.out.println(myList.isEmpty());
        System.out.println("Size= " + myList.size());
        myList.addRecursively(0);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());
        myList.add(1);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());
        myList.addRecursively(2);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());
        myList.add(3);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());

        myList.add(4, 5);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());
        myList.add(-1, 0);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());
        myList.add(-2, 0);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());
        myList.add(8, 8);
        System.out.println(myList);
        System.out.println("Size= " + myList.size());


        ArrayList<Integer> al1 = new ArrayList<>();

        al1.add(9);
        al1.add(10);
        al1.add(11);
        al1.add(12);
        myList.addAll(al1);

        System.out.println(myList);
        System.out.println("Size= " + myList.size());

        int[] iArr1 = new int[3];

        iArr1[0] = 13;

        iArr1[1] = 14;

        iArr1[2] = 15;

        myList.addAll(iArr1);

        System.out.println(myList);
        System.out.println("Size= " + myList.size());


        System.out.println(myList.getIndex(3));


        myList.reverseI();
        System.out.println(myList);
        System.out.println("Size= " + myList.size());


        System.out.println(myList.searchIfContains(0));
        System.out.println(myList.searchIfContains(-32));


        myList.clear();

        System.out.println(myList);
        System.out.println("Size= " + myList.size());


        IntList myList2 = new IntList();

        myList2.add(0);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        myList2.add(4);


        System.out.println(myList2);


        myList2.clear();

        System.out.println(myList2);
    }
}
