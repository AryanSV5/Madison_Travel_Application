
public interface MadisonAcademicBuildingsNetwork {
	//public MadisonAcademicBuildingNetwork(MadisonAcademicBuildings building1, MadisonAcademicBuildings building2, double weight)
	public MadisonAcademicBuildings getBuilding1();
	public MadisonAcademicBuildings getBuilding2();
	public double getWeight();
	public void setBuilding1(MadisonAcademicBuildings predecessor);
	public void setBuilding2(MadisonAcademicBuildings successor);
	public void setWeight(double weight);
}
