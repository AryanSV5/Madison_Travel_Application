import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class MadisonBuildingMapBackendFD implements MadisonBuildingMapBackendInterface{

    @Override
    public void loadData(String filename) {

    }

    @Override
    public List<String> getShortestPath(String start, String end) {
        ArrayList<String> r = new ArrayList<>();
        if(end.equals("2")){
            throw new NoSuchElementException();
        }
        r.add("Van Vleck");
        r.add("Memorial Union");
        r.add("College Library");
        return r;
    }

    @Override
    public double getShortestPathLength(String start, String end) {
        return 0.5;
    }

    @Override
    public boolean existsPath(String start, String end) {
        return false;
    }

    @Override
    public List<String[]> graphToString(List<String> buildings) {
        ArrayList<String[]> ret = new ArrayList<>();
        ret.add(new String[]{"Van Vleck","Chamberlin Hall","Sterling Hall"});
        ret.add(new String[]{});
        ret.add(new String[]{"Memorial Union","College Library","Humanities","Red Gym"});

        return ret;
    }
}
