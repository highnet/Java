import java.util.Queue;

public class IntTreeNode {
    int value;
    IntTreeNode right = null;
    IntTreeNode left = null;


    public IntTreeNode(int value) {

        this.value = value;
        this.right = null;
        this.left = null;
    }

    public boolean addToChildren(int numberToAdd) {

        if (this.value == numberToAdd) {
            return false;
        }

        if (numberToAdd < this.value) {
            if (this.left == null) {
                this.left = new IntTreeNode(numberToAdd);
                return true;
            } else {
                this.left.addToChildren(numberToAdd);
            }

        } else if (numberToAdd > this.value) {
            if (this.right == null) {
                this.right = new IntTreeNode(numberToAdd);
                return true;
            } else {
                this.right.addToChildren(numberToAdd);
            }
        }

        return false;
    }

    public boolean searchIfChildrenContain(int valueToFind) {

        return (this.value == valueToFind) || (this.right != null ? this.right.searchIfChildrenContain(valueToFind) : false) || (this.left != null ? this.left.searchIfChildrenContain(valueToFind) : false);
    }

    public int countChildrenNodes() {

        return 1 + (this.right != null ? this.right.countChildrenNodes() : 0) + (this.left != null ? this.left.countChildrenNodes() : 0);
    }

    public int maxHeightOfChildren() {


        return 1 + (Math.max((this.right != null ? this.right.maxHeightOfChildren() : 0), (this.left != null ? this.left.maxHeightOfChildren() : 0)));
    }

    public void printPreOrderChildren() {

        System.out.print(this.value + ", ");
        if (this.left != null) {
            this.left.printPreOrderChildren();
        }
        if (this.right != null) {
            this.right.printPreOrderChildren();
        }

    }


    public void paintChildrenInOrder() {

        if (this.left != null) {
            this.left.paintChildrenInOrder();
        }

        System.out.print(this.value + " ");

        if (this.right != null) {
            this.right.paintChildrenInOrder();
        }
    }

    public void paintChildrenInOrderDown() {

        if (this.right != null) {
            this.right.paintChildrenInOrderDown();
        }
        System.out.print(this.value + " ");
        if (this.left != null) {
            this.left.paintChildrenInOrderDown();
        }


    }

    public int minValueOfChildren(int min) {

        if (this.value < min) {
            min = this.value;
        }

        if (this.right == null && this.left == null) {
            return min;
        }

        return Math.min((this.right != null ? this.right.minValueOfChildren(min) : min), (this.left != null ? this.left.minValueOfChildren(min) : min));


    }

    public int maxValueOfChildren(int max) {

        if (this.value > max){
            max = this.value;
        }
        if (this.right == null && this.left == null){
            return max;
        }

        return Math.max((this.right != null ? this.right.maxValueOfChildren(max):max),(this.left != null ? this.left.maxValueOfChildren(max):max));
    }

    public void pushValues(Queue<Integer> myTree_values) {

        if (this.left != null){
            this.left.pushValues(myTree_values);
        }

        myTree_values.add(this.value);

        if (this.right != null){
            this.right.pushValues(myTree_values);
        }
    }
    
         @Override
        public String toString() {

            String s = Integer.toString(this.value);

            if (left != null) {
                s = " [" + left.toString() + "] " + s;
            }


            if (right != null) {
                s = s + " [" + right + "] ";
            }
            return s;
        }
}
