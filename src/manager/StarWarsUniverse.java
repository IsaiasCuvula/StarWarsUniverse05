package manager;

import exceptions.PlanetException;
import models.Jedi;
import models.Planet;
import models.Rank;

import java.util.ArrayList;
import java.util.List;

public class StarWarsUniverse {

    private final ArrayList<Planet> planets = new ArrayList<>();

    public  void printMenu() {
        System.out.println("**************************************");
        System.out.println("Choose an option:");
        System.out.println("1 - Add planet (add_planet <planet_name>)");
        System.out.println("2 - Create models.Jedi (create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>)");
        System.out.println("3 - Remove models.Jedi (remove_jedi <jedi_name> <planet_name>)");
        System.out.println("4 - Promote models.Jedi (<jedi_name> <multiplier>)");
        System.out.println("5 - Demote models.Jedi (<jedi_name> <multiplier>)");
        System.out.println("6 - Get Strongest models.Jedi  (get_strongest_jedi <planet_name>)");
        System.out.println("7 - Get Youngest models.Jedi (get_youngest_jedi <planet_name> <jedi_rank>)");
        System.out.println("8 - Get Most Used Saber Color (get_most_used_saber_color <planet_name> <jedi_rank>)");
        System.out.println("9 - Get Most Used Saber Color (get_most_used_saber_color <planet_name>)");
        System.out.println("10 - Show planet/moon details (print <planet_name>)");
        System.out.println("11 - Show details for a models.Jedi (print <jedi_name>)");
        System.out.println("12 - Show Jedis details for two planets (<planet_name> <planet_name>)");
        System.out.println("13 - Exit (To close the program)");
        System.out.println("**************************************");
        System.out.println("Command: ");
    }

    public void getYoungestJedi(String planetName, String jediRank){
        try {
            Rank rank = Rank.fromString(jediRank);

            Planet savedPlanet = getPlanetByName(planetName);
            List<Jedi> jedis = savedPlanet.getJediList();
            if(jedis.isEmpty()){
                throw new PlanetException("This Planet does not have any Jedi");
            }
            List<Jedi> sortedJedis = jedis.stream().sorted().toList();
            Jedi youngestJedi = null;

            for (Jedi jedi: sortedJedis){
                if(jedi.getRank() == rank){
                    if(youngestJedi == null){
                        youngestJedi = jedi;
                    }
                    if(jedi.getAge() < youngestJedi.getAge()){
                        youngestJedi = jedi;
                        break;
                    }
                }
            }
            if(youngestJedi == null){
                System.out.println("There is no Jedi with Rank");
            }
            System.out.println(youngestJedi);
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getStrongestJedi(String planetName){
        try {
            Planet savedPlanet = getPlanetByName(planetName);
            List<Jedi> jedis = savedPlanet.getJediList();
            if(jedis.isEmpty()){
                throw new PlanetException("This Planet does not have any Jedi");
            }
            Jedi strongestJedi = jedis.getFirst();

            for (Jedi jedi: jedis){
                if(jedi.getStrength() > strongestJedi.getStrength()){
                    strongestJedi = jedi;
                }
            }
            System.out.println(strongestJedi.toString());
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void demoteJedi(String jediName, double multiplier){
        try {
            if(multiplier < 0){
                throw new PlanetException("Multiplier must positive number");
            }
            Jedi jedi = getJediByName(jediName);
            double jediStrength = jedi.getStrength();
            double newJediStrength = jediStrength - (jediStrength * multiplier);
            jedi.setStrength(newJediStrength);
            Rank nextRank = jedi.getRank().stepDownRank();
            jedi.setRank(nextRank.name());
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public void promoteJedi(String jediName, double multiplier){
        try {
            if(multiplier < 0){
                throw new PlanetException("Multiplier must positive number");
            }
            Jedi jedi = getJediByName(jediName);
            double jediStrength = jedi.getStrength();
            double newJediStrength = jediStrength + (jediStrength * multiplier);
            jedi.setStrength(newJediStrength);
            Rank nextRank = jedi.getRank().stepUpRank();
            jedi.setRank(nextRank.name());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public void removeJedi(String jediName, String planetName) {
        try {
            Planet savedPlanet = getPlanetByName(planetName);
            savedPlanet.removeJedi(jediName);
            System.out.printf("Jedi %s removed successfully ", jediName);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public void createJedi(String planetName, String jediName,
    String jediRank, int jediAge, String saberColor,
            int jediStrength){
        try{
            Planet savedPlanet = getPlanetByName(planetName);
            Jedi savedJedi = tryToFindJedi(jediName);
            if(savedJedi != null){
                System.out.printf("Jedi %s already exist %n", jediName);
            }else{
                Jedi newJedi = new Jedi(
                        jediName, jediRank, jediAge, saberColor, jediStrength
                );
                savedPlanet.addJedi(newJedi);
            }
            System.out.println("Jedi Added Successfully...");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public void addPlanet(String name){
        try{
            boolean alreadyExist = tryToFindPlanet(name) != null;
            if(alreadyExist){
                System.out.printf("Planet %s already exist %n", name);
            }else{
                Planet planet = new Planet(name);
                planets.add(planet);
                System.out.printf("Planet %s added successfully...%n", planet.getName());
            }
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private Jedi getJediByName(String name) throws PlanetException {
        Jedi savedJedi = tryToFindJedi(name);
        if(savedJedi == null){
           throw new PlanetException("Jedi " + name + " does not exist");
        }
        return savedJedi;
    }

    private Jedi tryToFindJedi(String name) throws PlanetException {
       Jedi savedJedi = null;
       for (Planet planet: planets){
           savedJedi = planet.getJediByName(name);
           break;
       }
       return savedJedi;
    }

    private Planet  getPlanetByName(String name) throws PlanetException {
        Planet savedPlanet = tryToFindPlanet(name);
        if(savedPlanet == null){
            throw new PlanetException("Planet " + name + " does not exist.");
        }
        return  savedPlanet;
    }

    private Planet tryToFindPlanet(String name) throws PlanetException {
        Planet savedPlanet = null;
        if(name == null){
            throw new PlanetException("Planet name cannot be empty");
        }

        if(name.trim().isEmpty()){
            throw new PlanetException("Planet name cannot be empty");
        }
        for (Planet planet: planets){
            if (planet.getName().equalsIgnoreCase(name)) {
                savedPlanet = planet;
                break;
            }
        }
        return savedPlanet;
    }
}
