import java.util.ArrayList;

public class IntList {

    IntListNode head = null;

    public boolean isEmpty() {
        return head == null;
    }

    public boolean searchIfContains(int valueToFind) {

        IntListNode scanNode = head;

        while (scanNode.hasNext()) {
            if (scanNode.value == valueToFind) {
                return true;
            }
            scanNode = scanNode.next;
        }

        return scanNode.value == valueToFind;
    }
    
    public void reverseI2(){
        this.head = this.head.reverseI2();
    }
    
     public void reverseR(){
        this.head = this.head.reverseR();
    }

    public void reverseI() {

        IntList reversedList = new IntList();

        for (int i = size() - 1; i >= 0; i--) {
            reversedList.add(this.getIndex(i));
        }

        head = reversedList.head;


    }

    public boolean add(int value) {
        if (isEmpty()) {
            head = new IntListNode(value, null);
            return true;
        } else {
            IntListNode scanNode = head;
            while (scanNode.hasNext()) {
                scanNode = scanNode.next;
            }
            scanNode.next = new IntListNode(value, null);
            return true;
        }

    }

    public void addRecursively(int value) {
        if (isEmpty()) {
            this.head = new IntListNode(value, null);
        } else {
            this.head.addToChildren(value);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (this.size() == 1) {
            return "[" + head.value + "]";
        }

        IntListNode scanNode = head;
        String str = "";

        while (scanNode.hasNext()) {
            str += "[" + scanNode.value + "]";
            scanNode = scanNode.next;
        }

        return str + "[" + scanNode.value + "]";
    }

    public int size() {

        if (isEmpty()) {
            return 0;
        } else if (head.next == null) {
            return 1;
        }

        int size = 0;
        IntListNode scannerNode = head;

        while (scannerNode.hasNext()) {
            size++;
            scannerNode = scannerNode.next;
        }

        return size + 1;
    }

    public boolean add(int value, int index) throws IndexOutOfBoundsException {

        if (index > size() + 1) {
            throw new IndexOutOfBoundsException();

        } else if (index == size() + 1) { // put at tail
            this.add(value);
            return true;
        } else if (index == 0) {
            head = new IntListNode(value, head);
            return true;
        } else {

            IntListNode scanNode = head;
            int iteration = 0;
            boolean stopFlag = false;
            IntListNode conserverListNode = null;


            while (scanNode.hasNext() && !stopFlag) {
                if (iteration == index) {
                    conserverListNode = scanNode;
                    stopFlag = true;
                } else {
                    iteration++;
                    scanNode = scanNode.next;
                }
            }


            scanNode = head;
            iteration = 0;
            IntListNode newNode = new IntListNode(value, null);
            stopFlag = false;

            while (scanNode.hasNext() && !stopFlag) {
                if (iteration == index - 1) {

                    scanNode.next = newNode;
                    newNode.next = conserverListNode;
                    stopFlag = true;
                } else {
                    iteration++;
                    scanNode = scanNode.next;
                }
            }
            return true;
        }

    }

    public void addAll(ArrayList<Integer> al) {

        for (int i : al) {
            this.add(i);
        }
    }

    public void addAll(int[] iArr) {

        for (int i : iArr) {
            this.add(i);
        }
    }

    public void clear() {

        head = null;
    }

    public int getIndex(int index) throws IndexOutOfBoundsException, NullPointerException {

        if (isEmpty()) {
            throw new NullPointerException();
        } else if (index < 0 || index > size() - 1) {
            throw new IllegalArgumentException();
        }

        int iteration = 0;
        IntListNode scanNode = head;

        while (scanNode.hasNext()) {
            if (index == iteration) {
                return scanNode.value;
            } else {
                iteration++;
                scanNode = scanNode.next;
            }
        }

        return scanNode.value;
    }

    public boolean removeFirstInstanceOf(int valueToRemove) {

        System.out.println("want to removeFirstInstanceOf " + valueToRemove);

        if (searchIfContains(valueToRemove)) {


            IntListNode scanNode = head;

            int indexOfRemoval = 0;
            boolean stopFlag = false;

            while (scanNode.hasNext() && !stopFlag) {

                if (scanNode.value == valueToRemove) {
                    stopFlag = true;

                } else {
                    scanNode = scanNode.next;
                    indexOfRemoval++;
                }

            }

            if (indexOfRemoval == 0) { // REMOVE FIRST
                head = head.next;
            } else if (indexOfRemoval == size() - 1) { // REMOVE LAST
                scanNode = head;

                while (scanNode.next.next != null) {
                    scanNode = scanNode.next;

                }

                scanNode.next = null;

            } else {
                scanNode = head;
                int iteration = 0;

                IntListNode prev = null;
                IntListNode post = null;

                while (scanNode.hasNext()) {
                    if (indexOfRemoval - 1 == iteration) {
                        prev = scanNode;
                    }

                    if (indexOfRemoval == iteration) {
                        post = scanNode.next;
                    }

                    scanNode = scanNode.next;
                    iteration++;
                }


                if (prev != null) {
                    prev.next = post;
                }


            }


        } else {
            System.out.println("ERR: NON EXISTANT VALUE");
            return false;
        }
        return true;
    }


    private class IntListNode {
        int value;
        IntListNode next = null;

        public IntListNode(int value, IntListNode next) {
            this.next = next;
            this.value = value;
        }
        
        
        ListNode reverseI2(){
            ListNode prev = null, next = null, help = this;
            while(help != null){
                next = help.next;
                help.next = prev;
                prev = help;
                help = next;
            }
            return prev;
        }
        
        ListNode reverseR(){
            if (this.next == null){
                return this;
            }
            ListNode tail = this.next.reverseR();
            this.next.next = this;
            this.next = null;
            return tail;
        }

        public void addToChildren(int value) {
            if (this.next == null) {
                this.next = new IntListNode(value, null);
            } else {
                this.next.addToChildren(value);
            }
        }


        public boolean hasNext() {

            return this.next != null;
        }


    }


}
