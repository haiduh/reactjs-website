package com.github.group37.roadmap.other.enums;

public enum LevelOfExpertise {
    NOVICE("NOVICE"),
    INTERMEDIATE("INTERMEDIATE"),
    EXPERT("EXPERT");

    private final String value;

    LevelOfExpertise(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LevelOfExpertise fromValue(String value) {
        for (LevelOfExpertise expertise : values()) {
            if (expertise.getValue().equalsIgnoreCase(value)) {
                return expertise;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}