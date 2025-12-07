package manager;

import java.io.File;

public class FileManager {
    private final String filename =  "star_wars.txt";


    public void initFile(){
        try{
            File file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File created successfully: " + file.getName());
            }else {
                System.out.println("File already exists.");
            }
        }catch (Exception e){
            System.out.println("Error executing init file manager: " + e.getMessage());
        }
    }

    public void open(){

    }

    public void close(){

    }

    public void save(){

    }

    public void saveAs(){

    }

    public void help(){
        System.out.println("The following commands are supported: ");
        System.out.println("open <file> opens <file>");
        System.out.println("close closes currently opened file");
        System.out.println("save saves the currently open file");
        System.out.println("saveas <file> saves the currently open file in <file>");
        System.out.println("help prints this information");
        System.out.println("exit exists the program");

    }




}
