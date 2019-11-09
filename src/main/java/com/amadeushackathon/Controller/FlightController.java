package com.amadeushackathon.Controller;


import com.amadeushackathon.Enum.AMADEUS_LINK;
import com.google.gson.Gson;
import com.oracle.javafx.jmx.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @CrossOrigin()
    @GetMapping("/test")
    public String testController(){
        return "connection: OK with " + "test_server";
    }

    @CrossOrigin()
    @GetMapping("/auth")
    public String getAuthToken(){

        String URLink = "";

        try {

            String body = "grant_type=client_credentials&client_id=2mKxD7Wc3fSD0xpDQihYlLGXqODgiV7w&client_secret=pOwybs4X5ZM8nC1V";

            URL url = new URL(String.format(AMADEUS_LINK.AMADEUS_API + "/v1/security/oauth2/token"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //conn.setRequestProperty("Content-Length", "");

            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            outStreamWriter.write(body);
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            System.out.println("Output from Server .... \n");

            StringBuilder sb = new StringBuilder();

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

            try {
                JSONObject obj = new JSONObject(sb.toString());

                String token = obj.getString("access_token");

                AMADEUS_LINK.ACCESS_TOKEN.updateKeyWith(token);

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        } catch (MalformedURLException e) {


            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return URLink;
    }

    @CrossOrigin()
    @GetMapping("/all-destinations/{fromCountry}")
    public String getAllDestinations(@PathVariable String fromCountry){

        String URLink = "";

        try {

            URL url = new URL(String.format(AMADEUS_LINK.AMADEUS_API + "/v1/shopping/flight-destinations?origin=%s", fromCountry));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            conn.setRequestProperty("Authorization", "Bearer " + AMADEUS_LINK.ACCESS_TOKEN.toString());


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder sb = new StringBuilder();


            System.out.println("Output from Server .... \n");

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

            try {
                JSONObject obj = new JSONObject(sb.toString());

                return obj.toString();


            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        } catch (MalformedURLException e) {


            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }


        return URLink;
    }

    @CrossOrigin()
    @GetMapping("/offer-search/{fromCountry}/{toCountry}/{departureDate}/{adults}")
    public String getFlightsInRange(@PathVariable String fromCountry,@PathVariable String toCountry,@PathVariable String departureDate,@PathVariable String adults) {

        String URLink = "";

        try {

            URL url = new URL(String.format(AMADEUS_LINK.OFFERS_SEARCH.toString()
                    + "?originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&adults=%s",
                    toCountry, fromCountry, departureDate, adults));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            conn.setRequestProperty("Authorization", "Bearer " + AMADEUS_LINK.ACCESS_TOKEN.toString());


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            System.out.println("Output from Server .... \n");

            StringBuilder sb = new StringBuilder();


            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

            try {
                JSONObject obj = new JSONObject(sb.toString());

                return obj.toString();

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        } catch (MalformedURLException e) {


            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        
        return URLink;

    }
}
