package com.example.transfer.simpleClass;

public class Language {
    private final String title;
    private final String subtitle;

    public Language(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
