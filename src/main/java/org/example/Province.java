package org.example;

public enum Province {
    ALBERTA("AB"),
    BRITISH_COLUMBIA("BC"),
    MANITOBA("MB"),
    NEW_BRUNSWICK("NB"),
    NEWFOUNDLAND_LABRADOR("NL"),
    NORTHWEST_TERRITORIES("NT"),
    NOVA_SCOTIA("NS"),
    NUNAVUT("NU"),
    ONTARIO("ON"),
    PRINCE_EDWARD_ISLAND("PE"),
    QUEBEC("QC"),
    SASKATCHEWAN("SK"),
    YUKON("YT");

    private final String abbreviation;

    Province(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}