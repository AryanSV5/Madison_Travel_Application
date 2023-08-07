import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MadisonAcademicBuildingsReaderDW implements MadisonAcademicBuildingsReader {

	@Override
    /**
     * This method reads in a dot file containing a list of academic buildings and their connections and returns a list of
     * MadisonAcademicBuildings, where each building has its own list of possible outgoing edges.
     *
     * @param filename - the name of the file containing the academic buildings and their connections
     * @return a list of MadisonAcademicBuildings, with each building having its own list of outgoing edges
     * @throws FileNotFoundException if the file cannot be found
     */
	public List<MadisonAcademicBuildings> readBuildingsFromFile(String filename) throws FileNotFoundException {
	    // initialize an empty list of buildings, this will be returned with all buildings and their edges
	    List<MadisonAcademicBuildings> buildings = new ArrayList<>();

	    // initialize a hashmap to store building names and their corresponding objects
	    HashMap<String, MadisonAcademicBuildings> buildingMap = new HashMap<>();

	    // read in the dot file
	    Scanner scnr = new Scanner(new File(filename));
	    while (scnr.hasNextLine()) {
	        String line = scnr.nextLine().trim();

	        // ignore any line that is not an edge definition
	        if (!line.contains("->")) {
	            continue;
	        }

	        // extract the names of the buildings and their edge weight
	        String[] parts = line.split("\\[|\\]|\\s*->\\s*|\\s*;");
	        String predecessorName = parts[0].trim();
	        String successorName = parts[1].trim();
	        double weight = Double.parseDouble(parts[2].substring(7).trim());

	        // check if building1 already exists, if not, create a new building object and add it to the list and hashmap
	        MadisonAcademicBuildings sourceBuilding = buildingMap.get(predecessorName);
	        if (sourceBuilding == null) {
	            List<MadisonAcademicBuildingsNetwork> edges = new ArrayList<MadisonAcademicBuildingsNetwork>();
	            sourceBuilding = new MadisonAcademicBuildingsDW(predecessorName, edges);
	            buildings.add(sourceBuilding);
	            buildingMap.put(predecessorName, sourceBuilding);
	        }

	        // check if building2 already exists, if not, create a new building object and add it to the list and hashmap
	        MadisonAcademicBuildings destinationBuilding = buildingMap.get(successorName);
	        if (destinationBuilding == null) {
	            List<MadisonAcademicBuildingsNetwork> edges = new ArrayList<MadisonAcademicBuildingsNetwork>();
	            destinationBuilding = new MadisonAcademicBuildingsDW(successorName, edges);
	            buildings.add(destinationBuilding);
	            buildingMap.put(successorName, destinationBuilding);
	        }

	        MadisonAcademicBuildingsNetwork edge = new MadisonAcademicBuildingsNetworkDW(weight, sourceBuilding, destinationBuilding);
	        // add the edge to building1's list of outgoing edges
	        sourceBuilding.addEdge(edge);
	    }
	    scnr.close();
	    return buildings;
	}
}






