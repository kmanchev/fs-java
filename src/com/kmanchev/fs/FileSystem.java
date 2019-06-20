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
        this.root.setFullPath(rootName);
        this.currentDir = this.root;
    }

    public void addFile(String fileName, String content) {
        FileNode file = new FileNode(fileName, content);
        file.setFullPath(currentDir.getFullPath() + "/" + fileName);
        currentDir.setChild(file);
    }

    public void addDir(String dirName) {
        DirNode dir = new DirNode(dirName);
        dir.setFullPath(currentDir.getFullPath() + "/" + dirName);
        currentDir.setChild(dir);
    }

    public String gotToDir(String targetDir) {
        for (DirNode child : currentDir.getDirChildren()) {
            if (child.getName().equals(targetDir)) {
                currentDir = child;
                return "OK";
            }
        }
        return "Invalid command";
    }

    public String getRootName() {
        return this.root.getName();
    }

    public String getCurrentDirName() {
        return this.currentDir.getName();
    }

    public String getCurrentDirFullPath() {
        return this.currentDir.getFullPath();
    }

    public void goToParentDir() {
        this.currentDir = this.currentDir.getParent();
    }

    public void removeFile(String fileName) {
        for (Node child : currentDir.getChildren()) {
            if (child.getName().equals(fileName)) {
                currentDir.getChildren().remove(child);
                break;
            }
        }
    }

    public void removeDir(String dirName) throws NotAllowedOperationException {
        if (currentDir.getName().equals(dirName)) {
            throw new NotAllowedOperationException("Trying to delete yourself. Could not delete");
        }

        DirNode toRemove = null;
        for (Node child : currentDir.getChildren()) {
            if (child.getName().equals(dirName)) {
                if (child instanceof DirNode) {
                    if (((DirNode) child).getChildren().isEmpty()) {
                        toRemove = (DirNode) child;
                        break;
                    } else {
                        throw new NotAllowedOperationException("Existing Directory hierarchy. Could not delete");
                    }
                }
            }
        }

        currentDir.getChildren().remove(toRemove);
    }

    public void removeCurrentDirContent() {
        this.currentDir.getChildren().clear();
    }

    public void removeChildDirContent(String target) {
        for (Node current : this.currentDir.getChildren()) {
            if (current.getName().equals(target)) {
                if (current.getType().equals("directory")) {
                    DirNode child = (DirNode) current;
                    child.getChildren().clear();
                }
            }
        }
    }

    public ArrayList<String> getCurrentDirChildren() {
        ArrayList<String> childrenInfo = new ArrayList();

        for (Node child : this.currentDir.getChildren()) {
            childrenInfo.add(child.getName() + "; type: " + child.getType());
        }
        return childrenInfo;
    }

    public ArrayList<String> getCurrentDirChildrenDetailed() {
        ArrayList<String> childrenInfo = new ArrayList();

        for (Node child : this.currentDir.getChildren()) {
            childrenInfo.add(child.getName() + "; full Path:" + child.getFullPath() + "; type: " + child.getType());
        }
        return childrenInfo;
    }

    public boolean isChildDir(String candidate) {
        for (Node child : this.currentDir.getChildren()) {
            if (child.getName().equals(candidate)) {
                return child.getType().equals("directory");
            }
        }
        return false;
    }

    public boolean isChildFile(String candidate) {
        for (Node child : this.currentDir.getChildren()) {
            if (child.getName().equals(candidate)) {
                return child.getType().equals("file");
            }
        }
        return false;
    }

}
