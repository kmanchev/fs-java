package com.kmanchev.fs.models;

import java.util.ArrayList;

public class DirNode extends Node {
    private ArrayList<Node> children;
    private DirNode parent;

    public DirNode(String name) {
        super(name);
        children = new ArrayList<Node>();
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public ArrayList<DirNode> getDirChildren() {
        ArrayList<DirNode> nodes = new ArrayList<>();
        for (Node child : children) {
            if (child instanceof DirNode) {
                nodes.add((DirNode) child);
            }
        }
        return nodes;
    }

    public void setChild(Node child) {
        if (child instanceof DirNode) {
            ((DirNode) child).setParent(this);
        }

        children.add(child);
    }

    public void setParent(DirNode parent) {
        this.parent = parent;
    }

    public boolean hasChildren() {
        return children.size() != 0;
    }

    public DirNode getParent() {
        return this.parent;
    }

    @Override
    public String getType() {
        return "directory";
    }
}
