package manager;

import models.Planet;

import java.util.List;
import java.util.Scanner;

public class ApplicationManager {
    private final FileManager fileManager;
    private final StarWarsUniverse starWars;

    public ApplicationManager(FileManager fileManager,StarWarsUniverse starWars){
        this.fileManager = fileManager;
        this.starWars = starWars;
    }

    public void startProgram(Scanner scanner){
        List<Planet> planets = starWars.getPlanets();
        String inputData;

        while (true) {
            System.out.print("> ");
            inputData = scanner.nextLine().trim();

            if (inputData.equalsIgnoreCase("exit")) {
                System.out.println("> Exiting the program...");
                break;
            }

            if (inputData.isEmpty()) {
                continue;
            }

            String[] data = inputData.split(" ");
            String action = data[0];

            try {
                if (action.equalsIgnoreCase("help")) {
                    fileManager.help();
                } else if  (action.equalsIgnoreCase("add_planet") && data.length == 2) {
                    String planetName = data[1];
                    starWars.addPlanet(planetName);
                }
                else if (action.equalsIgnoreCase("create_jedi") && data.length == 7) {
                    String planetName = data[1];
                    String jediName = data[2];
                    String rank = data[3];
                    int age = Integer.parseInt(data[4]);
                    String lightsaber = data[5];
                    double strength = Double.parseDouble(data[6]);

                    starWars.createJedi(
                            planetName,jediName, rank,age, lightsaber, strength
                    );
                }
                else if (action.equalsIgnoreCase("remove_jedi") && data.length == 3) {
                    String jediName = data[1];
                    String planetName = data[2];
                    starWars.removeJedi(jediName, planetName);
                }
                else if (action.equalsIgnoreCase("promote_jedi") && data.length == 3) {
                    String jediName = data[1];
                    double multiplier = Double.parseDouble(data[2]);
                    starWars.promoteJedi(jediName, multiplier);
                }
                else if (action.equalsIgnoreCase("demote_jedi") && data.length == 3) {
                    String jediName = data[1];
                    double multiplier = Double.parseDouble(data[2]);
                    starWars.demoteJedi(jediName, multiplier);
                }
                else if (action.equalsIgnoreCase("get_strongest_jedi") && data.length == 2) {
                    String planetName = data[1];
                    starWars.getStrongestJedi(planetName);
                }
                else if (action.equalsIgnoreCase("get_youngest_jedi") && data.length == 3) {
                    String planetName = data[1];
                    String rank = data[2];
                    starWars.getYoungestJedi(planetName, rank);
                }
                else if (action.equalsIgnoreCase("get_most_used_saber_color") && data.length == 3) {
                    String planetName = data[1];
                    String rank = data[2];
                    starWars.getMostUsedSaberColor(planetName, rank);
                }
                else if (action.equalsIgnoreCase("get_most_used_saber_color") && data.length == 2) {
                    String planetName = data[1];
                    starWars.getMostUsedSaberColor(planetName);
                }
                else if (action.equalsIgnoreCase("print_jedi") && data.length == 2) {
                    String jediName = data[1];
                    starWars.printJedi(jediName);
                }
                else if (action.equalsIgnoreCase("print_planet") && data.length == 2) {
                    String planetName = data[1];
                    starWars.printPlanet(planetName);
                }
                else if (action.equalsIgnoreCase("print_two_planets") && data.length == 3) {
                    String planet1 = data[1];
                    String planet2 = data[2];
                    starWars.printTwoPlanets(planet1, planet2);
                }
                else if (action.equalsIgnoreCase("open") && data.length == 2) {
                    String filename = data[1];
                    fileManager.open(filename);
                    List<Planet> loadedPlanets = fileManager.loadPlanets();
                    planets.clear();
                    planets.addAll(loadedPlanets);
                }
                else if (action.equalsIgnoreCase("close")) {
                    fileManager.close();
                }
                else if (action.equalsIgnoreCase("save")) {
                    fileManager.save(planets);
                }
                else if (action.equalsIgnoreCase("saveas") && data.length == 2) {
                    String filename = data[1].replace("\"", "");
                    fileManager.saveAs(filename, planets);
                }
                else {
                    System.out.println("Invalid command or wrong number of parameters.");
                }
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
    }

}
