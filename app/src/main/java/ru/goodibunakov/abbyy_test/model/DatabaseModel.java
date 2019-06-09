package ru.goodibunakov.abbyy_test.model;

import androidx.annotation.NonNull;


public class DatabaseModel {
    private String title;
    private String international;
    private String internationalValue;
    private String translations;
    private String p2_0;
    private String p2_1;
    private String p2_2;
    private String p2_3;

    public DatabaseModel(String title, String international, String internationalValue, String translations, String p2_0, String p2_1, String p2_2, String p2_3) {
        this.title = title;
        this.international = international;
        this.internationalValue = internationalValue;
        this.translations = translations;
        this.p2_0 = p2_0;
        this.p2_1 = p2_1;
        this.p2_2 = p2_2;
        this.p2_3 = p2_3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international;
    }

    public String getInternationalValue() {
        return internationalValue;
    }

    public void setInternationalValue(String internationalValue) {
        this.internationalValue = internationalValue;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public String getP2_0() {
        return p2_0;
    }

    public void setP2_0(String p2_0) {
        this.p2_0 = p2_0;
    }

    public String getP2_1() {
        return p2_1;
    }

    public void setP2_1(String p2_1) {
        this.p2_1 = p2_1;
    }

    public String getP2_2() {
        return p2_2;
    }

    public void setP2_2(String p2_2) {
        this.p2_2 = p2_2;
    }

    public String getP2_3() {
        return p2_3;
    }

    public void setP2_3(String p2_3) {
        this.p2_3 = p2_3;
    }

    @NonNull
    @Override
    public String toString() {
        return "title = " + title + " international " + international + " internationalBalues = " + internationalValue + " translations = " + translations;
    }

}
