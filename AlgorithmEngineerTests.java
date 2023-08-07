// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryan Reddy Permalla
// Email: permalla@wisc.edu
// Team: AS
// TA: Jack Zhang
// Lecturer: Gary Dahl
// Notes to Grader: This is where I develop my Algorithm Engineer Tests

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import BaseGraph.Node;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * 
 * This class is designed to check if our Algorithm Engineer extension of the our initial graph
 * algorithm can appropriately facilitate the needs of application by assisting the Backend
 * Developer.
 * 
 * @author Aryan Permalla
 *
 */
public class AlgorithmEngineerTests {
  
  @Test
  /*
   * This method tests the functionality of the iterator object added as part of our graph 
   * utilizing the Dijkstra algorithm. The iterator should return node data in the breadth-first
   * search order. 
   * 
   */
  public void iteratorTest1() {
    DijkstraAR<Integer, Double> graph = new DijkstraAR<>(); // instantiating a a new graph
    graph.insertNode(1);
    graph.insertNode(2);
    graph.insertNode(4);
    graph.insertNode(5);
    graph.insertNode(6);
    graph.insertNode(7);
    
    
    graph.insertEdge(1, 4, 3.0);
    graph.insertEdge(1, 2, 6.0);
    graph.insertEdge(2, 5, 7.0);
    graph.insertEdge(2, 6, 2.0);
    graph.insertEdge(6, 7, 4.0); 

    
    Iterator<Integer> iterator = graph.iterator(); // Initializing our iterator 
    
    
    String actualList = "";
    String expected = "7, 6, 2, 5, 1, 4, ";  // expected BFS output for graph
    while (iterator.hasNext()) {
      actualList += iterator.next().toString() + ", ";  // Adding values in BFS order
    } 

    assertEquals(expected, actualList);
    
  }
  
  @Test
  /*
   * This method tests the functionality of the iterator object added as part of our graph 
   * utilizing the Dijkstra algorithm. The iterator should return node data in the breadth-first
   * search order. 
   * 
   * We also add a few unconnected nodes to graph and test if our iterator doesn't return them.
   * 
   */
  public void iteratorTest2() {
    DijkstraAR<String, Double> graph = new DijkstraAR<>(); // instantiating a a new graph
    graph.insertNode("A");
    graph.insertNode("B");
    graph.insertNode("C");    // unconnected node
    graph.insertNode("D");
    graph.insertNode("E");
    graph.insertNode("F");   // unconnected node
    graph.insertNode("G");
    graph.insertNode("H");
    graph.insertNode("I");
    graph.insertNode("J");
    
    graph.insertEdge("I", "J", 5.0);
    graph.insertEdge("J", "H", 2.0);
    graph.insertEdge("H", "B", 9.0);
    graph.insertEdge("A", "G", 6.0);
    graph.insertEdge("J", "A", 3.0);
    graph.insertEdge("D", "A", 33.0);

    
    Iterator<String> iterator = graph.iterator(); // Initializing our iterator 
    
    
    String actualList = "";
    String expected = "D, A, G, J, H, I, B, ";  // expected BFS output for graph
    while (iterator.hasNext()) {
      actualList += iterator.next().toString() + ", ";  // Adding values in BFS order
    } 

    assertEquals(expected, actualList);
    
  }
  
  @Test
  /*
   * This method tests the functionality of the iterator object and addresses cases where we
   * have a cyclic graph and we don't accidentally re-visit a previous node destination.
   * 
   */
  public void iteratorTest3() {
    DijkstraAR<String, Double> graph = new DijkstraAR<>(); // instantiating a a new graph
    graph.insertNode("A");
    graph.insertNode("B");
    graph.insertNode("C");   
    graph.insertNode("D");
    graph.insertNode("E");
    graph.insertNode("F");   
    graph.insertNode("G");
    graph.insertNode("H");
    graph.insertNode("I");
    graph.insertNode("J");
    
    //Arranging the nodes in the form of a cyclic graph
    
    graph.insertEdge("A", "B", 7.0);
    graph.insertEdge("A", "I", 4.0);
    graph.insertEdge("I", "H", 8.0);
    graph.insertEdge("B", "D", 2.0);
    graph.insertEdge("B", "C", 8.0);
    graph.insertEdge("C", "E", 35.0);
    graph.insertEdge("D", "E", 42.0);
    graph.insertEdge("E", "F", 73.0);
    graph.insertEdge("F", "H", 6.0);

    
    Iterator<String> iterator = graph.iterator(); // Initializing our iterator 
    
    
    String actualList = "";
    String expected = "D, E, B, F, C, A, H, I, ";  // expected BFS output for graph
    while (iterator.hasNext()) {
      actualList += iterator.next().toString() + ", ";  // Adding values in BFS order
    } 

    assertEquals(expected, actualList);
    
  }
  
  @Test
  /*
   * This method runs a simulated version of our final UW-Madison campus buildings and retrieves 
   * the node data for our map in the appropriate BFS order
   * 
   */
  public void simulationTest1() {
    DijkstraAR<String, Double> graph = new DijkstraAR<>(); // instantiating a a new graph
    graph.insertNode("Van Vleck");
    graph.insertNode("Bascom Hall");
    graph.insertNode("Engineering Hall");    
    graph.insertNode("Van Hise");
    graph.insertNode("Sewell Social Sciences");
    graph.insertNode("Mosse Humanities");   
    graph.insertNode("Memorial Union");
    graph.insertNode("Union South");

    
    graph.insertEdge("Van Hise", "Van Vleck", 2.0);
    graph.insertEdge("Van Vleck", "Sewell Social Sciences", 3.0);
    graph.insertEdge("Van Vleck", "Union South", 9.0);
    graph.insertEdge("Van Vleck", "Bascom Hall", 1.0);
    graph.insertEdge("Bascom Hall", "Memorial Union", 4.0);
    graph.insertEdge("Bascom Hall", "Mosse Humanities", 4.0);

    
    Iterator<String> iterator = graph.iterator(); // Initializing our iterator 
    
    
    String actualList = "";
    // expected BFS output for graph
    String expected = "Mosse Humanities, Bascom Hall,"
        + " Memorial Union, Van Vleck, Sewell Social Sciences, Union South, Van Hise, ";  
    while (iterator.hasNext()) {
      actualList += iterator.next().toString() + ", ";  // Adding values in BFS order
    } 

    assertEquals(expected, actualList);
    
  }
  @Test
  /*
   * This method runs a simulated version of our final UW-Madison campus buildings and retrieves 
   * the shortest building path cost and data between any two locations. 
   * 
   */
  public void simulationTest2() {
    DijkstraAR<String, Double> graph = new DijkstraAR<>(); // instantiating a a new graph
    graph.insertNode("Van Vleck");
    graph.insertNode("Bascom Hall");
    graph.insertNode("Engineering Hall");    
    graph.insertNode("Van Hise");
    graph.insertNode("Sewell Social Sciences");
    graph.insertNode("Mosse Humanities");   
    graph.insertNode("Memorial Union");
    graph.insertNode("Union South");

    
    graph.insertEdge("Van Hise", "Van Vleck", 2.0);
    graph.insertEdge("Van Vleck", "Sewell Social Sciences", 3.0);
    graph.insertEdge("Van Vleck", "Union South", 9.0);
    graph.insertEdge("Van Vleck", "Bascom Hall", 1.0);
    graph.insertEdge("Bascom Hall", "Memorial Union", 4.0);
    graph.insertEdge("Bascom Hall", "Mosse Humanities", 4.0);
    
    // Calculating path cost and data from Van Vleck to Mosse Humanities
    String expected = "[Van Vleck, Bascom Hall, Mosse Humanities]";
    List <String> actualList = graph.shortestBuildingPathData("Van Vleck", "Mosse Humanities"); 
    Double expectedCost = 5.0;
    Double cost = graph.shortestBuildingPathCost("Van Vleck", "Mosse Humanities"); 
    assertEquals(expected, actualList.toString());
    assertEquals(expectedCost, cost);
    
    // Calculating path cost and data from Van Hise to Memorial Union
    String expectedData = "[Van Hise, Van Vleck, Bascom Hall, Memorial Union]";
    List <String> actualListData = graph.shortestBuildingPathData("Van Hise", "Memorial Union"); 
    Double expectedCostData = 7.0;
    Double costData = graph.shortestBuildingPathCost("Van Hise", "Memorial Union");

    assertEquals(expectedData, actualListData.toString());
    assertEquals(expectedCostData, costData);
    
  }
  
  @Test
  /*
   * Ensures the user is prompted with the features available to them
   * in the application. 
   */
  public void CodeReviewOfFrontendDeveloperFirstTest() {
    TextUITester test = new TextUITester("Q");
    Scanner input = new Scanner(System.in);
    MadisonAcademicBuildingsReader reader = new MadisonAcademicBuildingsReaderDW();
    DijkstraInterface<MadisonAcademicBuildings,Number> graph = new DijkstraAR<>();
    MadisonBuildingMapBackendInterface backend = new MadisonBuildingMapBackendBD(graph,reader);
    UserInterfaceInterface app = new UserInterfaceFD(input, backend);
    app.runCommandLoop();
    String output = test.checkOutput();
    String expected = "Choose an option from the list below:";
    String expectedTwo = "2: Calculate travel time";
    String expectedThree = "4: Get possible path";
    assertTrue(output.contains(expected), "Incorrect user prompt");
    assertTrue(output.contains(expectedTwo), "Incorrect user prompt");
    assertTrue(output.contains(expectedThree), "Incorrect user prompt");
    
  }
  
  @Test
  /*
   * Ensures the application prompts the user for path between locations and returns
   * the data to the user in the correct format.
   * 
   */
  public void CodeReviewOfFrontendDeveloperSecondTest() {
    TextUITester test = new TextUITester("1\n2\nvan_vleck_hall\ningraham_hall\nQ\n");
    Scanner input = new Scanner(System.in);
    MadisonAcademicBuildingsReader reader = new MadisonAcademicBuildingsReaderDW();
    DijkstraInterface<MadisonAcademicBuildings,Number> graph = new DijkstraAR<>();
    MadisonBuildingMapBackendInterface backend = new MadisonBuildingMapBackendBD(graph,reader);
    UserInterfaceInterface app = new UserInterfaceFD(input, backend);
    app.runCommandLoop();
    String output = test.checkOutput();
    String expected = "The shortest path is: van vleck hall -> ingraham hall";
    String expectedResponse = "Choose between 2-3 locations:";
    assertTrue(output.contains(expected), "Incorrect user prompt");
    assertTrue(output.contains(expectedResponse), "Incorrect user prompt");
  }
  @Test
  /*
   * We check if a path exists between two academic buildings and then proceed to find the shortest
   * data between the two locations, and then we quit the application
   * 
   */
  public void IntegrationFirstTest() {
    TextUITester test = new TextUITester("3\nhumanities\nagriculture_h"
        + "all\n1\n2\nhumanities\nagriculture_hall\nQ");
    Scanner input = new Scanner(System.in);
    MadisonAcademicBuildingsReader reader = new MadisonAcademicBuildingsReaderDW();
    DijkstraInterface<MadisonAcademicBuildings,Number> graph = new DijkstraAR<>();
    MadisonBuildingMapBackendInterface backend = new MadisonBuildingMapBackendBD(graph,reader);
    UserInterfaceInterface app = new UserInterfaceFD(input, backend);
    app.runCommandLoop();
    String output = test.checkOutput();
    String expected = "Thank you for using UW-Madison Map Search App";
    String expectedResponse = "Path exist";
    String expectedResponseTwo = "The shortest path is: humanities ->"
        + " union south -> agriculture hall";
    assertTrue(output.contains(expected), "Incorrect user prompt");
    assertTrue(output.contains(expectedResponse), "Incorrect user prompt");
    assertTrue(output.contains(expectedResponseTwo), "Incorrect user prompt");
  }
  
  @Test
  /*
   * We check for the path data between memorial library and union south. We also calculate the
   * the estimated travel time between two locations. 
   * 
   */
  public void IntegrationSecondTest() {
    TextUITester test = new TextUITester("1\n2\nmemorial_library\nunion_south\n2"
        + "\nmemorial_library\nunion_south\nQ\n");
    Scanner input = new Scanner(System.in);
    MadisonAcademicBuildingsReader reader = new MadisonAcademicBuildingsReaderDW();
    DijkstraInterface<MadisonAcademicBuildings,Number> graph = new DijkstraAR<>();
    MadisonBuildingMapBackendInterface backend = new MadisonBuildingMapBackendBD(graph,reader);
    UserInterfaceInterface app = new UserInterfaceFD(input, backend);
    app.runCommandLoop();
    String output = test.checkOutput();
    String expected = "The shortest path is: memorial library -> engineering hall -> union south";
    String expectedResponse = "Travel Time: 18 minutes";
    assertTrue(output.contains(expected), "Incorrect user prompt");
    assertTrue(output.contains(expectedResponse), "Incorrect user prompt");
  }
  
  
}
