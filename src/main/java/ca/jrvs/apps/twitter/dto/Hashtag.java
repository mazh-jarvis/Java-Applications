package ca.jrvs.apps.twitter.dto;

import java.util.List;

public class Hashtag {
    private List<Integer> indices;
    private String text;

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
