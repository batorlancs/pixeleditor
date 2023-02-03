package sprites.io.file;

import junit.framework.TestCase;

public class FileManagerTest extends TestCase {

    FileManager fileManager = new FileManager();

//    public void testSaveFile() {
//    }
//
//    public void testOpenFile() {
//    }

    /**
     * Add Txt To String Tests
     */
    public void testAddTxtToStringWhenNull() {
        assertEquals("'' -> example.txt", "sprites.txt", fileManager.addTxtToString(""));
    }
    public void testAddTxtToString() {
        assertEquals("example -> example.txt", "example.txt", fileManager.addTxtToString("example"));
    }
    public void testAddTxtToString2() {
        assertEquals("file -> file.txt", "file.txt", fileManager.addTxtToString("file"));
    }
    public void testAddTxtToString3() {
        assertEquals("thisisalongfilename -> thisisalongfilename.txt", "thisisalongfilename.txt", fileManager.addTxtToString("thisisalongfilename"));
    }
    public void testAddTxtToString4() {
        assertEquals("example.txt -> example.txt", "example.txt", fileManager.addTxtToString("example.txt"));
    }
    public void testAddTxtToString5() {
        assertEquals("example.txt. -> example.txt", "example.txt.", fileManager.addTxtToString("example.txt."));
    }
    public void testAddTxtToString6() {
        assertEquals("exa.... -> exa.txt", "example.txt.", fileManager.addTxtToString("example.txt."));
    }
    public void testAddTxtToString7() {
        assertEquals("e.... -> e.txt", "e.txt", fileManager.addTxtToString("e...."));
    }
    public void testAddTxtToString8() {
        assertEquals("e.example.ex.exam -> e.txt", "e.txt", fileManager.addTxtToString("e.example.ex.exam"));
    }
}