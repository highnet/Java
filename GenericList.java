/**
 * Created by bokense1 on 25/06/16.
 */
public class GenericList<E> {

    GenericNode head = null;

    public GenericList() {

    }

    public void add(E element) {

        if (isEmptyList()) {
            head = new GenericNode(element, null);
        } else {
            head.addToChildren(element);
        }
    }


    @Override
    public String toString() {
        if (isEmptyList()) {
            return "";
        }
        return "[" + head.toString() + "]";
    }

    private boolean isEmptyList() {
        return head == null;
    }

    public int getIndexOf(E searchedElement) {
        int indexOfElement = 0;

        GenericNode scanNode = head;

        while (scanNode.hasNext()) {
            if (scanNode.element == searchedElement) {
                break;
            }
            indexOfElement++;
            scanNode = scanNode.next;
        }
        return indexOfElement;
    }

    public E getElementAtIndex(int index) {

        int counter = 0;

        GenericNode scanNode = head;

        while (scanNode.hasNext()) {
            if (counter == index) {
                return scanNode.element;
            }
            counter++;
            scanNode = scanNode.next;
        }

        return scanNode.element;
    }

    public boolean contains(E element) {

        if (head == null) {
            return false;
        }
        return this.head.childrenContain(element);
    }

    public void clear() {
        head = null;
    }

    public int size() {

        int size = 0;

        GenericNode scanNode = head;

        while (scanNode.next != null) {
            size++;
            scanNode = scanNode.next;
        }

        return size + 1;
    }

    public int findIndexOf(E key) {


        if (this.contains(key)) {
            int indexOfRemoval = 0;

            GenericNode scanNode = head;

            while (scanNode.hasNext()) {
                if (scanNode.element == key) {
                    return indexOfRemoval;
                }
                indexOfRemoval++;
                scanNode = scanNode.next;
            }
            if (scanNode.element == key) {
                return indexOfRemoval;
            }
        }
        return -1;
    }

    public void removeValue(int indexOfRemoval) {

        if (indexOfRemoval != -1) {
            if (indexOfRemoval == 0) {
                this.removeFirst();
            } else if (indexOfRemoval == this.size() - 1) {
                this.removeLast();
            } else {
                int iteration = 0;
                GenericNode scanNode = this.head;
                GenericNode previous = null;
                GenericNode follower = null;


                while (scanNode.hasNext()) {
                    if (iteration == indexOfRemoval - 1) {
                        previous = scanNode;
                    }
                    if (iteration == indexOfRemoval) {
                        follower = scanNode.next;
                    }
                    scanNode = scanNode.next;
                    iteration++;
                }
                previous.next = follower;
            }
        }


    }

    private void removeFirst() {

        head = head.next;

    }

    private void removeLast() {

    }

    public void updateValueAtIndex(int updateIndex, E value) {
        int iteration = 0;
        GenericNode scanNode = head;
        while (scanNode.hasNext()) {

            if (iteration == updateIndex) {
                scanNode.element = value;
                return;
            }
            iteration++;
            scanNode = scanNode.next;
        }
        scanNode.element = value;
    }

    private class GenericNode {
        E element = null;
        GenericNode next;

        public GenericNode(E element, GenericNode next) {
            this.element = element;
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean isEmptyNode() {
            return (element == null);
        }

        public void addToChildren(E element) {
            if (this.hasNext()) {
                this.next.addToChildren(element);
            } else {
                this.next = new GenericNode(element, null);
            }
        }

        @Override
        public String toString() {
            String str = "";

            str += this.element.toString();

            if (hasNext()) {
                str += ", " + this.next.toString();
            }

            return str;

        }

        public boolean childrenContain(E element) {

            return this.element.equals(element) || (this.next != null && this.next.childrenContain(element));
        }
    }
}
