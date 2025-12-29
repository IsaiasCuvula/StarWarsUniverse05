package universes;

import models.Planet;
import java.util.List;

/**
 * Defines the contract for universe management.
 * Responsible for handling planets, Jedi, and related operations.
 */
public interface Universe {
    
    /**
     * Gets the currently associated filename.
     *
     * @return current filename
     */
    String getCurrentFilename();
    
    /**
     * Sets the current filename.
     *
     * @param filename file name to associate
     */
    void setCurrentFilename(String filename);
    
    /**
     * Returns all planets in memory.
     *
     * @return list of planets
     */
    List<Planet> getPlanets();
    
    /**
     * Replaces the current planet list.
     *
     * @param planets new planet list
     */
    void setPlanets(List<Planet> planets);
    
    /**
     * Prints information for two planets.
     *
     * @param planetName1 first planet name
     * @param planetName2 second planet name
     */
    void printTwoPlanets(String planetName1, String planetName2);
    
    /**
     * Prints information about a Jedi.
     *
     * @param jediName Jedi name
     */
    void printJedi(String jediName);
    
    /**
     * Prints information about a planet.
     *
     * @param planetName planet name
     */
    void printPlanet(String planetName);
    
    /**
     * Gets the most used lightsaber color on a planet.
     *
     * @param planetName planet name
     */
    void getMostUsedSaberColor(String planetName);
    
    /**
     * Gets the most used lightsaber color on a planet for a given rank.
     *
     * @param planetName planet name
     * @param jediRank Jedi rank
     */
    void getMostUsedSaberColor(String planetName, String jediRank);
    
    /**
     * Gets the youngest Jedi by rank on a planet.
     *
     * @param planetName planet name
     * @param jediRank Jedi rank
     */
    void getYoungestJedi(String planetName, String jediRank);
    
    /**
     * Gets the strongest Jedi on a planet.
     *
     * @param planetName planet name
     */
    void getStrongestJedi(String planetName);
    
    /**
     * Demotes a Jedi by reducing strength.
     *
     * @param jediName Jedi name
     * @param multiplier strength multiplier
     */
    void demoteJedi(String jediName, double multiplier);
    
    /**
     * Promotes a Jedi by increasing strength.
     *
     * @param jediName Jedi name
     * @param multiplier strength multiplier
     */
    void promoteJedi(String jediName, double multiplier);
    
    /**
     * Removes a Jedi from a planet.
     *
     * @param jediName Jedi name
     * @param planetName planet name
     */
    void removeJedi(String jediName, String planetName);
    
    /**
     * Creates and assigns a new Jedi to a planet.
     *
     * @param planetName planet name
     * @param jediName Jedi name
     * @param jediRank Jedi rank
     * @param jediAge Jedi age
     * @param saberColor lightsaber color
     * @param jediStrength Jedi strength
     */
    void createJedi(String planetName, String jediName, String jediRank,
                    int jediAge, String saberColor, double jediStrength);
    
    /**
     * Adds a new planet to the universe.
     *
     * @param name planet name
     */
    void addPlanet(String name);
}
