import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

public class FrontendDeveloperTest {
    private MadisonBuildingMapBackendInterface backend;
    private Scanner in;
    private UserInterfaceFD UI;

    @BeforeEach
    public void setup(){
        backend = new MadisonBuildingMapBackendFD();
        in = new Scanner(System.in);
    }
    @Test
    /**
     * test the commandLoop
     */
    public void test1() {
        TextUITester test = new TextUITester("q");
        Scanner in = new Scanner(System.in);
        UI = new UserInterfaceFD(in, backend);
        UI.runCommandLoop();
        in.close();
        String output = test.checkOutput();

        assertTrue(output.contains("Thank you for using UW-Madison Map Search App"));
    }

    @Test
    /**
     * This tests that the method searchShortestPath works as intended,
     */
    public void test2() {
        TextUITester test = new TextUITester("1\n2\na\nb\nq");
        Scanner in = new Scanner(System.in);
        UI = new UserInterfaceFD(in, backend);
        UI.runCommandLoop();
        in.close();
        String output = test.checkOutput();

        assertTrue(output.contains("Van Vleck -> Memorial Union"));
    }

    @Test
    /**
     * This tests that the method averageTravelTime
     */
    public void test3() {
        TextUITester test = new TextUITester("2\na\nb\nq");
        Scanner in = new Scanner(System.in);
        UI = new UserInterfaceFD(in, backend);
        UI.runCommandLoop();
        in.close();
        String output = test.checkOutput();
        assertTrue(output.contains("Travel Time: 10 minutes"));
    }

    @Test
    /**
     * This tests that the method checkPath works as intended,
     */
    public void test4() {
        TextUITester test = new TextUITester("3\na\nb\nq");
        Scanner in = new Scanner(System.in);
        UI = new UserInterfaceFD(in, backend);
        UI.runCommandLoop();
        in.close();
        String output = test.checkOutput();
        assertTrue(output.contains("No path exist"));
    }

    @Test
    /**
     * This tests that the method graphToString
     */
    public void test5() {
        TextUITester test = new TextUITester("4\na\nb\nq");
        Scanner in = new Scanner(System.in);
        UI = new UserInterfaceFD(in, backend);
        UI.runCommandLoop();
        in.close();
        String output = test.checkOutput();
        assertTrue(output.contains("Memorial Union has no adjacent buildings"));
    }

}
