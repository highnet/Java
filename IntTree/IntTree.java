import java.util.ArrayList;

public class IntTree {

    IntTreeNode root = null;

    public boolean isEmpty() {
        return root == null;
    }


    public void add(int value) {
        if (isEmpty()) {
            root = new IntTreeNode(value);
        } else {
            root.addToChildren(value);
        }
    }

    public boolean searchIfContains(int valueToFind) {
        return root.searchIfChildrenContain(valueToFind);

    }

    public int countNodes() {


        if (isEmpty()) {
            return 0;
        }
        return root.countChildrenNodes();
    }

    public void clear() {
        root = null;
    }

    public int maxHeight() {

        if (isEmpty()) {
            return 0;
        }

        return root.maxHeightOfChildren();
    }

    public void printPreOrder() { // PRINT PRE ORDER

        if (!isEmpty()) {
            root.printPreOrderChildren();
        }
    }

    // paint in order up
    // paint in order down
    // paint post order          (1,5,6,4,14,13,15,12)


    public void paintInOrder() {
        System.out.println();
        root.paintChildrenInOrder();
    }

    public void paintInOrderDown() {
        System.out.println();

        root.paintChildrenInOrderDown();
    }

    public void printLevelOrder2(IntTreeNode root) {

        ArrayList<IntTreeNode> al = new ArrayList<>();

        al.add(root);

        while (!al.isEmpty()){
            int levelNodes = al.size();

            while(levelNodes > 0){
                IntTreeNode n = al.remove(0);
                System.out.print(" " +  n.value);

                if (n.left != null){
                    al.add(n.left);
                }

                if (n.right != null){
                    al.add(n.right);
                }

                levelNodes--;
            }
            System.out.println();
        }

    }
}





