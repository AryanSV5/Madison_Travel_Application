import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataWranglerTests {
	private MadisonAcademicBuildingsReaderDW test;
	private MadisonAcademicBuildingsDW building;
	private MadisonAcademicBuildingsNetworkDW edge;
	
	@BeforeEach 
	public void setup() {
		test = new MadisonAcademicBuildingsReaderDW();
		
	}
	
	@Test
	/**
	 * Tests that MadisonAcademicBuildings.dot can be read in and loads correct number of buildings
	 */
	public void test1() {
		try {
			List<MadisonAcademicBuildings> list = test.readBuildingsFromFile("MadisonAcademicBuildings.dot");
			
			assertEquals(10, list.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	/**
	 * Tests setters and getters for MadisonAcademicBuildings class
	 */
	public void test2() {
		edge = new MadisonAcademicBuildingsNetworkDW(1.2, building, null);
		List<MadisonAcademicBuildingsNetwork> outgoingEdges = new ArrayList<MadisonAcademicBuildingsNetwork> ();
		outgoingEdges.add(edge);
		building = new MadisonAcademicBuildingsDW("van_vleck", outgoingEdges);
		building.setBuildingName("humanities");
		assertEquals(building.getBuildingName(), "humanities");
		assertEquals(building.getEdges(), outgoingEdges);
	}
	
	@Test
	/**
	 * tests getEdges, making sure it returns correct number of outgoing edges for a node and properly adds 
	 * edge to a building's edges list
	*/
	public void test3() {
		List<MadisonAcademicBuildingsNetwork> edges = new ArrayList<MadisonAcademicBuildingsNetwork> ();
		MadisonAcademicBuildings building2 = new MadisonAcademicBuildingsDW("van_vleck", edges);
		MadisonAcademicBuildingsNetwork edge1 = new MadisonAcademicBuildingsNetworkDW(1.2, building, building2);
		building2.addEdge(edge1);
		MadisonAcademicBuildingsNetwork edge2 = new MadisonAcademicBuildingsNetworkDW(1.2, building2, building);
		building2.addEdge(edge2);
		assertEquals(2, building2.getEdges().size() );
	}
	 
	
	@Test
	/**
	 * This tests setters/getters for the edge class
	 */
	public void test4() {
		List<MadisonAcademicBuildingsNetwork> edges = new ArrayList<MadisonAcademicBuildingsNetwork> ();
		MadisonAcademicBuildings building2 = new MadisonAcademicBuildingsDW("van_vleck", edges);
		edge = new MadisonAcademicBuildingsNetworkDW(1.2, building, building2);
		assertEquals(edge.getBuilding1(), building);
		assertEquals(edge.getBuilding2(), building2);	
	}
	
	@Test
	/**
	 * Tests weights of edges, making sure they are accurate towards the created dot file
	 */
	public void test5() {
		try {
			List<MadisonAcademicBuildings> list = test.readBuildingsFromFile("MadisonAcademicBuildings.dot");
			List<MadisonAcademicBuildingsNetwork> edgeList = list.get(0).getEdges();
			System.out.println(edgeList.get(0).getWeight());
			assertEquals(edgeList.get(0).getWeight(), 0.2, 0.2);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
