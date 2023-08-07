import java.util.List;

public class MadisonAcademicBuildingsDW implements MadisonAcademicBuildings {
	
	private String name;
	private List<MadisonAcademicBuildingsNetwork> edges;
	
	public MadisonAcademicBuildingsDW(String name, List<MadisonAcademicBuildingsNetwork> edges) {
		this.name = name;
		this.edges = edges;
	}
	
	@Override
	/**
	 * This sets this building to newName
	 * @param newName - the name of this node/building
	 */
	public void setBuildingName(String newName) {
		this.name = newName;
	}

	@Override
	/**
	 * This returns the name of this building
	 */
	public String getBuildingName() {
		return this.name;
	}

	@Override
	/**
	 * This returns the list of possible edges/networks for this specific building
	 */
	public List<MadisonAcademicBuildingsNetwork> getEdges() {
		return this.edges;
	}
	
	/**
	 * This adds an edge for this building's list of edges
	 * @param edge - an edge that corresponds to possible paths from a given building
	 */
	@Override
	public void addEdge(MadisonAcademicBuildingsNetwork edge) {
		this.edges.add(edge);	
	}
	//@Override
	/**
	 * This returns the list of possible edges/networks for this specific building
	 
	public MadisonAcademicBuildingsNetwork getCheapestEdge(List<MadisonAcademicBuildingsNetwork> edges) {
		MadisonAcademicBuildingsNetwork cheapest = edges.get(0);
		for (int i = 0; i < edges.size(); i++) {
			if (cheapest.getWeight() < edges.get(i).getWeight()) {
				cheapest = edges.get(i);
			}
		}
	}*/

}
