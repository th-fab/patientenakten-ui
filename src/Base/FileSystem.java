package Base;

import java.io.*;

/**
 * @author Sancar Ucdu & Fabian Thien
 *
 */
public class FileSystem {

    private static String root = "";
    private static final String SUB_DIR_DATA;
    private static final String SUB_DIR_LISTS;
    private static final String SUB_DIR_RATINGS;
    public static String pathData;
    public static String pathLists;
    public static String pathRatings;

    static {
        SUB_DIR_RATINGS     = "/data/ratings/";
        SUB_DIR_LISTS       = "/data/lists/";
        SUB_DIR_DATA        = "/data/";

        pathData = SUB_DIR_DATA;
        pathLists = SUB_DIR_LISTS;
        pathRatings = SUB_DIR_RATINGS;
    }

    /**
     * Constructor for the FileSystem class
     * @param rootDirectory the path to root directory where the filesystem is placed
     */
    public FileSystem(String rootDirectory)
    {
        this.root = rootDirectory;
        initialize();
    }

    /**
     * Second(empty) constructor as per assignement requirement (Will and should not be called)
     */
    public FileSystem() {
        this.root = "";
        initialize();
    }

    /**
     * Creates the subdirectories and sets the working paths.
     */
    public void initialize()
    {
        pathData = createSubDir(SUB_DIR_DATA);
        pathLists = createSubDir(SUB_DIR_LISTS);
        pathRatings = createSubDir(SUB_DIR_RATINGS);
    }

    /**
     * Gets a data file from the internal filesystem
     * @param fileName the name of the data file
     * @return the data file with the given name
     */
    public static File getDataFile(String fileName)
    {
        File file = getFile(pathData, fileName + ".dat");
        return file;
    }

    /**
     * Gets a List file from the internal Filesystem
     * @param fileName the name of the list file
     * @return the list file with the given filename
     */
    public static File getListFile(String fileName)
    {
        File file = getFile(pathLists, fileName + ".list");
        return file;
    }

    /**
     * Gets a rating file from the internal filesystem
     * @param fileName the name of the rating file
     * @return the rating file with the given filename
     */
    public static File getRatingFile(String fileName)
    {
        File file = getFile(pathRatings, fileName + ".pat");
        return file;
    }

    /**
     * Gets the file from a given path and filename
     * @param path the path to the file
     * @param fileName the name of the file
     * @return the file found at the given path with the given filename
     */
    private static File getFile(String path, String fileName)
    {
        File file = new File(path + fileName);

        if(!file.exists())
            createFile(fileName, path);

        return file;

    }

    /**
     * Attempts to create a directory in the path given by the root parameter with the name provided in the nameOfSubDirectory parameter.
     * @param nameOfSubDirectory The name of the subDirectory that should be created
     * @return The full path to the created subdirectory, regardless if it was created or already exists.
     */
    public static String createSubDir(String nameOfSubDirectory)
    {
        String path = root + nameOfSubDirectory;

        boolean subDirCreated = new File(path).mkdir();
        if(subDirCreated)
            System.out.printf("Subdirectory: %s created\n", path);
        else
            System.out.printf("Subdirectory %s already exists\n", path);

        return path;
    }

    /**
     * Attempts to create a file with a given file-name in the directory provided in the <b>path</b> parameter.
     * @param fileName The name of the file to be created (e.g. File.exe).
     * @param path The path where the file should be created at.
     * @return <b>true</b> if file was successfully created, <b>false</b> if the file already exists.
     */
    public static boolean createFile(String fileName, String path)
    {
        File f = new File(path + fileName);
        boolean fileCreated = false;

        try {
            fileCreated = f.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if(fileCreated)
            System.out.printf("File: %s successfully created\n", fileName);
        else
            System.out.printf("File: %s already exists\n", fileName);

        return fileCreated;
    }

    /**
     * Writes the given content string to a given File(via File Object).
     * @param file The File in which you want to write
     * @param content The content you want to be written in the File
     * @param append Wether or not the content should be appended to the file
     */
    public static void writeContentsToFile(File file, String content, boolean append)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, append));

            bw.write(content);
            bw.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the String contents of a file.
     * @param file The file that's supposed to be read.
     * @return A string of the read content, lines seperated by newline characters.
     */
    public static String readContentsFromFile(File file)
    {
        String content = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            StringBuilder sb = new StringBuilder();

            while((line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }

            br.close();
            content = sb.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return content;
    }

    public static void deletePatientFile(String patientNumber) {
        File f = getRatingFile(patientNumber);
        try {
            f.delete();
        } catch (Exception e) {
            System.out.println("Error deleting file");
        }
    }

    public static String getRoot()
    {
        return root;
    }

    public static String getPathData()
    {
        return pathData;
    }

    public static String getPathLists()
    {
        return pathLists;
    }

    public static String getPathRatings()
    {
        return pathRatings;
    }
}
