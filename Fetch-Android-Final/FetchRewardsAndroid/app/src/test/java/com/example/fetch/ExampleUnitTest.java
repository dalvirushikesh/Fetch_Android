package com.example.fetch;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFetchJsonFromUrl() {
        String testUrl = "https://fetch-hiring.s3.amazonaws.com/hiring.json"; // Use a valid URL for testing

        try {
            JSONArray jsonArray = FetchData.fetchJsonFromUrl(testUrl);
            assertNotNull("JSON Array should not be null", jsonArray);
        } catch (IOException | JSONException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
