// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryan Reddy Permalla
// Email: permalla@wisc.edu
// Team: AS
// TA: Jack Zhang
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * This class extends our initial graph algorithm implementation by iterating through our 
 * graph in the breadth-first search order, without repeating any of the nodes. 
 */
public class DijkstraAR<NodeType, EdgeType extends Number> extends DijkstraGraph<NodeType, EdgeType>
 implements DijkstraInterface<NodeType, EdgeType> {
  
  public void DijkstraInterface() {
    new DijkstraAR<>();
}
  
  /**
   * This implementation of the iterator will traverse through the graph in a breadth-first search
   * order. We choose the first Node of our traversal in an arbitrary manner by getting all 
   * the values in the graph and then picking the first Node object. 
   * 
   * @return iterator
   */
  public Iterator<NodeType> iterator() {
    // return a single instance the iterator 
    return new Iterator<NodeType>() {
      Enumeration<NodeType> keys = nodes.keys();
      Node root = nodes.get(keys.nextElement()); // retrieving a node from the graph to use as root
      List<Node> visited = new ArrayList<>();
      List<Node> queue = new ArrayList<>();
      int queueCheck = 1; // A variable to check if our queue finished visiting all the nodes

      // Boolean method that returns true if the iterator has not finished
      // iterating over the tree and false if not
      public boolean hasNext() {
        if (!(queueCheck == 0)) { // if queue check is set to 0, then we finished iterating
          return true;
        }
        return false;
      }

      // returns data of the next node in breadth-first search order
      public NodeType next() {
        if (!visited.contains(root)) {  // checking to see if our starting node has been visited
          visited.add(0, root);
          for (Edge edge : root.edgesLeaving) {
            if (!visited.contains(edge.successor) && (!queue.contains(edge.predecessor))) {
              queue.add(edge.successor);
            }
          }
          for (Edge edge : root.edgesEntering) {
            if (!visited.contains(edge.predecessor) && (!queue.contains(edge.predecessor))) {
              queue.add(edge.predecessor);
            }
          }
          if (queue.isEmpty()) { // changing queue check value if we finished traversing our graph
            queueCheck = 0;
          }
          return root.data;
        } else {
          // visiting the next node in our queue
          Node currNode = queue.remove(0);
          
          if (!visited.contains(currNode)) {
            visited.add(currNode); // marking the next node as visited
          }
          for (Edge edge : currNode.edgesLeaving) {
            if (!visited.contains(edge.successor) && (!queue.contains(edge.successor))) {
              queue.add(edge.successor); // adding unvisited graph nodes to our queue
            }
          }
          
          for (Edge edge : currNode.edgesEntering) { 
            if (!visited.contains(edge.predecessor) && (!queue.contains(edge.predecessor))) {
              queue.add(edge.predecessor);
            }
          }
          if (queue.isEmpty()) {
            queueCheck = 0;
          }
            return currNode.data; // returning the data of the next node

        }
      }     
    };
  }
  
   
  /*
   * This is the method for the Backend Developer to retrieve 
   * a list of UW-Madison buildings which are encountered in the shortest path taken between
   * two locations.
   */
  public List<NodeType> shortestBuildingPathData(NodeType start, NodeType end) {
    return shortestPathData(start, end);
  }
  
  /*
   * This is the method for the Backend Developer to retrieve the cost for the shortest path taken
   * between two locations on the UW-Madison campus. 
   */
  public double shortestBuildingPathCost(NodeType start, NodeType end) {
    return shortestPathCost(start, end);
  }
  

}
