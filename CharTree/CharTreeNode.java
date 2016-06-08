/**
 * Created by p1408189 on 6/8/16.
 */
public class CharTreeNode {

    char value;
    int count;
    CharTreeNode right;
    CharTreeNode left;

    public CharTreeNode(char value) {

        this.count = 1;
        this.value = value;
        this.right = null;
        this.left = null;
    }


    public boolean addToChildren(char charToAdd) {
/*
        if (this.value == charToAdd) {
            return false;
        }
*/

        if (charToAdd < this.value) {
            if (this.left == null) {
                this.left = new CharTreeNode(charToAdd);
                return true;
            } else {
                this.left.addToChildren(charToAdd);
            }

        } else if (charToAdd > this.value) {
            if (this.right == null) {
                this.right = new CharTreeNode(charToAdd);
                return true;
            } else {
                this.right.addToChildren(charToAdd);
            }
        }

        return false;
    }

    public boolean searchIfChildrenContain(char valueToFind) {

        return (this.value == valueToFind) || (this.right != null ? this.right.searchIfChildrenContain(valueToFind) : false) || (this.left != null ? this.left.searchIfChildrenContain(valueToFind) : false);
    }

    public void increaseCount(char value) {

        if (this.value == value){
            this.count++;
        } else {
            if (this.right != null){
                this.right.increaseCount(value);
            }
            if (this.left != null){
                this.left.increaseCount(value);
            }
        }
    }

    public int countOf(char value) {
       if (this.value == value){
           return count;
       }
       return (this.right != null ? this.right.countOf(value) : 0) + (this.left != null ? this.left.countOf(value) : 0);


    }


    public void paintChildrenInOrder() {

        if (this.left != null ){
            this.left.paintChildrenInOrder();
        }

        System.out.print( "" + this.count + this.value + " ");

        if (this.right != null ){
            this.right.paintChildrenInOrder();
        }

    }

    public void paintChildrenInOrderIfGreaterThan(int cap) {

        if (this.left != null ){
            this.left.paintChildrenInOrderIfGreaterThan(cap);
        }

        if (cap < this.count){
            System.out.print( "" + this.count + this.value + " ");

        }

        if (this.right != null ){
            this.right.paintChildrenInOrderIfGreaterThan(cap);
        }
    }
}
