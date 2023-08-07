import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * User Interface for the application
 *
 * @author Vincent Philavong
 */
public class UserInterfaceFD implements UserInterfaceInterface {

    // var fields
    private final Scanner userInput;
    private final MadisonBuildingMapBackendInterface backend;
    private final DateTimeFormatter dtf;

    /**
     * constructor for the userInterface
     *
     * @param userInput defines the inputs for the Scanner
     * @param backend   defines the backend instance
     */
    public UserInterfaceFD(Scanner userInput, MadisonBuildingMapBackendInterface backend) {
        this.userInput = userInput;
        this.dtf = DateTimeFormatter.ofPattern("HH:mm");
        this.backend = backend;
    }

    /**
     * header method helper
     */
    protected void headerHelper(boolean x) {
        if (x) {
            System.out.println("*******************************************************************************");
            System.out.println("                            UW-Madison Map Search App");
            System.out.println("*******************************************************************************");
        } else {
            System.out.println("*******************************************************************************");
            System.out.println("                    Thank you for using UW-Madison Map Search App");
            System.out.println("*******************************************************************************");
        }
    }

    @Override
    public void runCommandLoop() {
        boolean restart;
        boolean inputMismatch;
        int numLocation = 0;
        String firstBuilding;
        String secondBuilding;
        String thirdBuilding;
        String result;
        String command;
        backend.loadData("MadisonAcademicBuildings.dot");
        headerHelper(true);
        do {
            inputMismatch = false;
            menuPrompt();
            command = userInput.next().toLowerCase();
            switch (command) {
                case "1":
                    String str;
                    boolean quit = false;
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Return to main menu enter: Q");
                    //Check number of locations
                    do {
                        restart = false;
                        System.out.print("Choose between 2-3 locations: ");
                        try {
                            str = userInput.next();
                            if (str.equalsIgnoreCase("q")) {
                                quit = true;
                                break;
                            }
                            numLocation = Integer.parseInt(str);
                            if (numLocation != 2 && numLocation != 3) {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            // if there is no firstBuilding or secondBuilding
                            System.out.println("\nInvalid input\n");
                            restart = true;
                        }
                    } while (restart);
                    if (quit) {
                        System.out.println("-------------------------------------------------------------------------------");
                        break;
                    }
                    do {
                        restart = false;
                        try {
                            // asks user for inputs
                            System.out.print("Input start location: ");
                            firstBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (firstBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            System.out.print("Input end location: ");
                            secondBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (secondBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            if (numLocation == 3) {
                                System.out.print("Input middle-point location: ");
                                thirdBuilding = userInput.next();
                                result = getShortestPath(firstBuilding, secondBuilding, thirdBuilding);
                            } else {
                                result = getShortestPath(firstBuilding, secondBuilding);
                            }
                            System.out.println("The shortest path is: " + result);
                        } catch (NoSuchElementException e) {
                            // if there is no firstBuilding or secondBuilding
                            System.out.println("\nDirections unavailable, enter valid location\n");
                            restart = true;
                        }
                    } while (restart);
                    System.out.println("-------------------------------------------------------------------------------");
                    break;
                case "2":
                    System.out.println("-------------------------------------------------------------------------------");
                    do {
                        restart = false;
                        try {
                            // ask user for inputs
                            System.out.println("Return to main menu enter: Q");
                            System.out.print("Input start location: ");
                            firstBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (firstBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            System.out.print("Input end location: ");
                            secondBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (secondBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            // gets the ETA and Travel time
                            result = calculateTravelTime(firstBuilding, secondBuilding);
                            System.out.println("\n" + result);
                        } catch (NoSuchElementException e) {
                            // if there is no firstBuilding or SecondBuilding
                            System.out.println("\nDirections unavailable, enter valid location\n");
                            restart = true;
                        }
                    } while (restart);
                    System.out.println("-------------------------------------------------------------------------------");
                    break;
                case "3":
                    System.out.println("-------------------------------------------------------------------------------");
                    do {
                        restart = false;
                        try {
                            // ask user for inputs
                            System.out.println("Return to main menu enter: Q");
                            System.out.print("Input start location: ");
                            firstBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (firstBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            System.out.print("Input end location: ");
                            secondBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (secondBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            // gets the adjacent buildings
                            result = checkPath(firstBuilding, secondBuilding);
                            System.out.println("\n" + result);
                        } catch (NoSuchElementException e) {
                            // if there is no firstBuilding or SecondBuilding
                            System.out.println("\nDirections unavailable, enter valid location\n");
                            restart = true;
                        }
                    } while (restart);
                    System.out.println("-------------------------------------------------------------------------------");
                    break;
                case "4":
                    do {
                        restart = false;
                        try {
                            // asks user for inputs
                            System.out.print("Input start location: ");
                            firstBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (firstBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            System.out.print("Input end location: ");
                            secondBuilding = userInput.next();
                            // quits if user wants to go back to menu
                            if (secondBuilding.equalsIgnoreCase("q")) {
                                break;
                            }
                            result = getPossiblePaths(firstBuilding, secondBuilding);
                            System.out.println("\n" + result);
                        } catch (NoSuchElementException e) {
                            // if there is no firstBuilding or secondBuilding
                            System.out.println("\nDirections unavailable, enter valid location\n");
                            restart = true;
                        }
                    } while (restart);
                    System.out.println("-------------------------------------------------------------------------------");
                    break;
                case "q":
                    break;
                default:
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("                            Enter Valid Input\n");
                    inputMismatch = true;
                    break;
            }
        } while (!command.equals("q") || inputMismatch);

        headerHelper(false);
    }

    @Override
    public void menuPrompt() {
        System.out.println("Choose an option from the list below: ");
        System.out.println("    1: Find shortest path");
        System.out.println("    2: Calculate travel time");
        System.out.println("    3: Check if path exist");
        System.out.println("    4: Get possible paths");
        System.out.println("    Q: Quit");
        System.out.print("Choose a command: ");
    }

    @Override
    public String getShortestPath(String FirstBuilding, String SecondBuilding) {
        StringBuilder r = new StringBuilder();
        boolean first = true;
        for (String s : backend.getShortestPath(FirstBuilding, SecondBuilding)) {
            if (first) {
                r.append(s);
                first = false;
            } else {
                r.append(" -> ").append(s);
            }
        }
        return r.toString();
    }

    @Override
    public String getShortestPath(String FirstBuilding, String SecondBuilding, String mustBe) {
        StringBuilder r = new StringBuilder();
        boolean first = true;
        boolean skipFirst = true;
        for (String s : backend.getShortestPath(FirstBuilding, mustBe)) {
            if (first) {
                r.append(s);
                first = false;
            } else {
                r.append(" -> ").append(s);
            }
        }
        for (String s : backend.getShortestPath(mustBe, SecondBuilding)) {
            if (!skipFirst) {
                r.append(" -> ").append(s);
            }
            skipFirst = false;
        }
        return r.toString();
    }

    @Override
    public String checkPath(String FirstBuilding, String SecondBuilding) {
        if (backend.existsPath(FirstBuilding, SecondBuilding)) {
            return "Path exist";
        }
        return "No path exist";
    }

    @Override
    public String calculateTravelTime(String FirstBuilding, String SecondBuilding) {
        double miles = backend.getShortestPathLength(FirstBuilding, SecondBuilding);
        long travelTime = (long) (miles / 3.0 * 60);
        LocalTime time = LocalTime.now();
        time = time.plusMinutes(travelTime);
        return "ETA: " + dtf.format(time) + "\nTravel Time: " + travelTime + " minutes" + "\nMiles: " + miles;
    }

    @Override
    public String getPossiblePaths(String FirstBuilding, String SecondBuilding) {
        ArrayList<String> list = (ArrayList<String>) backend.getShortestPath(FirstBuilding, SecondBuilding);
        StringBuilder r = new StringBuilder();
        int index = 0;

        for (String[] s : backend.graphToString(list)) {
            if (s.length == 0) {
                r.append(list.get(index)).append(" has no adjacent buildings");
            } else {
                r.append(list.get(index)).append("'s adjacent building: ");
                for (int i = 0; i < s.length; i++) {
                    r.append(s[i]);
                    if (i < s.length - 1)
                        r.append(", ");
                }
            }
            index++;
            if (index < list.size())
                r.append("\n\t\t\t\t|\n\t\t\t\tV\n");
        }
        return r.toString();
    }

    public static void main(String[] args) {
        UserInterfaceFD test = new UserInterfaceFD(new Scanner(System.in), new MadisonBuildingMapBackendFD());
        test.runCommandLoop();
    }
}
