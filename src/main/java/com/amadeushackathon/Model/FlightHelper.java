package com.amadeushackathon.Model;

public class FlightHelper {

    public static String getAirlineName(String subCode){

        switch(subCode){

            case("KL"):
                return "KLM ROYAL DUTCH AIRLINES";
            case("6X"):
                return "Amadeus Six";
            case("UX"):
                return "Air Europa";
            case("VY"):
                return "Vueling Airlines";
            case("AF"):
                return "Air France";
            case("I2"):
                return "Iberia Express";
            case("IB"):
                return "Iberia";
            case("BA"):
                return "British Airways";
            case("TP"):
                return "TAP Portugal";
            default:
                return "its a airline";

/*
            6X	:	AMADEUS SIX
            UX	:	AIR EUROPA
            VY	:	VUELING AIRLINES
            AF	:	AIR FRANCE
            I2	:	IBERIA EXPRESS
            LX	:	SWISS INTERNATIONAL AIR LINES
            CT	:	ALITALIA CITY LINER SPA
            A3	:	AEGEAN AIRLINES
            IB	:	IBERIA
            AZ	:	ALITALIA S.P.A. IN A.S
            2L	:	HELVETIC AIRWAYS
            NI	:	PORTUGALIA
            LG	:	LUXAIR
            SN	:	BRUSSELS AIRLINES
            TP	:	TAP PORTUGAL
            LH	:	LUFTHANSA
            BA	:	BRITISH AIRWAYS
*/
        }



    }

}
