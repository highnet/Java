package com.company;

public class Main {

    public static void main(String[] args) {
        JTree jt1 = new JTree();

        JVertex jv1 = new JVertex(1);
        JVertex jv2 = new JVertex(2);
        JVertex jv3 = new JVertex(3);
        JVertex jv4 = new JVertex(4);
        JVertex jv5 = new JVertex(5);
        JVertex jv6 = new JVertex(6);
        JVertex jv7 = new JVertex(7);
        JVertex jv8 = new JVertex(8);
        JVertex jv9 = new JVertex(9);
        JVertex jv10 = new JVertex(10);

        jt1.addVertex(jv1);
        jt1.addVertex(jv2);
        jt1.addVertex(jv3);
        jt1.addVertex(jv4);
        jt1.addVertex(jv5);
        jt1.addVertex(jv6);
        jt1.addVertex(jv7);
        jt1.addVertex(jv8);
        jt1.addVertex(jv9);
        jt1.addVertex(jv10);

        jt1.addEdge(jv1,jv2);
        jt1.addEdge(jv1,jv3);
        jt1.addEdge(jv2,jv4);
        jt1.addEdge(jv3,jv5);
        jt1.addEdge(jv3,jv6);
        jt1.addEdge(jv3,jv10);
        jt1.addEdge(jv6,jv9);
        jt1.addEdge(jv6,jv7);
        jt1.addEdge(jv7,jv8);

        jt1.breadthFirstSearch(jv1);
        System.out.println("XXX");
        jt1.depthFirstSearch(jv1);


    }
}
