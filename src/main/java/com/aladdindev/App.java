package com.aladdindev;

import com.aladdindev.src.GoeuroApi;
import com.aladdindev.src.GoeuroApiResponse;
import com.aladdindev.src.interfaces.GoeuroApiInterface;
import com.aladdindev.src.interfaces.GoeuroApiResponseInterface;
import com.aladdindev.src.JsonToCsv;
import org.json.JSONArray;

/**
 * Goeuro Test
 */
public class App {

    public static void main(String[] args) {

        /*
         * Get all suggestions as String
         */
        GoeuroApiInterface goeuroApi = new GoeuroApi(args[0]);
        String fullResponse = goeuroApi.getSuggestions();

        /*
         * Defining GoeuroApiInterface
         */
        GoeuroApiResponseInterface goeuroAPiResponse = new GoeuroApiResponse(fullResponse);

        /*
         * Get a JSONArray with only specific keys (_id,name,type,latitude, longitude)
         */
        JSONArray responseByKey = goeuroAPiResponse.getResponseByKeys();

        /*
         * Initializing JsonToCsv
         */
        JsonToCsv jsonToCsv = new JsonToCsv(responseByKey);

        /*
         *  export Json to Csv
         */
        jsonToCsv.exportCsv();
    }
}
