import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by highnet on 2016-06-14.
 */
public class Main {
    public static void main(String[] args) {

        IntTree myTree1 = new IntTree();

        myTree1.add(12);
        myTree1.add(77);
        myTree1.add(15);
        myTree1.add(12);
        myTree1.add(32);
        myTree1.add(13);
        myTree1.add(45);
        myTree1.add(14);

        printBinaryTree(myTree1.root,0);


        IntTree myTree2 = new IntTree();

        myTree2.add(12);
        myTree2.add(77);
        myTree2.add(15);
        myTree2.add(121);
        myTree2.add(32);
        myTree2.add(13);
        myTree2.add(45);
        myTree2.add(14);

        System.out.println(myTree1.equals(myTree2));


        System.out.println(equals2(myTree1,myTree2));


    }

    private static boolean equals2(IntTree myTree1, IntTree myTree2) {

        Queue<Integer> myTree1_values = new LinkedList<>();
        Queue<Integer> myTree2_values = new LinkedList<>();

        myTree1.pushValues(myTree1_values);
        myTree2.pushValues(myTree2_values);

        return myTree1_values.equals(myTree2_values);
    }


    private static void printBinaryTree(IntTreeNode root, int level){
        if(root==null)
            return;
        printBinaryTree(root.right, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+root.value);
        }
        else
            System.out.println(root.value);
        printBinaryTree(root.left, level+1);
    }
}
