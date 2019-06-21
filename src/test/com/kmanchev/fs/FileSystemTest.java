package test.com.kmanchev.fs;

import com.kmanchev.fs.FileSystem;
import com.kmanchev.fs.exceptions.NotAllowedOperationException;
import com.kmanchev.fs.models.DirNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {


    @Test
    public void testRemoveDirInFileSystem() throws NotAllowedOperationException {
        FileSystem fs = new FileSystem("root");
        fs.addDir("child1");
        fs.addDir("child2");
        fs.addDir("child3");

        fs.removeDir("child2");

        ArrayList<String> children = fs.getCurrentDirChildren();
        assertTrue(children.size() == 2);
        assertTrue(children.contains("child3; type: directory"));
        assertTrue(children.contains("child1; type: directory"));
    }

    @Test
    public void testRemoveAllContentInCurrentDir() throws NotAllowedOperationException {

        FileSystem fs = new FileSystem("root");
        fs.addDir("child1");
        fs.addDir("child2");
        fs.addDir("child3");
        fs.gotToDir("child1");
        fs.addDir("child11");
        fs.addDir("child12");
        fs.removeCurrentDirContent();

        ArrayList<String> children = fs.getCurrentDirChildren();
        assertTrue(children.size() == 0);
    }

    @Test
    public void tesCantRemoveDirIfItHasContent() {
        FileSystem fs = new FileSystem("root");
        fs.addDir("child1");
        fs.addDir("child2");
        fs.addDir("child3");
        fs.gotToDir("child1");
        fs.addDir("child11");
        fs.addDir("child12");
        fs.goToParentDir();

        boolean shouldFail = true;
        try {
            fs.removeDir("child1");
        } catch (NotAllowedOperationException ex) {
            assertTrue(true);
            shouldFail = false;
        }

        assertFalse(shouldFail);

    }

    @Test
    public void tesDirTryToDeleteHerself() {
        FileSystem fs = new FileSystem("root");
        fs.addDir("child1");
        fs.addDir("child2");
        fs.addDir("child3");
        fs.gotToDir("child1");
        fs.addDir("child11");
        fs.addDir("child12");

        boolean shouldFail = true;
        try {
            fs.removeDir("child1");
        } catch (NotAllowedOperationException ex) {
            assertEquals("Trying to delete yourself. Could not delete", ex.getMessage());
            shouldFail = false;
        }

        assertFalse(shouldFail);

    }

}