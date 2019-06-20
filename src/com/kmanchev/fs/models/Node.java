package com.kmanchev.fs.models;

public abstract class Node {

    private String fullPath;
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public void setFullPath(String fullPath) {
         this.fullPath = fullPath;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getType();

}
