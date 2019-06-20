package com.kmanchev.fs.models;

import java.util.ArrayList;

public class DirNode extends Node {
    private ArrayList<Node> children;

    public DirNode(String name) {
        super(name);
        children = new ArrayList<Node>();
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChild(Node child) {
        children.add(child);
    }

    public boolean hasChildren() {
        return children.size() != 0;
    }

    @Override
    public String getType() {
        return "directory";
    }
}
