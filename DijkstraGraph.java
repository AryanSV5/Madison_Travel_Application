
// --== CS400 File Header Information ==--
// Name: Aryan Permalla
// Email: permalla@wisc.edu
// Group and Team: AS Blue
// Group TA: Jack Zhang
// Lecturer: Gary Dahl
// Notes to Grader: None
import java.util.PriorityQueue;
import java.util.ArrayList;
// import BaseGraph.Node;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Scanner;

/**
 * This class extends the BaseGraph data structure with additional methods for computing the total
 * cost and list of node data along the shortest path connecting a provided starting to ending
 * nodes. This class makes use of Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode contains data about one
   * specific path between the start node and another node in the graph. The final node in this path
   * is stored in it's node field. The total cost of this path is stored in its cost field. And the
   * predecessor SearchNode within this path is referened by the predecessor field (this field is
   * null within the SearchNode containing the starting node in it's node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost SearchNode has the
   * highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;

    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }

    public int compareTo(SearchNode other) {
      if (cost > other.cost)
        return +1;
      if (cost < other.cost)
        return -1;
      return 0;
    }
  }

  /**
   * This helper method creates a network of SearchNodes while computing the shortest path between
   * the provided start and end locations. The SearchNode that is returned by this method is
   * represents the end of the shortest path that is found: it's cost is the cost of that shortest
   * path, and the nodes linked together through predecessor references represent all of the nodes
   * along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found or when either start or
   *                                end data do not correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end) {

    PriorityQueue<SearchNode> queue = new PriorityQueue<SearchNode>();
    Hashtable<NodeType, Node> visited = new Hashtable<>();
    Node startNode = nodes.get(start);
    Node endNode = nodes.get(end);
    if (startNode == null || endNode == null) {
      throw new NoSuchElementException();
    }
    SearchNode begin = new SearchNode(startNode, 0, null);
    if (start.equals(end)) { // Case 0: If start node is end node
      return begin;
    }
    visited.put(start, startNode); // Marking the start node as visited
    SearchNode returnSearchNode = new SearchNode(nodes.get(end), -1, null);

    while (true) { // Making sure we go through our entire priority queue

      for (Edge edge : startNode.edgesLeaving) {
        if (!visited.containsKey(edge.successor.data)) { // checking if node has been visited
          double expense = edge.data.doubleValue() + begin.cost; // retrieving cost from start node
          SearchNode newNode = new SearchNode(edge.successor, expense, begin);
          queue.add(newNode); // Adding to our priority queue
          if (newNode.node.data.equals(returnSearchNode.node.data)) {
            if (returnSearchNode.predecessor != null) {
              if (newNode.cost < returnSearchNode.cost) {
                returnSearchNode = newNode;
              }
            } else {
              returnSearchNode = newNode;
            }
          }
        }
      }
      begin = queue.poll();
      if (begin == null) { // Checking to see if there are no objects in our queue
        break;
      } else if (begin.node.data.equals(end)) {
        return begin;   // Return searchNode of shortest path
      }

      startNode = begin.node; // Pulling the closest node from our queue
      visited.put(startNode.data, startNode);
    }

    throw new NoSuchElementException(); // Throw exception if no path is found

  }

  /**
   * Returns the list of data values from nodes along the shortest path from the node with the
   * provided start value through the node with the provided end value. This list of data values
   * starts with the start value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end)
      throws NoSuchElementException {
    LinkedList<NodeType> dataValues = new LinkedList<NodeType>();
    SearchNode searchPath = computeShortestPath(start, end);
    while (searchPath.predecessor != null) {
      dataValues.add(0, searchPath.node.data);
      searchPath = searchPath.predecessor;
    }
    dataValues.add(0, searchPath.node.data);

    // TODO: implement in step 7
    return dataValues;
  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest path from the node
   * containing the start data to the node containing the end data. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) throws NoSuchElementException {
    SearchNode searchPath = computeShortestPath(start, end);
    return searchPath.cost;

  }

  @Test
  /*
   * This test uses an example from lecture on Week 11 (Thursday-4/6), where we calculated 
   * the shortest path cost from node D to I. I also check the shortest path cost from node D to
   * M. 
   * 
   */
  public void lectureExampleTest() {
    DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>();
    graph.insertNode("A");
    graph.insertNode("B");
    graph.insertNode("D");
    graph.insertNode("G");
    graph.insertNode("H");
    graph.insertNode("I");
    graph.insertNode("L");
    graph.insertNode("M");
    graph.insertNode("E");
    graph.insertNode("F");


    graph.insertEdge("D", "A", 7.0);
    graph.insertEdge("D", "G", 2.0);
    graph.insertEdge("G", "L", 7.0);
    graph.insertEdge("A", "H", 8.0);
    graph.insertEdge("A", "B", 1.0);
    graph.insertEdge("A", "M", 5.0);
    graph.insertEdge("B", "M", 3.0);
    graph.insertEdge("M", "E", 3.0);
    graph.insertEdge("M", "F", 4.0);
    graph.insertEdge("F", "G", 9.0);
    graph.insertEdge("H", "B", 6.0);
    graph.insertEdge("H", "I", 2.0);
    graph.insertEdge("I", "D", 1.0);
    graph.insertEdge("I", "H", 2.0);
    graph.insertEdge("I", "L", 5.0);


    double result = graph.shortestPathCost("D", "M");
    double expected = 11.0;
    double result1 = graph.shortestPathCost("D", "I");
    double expected1 = 17.0;
    List<String> resultData = graph.shortestPathData("D", "M");
    List<String> expectedData = new ArrayList<String>();
    expectedData.add("D");
    expectedData.add("A");
    expectedData.add("B");
    expectedData.add("M");
    assertEquals(result, expected, "Incorrect shortest route calculated");
    assertEquals(result1, expected1, "Incorrect shortest route calculated");
    assertEquals(resultData, expectedData, "Incorrect shortest route calculated");

  }
  
  @Test
  /*
   * This test uses an example from lecture on Week 11 (Thursday-4/6). I calculate the shortest
   * path cost from node H to node G, and also check the sequence of data along this shortest
   * path.
   * 
   */
  public void secondLectureExampleTest() {
    DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>();
    graph.insertNode("A");
    graph.insertNode("B");
    graph.insertNode("D");
    graph.insertNode("G");
    graph.insertNode("H");
    graph.insertNode("I");
    graph.insertNode("L");
    graph.insertNode("M");
    graph.insertNode("E");
    graph.insertNode("F");


    graph.insertEdge("D", "A", 7.0);
    graph.insertEdge("D", "G", 2.0);
    graph.insertEdge("G", "L", 7.0);
    graph.insertEdge("A", "H", 8.0);
    graph.insertEdge("A", "B", 1.0);
    graph.insertEdge("A", "M", 5.0);
    graph.insertEdge("B", "M", 3.0);
    graph.insertEdge("M", "E", 3.0);
    graph.insertEdge("M", "F", 4.0);
    graph.insertEdge("F", "G", 9.0);
    graph.insertEdge("H", "B", 6.0);
    graph.insertEdge("H", "I", 2.0);
    graph.insertEdge("I", "D", 1.0);
    graph.insertEdge("I", "H", 2.0);
    graph.insertEdge("I", "L", 5.0);


    double result = graph.shortestPathCost("H", "G");
    double expected = 5.0;
    List<String> resultData = graph.shortestPathData("H", "G");
    List<String> expectedData = new ArrayList<String>();
    expectedData.add("H");
    expectedData.add("I");
    expectedData.add("D");
    expectedData.add("G");
    assertEquals(result, expected, "Incorrect shortest route calculated");
    assertEquals(resultData, expectedData, "Incorrect shortest route calculated");


  }
  
  @Test
  /*
   * This test checks the scenario where the node which we are searching for path is in the graph
   * but we have no sequence directed edges which connect the start node to end node.
   * 
   */
  public void noDirectEdgeTest() {
    DijkstraGraph<Integer, Double> graph = new DijkstraGraph<Integer, Double>();
    graph.insertNode(1);
    graph.insertNode(2);
    graph.insertNode(3);
    graph.insertNode(4);
    graph.insertNode(5);
    graph.insertNode(6);
    graph.insertNode(7);  // End node
    
    graph.insertEdge(1, 4, 3.0);
    graph.insertEdge(1, 2, 6.0);
    graph.insertEdge(2, 5, 7.0);
    graph.insertEdge(2, 6, 2.0);
    graph.insertEdge(7, 6, 4.0);   // End node has no direct sequence from start node
    
    int result = 0;
    try {
      graph.shortestPathData(1, 7);  // No Such Element Exception gets thrown
    } catch (NoSuchElementException e) {
      result = 1;
    }
    int expected = 1;
    assertEquals(result, expected, "We did not catch the No Such Element Exception");

    
  }

  @Test
  /*
   * We create a small graph with nodes 6, 1 and 11. We calculate the distance between
   * node 6 and 11 but make sure our algorithm greedily searches the graph to acquire the shortest
   * path.
   * 
   */
  public void shortestPathCostTest() {
    DijkstraGraph<Integer, Double> graph = new DijkstraGraph<Integer, Double>();
    graph.insertNode(6);
    graph.insertNode(1);
    graph.insertNode(11);

    graph.insertEdge(11, 1, 3.0);
    graph.insertEdge(6, 11, 3.0);
    graph.insertEdge(1, 11, 5.0);
    graph.insertEdge(6, 6, 1.0);
    graph.insertEdge(1, 6, 4.0);
    graph.insertEdge(6, 1, 6.0);
    graph.insertEdge(6, 11, 7.0);

    double result = graph.shortestPathCost(6, 11);
    double expected = 7.0;
    assertEquals(result, expected, "Incorrect shortest path");
  }

  @Test
  /*
   * This test checks for the case where a node is not connected in our graph and we are able
   * to appropriately throw a No Such Element Exception to handle such cases. 
   * 
   */
  public void shortestPathDataTest() {
    DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>();
    graph.insertNode("A");
    graph.insertNode("B");
    graph.insertNode("D");
    graph.insertNode("G");
    graph.insertNode("H");
    graph.insertNode("I");
    graph.insertNode("L");
    graph.insertNode("M");
    graph.insertNode("E");
    graph.insertNode("F");
    graph.insertNode("C");  // Node which we insert but do not insert an edge to the graph
    graph.insertNode("O");
    graph.insertNode("P");

    graph.insertEdge("D", "A", 7.0);
    graph.insertEdge("D", "G", 2.0);
    graph.insertEdge("G", "L", 7.0);
    graph.insertEdge("A", "H", 8.0);
    graph.insertEdge("A", "B", 1.0);
    graph.insertEdge("A", "M", 5.0);
    graph.insertEdge("B", "M", 3.0);
    graph.insertEdge("M", "E", 3.0);
    graph.insertEdge("M", "F", 4.0);
    graph.insertEdge("F", "G", 9.0);
    graph.insertEdge("H", "B", 6.0);
    graph.insertEdge("H", "I", 2.0);
    graph.insertEdge("I", "D", 3.0);
    graph.insertEdge("I", "H", 2.0);
    graph.insertEdge("I", "L", 5.0);

    int result = 0;
    try {
      graph.shortestPathData("D", "C");  // No Such Element Exception gets thrown
    } catch (NoSuchElementException e) {
      result = 1;
    }
    int expected = 1;
    assertEquals(result, expected, "We did not catch the No Such Element Exception");

  }
}


