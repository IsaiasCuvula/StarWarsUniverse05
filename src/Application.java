import manager.StarWarsUniverse;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        Scanner scanner = new Scanner(System.in);
        String line;

        starWarsUniverse.printMenu();

        while (true) {
            System.out.print("> ");
            line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            String[] data = line.split(" ");
            String action = data[0];

            try {
                if (action.equalsIgnoreCase("add_planet") && data.length == 2) {
                    String planetName = data[1];
                    starWarsUniverse.addPlanet(planetName);
                }
                else if (action.equalsIgnoreCase("create_jedi") && data.length == 7) {
                    String planetName = data[1];
                    String jediName = data[2];
                    String rank = data[3];
                    int age = Integer.parseInt(data[4]);
                    String lightsaber = data[5];
                    double strength = Double.parseDouble(data[6]);

                    starWarsUniverse.createJedi(
                            planetName,jediName, rank,age, lightsaber, strength
                    );
                }
                else if (action.equalsIgnoreCase("remove_jedi") && data.length == 3) {
                    String jediName = data[1];
                    String planetName = data[2];
                    starWarsUniverse.removeJedi(jediName, planetName);
                }
                else if (action.equalsIgnoreCase("promote_jedi") && data.length == 3) {
                    String jediName = data[1];
                    double multiplier = Double.parseDouble(data[2]);
                    starWarsUniverse.promoteJedi(jediName, multiplier);
                }
                else if (action.equalsIgnoreCase("demote_jedi") && data.length == 3) {
                    String jediName = data[1];
                    double multiplier = Double.parseDouble(data[2]);
                    starWarsUniverse.demoteJedi(jediName, multiplier);
                }
                else if (action.equalsIgnoreCase("get_strongest_jedi") && data.length == 2) {
                    String planetName = data[1];
                    starWarsUniverse.getStrongestJedi(planetName);
                }
                else if (action.equalsIgnoreCase("get_youngest_jedi") && data.length == 3) {
                    String planetName = data[1];
                    String rank = data[2];
                    starWarsUniverse.getYoungestJedi(planetName, rank);
                }
                else if (action.equalsIgnoreCase("get_most_used_saber_color") && data.length == 3) {
                    String planetName = data[1];
                    String rank = data[2];
                    starWarsUniverse.getMostUsedSaberColor(planetName, rank);
                }
                else if (action.equalsIgnoreCase("get_most_used_saber_color") && data.length == 2) {
                    String planetName = data[1];
                    starWarsUniverse.getMostUsedSaberColor(planetName);
                }
                else if (action.equalsIgnoreCase("print_jedi") && data.length == 2) {
                    String jediName = data[1];
                    starWarsUniverse.printJedi(jediName);
                }
                else if (action.equalsIgnoreCase("print_planet") && data.length == 2) {
                    String planetName = data[1];
                    starWarsUniverse.printPlanet(planetName);
                }
                else if (action.equalsIgnoreCase("print_two_planets") && data.length == 3) {
                    String planet1 = data[1];
                    String planet2 = data[2];
                    starWarsUniverse.printTwoPlanets(planet1, planet2);
                }
                else {
                    System.out.println("Invalid command or wrong number of parameters.");
                }
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("> Program closed.");
    }
}
