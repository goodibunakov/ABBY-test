package ru.goodibunakov.abbyy_test.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("Markup")
    @Expose
    private List<Markup2> markup = null;
    @SerializedName("Node")
    @Expose
    private String node;
    @SerializedName("Text")
    @Expose
    private Object text;
    @SerializedName("IsOptional")
    @Expose
    private boolean isOptional;

    public List<Markup2> getMarkup() {
        return markup;
    }

    public void setMarkup(List<Markup2> markup) {
        this.markup = markup;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(Boolean isOptional) {
        this.isOptional = isOptional;
    }

}
