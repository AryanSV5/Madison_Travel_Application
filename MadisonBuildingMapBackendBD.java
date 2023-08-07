import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Iterator;
public class MadisonBuildingMapBackendBD implements MadisonBuildingMapBackendInterface {

  DijkstraInterface<MadisonAcademicBuildings, Number> graph;
  MadisonAcademicBuildingsReader reader;
  /**
   * 
   * Backend Constructor
   * 
   * @param graph
   * 
   * @param reader
   * 
   */
  public MadisonBuildingMapBackendBD(DijkstraInterface<MadisonAcademicBuildings, Number> graph,
      MadisonAcademicBuildingsReader reader) {
    this.graph = graph;
    this.reader = reader;
  }
  /**
   * 
   * Method to load data to the graph from a file containing graph data
   * 
   * @param filename
   * 
   */

  public void loadData(String filename) {
    try {
      // get the list of nodes from the using the reader
      List<MadisonAcademicBuildings> buildings = reader.readBuildingsFromFile(filename);
      // loop through all the nodes and add them to the graph
      for (MadisonAcademicBuildings building : buildings) {
        graph.insertNode(building);
        // each node contains a reference to all edges leaving the node. Loop throug
        // and add them to the graph
        for (MadisonAcademicBuildingsNetwork edge : building.getEdges()) {
          MadisonAcademicBuildings successor = edge.getBuilding2();
          graph.insertNode(successor);
          graph.insertEdge(building, successor, edge.getWeight());

        }

      }

    } catch (FileNotFoundException e) {

      // print stack trace if the file is not found
      throw new RuntimeException("No Such File");

    }

  }

  /**
   * 
   * Method to get the shortest path between two nodes.
   * 
   * @param start
   * 
   * @param end
   * 
   * @return names of buildings along shortest path
   * 
   */
  public List<String> getShortestPath(String start, String end) {

    // the node type in the graph is MadisonAcademicBuildingsInterface

    // get each of the objects in the graph which have the given names

    MadisonAcademicBuildings startBuilding = getNodeByName(start);

    MadisonAcademicBuildings endBuilding = getNodeByName(end);
    // call the Dijkstra method to get a list of nodes along the shortest path

    List<MadisonAcademicBuildings> pathNodes =
        graph.shortestBuildingPathData(startBuilding, endBuilding);
    // convert the list of MadisonAcademicBuildingsInterface objecst to a list of strings
    // with the building names
    List<String> pathNames = nodesToStrings(pathNodes);
    return pathNames;
  }



  /**
   * 
   * Get the length of the shortest path betwen two nodes.
   * 
   * @param start
   * 
   * @param end
   * 
   * @return shortest path length
   * 
   */

  public double getShortestPathLength(String start, String end) {
    // the node type in the graph is MadisonAcademicBuildingsInterface
    // get each of the objects in the graph which have the given names
    MadisonAcademicBuildings startBuilding = getNodeByName(start);
    MadisonAcademicBuildings endBuilding = getNodeByName(end);
    // return the path cost by calling the Dijkstra shortestPathCost
    return graph.shortestPathCost(startBuilding, endBuilding);
  }



  /**
   * 
   * Check if a path exists between two noes
   * 
   * @param start
   * 
   * @param end
   * 
   * @return true if a path exists and false if it does not
   * 
   */

  public boolean existsPath(String start, String end) {
    // the node type in the graph is MadisonAcademicBuildingsInterface
    // get each of the objects in the graph which have the given names
    MadisonAcademicBuildings startBuilding = getNodeByName(start);
    MadisonAcademicBuildings endBuilding = getNodeByName(end);
    // call the shortestPath cost method. if it does not throw an exception
    // then the shortest path exits. If it does then it does not exist.
    try {

      double pathLength = graph.shortestPathCost(startBuilding, endBuilding);
      return true;
    } catch (NoSuchElementException e) {
      return false;

    }
  }



  /**
   * 
   * Given a list of buidings, this method returns a list of string arrays. Each of the
   * 
   * arrays is of length three and contains the information for one edge leaving one of the
   * 
   * nodes passed to the method. Array[0] is the predecessor node (given in the list passed to the
   * method)
   * 
   * Array[1] is the edge weight and Array[2] is the successor node or neighbor.
   * 
   * @param list of building names
   * 
   * @return list of al edges leaving each building and their weights
   * 
   */

  public List<String[]> graphToString(List<String> buildings) {
    // get a list of all the nodes for each name in buildings
    List<MadisonAcademicBuildings> buildingNodes = new ArrayList<MadisonAcademicBuildings>();
    for (String building : buildings) {
      MadisonAcademicBuildings buildingNode = getNodeByName(building);
      buildingNodes.add(buildingNode);
    }
    // initialize array list to be returned
    List<String[]> adjacentBuildings = new ArrayList<>();

    // loop through each building
    for (MadisonAcademicBuildings buildingNode : buildingNodes) {
      // loop through all the edges, put them in an array, and add them to the list
      for (MadisonAcademicBuildingsNetwork edge : buildingNode.getEdges()) {
        String pred = edge.getBuilding1().getBuildingName();
        String edgeWeight = ((Double) edge.getWeight()).toString();
        String suc = edge.getBuilding2().getBuildingName();
        String[] edgeData = {pred, edgeWeight, suc};
        adjacentBuildings.add(edgeData);
      }
    }
    return adjacentBuildings;

  }
  // ********* Helper Methods **********//


  /**
   * 
   * helper method to find a node in the graph given a String with its name.
   * 
   * @param name
   * 
   * @return MadisonAcademicBuilding building
   * 
   * @throws NoSuchElementException
   * 
   */
  private MadisonAcademicBuildings getNodeByName(String name) throws NoSuchElementException {
    MadisonAcademicBuildings building;
    Iterator<MadisonAcademicBuildings> iterator = graph.iterator();

    String target = name.replaceAll("_", " ").trim();

    // use the Dijkstra iterator method to iterate over the graph to find the node
    while (iterator.hasNext()) {
      building = iterator.next();
      String graphName = building.getBuildingName().replaceAll("_", " ");
      if (graphName.equalsIgnoreCase(target)) {
        return building;
      }
    }
    // if the building is not found throw an exception
    throw new NoSuchElementException("Building not found: " + name);
  }
  /**
   * 
   * Given a list of building nodes in the graph, return a list of strings with their names
   * 
   * @param buildingNodes
   * 
   * @return List of strings of names
   * 
   */
  private List<String> nodesToStrings(List<MadisonAcademicBuildings> buildingNodes) {
    List<String> buildingStrings = new ArrayList<String>();
    for (int i = 0; i < buildingNodes.size(); i++) {
      buildingStrings.add(buildingNodes.get(i).getBuildingName().replaceAll("_", " "));
    }
    return buildingStrings;
  }
}
