/**
 * Created by p1408189 on 6/8/16.
 */
public class Main {
    public static void main(String[] args) {

        CharTree ct = new CharTree();

        ct.add('f');
        ct.add('a');
        ct.add('c');
        ct.add('x');
        ct.add('b');
        ct.add('d');

        ct.add('d');

        ct.add('f');

        printBinaryTree(ct.root, 0);

        System.out.println(ct.searchIfContains('b'));


        System.out.println(ct.countOf('x'));

         ct.printInOrder();

        System.out.println();

        ct.printInOrderIfGreaterThan(1);

    }



    public static void printBinaryTree(CharTreeNode root, int level){
        if(root==null)
            return;
        printBinaryTree(root.right, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+ root.count + root.value);
        }
        else
            System.out.println(" " +root.count + root.value);
        printBinaryTree(root.left, level+1);
    }
}
