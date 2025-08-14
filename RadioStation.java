package com.virk.pakradiostations;

public class RadioStation {
    private String name;
    private String streamUrl;
    private String logoUrl;
    private String genre;

    public RadioStation(String name, String streamUrl, String logoUrl, String genre) {
        this.name = name;
        this.streamUrl = streamUrl;
        this.logoUrl = logoUrl;
        this.genre = genre;
    }

    // Getters
    public String getName() { return name; }
    public String getStreamUrl() { return streamUrl; }
    public String getLogoUrl() { return logoUrl; }
    public String getGenre() { return genre; }
}