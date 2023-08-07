public interface UserInterfaceInterface{
    // public UserInterfaceInterface(Scanner userInput, MadisonBuildingMapBackendInterface backend);
    public void runCommandLoop();
    public void menuPrompt();
    public String getShortestPath(String FirstBuilding, String SecondBuilding);
    public String getShortestPath(String FirstBuilding, String SecondBuilding, String mustBe);
    public String checkPath(String FirstBuilding, String SecondBuilding);
    public String calculateTravelTime(String FirstBuilding, String SecondBuilding);
    public String getPossiblePaths(String FirstBuilding, String SecondBuilding);


}
