import java.util.List;

public interface MadisonAcademicBuildings {
	//public MadisonAcademicBuildings(String name)
	public void setBuildingName(String newName);
	public String getBuildingName();
	public List <MadisonAcademicBuildingsNetwork> getEdges();
	public void addEdge(MadisonAcademicBuildingsNetwork edge);
}
