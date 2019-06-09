package ru.goodibunakov.abbyy_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TranslateModel {
    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Body")
    @Expose
    private List<Body> bodyList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Body> getBody() {
        return bodyList;
    }

    public void setBody(List<Body> bodyList) {
        this.bodyList = bodyList;
    }
}
