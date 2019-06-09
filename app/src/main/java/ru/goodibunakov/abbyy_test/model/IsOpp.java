package ru.goodibunakov.abbyy_test.model;

import androidx.annotation.NonNull;

public class IsOpp {
    private String IsItalics;

    private String Node;

    private String IsOptional;

    private String Text;

    private String IsAccent;

    public String getIsItalics() {
        return IsItalics;
    }

    public void setIsItalics(String IsItalics) {
        this.IsItalics = IsItalics;
    }

    public String getNode() {
        return Node;
    }

    public void setNode(String Node) {
        this.Node = Node;
    }

    public String getIsOptional() {
        return IsOptional;
    }

    public void setIsOptional(String IsOptional) {
        this.IsOptional = IsOptional;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getIsAccent() {
        return IsAccent;
    }

    public void setIsAccent(String IsAccent) {
        this.IsAccent = IsAccent;
    }

    @NonNull
    @Override
    public String toString() {
        return "ClassPojo [IsItalics = " + IsItalics + ", Node = " + Node + ", IsOptional = " + IsOptional + ", Text = " + Text + ", IsAccent = " + IsAccent + "]";
    }
}
