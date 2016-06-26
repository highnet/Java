/**
 * Created by bokense1 on 25/06/16.
 */
class GenericList<E> {

    private GenericNode head = null;

    GenericList() {

    }

    void add(E element) {

        if (isEmptyList()) {
            head = new GenericNode(element, null);
        } else {
            head.addToChildren(element);
        }
    }


    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof GenericList)) {
            return false;
        }

        if ((this.getClass() != other.getClass())) {
            return false;
        }


        return equals(this.head, ((GenericList) other).head);


    }

    private boolean equals(GenericNode node1, GenericNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == node2;
        }

        if (node1.element != node2.element) {
            return false;
        }

        return equals(node1.next, node2.next);
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

    private boolean contains(E element) {

        return head != null && this.head.childrenContain(element);
    }

    public void clear() {
        head = null;
    }

    private int size() {

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

    void removeValueAtIndex(int indexOfRemoval) {

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
                if (previous != null) {
                    previous.next = follower;
                }
            }
        }


    }

    private void removeFirst() {

        head = head.next;

    }

    private void removeLast() {

        GenericNode scanNode = head;

        while (scanNode.next.hasNext()) {
            scanNode = scanNode.next;
        }

        scanNode.next = null;

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

        GenericList<E> copy() {
        GenericList<E> newList = new GenericList<E>();
        GenericNode scanNode = this.head;

        while (scanNode.hasNext()) {
            newList.add(scanNode.element);
            scanNode = scanNode.next;
        }

        newList.add(scanNode.element);

        return newList;

    }

    private class GenericNode {
        E element = null;
        GenericNode next;

        GenericNode(E element, GenericNode next) {
            this.element = element;
            this.next = next;
        }

        boolean hasNext() {
            return next != null;
        }

        public boolean isEmptyNode() {
            return (element == null);
        }

        void addToChildren(E element) {
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

        boolean childrenContain(E element) {

            return this.element.equals(element) || (this.next != null && this.next.childrenContain(element));
        }
    }
}
