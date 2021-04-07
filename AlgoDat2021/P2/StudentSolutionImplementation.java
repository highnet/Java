package main.java.exercise;

import main.java.exercise.graph.Graph;
import main.java.exercise.helper.Heuristic;
import main.java.exercise.helper.Point;
import main.java.exercise.helper.PriorityQueue;
import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        System.out.println("Hello A* from source: " + source + " to target: " + target + ", with total vertices: " + g.numberOfVertices() +  ", and total edges: " + g.numberOfEdges() );

        HashMap<Integer, Double> gScore = new HashMap<>(); // gscore = {map with default value of infinity} use the gscore map to store gscore
        gScore.put(source,0.0);  // gScore(s) = 0  set g score of initial node to 0

        HashMap<Integer,Integer> cameFrom = new HashMap<>();     // cameFrom = {an empty map}  use the cameFrom map to store predecessors
        q.add(source,h.evaluate(source));// Priority Queue Q ← {(s, h(s))} add source node to the open set

        while(!q.isEmpty()){ // while Q =/= ∅ do
            int x = q.removeFirst(); // x ← remove node x with minimal cost f(x) = g(x) + h(x) from Q
            if (x == target){ // if x = target then
                System.out.println("Found target node");
                tracePath(cameFrom,x); // build the path s, . . . , predecessors_of(t), t
            }

            int[] xSuccessors = g.getSuccessors(x);
            System.out.println("x has " + xSuccessors.length + " successors");
            for (int i = 0; i < xSuccessors.length; i++){
                System.out.println("successor: " + i);
                double gCandidate = gScore.getOrDefault(x,Double.MAX_VALUE) + g.getEdgeWeight(x,xSuccessors[i]); //gcandidate ← g(x) + w_xv
                System.out.println("gCandidate = " + gCandidate);

            }


        }
    }

    private void tracePath(HashMap<Integer, Integer> cameFrom, int x) {
        System.out.println("TODO: TRACE PATH");
    }


}
