package com.aladdindev.src;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonToCsv {

    private JSONArray jsonArray;

    /**
     * Construct a JsonToCsv from String
     *
     * @param jsonArray JSONArray
     * @throws JSONException If there is a syntax error.
     */
    public JsonToCsv(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    /**
     * @return JSONArray
     */
    private JSONArray getJSONArray() {
        return this.jsonArray;
    }

    /**
     * Get JSONArray as String
     *
     * @return String
     */
    public String toString() {
        return CDL.toString(this.getJSONArray());
    }


    public void exportCsv() {
        String output = "cities.csv";
        try {
            File file = new File(output);
            String csv = this.toString();
            FileUtils.writeStringToFile(file, csv);
            System.out.println(output + " is Created with Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
