/**
 * Created by p1408189 on 6/8/16.
 */
public class CharTree {
    CharTreeNode root = null;


    public boolean isEmpty() {
        return root == null;
    }

    public void add(char value) {
        if (isEmpty()) {
            root = new CharTreeNode(value);
        } else if (!searchIfContains(value)){
            root.addToChildren(value);
        } else {
            root.increaseCount(value);
        }
    }


    public boolean searchIfContains(char valueToFind){

        if (root == null){
            return false;
        }
        return root.searchIfChildrenContain(valueToFind);
    }


    public int countOf(char value) {

        if (root == null){
            return 0;
        }

        else return root.countOf(value);
    }

    public void printInOrder() {
        if (root != null){
            root.paintChildrenInOrder();
        }
    }

    public void printInOrderIfGreaterThan(int cap) {

        if (root != null){
            root.paintChildrenInOrderIfGreaterThan(cap);
        }
    }
}
