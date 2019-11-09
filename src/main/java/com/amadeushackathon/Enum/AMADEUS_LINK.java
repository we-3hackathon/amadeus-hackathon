package com.amadeushackathon.Enum;

public enum AMADEUS_LINK {

    AMADEUS_API("https://test.api.amadeus.com"),
    ACCESS_TOKEN(""),
    OFFERS_SEARCH("https://test.api.amadeus.com/v2/shopping/flight-offers")
    ;

    private String txt;

    AMADEUS_LINK(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return txt;
    }

    public void updateKeyWith(String text) {
        this.txt = text;
    }

}
