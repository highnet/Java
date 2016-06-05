/**
 * Created by bokense on 05-Jun-16.
 */
public class Main {

    public static void main(String[] args) {
        IntTree myTree1 = new IntTree();

        myTree1.add(12);
        myTree1.add(4);
        myTree1.add(15);
        myTree1.add(1);
        myTree1.add(6);
        myTree1.add(13);
        myTree1.add(5);
        myTree1.add(14);
        printBinaryTree(myTree1.root,0);

        System.out.println(myTree1.searchIfContains(321831));

        System.out.println(myTree1.countNodes());

        System.out.println(myTree1.maxHeight());


        myTree1.printPreOrder();
        System.out.println();
        myTree1.printLevelOrder2(myTree1.root);

        myTree1.paintInOrder();

        myTree1.paintInOrderDown();



    }

    public static void printBinaryTree(IntTreeNode root, int level){
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
