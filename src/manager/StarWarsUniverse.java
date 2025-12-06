package manager;

import exceptions.PlanetException;
import models.Jedi;
import models.Planet;
import models.Rank;

import java.util.*;

public class StarWarsUniverse {

    private final ArrayList<Planet> planets = new ArrayList<>();

    public  void printMenu() {
        System.out.println("**************************************");
        System.out.println("Choose an option:");
        System.out.println("1 - Add planet (add_planet <planet_name>)");
        System.out.println("2 - Create Jedi (create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>)");
        System.out.println("3 - Remove Jedi (remove_jedi <jedi_name> <planet_name>)");
        System.out.println("4 - Promote Jedi (<jedi_name> <multiplier>)");
        System.out.println("5 - Demote Jedi (<jedi_name> <multiplier>)");
        System.out.println("6 - Get Strongest Jedi  (get_strongest_jedi <planet_name>)");
        System.out.println("7 - Get Youngest Jedi (get_youngest_jedi <planet_name> <jedi_rank>)");
        System.out.println("8 - Get Most Used Saber Color (get_most_used_saber_color <planet_name> <jedi_rank>)");
        System.out.println("9 - Get Most Used Saber Color (get_most_used_saber_color <planet_name>)");
        System.out.println("10 - Show planet/moon details (print_planet <planet_name>)");
        System.out.println("11 - Show Jedi details (print_jedi <jedi_name>)");
        System.out.println("12 - Show Jedis details for two planets (<planet_name> <planet_name>)");
        System.out.println("13 - Exit (To close the program)");
        System.out.println("**************************************");
        System.out.println("Command: ");
    }

    public void printTwoPlanets(String planetName1, String planetName2){
        try {
            Planet p1 = getPlanetByName(planetName1);
            Planet p2 = getPlanetByName(planetName2);
            System.out.println(p1.getName() + " + " + p2.getName());

            List<Jedi> allJedis = new ArrayList<>();
            allJedis.addAll(p1.getJediList());
            allJedis.addAll(p2.getJediList());

            allJedis.stream()
                    .sorted((j1, j2) -> j1.getName().compareToIgnoreCase(j2.getName()))
                    .forEach(System.out::println);
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void  printJedi(String jediName){
        try {
            Jedi jedi = getJediByName(jediName);
            Planet jediPlanet = null;
            for (Planet planet: planets){
                if(planet.getJediList().contains(jedi)){
                    jediPlanet = planet;
                    break;
                }
            }
            System.out.println(jedi);
            if (jediPlanet != null) {
                System.out.println("Inhabits planet: " + jediPlanet.getName());
            } else {
                System.out.println("This Jedi does not inhabit any planet.");
            }
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void  printPlanet(String planetName){

       try {
           Planet planet = getPlanetByName(planetName);
           System.out.println("Planet: " + planet.getName() + " Jedis:");

           planet.getJediList().stream()
                   .sorted((j1, j2) -> {
                       int byRank = j1.getRank().compareTo(j2.getRank());
                       if (byRank != 0) return byRank;
                       return j1.getName().compareToIgnoreCase(j2.getName());
                   })
                   .forEach(j -> System.out.println(j.getName()));
       } catch (Exception e){
           System.out.println("Something went wrong: " + e.getMessage());
       }
    }

    public void getMostUsedSaberColor(String planetName){
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
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getMostUsedSaberColor(String planetName, String jediRank){
        try {
            Rank rank = Rank.fromString(jediRank);
            Planet savedPlanet = getPlanetByName(planetName);
            List<Jedi> jedis = savedPlanet.getJediList();

            if(jedis.isEmpty()){
                throw new PlanetException("This Planet does not have any Jedi");
            }
            Map<String, Integer> colorCounts = new HashMap<>();
            for (Jedi jedi: jedis){
                if(jedi.getRank().equals(rank)){
                    String color = jedi.getLightsaberColor();
                    int counter = colorCounts.getOrDefault(color, 0) + 1;
                    colorCounts.put(jedi.getLightsaberColor(), counter);
                }
            }
            if (colorCounts.isEmpty()) {
                System.out.println( "No Jedi found with rank: " + jediRank + " on " + planetName);
            }

            String mostUsedColor = null;
            int maxCount = 0;

            for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostUsedColor = entry.getKey();
                }
            }
            System.out.printf("For the Rank %s the most used saber color is: %s", mostUsedColor, maxCount);
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
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
                if(jedi.getRank().equals(rank)){
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
