package ru.goodibunakov.abbyy_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Markup {
    @SerializedName("Node")
    @Expose
    private String node;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("IsOptional")
    @Expose
    private Boolean isOptional;
    @SerializedName("IsItalics")
    @Expose
    private boolean isItalics;
    @SerializedName("IsAccent")
    @Expose
    private boolean isAccent;
    @SerializedName("FullText")
    @Expose
    private String fullText;
    @SerializedName("Markup")
    @Expose
    private List<Markup> markup;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("Item")
    @Expose
    private List<Item> items;

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

    public boolean isItalics() {
        return isItalics;
    }

    public void setItalics(boolean italics) {
        isItalics = italics;
    }

    public boolean isAccent() {
        return isAccent;
    }

    public void setAccent(boolean accent) {
        isAccent = accent;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public List<Markup> getMarkup() {
        return markup;
    }

    public void setMarkup(List<Markup> markup) {
        this.markup = markup;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
