/**
 * Created by bokense1 on 23/06/16.
 */
public class Deque {

    public IntNode head = null;

    public boolean empty() {
        return head == null;
    }

    public void addFirst(int value) {
        if (empty()) {
            head = new IntNode(value, null, null);
        } else {
            IntNode newNode = new IntNode(value, null, head);
            head.previous = newNode;
            head = newNode;
        }
    }

    public void addLast(int value) {
        if (empty()) {
            head = new IntNode(value, null, null);
        } else {
            head.addTailChild(value);
        }
    }


    @Override
    public String toString() {
        if (empty()) {
            return "[]";
        } else {
            return "[" + head.toString() + "]";
        }
    }

    public int size() {
        if (empty()) {
            return 0;
        } else {
            int counter = 0;
            IntNode scanner = head;
            while (scanner.next != null) {
                scanner = scanner.next;
                counter++;
            }
            return counter + 1;
        }
    }

    public void removeFirst() {

        if (empty()) {
            throw new NullPointerException();
        } else if (!empty()) {
            if (size() > 1) {
                head = head.next;
                head.previous = null;

            } else if (size() == 1) {
                head = null;
            }
        }
    }

    public int peekFirst() {
        if (empty()) {
            throw new NullPointerException();
        }
        return head.value;
    }

    public int peekLast() {
        if (empty()) {
            throw new NullPointerException();
        }
        IntNode scanner = head;
        while (scanner.hasNext()) {
            scanner = scanner.next;
        }
        return scanner.value;
    }

    public void removeLast() {
        if (empty()) {
            throw new NullPointerException();
        } else if (!empty()) {
            IntNode scanner = head;

            while (scanner.next.next != null) {
                scanner = scanner.next;
            }

            scanner.next = null;
        }
    }

    public boolean containsRec(int valueToSearch) {
        if (empty()) {
            return false;
        }
        return head.childrenContainRec(valueToSearch);
    }

    public boolean containsIter(int valueToSearch) {
        if (empty()) {
            return false;
        }
        IntNode scanner = head;
        while (scanner.hasNext()) {
            if (valueToSearch == scanner.value) {
                return true;
            }
            scanner = scanner.next;
        }
        return valueToSearch == scanner.value;
    }

    public int getIndex(int indexToReturn) {

        if (indexToReturn < 0 || indexToReturn >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int iteration = 0;
        IntNode scanner = head;

        while (scanner.hasNext()) {

            if (indexToReturn == iteration) {
                return scanner.value;
            }

            scanner = scanner.next;
            iteration++;

        }


        return scanner.value;
    }

    public void removeFirstInstanceOf(int removalObject) {

        if (!containsIter(removalObject)){
            System.out.println("no instances of " + removalObject + " found");
        }
        else {


            int indexOfRemoval = 0;

            IntNode scanner = head;

            while (scanner.hasNext()) {

                if (scanner.value == removalObject) {
                    break;
                }

                indexOfRemoval++;
                scanner = scanner.next;
            }

            removeAtIndex(indexOfRemoval);
        }
    }

    public void removeAtIndex(int indexOfRemoval) {

        if (indexOfRemoval < 0 || indexOfRemoval >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (indexOfRemoval == 0) {
            removeFirst();
        } else if (indexOfRemoval == size() - 1) {
            removeLast();
        } else {

            int iteration = 0;
            IntNode scanner = head;

            while (scanner.hasNext()) {

                if (indexOfRemoval == iteration) {
                    scanner.previous.next = scanner.next;
                    scanner.next.previous = scanner.previous;

                }

                scanner = scanner.next;
                iteration++;

            }

        }

    }



/*
todo:
    toArray
    addALL(int array)
    addAll(int List)
 */

    public class IntNode {
        public int value;
        public IntNode previous = null;
        public IntNode next = null;

        public IntNode(int setValue, IntNode setPrevious, IntNode setNext) {
            this.value = setValue;
            this.previous = setPrevious;
            this.next = setNext;

        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasPrevious() {
            return previous != null;
        }

        @Override
        public String toString() {
            String s = String.valueOf(this.value);

            if (this.hasNext()) {
                s += ", " + this.next.toString();
            }

            return s;
        }

        public void addTailChild(int value) {
            if (this.hasNext()) {
                this.next.addTailChild(value);
            } else {
                this.next = new IntNode(value, this, null);
            }
        }

        public boolean childrenContainRec(int valueToSearch) {
            return (valueToSearch == this.value) || (this.next != null ? this.next.childrenContainRec(valueToSearch) : false);
        }
    }
}
