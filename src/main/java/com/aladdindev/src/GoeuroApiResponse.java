package com.aladdindev.src;

import com.aladdindev.src.interfaces.GoeuroApiResponseInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoeuroApiResponse implements GoeuroApiResponseInterface {

    private String response;
    private JSONArray jsonArray;

    public GoeuroApiResponse(String response) {
        this.response = response;
        this.jsonArray = this.parseSuggestions(this.response);
    }

    public GoeuroApiResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    /**
     * Get _id, name, type, latitude, longitude as JSONArray
     *
     * @return JSONArray
     */
    public JSONArray getResponseByKeys() {

        JSONArray jsonArrayBuilder = new JSONArray();
        for (int i = 0; i < this.jsonArray.length(); ++i) {
            JSONObject jsonObject = new JSONObject();

            JSONObject obj = this.jsonArray.getJSONObject(i);
            JSONObject geoPosition = obj.getJSONObject("geo_position");
            jsonObject.put("_id", obj.getInt("_id"))
                    .put("name", obj.getString("name"))
                    .put("type", obj.getString("type"))
                    .put("latitude", geoPosition.getBigDecimal("latitude"))
                    .put("longitude", geoPosition.getBigDecimal("longitude"));

            jsonArrayBuilder.put(jsonObject);
        }

        return jsonArrayBuilder;

    }

    public JSONArray parseSuggestions(String jsonString) {
        JSONArray response = new JSONArray();
        try {
            // in case Goeuro API return a JSONObject
            if (jsonString.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonString);
                response = jsonObject.getJSONArray("");
            } else {
                response = new JSONArray(jsonString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }
}
