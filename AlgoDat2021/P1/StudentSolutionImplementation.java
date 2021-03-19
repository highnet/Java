package main.java.exercise;

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
            solution.setFree((i));
        }

        while(solution.hasUnassignedChildren()){ // while (a child is free and can choose a family)

            int child = solution.getNextUnassignedChild(); // choose a child s
            int family = instance.getFamilyOfChildAtRank(child,next[child]); // f is the first family in the preference list of s that has not chosen s yet
            int currentChildAssignedToFamily = solution.getAssignedChild(family); // s' is the current child assigned to this family
            if (solution.isFamilyFree(family)){ // if if s free
                solution.assign(child,family); // assign family to child in the solution
            } else if (instance.getRankOfChildForFamily(family,child) < instance.getRankOfChildForFamily(family,currentChildAssignedToFamily)){ // elseif f prefers s to their current partner s'
                solution.setFree(currentChildAssignedToFamily); // disconnect s' and f
                solution.assign(child,family); // connect s and f
            }
            else{
                  solution.setFree(child); // else child remains free
            }
            next[child] = next[child] + 1;
        }

    }

    // Implementieren Sie hier Ihre Methode zur Überprüfung, ob ein Matching stabil ist.
    public boolean isStableMatching(StableMatchingInstance instance, StableMatchingSolution solution) {

        int numberOfChildren = solution.getN();
        for (int family = 0; family < numberOfChildren; family++) {
            for (int child = 0; child < numberOfChildren; child++) {

                int rankOfChildForFamily = instance.getRankOfChildForFamily(family,child);
                int rankOfCurrentChildForFamily = instance.getRankOfChildForFamily(family,solution.getAssignedChild(family));

                int rankOfFamilyForChild = instance.getRankOfFamilyForChild(child,family);
                int rankOfCurrentFamilyForChild = instance.getRankOfFamilyForChild(child, solution.getAssignedFamily(child));

                if (rankOfChildForFamily < rankOfCurrentChildForFamily && rankOfFamilyForChild < rankOfCurrentFamilyForChild){
                    return  false;
                }
            }
        }
        return true;

        //for f in family:
            //for s in child:
                // if family prefers child over their currently assigned child AND if child prefers family over their currently assigned family
                        //-> return false
                        //-> else return true
    }
}
