package ru.goodibunakov.abbyy_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Markup2 {
    @SerializedName("Markup")
    @Expose
    private List<Markup> markup;
    @SerializedName("Type")
    @Expose
    private int type;
    @SerializedName("Items")
    @Expose
    private List<Item> listItems;
    @SerializedName("Node")
    @Expose
    private String node;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("IsOptional")
    @Expose
    private Boolean isOptional;

    public List<Markup> getMarkup() {
        return markup;
    }

    public void setMarkup(List<Markup> markup) {
        this.markup = markup;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getOptional() {
        return isOptional;
    }

    public void setOptional(Boolean optional) {
        isOptional = optional;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }
}
