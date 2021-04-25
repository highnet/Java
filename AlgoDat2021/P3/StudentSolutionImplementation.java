package main.java.exercise;

import main.java.exercise.tree.AVLNode;
import main.java.exercise.tree.Node;
import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Joaquin", // Vorname
                "Telleria", // Nachname
                "01408189" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für das Einfügen in einen einfachen binären Suchbaum
    public void insertSimpleBinarySearchTree(Node root, Node newNode) {
        //System.out.println("insert("+root.getKey()+", "+newNode.getKey()+")");
        Node predecessorNode = null; // predecessorNode keeps track of the previous node of the scanner node.
        Node scannerNode = root; // scanner node is our iteration reference node

        // Part 1 of the algorithm: Find the predecessor node of the new node to insert.
        // the first part of the while loop checks whether the iteration reference scanner node hasnt hit a dead end
        // the second part of the while loop checks whether the node already exits in the tree
        while(scannerNode != null && scannerNode.getKey() != newNode.getKey()){
            predecessorNode = scannerNode;
            if (newNode.getKey() < scannerNode.getKey()){
                scannerNode = scannerNode.getLeftChild();
            }
            else{
                scannerNode = scannerNode.getRightChild();
            }
        }

        // Part 2 of the algorithm: Insert the new node to the left or to the right of its predecessor node.
        if (predecessorNode == null){ // if predecessor is null, something has gone wrong.
            return; // then don't add to the tree
        }
        if (newNode.getKey() < predecessorNode.getKey()){ // check if the new node should go left or right of the predecessor
            predecessorNode.attachNodeLeft(newNode); // attach the smaller value to the left
        }else {
            predecessorNode.attachNodeRight(newNode); // attach the larger value to the right
        }

    }

    // Implementieren Sie hier Ihre Lösung für das Einfügen in einen AVL-Baum
    public void insertAVLTree(AVLNode p, AVLNode newNode) {

    }

}
