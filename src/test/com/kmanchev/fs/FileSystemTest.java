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
        DirNode root = new DirNode("root");
        DirNode child1 = new DirNode("child1");
        DirNode child11 = new DirNode("child1.1");
        child1.setChild(child11);

        DirNode child2 = new DirNode("child2");
        DirNode child3 = new DirNode("child3");

        root.setChild(child1);
        root.setChild(child2);
        root.setChild(child3);

        FileSystem fs = new FileSystem(root);
        fs.removeDir("child2");

        ArrayList<String> children = fs.getChildren();
        assertTrue(children.size() == 2);
        assertTrue(children.contains("child3; type: directory"));
        assertTrue(children.contains("child1; type: directory"));
    }

    @Test
    public void testRemoveAllContentInCurrentDir() throws NotAllowedOperationException {
        DirNode root = new DirNode("root");
        DirNode child1 = new DirNode("child1");
        DirNode child11 = new DirNode("child1.1");
        child1.setChild(child11);

        DirNode child2 = new DirNode("child2");
        DirNode child3 = new DirNode("child3");

        root.setChild(child1);
        root.setChild(child2);
        root.setChild(child3);

        FileSystem fs = new FileSystem(root);
        fs.removeCurrentDirContent();

        ArrayList<String> children = fs.getChildren();
        assertTrue(children.size() == 0);
    }

    @Test
    public void tesCantRemoveDirIfItHasContent() {
        DirNode root = new DirNode("root");
        DirNode child1 = new DirNode("child1");
        DirNode child11 = new DirNode("child1.1");
        child1.setChild(child11);

        DirNode child2 = new DirNode("child2");
        DirNode child3 = new DirNode("child3");

        root.setChild(child1);
        root.setChild(child2);
        root.setChild(child3);

        FileSystem fs = new FileSystem(root);

        boolean shouldFail = true;
        try {
            fs.removeDir("child1");
        } catch (NotAllowedOperationException ex) {
            assertTrue(true);
            shouldFail = false;
        }

        assertFalse(shouldFail);

    }

}