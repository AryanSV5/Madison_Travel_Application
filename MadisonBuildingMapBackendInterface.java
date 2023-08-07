import java.util.List;

public interface MadisonBuildingMapBackendInterface {

    public void loadData(String filename);
    public List<String> getShortestPath (String start, String end);
    public double getShortestPathLength(String start, String end);
    public boolean existsPath(String start, String end);
    public List<String[]> graphToString(List<String> buildings);

}
