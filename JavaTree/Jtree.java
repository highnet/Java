package com.company;

import java.util.*;

public class JTree {

    public Map<JVertex,List<JVertex>> structure = new HashMap<>();

    public void addVertex(JVertex v){
        structure.put(v,new LinkedList<>());
    }

    public boolean addEdge(JVertex source, JVertex destination){

        if (!structure.containsKey(source) || !structure.containsKey(destination)) {
            return false;
        }
       return structure.get(source).add(destination);
    }

    public int size()
    {
        return structure.keySet().size();
    }

    public int getDegree(JVertex v){
        return structure.get(v).size();
    }

    public void breadthFirstSearch(JVertex v){
        Queue<JVertex> queue = new LinkedList<>();
        List<JVertex> visited = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()){
            JVertex vertex = queue.poll();
            if (!visited.contains(vertex)){
                visited.add(vertex);
                System.out.println(vertex.value);
                List<JVertex> adjacentVertices = structure.get(vertex);
                for (JVertex u:adjacentVertices) {
                    queue.offer(u);
                }
            }
        }

    }

    public void depthFirstSearch(JVertex v){
        Deque<JVertex> stack = new ArrayDeque<>();
        List<JVertex> visited = new LinkedList<>();
        stack.push(v);
        while (!stack.isEmpty()){
            JVertex vertex = stack.pop();
            if (!visited.contains(vertex)){
                visited.add(vertex);
                System.out.println(vertex.value);
                List<JVertex> adjacentVertices = structure.get(vertex);
                for (JVertex u:adjacentVertices) {
                    stack.push(u);
                }
            }
        }

    }


}

