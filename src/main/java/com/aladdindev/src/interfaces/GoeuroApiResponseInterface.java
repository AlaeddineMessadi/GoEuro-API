package com.aladdindev.src.interfaces;

import org.json.JSONArray;

public interface GoeuroApiResponseInterface {

    JSONArray parseSuggestions(String jsonString);

    JSONArray getResponseByKeys();
}
