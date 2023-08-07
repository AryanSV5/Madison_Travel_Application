
public class MadisonAcademicBuildingsNetworkDW implements MadisonAcademicBuildingsNetwork {
	
	private double weight;
	private MadisonAcademicBuildings predecessor;
	private MadisonAcademicBuildings successor;
	
	
	/**
	 * This is a constructor for the MadisonAcademicBuildingsNetworkDW class
	 * @param weight - the cost of this path
	 * @param predecessor - the first building/node
	 * @param successor - the second building/node
	 */
	public MadisonAcademicBuildingsNetworkDW(double weight, MadisonAcademicBuildings predecessor, MadisonAcademicBuildings successor) {
		this.weight = weight;
		this.predecessor = predecessor;
		this.successor = successor;
	}
	
	
	@Override
	/**
	 * This returns the predecessor or first building of this edge
	 */
	public MadisonAcademicBuildings getBuilding1() {
		return this.predecessor;
	}

	@Override
	/**
	 * This returns the successor or second building of this edge
	 */
	public MadisonAcademicBuildings getBuilding2() {
		return this.successor;
	}
	
	/**
	 * This returns the weight/cost of this path
	 */
	@Override
	public double getWeight() {
		return this.weight;
	}
	
	/**
	 * This sets the predecessor field
	 * @param predecessor - the new building to be used as a predecessor
	 */
	@Override
	public void setBuilding1(MadisonAcademicBuildings predecessor) {
		this.predecessor = predecessor;
		
	}
	
	
	@Override
	/**
	 * This sets the successor field
	 * @param successor - the new building to be used as a successor
	 */
	public void setBuilding2(MadisonAcademicBuildings successor) {
		this.successor = successor;
		
	}
	
	@Override
	/**
	 * This sets the weight of the edge
	 * @param weight - the new weight to be used as the cost
	 */
	public void setWeight(double weight) {
		this.weight = weight;
		
	}

}
