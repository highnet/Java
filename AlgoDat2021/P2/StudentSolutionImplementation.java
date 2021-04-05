package main.java.exercise;

import main.java.exercise.graph.Graph;
import main.java.exercise.helper.Heuristic;
import main.java.exercise.helper.Point;
import main.java.exercise.helper.PriorityQueue;
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

    // Implementieren Sie hier Ihre Lösung für die euklidische Distanz
    public double heuristicManhattanDistance(Point point1, Point point2) {
        // The manhattan distance is the sum of the absolute differences
        // it defines the distance of two points  on a two dimensional grid system
        // The latter names allude to the grid layout of most streets on the island of Manhattan
        return Math.abs(point1.getX()-point2.getX()) + Math.abs(point1.getY()-point2.getY());
    }

    // Implementieren Sie hier Ihre Lösung für die euklidische Distanz
    public double heuristicEuclideanDistance(Point point1, Point point2) {
        //  Euclidean distance between two points in Euclidean space is the length of a line segment between the two points.
        //  It can be calculated from the Cartesian coordinates of the points using the Pythagorean theorem,
        //  therefore occasionally being called the Pythagorean distance.
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(),2) + Math.pow(point1.getY() - point2.getY(),2));
    }

    // Implementieren Sie hier Ihre Lösung für A*
    public void aStar(Graph g, PriorityQueue q, Heuristic h, int source, int target, int[] path) {
        // A* find the shortest path from a source node to a target node in a directed weighted graph G=(V,A)
        // A Priority Queue data structure is used to help solve A*
        // the source node is added to the open set
        // In order to traverse to the next node, we will pick the node with the lowest f_score from the open set
        // when we pick a node, we will update the f_score of all of its neighbors
        // f_score = g_score + h_score.
        // Where g_score is the shortest known cost distance to a node and
        // h_score is its heuristic score(distance to end node)

        // Graph g -> the graph we will conduct the A* search on
        // PriorityQueue q -> data structure to model the open set and help pick the next node in the open set with lowest f_score
        // Heuristic h -> heuristic used to determine the distance of a node to the end node

        // gscore = {map with default value of infinity}
        // gscore(s) = 0 # set g score of initial node to 0
        // gscore(x) = infinity for all x in V except s # set g score of other nodes to infinity

        // cameFrom = {an empty map} null for all x in V // set all predecessors to null, use the cameFrom map to store predecessors

        // Q.enqueue(s,h(s)) # add start node to the open set with its h_score

        // while Q =/= empty do # while q is not empty
        //          currentNode = take the node x with minimal f_cost from priorityqueue Q
        //          if curentNode == t # we found our source node?
        //                   return trace_path(cameFrom,currentNode)
        //          add current node to closed list
        //          for all v:(CurrentNode,v) in A do # for all neighbors of x
        //            if closedlist.contains(successor) then continue
        //            tentative_g = g(currentNode) + c(currentNode, successor)
        //            if openlist.contains(successor) and tentative_g >= gScore(successor) then continue
        //            successor.predecessor := currentNode
        //            g(successor) = tentative_g
        //            f := tentative_g + h(successor)
        //            if openlist.contains(successor) then
        //               openlist.updateKey(successor, f)
        //            else
        //               openlist.enqueue(successor, f)

    }


}
