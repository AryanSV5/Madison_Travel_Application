import java.util.Scanner;

public class UWMadisonMapSearchApp {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    MadisonAcademicBuildingsReader reader = new MadisonAcademicBuildingsReaderDW();
    DijkstraInterface<MadisonAcademicBuildings,Number> graph = new DijkstraAR<>();
    MadisonBuildingMapBackendInterface backend = new MadisonBuildingMapBackendBD(graph,reader);
    UserInterfaceInterface app = new UserInterfaceFD(input, backend);
    app.runCommandLoop();
    }
}
