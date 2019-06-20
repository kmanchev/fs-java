package com.kmanchev.fs.models;

public abstract class Node {

    private String fullPath;
    private String name;

    public Node(String name) {
        this.name = name;
        this.fullPath = this.fullPath + "/" + name;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getType();

}
