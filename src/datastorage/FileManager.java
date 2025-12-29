package datastorage;

import models.Jedi;
import models.Planet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles opening, loading, saving and closing of files that store the Star Wars universe data.
 * Uses classic java.io.Reader/Writer classes as required by the course material.
 */
public class FileManager implements DataStorage {
    private String currentFilename;
    private boolean isOpen = false;
    
    /**
     * Returns whether a file is currently open.
     *
     * @return true if a file is open, false otherwise
     */
    @Override
    public boolean isOpen() {
        return this.isOpen;
    }
    
    /**
     * Returns the currently opened filename.
     *
     * @return current filename or null if none is open
     */
    @Override
    public String getCurrentFilename() {
        return currentFilename;
    }
    
    /**
     * Opens the specified filename. If the file does not exist it will be created.
     * Sets the internal state to open and stores the filename.
     *
     * @param filename path to the file to open
     */
    @Override
    public void open(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
               boolean result = file.createNewFile();
               if(!result){
                   System.out.printf("File %s failed to be created %n", filename);
               }
               System.out.printf("File %s successfully created %n", filename);
            }
            currentFilename = filename;
            isOpen = true;
            System.out.printf("Successfully opened %s%n", filename);
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
    
    /**
     * Loads planets and their Jedis from the currently opened file.
     * The file format expected:
     *   Planet: <name>
     *   Jedi: Name:<name>, Rank:<rank>, Age:<age>, Color:<color>, Strength:<strength>
     * If no file is open this returns an empty list.
     *
     * @return list of loaded Planet objects (empty list if none loaded or no open file)
     */
    @Override
    public List<Planet> loadPlanets() {
        List<Planet> planets = new ArrayList<>();
        if (!isOpen) {
            return planets;
        }
        
        try {
            File file = new File(currentFilename);
            if (!file.exists() || file.length() == 0) {
                return planets;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            Planet currentPlanet = null;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                if (line.startsWith("Planet:")) {
                    String planetName = line.substring(7).trim();
                    currentPlanet = new Planet(planetName);
                    planets.add(currentPlanet);
                } else if (line.startsWith("Jedi:") && currentPlanet != null) {
                    String[] parts = line.substring(5).trim().split(",");
                    if (parts.length == 5) {
                        String name = parts[0].split(":")[1].trim();
                        String rank = parts[1].split(":")[1].trim();
                        int age = Integer.parseInt(parts[2].split(":")[1].trim());
                        String color = parts[3].split(":")[1].trim();
                        double strength = Double.parseDouble(parts[4].split(":")[1].trim());
                        
                        Jedi jedi = new Jedi(name, rank, age, color, strength);
                        currentPlanet.addJedi(jedi);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading planets: " + e.getMessage());
        }
        return planets;
    }
    
    /**
     * Closes the currently opened file. After closing, no file operations except open are allowed.
     * Prints a confirmation message on success.
     */
    @Override
    public void close() {
        try {
            ensureFileIsOpen();
            isOpen = false;
            System.out.printf("Successfully closed %s%n", currentFilename);
            currentFilename = null;
        } catch (Exception e) {
            System.out.println(" No file is currently open : " + e.getMessage());
        }
    }
    
    /**
     * Saves the provided list of planets to the currently opened file.
     * If no file is open, prints an error message.
     *
     * @param planets list of planets to save
     */
    @Override
    public void save(List<Planet> planets) {
        try {
            ensureFileIsOpen();
            writeContent(planets, currentFilename);
        } catch (Exception e) {
            System.out.println("Error in save: " + e.getMessage());
        }
    }
    
    /**
     * Saves the provided list of planets into a new file specified by filename.
     * This method opens the new file (creates if necessary) and writes the content.
     *
     * @param filename target file path
     * @param planets list of planets to save
     */
    @Override
    public void saveAs(String filename, List<Planet> planets) {
        try {
            this.open(filename);
            writeContent(planets, filename);
        } catch (Exception e) {
            System.out.println("Error in saveAs: " + e.getMessage());
        }
    }
    
    /**
     * Internal helper that writes the textual representation of planets and their Jedis
     * to the specified filename using BufferedWriter and FileWriter.
     *
     * @param planets list of planets to serialize
     * @param filename target filename
     */
    private void writeContent(List<Planet> planets, String filename) {
        try {
            BufferedWriter writer = this.getBufferedWriter(filename);
            for (Planet planet : planets) {
                writer.write("Planet: " + planet.getName());
                writer.newLine();
                for (Jedi jedi : planet.getJediList()) {
                    writer.write("Jedi: Name:" + jedi.getName() +
                                         ", Rank:" + jedi.getRank() +
                                         ", Age:" + jedi.getAge() +
                                         ", Color:" + jedi.getLightsaberColor() +
                                         ", Strength:" + jedi.getStrength());
                    writer.newLine();
                }
            }
            writer.close();
            System.out.printf("Successfully saved %s%n", filename);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    
    /**
     * Creates and returns a BufferedWriter for the given filename.
     *
     * @param filename target filename
     * @return BufferedWriter instance
     * @throws Exception if the writer cannot be created
     */
    private BufferedWriter getBufferedWriter(String filename) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            return new BufferedWriter(fileWriter);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Ensures that a file has been opened and is available for operations.
     * Throws IllegalStateException if no file is open.
     */
    private void ensureFileIsOpen() {
        if (!isOpen) {
            throw new IllegalStateException("No file is currently open.");
        }
    }
    
    /**
     * Prints the interactive help / menu to the console.
     * Lists all supported commands with brief usage.
     */
    @Override
    public void help() {
        System.out.println("************************************************************");
        System.out.println("                  STAR WARS COMMANDS MENU                   ");
        System.out.println("************************************************************");
        
        System.out.println("  JEDI MANAGEMENT (Creation, Removal, Rank)");
        System.out.println("------------------------------------------------------------");
        System.out.println(" 1. Add Planet:         add_planet <planet_name>");
        System.out.println(" 2. Create Jedi:        create_jedi <planet> <name> <rank> <age> <saber> <strength>");
        System.out.println(" 3. Remove Jedi:        remove_jedi <jedi_name> <planet_name>");
        System.out.println(" 4. Promote Jedi:       promote_jedi <jedi_name> <multiplier>");
        System.out.println(" 5. Demote Jedi:        demote_jedi <jedi_name> <multiplier>");
        System.out.println();
        
        System.out.println("  PLANET & QUERY OPERATIONS (Statistics and Details)");
        System.out.println("------------------------------------------------------------");
        System.out.println(" 6. Get Strongest Jedi: get_strongest_jedi <planet_name>");
        System.out.println(" 7. Get Youngest Jedi:  get_youngest_jedi <planet_name> <jedi_rank>");
        System.out.println(" 8. Most Used Saber:    get_most_used_saber_color <planet_name> <jedi_rank>");
        System.out.println(" 9. Most Used Saber (All): get_most_used_saber_color <planet_name>");
        System.out.println("10. Show Planet Details: print_planet <planet_name>");
        System.out.println("11. Show Jedi Details:   print_jedi <jedi_name>");
        System.out.println("12. Compare Planets:     print_two_planets <planet1> <planet2>");
        System.out.println();
        
        System.out.println("  FILE & PROGRAM CONTROL (System Commands)");
        System.out.println("------------------------------------------------------------");
        System.out.println("13. Exit Program:      exit");
        System.out.println("14. Show Menu:         help");
        System.out.println("15. Open file:         open <file>");
        System.out.println("16. Close file:        close");
        System.out.println("17. Save file:         save");
        System.out.println("18. Save As:           saveas <file>");
        System.out.println("************************************************************");
    }
}