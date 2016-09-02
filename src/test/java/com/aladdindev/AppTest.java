package com.aladdindev;

import com.aladdindev.src.GoeuroApi;
import com.aladdindev.src.GoeuroApiResponse;
import com.aladdindev.src.JsonToCsv;
import com.aladdindev.src.interfaces.GoeuroApiInterface;
import com.aladdindev.src.interfaces.GoeuroApiResponseInterface;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONArray;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Goeuro API Test
     */
    public void testGoeuroAPI() {
        GoeuroApiInterface goeuroApi1 = new GoeuroApi("Berlin");
        GoeuroApiInterface goeuroApi2 = new GoeuroApi("Tunis");
        GoeuroApiInterface goeuroApi3 = new GoeuroApi("goeuro");
        assertNotNull(goeuroApi1.getSuggestions());
        assertNotNull(goeuroApi2.getSuggestions());
        assertNotSame(goeuroApi1.getSuggestions(), goeuroApi2.getSuggestions());
        assertEquals("[]", goeuroApi3.getSuggestions());
    }

    /**
     * Goeuro API Response Test
     */
    public void testGoeuroAPIResponse() {
        GoeuroApiInterface goeuroApi1 = new GoeuroApi("Berlin");
        GoeuroApiInterface goeuroApi2 = new GoeuroApi("Tunis");
        String response1 = goeuroApi1.getSuggestions();
        String response2 = goeuroApi2.getSuggestions();

        GoeuroApiResponseInterface goeuroApiResponse1 = new GoeuroApiResponse(response1);
        GoeuroApiResponseInterface goeuroApiResponse2 = new GoeuroApiResponse(response2);


        JSONArray jsonResponse1 = goeuroApiResponse1.getResponseByKeys();
        JSONArray jsonResponse2 = goeuroApiResponse2.getResponseByKeys();

        assertNotNull(jsonResponse1);
        assertNotNull(jsonResponse2);

        assertNotSame(jsonResponse1, jsonResponse2);
    }

    /**
     * Goeuro JsonToCsv Test
     */
    public void testJsonToCsv() {
        GoeuroApiInterface goeuroApi1 = new GoeuroApi("Berlin");
        GoeuroApiInterface goeuroApi2 = new GoeuroApi("Tunis");
        String response1 = goeuroApi1.getSuggestions();
        String response2 = goeuroApi2.getSuggestions();

        GoeuroApiResponseInterface goeuroApiResponse1 = new GoeuroApiResponse(response1);
        GoeuroApiResponseInterface goeuroApiResponse2 = new GoeuroApiResponse(response2);


        JSONArray jsonResponse1 = goeuroApiResponse1.getResponseByKeys();
        JSONArray jsonResponse2 = goeuroApiResponse2.getResponseByKeys();

        JsonToCsv jsonToCsv1 = new JsonToCsv(jsonResponse1);
        JsonToCsv jsonToCsv2 = new JsonToCsv(jsonResponse2);

        assertNotNull(jsonToCsv1.toString());
        assertNotNull(jsonToCsv2.toString());
        assertNotSame(jsonToCsv1.toString(), jsonToCsv2.toString());

        jsonToCsv1.exportCsv();

        File f = new File("cities.csv");
        assertTrue(f.exists());
        assertTrue(!f.isDirectory());
    }
}
