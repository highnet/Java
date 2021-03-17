package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

import java.util.LinkedList;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Joaquin", // Vorname
                "Telleria", // Nachname
                "01408189" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für die Indexsuche
    public int findIndex(int[] numbers, int element) {

        for(int i = 0; i < numbers.length; i++){
            if (numbers[i] == element){
                return i;
            }
        }
        return -1;
    }

    // Implementieren Sie hier den Gale-Shapley-Algorithmus
    public void performGaleShapley(StableMatchingInstance instance, StableMatchingSolution solution) {


        int numberOfChildren = instance.getN();
        System.out.println("we start with " + numberOfChildren + " children");


        int[] next = new int[numberOfChildren]; // initialize the array that encodes for each child the next family to be checked for, for example if next[1] = 2, that means for child 1 next family to check is family 2
        for(int i = 0; i < numberOfChildren; i++){
            next[i] = 0;
        }

       while(solution.hasUnassignedChildren()){ // while (a child is free and can choose a family)

           int child = solution.getNextUnassignedChild(); // choose a child s
           int family = instance.getFamilyOfChildAtRank(child,next[child]);   // f is the first family in the preference list of s that has not chosen s yet

           if (solution.isFamilyFree(family)){ // if if s free
               solution.assign(child,family); // assign family to child in the solution
           }
           // elseif f prefers s to their current partner s'
                     // connect s and f
                    // disconnect s' and f
           // else gets rejected (remains free)

           next[child] = next[child] + 1;


       }



    }

    // Implementieren Sie hier Ihre Methode zur Überprüfung, ob ein Matching stabil ist.
    public boolean isStableMatching(StableMatchingInstance instance, StableMatchingSolution solution) {


        return true;
    }
}
