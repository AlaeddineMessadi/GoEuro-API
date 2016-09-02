package com.aladdindev.src;

import com.aladdindev.src.interfaces.GoeuroApiInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GoeuroApi implements GoeuroApiInterface {

    /**
     * Static Goeuro endpoint
     */
    private static final String ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";

    private String city;
    private String response;

    public GoeuroApi(String city) {
        this.city = city;
        this.response = this.getSuggestions();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Get Position Suggest from Goeuro Endpoint as a String
     *
     * @return response String
     */
    public String getSuggestions() {

        String response = "";
        try {
            URL url = new URL(ENDPOINT + this.city);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp;
            while (null != (strTemp = br.readLine())) {
                response += strTemp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }
}
