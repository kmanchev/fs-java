package com.kmanchev.fs.models;

public class FileNode extends Node {

    private String content;

    public FileNode(String name) {
        super(name);
        this.content = "";
    }


    public FileNode(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEmptyFile() {
        return content.isEmpty();
    }

    @Override
    public String getType() {
        return "file";
    }
}
