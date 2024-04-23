package Test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

public class TestFileSystem {

    private static final String ROOT_DIRECTORY = "test-directory/";

    @BeforeClass
    public static void setUp() {
        // Erstellt das Wurzelverzeichnis für den Testfall
        File rootDir = new File(ROOT_DIRECTORY);
        rootDir.mkdir();
    }

    @Test
    public void testGetDataFile() {
        String fileName = "dataFile";
        File file = FileSystem.getDataFile(fileName);
        Assert.assertEquals(ROOT_DIRECTORY + FileSystem.pathData + fileName + ".dat", file.getPath());
    }

    @Test
    public void testGetListFile() {
        String fileName = "listFile";
        File file = FileSystem.getListFile(fileName);
        Assert.assertEquals(ROOT_DIRECTORY + FileSystem.pathLists + fileName + ".list", file.getPath());
    }

    @Test
    public void testGetRatingFile() {
        String fileName = "ratingFile";
        File file = FileSystem.getRatingFile(fileName);
        Assert.assertEquals(ROOT_DIRECTORY + FileSystem.pathRatings + fileName + ".pat", file.getPath());
    }

    @Test
    public void testCreateSubDir() {
        String subDirName = "testSubDir";
        String path = ROOT_DIRECTORY + subDirName + "/";
        String createdPath = FileSystem.createSubDir(subDirName);
        Assert.assertEquals(path, createdPath);

        // Überprüft, ob das Verzeichnis tatsächlich erstellt wurde
        File subDir = new File(path);
        Assert.assertTrue(subDir.exists());
    }

    @Test
    public void testCreateFile() {
        String fileName = "testFile.txt";
        String path = ROOT_DIRECTORY;
        boolean fileCreated = FileSystem.createFile(fileName, path);
        Assert.assertTrue(fileCreated);

        // Überprüft, ob die Datei tatsächlich erstellt wurde
        File file = new File(path + fileName);
        Assert.assertTrue(file.exists());

        // Testet den Fall, in dem die Datei bereits existiert
        boolean existingFileCreated = FileSystem.createFile(fileName, path);
        Assert.assertFalse(existingFileCreated);
    }

    @Test
    public void testWriteContentsToFile() {
        String fileName = "testFile.txt";
        String path = ROOT_DIRECTORY;
        File file = new File(path + fileName);

        // Schreibt den Inhalt in die Datei
        String content = "Hello, World!";
        FileSystem.writeContentsToFile(file, content, false);

        // Überprüft, ob der Inhalt korrekt geschrieben wurde
        String readContent = FileSystem.readContentsFromFile(file);
        Assert.assertEquals(content + "\n", readContent);

        // Testet den Fall, in dem der Inhalt angehängt werden soll
        String appendedContent = "This is appended content.";
        FileSystem.writeContentsToFile(file, appendedContent, true);

        // Überprüft, ob der Inhalt korrekt angehängt wurde
        String appendedReadContent = FileSystem.readContentsFromFile(file);
        Assert.assertEquals(content + "\n" + appendedContent + "\n", appendedReadContent);
    }

    @Test
    public void testReadContentsFromFile() {
        String fileName = "testFile.txt";
        String path = ROOT_DIRECTORY;
        File file = new File(path + fileName);

        // Schreibt den Inhalt in die Datei
        String content = "Hello, World!";
        FileSystem.writeContentsToFile(file, content, false);

        // Liest den Inhalt aus der Datei
        String readContent = FileSystem.readContentsFromFile(file);

        // Überprüft, ob der Inhalt korrekt gelesen wurde
        Assert.assertEquals(content + "\n", readContent);
    }
}
