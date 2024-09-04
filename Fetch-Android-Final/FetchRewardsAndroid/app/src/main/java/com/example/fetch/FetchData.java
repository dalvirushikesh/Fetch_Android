package com.example.fetch;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Utility class to fetch JSON data from a URL.
 */
public class FetchData {

    /**
     * Fetches JSON data from the given URL.
     *
     * @param url URL to fetch data from.
     * @return JSON array from the URL.
     * @throws IOException if there's an error opening the stream.
     * @throws JSONException if the data can't be parsed.
     */
    public static JSONArray fetchJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONArray(jsonText);
        }
    }

    /**
     * Reads all characters from a Reader into a String.
     *
     * @param rd Reader to read from.
     * @return Contents of the Reader as a String.
     * @throws IOException if there's an error reading.
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
