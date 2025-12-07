package manager;

import exceptions.PlanetException;
import models.Jedi;
import models.Planet;
import models.Rank;

import java.util.*;

public class StarWarsUniverse {

    private final ArrayList<Planet> planets = new ArrayList<>();
    private FileManager fileManager;

    public StarWarsUniverse(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public void startProgram(Scanner scanner){
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
                    this.addPlanet(planetName);
                }
                else if (action.equalsIgnoreCase("create_jedi") && data.length == 7) {
                    String planetName = data[1];
                    String jediName = data[2];
                    String rank = data[3];
                    int age = Integer.parseInt(data[4]);
                    String lightsaber = data[5];
                    double strength = Double.parseDouble(data[6]);

                    this.createJedi(
                            planetName,jediName, rank,age, lightsaber, strength
                    );
                }
                else if (action.equalsIgnoreCase("remove_jedi") && data.length == 3) {
                    String jediName = data[1];
                    String planetName = data[2];
                    this.removeJedi(jediName, planetName);
                }
                else if (action.equalsIgnoreCase("promote_jedi") && data.length == 3) {
                    String jediName = data[1];
                    double multiplier = Double.parseDouble(data[2]);
                    this.promoteJedi(jediName, multiplier);
                }
                else if (action.equalsIgnoreCase("demote_jedi") && data.length == 3) {
                    String jediName = data[1];
                    double multiplier = Double.parseDouble(data[2]);
                    this.demoteJedi(jediName, multiplier);
                }
                else if (action.equalsIgnoreCase("get_strongest_jedi") && data.length == 2) {
                    String planetName = data[1];
                    this.getStrongestJedi(planetName);
                }
                else if (action.equalsIgnoreCase("get_youngest_jedi") && data.length == 3) {
                    String planetName = data[1];
                    String rank = data[2];
                    this.getYoungestJedi(planetName, rank);
                }
                else if (action.equalsIgnoreCase("get_most_used_saber_color") && data.length == 3) {
                    String planetName = data[1];
                    String rank = data[2];
                    this.getMostUsedSaberColor(planetName, rank);
                }
                else if (action.equalsIgnoreCase("get_most_used_saber_color") && data.length == 2) {
                    String planetName = data[1];
                    this.getMostUsedSaberColor(planetName);
                }
                else if (action.equalsIgnoreCase("print_jedi") && data.length == 2) {
                    String jediName = data[1];
                    this.printJedi(jediName);
                }
                else if (action.equalsIgnoreCase("print_planet") && data.length == 2) {
                    String planetName = data[1];
                    this.printPlanet(planetName);
                }
                else if (action.equalsIgnoreCase("print_two_planets") && data.length == 3) {
                    String planet1 = data[1];
                    String planet2 = data[2];
                    this.printTwoPlanets(planet1, planet2);
                }
                else {
                    System.out.println("Invalid command or wrong number of parameters.");
                }
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
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
            System.out.println("> Something went wrong: " + e.getMessage());
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
                System.out.println(" Inhabits planet: " + jediPlanet.getName());
            } else {
                System.out.println(" This Jedi does not inhabit any planet.");
            }
        }catch (Exception e){
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }

    public void printPlanet(String planetName){

       try {
           Planet planet = getPlanetByName(planetName);
           System.out.println("Planet: " + planet.getName());

           List<Jedi> jedis = planet.getJediList();
           if(jedis.isEmpty()){
               System.out.println("This planet has any Jedis yet...");
           }else{
               System.out.println("Jedis: ");
           }

           jedis.stream()
                   .sorted((j1, j2) -> {
                       int byRank = j1.getRank().compareTo(j2.getRank());
                       if (byRank != 0) return byRank;
                       return j1.getName().compareToIgnoreCase(j2.getName());
                   })
                   .forEach(j -> System.out.println(j.getName()));
       } catch (Exception e){
           System.out.println("> Something went wrong: " + e.getMessage());
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
            System.out.println("> Something went wrong: " + e.getMessage());
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
            System.out.printf("For the Rank %s the most used saber color is: %s", mostUsedColor, maxCount);
        }catch (Exception e){
            System.out.println("> Something went wrong: " + e.getMessage());
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
            System.out.println("> Something went wrong: " + e.getMessage());
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
            System.out.println("> Something went wrong: " + e.getMessage());
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
            System.out.println("> Something went wrong: " + e.getMessage());
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
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }


    public void removeJedi(String jediName, String planetName) {
        try {
            Planet savedPlanet = getPlanetByName(planetName);
            savedPlanet.removeJedi(jediName);
            System.out.printf("Jedi %s removed successfully ", jediName);
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
        }
    }


    public void createJedi(String planetName, String jediName,
        String jediRank, int jediAge, String saberColor, double jediStrength){
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
            System.out.printf("Jedi %s created Successfully in planet %s %n", jediName, planetName);
        } catch (Exception e) {
            System.out.println("> Something went wrong: " + e.getMessage());
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
                fileManager.save(planet.toString());
                System.out.printf("Planet %s added successfully...%n", planet.getName());
            }
        }catch (Exception e){
            System.out.println("> Something went wrong: " + e.getMessage());
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
        Jedi resultJedi = null;
        if(name == null){
            throw new PlanetException("Jedi name cannot be empty");
        }

        if(name.trim().isEmpty()){
            throw new PlanetException("Jedi name cannot be empty");
        }

        for (Planet planet: planets){
            Jedi savedJedi = planet.getJediByName(name);
            if(savedJedi != null){
                resultJedi = savedJedi;
                break;
            }
        }
       return resultJedi;
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
