package datastorage;

import models.Planet;
import java.util.List;

/**
 * Defines a contract for data persistence operations in a universe management system.
 * This interface provides methods for managing file operations including opening,
 * closing, loading, and saving universe data.
*/
public interface DataStorage {
    
    /**
     * Checks whether a storage resource is currently open and available for operations.
     *
     * @return true if a storage resource is open, false otherwise
     */
    boolean isOpen();
    
    /**
     * Returns the identifier of the currently opened storage resource.
     *
     * @return current storage identifier (e.g., filename) or null if none is open
     */
    String getCurrentFilename();
    
    /**
     * Opens a storage resource for read/write operations.
     * If the resource does not exist, it should be created.
     *
     * @param filename identifier of the storage resource to open (e.g., file path)
     */
    void open(String filename);
    
    /**
     * Loads all planets and their associated data from the currently opened storage resource.
     * The storage format and structure is implementation-specific.
     *
     * @return list of loaded Planet objects, or empty list if no data exists or no resource is open
     */
    List<Planet> loadPlanets();
    
    /**
     * Closes the currently opened storage resource.
     * After closing, no operations except open should be allowed.
     * Should provide appropriate feedback to the user.
     */
    void close();
    
    /**
     * Saves the provided list of planets to the currently opened storage resource.
     * If no resource is open, appropriate error handling should occur.
     *
     * @param planets list of planets to persist
     */
    void save(List<Planet> planets);
    
    /**
     * Saves the provided list of planets to a new storage resource.
     * This operation should open the new resource if necessary.
     *
     * @param filename identifier of the target storage resource
     * @param planets list of planets to persist
     */
    void saveAs(String filename, List<Planet> planets);
    
    /**
     * Displays help information about available commands and operations.
     * This method provides user guidance on how to use the system.
     */
    void help();
}