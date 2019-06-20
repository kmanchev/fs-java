package com.kmanchev.fs;

import com.kmanchev.fs.exceptions.NotAllowedOperationException;
import com.kmanchev.fs.models.DirNode;
import com.kmanchev.fs.models.FileNode;
import com.kmanchev.fs.models.Node;

import java.util.ArrayList;

public class FileSystem {
    private DirNode root;
    private DirNode currentDir;

    public FileSystem(String rootName) {
        this.root = new DirNode(rootName);
        this.currentDir = this.root;
    }

    public void addFile(String fileName, String content) {
        FileNode file = new FileNode(fileName, content);
        currentDir.setChild(file);
    }

    public void addDir(String dirName) {
        DirNode dir = new DirNode(dirName);
        currentDir.setChild(dir);
    }

    public void removeFile(String fileName) {
        for (Node child : currentDir.getChildren()) {
            if (child.getName().equals(fileName)) {
                currentDir.getChildren().remove(child);
            }
        }
    }

    public void removeDir(String dirName) throws NotAllowedOperationException {
        DirNode toRemove = null;
        for (Node child : currentDir.getChildren()) {
            if (child.getName().equals(dirName)) {
                if (child instanceof DirNode) {
                    if (((DirNode) child).getChildren().isEmpty()) {
                        toRemove = (DirNode) child;
                    } else {
                        throw new NotAllowedOperationException("Existing Directory hierarchy. Could not delete");
                    }
                }
            }
        }

        if (toRemove != null) {
            currentDir.getChildren().remove(toRemove);
        }
    }

    public void removeCurrentDirContent() {
        this.currentDir.getChildren().clear();
    }

    public String getCurrentDir() {
        return currentDir.getName();
    }

    public String getRoot() {
        return this.root.getName();
    }

    public ArrayList<String> getChildren() {
        ArrayList<String> childrenInfo = new ArrayList();

        for (Node child : this.currentDir.getChildren()) {
            childrenInfo.add(child.getName() + "; type: " + child.getType());
        }
        return childrenInfo;
    }

}
