package com.techiespace.projects.fallingnotes.fragments.nestedListUi;

public class SingleItemModel {
    private String name, url, instructions;

    public SingleItemModel() {

    }

    public SingleItemModel(String name, String url,String instructions) {
        this.name = name;
        this.url = url;
        this.instructions = instructions;
    }
    public SingleItemModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
