import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface MadisonAcademicBuildingsReader {
	// public MadisonAcademicBuildingsReader();
public List<MadisonAcademicBuildings> readBuildingsFromFile (String filename) throws FileNotFoundException;
}

