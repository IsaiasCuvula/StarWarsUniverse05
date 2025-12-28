package manager;

import models.Planet;
import java.util.List;

public interface Universe {
    
    List<Planet> getPlanets();
    
    void setPlanets(List<Planet> planets);
    
    void printTwoPlanets(String planetName1, String planetName2);
    
    void printJedi(String jediName);
    
    void printPlanet(String planetName);
    
    void getMostUsedSaberColor(String planetName);
    
    void getMostUsedSaberColor(String planetName, String jediRank);
    
    void getYoungestJedi(String planetName, String jediRank);
    
    void getStrongestJedi(String planetName);
    
    void demoteJedi(String jediName, double multiplier);
    
    void promoteJedi(String jediName, double multiplier);
    
    void removeJedi(String jediName, String planetName);
    
    void createJedi(String planetName, String jediName, String jediRank,
                    int jediAge, String saberColor, double jediStrength);
    
    void addPlanet(String name);
}