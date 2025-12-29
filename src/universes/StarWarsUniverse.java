package universes;

import exceptions.JediException;
import exceptions.PlanetException;
import models.Jedi;
import models.Planet;
import models.Rank;

import java.util.*;

/**
 * Manages the entire Star Wars universe, including planets and their Jedi inhabitants.
 * This class is responsible for creating, searching, modifying and printing information
 * about planets and Jedi, as well as performing various statistical operations.
 */
public class StarWarsUniverse implements Universe {

    /** List of all planets currently stored in memory. */
    private List<Planet> planets = new ArrayList<>();

    /**
     * Returns the list of planets in the universe.
     *
     * @return list of planets
     */
    public List<Planet> getPlanets() {
        return this.planets;
    }

    /**
     * Replaces the current planet list with a new one.
     *
     * @param planets new planet list
     */
    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Prints lexicographically sorted Jedi from two planets, combined into one list.
     * If either planet does not exist, an error message is printed.
     *
     * @param planetName1 first planet
     * @param planetName2 second planet
     */
    @Override
    public void printTwoPlanets(String planetName1, String planetName2) {
        try {
            Planet p1 = getPlanetByName(planetName1);
            Planet p2 = getPlanetByName(planetName2);
            System.out.println(p1.getName() + " + " + p2.getName());

            List<Jedi> allJedis = new ArrayList<>();
            allJedis.addAll(p1.getJediList());
            allJedis.addAll(p2.getJediList());

            // Sort alphabetically by name (case-insensitive)
            allJedis.sort(Comparator.comparing(Jedi::getName, String.CASE_INSENSITIVE_ORDER));

            // Print each Jedi
            for (Jedi jedi : allJedis) {
                System.out.println(jedi);
            }
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Prints detailed information about a specific Jedi, including the planet
     * he/she inhabits.
     *
     * @param jediName name of the Jedi to print
     */
    @Override
    public void printJedi(String jediName) {
        try {
            Jedi jedi = getJediByName(jediName);
            Planet jediPlanet = null;

            for (Planet planet : planets) {
                if (planet.getJediList().contains(jedi)) {
                    jediPlanet = planet;
                    break;
                }
            }

            System.out.println(jedi);
            if (jediPlanet != null) {
                System.out.println(" Inhabits planet: " + jediPlanet.getName());
            } else {
                System.out.println(" This Jedi does not inhabit any planet.");
            }
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Prints a planet name and its Jedi sorted by rank and name.
     *
     * @param planetName name of the planet to print
     */
    @Override
    public void printPlanet(String planetName) {
        try {
            Planet planet = getPlanetByName(planetName);
            System.out.println("Planet: " + planet.getName());

            List<Jedi> jedis = planet.getJediList();
            if (jedis.isEmpty()) {
                System.out.println("This planet has any Jedis yet...");
            } else {
                System.out.println("Jedis: ");
            }

            jedis.stream()
                    .sorted((j1, j2) -> {
                        int byRank = j1.getRank().compareTo(j2.getRank());
                        if (byRank != 0) return byRank;
                        return j1.getName().compareToIgnoreCase(j2.getName());
                    })
                    .forEach(j -> System.out.println(j.getName()));

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Determines and prints the most used lightsaber color on a planet
     * among Jedi whose color matches at least one GRAND_MASTER.
     *
     * @param planetName name of the planet
     */
    @Override
    public void getMostUsedSaberColor(String planetName) {
        try {
            Planet planet = getPlanetByName(planetName);
            List<Jedi> jedis = planet.getJediList();

            if (jedis.isEmpty()) {
                throw new PlanetException("This planet does not have any Jedi");
            }

            Set<String> gmColors = new HashSet<>();
            for (Jedi jedi : jedis) {
                if (jedi.getRank() == Rank.GRAND_MASTER) {
                    gmColors.add(jedi.getLightsaberColor());
                }
            }

            if (gmColors.isEmpty()) {
                System.out.println("No GRAND_MASTER exists on " + planetName);
                return;
            }

            Map<String, Integer> colorCounts = new HashMap<>();
            for (Jedi jedi : jedis) {
                String color = jedi.getLightsaberColor();
                if (gmColors.contains(color)) {
                    colorCounts.put(color, colorCounts.getOrDefault(color, 0) + 1);
                }
            }

            String mostUsedColor = null;
            int maxCount = 0;

            for (var entry : colorCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostUsedColor = entry.getKey();
                }
            }

            System.out.printf("Most used saber color on %s (used by at least one GRAND_MASTER) is: %s%n",
                    planetName, mostUsedColor);

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Determines and prints the most used lightsaber color among Jedi
     * of a specific rank on a planet.
     *
     * @param planetName name of the planet
     * @param jediRank rank filter
     */
    @Override
    public void getMostUsedSaberColor(String planetName, String jediRank) {
        try {
            Rank rank = Rank.fromString(jediRank);
            Planet savedPlanet = getPlanetByName(planetName);
            List<Jedi> jedis = savedPlanet.getJediList();

            if (jedis.isEmpty()) {
                throw new PlanetException("This Planet does not have any Jedi");
            }

            Map<String, Integer> colorCounts = new HashMap<>();
            for (Jedi jedi : jedis) {
                if (jedi.getRank().equals(rank)) {
                    String color = jedi.getLightsaberColor();
                    int counter = colorCounts.getOrDefault(color, 0) + 1;
                    colorCounts.put(color, counter);
                }
            }

            if (colorCounts.isEmpty()) {
                System.out.println("No Jedi found with rank: " + jediRank + " on " + planetName);
            }

            String mostUsedColor = null;
            int maxCount = 0;

            for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostUsedColor = entry.getKey();
                }
            }

            System.out.printf("For Rank %s the most used saber color is: %s , used %d times %n",
                    rank.name(), mostUsedColor, maxCount);

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Finds and prints the youngest Jedi of a given rank on a planet.
     *
     * @param planetName name of the planet
     * @param jediRank rank filter
     */
    @Override
    public void getYoungestJedi(String planetName, String jediRank) {
        try {
            Rank rank = Rank.fromString(jediRank);
            Planet savedPlanet = getPlanetByName(planetName);
            List<Jedi> jedis = savedPlanet.getJediList();

            if (jedis.isEmpty()) {
                throw new PlanetException("This Planet does not have any Jedi");
            }

            int minAge = Integer.MAX_VALUE;
            boolean foundRank = false;

            for (Jedi jedi : jedis) {
                if (jedi.getRank().equals(rank)) {
                    foundRank = true;
                    if (jedi.getAge() < minAge) {
                        minAge = jedi.getAge();
                    }
                }
            }

            if (!foundRank) {
                System.out.println("There is no Jedi with Rank " + jediRank);
                return;
            }

            List<Jedi> youngestJedis = new ArrayList<>();
            for (Jedi jedi : jedis) {
                if (jedi.getRank().equals(rank) && jedi.getAge() == minAge) {
                    youngestJedis.add(jedi);
                }
            }

            youngestJedis.sort(Comparator.comparing(Jedi::getName));
            System.out.println(youngestJedis.getFirst());

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Finds and prints the strongest Jedi on a planet.
     *
     * @param planetName name of the planet
     */
    @Override
    public void getStrongestJedi(String planetName) {
        try {
            Planet savedPlanet = getPlanetByName(planetName);
            List<Jedi> jedis = savedPlanet.getJediList();

            if (jedis.isEmpty()) {
                throw new PlanetException("This Planet does not have any Jedi");
            }

            Jedi strongestJedi = jedis.getFirst();

            for (Jedi jedi : jedis) {
                if (jedi.getStrength() > strongestJedi.getStrength()) {
                    strongestJedi = jedi;
                }
            }

            System.out.println(strongestJedi.toString());

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Demotes a Jedi by decreasing strength and lowering rank.
     *
     * @param jediName name of the Jedi
     * @param multiplier strength decrease percentage
     */
    @Override
    public void demoteJedi(String jediName, double multiplier) {
        try {
            if (multiplier < 0) {
                throw new PlanetException("Multiplier must positive number");
            }
            Jedi jedi = getJediByName(jediName);
            double jediStrength = jedi.getStrength();
            double newJediStrength = jediStrength - (jediStrength * multiplier);
            jedi.setStrength(newJediStrength);

            Rank nextRank = jedi.getRank().stepDownRank();
            jedi.setRank(nextRank.name());
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }

    }

    /**
     * Promotes a Jedi by increasing strength and raising rank.
     *
     * @param jediName name of the Jedi
     * @param multiplier strength increase percentage
     */
    @Override
    public void promoteJedi(String jediName, double multiplier) {
        try {
            if (multiplier < 0) {
                throw new PlanetException("Multiplier must positive number");
            }

            Jedi jedi = getJediByName(jediName);

            if (jedi.getRank() == Rank.GRAND_MASTER) {
                throw new JediException("Jedi is already at the highest rank (GRAND_MASTER)");
            }

            double jediStrength = jedi.getStrength();
            double newJediStrength = jediStrength + (jediStrength * multiplier);

            if (newJediStrength > 2) {
                newJediStrength = 2;
            }

            jedi.setStrength(newJediStrength);
            Rank nextRank = jedi.getRank().stepUpRank();
            jedi.setRank(nextRank.name());

            System.out.println("Jedi " + jediName + " got promoted successfully ...");

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Removes a Jedi from a planet.
     *
     * @param jediName name of the Jedi to remove
     * @param planetName planet to remove the Jedi from
     */
    @Override
    public void removeJedi(String jediName, String planetName) {
        try {
            Planet savedPlanet = getPlanetByName(planetName);
            savedPlanet.removeJedi(jediName);
            System.out.printf("Jedi %s removed successfully %n", jediName);
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Creates a Jedi and assigns it to a planet.
     *
     * @param planetName planet where the Jedi will be created
     * @param jediName name of the Jedi
     * @param jediRank rank of the Jedi
     * @param jediAge age of the Jedi
     * @param saberColor lightsaber color
     * @param jediStrength Jedi strength (1 to 2)
     */
    @Override
    public void createJedi(String planetName, String jediName,
                           String jediRank, int jediAge, String saberColor, double jediStrength) {
        try {
            Planet savedPlanet = getPlanetByName(planetName);
            Jedi savedJedi = tryToFindJedi(jediName);

            if (savedJedi != null) {
                System.out.printf("Jedi %s already exist %n", jediName);
                return;
            }

            Jedi newJedi = new Jedi(jediName, jediRank, jediAge, saberColor, jediStrength);
            savedPlanet.addJedi(newJedi);

            System.out.printf("Jedi %s created Successfully in planet %s %n", jediName, planetName);

        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Adds a new planet to the universe if it does not already exist.
     *
     * @param name name of the planet
     */
    @Override
    public void addPlanet(String name) {
        try {
            boolean alreadyExist = tryToFindPlanet(name) != null;
            if (alreadyExist) {
                System.out.printf("Planet %s already exist %n", name);
            } else {
                Planet planet = new Planet(name);
                planets.add(planet);
                System.out.printf("Planet %s added successfully...%n", planet.getName());
            }
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Retrieves a Jedi by name, or throws an exception if not found.
     *
     * @param name Jedi name
     * @return Jedi object
     * @throws PlanetException if the Jedi does not exist
     */
    private Jedi getJediByName(String name) throws PlanetException {
        Jedi savedJedi = tryToFindJedi(name);
        if (savedJedi == null) {
            throw new PlanetException("Jedi " + name + " does not exist");
        }
        return savedJedi;
    }

    /**
     * Attempts to find a Jedi by name across all planets.
     *
     * @param name name of the Jedi
     * @return matching Jedi or null
     * @throws PlanetException if name is invalid
     */
    private Jedi tryToFindJedi(String name) throws PlanetException {
        Jedi resultJedi = null;

        if (name == null || name.trim().isEmpty()) {
            throw new PlanetException("Jedi name cannot be empty");
        }

        for (Planet planet : planets) {
            Jedi savedJedi = planet.getJediByName(name);
            if (savedJedi != null) {
                resultJedi = savedJedi;
                break;
            }
        }

        return resultJedi;
    }

    /**
     * Retrieves a planet by name or throws an exception.
     *
     * @param name name of the planet
     * @return matching planet
     * @throws PlanetException if not found
     */
    private Planet getPlanetByName(String name) throws PlanetException {
        Planet savedPlanet = tryToFindPlanet(name);
        if (savedPlanet == null) {
            throw new PlanetException("Planet " + name + " does not exist.");
        }
        return savedPlanet;
    }

    /**
     * Attempts to find a planet by name.
     *
     * @param name name of the planet
     * @return matching planet or null
     * @throws PlanetException if name invalid
     */
    private Planet tryToFindPlanet(String name) throws PlanetException {
        Planet savedPlanet = null;

        if (name == null || name.trim().isEmpty()) {
            throw new PlanetException("Planet name cannot be empty");
        }

        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(name)) {
                savedPlanet = planet;
                break;
            }
        }

        return savedPlanet;
    }
}
